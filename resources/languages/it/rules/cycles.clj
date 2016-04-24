; See comments in en.cycles.clj

(
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
  
  "this <cycle>"
  [#"(?i)(in )?quest['oa]" (dim :cycle)]
  (cycle-nth (:grain %2) 0)

  "the <cycle> last"
  [#"(?i)l'|il|la" (dim :cycle) #"(?i)(ultim|scors)[oa]"]
  (cycle-nth (:grain %2) -1)

  "the <cycle> next"
  [#"(?i)l'|il|la" (dim :cycle) #"(?i)prossim[oa]"]
  (cycle-nth (:grain %2) 1)

  "next <cycle> "
  [#"(?i)(l'|il|la) prossim[oa]" (dim :cycle)]
  (cycle-nth (:grain %2) 1)
  
  ; "le <cycle> après|suivant <time>"
  ; [#"(?i)l[ea']? ?" (dim :cycle) #"(?i)suivante?|apr[eèé]s" (dim :time)]
  ; (cycle-nth-after (:grain %2) 1 %4)
  
  ; "le <cycle> avant|précédent <time>"
  ; [#"(?i)l[ea']? ?" (dim :cycle) #"(?i)avant|pr[ée]c[ée]dent" (dim :time)]
  ; (cycle-nth-after (:grain %2) -1 %4)
  
  "n last <cycle>"
  [#"(?i)(scors|ultim)[aoei]" (integer 2 9999)(dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (- (:value %2)))
  
  "n next <cycle>"
  [#"(?i)prossim[oaei]" (integer 2 9999) (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (:value %2))

  ; "n <cycle> passes|precedents"
  ; [(integer 2 9999) (dim :cycle) #"(?i)pass[eèé][eèé]?s?"]
  ; (cycle-n-not-immediate (:grain %2) (- (:value %1)))
  
  ; "n <cycle> suivants"
  ; [(integer 2 9999) (dim :cycle) #"(?i)suivante?s?" ]
  ; (cycle-n-not-immediate (:grain %2) (:value %1))
)
