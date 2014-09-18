(
    ;; Temperature 

    ; latent temperature
    "number as temp"
    (dim :number)
    {:dim :temperature
     :latent true
     :value {:temperature (:value %1)}}

    "<latent temp> degrees"
    [(dim :temperature) #"(?i)(deg(ree?)?s?\.?)|°"]
    (dissoc %1 :latent)

    "<temp> Celcius"
    [(dim :temperature) #"(?i)c(el[cs]?(ius)?)?\.?"]
    (-> %1
        (assoc-in [:value :unit] "C")
        (dissoc :latent))

    "<temp> Fahrenheit"
    [(dim :temperature) #"(?i)f(ah?rh?eh?n(h?eit)?)?\.?"]
    (-> %1
        (assoc-in [:value :unit] "F")
        (dissoc :latent))

)