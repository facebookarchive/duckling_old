(ns duckling.ml.naivebayes
  (:refer-clojure :exclude [update])
  (:use [clojure.test])
  (:require [clojure.string :as strings]))

;; accessors
(defn datum-features [datum] (first datum))
(defn datum-class [datum] (second datum))

(defn- p-doc-given-class
  "Computes the probability of a doc (bag of features) for a given class
  using log probabilities.
  P(d|c) = P(x1|c) . P(x2|c) ... (Pxn|c) . P(c)"
  [bag-of-feats cla]
  (let [class-proba (:class-proba cla)
        unk-p (:unk-proba cla)
        compute-feat-proba (fn [[feat wcount]]
      (* wcount (get-in cla [:feat-probas feat] unk-p)))
        doc-proba-given-cla (reduce + (map compute-feat-proba bag-of-feats))]
    (+ doc-proba-given-cla class-proba)))

(defn classify
  "Tries to find the most likely class by computing each score for the given
  bag of features"
  [classifier bag-of-feats]
  (let [classes (:classes classifier)
        f (fn [max-cla [cla-name cla-info]]
      (let [[_ max-proba] max-cla
            cla-proba (p-doc-given-class bag-of-feats cla-info)]
        (if-not max-cla
          [cla-name cla-proba]
          (if (> cla-proba max-proba)
            [cla-name cla-proba]
            max-cla))))
        [winner p] (reduce f nil classes)]
    [winner p]))

(defn- aggregate
  "Transform the various counts into probabilities"
  [classifier]
  (let [classes (:classes classifier)
        class-infos (vals classes)
        total-docs (reduce + (map :n class-infos))
        vocab-size (count (set (mapcat #(keys (:feat-counts %)) class-infos)))
        f (fn [class-info]
            (let [counts (:feat-counts class-info)
                  total-counts (reduce + (vals counts)) ;; Laplace smoothing
                  class-proba (Math/log (/ (:n class-info) total-docs))
                  smoothed-denum (+ vocab-size total-counts)
                  feat-probas (into {}
                                    (for [[k v] counts]
                                      [k (Math/log (/ (inc v) smoothed-denum))]))
                  unk-proba (Math/log (/ 1 (inc smoothed-denum)))
                  new-map (hash-map :class-proba class-proba
                                    :feat-probas feat-probas
                                    :unk-proba unk-proba
                                    :n (:n class-info))]
              new-map))
        classes (into {} (for [[k v] classes] [k (f v)]))]
    {:classes classes}))

(defn train-classifier
  "Returns a Naive Bayes classifier.
  Accepts a dataset following:
  [[{:feat1 1 :feat2 4 :feat3 6} <class1>]
   [{:feat2 5 :feat3 1 :feat6 9} <class2>]]
  First, counts every occurrence of each feature for each class
  Then, aggregates these counts into probabilities"
  [dataset]
  (let [f (fn [c datum]
            (let [counts (datum-features datum)
                  cla (datum-class datum)
                  c (update-in c [:classes cla :n ] #(inc (or % 0)))
                  c (update-in c [:classes cla :feat-counts ]
                               #(merge-with + % counts))]
              c))
        c (reduce f {} dataset)] ;; create classifier with simple counts
    (aggregate c)))

(defn top10-classes [classifier]
  (->> (:classes classifier)
       (into [])
       (sort-by #(-> % val :n) (comp - compare)) ;; descending
       (map key)
       (take 10)))
