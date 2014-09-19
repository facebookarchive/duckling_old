(
	;; Distance

	; latent distance
	"number as distance"
	(dim :number)
	{:dim :distance
	 :latent true
	 :value (:value %1)}

	"<latent dist> km"
	[(dim :distance) #"(?i)k(ilo)?m?(eter)?s?"]
	(-> %1
	    (dissoc  :latent)
	   	(merge {:unit "kilometre"
	    		:normalized {:value (* 1000 (-> %1 :value))
	    					 :unit "metre"}}))

	"<dist> meters"
	[(dim :distance) #"(?i)meters?"]
	(-> %1
	    (dissoc  :latent)
	   	(merge {:unit "metre"}))

	"<dist> centimeters"
	[(dim :distance) #"(?i)cm|centimeters?"]
	(-> %1
	    (dissoc  :latent)
	   	(merge {:unit "centimetre"
	    		:normalized {:value (* 0.01 (-> %1 :value))
	    					 :unit "metre"}}))

	"<dist> miles"
	[(dim :distance) #"(?i)miles?"]
	(-> %1
	    (dissoc :latent)
	   	(merge {:unit "mile"
	    		:normalized {:value (* 1609 (-> %1 :value))
	    					 :unit "metre"}}))

	"<dist> m (ambiguous miles or meters)"
	[(dim :distance) #"(?i)m"]
	(-> %1
	    (dissoc :latent)
	   	(merge {:unit "m"}))

	;; volume

	; latent volume
	"number as volume"
	(dim :number)
	{:dim :volume
	 :latent true
	 :value (:value %1)}

	 "<latent vol> ml"
	[(dim :volume) #"(?i)m(l|illilit(er|re)s?)"]
	(-> %1
	    (dissoc  :latent)
	    (merge {:unit "millilitre"
	    		:normalized {:value (* 0.001 (-> %1 :value))
	    					 :unit "litre"}}))

	 "<vol> hectoliters"
	[(dim :volume) #"(?i)hectolit(er|re)s?"]
	(-> %1
	    (dissoc  :latent)
	   	(merge {:unit "hectolitre"
	    		:normalized {:value (* 100 (-> %1 :value))
	    					 :unit "litre"}}))

	"<vol> liters"
	[(dim :volume) #"(?i)l(it(er|re)s?)?"]
	(-> %1
	    (dissoc  :latent)
	   	(merge {:unit "litre"}))
	
	"half liter"
	[#"(?i)half lit(er|re)"]
	(-> %1
	    (dissoc  :latent)
	   	(merge {:unit "litre"
	   			:value 0.5}))

	"<latent vol> gallon"
	[(dim :volume) #"(?i)gal(l?ons?)?"]
	(-> %1
	    (dissoc  :latent)
	    (merge {:unit "gallon"
	    		:normalized {:value (* 3.785 (-> %1 :value))
	    					 :unit "litre"}}))
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