(
    ;; Temperature 

    ; latent temperature
    "number as temp"
    (dim :number)
    {:dim :temperature
     :latent true
     :value (:value %1)}

    "<latent temp> degrees"
    [(dim :temperature) #"도|°"]
    (-> %1 (dissoc :latent)
           (merge {:unit "degree"}))

    "섭씨 <temp>"
    [#"(섭씨)" (dim :temperature)]
    (-> %2 (dissoc :latent)
           (merge {:unit "celsius"}))

    "<temp> °C"
    [(dim :temperature) #"(?i)c"]
    (-> %1 (dissoc :latent)
           (merge {:unit "celsius"}))

    "화씨 <temp>"
    [#"(화씨)" (dim :temperature)]
    (-> %2 (dissoc :latent)
           (merge {:unit "fahrenheit"}))

    "<temp> °F"
    [(dim :temperature) #"(?i)f"]
    (-> %1 (dissoc :latent)
           (merge {:unit "fahrenheit"}))

)
