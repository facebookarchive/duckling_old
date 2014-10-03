(
    ;; Temperature 

    ; latent temperature
    "number as temp"
    (dim :number)
    {:dim :temperature
     :latent true
     :value (:value %1)}

    "<latent temp> degrees"
    [(dim :temperature) #"度|°"]
    (-> %1 (dissoc :latent)
           (merge {:unit "degree"}))

    "<temp> Celcius"
    [(dim :temperature) #"[摄攝]氏[°度]|°C"]
    (-> %1 (dissoc :latent)
           (merge {:unit "celsius"}))

    "Celcius <temp>"
    [#"[摄攝]氏" (dim :temperature) #"度|°"]
    (-> %2 (dissoc :latent)
           (merge {:unit "celsius"}))

    "<temp> Fahrenheit"
    [(dim :temperature) #"[华華]氏[°度]|°F"]
    (-> %1 (dissoc :latent)
           (merge {:unit "fahrenheit"}))

    "Fahrenheit <temp>"
    [#"[华華]氏" (dim :temperature) #"度|°"]
    (-> %2 (dissoc :latent)
           (merge {:unit "fahrenheit"}))

)