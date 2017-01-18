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
  "secunde (cycle)"
  #"(?i)secund[aăe]"
  {:dim :cycle
   :grain :second}

  "minute (cycle)"
  #"(?i)minut(e|ul)?"
  {:dim :cycle
   :grain :minute}

  "ore (cycle)"
  #"(?i)or[aăe]"
  {:dim :cycle
   :grain :hour}

  "zile (cycle)"
  #"(?i)zi(le|ua)?"
  {:dim :cycle
   :grain :day}

  "saptamani (cycle)"
  #"(?i)sapt[aă]m[aâ]n(ile|a|ă|i)"
  {:dim :cycle
   :grain :week}

  "luni (cycle)"
  #"(?i)lun(ile|a|ă|i)"
  {:dim :cycle
   :grain :month}
  
  "trimestru (cycle)"
  #"(?i)trimestr(ele|ul|u|e)"
  {:dim :cycle
   :grain :quarter}
  
  "ani (cycle)"
  #"(?i)an(ii|i)?"
  {:dim :cycle
   :grain :year}
  
  "<cycle> acesta"
    [#"(?i)aceasta|acest|asta|[aă]sta" (dim :cycle)]
    (cycle-nth (:grain %2) 0)

  "<cycle> acesta"
  [(dim :cycle) #"(?i)aceasta|acest|asta|[aă]sta|curent[aă]"]
  (cycle-nth (:grain %1) 0)

  "ultima <cycle>"
  [#"(?i)ultim(a|ul)" (dim :cycle)]
  (cycle-nth (:grain %2) -1)

  "<cycle> trecut"
   [(dim :cycle) #"(?i)trecut(a|ă)?"]
   (cycle-nth (:grain %1) -1)

  "urmatoarea <cycle>"
  [#"(?i)(urm[aă]to(area|rul)|viito(are|r))" (dim :cycle)]
  (cycle-nth (:grain %2) 1)

  "<cycle> urmatoare"
    [(dim :cycle) #"(?i)(urm[aă]to(are|r)|viito(are|r))"]
    (cycle-nth (:grain %2) 1)

  "ultimele n <cycle>"
  [#"(?i)ultim(ele|ii|a)" (integer 1 9999) (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (- (:value %2)))
  
  "urmatoarele n <cycle>"
  [#"(?i)urm[aă]to(arele|rii|area)" (integer 1 9999) (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (:value %2))
  
  ; quarters are a little bit different, you can say "3rd quarter" alone
  
  "<ordinal> trimestru"
  [(dim :ordinal) (dim :cycle #(= :quarter (:grain %)))]
  (cycle-nth-after :quarter (dec (:value %1)) (cycle-nth :year 0))

  "<ordinal> trimestru <year>"
  [(dim :ordinal) (dim :cycle #(= :quarter (:grain %))) (dim :time)]
  (cycle-nth-after :quarter (dec (:value %1)) %3)

  "trimestru <number> <year>"
    [(dim :cycle) (dim :number) #(= :quarter (:grain %)) (dim :time)]
    (cycle-nth-after :quarter (dec (:value %1)) %3)
)
