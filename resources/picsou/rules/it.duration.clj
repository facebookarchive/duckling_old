; Durations / Periods

(
  "second (unit-of-duration)"
  #"(?i)sec(ond[oi])?"
  {:dim :unit-of-duration
   :grain seconds
   :in-width (seconds 1)} ; width of interval when "in 10 secs"

  "minute (unit-of-duration)"
  #"(?i)min(ut[oi])?"
  {:dim :unit-of-duration
   :grain minutes
   :in-width (minutes 1)} 

  "hour (unit-of-duration)"
  #"(?i)or[ae]"
  {:dim :unit-of-duration
   :grain hours
   :in-width (hours 1)} 

  "day (unit-of-duration)"
  #"(?i)giorn[oi]"
  {:dim :unit-of-duration
   :grain days
   :in-width (days 1)}

  "week (unit-of-duration)"
  #"(?i)settiman[ae]"
  {:dim :unit-of-duration
   :grain weeks
   :in-width (weeks 1)}

  "month (unit-of-duration)"
  #"(?i)mes[ei]"
  {:dim :unit-of-duration
   :grain months
   :in-width (months 1)}
  
  "year (unit-of-duration)"
  #"(?i)ann[oi]"
  {:dim :unit-of-duration
   :grain years
   :in-width (years 1)}
  
  "<integer> <unit-of-duration>"
  [(integer) (dim :unit-of-duration)]
  {:dim :duration
   :val ((:grain %2) (:val %1))
   :in-width (:in-width %2)} ; used by "in <duration>"

  "in/after <duration>"
  [#"(?i)in|dopo" (dim :duration)]
  (in-duration (:val %2) (:in-width %2))

  "<duration> ago"
  [(dim :duration) #"(?i)fa"]
  (in-duration (:val %1) (:in-width %1) -)
)
