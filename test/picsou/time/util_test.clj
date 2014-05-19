(ns picsou.time.util-test
  (:use [clojure.test]
        [picsou.time.util])
  (:require [clj-time.core :as t]))

(defn dt [& args] (apply t/date-time args))

(deftest interval-intersection
  (are [in1 in2 out] (= out (i-intersect in1 in2))
       [(dt 2013 7 20) (dt 2013 7 21)] [(dt 2013 7 20) (dt 2013 7 21)] [(dt 2013 7 20) (dt 2013 7 21)]
       [(dt 2013 7 22) (dt 2013 7 24)] [(dt 2013 7 20) (dt 2013 7 23)] [(dt 2013 7 22) (dt 2013 7 23)]
       [(dt 2013 7 22) (dt 2013 7 27)] [(dt 2013 7 2) (dt 2013 7 8)] nil
       [(dt 2013 7 22) (dt 2013 7 27)] [(dt 2013 7 27) (dt 2013 7 28)] nil))