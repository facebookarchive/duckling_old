(ns picsou.engine
  "This component parses a phrase and extracts information based on rules.
  The two main phases are matching and production.
  1. rules are transformed into objets via rules macro
  2. rules are (recursively) matched based on theirs pattern vectors.
  3. tokens containing final info are produced using their production rules"
  (:require [clojure.set :as sets]
            [picsou.time.prod]
            [picsou.util :as util]
            [clj-time.coerce :as coerce]))

;;
;; Lookup and basic matching functions, used by patterns in rules
;;

(defn- re-pos
  "Finds regex matches in s, with their position and groups.
  Returns a vec of [pos matched_text groups]"
  [re s]
  (loop [m (re-matcher re s)
         res []]
    (if (.find m)
      (recur m
        (conj res
          [(.start m)
           (.group m)
           (vec (map #(.group m %) (range 1 (inc (.groupCount m)))))]))
      res)))

(defn- lookup-re
  "Lookup regex in s, starting at a given position, and builds one token per found match"
  [regex [{s :text}] position]
  {:pre [regex s]}
  (try
    (let [matches (for [[pos word groups] (re-pos regex (subs s position))]
                    (do
                      (when (clojure.string/blank? word)
                        (throw (ex-info "Zero-length or blank captures forbidden"
                                        {:regex regex :s s})))
                      {:pos (+ position pos)
                       :end (+ position pos (count word))
                       :text word
                       :groups groups}))]
      (->> matches
           (filter #(util/separated-substring? s (:pos %) (:end %)))))
    (catch Exception e
      (throw (ex-info "@lookup-re" {:exception e :s s :regex regex})))))

(defn- lookup-token
  "Finds tokens satisfying constraints"
  [pattern-filter stash]
  {:pre [pattern-filter]}
  ;; FIXME brute force approach that could be improved!
  ;; note that position is ignored at this point, adjacent? will need to do the job
  ;; this fn does not do much and could be avoided... but might be more complex later
  (try
    (filter pattern-filter stash)
    (catch Exception e
      (throw (Exception. "@Look-up token")))))

;;
;; Rules parsing
;;
(defn pattern-fn
  "Makes a pattern function from the pattern slice (regex...)"
  [pattern]
  (cond
    (instance? java.util.regex.Pattern pattern)
    (fn [stash position]
      (lookup-re pattern stash position))

    (map? pattern)
    (fn [stash position]
      (lookup-token #(util/hash-match pattern %) stash))

    (fn? pattern)
    (fn [stash position]
      (lookup-token pattern stash))

    :else (throw
            (Exception. (str "Unable to parse pattern: " (prn-str pattern) " class:" (class pattern))))))

(defn build-rule
  "Builds a new rule"
  [name pattern production]
  (if (not (string? name)) (throw (Exception. "Can't accept rule without name.")))
  (let [picsou-helper-ns (the-ns 'picsou.time.prod) ; could split time.patterns and time.prod helpers
        pattern (binding [*ns* picsou-helper-ns] (eval pattern))
        pattern-vec (if (vector? pattern) pattern [pattern])]
    {:name name
     :pattern (map pattern-fn pattern-vec)
     :production (binding [*ns* picsou-helper-ns]
                   (eval `(fn ~(vec (map #(symbol (str "%" %))
                                        (range 1 (inc (count pattern-vec)))))
                                        ~production)))}))

(defn rules
  "Parses a set of rules and 'add' them into 'the-rules'.
  Can be called several times, since rules might spread into several files."
  [forms]
  (let [triples (partition 3 forms)
        tests (mapv (fn [[nam pat prod]] (build-rule nam pat prod))
                triples)]
    tests))

;;
;; Runtime parsing (core algorithm)
;;
(defn- adjacent?
  "True if token is adjacent following position (like, there are just spaces
   from position to the beginning of the token)"
  [position token [{s :text}]]
  {:pre [(number? position) (map? token) (string? s)]}
  (try
    (if (< (:pos token) position)
      false ; token starts before position... no chance it's following position
      (let [separation (subs s position (:pos token))]
        (re-find #"^[\p{Space}-]*$" separation)))
    (catch Exception e
      (throw (Exception. (str "@adjacent? " e))))))

(defn- produce
  "Produce a new token when a rule has matched.
  The 'route' is a seq of tokens, it is provided by the 'match' function"
  [rule route sentence]
  {:pre [rule route sentence]}
  (let [pos (:pos (first route))
        end (:end (last route))]
    (try
      (when-let [product (apply (:production rule) route)]
        (merge product
               {:text (subs sentence pos end), :pos pos, :end end, :rule rule, :route route}))
      (catch Exception e
        (throw (ex-info (format "Exception picsou@produce span='%s' rule='%s' sentence='%s' ex='%s' stack=%s"
                                    (subs sentence pos end)
                                    (:name rule)
                                    sentence
                                    e (.printStackTrace e))
                        {:exception e}))))))

(defn- never-produced?
  "Check if a token, about to be produced by 'rule' on 'route', is not already in the 'stash'.
   Two tokens are different if either the rule which produce them, or the route, is different."
  ;; TODO use signatures for efficiency
  [stash rule route]
  {:pre [stash rule route]}
  (every? #(or (not= rule (:rule %)) (not= route (:route %))) stash))

(defn- match
  "Tries to match 'pattern' in the 'stash'.
  Return a seq of routes. A route is a seq of tokens."
  [pattern stash]
  (letfn [(match-recur [pattern first-pattern? stash position route results]
            (if (empty? pattern)
              (cons route results) ;; add "finished" route to results and return
              (try
                (apply concat
                  (for [token ((first pattern) stash position)
                        :when (or first-pattern? (adjacent? position token stash))]
                    (match-recur (rest pattern)
                      false
                      stash
                      (:end token)
                      (conj route token)
                      results)))
                (catch Exception e
                  (.printStackTrace e)
                  (prn stash)
                  (throw (ex-info "Exception @match" {:exception e}))))))]
    (match-recur pattern true stash 0 [] [])))

(defn- pass-once
  "Make one pass of each rule on the stash.
  Returns a new stash augmented with the seq of produced tokens."
  [stash rules sentence]
  (into stash ; we want a vector, not a list, or into changes the order of items
    (apply concat
      (for [rule rules]
        (try
          (->> (match (:pattern rule) stash) ; get the routes that match this rule
               (filter #(never-produced? stash rule %)) ; remove what we already have
               (map (fn [route] (produce rule route sentence)))) ; produce
          (catch Exception e
            (throw (Exception. (str "Exception matching rule: "
                                 (:name rule) " " e)))))))))

(defn pass-all
  "Make as many passes as necessary until no new tokens are produced
  (there is a limit to avoid infinite loops though)"
  [sentence rules]
  (loop [stash [{:text sentence}]
         prev-stash-size 0
         ; safeguard: number of max iterations (loops DO occur :))
         remaining-iter 10]
    (if (> (count stash) prev-stash-size)
      (if (> remaining-iter 0)
        (recur (pass-once stash rules sentence) (count stash) (dec remaining-iter))
        (throw (Exception. (str "@pass-all reached maximum iterations for sentence '" sentence "'"))))
      stash)))

(defn maxlen-judge
  "Choose the winning token in the stash."
  [stash]
  (apply max-key (fn [token]
                   (if (:pos token)
                     (- (:end token) (:pos token))
                     0))
    stash))

(defn resolve-token
  "Resolve a token based on its dimension, predicate, and the context.
  Returns a coll of tokens, since they can have multiple resolutions, or none.
  Unresolved tokens are returned as is.
  Fields are put at the :value level for all dims"
  [token context module]
  (let [values (picsou.time.pred/resolve token context)] ; ns should be dynamic based on dim ; or better use a protocol
    (if-not (empty? values)
      (map #(assoc token :value %) values)
      [(assoc token :not-resolved true)]))) ; return token if not resolved with flag

(defn estimate-confidence
  "Returns the tokens with :confidence a rough confidence estimation for each.
   Numbers covered by datetime are very unlikely."
  [context module tokens]
  tokens)
