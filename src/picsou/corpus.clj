(ns picsou.corpus
  (:use     [clojure.tools.logging]
            [plumbing.core :except [millis]]) 
  (:require [picsou.time.obj :as time]
            [picsou.util :as util]))

;; Checker functions return nil when OK, or an explanation string when not OK.
;; TODO move them to the module namespace

(defn- vec->date-and-map
  "Turns a vector of args into a date and a map of extra fields"
  [args]
  (let [[date-fields other-keys-and-values] (split-with integer? args)
        token-fields (into {} (map vec (partition 2 other-keys-and-values)))
        date (-> (apply time/t date-fields)
                 (?> (:grain token-fields) assoc :grain (:grain token-fields)))]
    [date token-fields]))

(defn datetime
  "Creates a datetime checker function to check if the token is valid"
  [& args]
  (let [[date token-fields] (vec->date-and-map args)]
    (fn [context token]
        (when-not
          (and
            (= :time (:dim token))
            #_(util/hash-match token-fields (:value token))
            (= (-> token :value) date))
          (format "\nExpected %s\nGot      %s\n" date (:value token))))))

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
        (format "\nExpected %s\nGot      %s\n" date value)))))

(defn number
  "check if the token is a number equal to value.
  If value is integer, it also checks :integer true"
  [value]
  (fn [token _] (and
                  (= :number (:dim token))
                  (or (not (integer? value)) (:integer token))
                  (= (:val token) value))))

(defn temperature
  "Create a temp condition"
  [value & [unit precision]]
  (fn [token _] (and
                  (= :temperature (:dim token))
                  (== value (-> token :val :temperature))
                  (= unit  (-> token :val :unit))
                  (= precision (-> token :val :precision)))))

(defn distance
  "Create a distance condition"
  [value & [unit precision]]
  (fn [token _] (and
                  (= :distance (:dim token))
                  (== value (-> token :val :distance))
                  (= unit  (-> token :val :unit))
                  (= precision (-> token :val :precision)))))

(defn money
  "Create a amount-of-money condition"
  [value & [unit precision]]
  (fn [token _] (and
                  (= :amount-of-money (:dim token))
                  (= value (-> token :val :amount))
                  (= unit (-> token :val :unit))
                  (= precision (-> token :val :precision)))))

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
