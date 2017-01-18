(
	;; Distance
	;; NEED TO PUSH THE NORMALIZATION IN THE HELPER + CREATE Function to combine distance

 	; "two distance tokens in a row"
 	; [(dim :distance #(not (:latent %))) (dim :distance #(not (:latent %)))] ; sequence of two tokens with a distance dimension
 	; (combine-distance %1 %2)
  	
  ; 	; same thing, with "and" in between like "3 feets and 2 inches"
 	; "two distance tokens separated by and"
 	; [(dim :distance #(not (:latent %))) #"(?i)(si|și)" (dim :distance #(not (:latent %)))] ; sequence of two tokens with a time fn
 	; (combine-distance %1 %3)

	; latent distance
	"number as distance"
	(dim :number)
	{:dim :distance
	 :latent true
	 :value (:value %1)}

	"<latent dist> km"
	[(dim :distance) #"(?i)(kilometr[iu]|km)"]
	(-> %1
	    (dissoc  :latent)
	   	(merge {:unit "kilometru"
	    		:normalized {:value (* 1000 (-> %1 :value))
	    					 :unit "metru"}}))

	"<latent dist> picioare"
	[(dim :distance) #"(?i)(picio(are|r))"]
	(-> %1
	    (dissoc  :latent)
	   	(merge {:unit "foot"
	    		:normalized {:value (* 0.3048 (-> %1 :value))
	    					 :unit "metru"}}))

	"<latent dist> inch"
	[(dim :distance) #"(?i)(inch|inci|inchi)"]
	(-> %1
	    (dissoc  :latent)
	   	(merge {:unit "inch"
	    		:normalized {:value (* 0.0254 (-> %1 :value))
	    					 :unit "metru"}}))

	;;temporary hack
	;"<latent dist> feet and <latent dist> inch "
	;[(dim :distance) #"(?i)('|f(oo|ee)?ts?)( and)?" (dim :distance) #"(?i)(''|inch(es)?)"]
	;(-> %1
	;    (dissoc  :latent)
	;   	(merge {:unit "foot"
	;    		:normalized {:value (+ (* 0.3048 (-> %1 :value)) (* 0.0254 (-> %3 :value)))
	;    					 :unit "metru"}}))

	"<latent dist> yarzi"
	[(dim :distance) #"(?i)y(ar)?(zi|d)?"]
	(-> %1
	    (dissoc  :latent)
	   	(merge {:unit "yard"
	    		:normalized {:value (* 0.9144 (-> %1 :value))
	    					 :unit "metru"}}))

	"<dist> meters"
	[(dim :distance) #"(?i)(metr[ui]|m)"]
	(-> %1
	    (dissoc  :latent)
	   	(merge {:unit "metru"}))

	"<dist> centimeters"
	[(dim :distance) #"(?i)(centimetr[iu]|cm)"]
	(-> %1
	    (dissoc  :latent)
	   	(merge {:unit "centimetru"
	    		:normalized {:value (* 0.01 (-> %1 :value))
	    					 :unit "metru"}}))

	"<dist> miles"
	[(dim :distance) #"(?i)mil[eaă]"]
	(-> %1
	    (dissoc :latent)
	   	(merge {:unit "mile"
	    		:normalized {:value (* 1609 (-> %1 :value))
	    					 :unit "metru"}}))

	;"<dist> m (ambiguous miles or meters)"
	;[(dim :distance) #"(?i)m"]
	;(-> %1
	;    (dissoc :latent)
	;   	(merge {:unit "m"}))

	;; volume

	; latent volume
	"number as volume"
	(dim :number)
	{:dim :volume
	 :latent true
	 :value (:value %1)}

	 "<latent vol> ml"
	[(dim :volume) #"(?i)m(ililit(ri|ri)|l)"]
	(-> %1
	    (dissoc  :latent)
	    (merge {:unit "millilitre"
	    		:normalized {:value (* 0.001 (-> %1 :value))
	    					 :unit "litre"}}))

	 "<vol> hectoliters"
	[(dim :volume) #"(?i)hectolit(ru|ri)"]
	(-> %1
	    (dissoc  :latent)
	   	(merge {:unit "hectolitre"
	    		:normalized {:value (* 100 (-> %1 :value))
	    					 :unit "litre"}}))

	"<vol> liters"
	[(dim :volume) #"(?i)l(it(ru|ri))?"]
	(-> %1
	    (dissoc  :latent)
	   	(merge {:unit "litre"}))
	
	"half liter"
	[#"(?i)jum[aă]tate de lit(ru|ri)"]
	(-> %1
	    (dissoc  :latent)
	   	(merge {:unit "litre"
	   			:value 0.5}))

	"<latent vol> galon"
	[(dim :volume) #"(?i)gal(oane|on)?"]
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
	;"a <unit>"
	;[#"(?i)o?" (dim :leven-unit)]
	;{:dim :quantity
	; :value 1
	; :unit (:value %2)
	; :form :no-product}

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

	;; an apple
	;"a <product>"
	;[#"(?i)(o|un)?" (dim :leven-product)]
	;{:dim :quantity
	; :value 1
	; :product (:value %2)}

	 ; apple 
	"<product>"
	[(dim :leven-product)]
	{:dim :quantity
	 :value 1
	 :product (:value %1)}

	; Stubs for corpus
	"pounds"
	#"(livr[aăe])"
	{:dim :leven-unit :value "pound"}

	"carne"
	#"carne"
	{:dim :leven-product :value "carne"}

	"cana"
	#"can[aă]?"
	{:dim :leven-unit :value "cana"}

	"sugar"
	#"zah[aă]r"
	{:dim :leven-product :value "zahar"}

)