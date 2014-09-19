(
	; Context map
	{}

	; Distance

	"3 kilomètres"
	"3 kilometres"
	"3 km"
	"3km"
	"3k"
	(distance 3 "kilometre" {:value 3000 :unit "metre"})

	"3,0 km"
	(distance 3.0 "kilometre" {:value 3000.0 :unit "metre"})

	"8 miles"
	(distance 8 "mile" {:value 12872 :unit "metre"})

	"9 metres"
	"9m"
	(distance 9 "metre")

	"2cm"
	"2 centimetres"
	(distance 2 "centimetre" {:value 0.02 :unit "metre"})

	; Volume
	
	"250 millilitres"
	"250ml"
	"250 ml"
	(volume  250 "millilitre" {:value 0.25 :unit "litre"})

	"2 litres"
	(volume 2 "litre")

	"3 gallons"
	"3 gal"
	(volume 3 "gallon" {:value 11.355 :unit "litre"})

	"3 hectolitres"
	(volume 300 "litre" {:value 30 :unit "litre"})

	"demi-litre"
	"demi litre"
	(volume 0.5 "litre")

	; Quantity
	"2 tasses de café"
	(quantity 2 "tasse" "café")
	 
	"une tasse"
	(quantity 1 "tasse")
	 
	"un café"
	(quantity 1 nil "café")

	"3 cuillères à soupe de sucre"
	(quantity 3 "cuillère à soupe" "sucre")

)
