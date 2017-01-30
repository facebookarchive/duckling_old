; Durations / Periods

(
  "second (unit-of-duration)"
  #"(?i)sec(ond[oi])?"
  {:dim :unit-of-duration
   :grain :second} ; width of interval when "in 10 secs"

  "minute (unit-of-duration)"
  #"(?i)min(ut[oi])?"
  {:dim :unit-of-duration
   :grain :minute}

  "hour (unit-of-duration)"
  #"(?i)or[ae]"
  {:dim :unit-of-duration
   :grain :hour}

  "day (unit-of-duration)"
  #"(?i)giorn[oi]"
  {:dim :unit-of-duration
   :grain :day}

  "week (unit-of-duration)"
  #"(?i)settiman[ae]"
  {:dim :unit-of-duration
   :grain :week}

  "month (unit-of-duration)"
  #"(?i)mes[ei]"
  {:dim :unit-of-duration
   :grain :month}

  "year (unit-of-duration)"
  #"(?i)ann[oi]"
  {:dim :unit-of-duration
   :grain :year}

  "<integer> <unit-of-duration>"
  [(integer 0) (dim :unit-of-duration)] ;prevent negative duration...
  {:dim :duration
   :value (duration (:grain %2) (:value %1))}

  "une <unit-of-duration>"
  [#"(?i)un[a']?" (dim :unit-of-duration)]
  {:dim :duration
   :value (duration (:grain %2) 1)}

  "un quarto d'ora"
  [#"(?i)un quarto d['i] ?ora"]
  {:dim :duration
   :value (duration :minute 15)}

  "mezz'ora"
  [#"(?i)mezz[a'] ?ora"]
  {:dim :duration
   :value (duration :minute 30)}

  "tre quarti d'ora"
  [#"(?i)(3|tre) quarti d['i] ?ora"]
  {:dim :duration
   :value (duration :minute 45)}

  "in/after <duration>"
  [#"(?i)[tf]ra|dopo" (dim :duration)]
  (in-duration (:value %2))

  "<duration> ago"
  [(dim :duration) #"(?i)fa"]
  (duration-ago (:value %2))
)
