(ns picsou.time.helpers
  "Time-specific rules helpers"
  (:use [picsou.time.util]
        [picsou.helpers]
        [plumbing.core :exclude [millis]])
	(:require [clj-time.core :as t]
            [picsou.util :as util])
  (:import [java.text NumberFormat]
           [java.util Locale])
  (:refer-clojure :exclude [resolve]))

;;
;; Warning: never use clj-time/date-time to build dates in this context
;; It would make UTC dates, whereas picsou uses local dates, resulting in a mess
;;

(defn fn&field
  "Build a pred from a periodic interval that coincide with a Joda field"
  [grain field-fn width modulo-step]
  (fn& time [t ctx]
    (let [rounded (round-to-grain t grain)
          start (field-fn rounded)
          end   (t/plus start width)]
      (modulo [start end] modulo-step t ctx))))

(defn fn&i
  "Build a pred from a single (not periodic) interval"
  [[start end :as interval]]
  (fn& time [t ctx]
    (when
      (if (bwd ctx)
        (t< start t)
        (t< t end))
      interval)))

; to parse decimal number in picsou FR
(def number-format-fr (NumberFormat/getInstance Locale/FRANCE))

(defn parse-number-fr
  "Parses a string with FRANCE locale. Returns a double"
  [s]
  (.doubleValue (.parse number-format-fr s)))

;;
;; First-order public helpers
;;

(defn year [year & [convert-two-digit-year?]]
  {:pre [(integer? year)]}
  (let [year (if convert-two-digit-year?
               (-> year (+ 50) (mod 100) (+ 2000) (- 50))
               year)]
    (fn& time [t ctx]
      (let [rounded (round-to-grain t years)
            start (.withYear rounded year)
            end   (t/plus start (t/years 1))]
        (filter-interval t ctx [start end])))))
 
