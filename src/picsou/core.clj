(ns picsou.core
  (:use [clojure.tools.logging :exclude [trace]]
        [plumbing.core])
  (:require [clojure.string :as strings]
            [picsou.engine :as engine]
            [picsou.time.obj :as time]
            [picsou.learn :as learn]
            [picsou.util :as util]
            [picsou.time.api :as api]
            [clojure.java.io :as io]
            [picsou.corpus :as corpus]
            [midje.repl]))

(defn dev [] (midje.repl/autotest))

(def rules-map (atom {}))
(def corpus-map (atom {}))
(def classifiers-map (atom {}))
(def default-context {:reference-time (time/t -2 2013 2 12 4 30)})

(defn- get-classifier
  [id]
  (when id
    (get @classifiers-map (keyword id))))

(defn- get-rules
  [id]
  (when id
    (get @rules-map (keyword id))))

(defn- compare-tokens
  "Compares two candidate tokens a and b for runtime selection.
  wanted-dim is a hash whose keys are the :dim wanted by the caller, the value
  can be anything truthy.
  Returns nil: not comparable 0: equal 1: greater -1: lesser"
  [a b classifiers wanted-dims]
  {:pre [(map? classifiers)]}
  (let [same-dim (= (:dim a) (:dim b))
        wanted-a (get wanted-dims (:dim a))
        wanted-b (get wanted-dims (:dim b))
        cmp-interval (util/compare-intervals
                       [(:pos a) (:end a)]
                       [(:pos b) (:end b)])] ; +1 0 -1 nil
  ;(printf "Comparing %d and %d \n" (:index a) (:index b))
  (if-not same-dim
    ; unless a is wanted and covers b, or the contrary, they are not comparable
    (cond (and wanted-a (= 1 cmp-interval)) 1
          (and wanted-b (= -1 cmp-interval)) -1
          :else nil)
    (if (not= 0 cmp-interval)
      cmp-interval ; one interval recovers the other
      (let [pa (learn/route-prob a classifiers) ; same interval
            pb (learn/route-prob b classifiers)] 
        ;(printf "Comparing %d (%f) and %d (%f) \n" (:index a) pa (:index b) pb)
        (compare pa pb))))))

(defn- select-winners
  "Winner= token that is not 'smaller' (in the sense of the provided partial
  order) than another winner, and that resolves to a value"
  ([compare-fn resolve-fn candidates]
    (select-winners compare-fn resolve-fn candidates []))
  ([compare-fn resolve-fn candidates already-selected]
    (if (seq candidates)
      (let [[maxima others] (util/split-by-partial-max 
                              compare-fn
                              candidates
                              (concat already-selected candidates))
            new-winners (->> maxima
                             (mapcat resolve-fn)
                             (filter :value))] ; remove unresolved
        (if (seq maxima)
          (recur compare-fn resolve-fn others (concat already-selected new-winners))
          already-selected))
      already-selected)))

(defn parse
  "Parse a sentence. Returns the stash and a curated list of winners.
   Targets is a coll of {:dim dim :label label} : only winners of these dims are
   kept, and they receive a :label key = the label provided.
   If no targets specified, all winners are returned."
  [s context module & [targets]]
  {:pre [s context module]}
  (let [classifiers (get-classifier module)
        _ (when-not (map? classifiers)
            (warnf "[picsou] classifiers is not a map s=%s module=%s targets=%s"
                   s module (util/spprint targets)))
        rules (get-rules module)
        stash (engine/pass-all s rules)
        ; add an index to tokens in the stash
        stash (map #(if (map? %1) (assoc %1 :index %2) %1)
                   stash
                   (iterate inc 0))
        dim-label (when targets (into {} (for [{:keys [dim label]} targets]
                                           [(keyword dim) label])))
        winners (->> stash
                     (filter :pos)
                     ; just keep the dims we want, and add the label key
                     (?>> dim-label (map #(when-let [label (get dim-label (:dim %))]
                                            (assoc % :label label))))
                     (remove nil?)
                     
                     (select-winners
                       #(compare-tokens %1 %2 classifiers dim-label)
                       #(engine/resolve-token % context module))
                     
                     ; add a confidence key
                     ; low confidence for numbers covered by datetime
                     (engine/estimate-confidence context module)
                     ; adapt the keys for the outside world
                     (map #(merge % {:start (:pos %)
                                     :end (:end %)
                                     :body (:text %)})))]
    {:stash stash :winners winners}))

