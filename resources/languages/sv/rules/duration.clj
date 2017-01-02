; Durations / Periods

(
  "second (unit-of-duration)"
  #"(?i)sek(und(er(na)?)?)?"
  {:dim :unit-of-duration
   :grain :second}

  "minute (unit-of-duration)"
  #"(?i)min(ut(er(na)?)?)?"
  {:dim :unit-of-duration
   :grain :minute}

  "hour (unit-of-duration)"
  #"(?i)t(imm(e(n)?|ar(na)?)?)?"
  {:dim :unit-of-duration
   :grain :hour}

  "day (unit-of-duration)"
  #"(?i)dag(ar(na)?)?"
  {:dim :unit-of-duration
   :grain :day}

  "week (unit-of-duration)"
  #"(?i)veck(a(n)?|or(na)?)?"
  {:dim :unit-of-duration
   :grain :week}

  "month (unit-of-duration)"
  #"(?i)månad(er(na)?)?"
  {:dim :unit-of-duration
   :grain :month}

  "year (unit-of-duration)"
  #"(?i)år"
  {:dim :unit-of-duration
   :grain :year}

   "half an hour"
  [#"(?i)(1/2|en halv) timme"]
  {:dim :duration
   :value (duration :minute 30)}

  "a <duration>"
  [#"(?i)(om )?en|ett" (dim :duration)]
  (in-duration (:value %2))

  "<integer> <unit-of-duration>"
  [(integer 0) (dim :unit-of-duration)]; duration can't be negative...
  {:dim :duration
   :value (duration (:grain %2) (:value %1))}

  "<integer> more <unit-of-duration>"
  [(integer 0) (dim :unit-of-duration) #"(?i)fler|mer"]; would need to add fields at some point
  {:dim :duration
   :value (duration (:grain %2) (:value %1))}

  ; TODO handle cases where ASR outputs "1. 5 hours"
  ; but allowing a space creates many false positive
  "number.number hours" ; in 1.5 hour but also 1.75
  [#"(\d+)\,(\d+)" #"(?i)timm(e|ar)?"] ;duration can't be negative...
  {:dim :duration
   :value (duration :minute (int (+ (quot (* 6 (Long/parseLong (second (:groups %1)))) (java.lang.Math/pow 10 (- (count (second (:groups %1))) 1))) (* 60 (Long/parseLong (first (:groups %1)))))))}

  "<integer> and an half hours"
  [(integer 0) #"(?i)och (en )?halv timme?"] ;duration can't be negative...
  {:dim :duration
   :value (duration :minute (+ 30 (* 60 (:value %1))))}

  "a <unit-of-duration>"
  [#"(?i)en|ett?" (dim :unit-of-duration)]
  {:dim :duration
   :value (duration (:grain %2) 1)}

  "in <duration>"
  [#"(?i)om" (dim :duration)]
  (in-duration (:value %2))

  "after <duration>"
  [#"(?i)efter" (dim :duration)]
  (merge (in-duration (:value %2)) {:direction :after})

  "<duration> from now"
  [(dim :duration) #"(?i)från (idag|nu)"]
  (in-duration (:value %1))

  "<duration> ago"
  [(dim :duration) #"(?i)sedan"]
  (duration-ago (:value %1))

  "<duration> after <time>"
  [(dim :duration) #"(?i)efter" (dim :time)]
  (duration-after (:value %1) %3)

  "<duration> before <time>"
  [(dim :duration) #"(?i)före" (dim :time)]
  (duration-before (:value %1) %3)

  "about <duration>" ; about
  [#"(?i)(omkring|cirka|ca.|ca|runt)" (dim :duration)]
  (-> %2
    (merge {:precision "approximate"}))

  "exactly <duration>" ; sharp
  [#"(?i)(precis|exakt)" (dim :duration)]
  (-> %2
    (merge {:precision "exact"}))

)
