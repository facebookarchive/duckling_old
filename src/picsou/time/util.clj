(ns picsou.time.util
  (:require [clj-time.core :as t])
  (:import
    [org.joda.time DateTimeFieldType]
    [org.joda.time DurationFieldType]
    [org.joda.time Period]))

;;
;; grains
;;

;; Time specific
(def ^:const millis  #'t/millis)
(def ^:const seconds #'t/secs)
(def ^:const minutes #'t/minutes)
(def ^:const hours   #'t/hours)
(def ^:const days    #'t/days)
(def ^:const weeks   #'t/weeks)
(def ^:const months  #'t/months)
(def ^:const years   #'t/years)
(def ^:const default-grain days)

(def grain-hierarchy (zipmap [millis
                              seconds
                              minutes
                              hours
                              days
                              weeks
                              months
                              years]
                             (iterate inc 1)))

(def grain-to-field {millis  [0 (DateTimeFieldType/millisOfSecond)]
                     seconds [0 (DateTimeFieldType/secondOfMinute)]
                     minutes [0 (DateTimeFieldType/minuteOfHour)]
                     hours   [0 (DateTimeFieldType/hourOfDay)]
                     days    [1 (DateTimeFieldType/dayOfMonth)
                              {weeks (DateTimeFieldType/dayOfWeek)}]
                     months  [1 (DateTimeFieldType/monthOfYear)]})

(def grain-to-duration-field 
                    {millis  (DurationFieldType/millis)
                     seconds (DurationFieldType/seconds)
                     minutes (DurationFieldType/minutes)
                     hours   (DurationFieldType/hours)
                     days    (DurationFieldType/days)
                     weeks   (DurationFieldType/weeks)
                     months  (DurationFieldType/months)
                     years   (DurationFieldType/years)})

(defn period [& args]
  "Builds a period from grains and quantity
   (period days 1 hours 2)"
  (let [pairs (partition 2 args)
        append (fn [duration [grain qty :as pair]] 
                 (.withField duration 
                             (get grain-to-duration-field grain) 
                             qty))]
    (reduce append (Period.) pairs)))

(defn p+ 
  "Add periods"
  [period1 & [period2 & more]]
  (if period2
    (apply p+ (.plus period1 period2) more)
    period1))

(defn compare-grains
  "Grains must be functions (e.g. secs)"
  [grain1 grain2]
  (compare (get grain-hierarchy grain1) (get grain-hierarchy grain2)))

(defn round-to-grain
  "Rounds a datetime to a certain grain by resetting lower grains"
  [d grain]
  (let [grain-rank   (get grain-hierarchy grain)
        lower-grains (->> (vec grain-hierarchy)
                          (keep #(when (> grain-rank (second %))
                                   (get grain-to-field (first %)))))
        reset-field (fn [^org.joda.time.DateTime date [^int default
                                                       ^DateTimeFieldType field
                                                       opts]]
                      (let [field (if-let [cust-field (get opts grain)]
                                    cust-field
                                    field)]
                        (.withField date field default)))]
    (reduce reset-field d lower-grains)))

;; 
;; Low-level intervals / rounding manipulation
;;

(defn t<  [t1 t2] (t/before? t1 t2))
(defn t<= [t1 t2] (or (= t1 t2) (t< t1 t2)))
(defn t>  [t1 t2] (t<  t2 t1))
(defn t>= [t1 t2] (t<= t2 t1))
(defn t-min [t1 t2] (if (t< t1 t2) t1 t2))
(defn t-max [t1 t2] (if (t< t1 t2) t2 t1))

; before and after are strict
(defn before? [t interval] (t< t (first interval)))
(defn after? [t interval] (t<= (last interval) t))
(defn in? [t interval] (and (t< t (last interval)) (t<= (first interval) t)))
  
(defn i-and [i1 i2] (not (or (nil? i1) (nil? i2))))

(defn i-intersect [[start1 end1 :as i1] [start2 end2 :as i2]]
  (when (i-and i1 i2) ; nil intersect anything => nil
	  (if (t<= start1 start2)
	  	(when (t< start2 end1) ; nil otherwise
	     	[start2 (t-min end1 end2)])
	   	(i-intersect [start2 end2] [start1 end1]))))

(defn i+ [interval period]
  (map #(t/plus % period) interval))
(defn i- [interval period]
  (map #(t/minus % period) interval))

(defn bwd [context] (:backward context))
(defn fwd [context] (not (bwd context))) ; forward by default
(defn rf  [context] (:reference-time context))

(defn modulo [[start end :as interval] period t ctx]
  "Shifts an interval by <period> right or left, so that:
   - if forward, t is before and not further than period
   - if backard, t is after is not further than period
  Useful when using periodic intervals like monthOfYear in predicates"
  (cond (and (in? t interval) (or (fwd ctx)
                                  (t< start t))) interval ; edge case in backward mode, t is exclusive
        (and (before? t interval) (fwd ctx)) interval
        (and (after? t interval) (bwd ctx)) interval
        (and (bwd ctx) (= t start)) (i- interval period) ; edge case
        (and (before? t interval) (bwd ctx)) (i- interval period)
        (and (after? t interval) (fwd ctx)) (i+ interval period)
        :else (throw (ex-info "modulo case not taken into account" {:i interval}))))

(defn filter-interval
  "Let through or not a single (not periodic) interval based on t and ctx"
  [t ctx [start end :as interval]]
  (when (if (bwd ctx)
          (t< start t)
          (t< t end))
    interval))
