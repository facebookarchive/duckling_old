(
	;; Temperature
	  
	; latent temperature
	"number as temp"
	(dim :number)
	{:dim :temperature
	 :latent true
	 :value (:value %1)}

	"<latent temp> degrees"
	[(dim :temperature) #"(?i)(deg(r[éeè])?s?\.?)|°"]
	(-> %1
		(dissoc :latent)
		(merge {:unit "degree"}))

	"<temp> Celsius"
	[(dim :temperature) #"(?i)c(el[cs]?(ius)?)?\.?"]
	(-> %1
	    (dissoc :latent)
	    (merge {:unit "celsius"}))

	"<temp> Fahrenheit"
	[(dim :temperature) #"(?i)f(ah?reh?n(h?eit)?)?\.?"]
	(-> %1
	    (dissoc :latent)
	    (merge {:unit "fahrenheit"}))

	"<latent temp> en dessous de zero"
    [(dim :temperature) #"(?i)en dessous de (0|z[ée]ro)"]
    (-> %1
        (dissoc :latent)
        (merge {:value (* -1 (-> %1 :value))}))

)