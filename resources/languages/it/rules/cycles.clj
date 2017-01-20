; See comments in en.cycles.clj

(
  ; intersect with "di" "della", ... in between like "Sunday of last week"
  "intersect by \"di\", \"della\", \"del\""
  [(dim :time #(not (:latent %))) #"(?i)di|del(la)?" (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)

  "seconde (cycle)"
  #"(?i)second[oi]"
  {:dim :cycle
   :grain :second}

  "minute (cycle)"
  #"(?i)minut[oi]"
  {:dim :cycle
   :grain :minute}

  "heure (cycle)"
  #"(?i)or[ae]"
  {:dim :cycle
   :grain :hour}

  "jour (cycle)"
  #"(?i)giorn[oi]"
  {:dim :cycle
   :grain :day}

  "semaine (cycle)"
  #"(?i)settiman[ae]"
  {:dim :cycle
   :grain :week}

  "mois (cycle)"
  #"(?i)mes[ei]"
  {:dim :cycle
   :grain :month}

  "année (cycle)"
  #"(?i)ann?[oi]"
  {:dim :cycle
   :grain :year}

  "trimestre (cycle)"
  #"(?i)trimestr[ei]"
  {:dim :cycle
  :grain :quarter}

  "this <cycle>"
  [#"(?i)(in )?quest['oa]|per (il|l['a])" (dim :cycle)]
  (cycle-nth (:grain %2) 0)

  "the <cycle> last"
  [#"(?i)l'|il|la|[nd]el(la)?" (dim :cycle) #"(?i)(scors|passat)[oa]"]
  (cycle-nth (:grain %2) -1)

  "the last <cycle>"
  [#"(?i)(([nd]el)?l' ?ultim|(il|la) passat|([nd]el)?l[ao] scors)[oa]" (dim :cycle)]
  (cycle-nth (:grain %2) -1)

  "the <cycle> next"
  [#"(?i)l'|il|la|[nd]el(la)?" (dim :cycle) #"(?i)prossim[oa]"]
  (cycle-nth (:grain %2) 1)

  "<cycle> next"
  [(dim :cycle) #"(?i)prossim[oa]"]
  (cycle-nth (:grain %1) 1)

  "next <cycle> "
  [#"(?i)((il|la|[nd]el(la)?) )?prossim[oa]" (dim :cycle)]
  (cycle-nth (:grain %2) 1)

  "il <cycle> dopo <time>"
  [#"(?i)l[a']|il|[nd]el" (dim :cycle) #"(?i)dopo" (dim :time)]
  (cycle-nth-after (:grain %2) 1 %4)

  "i|le n <cycle> passati|passate"
  [#"(?i)([nd]e)?i|([nd]el)?le" (integer 2 9999) (dim :cycle) #"(?i)(scors|passat)[ie]"]
  (cycle-n-not-immediate (:grain %3) (- (:value %2)))

  "gli ultimi <n> <cycle>"
  [#"(?i)((([nd]el)?le|([nd]e)?gli) )?(scors|ultim)[ei]" (integer 2 9999) (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (- (:value %2)))

  "gli <n> ultimi <cycle>"
  [#"(?i)([nd]e)?i|([nd]el)?le" (integer 2 9999) #"(?i)(scors|ultim)[ei]" (dim :cycle)]
  (cycle-n-not-immediate (:grain %4) (- (:value %2)))

  ;"l'ultima|la scorsa <cycle>"
  ;[#"(?i)(nel)?(l'ultima|la scorsa)" (dim :cycle)]
  ;(cycle-n-not-immediate (:grain %2) -1)

  "next n <cycle>"
  [#"(?i)((([nd]e)?i|([nd]el)?le) )?prossim[ei]" (integer 2 9999) (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (:value %2))

  "next n <cycle>"
  [#"(?i)([nd]e)?i|([nd]el)?le" (integer 2 9999) #"(?i)prossim[ei]" (dim :cycle)]
  (cycle-n-not-immediate (:grain %4) (:value %2))

  "last <day-of-week> of <time>"
  [#"(?i)(([nd]el)?l')?ultim[oa]" {:form :day-of-week} #"(?i)di|del(l[a'])?" (dim :time)]
  (pred-last-of %2 %4)

  "the nth <time> of <time>"
  [#"(?i)il|l[a']|[nd]el(l[a'])?" (dim :ordinal) (dim :time) #"(?i)di|del(l[a'])?" (dim :time)]
  (pred-nth (intersect %5 %3) (dec (:value %2)))

  "nth <time> of <time>"
  [(dim :ordinal) (dim :time) #"(?i)di|del(l[a'])?" (dim :time)]
  (pred-nth (intersect %4 %2) (dec (:value %1)))

  "<ordinal> <cycle> of <time>"
  [(dim :ordinal) (dim :cycle) #"(?i)di|del(l[a'])?" (dim :time)]
  (cycle-nth-after-not-immediate (:grain %2) (dec (:value %1)) %4)

  "the <ordinal> <cycle> of <time>"
  [#"(?i)il|l[a']|[nd]el(l[a'])?" (dim :ordinal) (dim :cycle) #"(?i)di|del(l[a'])?" (dim :time)]
  (cycle-nth-after-not-immediate (:grain %3) (dec (:value %2)) %5)

  "the <cycle> of <time>"
  [#"(?i)il|la" (dim :cycle) #"(?i)del" (dim :time)]
  (cycle-nth-after-not-immediate (:grain %2) 0 %4)

  ; quarters are a little bit different, you can say "3rd quarter" alone

  "<ordinal> quarter"
  [(dim :ordinal) (dim :cycle #(= :quarter (:grain %)))]
  (cycle-nth-after :quarter (dec (:value %1)) (cycle-nth :year 0))

  "the <ordinal> quarter"
  [#"(?i)il|[nd]el(l')?|l'" (dim :ordinal) (dim :cycle #(= :quarter (:grain %)))]
  (cycle-nth-after :quarter (dec (:value %2)) (cycle-nth :year 0))

  "<ordinal> quarter <year>"
  [(dim :ordinal) (dim :cycle #(= :quarter (:grain %))) (dim :time)]
  (cycle-nth-after :quarter (dec (:value %1)) %3)

  "the <ordinal> quarter"
  [#"(?i)il|[nd]el(l')?|l'" (dim :ordinal) (dim :cycle #(= :quarter (:grain %))) (dim :time)]
  (cycle-nth-after :quarter (dec (:value %2)) %4)))
)
