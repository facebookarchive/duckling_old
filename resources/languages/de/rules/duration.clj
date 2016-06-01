; Durations / Periods

(
  "second (unit-of-duration)"
  #"(?i)sek(unde)?n?"
  {:dim :unit-of-duration
   :grain :second}

  "minute (unit-of-duration)"
  #"(?i)min(ute)?n?"
  {:dim :unit-of-duration
   :grain :minute}

  "hour (unit-of-duration)"
  #"(?i)stunden?"
  {:dim :unit-of-duration
   :grain :hour}

  "day (unit-of-duration)"
  #"(?i)tage?n?"
  {:dim :unit-of-duration
   :grain :day}

  "week (unit-of-duration)"
  #"(?i)wochen?"
  {:dim :unit-of-duration
   :grain :week}

  "month (unit-of-duration)"
  #"(?i)monate?n?"
  {:dim :unit-of-duration
   :grain :month}
  
  "year (unit-of-duration)"
  #"(?i)jahre?n?"
  {:dim :unit-of-duration
   :grain :year}
  
  "half an hour"
  [#"(?i)(1/2\s?|(einer )halbe?n? )stunde"]
  {:dim :duration
   :value (duration :minute 30)}

  "fortnight" ;14 days
  #"(?i)(a|one)? fortnight"
  {:dim :duration
   :value (duration :day 14)}
  
  "a <duration>"
  [#"(?i)(in )?eine?(r|n)?" (dim :duration)]
 (in-duration (:value %2))

 "<integer> <unit-of-duration>"
  [(integer 0) (dim :unit-of-duration)]; duration can't be negative...
  {:dim :duration
   :value (duration (:grain %2) (:value %1))}
    
  "<integer> more <unit-of-duration>"
  [(integer 0) #"(?i)mehr|weniger" (dim :unit-of-duration)]; would need to add fields at some point
  {:dim :duration
   :value (duration (:grain %3) (:value %1))}

  ; TODO handle cases where ASR outputs "1. 5 hours"
  ; but allowing a space creates many false positive
  "number.number hours" ; in 1.5 hour but also 1.75
  [#"(\d+)\.(\d+)" #"(?i)stunden?"] ;duration can't be negative...
  {:dim :duration
   :value (duration :minute (int (+ (quot (* 6 (Long/parseLong (second (:groups %1)))) (java.lang.Math/pow 10 (- (count (second (:groups %1))) 1))) (* 60 (Long/parseLong (first (:groups %1)))))))}

  "<integer> and an half hours"
  [(integer 0) #"(?i)ein ?halb stunden?"] ;duration can't be negative...
  {:dim :duration
   :value (duration :minute (+ 30 (* 60 (:value %1))))}

  "a <unit-of-duration>"
  [#"(?i)eine?(r|n)?" (dim :unit-of-duration)]
  {:dim :duration
   :value (duration (:grain %2) 1)}
  
  
  
 "in <duration>"
  [#"(?i)in" (dim :duration)]
  (in-duration (:value %2))

  "after <duration>"
  [#"(?i)nach" (dim :duration)]
  (in-duration (:value %2))

  "<duration> from now"
  [(dim :duration) #"(?i)ab (heute|jetzt)"]
  (in-duration (:value %1))

  "<duration> ago"
  [#"(?i)vor" (dim :duration)]
  (duration-ago (:value %2))
  
  "<duration> hence"
  [(dim :duration) #"(?i)hence"]
  (in-duration (:value %1))
  
  "<duration> after <time>"
  [(dim :duration) #"(?i)nach" (dim :time)]
  (duration-after (:value %1) %3)

  "<duration> before <time>"
  [(dim :duration) #"(?i)vor" (dim :time)]
  (duration-before (:value %1) %3)

  "about <duration>" ; about
  [#"(?i)ungefähr|zirka" (dim :duration)]
  (-> %2
    (merge {:precision "approximate"}))

  "exactly <duration>" ; sharp
  [#"(?i)genau|exakt" (dim :duration)]
  (-> %2
    (merge {:precision "exact"})))