(defn day-of-month [day]
  {:pre [(integer? day) (<= 1 day 31)]}
  ; 'seek' the next date where day-of-month exists
  ; copes with August 31 + 1 mon => Sep 31 !!! and February leap years
  (letfn [(seek [start step tgt-day] ; step is -1 or +1
                       (->> (iterate #(+ % step) 0) ; 0 1 2 3 4 ...
                            (map #(t/plus (round-to-grain start days) (t/days %))) ; d d+1 d+2 ...
                            (drop-while #(or (not= (t/day %) tgt-day)
                                             (and (= -1 step) (= start %))))
                            first))]
	  (fn& time [t ctx]
      (let [pos (seek t (if (bwd ctx) -1 1) day)
            start (round-to-grain pos days)]
  			[start (t/plus start (t/days 1))]))))

(defn month-of-year [month]
  {:pre [(<= 1 month 12)]}
  (fn&field months #(. % withMonthOfYear month) (t/months 1) (t/years 1)))

(defn day-of-week [dow]
  {:pre [(<= 1 dow 7)]}
  (fn&field weeks #(. % withDayOfWeek dow) (t/days 1) (t/weeks 1)))

(defn hour 
  "[3 false] => 3am
   [3 true]  => 3am or 3pm
   [15 true] => 3pm"
  [hour ambiguous-am-pm?]
  {:pre [(<= 0 hour 23)]}
  (let [step (if (and ambiguous-am-pm? (<= hour 12))
               (t/hours 12)
               (t/days 1))]
    (-> (fn&field hours #(. % withHourOfDay hour) (t/hours 1) step)
        (assoc :ambiguous-am-pm ambiguous-am-pm?))))

(defn minute [minute]
  {:pre [(<= 0 minute 59)]}
  (fn&field minutes #(. % withMinuteOfHour minute) (t/minutes 1) (t/hours 1)))

(defn sec [second]
  {:pre [(<= 0 second 59)]}
  (fn&field seconds #(. % withSecondOfMinute second) (t/secs 1) (t/minutes 1)))

(defn between-hours [h1 h2]
  "[0 12] => morning"
  {:pre [(<= 0 h1 23) (<= 0 h2 23)]} ; note that h2 can be < h1
  (fn& time [t ctx]
    (let [rounded (round-to-grain t hours)
          start (.withHourOfDay rounded h1)
          end (-> rounded
                  (.withHourOfDay h2)
                  (?> (< h2 h1) t/plus (t/days 1)))] ; from 10pm to 2am
      (modulo [start end] (t/days 1) t ctx))))
 
(defn between-days-of-weeks-hours [d1 h1 d2 h2]
  "[5 18 1 0] => weekend from Friday 18:00 to Sunday midnight"
  {:pre [(<= 0 h1 23) (<= 0 h2 23)]} ; note that h2 can be < h1
  (fn& time [t ctx]
    (let [rounded (round-to-grain t hours)
          start (-> rounded
                    (.withDayOfWeek d1)
                    (.withHourOfDay h1))
          end (-> rounded
                  (.withDayOfWeek d2)
                  (.withHourOfDay h2))
          end (if (t< end start)
                (t/plus end (t/weeks 1)))]
      (modulo [start end] (t/weeks 1) t ctx))))

;; 
;; functions that need a reference date (provided by context)
;;

(defn in-duration [duration & [width negative]] ; a joda duration that can be negative for 'ago'
  (fn& time [t ctx]
    (let [target ((if negative t/minus t/plus) (rf ctx) duration)
          width (or width (t/hours 1))
          interval [target (t/plus target width)]]
      (filter-interval t ctx interval))))
 
(defn this-cycle
  "Cycles, as opposed to durations.
   [ref days 0] => today
   [ref weeks 0] => this week
   [ref months 1] => next calendar month
   [ref months -2 2] => last 2 calendar months"
	[grain & [pos width]]
 		(let [pos (or pos 0)
          width (or width 1)]
      (fn& time [t ctx]
        (let [reference (rf ctx)
              start (round-to-grain (t/plus reference (grain pos)) grain)
              interval [start (t/plus start (grain width))]]
   		    (filter-interval t ctx interval)))))

(defn cycle-relative
  "The week after X-mas. pred represents the X-mas."
	[{:keys [pred]} grain & [pos width]]
 		(let [pos (or pos 0)
          width (or width 1)]
     	(fn& time [t ctx]
        (let [[anchor-s anchor-e :as i] (pred t ctx)]
          (when i
            (let [anchor (if (pos? pos) anchor-e anchor-s)
                  start (round-to-grain 
                          (t/plus anchor (grain (if (pos? pos)
                                                  (dec pos)
                                                  pos))) grain)
                  end (t/plus start (grain width))]
    			    (when ; classical fn&i stuff
    			      (if (bwd ctx)
    			        (t< start t)
    			        (t< t end))
    			      [start end])))))))

(defn this-pred 
  "[ref tuesday-fn 1] => next tuesday
   [ref tuesday-fn 2] => tuesday in 8
   We explicitely seek fwd or bwd depending on the sign of pos.
   ! don't use with pos 0, there is an ambiguity, need a special fn that returns both
   closest backward and forward intervals."
	[{:keys [pred] :as token} pos]
 	{:pre [(not= 0 pos) pred]}
  (fn& time [t ctx]
	 	(->> (loop [cursor (rf ctx)
                ; tricky situation. if pos is negative (... ago) we don't want to look forward
                context (-> ctx (assoc :backward (neg? pos)))
      	        cnt 0]
      	    (let [[start end :as interval] (pred cursor context)]
      	      (cond (and (= cnt 0) (in? cursor interval))
      	            	(recur (if (fwd context) end start) context cnt)
      	            (< (inc cnt) (if (pos? pos) pos (- pos)))
      	            	(recur (if (fwd context) end start) context (inc cnt))
      	            :else
      	            	interval)))
         (filter-interval t ctx))))

;;
;; Higher order functions
;;

(defn intersect 
  ([{fn1 :pred :as token1} {fn2 :pred :as token2}]
  (letfn [(seek [t ctx cnt]
                ;(prn "seeking" t ctx)
            (when t
              (let [[start1 end1 :as i1] (fn1 t ctx)
        						[start2 end2 :as i2] (fn2 t ctx)]
                ;(when (zero? cnt)
                  ;(throw (ex-info "I think I'm looping" {:seek t :tok1 token1 :tok2 token2})))
                (when (and (i-and i1 i2) ; if one interval in nil, no chance to seek anything
                           (> cnt 0))    ; too many tries, e.g. "4pm in the morning"
                	(or
                  	(i-intersect i1 i2)
                   	(if-not (bwd ctx)
                      (cond 
                        (in? t i1)
                          (seek (t-max start2
                                       (first (fn1 start2 ctx))) ctx (dec cnt))
                        (in? t i2)
                          (seek (t-max start1
                                       (first (fn2 start1 ctx))) ctx (dec cnt))
                        :else
                        	(seek (t-max start1 start2) ctx (dec cnt)))
                      (cond 
                        (t<= t end1)
                          (seek (t-min end2
                                       (last (fn1 end2 ctx))) ctx (dec cnt))
                        (t<= t end2)
                          (seek (t-min end1
                                       (last (fn2 end1 ctx))) ctx (dec cnt))
                        :else
                        	(seek (t-min end1 end2) ctx (dec cnt)))))))))]
  (fn& time [t ctx]
    (seek t ctx 100))))
  ([fn1 fn2 fn3 & more]
   (intersect 
     (intersect fn1 fn2)
     (if more (apply intersect fn3 more) fn3))))

;;
;; patterns
;;

(defn hour-min [h ambiguous-am-pm? m]
  (assoc (intersect (hour h ambiguous-am-pm?) (minute m))
         :form :time-of-day))

(defn hour-relativemin [hour ambiguous-am-pm? min]
  (if ambiguous-am-pm?
    (hour-min (mod (if (pos? min) hour (dec hour)) 12) true  (mod min 60))
    (hour-min (mod (if (pos? min) hour (dec hour)) 24) false (mod min 60))))

(defn parse-dmy
  "Build date from day, month, year as strings of numerics.
   Please provide at least one non-nil argument"
  [day-string mo-string y-string convert-two-digit-year?]
  (let [day (when day-string (day-of-month (Integer/parseInt day-string)))
        mo  (when mo-string (month-of-year (Integer/parseInt mo-string)))
        y   (when y-string (year (Integer/parseInt y-string) convert-two-digit-year?))
        v (remove nil? [day mo y])]
    (if (= 1 (count v)) (first v) (apply intersect v))))

