(ns duckling.core
  (:use [clojure.tools.logging :exclude [trace]]
        [plumbing.core])
  (:require [clojure.java.io :as io]
            [clojure.set :as set]
            [clojure.string :as string]
            [duckling.corpus :as corpus]
            [duckling.engine :as engine]
            [duckling.learn :as learn]
            [duckling.resource :as res]
            [duckling.time.api :as api]
            [duckling.time.obj :as time]
            [duckling.util :as util]))

(defonce rules-map (atom {}))
(defonce corpus-map (atom {}))
(defonce classifiers-map (atom {}))

(defn default-context
  "Build a default context for testing. opt can be either :corpus or :now"
  [opt]
  {:reference-time (case opt
                     :corpus (time/t -2 2013 2 12 4 30)
                     :now (time/now))})

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
      (compare (:log-prob a) (:log-prob b))))))

(defn- select-winners*
  [compare-fn resolve-fn already-selected candidates]
  (if (seq candidates)
    (let [[maxima others] (util/split-by-partial-max
                           compare-fn
                           candidates
                           (concat already-selected candidates))
          new-winners (->> maxima
                           (mapcat resolve-fn)
                           (filter :value))] ; remove unresolved
      (if (seq maxima)
        (recur compare-fn resolve-fn (concat already-selected new-winners) others)
        already-selected))
    already-selected))

