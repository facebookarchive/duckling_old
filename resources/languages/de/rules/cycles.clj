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
  #"(?i)sekunden?"
  {:dim :cycle
   :grain :second}

  "minute (cycle)"
  #"(?i)minuten?"
  {:dim :cycle
   :grain :minute}

  "hour (cycle)"
  #"(?i)stunden?"
  {:dim :cycle
   :grain :hour}

  "day (cycle)"
  #"(?i)tage?n?"
  {:dim :cycle
   :grain :day}

  "week (cycle)"
  #"(?i)wochen?"
  {:dim :cycle
   :grain :week}

  "month (cycle)"
  #"(?i)monate?n?"
  {:dim :cycle
   :grain :month}
  
  "quarter (cycle)"
  #"(?i)quartale?"
  {:dim :cycle
   :grain :quarter}
  
  "year (cycle)"
  #"(?i)jahre?n?"
  {:dim :cycle
   :grain :year}
  
  "this <cycle>"
  [#"(?i)diese(r|n|s)?|kommende(r|n|s)?" (dim :cycle)]
  (cycle-nth (:grain %2) 0)

  "last <cycle>"
  [#"(?i)letzte(r|n|s)?|vergangene(r|n|s)?" (dim :cycle)]
  (cycle-nth (:grain %2) -1)

  "next <cycle>"
  [#"(?i)nächste(r|n|s)?|kommende(r|n|s)?" (dim :cycle)]
  (cycle-nth (:grain %2) 1)
  
  "the <cycle> after <time>"
  [#"(?i)der" (dim :cycle) #"(?i)nach" (dim :time)]
  (cycle-nth-after (:grain %2) 1 %4)
  
  "the <cycle> before <time>"
  [#"(?i)der" (dim :cycle) #"(?i)vor" (dim :time)]
  (cycle-nth-after (:grain %2) -1 %4)
  
  "last n <cycle>"
  [#"(?i)letzten?|vergangenen?" (integer 1 9999) (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (- (:value %2)))
  
  "next n <cycle>"
  [#"(?i)nächsten?|kommenden?" (integer 1 9999) (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (:value %2))
  
  "<ordinal> <cycle> of <time>"
  [(dim :ordinal) (dim :cycle) #"(?i)im|in|von" (dim :time)]
  (cycle-nth-after-not-immediate (:grain %2) (dec (:value %1)) %4)
  
  "the <ordinal> <cycle> of <time>"
  [#"(?i)der|die|das" (dim :ordinal) (dim :cycle) #"(?i)im|in|von" (dim :time)]
  (cycle-nth-after-not-immediate (:grain %3) (dec (:value %2)) %5)

  ; the 2 following rules may need a different helper
  
  "<ordinal> <cycle> after <time>"
  [(dim :ordinal) (dim :cycle) #"(?i)nach" (dim :time)]
  (cycle-nth-after-not-immediate (:grain %2) (dec (:value %1)) %4)
  
  "the <ordinal> <cycle> after <time>"
  [#"(?i)the" (dim :ordinal) (dim :cycle) #"(?i)nach" (dim :time)]
  (cycle-nth-after-not-immediate (:grain %3) (dec (:value %2)) %5)

  
  ; quarters are a little bit different, you can say "3rd quarter" alone
  
  "<ordinal> quarter"
  [(dim :ordinal) (dim :cycle #(= :quarter (:grain %)))]
  (cycle-nth-after :quarter (dec (:value %1)) (cycle-nth :year 0))

  "<ordinal> quarter <year>"
  [(dim :ordinal) (dim :cycle #(= :quarter (:grain %))) (dim :time)]
  (cycle-nth-after :quarter (dec (:value %1)) %3))

