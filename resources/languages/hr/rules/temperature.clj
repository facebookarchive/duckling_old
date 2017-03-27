(
    ;; Temperature 

    ; latent temperature
    "number as temp"
    (dim :number)
    {:dim :temperature
     :latent true
     :value (:value %1)}

    "<latent temp> stupnjevi"
    [(dim :temperature) #"(?i)deg\.?|stupa?nj((ev)?a)?|°"]
    (-> %1 (dissoc :latent)
           (merge {:unit "degree"}))

    "<temp> Celzij"
    [(dim :temperature) #"(?i)c(el[z]?(ij(a)?)?)?\.?"]
    (-> %1 (dissoc :latent)
           (merge {:unit "celsius"}))

    "<temp> Fahrenheit"
    [(dim :temperature) #"(?i)f(ah?rh?eh?n(h?eit)?)?\.?"]
    (-> %1 (dissoc :latent)
           (merge {:unit "fahrenheit"}))

)