(
	; Context map
	{}

	; Distance

	"3 kilometri"
	"3 km"
	"3km"
	(distance 3 "kilometru" {:value 3000 :unit "metru"})

	"3,0 km"
	(distance 3.0 "kilometru" {:value 3000.0 :unit "metru"})

	"8 mile"
	(distance 8 "mile" {:value 12872 :unit "metru"})

	"9m"
	"9 m"
	(distance 9 "metru")

	"2cm"
	"2 centimetri"
	(distance 2 "centimetru" {:value 0.02 :unit "metru"})

	; Volume
	
	"250 mililitri"
	"250ml"
	"250 ml"
	(volume  250 "mililitru" {:value 0.25 :unit "litru"})

	"2 litri"
	"2 l"
	"2l"
	(volume 2 "litru")

	"3 galoane"
	"3 gal"
	(volume 3 "galon" {:value 11.355 :unit "litru"})

	"3 hectolitri"
	(volume 300 "litru" {:value 30 :unit "litru"})

	"jumatate de litru"
	"jumătate de litru"
	(volume 0.5 "litru")

	; Quantity
	"doua livre de carne"
	(quantity 2 "livra" "carne")
	 
	"o livră"
	(quantity 1 "livra")
	 
	"o carne"
	(quantity 1 nil "carne")

)

