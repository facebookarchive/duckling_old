; Cycles are like a heart beat, the next starts just when the previous ends.
; Unlike durations, they have an absolute position in the time, it's just that this position is periodic.
; Examples of phrases involving cycles:
; - this week
; - today (= this day)
; - last month
; - last 2 calendar months (last 2 months is interpreted as a duration)
;
; As soon as you put a quantity (2 months), the cycle becomes a duration.


(
  "second (cycle)"
  #"초"
  {:dim :cycle
   :grain :second}

  "minute (cycle)"
  #"분"
  {:dim :cycle
   :grain :minute}

  "hour (cycle)"
  #"시간?"
  {:dim :cycle
   :grain :hour}

  "day (cycle)"
  #"날|일(간|동안)?"
  {:dim :cycle
   :grain :day}

  "week (cycle)"
  #"주(간|동안)?"
  {:dim :cycle
   :grain :week}

  "month (cycle)"
  #"(달)(간|동안)?"
  {:dim :cycle
   :grain :month}
  
  "quarter (cycle)"
  #"분기(간|동안)?"
  {:dim :cycle
   :grain :quarter}
  
  "year (cycle)"
  #"해|연간|년(간|동안)?"
  {:dim :cycle
   :grain :year}
  
  "this <cycle>"
  [#"이번|금|올" (dim :cycle)]
  (cycle-nth (:grain %2) 0)

  "last <cycle>"
  [#"지난|작|전|저번" (dim :cycle)]
  (cycle-nth (:grain %2) -1)

  "next <cycle>"
  [#"다음|오는|차|내" (dim :cycle)]
  (cycle-nth (:grain %2) 1)

  "<time> 마지막 <cycle> "
  [(dim :time) #"마지막" (dim :cycle)]
  (cycle-last-of %3 %1)

  "<time> <ordinal> <cycle>"
  [(dim :time) (dim :ordinal) (dim :cycle)]
  (cycle-nth-after-not-immediate (:grain %3) (dec (:value %2)) %1)

  "the day after tomorrow - 내일모래"
  #"(내일)?모래"
  (cycle-nth-after :day 1 (cycle-nth :day 1))

  "the day before yesterday - 엊그제"
  #"엊?그[제|재]"
  (cycle-nth-after :day -1 (cycle-nth :day -1))

;  "<time> <cycle>이후"
;  [(dim :time) (dim :cycle) #"이?후" ]
;  (cycle-nth-after (:grain %2) 1 %1)
;
;  "<time> <cycle>이전"
;  [(dim :time) (dim :cycle) #"이?전" ]
;  (cycle-nth-after (:grain %2) -1 %1)

  "last n <cycle>"
  [#"지난" (integer 1 9999) (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (- (:value %2)))
  
  "next n <cycle>"
  [#"다음" (integer 1 9999) (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (:value %2))

  "<1..4> quarter"
  [(integer 1 4) (dim :cycle #(= :quarter (:grain %)))]
  (cycle-nth-after :quarter (dec (:value %1)) (cycle-nth :year 0))

  "<year> <1..4>quarter"
  [(dim :time) (integer 1 4) (dim :cycle #(= :quarter (:grain %))) ]
  (cycle-nth-after :quarter (dec (:value %2)) %1)
  
)
