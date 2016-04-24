(
	; Context map
	{}

	; Distance

	"3 kilomeetrit"
	"3 km"
	"3km"
	"3k"
	(distance 3 "kilometre" {:value 3000 :unit "metre"})

	"3.0 km"
	(distance 3.0 "kilometre" {:value 3000.0 :unit "metre"})

	"8 miili"
	(distance 8 "mile" {:value 12872 :unit "metre"})

	"9m"
	(distance 9 "m")

	"2cm"
	"2 sentimeetrit"
	(distance 2 "centimetre" {:value 0.02 :unit "metre"})

	; Volume
	
	"250 milliliitrit"
	"250ml"
	"250 ml"
	(volume  250 "millilitre" {:value 0.25 :unit "litre"})

	"2 liitrit"
	(volume 2 "litre")

	"3 gallonit"
	"3 gal"
	(volume 3 "gallon" {:value 11.355 :unit "litre"})

	"3 hektoliitrit"
	"3 kuupliitrit"
	(volume 300 "litre" {:value 30 :unit "litre"})

	"pool liitrit"
	(volume 0.5 "litre")

	; Quantity
	"kaks naela liha"
	(quantity 2 "pound" "meat")
	 
	"nael"
	(quantity 1 "pound")
	 
	"liha"
	(quantity 1 nil "meat")

	"3 klaasi suhkrut"
	(quantity 3 "cup" "sugar")
)

