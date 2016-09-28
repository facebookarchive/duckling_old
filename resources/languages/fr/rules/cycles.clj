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

  "le <cycle> prochain|suivant|d'après"
  [#"(?i)l[ae']? ?|une? ?" (dim :cycle) #"(?i)prochaine?|suivante?|qui suit|(d'? ?)?apr[eèé]s"]
  (cycle-nth (:grain %2) 1)

  "<cycle> dernier"
  [(dim :cycle) #"(?i)derni[èe]re?|pass[ée]e?|pr[eé]c[eé]dente?|(d')? ?avant"]
  (cycle-nth (:grain %1) -1)

  "<cycle> prochain|suivant|d'après"
  [(dim :cycle) #"(?i)prochaine?|suivante?|qui suit|(d')? ?apr[eèé]s"]
  (cycle-nth (:grain %1) 1)

  "n <cycle> avant"
  [(integer 2 9999) (dim :cycle) #"(?i)(d')? ?avant|plus t[oô]t"]
  (cycle-nth (:grain %2) (- (:value %1)))

  "n <cycle> après"
  [(integer 2 9999) (dim :cycle) #"(?i)(d')? ?apr[eèé]s|qui sui(t|ves?)|plus tard" ]
  (cycle-nth (:grain %2) (:value %1))
  
  "le <cycle> après|suivant <time>"
  [#"(?i)l[ea']? ?" (dim :cycle) #"(?i)suivante?|apr[eèé]s" (dim :time)]
  (cycle-nth-after (:grain %2) 1 %4)

  "le <cycle> avant|précédent <time>"
  [#"(?i)l[ea']? ?" (dim :cycle) #"(?i)avant|pr[ée]c[ée]dent" (dim :time)]
  (cycle-nth-after (:grain %2) -1 %4)
  
  "n derniers <cycle>"
  [(integer 2 9999) #"(?i)derni.re?s?" (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (- (:value %1)))
  
  "n prochains <cycle>"
  [(integer 2 9999) #"(?i)prochaine?s?|suivante?s?|apr[eèé]s" (dim :cycle)]
  (cycle-n-not-immediate (:grain %3) (:value %1))

  "n <cycle> passes|precedents"
  [(integer 2 9999) (dim :cycle) #"(?i)pass[eèé][eèé]?s?|pr[eé]c[eé]dente?s?|(d')? ?avant|plus t[oô]t"]
  (cycle-n-not-immediate (:grain %2) (- (:value %1)))
  
  "n <cycle> suivants"
  [(integer 2 9999) (dim :cycle) #"(?i)prochaine?s?|suivante?s?|apr[eèé]s|qui sui(t|ves?)|plus tard" ]
  (cycle-n-not-immediate (:grain %2) (:value %1))

  "<ordinal> <cycle> de <time>"
  [(dim :ordinal) (dim :cycle) #"(?i)d['eu]|en" (dim :time)]
  (cycle-nth-after-not-immediate (:grain %2) (dec (:value %1)) %4)
  
  "le <ordinal> <cycle> de <time>"
  [#"(?i)l[ea]" (dim :ordinal) (dim :cycle) #"(?i)d['eu]|en" (dim :time)]
  (cycle-nth-after-not-immediate (:grain %3) (dec (:value %2)) %5)

  "le <cycle> de <time>"
  [#"(?i)l[ea]" (dim :cycle) #"(?i)d['eu]|en" (dim :time)]
  (cycle-nth-after-not-immediate (:grain %2) 0 %4)

  "le lendemain du <time>"
  [#"(?i)(le|au)? ?lendemain du" (dim :time)]
  (cycle-nth-after-not-immediate :day 1 %2)

  "le veille du <time>"
  [#"(?i)(la )?veille du" (dim :time)]
  (cycle-nth-after-not-immediate :day -1 %2)
)
