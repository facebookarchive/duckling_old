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
  #"(?i)(giây|s|sec)"
  {:dim :cycle
   :grain :second}

  "minute (cycle)"
  #"(?i)(phút|m|min)"
  {:dim :cycle
   :grain :minute}

  "hour (cycle)"
  #"(?i)(giờ|h|tiếng)"
  {:dim :cycle
   :grain :hour}

  "day (cycle)"
  #"(?i)ngày"
  {:dim :cycle
   :grain :day}

  "week (cycle)"
  #"(?i)tuần"
  {:dim :cycle
   :grain :week}

  "month (cycle)"
  #"(?i)tháng"
  {:dim :cycle
   :grain :month}

  "quarter (cycle)"
  #"(?i)quý"
  {:dim :cycle
   :grain :quarter}

  "year (cycle)"
  #"(?i)năm"
  {:dim :cycle
   :grain :year}

  "this <cycle>"
  [(dim :cycle) #"(?i)nay|này|hiện tại|hôm nay|năm nay"]
  (cycle-nth (:grain %1) 0)

  "last <cycle>"
  [(dim :cycle) #"(?i)trước|qua|vừa rồi|ngoái"]
  (cycle-nth (:grain %1) -1)

  "next <cycle>"
  [(dim :cycle) #"(?i)(tới|kế|sau|tiếp)( theo)?( tiếp)?"]
  (cycle-nth (:grain %1) 1)

  "last n <cycle>"
  [(integer 1 9999) (dim :cycle) #"(?i)trước|qua|vừa rồi|ngoái|vừa qua"]
  (cycle-n-not-immediate (:grain %2) (- (:value %1)))

  "next n <cycle>"
  [(integer 1 9999) (dim :cycle) #"(?i)(tới|kế|sau|tiếp)( theo)?( tiếp)?"]
  (cycle-n-not-immediate (:grain %2) (:value %1))

  "<ordinal> <cycle> of <time>"
  [(dim :cycle) (dim :ordinal) #"(?i)của|trong" (dim :time)]
  (cycle-nth-after-not-immediate (:grain %1) (dec (:value %2)) %4)

   ; quarters are a little bit different, you can say "3rd quarter" alone

  "quarter <number>"
  [(dim :cycle #(= :quarter (:grain %))) (dim :number)]
  (cycle-nth-after :quarter (dec (:value %2)) (cycle-nth :year 0))

  "quarter <number> <year>"
  [(dim :cycle #(= :quarter (:grain %))) (dim :number) (dim :time)]
  (cycle-nth-after :quarter (dec (:value %2)) %3)

  "quarter <number> of <year>"
  [(dim :cycle #(= :quarter (:grain %))) (dim :number) #"(?i)của|trong" (dim :time)]
  (cycle-nth-after :quarter (dec (:value %2)) %4)
)
