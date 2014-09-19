(
    ;; Temperature 

    ; latent temperature
    "number as temp"
    (dim :number)
    {:dim :temperature
     :latent true
     :value (:value %1)}

    "<latent temp> degrees"
    [(dim :temperature) #"(?i)(deg(ree?)?s?\.?)|°"]
    (dissoc %1 :latent)

    "<temp> Celcius"
    [(dim :temperature) #"(?i)c(el[cs]?(ius)?)?\.?"]
    (-> %1
        (dissoc :latent)
        (merge {:unit "C"}))

    "<temp> Fahrenheit"
    [(dim :temperature) #"(?i)f(ah?rh?eh?n(h?eit)?)?\.?"]
    (-> %1
        (dissoc :latent)
        (merge {:unit "F"}))

)