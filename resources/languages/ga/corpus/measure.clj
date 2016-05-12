(
  ; Context map
  {}

  ; Distance

  "3 ciliméadair"
  "3 km"
  "3km"
  "3k"
  (distance 3 "kilometre" {:value 3000 :unit "metre"})

  "3.0 km"
  (distance 3.0 "kilometre" {:value 3000.0 :unit "metre"})

  "8 mhíle"
  "8 míle"
  (distance 8 "mile" {:value 12872 :unit "metre"})

  "9m"
  (distance 9 "m")

  "2cm"
  "2 cheintiméadar"
  (distance 2 "centimetre" {:value 0.02 :unit "metre"})

  ; Volume

  "250 millilítir"
  "250 millilitir"
  "250ml"
  "250 ml"
  (volume  250 "millilitre" {:value 0.25 :unit "litre"})

  "2 lítir"
  (volume 2 "litre")

  "5 galúin"
  (volume 5 "gallon" {:value 11.355 :unit "litre"})

)
