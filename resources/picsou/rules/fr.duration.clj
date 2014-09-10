; Durations / Periods

(
  "seconde (unit-of-duration)"
  #"(?i)sec(onde)?s?"
  {:dim :unit-of-duration
   :grain :second}

  "minute (unit-of-duration)"
  #"(?i)min(ute)?s?"
  {:dim :unit-of-duration
   :grain :minute}

  "heure (unit-of-duration)"
  #"(?i)h(eure)?s?"
  {:dim :unit-of-duration
   :grain :hour}

  "jour (unit-of-duration)"
  #"(?i)jour(n[ée]e?)?s?"
  {:dim :unit-of-duration
   :grain :day}

  "semaine (unit-of-duration)"
  #"(?i)semaines?"
  {:dim :unit-of-duration
   :grain :week}

  "mois (unit-of-duration)"
  #"(?i)mois?"
  {:dim :unit-of-duration
   :grain :month}
  
  "année (unit-of-duration)"
  #"(?i)an(n[ée]e?)?s?"
  {:dim :unit-of-duration
   :grain :year}
  
  "<integer> <unit-of-duration>"
  [(integer) (dim :unit-of-duration)]
  {:dim :duration
   :val (duration (:grain %2) (:val %1))}

  "une <unit-of-duration>"
  [#"(?i)une?" (dim :unit-of-duration)]
  {:dim :duration
   :val (duration (:grain %2) 1)}

  "dans <duration>"
  [#"(?i)dans" (dim :duration)]
  (in-duration (:val %2))

  "il y a <duration>"
  [#"(?i)il y a" (dim :duration)]
  (duration-ago (:val %2))

)
