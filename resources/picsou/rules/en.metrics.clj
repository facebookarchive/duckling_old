(
  ;measures need metrics
  ;are converted for normalization purpose
  ;International Bureau of Weights and Measures
  ;http://www.bipm.org/en/si/si_brochure/chapter2/2-1/metre.html
  
  "meter metric"
  #"(?i)meters?"
  (metric :distance "metre" 1 "metre")

  "miles metric"
  #"(?i)miles?"
  (metric :distance "mile" 1609.34 "metre")

  "km metric"
  #"(?i)kms?|kilometers?"
  (metric :distance "kilometre" 1000 "metre")

  ; "x length"
  ; [(integer) (metric? :length)]
  ; (metric :length (* (:val %1) (:val %2)))

  )
