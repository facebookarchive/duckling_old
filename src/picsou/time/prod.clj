(ns picsou.time.prod
  (:use [clojure.tools.logging])
  (:require [picsou.time.pred :as p]
            [picsou.time.obj :as t])
  (:import [java.text NumberFormat]
           [java.util Locale]))

;; Production helpers, called from the rules

; Note on not-immediate
; ---------------------
; You can use not-immediate at two levels:
; 1- Calling take-the-nth with an option
; 2- Adding a :not-immediate key to the token
; The difference is that with 1, you get a pred that will generate only one
; time slot. Higher layers constructs won't be able to "lose" the not-immediate.
; With 2, not-immediate is applied during resolution of the token, but higher
; level contructs won't be affected. In practice 2 is useful for weekdays, for
; which you want "Monday" to refer just to "this Monday", but for something like
; "the third Monday of september" you need all the slots.

(defn ti [pred & [m]]
  (merge m {:dim :time
            :pred pred}))

(defn intersect
  "Combines several time tokens." ; FIXME shouldn't accept that both have timezone
  ([tok1 tok2]
   (ti (p/compose (:pred tok1) (:pred tok2))
       {:timezone (or (:timezone tok1) (:timezone tok2))}))
  ([tok1 tok2 & more]
   (apply intersect (intersect tok1 tok2) more)))

(defn interval
  "Interval between two tokens. The interval starts at the start of tok1,
  and ends at the *start* of tok2.
  The grains of tok1 and tok2 must be equal.
  If to-inclusive? is true, it ends at the *end* of tok2."
  [tok1 tok2 & [to-inclusive?]]
  (let [grain1 (-> tok1 :pred meta :grain)
        grain2 (-> tok2 :pred meta :grain)
        incl (= :day grain1 grain2)]
  ;(prn "interval called")
  (if true;(=  )
    (ti (p/intervals (:pred tok1) (:pred tok2) incl)
        {:timezone (or (:timezone tok1) (:timezone tok2))})
    {:dim :invalid})))

;; if we say "Monday" and today is Monday, we mean next Monday
;; hence the :not-immediate that modifies resolution

(defn day-of-week [dow]
  {:pre [(<= 1 dow 7)]}
  (ti (p/day-of-week dow) {:form :day-of-week
                           :not-immediate true}))

(defn year [y]
  (ti (p/year y)))

(defn month [mo]
  {:pre [(<= 1 mo 12)]}
  (ti (p/month mo) {:form :month}))

(defn day-of-month [day]
  {:pre [(<= 1 day 31)]}
  (ti (p/day-of-month day)))

(defn month-day [mo d]
  (intersect (month mo) (day-of-month d)))

(defn hour [h & [twelve-hour-clock?]]
  (ti (p/hour h twelve-hour-clock?) {:form :time-of-day
                                     ; the 2 following fields are used for relative-minutes
                                     :full-hour h
                                     :twelve-hour-clock? twelve-hour-clock?}))

(defn minute [m]
  {:pre [(<= 0 m 59)]}
  (ti (p/minute m)))

(defn hour-minute [h m & [twelve-hour-clock?]]
  (assoc (intersect (hour h twelve-hour-clock?)
                    (minute m))
         :form :time-of-day))

; twelve-hour clock is 12, 1, 2, 3, ... 11 (no 0)

(defn hour-relativemin [h m & [twelve-hour-clock?]]
  {:pre [(<= 0 h 23) (<= -59 m 59)]}
  (if twelve-hour-clock?
    (hour-minute (if (pos? m) h (case h 0 23 1 12 (dec h))) (mod m 60) true)
    (hour-minute (if (pos? m) h (case h 0 23 1 0 (dec h))) (mod m 60) false)))
  

(defn cycle-nth [grain n]
  (ti (p/take-the-nth (p/cycle grain) n)))

(defn cycle-nth-after [grain n {:keys [pred] :as token}]
  (ti (p/take-the-nth-after (p/cycle grain) pred n)))

(defn cycle-n [grain n]
  (ti (p/take-n (p/cycle grain) n)))

(defn cycle-n-not-immediate [grain n]
  (ti (p/take-n (p/cycle grain) n {:not-immediate true})))

(defn pred-last-of [cyclic base]
  (ti (p/take-the-last-of (:pred cyclic) (:pred base))))

(defn pred-nth [{:keys [pred] :as token} n]
  (ti (p/take-the-nth pred n) {:timezone (:timezone token)}))

(defn pred-nth-not-immediate [{:keys [pred] :as token} n]
  (ti (p/take-the-nth pred n {:not-immediate true}) {:timezone (:timezone token)}))

(defn parse-dmy
  "Build date from day, month, year as strings of numerics.
   Please provide at least one non-nil argument"
  [day-string mo-string y-string convert-two-digit-year?]
  (let [day (when day-string (day-of-month (Integer/parseInt day-string)))
        mo  (when mo-string (month (Integer/parseInt mo-string)))
        y   (when y-string (year (Integer/parseInt y-string)))
        v (remove nil? [y mo day])]
    (if (= 1 (count v)) (first v) (apply intersect v))))

(defn duration [grain n]
  (t/period grain n))

(defn in-duration
  "Shifts the present to present+duration and changes the grain, typically to
  the one just below the duration grain. See pred.clj for conversion."
  [duration]
  (ti (p/shift-duration (p/take-the-nth (p/cycle :second) 0) 
                        duration)))

(defn duration-ago
  "See in-duration"
  [duration]
  (ti (p/shift-duration (p/take-the-nth (p/cycle :second) 0) 
                        (t/negative-period duration))))

(defn set-timezone
  "Sets the provided timezone. Must be a java.util.TimeZone compatible ID."
  [token timezone-id]
  (assoc token :timezone timezone-id))

; to parse decimal number in picsou FR
; FIXME shouldn't be a full Locale, we should be more flexible to accept . and ,

(defn parse-number-fr
  "Parses a string with FRANCE locale. Returns a double"
  [s]
  (.doubleValue (.parse (NumberFormat/getInstance Locale/FRANCE) s)))


;;;;;;;;;;;;;;;;;;;;;;
;; Patterns (may be moved to their own ns)

(defn dim
  "Returns a func checking dim of a token and additional preds"
  [dim-val & predicates]
  (fn [token]
    (and (= dim-val (:dim token))
         (every? #(% token) predicates))))

(defn integer
  "Return a func (picsou pattern) checking that dim=number and integer=true,
  optional range (inclusive), and additional preds"
  [& [min max & predicates]]
  (fn [token]
    (and (= :number (:dim token))
         (:integer token)
         (or (nil? min) (<= min (:value token)))
         (or (nil? max) (<= (:value token) max))
         (every? #(% token) predicates))))
