(ns duckling.util
  (:use [clojure.tools.logging :exclude [trace]])
  (:require [clojure.string  :as string]
            [clojure.set     :as sets]
            [clojure.java.io :as io]
            [clj-time.core :as t]
            [clj-time.format :as tf]
            [clojure.repl :as repl]
            [clojure.pprint :as pprint]
            [clj-time.coerce :as tcoerce])
  (:import [java.io IOException OutputStream StringReader]
           [java.math BigInteger]
           [java.lang.management ManagementFactory]
           [java.io StringWriter]
           [org.joda.time DateTimeZone DateTime]))

(defn hash-match
  "Matching hashmap over hashmap. Keys can be functions.
  WARNING THIS IS NOT RECURSIVE FOR THE MOMENT"
  [pattern input]
  (every? (fn [[key val]] (= val (key input)))
    pattern))

(defn valid-limit?
  "Decide if two adjacent chars are reasonably separated
  ab => false
  aR => true
  a1 => true
  -) => true etc."
  [char1 char2]
  {:pre [char1 char2]}
  (letfn [(get-char-class [s] (cond (re-find #"\p{javaLowerCase}" s) :lower
                                    (re-find #"\p{javaUpperCase}" s) :upper
                                    (re-find #"\p{Digit}" s) :digit
                                    :else s))] ; its own class
    (not= (get-char-class (str char1))
          (get-char-class (str char2)))))

(defn separated-substring?
  "Since we match regexes without whitespace delimitator, we have to check
   the reasonability of the match to actually be a word
   January19this is my birthday => OK
   Cameroun (as Camero + 'un') => NOT OK"
  [sentence pos end]
  {:pre [(<= end (count sentence))]}
  (and (or (= 0 pos)
           (valid-limit? (nth sentence (dec pos)) (nth sentence pos)))
       (or (= (count sentence) end)
           (valid-limit? (nth sentence (dec end)) (nth sentence end)))))

(defn keep-max
  "Returns the sublist of elem for which f is maxed"
  [fmax coll]
  (let [f (fn [[elems max-val] elem]
            (let [result (fmax elem)]
              (case (compare result max-val)
                1 [[elem] result]
                0 [(conj elems elem) max-val]
                -1 [elems max-val])))]
    (first (reduce f [] coll))))

(defn split-by-partial-max
  "Splits coll into two colls. The first one contains the items in coll that do
   not have any elem greater than them in base-coll according the given partial
   order function. The second contains the other items in coll.
   (When the partial order returns nil, it means the 2 elems
   cannot be compared.)"
  [partial-order-fn coll base-coll]
  (let [splitted (group-by
                   (fn [x] (every? #(let [p (partial-order-fn x %)]
                                      (or (nil? p) (>= p 0)))
                                   base-coll))
                   coll)]
    [(splitted true) (splitted false)]))

(defn merge-according-to
  "Merges a list of maps from left to right.
  When 2 conflicting keys are found, lookup the merging function to use.
  If the merging function can't be found, right (new) value is used.
  Merging functions are called as (f left right)."
  [fn-map & maps]
  (let [merge-entry (fn [m [k v]]
                      (if-let [conflict (get m k)]
                        (if-let [f (get fn-map k)]
                          (assoc m k (f conflict v))
                          (assoc m k v))
                        (assoc m k v)))]
    (reduce #(reduce merge-entry %1 (seq %2)) {} maps)))

(defn compare-intervals
  "Compares two intervals. i1 > i2 if i1 recovers i2."
  [[a b] [c d]]
  (cond
    (< a c)
    (when (>= b d) 1) ; nil (not comparable) in all other cases
    (= a c)
    (compare b d)
    (> a c)
    (when (>= d b) -1))) ; or nil

(defn spprint
  "Pretty-prints to a string (useful to print hashmaps)"
  [m]
  (let [w (StringWriter.)]
    (pprint/pprint m w)
    (.toString w)))
