; Cycles are like a heart beat, the next starts just when the previous ends.
; Unlike durations, they have an absolute position in the time, it's just that this position is periodic.
; Examples of phrases involving cycles:
; - this week
; - today (= this day)
; - last month
; - last 2 calendar months (last 2 months is interpreted as a duration)
;
; As soon as you put a quantity (2 months), the cycle becomes a duration.

; Not clear if we need hours, etc. What does 'last hour' mean ?

(
  "second (cycle)"
  #"秒毎?"
  {:dim :cycle
   :grain :second}

  "minute (cycle)"
  #"分毎?"
  {:dim :cycle
   :grain :minute}

  "hour (cycle)"
  #"時毎?"
  {:dim :cycle
   :grain :hour}

  "day (cycle)"
  #"日毎?"
  {:dim :cycle
   :grain :day}

  "week (cycle)"
  #"週毎?"
  {:dim :cycle
   :grain :week}

  "month (cycle)"
  #"月毎?"
  {:dim :cycle
   :grain :month}
  
  "year (cycle)"
  #"年毎?"
  {:dim :cycle
   :grain :year}
  
  "this <cycle>"
  [#"毎" (dim :cycle)]
  (cycle-nth (:grain %2) 0)

  "last <cycle>"
  [#"上[个|個]?" (dim :cycle)]
  (cycle-nth (:grain %2) -1)

  "next <cycle>"
  [#"下[个|個]?" (dim :cycle)]
  (cycle-nth (:grain %2) 1)
  
  "the <cycle> after <time>"
  [#"那" (dim :cycle) #"之?[后後]" (dim :time)]
  (cycle-nth-after (:grain %2) 1 %4)
  
  "the <cycle> before <time>"
  [#"那" (dim :cycle) #"之?前" (dim :time)]
  (cycle-nth-after (:grain %2) -1 %4)
  
  "last n <cycle>"
  [#"上|前" (integer 1 9999) (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (- (:value %2)))
  
  "next n <cycle>"
  [#"下|后|後" (integer 1 9999) (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (:value %2))
)
