(
	; Context map
	{}

	; Distance

	"3 kilometer"
	"3 km"
	"3km"
	"3k"
	(distance 3 "kilometre" {:value 3000 :unit "metre"})

	"3,0 km"
	"3,0km"
	(distance 3.0 "kilometre" {:value 3000.0 :unit "metre"})

	"8 mijl"
	(distance 8 "mile" {:value 12872 :unit "metre"})

	"9m"
	"9 m"
	"9 meter"
	(distance 9 "metre")

	"2cm"
	"2 cm"
	"2 centimeter"
	(distance 2 "centimetre" {:value 0.02 :unit "metre"})

	; Volume
	"250 mililiter"
	"250ml"
	"250 ml"
	(volume  250 "millilitre" {:value 0.25 :unit "litre"})

	"2 liter"
	"2l"
	"2 l"
	(volume 2 "litre")

	"3 gallon"
	(volume 3 "gallon" {:value 11.355 :unit "litre"})

	"3 hectoliter"
	(volume 300 "litre" {:value 30 :unit "litre"})

	"halve liter"
	(volume 0.5 "litre")
)
