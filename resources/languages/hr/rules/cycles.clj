; Cycles are like a heart beat, the next starts just when the previous ends.
; Unlike durations, they have an absolute position in the time, it's just that this position is periodic.
; Examples of phrases involving cycles:
; - this week
; - today (= this day)
; - last month
; - last 2 calendar months (last 2 months is interpreted as a duration)
;
; As soon as you put a quantity (2 months), the cycle becomes a duration.


("second (cycle)"
  #"(?i)sekund(a|e|u)?"
  {:dim :cycle :grain :second}

  "minute (cycle)"
  #"(?i)minut(a|e|u)?"
  {:dim :cycle :grain :minute}

  "hour (cycle)"
  #"(?i)h|sat(i|a|e)?"
  {:dim :cycle :grain :hour}

  "day (cycle)"
  #"(?i)dan(i|a|e)?"
  {:dim :cycle :grain :day}

  "week (cycle)"
  #"(?i)tjeda?n(a|e|u|i)?"
  {:dim :cycle :grain :week}

  "month (cycle)"
  #"(?i)mjesec(a|e|u|i)?"
  {:dim :cycle :grain :month}

  "quarter (cycle)"
  #"(?i)kvartalu?|tromjese(c|č)j(e|u)"
  {:dim :cycle :grain :quarter}

  "year (cycle)"
  #"(?i)godin(a|e|u)"
  {:dim :cycle :grain :year}

  "this <cycle>"
  [#"(?i)ov(aj?|og?|e|i)" (dim :cycle)]
  (cycle-nth (:grain %2) 0)

  "last <cycle>"
  [#"(?i)prethodn(i|a|e|u)|pro(s|š)l(i|a|e|u|o(ga?)?)" (dim :cycle)]
  (cycle-nth (:grain %2) -1)

  "next <cycle>"
  [#"(?i)sljede(c|ć)(i|a|u|e(ga?)?)" (dim :cycle)]
  (cycle-nth (:grain %2) 1)

  ;  "the <cycle> after <time>"
  ;  [#"(?i)the" (dim :cycle) #"(?i)after" (dim :time)]
  ;  (cycle-nth-after (:grain %2) 1 %4)

  "<cycle> after <time>"
  [(dim :cycle) #"(?i)nakon" (dim :time)]
  (cycle-nth-after (:grain %1) 1 %3)

  ;  "the <cycle> before <time>"
  ;  [#"(?i)the" (dim :cycle) #"(?i)before" (dim :time)]
  ;  (cycle-nth-after (:grain %2) -1 %4)

  "<cycle> before <time>"
  [(dim :cycle) #"(?i)prije" (dim :time)]
  (cycle-nth-after (:grain %1) -1 %3)

  "last n <cycle>"
  [#"(?i)prethodn(ih?|a|e)|pro(s|š)l(a|e|ih?)" (integer 1 9999)
   (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (- (:value %2)))

  "next n <cycle>"
  [#"(?i)(u )?(sljede(c|ć)(ih?|a|eg?))" (integer 1 9999) (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (:value %2))

  "<ordinal> <cycle> of <time>"
  [(dim :ordinal) (dim :cycle) #"(?i)od|u" (dim :time)]
  (cycle-nth-after-not-immediate (:grain %2) (dec (:value %1)) %4)

  ;  "the <ordinal> <cycle> of <time>"
  ;  [#"(?i)the" (dim :ordinal) (dim :cycle) #"(?i)of|in|from" (dim :time)]
  ;  (cycle-nth-after-not-immediate (:grain %3) (dec (:value %2)) %5)

  ;  "the <cycle> of <time>"
  ;  [#"(?i)the" (dim :cycle) #"(?i)of" (dim :time)]
  ;  (cycle-nth-after-not-immediate (:grain %2) 0 %4)

  ; the 2 following rules may need a different helper

  "<ordinal> <cycle> after <time>"
  [(dim :ordinal) (dim :cycle) #"(?i)nakon" (dim :time)]
  (cycle-nth-after-not-immediate (:grain %2) (dec (:value %1)) %4)

  ;  "the <ordinal> <cycle> after <time>"
  ;  [#"(?i)the" (dim :ordinal) (dim :cycle) #"(?i)after" (dim :time)]
  ;  (cycle-nth-after-not-immediate (:grain %3) (dec (:value %2)) %5)


  ; quarters are a little bit different, you can say "3rd quarter" alone

  "<ordinal> quarter"
  [(dim :ordinal) (dim :cycle #(= :quarter (:grain %)))]
  (cycle-nth-after :quarter (dec (:value %1)) (cycle-nth :year 0))

  ;  "the <ordinal> quarter"
  ;  [#"(?i)the" (dim :ordinal) (dim :cycle #(= :quarter (:grain %)))]
  ;  (cycle-nth-after :quarter (dec (:value %2)) (cycle-nth :year 0))

  "<ordinal> quarter <year>"
  [(dim :ordinal) (dim :cycle #(= :quarter (:grain %))) (dim :time)]
  (cycle-nth-after :quarter (dec (:value %1)) %3))
