(
  ;Lengths are converted into a value in meter for processing purpose
  "meter metric"
  #"(?i)m[eè]tres?"
  (metric :length 1)

  "miles metric"
  #"(?i)miles?"
  (metric :length 1609.34)

  "km metric"
  #"(?i)kms?|kilom[eè]tres?"
  (metric :length 1000)

  "x length"
  [(integer) (metric? :length)]
  (metric :length (* (:val %1) (:val %2)))

  )
