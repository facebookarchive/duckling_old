(ns picsou.time.pred-test
  (:use [clojure.test]
        [picsou.time.pred])
  (:refer-clojure :exclude [cycle resolve]))

(def evening (intervals (hour 18 false) (hour 0 false) false))

(def today (take-the-nth (cycle :day) 0))

(def tonight8 (compose (compose today evening) (hour 8 true)))

