; Durations / Periods

(
  "soicind (unit-of-duration)"
  #"(?i)t?sh?oicind[íi]?"
  {:dim :unit-of-duration
   :grain :second}

  "nóiméad (unit-of-duration)"
  #"(?i)n[óo]im[ée][ai]da?"
  {:dim :unit-of-duration
   :grain :minute}

  "uair (unit-of-duration)"
  #"(?i)([thn]-?)?uair(e|eanta)?"
  {:dim :unit-of-duration
   :grain :hour}

  "lá (unit-of-duration)"
  #"(?i)l(ae(thanta)?|[áa])"
  {:dim :unit-of-duration
   :grain :day}

  "seachtain (unit-of-duration)"
  #"(?i)t?sh?eachtain(e|[íi])?"
  {:dim :unit-of-duration
   :grain :week}

  "mí (unit-of-duration)"
  #"(?i)mh?[íi](sa|nna)?"
  {:dim :unit-of-duration
   :grain :month}

  "bliain (unit-of-duration)"
  #"(?i)m?bh?lia(in|na|nta)"
  {:dim :unit-of-duration
   :grain :year}

  "coicís" ;14 days
  #"(?i)coic[íi]s[íie]?"
  {:dim :duration
   :value (duration :day 14)}

  "leathuair"
  #"(?i)leathuair(e|eanta)?"
  {:dim :duration
   :value (duration :minute 30)}

  "<integer> <unit-of-duration>"
  [(integer 0) (dim :unit-of-duration)]; duration can't be negative...
  {:dim :duration
   :value (duration (:grain %2) (:value %1))}

  ; unit-integer duration tens-integer
  ; *but* aon X amháin = one single X
  ; but either alone can mean one
  "<integer> <unit-of-duration> <integer>"
  [(integer 0) (dim :unit-of-duration) (integer 0)]
  {:dim :duration
   :value (if (and (re-matches #"(?i)([thn]-?)?aon" (:text %1))
                   (re-matches #"(?i)amh[áa]in" (:text %3)))
            (duration (:grain %2) (:value %1))
            (duration (:grain %2) (+ (:value %1) (:value %3))))}
)