(
  ;measures need metrics
  ;are converted for normalization purpose
  ;International Bureau of Weights and Measures
  ;http://www.bipm.org/en/si/si_brochure/chapter2/2-1/metre.html

  "meter metric"
  #"(?i)mh?[eé]adai?r"
  (metric :distance-unit "metre" 1 "metre")

  "miles metric"
  #"(?i)mh?[íi]lt?e"
  (metric :distance-unit "mile" 1609.34 "metre")

  "km metric"
  #"(?i)km|g?ch?ilim[eé]dai?r"
  (metric :distance-unit "kilometre" 1000 "metre")
)
