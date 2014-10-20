(ns duckling.helpers
  "This namespace contains the common helpers used in rules"
  (:require
    [clj-time.core :as t]
    [duckling.util :as util])
  (:refer-clojure :exclude [resolve]))

(defmacro fn& [dim & args-body]
  (let [meta-map (when (-> args-body first map?)
                   (first args-body))
        args-body (if meta-map
                    (rest args-body)
                    args-body)]
    (merge meta-map
           `{:dim ~(keyword dim)
             :pred (fn ~@args-body)})))

(defn dim
  "Returns a func checking dim of a token and additional preds"
  [dim-val & predicates]
  (fn [token]
    (and (= dim-val (:dim token))
         (every? #(% token) predicates))))

(defn integer
  "Return a func (duckling pattern) checking that dim=number and integer=true,
  optional range (inclusive), and additional preds"
  [& [min max & predicates]]
  (fn [token]
    (and (= :number (:dim token))
         (:integer token)
         (or (nil? min) (<= min (:val token)))
         (or (nil? max) (<= (:val token) max))
         (every? #(% token) predicates))))
