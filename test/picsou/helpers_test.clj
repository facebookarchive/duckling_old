(ns picsou.helpers-test
  (:use [picsou.helpers]
        [clojure.test]))

(deftest integer-helpers
       (testing "simple"
         (are [dim int res] (= ((integer) {:dim dim :integer int}) res)
              :number true true
              :number false false
              :other true false))
       (testing "with range"
         (are [val res] (= ((integer 1 2) {:dim :number :integer true :val val}) res)
              2 true
              3 false))
       (testing "with range and pred"
         (is (= ((integer 1 3 #(odd? (:val %))) {:dim :number :integer true :val 2}) false))))