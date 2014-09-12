; See comments in en.cycles.clj

(
  "seconde (cycle)"
  #"(?i)secondes?"
  {:dim :cycle
   :grain :second}

  "minute (cycle)"
  #"(?i)minutes?"
  {:dim :cycle
   :grain :minute}

  "heure (cycle)"
  #"(?i)heures?"
  {:dim :cycle
   :grain :hour}

  "jour (cycle)"
  #"(?i)jour(n[ée]e?)?s?"
  {:dim :cycle
   :grain :day}

  "semaine (cycle)"
  #"(?i)semaines?"
  {:dim :cycle
   :grain :week}

  "mois (cycle)"
  #"(?i)mois"
  {:dim :cycle
   :grain :month}
  
  "année (cycle)"
  #"(?i)an(n[ée]e?)?s?"
  {:dim :cycle
   :grain :year}
  
  "ce|dans le <cycle>"
  [#"(?i)(cet?t?e?s?)|(dans l[ae']? ?)" (dim :cycle)]
  (cycle-nth (:grain %2) 0)

  "le <cycle> dernier"
  [#"(?i)l[ae']? ?" (dim :cycle) #"(?i)derni[èe]re?|pass[ée]e?"]
  (cycle-nth (:grain %2) -1)

  "le <cycle> prochain"
  [#"(?i)l[ae']? ?" (dim :cycle) #"(?i)prochaine?"]
  (cycle-nth (:grain %2) 1)
  
  "le <cycle> après|suivant <time>"
  [#"(?i)l[ea']? ?" (dim :cycle) #"(?i)suivante?|apr[eèé]s" (dim :time)]
  (cycle-nth-after (:grain %2) 1 %4)
  
  "le <cycle> avant|précédent <time>"
  [#"(?i)l[ea']? ?" (dim :cycle) #"(?i)avant|pr[ée]c[ée]dent" (dim :time)]
  (cycle-nth-after (:grain %2) -1 %4)
  
  "n derniers <cycle>"
  [(integer 2 9999) #"(?i)derni.re?s?" (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (- (:val %1)))
  
  "n prochains <cycle>"
  [(integer 2 9999) #"(?i)prochaine?s?" (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (:val %1))

  "n <cycle> passes|precedents"
  [(integer 2 9999) (dim :cycle) #"(?i)pass[eèé][eèé]?s?"]
  (cycle-n-not-immediate (:grain %2) (- (:val %1)))
  
  "n <cycle> suivants"
  [(integer 2 9999) (dim :cycle) #"(?i)suivante?s?" ]
  (cycle-n-not-immediate (:grain %2) (:val %1))
)
