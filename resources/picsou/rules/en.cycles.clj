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
  #"(?i)seconds?"
  {:dim :cycle
   :grain :second}

  "minute (cycle)"
  #"(?i)minutes?"
  {:dim :cycle
   :grain :minute}

  "hour (cycle)"
  #"(?i)hours?"
  {:dim :cycle
   :grain :hour}

  "day (cycle)"
  #"(?i)days?"
  {:dim :cycle
   :grain :day}

  "week (cycle)"
  #"(?i)weeks?"
  {:dim :cycle
   :grain :week}

  "month (cycle)"
  #"(?i)months?"
  {:dim :cycle
   :grain :month}
  
  "year (cycle)"
  #"(?i)years?"
  {:dim :cycle
   :grain :year}
  
  "this <cycle>"
  [#"(?i)this" (dim :cycle)]
  (cycle-nth (:grain %2) 0)

  "last <cycle>"
  [#"(?i)last" (dim :cycle)]
  (cycle-nth (:grain %2) -1)

  "next <cycle>"
  [#"(?i)next" (dim :cycle)]
  (cycle-nth (:grain %2) 1)
  
  "the <cycle> after <time>"
  [#"(?i)the" (dim :cycle) #"(?i)after" (dim :time)]
  (cycle-nth-after (:grain %2) 1 %4)
  
  "the <cycle> before <time>"
  [#"(?i)the" (dim :cycle) #"(?i)before" (dim :time)]
  (cycle-nth-after (:grain %2) -1 %4)
  
  "last n <cycle>"
  [#"(?i)last" (integer 1 9999) (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (- (:val %2)))
  
  "next n <cycle>"
  [#"(?i)next" (integer 1 9999) (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (:val %2))
)
