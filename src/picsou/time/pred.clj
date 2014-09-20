(ns picsou.time.pred
  (:use [clojure.tools.logging]
        [plumbing.core])
  (:require [picsou.time.obj :as t])
  (:refer-clojure :exclude [cycle resolve]))

;; Contains the time semantics.
;; Knows nothing  about tokens, morphology, syntax, forms.
;; These functions are normally called by production helpers (picsou.time.prod)

(defmacro fn& [grain args & forms]
  (let [[t ctx] args]
  `(with-meta
     (fn ~args
       (assert (and (:start ~t) (:grain ~t)) (format "Invalid t argument provided to predicate: %s" ~t))
       (assert (:max ~ctx) "Invalid context, missing :max")
       ~@forms)
     {:grain ~grain})))


;; The clojure.core/mapcat breaks the lazyness of its arguments
;; This one is truly lazy

(defn my-mapcat
  [f coll]
  (lazy-seq
   (if (not-empty coll)
     (concat
      (f (first coll))
      (my-mapcat f (rest coll))))))

; Limit the space search beam

(def safe-max 100)
(def safe-max-interval 12)

;; Debug utlity

(defn show [f]
  [(take 5 (first (f (t/now) {:reference-time (t/now)})))
   (take 5 (second (f (t/now) {:reference-time (t/now)})))])

; Config (could be moved to config file)

; Defines the resulting grain after a shift. For instance, for 'in two years'
; the result grain will be :month

(def grain-after-shift {:year :month
                        :month :day
                        :week :day
                        :day :hour
                        :hour :minute
                        :minute :second
                        :second :second})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; First-order Predicates
;
; A predicate is a function that given an input time interval,
; returns two possibly infinite lazy seqs: 
; one of intervals ahead, one of intervals behind.
;
; Ahead contains the succession of intervals ending after the start of input
; So if input is 2014-6-18 and pred is (year 2014), the first item in ahead
; will be the year 2014.
;
; Behind contains intervals ending before the *start* of input [1]
; So with the same example, behind is empty.
;
; [1] As a consequence, the end of input doesn't matter. This is leveraged by
; the fact that time functions called on input (round, year, etc.) all actually
; use the start field, not the end.

; By default year converts two-digits to a year between 1950 and 2050
; TODO: check context if we should NOT do this (history apps?)

(defn year [yyyy]
  (fn& :year [t _] 
    (let [true-year (if (<= yyyy 99)
                      (-> yyyy (+ 50) (mod 100) (+ 2000) (- 50))
                      yyyy)]
      (if (<= (t/year t) true-year)
        [[(t/t true-year)] nil]
        [nil [(t/t true-year)]]))))

(defn month [mo]
  (fn& :month [t _] (let [rounded (t/t (t/year t) mo)
                anchor (if (t/start-before-the-end-of? t rounded)
                         rounded
                         (t/plus rounded :year 1))]
             [(iterate #(t/plus % :year 1) anchor)
              (next (iterate #(t/minus % :year 1) anchor))])))

; day-of-month is tricky for values 29, 30 and 31 that are not always valid
; also, adding 1-month steps doesn't work because (Aug 31) + 1-month = (Sep 30)
; so the following times would be 30 not 31 

(defn day-of-month [dom]
  (fn& :day [t _] 
       (let [anchor (if (<= (t/day t) dom)
                      (t/round t :month)
                      (t/plus (t/round t :month) :month 1))
             enough-days (fn [tt] (<= dom (t/days-in-month tt)))
             add-days (fn [tt] (t/plus tt :day (dec dom)))
             months-f (->> (iterate #(t/plus % :month 1) anchor)
                           (filter enough-days)
                           (map add-days))
             months-b (->> (iterate #(t/minus % :month 1) (t/minus anchor :month 1))
                           (filter enough-days)
                           (map add-days))]
         [months-f months-b])))
       
(defn day-of-week [dow]
  (fn& :day [t _] (let [t-dow (t/day-of-week t)
                diff (mod (- dow t-dow) 7)
                anchor (t/plus (t/round t :day) :day diff)]
            [(iterate #(t/plus % :day 7) anchor)
             (next (iterate #(t/minus % :day 7) anchor))])))

(defn hour [h twelve-hour-clock?]
  (fn& :hour [t _] (let [step (if (and twelve-hour-clock? (<= h 11))
                          12
                          24)
                   diff (mod (- h (t/hour t)) step)
                   anchor (t/plus (t/round t :hour) :hour diff)]
               [(iterate #(t/plus % :hour step) anchor)
                (next (iterate #(t/minus % :hour step) anchor))])))

(defn minute [m]
  (fn& :minute [t _] (let [diff (mod (- m (t/minute t)) 60)
                   anchor (t/plus (t/round t :minute) :minute diff)]
               [(iterate #(t/plus % :hour 1) anchor)
                (next (iterate #(t/minus % :hour 1) anchor))])))

(defn cycle
  "A sequence of each year, or month, or week, etc.
  Used for 'this year', 'next month', 'last week'.."
  [grain]
  {:pre [#{:year :month :week :day :hour :minute :second} grain]}
  (fn& grain [t _]
          (let [anchor (t/round t grain)]
            [(iterate #(t/plus % grain 1) anchor)
             (next (iterate #(t/minus % grain 1) anchor))])))

;;;;;;;;;;;;;;;;;;;;;
;; Second order functions

(declare seq-map)

(defn compose 
  "Compose several predicates - can see this as intersection"
  ([pred] pred)
  ([pred1' pred2']
   (assert (fn? pred1') (format "Invalid predicate (1): %s" pred1'))
   (assert (fn? pred2') (format "Invalid predicate (2): %s" pred2'))
   (let [[pred1 pred2] (sort-by #(-> % meta :grain t/grain-order) [pred1' pred2'])
         grain (-> pred2 meta :grain)] ; finer grain
     (fn& grain [t ctx]
          ;(prn t (-> pred1 meta :grain) (-> pred2 meta :grain))
          ;(prn t (:max ctx) (:min ctx))
          (let [;; take the sequence of pred1 forward and backward
                   [seq1-f seq1-b] (pred1 t ctx)
                   
                   ;; clojure.core/mapcat uses apply which breaks lazyness
                   fwd (my-mapcat (fn [time1] ;(infof "hi %s" time1)
                                    (->> (first (pred2 time1 (assoc ctx :max time1 :min time1)))
                                         (take safe-max)
                                         (take-while #(t/start-before-the-end-of? % time1))
                                         (map #(t/intersect time1 %))
                                         (remove nil?)))
                                  (take safe-max (take-while #(t/start-before-the-end-of? % (:max ctx)) seq1-f))) ;; we need a safety net for impossible combinations
                   bwd (my-mapcat (fn [time1] 
                                    (->> (first (pred2 time1 (assoc ctx :max time1 :min time1)))
                                         (take safe-max)
                                         (take-while #(t/start-before-the-end-of? % time1))
                                         (map #(t/intersect time1 %))
                                         (remove nil?)))
                                  (take safe-max (take-while #(t/start-before-the-end-of? (:min ctx) %) seq1-b)))]
              [(take safe-max fwd) (take safe-max bwd)])))) ; this safety net should not be necessary
  ([pred1 pred2 & more]
     (compose (compose pred1 pred2) (apply compose more))))

; (defn compose-2
;   ""
;   [pred1 pred2]
;   (let [grain (max-key t/grain-order (-> pred1 meta :grain) (-> pred2 meta :grain))]
;     (fn& grain [t ctx]
;          (let [a (loop [[head1 & more1 :as seq1] (pred1 )])]))
;     (loop [])))

(defn take-the-nth ; TODO base-time-pred should actually use seq-map
  "Builds a predicate with only the nth time slot of a presumably cyclical pred after ref-time,
  backward (negative n) or forward (positive n).
  Beware that 0 => first forward, but -1 => first backward
  
  Options:
  
  :not-immediate: if true, the first slot will be dropped if it
  contains t. No effect on backward lookups (t is never containes in them)."
  
  [pred n & [opts]]
  (assert (fn? pred) (format "Invalid predicate: %s" pred))
  (fn& (-> pred meta :grain) [t ctx]  
    (let [base-time (:reference-time ctx)
          slot (if (<= 0 n)
                 (let [[head & more :as seq] (first (pred base-time ctx))
                       seq (if (and (:not-immediate opts) head (t/intersect head base-time))
                              more
                              seq)]
                   (first (drop n seq)))
                 (let [seq (second (pred base-time ctx))]
                   (first (drop (- (inc n)) seq))))]
      (if slot
        (if (t/start-before-the-end-of? t slot)
                  [[slot] nil]
                  [nil [slot]])
        [nil nil]))))

(defn take-n
  "Takes n cycles of pred. Used for 'next 2 weeks' for instance.
  Goes forward for positive n, backward otherwise.
  
  Accepts a :not-immediate option like take-the-nth"
  
  [pred n & [opts]]
  (assert (fn? pred) (format "Invalid predicate: %s" pred))
  (fn& (-> pred meta :grain) [t ctx]  
    (let [base-time (:reference-time ctx)
          slot (if (<= 0 n)
                 (let [[head & more :as seq] (first (pred base-time ctx))
                       seq (if (and (:not-immediate opts) head (t/intersect head base-time))
                              more
                              seq)
                       start (first seq)
                       end (first (drop n seq))]
                   (t/interval start end))
                 (let [seq (second (pred base-time ctx))
                       end (first seq)
                       start (first (drop (dec (- n)) seq))]
                   (t/interval-start-end start end)))]
      (if (t/start-before-the-end-of? t slot)
                [[slot] nil]
                [nil [slot]]))))


(defn take-the-nth-after
  "Like take-the-nth, but takes the nth cyclic-pred *after base-pred*
  (or before if n is negative.
  Since pred generates sequences, it also generates sequences.
  
  Options: :not-immediate works as usual"
  
  [cyclic-pred base-pred n & [opts]]
  (let [f (fn& (-> cyclic-pred meta :grain) [t ctx]
               (if (<= 0 n)
                 (let [[head & more :as seq] (first (cyclic-pred t ctx))
                       seq (if (and (:not-immediate opts) head (t/intersect head t))
                              more
                              seq)]
                   (first (drop n seq)))
                 (let [seq (second (cyclic-pred t ctx))]
                   (first (drop (- (inc n)) seq)))))]
    (seq-map f base-pred)))

(defn take-the-last-of
  "Takes the *last* occurence of cyclic-pred *within* base-pred.
  For example, cyclic-pred is 'Monday' and base-pred 'October'"
  
  [cyclic-pred base-pred]
  (let [f (fn& (-> cyclic-pred meta :grain) [t ctx]
               (let [pivot (t/starting-at-the-end-of t)
                     seq (second (cyclic-pred pivot ctx))]
                 (first seq)))]
    (seq-map f base-pred)))

(defn seq-map
  "Applies f to each interval yielded by pred.
  As f changes intervals, an interval that was ahead can become behind, and
  reciprocally. We make the assumption that f doesn't change the order of
  intervals though, or it would be much harder to maintain lazyness."
  [f pred & [dont-reverse?]]
  (fn& (-> pred meta :grain) [t ctx] (let [;; take the sequence of pred forward and backward
                 [seq1-f seq1-b] (pred t ctx) ; FIXME TOO RESTRICTIVE, AFTER APPLYING F IT WILL MOVE
                 
                 ;_ (prn "map" t (:min ctx) (:max ctx) (when (first seq1-f) (f (first seq1-f) ctx)))
                 
                 ;seq1-f (take-while #(t/start-before-the-end-of? % (:max ctx)) seq1-f)
                 ;seq1-b (take-while #(t/start-before-the-end-of? (:min ctx) %) seq1-b)
                  
                 ;; times moved from behind to ahead
                 bh-ah (->> seq1-b
                            (take safe-max-interval)
                            (map #(f % ctx))
                            (remove nil?)
                            (take-while #(t/start-before-the-end-of? t %))
                            (?>> (not dont-reverse?) reverse))
                 
                 ; times remaining ahead
                 ah-ah (->> seq1-f
                         (take safe-max-interval)
                         (map #(f % ctx))
                         (remove nil?)
                         (drop-while #(not (t/start-before-the-end-of? t %)))
                         (take-while #(t/start-before-the-end-of? % (:max ctx))))
                 
                 ahead (concat bh-ah ah-ah)
                 
                 ;; times moved from ahead to behind
                 ah-bh (->> seq1-f
                            (take safe-max-interval)
                            (map #(f % ctx))
                            (remove nil?)
                            (take-while #(not (t/start-before-the-end-of? t %)))
                            (?>> (not dont-reverse?) reverse))
                 
                 ; times remaining behing
                 bh-bh (->> seq1-b
                         (take safe-max-interval)
                         (map #(f % ctx))
                         (remove nil?)
                         (drop-while #(t/start-before-the-end-of? t %))
                         (take-while #(t/start-before-the-end-of? (:min ctx) %)))
                 
                 behind (concat ah-bh bh-bh)]
            [ahead behind])))

(defn intervals
  "Builds a sequence of intervals, each starting at the start of pred-from
  and ending at the start (inclusive-to? false) or end (inclusive-to? true)
  of the first pred-from time that follows the start of pred-from.
  Example: (intervals (day-of-week 1) (day-of-week 3) true)"
  
  [pred-from pred-to inclusive-to?]
  (let [inter-fn (if inclusive-to? t/interval-start-end t/interval)
        f (fn [t ctx] (let [slot (first (first (pred-to t ctx)))]
                        (when slot
                          (inter-fn t slot))))]
    (seq-map f pred-from true)))

(defn shift-duration
  "Shifts base-pred forward or backward ('two days after pred')
  Duration can be negative ('three hours before pred').
  The resulting grain is the one just below the duration's grain
  Shifted slots' width is exactly their grain"
  
  [base-pred duration]
  (let [grain (grain-after-shift (t/period-grain duration))
        f (fn [t ctx] (-> t
                          (t/round grain)
                          (t/plus-period duration)))]
    (seq-map f base-pred)))

(defn- print-token [{:keys [text rule route] :as token} & [prefix]]
  (printf "%s\"%s\" as %s\n" (or prefix "") text (:name rule))
  (doseq [child route]
    (print-token child (str "--" (or prefix "")))))

(defn resolve ; TODO not immediate + expain two ways
  "Turns a token into a list of actual possible time values.
  Behavior depends on the ref-time in context, and token fields like
  :not-immediate."
  [{:keys [dim pred not-immediate timezone] :as token} {:keys [reference-time] :as context}]
  {:pre [reference-time]}
  ;(prn "Resolving" (:text token)) (print-token token)
  (try
    (case dim
      :time
      (do
        (assert pred (format "Cannot resolve token without pred: %s" token))
        
        ; we use ref-time twice
        ; as the first arg of pred, it's just as a lookup starting point
        (let [[[first-ahead second-ahead] [first-behind]] (pred reference-time (assoc context :max (t/t 3000) :min (t/t 1000)))
              ahead (if (and not-immediate (t/intersect first-ahead reference-time))
                      second-ahead
                      first-ahead)]
          (->> (vector ahead first-behind)
               (remove nil?)
               ; FIXME use timezone in resolution instead of just adding the field
               (?>> timezone (map #(assoc % :timezone timezone)))
               (map #(assoc token :value %)))))
      
      [token]) ; default for other dims
    (catch Throwable e
      (.printStackTrace e)
      (print-token token)
      (throw (ex-info (format "Error while resolving %s" (dissoc token :route)) {})))))

; Debug utlity

(defn show [f]
  (time
    (let [now (t/t 2013 2 12 4 30)
          ctx {:reference-time (t/t 2013 2 12 4 30)
               :min (t/t 2000)
               :max (t/t 2018)}]
      (prn (take 5 (first (f now ctx))))
      (prn (take 5 (second (f now ctx)))))))
