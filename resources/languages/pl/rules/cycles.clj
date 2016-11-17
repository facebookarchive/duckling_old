; Cycles are like a heart beat, the next starts just when the previous ends.
; Unlike durations, they have an absolute position in the time, it's just that this position is periodic.
; Examples of phrases involving cycles:
; - this week
; - today (= this day)
; - last month
; - last 2 calendar months (last 2 months is interpreted as a duration)
;
; As soon as you put a quantity (2 months), the cycle becomes a duration.


(
  "second (cycle)"
  #"(?i)sekund(y|zie|(e|ę)|om|ami|ach|o|a)?"
  {:dim :cycle
   :grain :second}

  "minute (cycle)"
  #"(?i)minut(y|cie|(e|ę)|om|o|ami|ach|(a|ą))?"
  {:dim :cycle
   :grain :minute}

  "hour (cycle)"
  #"(?i)godzin(y|(e|ę)|ie|om|o|ami|ach|(a|ą))?"
  {:dim :cycle
   :grain :hour}

  "day (cycle)"
  #"(?i)dzie(n|ń|ni(a|ą))|dni(owi|a|ą)?"
  {:dim :cycle
   :grain :day}

  "week (cycle)"
  #"(?i)tydzie(n|ń|)|tygod(ni(owi|u|a|em))|tygodn(iach|iami|iom|ie|i)|tyg\.?"
  {:dim :cycle
   :grain :week}

  "month (cycle)"
  #"(?i)miesi(a|ą)c(owi|em|u|e|om|ami|ach|a)?"
  {:dim :cycle
   :grain :month}

  "quarter (cycle)"
  #"(?i)kwarta(l|ł)(u|owi|em|e|(o|ó)w|om|ach|ami|y)?"
  {:dim :cycle
   :grain :quarter}

  "year (cycle)"
  #"(?i)rok(u|owi|iem)?|lat(ami|ach|a|om)?"
  {:dim :cycle
   :grain :year}

  "this <cycle>"
  [#"(?i)te(mu|n|go|j)|tym|t(a|ą)|nadchodz(a|ą)c(ym|y|ego|emu|(a|ą)|ej)|obecn(ym|y|emu|ego|nym|(a|ą)|ej)" (dim :cycle)]
  (cycle-nth (:grain %2) 0)

  "last <cycle>"
  [#"(?i)ostatni(ego|ch|emu|mi|m|(a|ą)|ej|e)?|(po ?)?przedni(ego|ch|emu|mi|m|e|(a|ą)|ej)?" (dim :cycle)]
  (cycle-nth (:grain %2) -1)

  "next <cycle>"
  [#"(?i)kolejn(ym|y|ego|emu|(a|ą)|ej|e)|nast(e|ę)pn(ym|y|ego|emu|(a|ą)|ej|e)|przysz(l|ł)(ego|emu|ym|(a|ą)|ej|ych|i|ymi|y|e)|za" (dim :cycle)]
  (cycle-nth (:grain %2) 1)

  ;; "the <cycle> after <time>"
  ;; [#"(?i)the" (dim :cycle) #"(?i)after" (dim :time)]
  ;; (cycle-nth-after (:grain %2) 1 %4)

  "<cycle> after <time>"
  [(dim :cycle) #"(?i)po" (dim :time)]
  (cycle-nth-after (:grain %1) 1 %3)

  ;; "the <cycle> before <time>"
  ;; [#"(?i)the" (dim :cycle) #"(?i)before" (dim :time)]
  ;; (cycle-nth-after (:grain %2) -1 %4)

  "<cycle> before <time>"
  [(dim :cycle) #"(?i)przed" (dim :time)]
  (cycle-nth-after (:grain %1) -1 %3)

  "last n <cycle>"
  [#"(?i)ostatni(ego|ch|emu|mi|m|(a|ą)|ej|e)?|(po ?)?przedni(ego|ch|emu|mi|m|e|(a|ą)|ej)?" (integer 1 9999) (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (- (:value %2)))

  "next n <cycle>"
  [#"(?i)kolejn(ym|y|ego|emu|(a|ą)|ej|e)|nast(e|ę)pn(ym|y|ego|emu|(a|ą)|ej|e)" (integer 1 9999) (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (:value %2))

 "<ordinal> <cycle> of <time>"
 [(dim :ordinal) (dim :cycle) #"(?i)w(e)?|z(e)?" (dim :time)]
 (cycle-nth-after-not-immediate (:grain %2) (dec (:value %1)) %4)

 "<ordinal> <cycle> <time>"
[(dim :ordinal) (dim :cycle) (dim :time)]
 (cycle-nth-after-not-immediate (:grain %2) (dec (:value %1)) %3)

  ;; "the <ordinal> <cycle> of <time>"
  ;; [#"(?i)the" (dim :ordinal) (dim :cycle) #"(?i)of|in|from" (dim :time)]
  ;; (cycle-nth-after-not-immediate (:grain %3) (dec (:value %2)) %5)

  "the <cycle> of <time>"
  [#"(?i)the" (dim :cycle) #"(?i)of" (dim :time)]
  (cycle-nth-after-not-immediate (:grain %2) 0 %4)

  ; the 2 following rules may need a different helper

  "<ordinal> <cycle> after <time>"
  [(dim :ordinal) (dim :cycle) #"(?i)after" (dim :time)]
  (cycle-nth-after-not-immediate (:grain %2) (dec (:value %1)) %4)

  "the <ordinal> <cycle> after <time>"
  [#"(?i)the" (dim :ordinal) (dim :cycle) #"(?i)after" (dim :time)]
  (cycle-nth-after-not-immediate (:grain %3) (dec (:value %2)) %5)


  ; quarters are a little bit different, you can say "3rd quarter" alone

  "<ordinal> quarter"
  [(dim :ordinal) (dim :cycle #(= :quarter (:grain %)))]
  (cycle-nth-after :quarter (dec (:value %1)) (cycle-nth :year 0))

  "<ordinal> quarter <year>"
  [(dim :ordinal) (dim :cycle #(= :quarter (:grain %))) (dim :time)]
  (cycle-nth-after :quarter (dec (:value %1)) %3)
)
