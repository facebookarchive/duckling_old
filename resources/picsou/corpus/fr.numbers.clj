(; Context map
  ; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:now (t/date-time 2013 2 12 4 30)}

  "1"
  "un"
  "une"
  (number 1)

  "onze"
  (number 11)

  "dix sept"
  "dix-sept"
  (number 17)

  "vingt et un"
  "vingt-et-un"
  (number 21)

  "vingt trois"
  "vingt-trois"
  (number 23)

  "soixante dix"
  (number 70)

  "soixante dix huit"
  (number 78)

  "soixante treize"
  (number 73)

  "quatre vingt"
  (number 80)

  "quatre vingt un"
  (number 81)

  "quatre vingt dix"
  (number 90)

  "quatre vingt onze"
  (number 91)

  "quatre vingt dix neuf"
  (number 99)

  "33"
  "trente trois"
  "trente-trois"
  "trente 3"
  (number 33)

  "100.000"
  "100000"
  "100K"
  "100k"
  (number 100000)
  
  "3M"
  "3000K"
  "3000000"
  "3.000.000"
  (number 3000000)
  
  "1.200.000"
  "1200000"
  "1,2M"
  "1200K"
  ",0012G"
  (number 1200000)

  "- 1.200.000"
  "-1200000"
  "moins 1200000"
  "-1,2M"
  "-1200K"
  "-,0012G"
  (number -1200000)

  "1er"
  "1ere"
  (number 1)

  )
