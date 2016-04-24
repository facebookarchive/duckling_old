; Durations / Periods

(
  "seconde (unit-of-duration)"
  #"(?i)seg(undo)?s?"
  {:dim :unit-of-duration
   :grain :second}

  "minute (unit-of-duration)"
  #"(?i)min(uto)?s?"
  {:dim :unit-of-duration
   :grain :minute}

  "hour (unit-of-duration)"
  #"(?i)h(ora)?s?"
  {:dim :unit-of-duration
   :grain :hour}

  "day (unit-of-duration)"
  #"(?i)d(í|i)as?"
  {:dim :unit-of-duration
   :grain :day}

  "week (unit-of-duration)"
  #"(?i)semanas?"
  {:dim :unit-of-duration
   :grain :week}

  "month (unit-of-duration)"
  #"(?i)mes(es)?"
  {:dim :unit-of-duration
   :grain :month}
  
  "year (unit-of-duration)"
  #"(?i)a(n|ñ)os?"
  {:dim :unit-of-duration
   :grain :year}
  
  "<integer> <unit-of-duration>"
  [(integer 0) (dim :unit-of-duration)] ;prevent negative duration...
  {:dim :duration
   :value (duration (:grain %2) (:value %1))}

  ; "uno <unit-of-duration>" not sure we need that one
  ; [#"(?i)une?" (dim :unit-of-duration)]
  ; {:dim :duration
  ;  :value (duration (:grain %2) 1)}

  "en <duration>"
  [#"(?i)en" (dim :duration)]
  (in-duration (:value %2))

  "hace <duration>"
  [#"hace" (dim :duration)]
  (duration-ago (:value %2))

)
