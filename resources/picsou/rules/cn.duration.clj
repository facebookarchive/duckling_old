; Durations / Periods

(
  "second (unit-of-duration)"
  #"秒钟?"
  {:dim :unit-of-duration
   :grain seconds
   :in-width (seconds 1)} ; width of interval when "in 10 secs"

  "minute (unit-of-duration)"
  #"分钟?"
  {:dim :unit-of-duration
   :grain minutes
   :in-width (minutes 1)} 

  "hour (unit-of-duration)"
  #"小时"
  {:dim :unit-of-duration
   :grain hours
   :in-width (hours 1)} 

  "day (unit-of-duration)"
  #"天"
  {:dim :unit-of-duration
   :grain days
   :in-width (days 1)}

  "week (unit-of-duration)"
  #"周|礼拜"
  {:dim :unit-of-duration
   :grain weeks
   :in-width (weeks 1)}

  "month (unit-of-duration)"
  #"月"
  {:dim :unit-of-duration
   :grain months
   :in-width (months 1)}
  
  "year (unit-of-duration)"
  #"年"
  {:dim :unit-of-duration
   :grain years
   :in-width (years 1)}
  
  "<integer> <unit-of-duration>"
  [(integer) (dim :unit-of-duration)]
  {:dim :duration
   :val ((:grain %2) (:val %1))
   :in-width (:in-width %2)} ; used by "in <duration>"

  "in <duration>"
  [#"再" (dim :duration)]
  (in-duration (:val %2) (:in-width %2))

  "<duration> from now"
  [(dim :duration) #"后"]
  (in-duration (:val %2) (:in-width %2))

  "<duration> ago"
  [(dim :duration) #"前" ]
  (in-duration (:val %1) (:in-width %1) -)
)
