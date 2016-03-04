(
	; Context map
	{}

	; Distance

	"3 kilómetros"
	"3 kilometros"
	"3 km"
	"3km"
	"3k"
	(distance 3 "kilometre" {:value 3000 :unit "metre"})

	"3,0 km"
	(distance 3.0 "kilometre" {:value 3000.0 :unit "metre"})

	"8 milhas"
	(distance 8 "mile" {:value 12872 :unit "metre"})

	"9m"
	"9 metros"
	(distance 9 "metre")

	"2cm"
	"2 centímetros"
	(distance 2 "centimetre" {:value 0.02 :unit "metre"})

	; Volume
	"250 mililitros"
	"250ml"
	"250 ml"
	(volume  250 "millilitre" {:value 0.25 :unit "litre"})

	"2 litros"
	(volume 2 "litre")

	"1 galão"
	(volume 1 "gallon" {:value 3.785 :unit "litre"})

	"3 galões"
	(volume 3 "gallon" {:value 11.355 :unit "litre"})

	"3 hectolitros"
	(volume 300 "litre" {:value 30 :unit "litre"})

	"meio litro"
	(volume 0.5 "litre")

)

