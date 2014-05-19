(ns picsou.time.helpers-test
  (:use [clojure.test]
        [picsou.time.helpers]
        [picsou.time.util])
  (:require [clj-time.core :as t]))

(defn dt [& args] (apply t/date-time args))
(def now (dt 2013 7 21 17 27 58))
(def now-1d (dt 2013 7 20 17 27 58))
(def now+1d (dt 2013 7 22 17 27 58))
(def today [(dt 2013 7 21) (dt 2013 7 22)])
(def ctx-bw {:backward true :reference-time now})
(def ctx-fw {:backward false :reference-time now})

(deftest parsing-number
  (is (= (parse-number-fr "1,23") 1.23)))

(deftest basic
       (testing "day-of-week"
         (are [day ctx res] (= ((:pred (day-of-week day)) now ctx) res)
              5 ctx-fw [(dt 2013 7 26) (dt 2013 7 27)]
              5 ctx-bw [(dt 2013 7 19) (dt 2013 7 20)]
              7 ctx-fw [(dt 2013 7 21) (dt 2013 7 22)]
              7 ctx-bw [(dt 2013 7 21) (dt 2013 7 22)]))
       (testing "this-cycle (today, this month...)"
         (is (= ((:pred (this-cycle days 0)) now-1d ctx-fw) [(dt 2013 7 21) (dt 2013 7 22)]))
         (are [d ctx res] (= ((:pred (this-cycle days d)) now ctx) res)
              -10 ctx-fw nil
              -10 ctx-bw [(dt 2013 7 11) (dt 2013 7 12)]
              0 ctx-bw [(dt 2013 7 21) (dt 2013 7 22)]
              31 ctx-fw [(dt 2013 8 21) (dt 2013 8 22)]))
       (testing "day-of-month"
         (are [d ctx res] (= ((:pred (day-of-month d)) now ctx) res)
              21 ctx-fw [(dt 2013 7 21) (dt 2013 7 22)]
              21 ctx-bw [(dt 2013 7 21) (dt 2013 7 22)]
              1 ctx-fw [(dt 2013 8 1) (dt 2013 8 2)]
              30 ctx-fw [(dt 2013 7 30) (dt 2013 7 31)]
              31 ctx-fw [(dt 2013 7 31) (dt 2013 8 1)]
              31 ctx-bw [(dt 2013 5 31) (dt 2013 6 1)]))
       (testing "month-of-year"
             (are [d ctx res] (= ((:pred (month-of-year d)) now ctx) res)
                  1 ctx-fw [(dt 2014 1 1) (dt 2014 2 1)]
                  1 ctx-bw [(dt 2013 1 1) (dt 2013 2 1)]
                  7 ctx-fw [(dt 2013 7 1) (dt 2013 8 1)]
                  7 ctx-bw [(dt 2013 7 1) (dt 2013 8 1)]
                  12 ctx-fw [(dt 2013 12 1) (dt 2014 1 1)])
             (is (= ((:pred (month-of-year 8)) (dt 2013 8 1) ctx-bw) [(dt 2012 8 1) (dt 2012 9 1)])))
       (testing "in-duration"
         (is (= (first ((:pred (in-duration (t/days 3))) now ctx-fw)) (dt 2013 7 24 17 27 58))))
       (testing "years"
         (are [y ctx res] (= ((:pred (year y)) now ctx) res)
              2019 ctx-fw [(dt 2019) (dt 2020)]
              2013 ctx-fw [(dt 2013) (dt 2014)]
              2013 ctx-bw [(dt 2013) (dt 2014)])))

(deftest higher-level
       (testing "intersect"
         (is (= ((:pred (intersect (day-of-week 4) (hour 13 false) (minute 18) (sec 59))) now ctx-fw)
                [(dt 2013 7 25 13 18 59) (dt 2013 7 25 13 19 00)]))))