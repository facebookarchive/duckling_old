(ns picsou.time.obj-test
  (:use [picsou.time.obj] 
        [clojure.test]))

(def today (t 2014 8 30 5 30 18))

(deftest obj-test
  (testing "rounding"
    (is (= (t 2014) (round today :year)))
    (is (= (t 2014 8 30) (round today :day))))
  (testing "days in month"
    (is (= 31
           (days-in-month today)))))

(deftest period-test
  (testing "adding"
    (is (= (t 2014 8 30 6 30 18)
           (plus-period today {:hour 1}))))
  (testing "grain"
    (is (= :second
           (period-grain {:week 1 :day 3 :second 18}))))
  (testing "negative"
    (is (= {:week -1 :day -3 :second -18}
           (negative-period {:week 1 :day 3 :second 18})))))