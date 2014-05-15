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
  #"(?i)seconds?"
  {:dim :cycle
   :grain seconds}

  "minute (cycle)"
  #"(?i)minutes?"
  {:dim :cycle
   :grain minutes}

  "hour (cycle)"
  #"(?i)hours?"
  {:dim :cycle
   :grain hours}

  "day (cycle)"
  #"(?i)days?"
  {:dim :cycle
   :grain days}

  "week (cycle)"
  #"(?i)weeks?"
  {:dim :cycle
   :grain weeks}

  "month (cycle)"
  #"(?i)months?"
  {:dim :cycle
   :grain months}
  
  "year (cycle)"
  #"(?i)years?"
  {:dim :cycle
   :grain years}
  
  "this <cycle>"
  [#"(?i)this" (dim :cycle)]
  (this-cycle (:grain %2) 0)

  "last <cycle>"
  [#"(?i)last" (dim :cycle)]
  (this-cycle (:grain %2) -1)

  "next <cycle>"
  [#"(?i)next" (dim :cycle)]
  (this-cycle (:grain %2) 1)
  
  "the <cycle> after <time>"
  [#"(?i)the" (dim :cycle) #"(?i)after" (dim :time)]
  (cycle-relative %4 (:grain %2) 1)
  
  "the <cycle> before <time>"
  [#"(?i)the" (dim :cycle) #"(?i)before" (dim :time)]
  (cycle-relative %4 (:grain %2) -1)
  
  "last n <cycle>"
  [#"(?i)last" (integer 2 9999) (dim :cycle)]
  (this-cycle (:grain %3) (- (:val %2)) (:val %2))
  
  "next n <cycle>"
  [#"(?i)next" (integer 2 9999) (dim :cycle)]
  (this-cycle (:grain %3) 1 (:val %2))
)
