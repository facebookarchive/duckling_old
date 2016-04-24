(
	; Context map
	{}

	; Distance

	"3 kilometers"
	"3 km"
	"3km"
	"3k"
	(distance 3 "kilometre" {:value 3000 :unit "metre"})

	"3.0 km"
	(distance 3.0 "kilometre" {:value 3000.0 :unit "metre"})

	"8 miles"
	(distance 8 "mile" {:value 12872 :unit "metre"})

	"9m"
	(distance 9 "m")

	"2cm"
	"2 centimeters"
	(distance 2 "centimetre" {:value 0.02 :unit "metre"})

	; Volume
	
	"250 milliliters"
	"250ml"
	"250 ml"
	(volume  250 "millilitre" {:value 0.25 :unit "litre"})

	"2 liters"
	(volume 2 "litre")

	"3 gallons"
	"3 gal"
	(volume 3 "gallon" {:value 11.355 :unit "litre"})

	"3 hectoliters"
	(volume 300 "litre" {:value 30 :unit "litre"})

	"half liter"
	(volume 0.5 "litre")

	; Quantity
	"two pounds of meat"
	(quantity 2 "pound" "meat")
	 
	"a pound"
	(quantity 1 "pound")
	 
	"a meat"
	(quantity 1 nil "meat")

	"3 cups of sugar"
	(quantity 3 "cup" "sugar")
)

