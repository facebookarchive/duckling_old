; Durations / Periods

(
  "second (unit-of-duration)"
  #"(?i)שנייה|שניות"
  {:dim :unit-of-duration
   :grain :second}

  "minute (unit-of-duration)"
  #"(?i)דקה|דקות"
  {:dim :unit-of-duration
   :grain :minute}

  "hour (unit-of-duration)"
  #"(?i)שעה|שעות"
  {:dim :unit-of-duration
   :grain :hour}

  "day (unit-of-duration)"
  #"(?i)יום|ימים"
  {:dim :unit-of-duration
   :grain :day}

  "week (unit-of-duration)"
  #"(?i)שבוע|שבועות"
  {:dim :unit-of-duration
   :grain :week}

  "month (unit-of-duration)"
  #"(?i)חודש|חודשים"
  {:dim :unit-of-duration
   :grain :month}

  "year (unit-of-duration)"
  #"(?i)שנה|שנים"
  {:dim :unit-of-duration
   :grain :year}

   "quarter of an hour"
  [#"(?i)(1/4/s שעה|רבע שעה)"]
  {:dim :duration
   :value (duration :minute 15)}

   "half an hour"
  [#"(?i)(1/2/s שעה|חצי שעה)"]
  {:dim :duration
   :value (duration :minute 30)}

   "three-quarters of an hour"
  [#"(?i)(3/4/s שעה|שלושת רבעי שעה)"]
  {:dim :duration
   :value (duration :minute 45)}

  "<integer> <unit-of-duration>"
  [(integer 0) (dim :unit-of-duration)]; duration can't be negative...
  {:dim :duration
   :value (duration (:grain %2) (:value %1))}

  ; TODO handle cases where ASR outputs "1. 5 hours"
  ; but allowing a space creates many false positive
  "number.number hours" ; in 1.5 hour but also 1.75
  [#"(\d+)\.(\d+)" #"(?i)שעה|שעות"] ;duration can't be negative...
  {:dim :duration
   :value (duration :minute (int (+ (quot (* 6 (Long/parseLong (second (:groups %1)))) (java.lang.Math/pow 10 (- (count (second (:groups %1))) 1))) (* 60 (Long/parseLong (first (:groups %1)))))))}

  "<integer> and an half hours"
  [(integer 0) #"(?i)וחצי (שעות|שעה)"] ;duration can't be negative...
  {:dim :duration
   :value (duration :minute (+ 30 (* 60 (:value %1))))}

  "in <duration>"
  [#"(?i)בעוד" (dim :duration)]
  (in-duration (:value %2))

  "for <duration>"
  [#"(?i)תוך" (dim :duration)]
  (in-duration (:value %2))

  "after <duration>"
  [#"(?i)אחרי" (dim :duration)]
  (merge (in-duration (:value %2)) {:direction :after})

  "<duration> from now"
  [(dim :duration) #"(?i)מעכשיו"]
  (in-duration (:value %1))

  "<duration> ago"
  [#"(?i)לפני" (dim :duration)]
  (duration-ago (:value %2))

  "<duration> after <time>"
  [(dim :duration) #"(?i)אחרי|לאחר" (dim :time)]
  (duration-after (:value %1) %3)

  "<duration> before <time>"
  [(dim :duration) #"(?i)לפני" (dim :time)]
  (duration-before (:value %1) %3)

  "about <duration>" ; about
  [#"(?i)(בערך|סביבות|בקירוב)" (dim :duration)]
  (-> %2
    (merge {:precision "approximate"}))

  "exactly <duration>" ; sharp
  [#"(?i)בדיוק" (dim :duration)]
  (-> %2
    (merge {:precision "exact"}))

)
