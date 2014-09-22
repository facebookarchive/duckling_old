(ns picsou.time.obj
  (:require [clj-time.core :as time]))

; This ns constructs and operates time objects. It's a wall between Picsou and
; the actual implementation of time (here, clj-time).
;
; Intervally there are two kinds of time objects:
; 1) "Grains" defined by a :start instant and a :grain
; 2) True intervals defined by :start and :end instants. They also have a grain
;    because :end is exclusive, and we need a grain to know what to exclude
;
; Some fn like 'round', 'year' (and all the field getters) operate on the :start
; instant. If you use them, make sure it's what you want.



; week is a special case (it's not a field byitself), it's managed as a special
; case in functions
(def fields {:year [0 time/year time/years]
             :month [1 time/month time/months]
             :day [2 time/day time/days]
             :hour [3 time/hour time/hours]
             :minute [4 time/minute time/minutes]
             :second [5 time/second time/seconds]})

; for grain ordering
(def grain-order (into {} (map vector
                      [:year :quarter :month :week :day :hour :minute :second]
                      (range))))

(def period-fields {:year     [time/years 1]
                    :quarter  [time/months 3]
                    :month    [time/months 1]
                    :week     [time/weeks 1]
                    :day      [time/days 1]
                    :hour     [time/hours 1]
                    :minute   [time/minutes 1]
                    :second   [time/seconds 1]})

(defn valid? [{:keys [start grain end] :as t}]
  (and (instance? org.joda.time.DateTime start)
       (grain-order grain)
       (or (nil? end) (instance? org.joda.time.DateTime end))))

(defn t
  "Builds a time object with start and grain"
  [& [y mo day hour mi sec :as args]]
  {:start (apply time/date-time args)
   :grain (cond (nil? mo) :year
                (nil? day) :month
                (nil? hour) :day
                (nil? mi) :hour
                (nil? sec) :minute
                :else :second)})

(declare plus)

(defn end
  "Returns the end *instant* of the time object"
  [{:keys [start grain end] :as t}]
  {:pre [(valid? t)]}
  (or end (time/plus 
            start
            (let [[g n] (period-fields grain)] (g n)))))

(defn interval 
  "Builds a time interval between start of t1 and *start* of t2.
  The grain is the smallest of the args."
  [t1 t2]
  {:pre [(valid? t1) (valid? t2)]}
  {:start (:start t1)
   :grain (max-key grain-order (:grain t1) (:grain t2))
   :end (:start t2)})

(defn interval-start-end 
  "Builds a time interval between start of t1 and *end* of t2.
  The grain is the smallest of the args."
  [t1 t2]
  {:pre [(valid? t1) (valid? t2)]}
  {:start (:start t1)
   :grain (max-key grain-order (:grain t1) (:grain t2))
   :end (end t2)})

(defn intersect
  "With the special case of time grains, it's quite easy. Will need to generalize to
  intervals later. Returns nil if no intersection.
  The result grain is the smallest of the args grains."
  [t1 t2]
  {:pre [(valid? t1) (valid? t2)]}
  (let [s1 (:start t1)
        e1 (end t1)
        s2 (:start t2)
        e2 (end t2)]
  (if (or (= s1 s2) (time/before? s1 s2))
    (when (time/before? s2 e1)
      (if (or (time/before? e2 e1) (= e1 e2))
        t2
        {:start s1
         :grain (max-key grain-order (:grain t1) (:grain t2))
         :end e2}))
    (intersect t2 t1))))

(defn starting-at-the-end-of
  "Build a time that starts at the end of provided time, with same grain"
  [tt]
  {:start (end tt) :grain (:grain tt)})

(defn year [t]
  "Returns the year of the start of a time grain"
  (time/year (-> t :start)))

(defn month [t]
  "Returns the month of a time grain"
  (time/month (-> t :start)))

(defn day-of-week [t]
  "Returns the day of week of a time grain"
  (time/day-of-week (-> t :start)))

(defn day [t]
  "Returns the day of month"
  (time/day (:start t)))

(defn hour [t]
  (time/hour (-> t :start)))

(defn minute [t]
  (time/minute (:start t)))

(defn ->fields [{:keys [start] :as t}]
  [(time/year start) (time/month start) (time/day start) (time/hour start) (time/minute start) (time/second start)])

(defn plus
  "Add n grain to tt.
  Set the grain to the finest between tt's and the added one."
  [tt grain n]
  {:pre [(valid? tt) (grain-order grain) (integer? n)]}
  (let [[g n'] (period-fields grain)
        duration (g (* n n'))
        new-start (time/plus (:start tt) duration)
        new-grain (max-key grain-order (:grain tt) grain)
        new-t {:start new-start :grain new-grain}]
    (if-not (:end tt)
      new-t
      (assoc new-t :end (time/plus (end tt) duration)))))

(defn minus [tt grain n]
  (plus tt grain (- n)))

(defn round 
  "Rounds the time grain to the grain: all smaller grain fields set to 0.
  If applied to a true interval (with :end), it turns the interval into
  a grain time objects (rounds start, removes :end)."
  [tt grain]
  {:pre [(valid? tt)]}
  (cond 
    (= :week grain)
    (let [t-dow (day-of-week tt)]
      (-> (plus (round tt :day) :day (- 1 t-dow))
          (assoc :grain :week)
          (dissoc :end)))
    
    (= :quarter grain)
    (let [t-mo (round tt :month)
          mo-delta (mod (dec (month t-mo)) 3)]
      (-> (minus t-mo :month mo-delta)
          (assoc :grain :quarter)
          (dissoc :end)))
    
    :else
    (let [fields (for [[f idx] (map vector (->fields tt) (range))
                       :when (<= idx (first (fields grain)))]
                   f)]
      (apply t fields))))

(defn start-before-the-end-of? [t1 t2] ; TODO equality?
  {:pre [(valid? t1) (valid? t2)]}
  (let [t2-end (end t2)]
    (time/before? (:start t1) t2-end)))

(defn days-in-month [tt]
  "Returns the number of days in the month of tt"
  (time/day (time/last-day-of-the-month (:start tt))))

; only for debug purpose!
(defn now []
  {:start (time/now) :grain :second})


;;;;;;;;;;;;;;;;;;;;;;
; Periods

; Periods are almost durations, but not exactly. For instance the duration of 
; "one month" depends on which month. (When added to a time instant, a period
; can become a duration.)

; As a consequence, we store periods as a map keeping count for each field like
; year, month, etc.

(defn period
  "Creates a period object with the given grain and quantity (can be negative)"
  [grain n]
  {:pre [(period-fields grain) (integer? n)]}
  {grain n})

(defn add-to-period
  "Adds the given quantity of grain to the period"
  [p grain n]
  {:pre [(map? p) (period-fields grain) (pos? n)]}
  (merge-with + p (period grain n)))

(defn plus-period
  "Adds the period to the time object. The resulting grain is the finest."
  [tt period]
  (loop [[[grain value] & more] (seq period)
         acc tt]
    (if grain
      (recur more (plus acc grain value))
      acc)))

(defn period-grain
  "Returns the grain of the period (the finest of its grains)"
  [period]
  (apply max-key grain-order (keys period)))

(defn negative-period
  "Turn a period into its opposite sign"
  [period]
  (into {} (map (fn [[k v]] [k (- v)]) period)))
   


