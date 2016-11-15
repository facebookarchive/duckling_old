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

   "un quart heure"
  [#"(?i)(1/4\s?h(eure)?|(un|1) quart d'heure)"]
  {:dim :duration
   :value (duration :minute 15)}

   "une demi heure"
  [#"(?i)(1/2\s?h(eure)?|(1|une) demi(e)?(\s|-)heure)"]
  {:dim :duration
   :value (duration :minute 30)}

   "trois quarts d'heure"
  [#"(?i)(3/4\s?h(eure)?|(3|trois) quart(s)? d'heure)"]
  {:dim :duration
   :value (duration :minute 45)}

  "<integer> <unit-of-duration>"
  [(integer 0) (dim :unit-of-duration)] ;prevent negative duration...
  {:dim :duration
   :value (duration (:grain %2) (:value %1))}

  "une <unit-of-duration>"
  [#"(?i)une|la|le?" (dim :unit-of-duration)]
  {:dim :duration
   :value (duration (:grain %2) 1)}

  "dans <duration>"
  [#"(?i)dans" (dim :duration)]
  (in-duration (:value %2))

  "environ <duration>"
  [#"(?i)environ" (dim :duration)]
  (in-duration (:value %2))

  "il y a <duration>"
  [#"(?i)il y a" (dim :duration)]
  (duration-ago (:value %2))

  "<duration> apres <time>"
  [(dim :duration) #"(?i)apr[eè]s" (dim :time)]
  (duration-after (:value %1) %3)

  "<duration> avant <time>"
  [(dim :duration) #"(?i)avant" (dim :time)]
  (duration-before (:value %1) %3)

)
