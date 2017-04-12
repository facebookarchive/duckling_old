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
  #"(?i)שניות|שנייה"
  {:dim :cycle
   :grain :second}

  "minute (cycle)"
  #"(?i)דקה|דקות"
  {:dim :cycle
   :grain :minute}

  "hour (cycle)"
  #"(?i)שעות|שעה"
  {:dim :cycle
   :grain :hour}

  "day (cycle)"
  #"(?i)ימים|יום"
  {:dim :cycle
   :grain :day}
  
  "week (cycle)"
  #"(?i)שבוע|שבועות"
  {:dim :cycle
   :grain :week}

  "month (cycle)"
  #"(?i)חודש|חודשים"
  {:dim :cycle
   :grain :month}
  
  "quarter (cycle)"
  #"(?i)רבע"
  {:dim :cycle
   :grain :quarter}
  
  "year (cycle)"
  #"(?i)שנה"
  {:dim :cycle
   :grain :year}
  
  "this <cycle>"
  [(dim :cycle) #"(?i)הקרוב?ה|הזה|הזאת"]
  (cycle-nth (:grain %1) 0)

  "last <cycle>"
  [(dim :cycle) #"(?i)האחרון|האחרונה|שעבר|שעברה"]
  (cycle-nth (:grain %1) -1)

  "next <cycle>"
  [(dim :cycle) #"(?i)הבאה?" ]
  (cycle-nth (:grain %1) 1)
  
  "the <cycle> after <time>"
  [#"(?i)ה" (dim :cycle) #"(?i)אחרי" (dim :time)]
  (cycle-nth-after (:grain %2) 1 %4)

  "<cycle> after <time>"
  [(dim :cycle) #"(?i)אחרי" (dim :time)]
  (cycle-nth-after (:grain %1) 1 %3)
  
  "the <cycle> before <time>"
  [#"(?i)ה" (dim :cycle) #"(?i)לפני" (dim :time)]
  (cycle-nth-after (:grain %2) -1 %4)
  
  "<cycle> before <time>"
  [(dim :cycle) #"(?i)לפני" (dim :time)]
  (cycle-nth-after (:grain %1) -1 %3)

  "last n <cycle>"
  [(integer 1 9999) (dim :cycle) #"(?i)אחרון|אחרונות|אחרונה|אחרונים" ]
  (cycle-n-not-immediate (:grain %2) (- (:value %1)))
  
  "next n <cycle>"
  [(integer 1 9999) (dim :cycle) #"(?i)הבא|הבאה|הבאים|הבאות"]
  (cycle-n-not-immediate (:grain %3) (:value %2))
  
  ; "<ordinal> <cycle> of <time>"
  ; [(dim :ordinal) (dim :cycle) #"(?i)of|in|from" (dim :time)]
  ; (cycle-nth-after-not-immediate (:grain %2) (dec (:value %1)) %4)
  
  ; "the <ordinal> <cycle> of <time>"
  ; [#"(?i)the" (dim :ordinal) (dim :cycle) #"(?i)of|in|from" (dim :time)]
  ; (cycle-nth-after-not-immediate (:grain %3) (dec (:value %2)) %5)

  ; "the <cycle> of <time>"
  ; [#"(?i)the" (dim :cycle) #"(?i)of" (dim :time)]
  ; (cycle-nth-after-not-immediate (:grain %2) 0 %4)

  ; the 2 following rules may need a different helper
  
  "<ordinal> <cycle> after <time>"
  [(dim :ordinal) (dim :cycle) #"(?i)אחרי|לאחר" (dim :time)]
  (cycle-nth-after-not-immediate (:grain %2) (dec (:value %1)) %4)
  
  "the <ordinal> <cycle> after <time>"
  [#"(?i)ה" (dim :ordinal) (dim :cycle) #"(?i)אחרי|לאחר" (dim :time)]
  (cycle-nth-after-not-immediate (:grain %3) (dec (:value %2)) %5)

  
  ; quarters are a little bit different, you can say "3rd quarter" alone
  
  "<ordinal> quarter"
  [(dim :ordinal) (dim :cycle #(= :quarter (:grain %)))]
  (cycle-nth-after :quarter (dec (:value %1)) (cycle-nth :year 0))

  "<ordinal> quarter <year>"
  [(dim :ordinal) (dim :cycle #(= :quarter (:grain %))) (dim :time)]
  (cycle-nth-after :quarter (dec (:value %1)) %3)
)
