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
  "soicind (cycle)"
  #"(?i)t?sh?oicind[íi]?"
  {:dim :cycle
   :grain :second}

  "nóiméad (cycle)"
  #"(?i)n[óo]im[ée][ai]da?"
  {:dim :cycle
   :grain :minute}

  "uair (cycle)"
  #"(?i)([thn]-?)?uair(e|eanta)?"
  {:dim :cycle
   :grain :hour}

  "lá (cycle)"
  #"(?i)l(ae(thanta)?|[áa])"
  {:dim :cycle
   :grain :day}

  "seachtain (cycle)"
  #"(?i)t?sh?eachtain(e|[íi])?"
  {:dim :cycle
   :grain :week}

  "mí (cycle)"
  #"(?i)mh?[íi](sa|nna)"
  {:dim :cycle
   :grain :month}

  "ráithe (cycle)"
  #"(?i)r[áa]ith(e|[íi])"
  {:dim :cycle
   :grain :quarter}

  "bliain (cycle)"
  #"(?i)m?bh?lia(in|na|nta)"
  {:dim :cycle
   :grain :year}

  "an <cycle> seo"
  [#"(?i)an" (dim :cycle) #"(?i)seo"]
  (cycle-nth (:grain %2) 0)

  "<cycle> ó shin"
  [(dim :cycle) #"(?i)[óo] shin"]
  (cycle-nth (:grain %1) -1)

  ;"next <cycle>"
  ;[#"(?i)next" (dim :cycle)]
  ;(cycle-nth (:grain %2) 1)

  "an <cycle> i ndiaidh <time>"
  [#"(?i)the" (dim :cycle) #"(?i)(i ndiaidh|tar [ée]is)" (dim :time)]
  (cycle-nth-after (:grain %2) 1 %4)

  "<cycle> i ndiaidh <time>"
  [(dim :cycle) #"(?i)(i ndiaidh|tar [ée]is)" (dim :time)]
  (cycle-nth-after (:grain %1) 1 %3)

  "(an|na) <cycle> roimh <time>"
  [#"(?i)the" (dim :cycle) #"(?i)roimh" (dim :time)]
  (cycle-nth-after (:grain %2) -1 %4)

  "<cycle> roimh <time>"
  [(dim :cycle) #"(?i)roimhe?" (dim :time)]
  (cycle-nth-after (:grain %1) -1 %3)

  ;"last n <cycle>"
  ;[#"(?i)last|past" (integer 1 9999) (dim :cycle)]
  ;(cycle-n-not-immediate (:grain %3) (- (:value %2)))

  "<cycle> ó inniu"
  [(integer 1 9999) (dim :cycle) #"(?i)[óo](n l[áa] (at[áa] )?)?inniu"]
  (cycle-n-not-immediate (:grain %2) (:value %1))

  "i gceann <cycle>"
    [#"(?i)(i|faoi) g?ch?eann" (integer 1 9999) (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (:value %2))

  "<ordinal> <cycle> de <time>"
  [(dim :ordinal) (dim :cycle) #"(?i)d[e']" (dim :time)]
  (cycle-nth-after-not-immediate (:grain %2) (dec (:value %1)) %4)

  "an <ordinal> <cycle> de <time>"
  [#"(?i)an" (dim :ordinal) (dim :cycle) #"(?i)d[e']" (dim :time)]
  (cycle-nth-after-not-immediate (:grain %3) (dec (:value %2)) %5)

  "an <cycle> de <time>"
  [#"(?i)an" (dim :cycle) #"(?i)d[e']" (dim :time)]
  (cycle-nth-after-not-immediate (:grain %2) 0 %4)

  ; the 2 following rules may need a different helper

  "<ordinal> <cycle> i ndiaidh <time>"
  [(dim :ordinal) (dim :cycle) #"(?i)(i ndiaidh|tar [ée]is)" (dim :time)]
  (cycle-nth-after-not-immediate (:grain %2) (dec (:value %1)) %4)

  "an <ordinal> <cycle> i ndiaidh <time>"
  [#"(?i)an" (dim :ordinal) (dim :cycle) #"(?i)(i ndiaidh|tar [ée]is)" (dim :time)]
  (cycle-nth-after-not-immediate (:grain %3) (dec (:value %2)) %5)


  ; quarters are a little bit different, you can say "3rd quarter" alone

  "<ordinal> ráithe"
  [(dim :ordinal) (dim :cycle #(= :quarter (:grain %)))]
  (cycle-nth-after :quarter (dec (:value %1)) (cycle-nth :year 0))

  "<ordinal> ráithe <year>"
  [(dim :ordinal) (dim :cycle #(= :quarter (:grain %))) (dim :time)]
  (cycle-nth-after :quarter (dec (:value %1)) %3)
)
