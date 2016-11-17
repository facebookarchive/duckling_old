; Durations / Periods

(
  "second (unit-of-duration)"
  #"(?i)sekund(y|zie|(e|ę)|(a|ą)|om|ami|ach|o)?|s"
  {:dim :unit-of-duration
   :grain :second}

  "minute (unit-of-duration)"
  #"(?i)minut(y|cie|(e|ę)|om|o|ami|ach|(a|ą))?|m"
  {:dim :unit-of-duration
   :grain :minute}

  "hour (unit-of-duration)"
  #"(?i)h|godzin(y|(e|ę)|ie|om|o|ami|ach|(a|ą))?"
  {:dim :unit-of-duration
   :grain :hour}

  "day (unit-of-duration)"
  #"(?i)dzie(n|ń|ni(a|ą))|dni(owi|ach|a|ą)?"
  {:dim :unit-of-duration
   :grain :day}

  "week (unit-of-duration)"
  #"(?i)tydzie(n|ń|)|tygod(ni(owi|u|a|em))|tygodn(iach|iami|iom|ie|i)|tyg\.?"
  {:dim :unit-of-duration
   :grain :week}

  "month (unit-of-duration)"
  #"(?i)miesi(a|ą)c(owi|em|u|e|om|ami|ach|a)?"
  {:dim :unit-of-duration
   :grain :month}

  "year (unit-of-duration)"
  #"(?i)rok(u|owi|iem)?|lat(ami|ach|a|om)?"
  {:dim :unit-of-duration
   :grain :year}

   "half an hour"
  [#"(?i)p(o|ó)(l|ł) godziny"]
  {:dim :duration
   :value (duration :minute 30)}

  "<integer> <unit-of-duration>"
  [(integer 0) (dim :unit-of-duration)]; duration can't be negative...
  {:dim :duration
   :value (duration (:grain %2) (:value %1))}

  "<integer> more <unit-of-duration>"
  [(integer 0) #"(?i)more|less" (dim :unit-of-duration)]; would need to add fields at some point
  {:dim :duration
   :value (duration (:grain %3) (:value %1))}

  ; TODO handle cases where ASR outputs "1. 5 hours"
  ; but allowing a space creates many false positive
  "number.number hours" ; in 1.5 hour but also 1.75
  [#"(\d+)\.(\d+)" #"(?i)godzin(y)?"] ;duration can't be negative...
  {:dim :duration
   :value (duration :minute (int (+ (quot (* 6 (Long/parseLong (second (:groups %1)))) (java.lang.Math/pow 10 (- (count (second (:groups %1))) 1))) (* 60 (Long/parseLong (first (:groups %1)))))))}

  "<integer> and an half hours"
  [(integer 0) #"(?i)i (p(o|ó)(l|ł)) godziny"] ;duration can't be negative...
  {:dim :duration
   :value (duration :minute (+ 30 (* 60 (:value %1))))}

 "<unit-of-duration> as a duration"
 (dim :unit-of-duration)
 {:dim :duration
  :value (duration (:grain %1) 1)}

 "in <duration>"
 [#"(?i)(w( ?(prze)?ciągu)?|za) ?(jeszcze)?|przez" (dim :duration)]
 (in-duration (:value %2))

 "after <duration>"
 [#"(?i)po" (dim :duration)]
 (merge (in-duration (:value %2)) {:direction :after})

  "<duration> from now"
  [(dim :duration) #"(?i)od (dzi(s|ś)|teraz)"]
  (in-duration (:value %1))

  "<duration> ago"
  [(dim :duration) #"(?i)temu"]
  (duration-ago (:value %1))

  "<duration> hence"
  [(dim :duration) #"(?i)p(o|ó)(z|ź)niej|potem"]
  (in-duration (:value %1))

  "<duration> after <time>"
  [(dim :duration) #"(?i)po" (dim :time)]
  (duration-after (:value %1) %3)

  "<duration> before <time>"
  [(dim :duration) #"(?i)do|przed" (dim :time)]
  (duration-before (:value %1) %3)

  "about <duration>" ; about
  [#"(?i)(oko(l|ł)o|miej wi(ę|e)cej|jakie(s|ś))" (dim :duration)]
  (-> %2
    (merge {:precision "approximate"}))

 "exactly <duration>" ; sharp
 [#"(?i)r(o|ó)wno|dok(l|ł)adnie" (dim :duration)]
 (-> %2
     (merge {:precision "exact"}))

 )
