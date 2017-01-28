(
  ; cycle
  "second (cycle)"
  #"(?i)seconde(n?)?"
  {:dim :cycle
   :grain :second}

  "minute (cycle)"
  #"(?i)minuten|minuut?"
  {:dim :cycle
    :grain :minute}

  "hour (cycle)"
  #"(?i)uur?"
  {:dim :cycle
   :grain :hour}

   "day (cycle)"
   #"(?i)dag(en?)?"
   {:dim :cycle
    :grain :day}

   "week (cycle)"
   #"(?i)week|weken?"
   {:dim :cycle
    :grain :week}

   "month (cycle)"
   #"(?i)maand(en)?"
   {:dim :cycle
    :grain :month}

   "quarter (cycle)"
   #"(?i)kwart?"
   {:dim :cycle
    :grain :quarter}

   "year (cycle)"
   #"(?i)jaar?"
   {:dim :cycle
    :grain :year}

  "this <cycle>"
  [#"(?i)deze|dit" (dim :cycle)]
  (cycle-nth (:grain %2) 0)

  "last <cycle>"
  [#"(?i)afgelopen|vorig(e)?" (dim :cycle)]
  (cycle-nth (:grain %2) -1)

  "next <cycle>"
  [#"(?i)over|volgend(e)?" (dim :cycle)]
  (cycle-nth (:grain %2) 1)

  "next n <cycle>"
  [#"(?i)over" (integer 1 9999) (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (:value %2))

  "last n <cycle>"
  [#"(?i)laatste|afgelopen|voor" (integer 1 9999) (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (- (:value %2)))
)
