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
  #"(?i)l([áa]|ae(thanta)?)"
  {:dim :unit-of-duration
   :grain :day}

  "seachtain (unit-of-duration)"
  #"(?i)t?sh?eachtain(e|[íi])?"
  {:dim :unit-of-duration
   :grain :week}

  "mí (unit-of-duration)"
  #"(?i)mh?[íi](sa|nna)"
  {:dim :unit-of-duration
   :grain :month}

  "bliain (unit-of-duration)"
  #"(?i)m?bh?lia(in|na|nta)"
  {:dim :unit-of-duration
   :grain :year}

)