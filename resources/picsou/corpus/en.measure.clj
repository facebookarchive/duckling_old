(
	; Context map
	{}

	; Distance

	"3 kilometers"
	"3 km"
	"3km"
	"3k"
	"3.0 km"
	(distance 3000 "meters")

	"8 miles"
	(distance 8 "miles")

	"9m"
	(distance 9 "m")

	"2cm"
	"2 centimeters"
	(distance 2 "centimeters")

	; Volume
	"250 milliliters"
	"250ml"
	"250 ml"
	(volume  0.250 "litre")

	"2 liters"
	(volume 2 "litre")

	"3 gallons"
	"3 gal"
	(volume 11.355 "litre")

	"3 hectoliters"
	(volume 300 "litre")

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

