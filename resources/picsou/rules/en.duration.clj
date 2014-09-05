; Durations / Periods

(
  "second (unit-of-duration)"
  #"(?i)sec(ond)?s?"
  {:dim :unit-of-duration
   :grain :second}

  "minute (unit-of-duration)"
  #"(?i)min(ute)?s?"
  {:dim :unit-of-duration
   :grain :minute}

  "hour (unit-of-duration)"
  #"(?i)hours?"
  {:dim :unit-of-duration
   :grain :hour}

  "day (unit-of-duration)"
  #"(?i)days?"
  {:dim :unit-of-duration
   :grain :day}

  "week (unit-of-duration)"
  #"(?i)weeks?"
  {:dim :unit-of-duration
   :grain :week}

  "month (unit-of-duration)"
  #"(?i)months?"
  {:dim :unit-of-duration
   :grain :month}
  
  "year (unit-of-duration)"
  #"(?i)years?"
  {:dim :unit-of-duration
   :grain :year}
  
  ; "half an <unit-of-duration>"
  ; [#"(?i)half an" (dim :unit-of-duration)]
  ; {:dim :duration
  ;  :val ((:grain %2) 0.5)
  ;  :grain (:grain %2)}

  "<integer> <unit-of-duration>"
  [(integer) (dim :unit-of-duration)]
  {:dim :duration
   :val (duration (:grain %2) (:val %1))}
    
  "a <unit-of-duration>"
  [#"(?i)an?" (dim :unit-of-duration)]
  {:dim :duration
   :val (duration (:grain %2) 1)}

  "in/after <duration>"
  [#"(?i)in|after" (dim :duration)]
  (in-duration (:val %2))

  "<duration> from now"
  [(dim :duration) #"(?i)from now"]
  (in-duration (:val %1))

  "<duration> ago"
  [(dim :duration) #"(?i)ago"]
  (duration-ago (:val %1))
)
