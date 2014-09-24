(
    ;; Temperature
      
    ; latent temperature
    "number as temp"
    (dim :number)
    {:dim :temperature
     :latent true
     :val {:temperature (:val %1)}}

    "<latent temp> temp"
    [(dim :temperature) #"(?i)(grados?)|°"]
    (dissoc %1 :latent)

    "<temp> Celsius"
    [(dim :temperature) #"(?i)(cent(i|í)grados?|c(el[cs]?(ius)?)?\.?)"]
    (-> %1
        (assoc-in [:val :unit] "C")
        (dissoc :latent))

    "<temp> Fahrenheit"
    [(dim :temperature) #"(?i)f(ah?reh?n(h?eit)?)?\.?"]
    (-> %1
        (assoc-in [:val :unit] "F")
        (dissoc :latent))

    "<latent temp> temp bajo cero"
    [(dim :temperature) #"(?i)((grados?)|°)?( bajo cero)"]
    (-> %1
        (dissoc :latent)
        (merge {:value (* -1 (-> %1 :value))}))

)