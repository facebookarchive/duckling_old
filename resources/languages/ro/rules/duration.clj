; Durations / Periods

(
  "secunde (unit-of-duration)"
  #"(?i)sec(und)?[aăe]?"
  {:dim :unit-of-duration
   :grain :second}

  "minute (unit-of-duration)"
  #"(?i)min(uteÂul)?"
  {:dim :unit-of-duration
   :grain :minute}

  "ora (unit-of-duration)"
  #"(?i)h|or(ele|a|ă)"
  {:dim :unit-of-duration
   :grain :hour}

  "ziua (unit-of-duration)"
  #"(?i)zi(lele|le|u[aă])?"
  {:dim :unit-of-duration
   :grain :day}

  "saptamani (unit-of-duration)"
  #"(?i)sapt[aă]m[aâ]n(ile|a|ă|i)"
  {:dim :unit-of-duration
   :grain :week}

  "luni (unit-of-duration)"
  #"(?i)lun(ile|i|a|ă)"
  {:dim :unit-of-duration
   :grain :month}

  "ani (unit-of-duration)"
  #"(?i)an(ul|ii|i)?"
  {:dim :unit-of-duration
   :grain :year}

   "quarter of an hour"
  [#"(?i)(1/4\s?(h|or[aă])|sfert de or[aă])"]
  {:dim :duration
   :value (duration :minute 15)}

   "jumatate de ora"
  [#"(?i)(1/2\s?(h|or[aă])|jum[aă]tate (de)? or[aă])"]
  {:dim :duration
   :value (duration :minute 30)}

   "trei sferturi de ora"
  [#"(?i)(3/4\s?(h|or[aă])|trei sferturi de or[aă])"]
  {:dim :duration
   :value (duration :minute 45)}

  "<integer> <unit-of-duration>"
  [(integer 0) (dim :unit-of-duration)]; duration can't be negative...
  {:dim :duration
   :value (duration (:grain %2) (:value %1))}

  "o <unit-of-duration>"
  [#"(?i)o|un" (dim :unit-of-duration)]
  {:dim :duration
   :value (duration (:grain %2) 1)}

  "in <duration>"
  [#"(?i)[iî]n" (dim :duration)]
  (in-duration (:value %2))

  "pentru <duration>"
  [#"(?i)pentru" (dim :duration)]
  (in-duration (:value %2))

  "dupa <duration>"
  [#"(?i)dup[aă]" (dim :duration)]
  (merge (in-duration (:value %2)) {:direction :after})

  "<duration> de acum"
  [(dim :duration) #"(?i)de (acum|azi)"]
  (in-duration (:value %1))

  "<duration> in urma"
  [(dim :duration) #"(?i)[iî]n urm[aă]"]
  (duration-ago (:value %1))

  "<duration> inainte de <time>"
  [(dim :duration) #"(?i)[iî]nainte de" (dim :time)]
  (duration-before (:value %1) %3)

  "in jur de <duration>" ; about
  [#"(?i)(aproximativ|[iî]n jur de)" (dim :duration)]
  (-> %2
    (merge {:precision "approximate"}))

  "exact <duration>" ; sharp
  [#"(?i)exact" (dim :duration)]
  (-> %2
    (merge {:precision "exact"}))

)
