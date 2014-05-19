(ns picsou.util-test
  (:use [clojure.test]
        [picsou.util])
  (:require [clojure.string :as string]))


(deftest mergings
  (is (= {:foo 2 :bar 2} (merge-according-to {:foo +} {:foo 1 :bar 2} {:foo 1}))))
