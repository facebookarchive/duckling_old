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
  
   "half an hour"
  [#"(?i)(1/2\s?|half an? )hour"]
  {:dim :duration
   :value (duration :minute 30)}

  "<integer> <unit-of-duration>"
  [(integer 0) (dim :unit-of-duration)]; duration can't be negative...
  {:dim :duration
   :value (duration (:grain %2) (:value %1))}
    
  "a <unit-of-duration>"
  [#"(?i)an?" (dim :unit-of-duration)]
  {:dim :duration
   :value (duration (:grain %2) 1)}

  "in/after <duration>"
  [#"(?i)in|after" (dim :duration)]
  (in-duration (:value %2))

  "<duration> from now"
  [(dim :duration) #"(?i)from now"]
  (in-duration (:value %1))

  "<duration> ago"
  [(dim :duration) #"(?i)ago"]
  (duration-ago (:value %1))
)