(defn- print-stash
  "Print stash to STDOUT"
  [stash classifiers winners]
  (let [width (count (:text (first stash)))
        winners-indices (map :index winners)]
    (doseq [[tok i] (reverse (map vector stash (iterate inc 0)))]
      (let [pos (:pos tok)
            end (:end tok)]
        (if pos
          (printf "%s %s%s%s %2d | %-9s | %-25s | P = %04.4f | %.20s\n"
                  (if (some #{(:index tok)} winners-indices) "W" " ")
                  (apply str (repeat pos \space))
                  (apply str (repeat (- end pos) \-))
                  (apply str (repeat (- width end -1) \space))
                  i
                  (when-let [x (:dim tok)] (name x))
                  (when-let [x (-> tok :rule :name)] (name x))
                  (float (learn/route-prob tok classifiers))
                  (strings/join " + " (mapv #(get-in % [:rule :name]) (:route tok))))
          (printf "  %s\n" (:text tok)))))))

(defn- print-tokens
  "Recursively prints a tree representing a route"
  ([tokens classifiers]
    {:pre [(coll? tokens)]}
    (let [tokens (if (vector? tokens)
                   tokens
                   [tokens])
          tokens (if (= 1 (count tokens))
                   tokens
                   [{:route tokens :rule {:name "root"}}])]
      (print-tokens tokens classifiers 0)))
  ([tokens classifiers depth]
    (print-tokens tokens classifiers depth ""))
  ([tokens classifiers depth prefix]
    (doseq [[token i] (map vector tokens (iterate inc 1))]
      (let [;; determine name to display
            name (if-let [name (get-in token [:rule :name ])]
                   name
                   (str "text: " (:text token)))
            p (learn/route-prob token classifiers)
            ;; prepare children prefix
            last? (= i (count tokens))
            new-prefix (if last? \space \|)
            new-prefix (str prefix new-prefix \space \space \space)]
        (when (pos? depth)
          (print (format "%s%s-- "
                   prefix
                   (if last? \` \|))))
        (println (format "%s (%s)" name p))
        (print-tokens (:route token)
          classifiers
          (inc depth)
          (if (pos? depth) new-prefix ""))))))

(defn- merge-rules
  [current config-key new-file]
  (let [new-file (io/resource (str "picsou/rules/" new-file ".clj"))
        new-rules (engine/rules (read-string (slurp new-file)))]
    (assoc
        current
      config-key
      (concat (config-key current) new-rules))))

(defn- merge-corpus
  [current config-key new-file]
  (let [new-file (io/resource (str "picsou/corpus/" new-file ".clj"))
        new-corpus (corpus/read-corpus new-file)]
    (assoc
        current
      config-key
      (util/merge-according-to {:tests concat :context merge} (config-key current) new-corpus))))

(defn run-corpus
  "Run the corpus given in parameter for the given module.
  Returns a list of vectors [0|1 text error-msg]"
  [{context :context, tests :tests} module]
  (for [test tests
        text (:text test)]
    (try
      (let [{:keys [stash winners]} (parse text context module)
            winner-count (count winners)
            check (first (:checks test)) ; only one test is supported now
            check-results (map (partial check context) winners)] ; return nil if OK, [expected actual] if not OK
        (if (some #(or (nil? %) (false? %)) check-results)
          [0 text nil]
          [1 text [(first (first check-results)) (map second check-results)]]))
      (catch Exception e
        [1 text (.getMessage e)]))))

;; default context is the same as the corpus context
(defn play
  "Show processing details for one sentence. Defines a 'details' function."
  ([module-id s]
   (play module-id s nil))
  ([module-id s targets]
   (play module-id s targets default-context))
  ([module-id s targets context]
   (let [targets (when targets (map (fn [dim] {:dim dim :label dim}) targets))
         {stash :stash
          winners :winners} (parse s context module-id targets)]
     
     ;; 1. print stash
     (print-stash stash (get-classifier module-id) winners)

     ;; 2. print winners
     (printf "\n%d winners:\n" (count winners))
     (doseq [winner winners]
       (printf "%-25s %s %s\n" (str (name (:dim winner))
                                     (if (:latent winner) " (latent)" ""))
                               (api/export-value winner)
                               (dissoc winner :value :route :rule :pos :text :end :index
                                               :dim :start :latent :body :pred :timezone)))

     ;; 3. ask for details
     (printf "For further info: (details idx) where 1 <= idx <= %d\n" (dec (count stash)))
     (def details (fn [n]
                    (print-tokens (nth stash n) (get-classifier module-id))))
     (def token (fn [n]
                  (nth stash n))))))

(defn run
  "Runs the corpus and prints the results to the terminal."
  ([]
   (run (keys @corpus-map)))
  ([module-id]
   (loop [[mod & more] (if (seq? module-id) module-id [module-id])
          line 0
          acc []]
     (if mod
       (let [output (run-corpus (mod @corpus-map) mod)
             failed (remove (comp (partial = 0) first) output)]
         (doseq [[[error-count text error-msg] i] (map vector failed (iterate inc line))]
           (printf "%d FAIL \"%s\"\n    Expected %s\n" i text (first error-msg))
           (doseq [got (second error-msg)]
             (printf "    Got      %s\n" got)))
         (printf "%s: %d examples, %d failed.\n" mod (count output) (count failed))
         (recur more (+ line (count failed)) (concat acc (map (fn [[_ t _]] [mod t]) failed))))
       (defn c [n] 
         (let [[mod text] (nth acc n)]
           (printf "(play %s \"%s\")\n" mod text)
           (play mod text)))))))

(defn load!
  "Load/Reload rules and classifiers from the config in parameter.
  If no config provided, load the default config"
  ([]
   (let [config (-> "default-config.clj" io/resource slurp read-string)]
     (load! config)))
  ([config]
   (reset! rules-map {})
   (reset! corpus-map {})
   (reset! classifiers-map {})
   (doseq [[config-key {corpus-files :corpus rules-files :rules}] config]
     (info "Loading module " config-key)
     (doseq [corpus-file corpus-files]
       (swap! corpus-map merge-corpus config-key corpus-file))
     (doseq [rules-file rules-files]
       (swap! rules-map merge-rules config-key rules-file)))
   (doseq [[config-key rules] @rules-map]
     (swap! classifiers-map assoc config-key
            (learn/train-classifiers (get @corpus-map config-key)
                                     rules
                                     learn/extract-route-features)))))

(defn- generate-context
  [base-context]
  (-> base-context
      (?> (instance? org.joda.time.DateTime (:reference-time base-context))
          (assoc :reference-time {:start (:reference-time base-context)
                                  :grain :second}))))

(defn extract
  "Public API. Leven-stash is ignored for the moment.
   targets is a coll of maps {:module :dim :label} for instance:
   {:module fr$core, :dim duration, :label wit$duration} to get duration results
   Returns a single coll of tokens with :body :value :start :end :label (=wisp) :latent"
  [sentence context leven-stash targets]
  {:pre [(string? sentence)
         (map? context)
         (:reference-time context)
         (vector? targets)]}
  (try
    (infof "Extracting from '%s' with targets %s" sentence targets)
    (letfn [(extract'
              [module targets] ; targets specify all the dims we should extract
              (let [module (keyword module)
                    pic-context (generate-context context)]
                (when-not (module @rules-map)
                  (throw (ex-info "Unknown picsou module" {:module module})))
                (->> (parse sentence pic-context module targets)
                     :winners
                     (map #(assoc % :value (engine/export-value %)))
                     (map #(select-keys % [:label :body :value :start :end :latent])))))]
      (->> targets
           (group-by :module) ; we want to run each config only once
           (mapcat (fn [[module targets]] (extract' module targets)))
           vec))
    (catch Exception e
      (let [err {:e e
                 :sentence sentence
                 :context context
                 :leven-stash leven-stash
                 :targets targets}]
        (errorf e "picsou error err=%s" (pr-str err))
        []))))
