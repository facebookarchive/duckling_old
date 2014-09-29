; Durations / Periods

(
  "second (unit-of-duration)"
  #"秒[钟|鐘]?"
  {:dim :unit-of-duration
   :grain :second}

  "minute (unit-of-duration)"
  #"分[钟|鐘]?"
  {:dim :unit-of-duration
   :grain :minute}

  "hour (unit-of-duration)"
  #"小时|小時"
  {:dim :unit-of-duration
   :grain :hour}

  "day (unit-of-duration)"
  #"天"
  {:dim :unit-of-duration
   :grain :day}

  "week (unit-of-duration)"
  #"周|週|礼拜|禮拜"
  {:dim :unit-of-duration
   :grain :week}

  "month (unit-of-duration)"
  #"月"
  {:dim :unit-of-duration
   :grain :month}
  
  "year (unit-of-duration)"
  #"年(?=[前|后|後])"
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
  [(dim :duration) #"后|後"]
  (in-duration (:value %1))

  "<duration> ago"
  [(dim :duration) #"前" ]
  (duration-ago (:value %1))
)