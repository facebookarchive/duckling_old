(
	; Context map
	{}

	; Distance

	"3 kilometra"
	"3 km"
	"3km"
	"3k"
	(distance 3 "kilometre" {:value 3000 :unit "metre"})

	"3,0 km"
	(distance 3.0 "kilometre" {:value 3000.0 :unit "metre"})

	"8 milja"
	(distance 8 "mile" {:value 12872 :unit "metre"})

	"9m"
	(distance 9 "m")

	"2cm"
	"2 centimetra"
	(distance 2 "centimetre" {:value 0.02 :unit "metre"})

	; Volume

	"250 mililitara"
	"250ml"
	"250 ml"
	(volume  250 "millilitre" {:value 0.25 :unit "litre"})

	"2 litre"
	(volume 2 "litre")

	"3 galona"
	"3 gal"
	(volume 3 "gallon" {:value 11.355 :unit "litre"})

	"3 hektolitra"
	(volume 300 "litre" {:value 30 :unit "litre"})

	"pola litre"
	(volume 0.5 "litre")

	; Quantity
	"dvije kile mesa"
	"dva kilograma mesa"
	(quantity 2 "kilogram" "meso")

	"kila"
  "kilogram"
  "kg"
	(quantity 1 "kilogram")

	"meso"
	(quantity 1 nil "meso")

)

