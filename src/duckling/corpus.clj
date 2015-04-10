(ns duckling.corpus
  (:use     [clojure.tools.logging]
            [plumbing.core :except [millis]])
  (:require [duckling.time.obj :as time]
            [duckling.util :as util]))

; Checker functions return *nil* when OK, or [expected actual] when not OK

(defn- vec->date-and-map
  "Turns a vector of args into a date and a map of extra fields"
  [args]
  (let [[date-fields other-keys-and-values] (split-with integer? args)
        token-fields (into {} (map vec (partition 2 other-keys-and-values)))
        date (-> (apply time/t -2 date-fields)
                 (?> (:grain token-fields) (assoc :grain (:grain token-fields)))
                 (?> (:timezone token-fields) (assoc :timezone (:timezone token-fields))))]
    [date token-fields]))

(defn datetime
  "Creates a datetime checker function to check if the token is valid"
  [& args]
  (let [[date token-fields] (vec->date-and-map args)]
    (fn [context token]
        (when-not
          (and
            (= :time (:dim token))
            (util/hash-match (select-keys token-fields [:direction :precision])
                             token)
            (= (-> token :value) date))
          [date (:value token)]))))

(defn datetime-interval
  "Creates a datetime interval checker function"
  [from to]
  (let [[start start-fields] (vec->date-and-map from)
        [end end-fields] (vec->date-and-map to)
        date (time/interval start end)]
    (fn [context {:keys [value dim] :as token}]
      (when-not
        (and
          (= :time dim)
          (= value date))
        [date value]))))

(defn number
  "check if the token is a number equal to value.
  If value is integer, it also checks :integer true"
  [value]
  (fn [_ token] (when-not
                  (and
                    (= :number (:dim token))
                    (or (not (integer? value)) (:integer token))
                    (= (:value token) value))
                  [value (:value token)])))

(defn ordinal
  [value]
  (fn [_ token] (when-not
                  (and
                    (= :ordinal (:dim token))
                    (= (:value token) value))
                  [value (:value token)])))

(defn temperature
  "Create a temp condition"
  [value' & [unit' precision']]
  (fn [_ {:keys [dim value unit precision] :as token}]
    (not (and
                  (= :temperature dim)
                  (= value' value)
                  (= unit' unit)
                  (= precision' precision)))))

(defn distance
  "Create a distance condition"
  [value' & [unit' normalized' precision']]
  (fn [_ {:keys [dim value unit normalized precision] :as token}]
    (not (and
                  (= :distance dim)
                  (= value' value)
                  (= unit' unit)
                  (= normalized' normalized)
                  (= precision' precision)))))

(defn money
  "Create a amount-of-money condition"
  [value' & [unit' precision']]
  (fn [_ {:keys [dim value unit precision] :as token}]
    (not (and
                  (= :amount-of-money dim)
                  (= value' value)
                  (= unit' unit)
                  (= precision' precision)))))

(defn place
  "Create a place checker"
  [pnl n]
  (fn [token context] (and
                        (= :pnl (:dim token))
                        (= n (:n token))
                        (= pnl (:pnl token)))))

(defn metric
  "Create a metric checker"
  [cat val]
  (fn [token context] (and
                        (= :unit (:dim token))
                        (= val (:val token))
                        (= cat (:cat token)))))

(defn quantity
  "Create a quantity condition"
  [value unit & [product]]
  (fn [token _] (and
                  (= :quantity (:dim token))
                  (= value (-> token :value :value))
                  (= unit (-> token :value :unit))
                  (= product (-> token :value :product)))))

(defn volume
  "Create a volume condition"
  [value unit & [normalized]]
  (fn [token _] (and
                  (= :volume (:dim token))
                  (= value (-> token :value :value))
                  (= unit  (-> token :value :unit))
                  (= normalized (-> token :value :normalized)))))


(defn integer
  "Return a func (duckling pattern) checking that dim=number and integer=true,
  optional range (inclusive), and additional preds"
  [& [min max & predicates]]
  (fn [token]
    (and (= :number (:dim token))
         (:integer token)
         (or (nil? min) (<= min (:value token)))
         (or (nil? max) (<= (:value token) max))
         (every? #(% token) predicates))))

(defn corpus
  "Parse corpus" ;; TODO should be able to load several files, like rules
  [forms]
  (-> (fn [state [head & more :as forms] context tests]
        (if head
          (case state
            :init (cond (map? head) (recur :test-strings more
                                      head
                                      (conj tests {:text [], :checks []}))
                    :else (throw (Exception. (str "Invalid form at init state. A map is expected for context:" (prn-str head)))))

            :test-strings (cond (string? head) (recur :test-strings more
                                                 context
                                                 (assoc-in tests
                                                   [(dec (count tests)) :text (count (:text (peek tests)))]
                                                   head))
                            (fn? head) (recur :test-checks forms
                                         context
                                         tests)
                            :else (throw (Exception. (str "Invalid form at test-strings state: " (prn-str head)))))

            :test-checks (cond (fn? head) (recur :test-checks more
                                            context
                                            (assoc-in tests
                                              [(dec (count tests)) :checks (count (:checks (peek tests)))]
                                              head))
                           (string? head) (recur :test-strings forms
                                            context
                                            (conj tests {:text [], :checks []}))
                           :else (throw (Exception. (str "Invalid form at test-checks stats:" (prn-str head))))))
          {:context context, :tests tests}))
    (apply [:init forms [] []])))

(defmacro this-ns "Total hack to get ns of this file at compile time" [] *ns*)

(defn read-corpus
  "Reade a list of symbol and return a Corpus map {:context {}, :tests []}"
  [new-file]
  (let [symbols (read-string (slurp new-file))]
    (corpus (map #(binding [*ns* (this-ns)] (eval %)) symbols))))
