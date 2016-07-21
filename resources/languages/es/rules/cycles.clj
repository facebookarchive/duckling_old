; See comments in en.cycles.clj

(
  "segundo (cycle)"
  #"(?i)segundos?"
  {:dim :cycle
   :grain :second}

  "minutos (cycle)"
  #"(?i)minutos?"
  {:dim :cycle
   :grain :minute}

  "hora (cycle)"
  #"(?i)horas?"
  {:dim :cycle
   :grain :hour}

  "dia (cycle)"
  #"(?i)d(í|i)as?"
  {:dim :cycle
   :grain :day}

  "semana (cycle)"
  #"(?i)semanas?"
  {:dim :cycle
   :grain :week}

  "mes (cycle)"
  #"(?i)mes(es)?"
  {:dim :cycle
   :grain :month}

  "trimestre (cycle)"
  #"(?i)trimestres?"
  {:dim :cycle
   :grain :quarter}
  
  "año (cycle)"
  #"(?i)a(n|ñ)os?"
  {:dim :cycle
   :grain :year}
  
  "este|en un <cycle>"
  [#"(?i)(est(e|a|os)|en (el|los|la|las) ?)" (dim :cycle)]
  (cycle-nth (:grain %2) 0)

  ; "dentro de <integer> <cycle>"
  ; [#"(?i)dentro de" (integer) (dim :cycle)]
  ; (cycle-nth  (:grain %3) 1 (:value %2))

  "la <cycle> pasado"
  [#"(?i)(el|los|la|las) ?" (dim :cycle) #"(?i)pasad(a|o)s?|[u|ú]ltim[a|o]s?"]
  (cycle-nth (:grain %2) -1)

  "la pasado <cycle>"
  [#"(?i)(el|los|la|las) ?" #"(?i)pasad(a|o)s?|[u|ú]ltim[a|o]s?" (dim :cycle)]
  (cycle-nth  (:grain %3) -1)

  "el <cycle> (proximo|que viene)"
  [#"(?i)(el|los|la|las) ?" (dim :cycle) #"(?i)(pr(ó|o)xim(o|a)s?|que vienen?|siguientes?)"]
  (cycle-nth  (:grain %2) 1)
  
  "el proximo <cycle> "
  [#"(?i)(el|los|la|las) ?" #"(?i)pr(ó|o)xim(o|a)s?|siguientes?" (dim :cycle)]
  (cycle-nth  (:grain %3) 1)

  "el <cycle> proximo|que viene <time>"
  [#"(?i)(el|los|la|las)" (dim :cycle) #"(?i)(pr(ó|o)xim(o|a)s?|que vienen?|siguientes?)" (dim :time)]
  (cycle-nth-after (:grain %2) 1 %4)
  
  "el <cycle> antes <time>"
  [#"(?i)l[ea']? ?" (dim :cycle) #"(?i)antes de" (dim :time)]
  (cycle-nth-after (:grain %2) -1 %4)

  "pasados n <cycle>"
  [#"(?i)pasad(a|o)s?" (integer 2 9999) (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (- (:value %2)))

  "n pasados <cycle>"
  [(integer 2 9999) #"(?i)pasad(a|o)s?" (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (- (:value %1)))
  
  "proximas n <cycle>"
  [#"(?i)pr(ó|o)xim(o|a)s?" (integer 2 9999) (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (:value %2))

  "n proximas <cycle>"
  [(integer 2 9999) #"(?i)pr(ó|o)xim(o|a)s?" (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (:value %1))

  "n <cycle> (proximo|que viene)"
  [(integer 2 9999) (dim :cycle) #"(?i)(pr(ó|o)xim(o|a)s?|que vienen?|siguientes?)"]
  (cycle-n-not-immediate  (:grain %2) (:value %1))
  
  ; quarters are a little bit different, you can say "3rd quarter" alone
  
  "<ordinal> quarter"
  [(dim :ordinal) (dim :cycle #(= :quarter (:grain %)))]
  (cycle-nth-after :quarter (dec (:value %1)) (cycle-nth :year 0))

  "<ordinal> quarter <year>"
  [(dim :ordinal) (dim :cycle #(= :quarter (:grain %))) #"(?i)del? ?" (dim :time)]
  (cycle-nth-after :quarter (dec (:value %1)) %3)
  
)
