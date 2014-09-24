(ns picsou.util-test
  (:use [clojure.test]
        [picsou.util]))


(deftest mergings
  (is (= {:foo 2 :bar 2} (merge-according-to {:foo +} {:foo 1 :bar 2} {:foo 1}))))



(deftest partialmax
  (is (= [[6] [1 2]]
         (split-by-partial-max compare [1 2 6] [1 2 3 4]))))