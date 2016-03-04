; Durations / Periods

(
  "segundo (unit-of-duration)"
  #"(?i)seg(undo)?s?"
  {:dim :unit-of-duration
   :grain :second}

  "minuto (unit-of-duration)"
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
  #"(?i)m[eê]s(es)?"
  {:dim :unit-of-duration
   :grain :month}
  
  "year (unit-of-duration)"
  #"(?i)anos?"
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

  "em <duration>"
  [#"(?i)em" (dim :duration)]
  (in-duration (:value %2))

  "fazem <duration>"
  [#"faz(em)?" (dim :duration)]
  (duration-ago (:value %2))

)
