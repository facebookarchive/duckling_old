; Durations / Periods

(
  "second (unit-of-duration)"
  #"秒間?"
  {:dim :unit-of-duration
   :grain :second}

  "minute (unit-of-duration)"
  #"分間?"
  {:dim :unit-of-duration
   :grain :minute}

  "hour (unit-of-duration)"
  #"時間?"
  {:dim :unit-of-duration
   :grain :hour}

  "day (unit-of-duration)"
  #"日間?"
  {:dim :unit-of-duration
   :grain :day}

  "week (unit-of-duration)"
  #"週間?"
  {:dim :unit-of-duration
   :grain :week}

  "month (unit-of-duration)"
  #"月間?"
  {:dim :unit-of-duration
   :grain :month}
  
  "year (unit-of-duration)"
  #"年間?"
  {:dim :unit-of-duration
   :grain :year}
  
  "<integer> <unit-of-duration>" 
  [(integer 0) (dim :unit-of-duration)] ; duration can't be negative
  {:dim :duration
   :value (duration (:grain %2) (:value %1))}

  "in <duration>"
  [#"再" (dim :duration)]
  (in-duration (:value %2))

  "<duration> from now"
  [(dim :duration) #"後"]
  (in-duration (:value %1))

  "<duration> ago"
  [(dim :duration) #"前" ]
  (duration-ago (:value %1))
)
