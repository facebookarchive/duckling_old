(
	;; Distance

	; latent distance
	"number as distance"
	(dim :number)
	{:dim :distance
	 :latent true
	 :value {:distance (:value %1)}}

	"<latent dist> km"
	[(dim :distance) #"(?i)k(ilo)?m?(eter)?s?"]
	(-> %1
	    (dissoc  :latent)
	    (assoc-in [:value :distance] (* 1000 (-> %1 :value :distance)))
	    (assoc-in [:value :unit] "meters"))

	"<dist> meters"
	[(dim :distance) #"(?i)meters?"]
	(-> %1
	    (assoc-in [:value :unit] "meters")
	    (dissoc :latent))

	"<dist> centimeters"
	[(dim :distance) #"(?i)cm|centimeters?"]
	(-> %1
	    (assoc-in [:value :unit] "centimeters")
	    (dissoc :latent))

	"<dist> miles"
	[(dim :distance) #"(?i)miles?"]
	(-> %1
	    (assoc-in [:value :unit] "miles")
	    (dissoc :latent))

	"<dist> m (ambiguous miles or meters)"
	[(dim :distance) #"(?i)m"]
	(-> %1
	    (assoc-in [:value :unit] "m")
	    (dissoc :latent))

	;; volume

	; latent volume
	"number as volume"
	(dim :number)
	{:dim :volume
	 :latent true
	 :value {:volume (:value %1)}}

	 "<latent vol> ml"
	[(dim :volume) #"(?i)m(l|illilit(er|re)s?)"]
	(-> %1
	    (dissoc  :latent)
	    (assoc-in [:value :volume] (* 0.001 (-> %1 :value :volume)))
	    (assoc-in [:value :unit] "litre"))  ;International spelling as used by the International Bureau of Weights 

	 "<vol> hectolitres"
	[(dim :volume) #"(?i)hectolit(er|re)s?"]
	(-> %1
	    (dissoc  :latent)
	    (assoc-in [:value :volume] (* 100 (-> %1 :value :volume)))
	    (assoc-in [:value :unit] "litre"))

	"<vol> litre"
	[(dim :volume) #"(?i)l(it(er|re)s?)?"]
	(-> %1
	    (assoc-in [:value :unit] "litre")
	    (dissoc :latent))

	"half liter"
	[#"(?i)half lit(er|re)"]
	{:dim :volume
	 :value {:volume 0.5 :unit "litre"}}

	"<latent vol> gallon"
	[(dim :volume) #"(?i)gal(l?ons?)?"]
	(-> %1
	    (dissoc  :latent)
	    (assoc-in [:value :volume] (* 3.785 (-> %1 :value :volume)))
	    (assoc-in [:value :unit] "litre"))

	;; Quantity
  
	; three teaspoons
	"<number> <units>"
	[(dim :number) (dim :leven-unit)]
	{:dim :quantity
	:value (:value %1)
	:unit (:value %2)
	:form :no-product}

	; a pound
	"a <unit>"
	[#"(?i)an?" (dim :leven-unit)]
	{:dim :quantity
	:value 1
	:unit (:value %2)
	:form :no-product}

	; 3 pounds of flour
	"<quantity> of product"
	[(dim :quantity #(= :no-product (:form %))) #"(?i)of" (dim :leven-product)]
	(-> %1 
	  (assoc :product (:value %3))
	  (dissoc :form))

	; 2 apples
	"<number> product"
	[(dim :number) (dim :leven-product)]
	{:dim :quantity
	:value (:value %1)
	:product (:value %2)}

	; an apple
	"a <product>"
	[#"(?i)an?" (dim :leven-product)]
	{:dim :quantity
	:value 1
	:product (:value %2)}

	; Stubs for corpus
	"pounds"
	#"pounds?"
	{:dim :leven-unit :value "pound"}

	"meat"
	#"meat"
	{:dim :leven-product :value "meat"}

	"cup"
	#"cups?"
	{:dim :leven-unit :value "cup"}

	"sugar"
	#"sugar"
	{:dim :leven-product :value "sugar"}

)