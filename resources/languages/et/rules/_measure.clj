(
	;; Distance
	;; NEED TO PUSH THE NORMALIZATION IN THE HELPER + CREATE Function to combine distance

 	; "two distance tokens in a row"
 	; [(dim :distance #(not (:latent %))) (dim :distance #(not (:latent %)))] ; sequence of two tokens with a distance dimension
 	; (combine-distance %1 %2)
  	
  ; 	; same thing, with "and" in between like "3 feets and 2 inches"
 	; "two distance tokens separated by and"
 	; [(dim :distance #(not (:latent %))) #"(?i)and" (dim :distance #(not (:latent %)))] ; sequence of two tokens with a time fn
 	; (combine-distance %1 %3)

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

	"<latent dist> feet"
	[(dim :distance) #"(?i)(′|f(oo|ee)?ts?)"]
	(-> %1
	    (dissoc  :latent)
	   	(merge {:unit "foot"
	    		:normalized {:value (* 0.3048 (-> %1 :value))
	    					 :unit "metre"}}))

	"<latent dist> inch"
	[(dim :distance) #"(?i)(′′|inch(es)?)"]
	(-> %1
	    (dissoc  :latent)
	   	(merge {:unit "inch"
	    		:normalized {:value (* 0.0254 (-> %1 :value))
	    					 :unit "metre"}}))

	"<latent dist> yard"
	[(dim :distance) #"(?i)y(ar)?ds?"]
	(-> %1
	    (dissoc  :latent)
	   	(merge {:unit "yard"
	    		:normalized {:value (* 0.9144 (-> %1 :value))
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
 
    ; qantity token inherits metadata from product
  
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

	 ; apple 
	"<product>"
	[(dim :leven-product)]
	{:dim :quantity
	 :value 1
	 :product (:value %1)}

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