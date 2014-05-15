; See comments in en.cycles.clj

(
  "segundo (cycle)"
  #"(?i)segundos?"
  {:dim :cycle
   :grain seconds}

  "minutos (cycle)"
  #"(?i)minutos?"
  {:dim :cycle
   :grain minutes}

  "hora (cycle)"
  #"(?i)horas?"
  {:dim :cycle
   :grain hours}

  "dia (cycle)"
  #"(?i)d(í|i)as?"
  {:dim :cycle
   :grain days}

  "semana (cycle)"
  #"(?i)semanas?"
  {:dim :cycle
   :grain weeks}

  "mes (cycle)"
  #"(?i)mes(es)?"
  {:dim :cycle
   :grain months}
  
  "año (cycle)"
  #"(?i)a(n|ñ)os?"
  {:dim :cycle
   :grain years}
  
  "este|en un <cycle>"
  [#"(?i)(est(e|a|os)|en (el|los|la|las) ?)" (dim :cycle)]
  (this-cycle (:grain %2) 0)

  "dentro de <integer> <cycle>"
  [#"(?i)dentro de" (integer) (dim :cycle)]
  (this-cycle (:grain %3) 1 (:val %2))

  "la <cycle> pasado"
  [#"(?i)(el|los|la|las) ?" (dim :cycle) #"(?i)pasad(a|o)s?"]
  (this-cycle (:grain %2) -1)

  "la pasado <cycle>"
  [#"(?i)(el|los|la|las) ?" #"(?i)pasad(a|o)s?" (dim :cycle)]
  (this-cycle (:grain %3) -1)

  "el <cycle> (proximo|que viene)"
  [#"(?i)(el|los|la|las) ?" (dim :cycle) #"(?i)(pr(ó|o)xim(o|a)s?|que vienen?|siguientes?)"]
  (this-cycle (:grain %2) 1)
  
  "el proximo <cycle> "
  [#"(?i)(el|los|la|las) ?" #"(?i)pr(ó|o)xim(o|a)s?" (dim :cycle)]
  (this-cycle (:grain %3) 1)

  "pasados n <cycle>"
  [#"(?i)pasad(a|o)s?" (integer 2 9999) (dim :cycle)]
  (this-cycle (:grain %3) (- (:val %2)) (:val %2))

  "n pasados <cycle>"
  [(integer 2 9999) #"(?i)pasad(a|o)s?" (dim :cycle)]
  (this-cycle (:grain %3) (- (:val %1)) (:val %1))
  
  "proximas n <cycle>"
  [#"(?i)pr(ó|o)xim(o|a)s?" (integer 2 9999) (dim :cycle)]
  (this-cycle (:grain %3) 1 (:val %2))

  "n proximas <cycle>"
  [(integer 2 9999) #"(?i)pr(ó|o)xim(o|a)s?" (dim :cycle)]
  (this-cycle (:grain %3) 1 (:val %1))

  "n <cycle> (proximo|que viene)"
  [(integer 2 9999) (dim :cycle) #"(?i)(pr(ó|o)xim(o|a)s?|que vienen?|siguientes?)"]
  (this-cycle (:grain %2) 1 (:val %1))
)
