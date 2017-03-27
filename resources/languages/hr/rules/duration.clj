; Durations / Periods

("second (unit-of-duration)"
  #"(?i)sek(und)?(a|e|i|u)?"
  {:dim :unit-of-duration :grain :second}

  "minute (unit-of-duration)"
  #"(?i)min(ut)?(a|e|i|u)?"
  {:dim :unit-of-duration :grain :minute}

  "hour (unit-of-duration)"
  #"(?i)(h|sat(a|i|)?)"
  {:dim :unit-of-duration :grain :hour}

  "day (unit-of-duration)"
  #"(?i)dana?"
  {:dim :unit-of-duration :grain :day}

  "week (unit-of-duration)"
  #"(?i)tjeda?na?"
  {:dim :unit-of-duration :grain :week}

  "month (unit-of-duration)"
  #"(?i)mjesec(a|i)?"
  {:dim :unit-of-duration :grain :month}

  "year (unit-of-duration)"
  #"(?i)godin(a|e|u)"
  {:dim :unit-of-duration :grain :year}

  "quarter of an hour"
  [#"(?i)((1/4|frtalj|kvarat|(c|č)etvrt)\s?(h|sata)?)"]
  {:dim :duration :value (duration :minute 15)}

  "half an hour"
  [#"(?i)(1/2\s?(h|sata)?|pol?a? sata)"]
  {:dim :duration :value (duration :minute 30)}

  "three-quarters of an hour"
  [#"(?i)((3/4|tri-?frtalja|tri-?kvarat|tri-?(c|č)etvrt(ine)?)\s?(h|sata)?)"]
  {:dim :duration :value (duration :minute 45)}

  ;  "fortnight" ;14 days
  ;  #"(?i)(a|one)? fortnight"
  ;  {:dim :duration
  ;   :value (duration :day 14)}

  "<integer> <unit-of-duration>"
  [(integer 0) (dim :unit-of-duration)]; duration can't be negative...
  {:dim :duration :value (duration (:grain %2) (:value %1))}

  "<integer> more <unit-of-duration>"
  [(integer 0) #"(?i)vi(s|š)e|manje" (dim :unit-of-duration)]; would need to add fields at some point
  {:dim :duration :value (duration (:grain %3) (:value %1))}

  ; TODO handle cases where ASR outputs "1. 5 hours"
  ; but allowing a space creates many false positive
  "number.number hours" ; in 1.5 hour but also 1.75
  [#"(\d+)\.(\d+)" #"(?i)sat(i|a)?"] ;duration can't be negative...
  {:dim   :duration
   :value (duration :minute
                    (int
                      (+
                        (quot (* 6 (Long/parseLong (second (:groups %1))))
                              (java.lang.Math/pow 10 (- (count (second (:groups %1))) 1)))
                        (* 60 (Long/parseLong (first (:groups %1)))))))}

  "<integer> and an half hours"
  [(integer 0) #"(?i)i pol?a?"] ;duration can't be negative...
  {:dim :duration :value (duration :minute (+ 30 (* 60 (:value %1))))}

  ;  "a <unit-of-duration>"
  ;  [#"(?i)an?" (dim :unit-of-duration)]
  ;  {:dim :duration
  ;   :value (duration (:grain %2) 1)}

  "in <duration>"
  [#"(?i)za" (dim :duration)]
  (in-duration (:value %2))

  "about <duration>"
  [#"(?i)oko" (dim :duration)]
  (in-duration (:value %2))

  "for <duration>"
  [#"(?i)za( jo(s|š))?|u" (dim :duration)]
  (in-duration (:value %2))

  "after <duration>"
  [#"(?i)(nakon|poslije|za)( jo(s|š))?" (dim :duration)]
  (merge (in-duration (:value %2)) {:direction :after})

  "<duration> from now"
  [(dim :duration) #"(?i)od (sada?|ovog trenutka|dana(s|š)nj(i|eg) dana?)"]
  (in-duration (:value %1))

  "prije <duration>"
  [#"(?i)prije" (dim :duration)]
  (duration-ago (:value %2))

;  "za <duration>"
;  [#"(?i)za" (dim :duration)]
;  (in-duration (:value %2))

  "<duration> after <time>"
  [(dim :duration) #"(?i)nakon|poslije|od" (dim :time)]
  (duration-after (:value %1) %3)

  "<duration> before <time>"
  [(dim :duration) #"(?i)prije" (dim :time)]
  (duration-before (:value %1) %3)

  "about <duration>" ; about
  [#"(?i)(oko|otprilike|odokativno)" (dim :duration)]
  (-> %2
      (merge {:precision "approximate"}))

  "exactly <duration>" ; sharp
  [#"(?i)to(c|č)no" (dim :duration)]
  (-> %2
      (merge {:precision "exact"})))
