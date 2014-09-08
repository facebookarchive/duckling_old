; Durations / Periods

(
  "second (unit-of-duration)"
  #"(?i)sec(ond)?s?"
  {:dim :unit-of-duration
   :grain seconds
   :in-width (seconds 1)} ; width of interval when "in 10 secs"

  "minute (unit-of-duration)"
  #"(?i)min(ute)?s?"
  {:dim :unit-of-duration
   :grain minutes
   :in-width (minutes 1)} 

  "hour (unit-of-duration)"
  #"(?i)hours?"
  {:dim :unit-of-duration
   :grain hours
   :in-width (hours 1)} 

  "day (unit-of-duration)"
  #"(?i)days?"
  {:dim :unit-of-duration
   :grain days
   :in-width (days 1)}

  "week (unit-of-duration)"
  #"(?i)weeks?"
  {:dim :unit-of-duration
   :grain weeks
   :in-width (weeks 1)}

  "month (unit-of-duration)"
  #"(?i)months?"
  {:dim :unit-of-duration
   :grain months
   :in-width (months 1)}
  
  "year (unit-of-duration)"
  #"(?i)years?"
  {:dim :unit-of-duration
   :grain years
   :in-width (years 1)}
  
  "half an <unit-of-duration>"
  [#"(?i)half an" (dim :unit-of-duration)]
  {:dim :duration
   :val ((:grain %2) 0.5)
   :in-width (:in-width %2)}

  "<integer> <unit-of-duration>"
  [(integer 0) (dim :unit-of-duration)] ;duration can't be negative...
  {:dim :duration
   :val ((:grain %2) (:val %1))
   :in-width (:in-width %2)} ; used by "in <duration>"

  "number.number hours" ; in 1. 5 hour
  [#"(\d*)\.\s?(\d+)" #"(?i)hours?"] ;duration can't be negative...#"(?i)hours?"
  {:dim :duration
   :val (minutes (+ (/ (* 6 (Long/parseLong (second (:groups %1)))) (java.lang.Math/pow 10 (- (count (second (:groups %1))) 1))) (* 60 (Long/parseLong (first (:groups %1))))))
   :in-width (minutes 1)} ; used by "in <duration>"

  "a <unit-of-duration>"
  [#"(?i)an?" (dim :unit-of-duration)]
  {:dim :duration
   :val ((:grain %2) 1)
   :in-width (:in-width %2)} ; used by "in <duration>"

  "in/after <duration>"
  [#"(?i)in|after" (dim :duration)]
  (in-duration (:val %2) (:in-width %2))

  "<duration> from now"
  [(dim :duration) #"(?i)from now"]
  (in-duration (:val %1) (:in-width %1))

  "<duration> ago"
  [(dim :duration) #"(?i)ago"]
  (in-duration (:val %1) (:in-width %1) -)
)
