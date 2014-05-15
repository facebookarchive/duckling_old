; See comments in en.cycles.clj

(
  "seconde (cycle)"
  #"(?i)secondes?"
  {:dim :cycle
   :grain seconds}

  "minute (cycle)"
  #"(?i)minutes?"
  {:dim :cycle
   :grain minutes}

  "heure (cycle)"
  #"(?i)heures?"
  {:dim :cycle
   :grain hours}

  "jour (cycle)"
  #"(?i)jour(n[ée]e?)?s?"
  {:dim :cycle
   :grain days}

  "semaine (cycle)"
  #"(?i)semaines?"
  {:dim :cycle
   :grain weeks}

  "mois (cycle)"
  #"(?i)mois"
  {:dim :cycle
   :grain months}
  
  "année (cycle)"
  #"(?i)an(n[ée]e?)?s?"
  {:dim :cycle
   :grain years}
  
  "ce|dans le <cycle>"
  [#"(?i)(cet?t?e?s?)|(dans l[ae']? ?)" (dim :cycle)]
  (this-cycle (:grain %2) 0)

  "le <cycle> dernier"
  [#"(?i)l[ae']? ?" (dim :cycle) #"(?i)derni[èe]re?|pass[ée]e?"]
  (this-cycle (:grain %2) -1)

  "le <cycle> prochain"
  [#"(?i)l[ae']? ?" (dim :cycle) #"(?i)prochaine?"]
  (this-cycle (:grain %2) 1)
  
  "le <cycle> après|suivant <time>"
  [#"(?i)l[ea']? ?" (dim :cycle) #"(?i)suivante?|apr[eèé]s" (dim :time)]
  (cycle-relative %4 (:grain %2) 1)
  
  "le <cycle> avant|précédent <time>"
  [#"(?i)l[ea']? ?" (dim :cycle) #"(?i)avant|pr[ée]c[ée]dent" (dim :time)]
  (cycle-relative %4 (:grain %2) -1)
  
  "n derniers <cycle>"
  [(integer 2 9999) #"(?i)derni.re?s?" (dim :cycle)]
  (this-cycle (:grain %3) (- (:val %1)) (:val %1))
  
  "n prochains <cycle>"
  [(integer 2 9999) #"(?i)prochaine?s?" (dim :cycle)]
  (this-cycle (:grain %3) 1 (:val %1))
)
