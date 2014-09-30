(ns duckling.ml.naivebayes-test
  (:use [clojure.test]
        [plumbing.core]
        [duckling.ml.naivebayes])
  (:require [clojure.string :as string]))

(def text-dataset [["chinese beijing chinese" "c"]
                   ["chinese chinese shangai" "c"]
                   ["chinese macao" "c"]
                   ["tokyo japan chinese" "j"]])

(defn count-words
  [text]
  (-> text
      (string/split #"\s")
      (->> (remove empty?))
      (->> (map string/lower-case))
      frequencies))

(def text-classifier
  (train-classifier
    (map #(vector (count-words (first %)) (second %)) text-dataset)))

(def gender-classifier
  (train-classifier [[{:height 6    :weight 180 :foot-size 12} "male"]
                     [{:height 5.92 :weight 190 :foot-size 11} "male"]
                     [{:height 5.58 :weight 170 :foot-size 12} "male"]
                     [{:height 5.92 :weight 165 :foot-size 10} "male"]
                     [{:height 5    :weight 100 :foot-size 6} "female"]
                     [{:height 5.5  :weight 150 :foot-size 8} "female"]
                     [{:height 5.42 :weight 130 :foot-size 7} "female"]
                     [{:height 5.75 :weight 150 :foot-size 9} "female"]]))

(deftest classify-test
  (testing "classify gender"
    (is (= ["female" -56.20678430840421]
           (classify gender-classifier {:height 6 :weight 130 :foot-size 8})))
    (is (= "male"
           (first (classify gender-classifier {:height 6 :weight 170 :foot-size 10})))))
  (testing "classify word"
    (is (= ["c" -8.245676055817812]
           (classify text-classifier (count-words "chinese chinese chinese tokyo japan"))))))

