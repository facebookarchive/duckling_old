(
  ; Context map
  {}

  ; Distance

  "3 킬로미터"
  "3 킬로"
  "3 키로"
  "3 km"
  "3km"
  (distance 3 "kilometre" {:value 3000 :unit "metre"})

  "3.0 km"
  (distance 3.0 "kilometre" {:value 3000.0 :unit "metre"})

  "8 miles"
  "8 마일"
  "8 마일즈"
  (distance 8 "mile" {:value 12872 :unit "metre"})

  "9m"
  "9미터"
  "9메터"
  "구메터"
  (distance 9 "metre")

  "2cm"
  "2 센치"
  "이센치"
  "2 센티"
  "2 센티미터"
  "2 센치미터"
  (distance 2 "centimetre" {:value 0.02 :unit "metre"})

  ; Volume
  
  "250 밀리리터"
  "250 미리리터"
  "이백오십미리리터"
  "250ml"
  "250 ml"
  (volume  250 "millilitre" {:value 0.25 :unit "litre"})

  "2 리터"
  "이리터"
  (volume 2 "litre")

  "3 갤론"
  "삼 갤론"
  (volume 3 "gallon" {:value 11.355 :unit "litre"})

  "3 헥토리터"
  "삼 헥토리터"
  (volume 300 "litre" {:value 30 :unit "litre"})

  "반 리터"
  (volume 0.5 "litre")

  ; Quantity
  "삼겹살 두근"
  (quantity 2 "근" "삼겹살")
   
  "한근"
  (quantity 1 "근")
   
  "육백그람"
  (quantity 1 "그램")

  "콜라 세컵"
  (quantity 3 "컵" "콜라")
)

