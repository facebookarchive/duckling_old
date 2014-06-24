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
  #"秒钟?"
  {:dim :cycle
   :grain seconds}

  "minute (cycle)"
  #"分钟?"
  {:dim :cycle
   :grain minutes}

  "hour (cycle)"
  #"小时"
  {:dim :cycle
   :grain hours}

  "day (cycle)"
  #"天"
  {:dim :cycle
   :grain days}

  "week (cycle)"
  #"周|礼拜"
  {:dim :cycle
   :grain weeks}

  "month (cycle)"
  #"月"
  {:dim :cycle
   :grain months}
  
  "year (cycle)"
  #"年"
  {:dim :cycle
   :grain years}
  
  "this <cycle>"
  [#"这一?" (dim :cycle)]
  (this-cycle (:grain %2) 0)

  "last <cycle>"
  [#"上个?" (dim :cycle)]
  (this-cycle (:grain %2) -1)

  "next <cycle>"
  [#"下个?" (dim :cycle)]
  (this-cycle (:grain %2) 1)
  
  "the <cycle> after <time>"
  [#"那" (dim :cycle) #"之?后" (dim :time)]
  (cycle-relative %4 (:grain %2) 1)
  
  "the <cycle> before <time>"
  [#"那" (dim :cycle) #"之?前" (dim :time)]
  (cycle-relative %4 (:grain %2) -1)
  
  "last n <cycle>"
  [#"上|前" (integer 2 9999) (dim :cycle)]
  (this-cycle (:grain %3) (- (:val %2)) (:val %2))
  
  "next n <cycle>"
  [#"下|后" (integer 2 9999) (dim :cycle)]
  (this-cycle (:grain %3) 1 (:val %2))
)
