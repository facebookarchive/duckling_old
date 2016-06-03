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
  [(integer 0) (dim :unit-of-duration)] ;prevent negative duration...
  {:dim :duration
   :value (duration (:grain %2) (:value %1))}

  "une <unit-of-duration>"
  [#"(?i)une?" (dim :unit-of-duration)]
  {:dim :duration
   :value (duration (:grain %2) 1)}

  "dans <duration>"
  [#"(?i)dans" (dim :duration)]
  (in-duration (:value %2))

  "<duration> plus tard"
  [(dim :duration) #"(?i)plus tard" ]
  (in-duration (:value %1))

  "il y a <duration>"
  [#"(?i)il y a" (dim :duration)]
  (duration-ago (:value %2))

  "<duration> plus tôt"
  [(dim :duration) #"(?i)plus t[oô]t"]
  (duration-ago (:value %1))

  "<duration> apres <time>"
  [(dim :duration) #"(?i)apr[eè]s" (dim :time)]
  (duration-after (:value %1) %3)

  "<duration> avant <time>"
  [(dim :duration) #"(?i)avant" (dim :time)]
  (duration-before (:value %1) %3)

)
