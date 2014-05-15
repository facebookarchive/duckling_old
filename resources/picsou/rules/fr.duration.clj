; Durations / Periods

(
  "seconde (unit-of-duration)"
  #"(?i)sec(onde)?s?"
  {:dim :unit-of-duration
   :grain seconds
   :in-width (seconds 1)} ; width of interval when "in 10 secs"

  "minute (unit-of-duration)"
  #"(?i)min(ute)?s?"
  {:dim :unit-of-duration
   :grain minutes
   :in-width (minutes 1)}

  "heure (unit-of-duration)"
  #"(?i)h(eure)?s?"
  {:dim :unit-of-duration
   :grain hours
   :in-width (hours 1)}

  "jour (unit-of-duration)"
  #"(?i)jour(n[ée]e?)?s?"
  {:dim :unit-of-duration
   :grain days
   :in-width (days 1)}

  "semaine (unit-of-duration)"
  #"(?i)semaines?"
  {:dim :unit-of-duration
   :grain weeks
   :in-width (weeks 1)}

  "mois (unit-of-duration)"
  #"(?i)mois?"
  {:dim :unit-of-duration
   :grain months
   :in-width (months 1)}
  
  "année (unit-of-duration)"
  #"(?i)an(n[ée]e?)?s?"
  {:dim :unit-of-duration
   :grain years
   :in-width (years 1)}
  
  "<integer> <unit-of-duration>"
  [(integer) (dim :unit-of-duration)]
  {:dim :duration
   :val ((:grain %2) (:val %1))
   :in-width (:in-width %2)} ; used by "in <duration>"

  "dans <duration>"
  [#"(?i)dans" (dim :duration)]
  (in-duration (:val %2) (:in-width %2))

  "il y a <duration>"
  [#"(?i)il y a" (dim :duration)]
  (in-duration (:val %2) (:in-width %2) -)

)
