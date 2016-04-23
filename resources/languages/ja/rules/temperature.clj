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
    [(dim :temperature) #"摂氏[°度]|°C"]
    (-> %1 (dissoc :latent)
           (merge {:unit "celsius"}))

    "Celcius <temp>"
    [#"摂氏" (dim :temperature) #"度|°"]
    (-> %2 (dissoc :latent)
           (merge {:unit "celsius"}))

    "<temp> Fahrenheit"
    [(dim :temperature) #"華氏[°度]|°F"]
    (-> %1 (dissoc :latent)
           (merge {:unit "fahrenheit"}))

    "Fahrenheit <temp>"
    [#"華氏" (dim :temperature) #"度|°"]
    (-> %2 (dissoc :latent)
           (merge {:unit "fahrenheit"}))

)
