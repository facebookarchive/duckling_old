(
	;; Distance

	; latent distance
	"number as distance"
	(dim :number)
	{:dim :distance
	 :latent true
	 :value (:value %1)}

	"<latent dist> km"
	[(dim :distance) #"(?i)k(il(ó|o))?m?(etro)?s?"]
	(-> %1
	    (dissoc  :latent)
	   	(merge {:unit "kilometre"
	    		:normalized {:value (* 1000 (-> %1 :value))
	    					 :unit "metre"}}))

	"<dist> meters"
	[(dim :distance) #"(?i)m(etros?)?"]
	(-> %1
	    (dissoc  :latent)
	   	(merge {:unit "metre"}))

	"<dist> centimeters"
	[(dim :distance) #"(?i)(cm|cent(í|i)m(etros?))"]
	(-> %1
    	(dissoc :latent)
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

	;; volume

	; latent volume
	"number as volume"
	(dim :number)
	{:dim :volume
	 :latent true
	 :value (:value %1)}

	 "<latent vol> ml"
	[(dim :volume) #"(?i)m(l|ililitros?)"]
	(-> %1
	    (dissoc  :latent)
	    (merge {:unit "millilitre"
	    		:normalized {:value (* 0.001 (-> %1 :value))
	    					 :unit "litre"}}))

	 "<vol> hectoliters"
	[(dim :volume) #"(?i)hectolitros?"]
	(-> %1
	    (dissoc  :latent)
	   	(merge {:unit "hectolitre"
	    		:normalized {:value (* 100 (-> %1 :value))
	    					 :unit "litre"}}))

	"<vol> liters"
	[(dim :volume) #"(?i)l(itros?)?"]
	(-> %1
	    (dissoc  :latent)
	   	(merge {:unit "litre"}))
	
	"half liter"
	[#"(?i)medio litros?"]
	(-> %1
	    (dissoc  :latent)
	   	(merge {:unit "litre"
	   			:value 0.5}))

	"<latent vol> gallon"
	[(dim :volume) #"(?i)gal[oó]ne?s?"]
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
	[(dim :quantity #(= :no-product (:form %))) #"(?i)de" (dim :leven-product)]
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
	[#"(?i)une?" (dim :leven-product)]
	{:dim :quantity
	:value 1
	:product (:value %2)}

	; Stubs for corpus
	"tasse"
	#"tasses?"
	{:dim :leven-unit :value "tasse"}

	"café"
	#"caf[eé]"
	{:dim :leven-product :value "café"}

	"cuillere a soupe"
	#"cuill?[eè]res? à soupe?"
	{:dim :leven-unit :value "cuillère à soupe"}

	"sucre"
	#"sucre"
	{:dim :leven-product :value "sucre"}

)