(defn- select-winners
  "Winner= token that is not 'smaller' (in the sense of the provided partial
  order) than another winner, and that resolves to a value"
  [compare-fn log-prob-fn resolve-fn candidates]
  (->> candidates
       (map #(assoc % :log-prob (log-prob-fn %)))
       (select-winners* compare-fn resolve-fn [])
       (map #(dissoc % :log-prob))))

(defn analyze
  "Parse a sentence, returns the stash and a curated list of winners.
   Targets is a coll of {:dim dim :label label} : only winners of these dims are
   kept, and they receive a :label key = the label provided.
   If no targets specified, all winners are returned."
  [s context module targets base-stash]
  {:pre [s context module]}
  (let [classifiers (get-classifier module)
        _ (when-not (map? classifiers)
            (errorf "[duckling] Module %s is not loaded. Did you (load!)?" module))
        rules (get-rules module)
        stash (engine/pass-all s rules base-stash)
        ; add an index to tokens in the stash
        stash (map #(if (map? %1) (assoc %1 :index %2) %1)
                   stash
                   (iterate inc 0))
        dim-label (when (seq targets) (into {} (for [{:keys [dim label]} targets]
                                           [(keyword dim) label])))
        winners (->> stash
                     (filter :pos)
                     ; just keep the dims we want, and add the label key
                     (?>> dim-label (keep #(when-let [label (get dim-label (:dim %))]
                                             (assoc % :label label))))

                     (select-winners
                       #(compare-tokens %1 %2 classifiers dim-label)
                       #(learn/route-prob % classifiers)
                       #(engine/resolve-token % context module))

                     ; add a confidence key
                     ; low confidence for numbers covered by datetime
                     (engine/estimate-confidence context module)
                     ; adapt the keys for the outside world
                     (map (fn [{:keys [pos end text] :as token}]
                            (merge token {:start pos
                                          :end end
                                          :body text}))))]
    {:stash stash :winners winners}))


;--------------------------------------------------------------------------
; REPL utilities
;--------------------------------------------------------------------------

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
                  (string/join " + " (mapv #(get-in % [:rule :name]) (:route tok))))
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

(defn play
  "Show processing details for one sentence. Defines a 'details' function."
  ([module-id s]
   (play module-id s nil))
  ([module-id s targets]
   (play module-id s targets (default-context :corpus)))
  ([module-id s targets context]
   (let [targets (when targets (map (fn [dim] {:dim dim :label dim}) targets))
         {stash :stash
          winners :winners} (analyze s context module-id targets nil)]

     ;; 1. print stash
     (print-stash stash (get-classifier module-id) winners)

     ;; 2. print winners
     (printf "\n%d winners:\n" (count winners))
     (doseq [winner winners]
       (printf "%-25s %s %s\n" (str (name (:dim winner))
                                     (if (:latent winner) " (latent)" ""))
                               (engine/export-value winner {:date-fn str})
                               (dissoc winner :value :route :rule :pos :text :end :index
                                               :dim :start :latent :body :pred :timezone :values)))

     ;; 3. ask for details
     (printf "For further info: (details idx) where 1 <= idx <= %d\n" (dec (count stash)))
     (def details (fn [n]
                    (print-tokens (nth stash n) (get-classifier module-id))))
     (def token (fn [n]
                  (nth stash n))))))

;--------------------------------------------------------------------------
; Configuration loading
;--------------------------------------------------------------------------

(defn- gen-config-for-lang
  "Generates the full config for a language from directory structure."
  [lang]
  (->> ["corpus" "rules"]
       (map (fn [dir]
              (let [files (->> (format "languages/%s/%s" lang dir)
                               res/get-files
                               (filter (comp not #(.startsWith % "_")))
                               (map #(subs % 0 (- (count %) 4)))
                               vec)]
                [(keyword dir) files])))
       (into {})))

(defn- gen-config-for-langs
  "Generates the full config for langs from directory structure."
  [langs]
  (->> langs
       (map (fn [lang]
              [(keyword (format "%s$core" lang)) (gen-config-for-lang lang)]))
       (into {})))

(defn- read-rules
  [lang new-file]
  (-> (format "languages/%s/rules/%s.clj" lang new-file)
      io/resource
      slurp
      read-string
      engine/rules))

(defn- read-corpus
  [lang new-file]
  (-> (format "languages/%s/corpus/%s.clj" lang new-file)
      io/resource
      corpus/read-corpus))

(defn- make-corpus
  [lang corpus-files]
  (->> corpus-files
       (pmap (partial read-corpus lang))
       (reduce (partial util/merge-according-to {:tests concat :context merge}))))

(defn- make-rules
  [lang rules-files]
  (->> rules-files
       (pmap (partial read-rules lang))
       (apply concat)))

(defn- get-dims-for-test
  [context module {:keys [text]}]
  (mapcat (fn [text]
            (try
              (->> (analyze text context module nil nil)
                   :stash
                   (keep :dim))
              (catch Exception e
                (warnf "Error while analyzing module=%s context=%s text=%s"
                       module context text)
                [])))
          text))

(defn get-dims
  "Retrieves all available dimensions for module by running its corpus."
  [module {:keys [context tests]}]
  (->> tests
       (pmap (partial get-dims-for-test context module))
       (apply concat)
       distinct))

(defn load!
  "(Re)loads rules and classifiers for languages or/and config.
   If no language list nor config provided, loads all languages.
   Returns a map of loaded modules with available dimensions."
  ([] (load! nil))
  ([{:keys [languages config]}]
   (let [langs (seq languages)
         lang-config (when (or langs (empty? config))
                       (cond-> (set (res/get-subdirs "languages"))
                         langs (set/intersection (set langs))
                         true gen-config-for-langs))
         config (merge lang-config config)]
     (reset! rules-map {})
     (reset! corpus-map {})
     (let [data (->> config
                     (pmap (fn [[config-key {corpus-files :corpus rules-files :rules}]]
                             (let [lang (-> config-key name (string/split #"\$") first)
                                   corpus (make-corpus lang corpus-files)
                                   rules (make-rules lang rules-files)
                                   c (learn/train-classifiers corpus rules learn/extract-route-features)]
                               [config-key {:corpus corpus :rules rules :classifier c}])))
                     (into {}))]
       (doseq [[config-key {:keys [classifier corpus rules]}] data]
         (swap! corpus-map assoc config-key corpus)
         (swap! rules-map assoc config-key rules)
         (swap! classifiers-map assoc config-key classifier)))
     (->> @corpus-map
          (pmap (fn [[module corpus]]
                  [module (get-dims module corpus)]))
          (into {})))))


;--------------------------------------------------------------------------
; Corpus running
;--------------------------------------------------------------------------

(defn run-corpus
  "Run the corpus given in parameter for the given module.
  Returns a list of vectors [0|1 text error-msg]"
  [{context :context, tests :tests} module]
  (for [test tests
        text (:text test)]
    (try
      (let [{:keys [stash winners]} (analyze text context module nil nil)
            winner-count (count winners)
            check (first (:checks test)) ; only one test is supported now
            check-results (map (partial check context) winners)] ; return nil if OK, [expected actual] if not OK
        (if (some #(or (nil? %) (false? %)) check-results)
          [0 text nil]
          [1 text [(ffirst check-results) (map second check-results)]]))
      (catch Exception e
        [1 text (.getMessage e)]))))

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


;--------------------------------------------------------------------------
; Public API
;--------------------------------------------------------------------------

(defn parse
  "Public API. Parses text using given module. If dims are provided as a list of
  keywords referencing token dimensions, only these dimensions are extracted.
  Context is a map with a :reference-time key. If not provided, the system
  current date and time is used."
  ([module text]
   (parse module text []))
  ([module text dims]
   (parse module text dims (default-context :now)))
  ([module text dims context]
   (->> (analyze text context module (map (fn [dim] {:dim dim :label dim}) dims) nil)
        :winners
        (map #(assoc % :value (engine/export-value % {})))
        (map #(select-keys % [:dim :body :value :start :end :latent])))))


;--------------------------------------------------------------------------
; The stuff below is specific to Wit.ai and will be moved out of Duckling
;--------------------------------------------------------------------------

(defn- generate-context
  "Wit.ai internal. Will move to Wit."
  [base-context]
  (-> base-context
      (?> (instance? org.joda.time.DateTime (:reference-time base-context))
          (assoc :reference-time {:start (:reference-time base-context)
                                  :grain :second}))))

(defn extract
  "API used by Wit.ai (will be moved to Wit)
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
                  (throw (ex-info "Unknown duckling module" {:module module})))
                (->> (analyze sentence pic-context module targets leven-stash)
                     :winners
                     (map #(assoc % :value (engine/export-value % {:date-fn str})))
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
         (errorf e "duckling error err=%s" (pr-str err))
         []))))
