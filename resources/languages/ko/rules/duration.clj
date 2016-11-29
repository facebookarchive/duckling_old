; Durations / Periods

(
  "second (unit-of-duration)"
  #"초"
  {:dim :unit-of-duration
   :grain :second}

  "minute (unit-of-duration)"
  #"분"
  {:dim :unit-of-duration
   :grain :minute}

  "hour (unit-of-duration)"
  #"시간?"
  {:dim :unit-of-duration
   :grain :hour}

  "day (unit-of-duration)"
  #"날|일(간|동안)?"
  {:dim :unit-of-duration
   :grain :day}

  "week (unit-of-duration)"
  #"주일?"
  {:dim :unit-of-duration
   :grain :week}

  "month (unit-of-duration)"
  #"(달)(간|동안)?"
  {:dim :unit-of-duration
   :grain :month}
  
  "year (unit-of-duration)"
  #"해|연간|년(간|동안)?"
  {:dim :unit-of-duration
   :grain :year}
  
  "half an hour"
  [(dim :cycle #(= :hour (:grain %))) #"반"]
  {:dim :duration
   :value (duration :minute 30)}

  "a day - 하루"
  #"하루"
  {:dim :duration
   :value (duration :day 1)}

  "<integer> <unit-of-duration>"
  [(integer 0) (dim :unit-of-duration)]; duration can't be negative...
  {:dim :duration
   :value (duration (:grain %2) (:value %1))}

  ; TODO handle cases where ASR outputs "1. 5 hours"
  ; but allowing a space creates many false positive
  "number.number hours" ; in 1.5 hour but also 1.75
  [#"(\d+)\.(\d+)" #"시간"] ;duration can't be negative...
  {:dim :duration
   :value (duration :minute (int (+ (quot (* 6 (Long/parseLong (second (:groups %1)))) (java.lang.Math/pow 10 (- (count (second (:groups %1))) 1))) (* 60 (Long/parseLong (first (:groups %1)))))))}

  "<integer> and an half hours"
  [(integer 0) #"시간반"] ;duration can't be negative...
  {:dim :duration
   :value (duration :minute (+ 30 (* 60 (:value %1))))}

  "in <duration>"
  [(dim :duration) #"(안|내)에?"]
  (in-duration (:value %1))

  "after <duration>"
  [(dim :duration) #"이?후"]
  (merge (in-duration (:value %1)) {:direction :after})

  "<duration> from now"
  [#"지금부터" (dim :duration)]
  (in-duration (:value %1))

  "<duration> ago"
  [(dim :duration) #"이?전"]
  (duration-ago (:value %1))
  
;  "<duration> after <time>"
;  [(dim :duration) #"(?i)after" (dim :time)]
;  (duration-after (:value %1) %3)
;
;  "<duration> before <time>"
;  [(dim :duration) #"(?i)before" (dim :time)]
;  (duration-before (:value %1) %3)

  "about <duration>" ; about
  [#"대충|약" (dim :duration)]
  (-> %2
    (merge {:precision "approximate"}))

  "exactly <duration>" ; sharp
  [#"정확히" (dim :duration)]
  (-> %2
    (merge {:precision "exact"}))

)
