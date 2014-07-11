(
    ;; Temperature 

    ; latent temperature
    "number as temp"
    (dim :number)
    {:dim :temperature
     :latent true
     :val {:temperature (:val %1)}}

    "<latent temp> degrees"
    [(dim :temperature) #"度|°"]
    (dissoc %1 :latent)

    "<temp> Celcius"
    [(dim :temperature) #"摄氏[°度]|°C"]
    (-> %1
        (assoc-in [:val :unit] "C")
        (dissoc :latent))

    "Celcius <temp>"
    [#"摄氏" (dim :temperature) #"度|°"]
    (-> %2
        (assoc-in [:val :unit] "C")
        (dissoc :latent))

    "<temp> Fahrenheit"
    [(dim :temperature) #"华氏[°度]|°F"]
    (-> %1
        (assoc-in [:val :unit] "F")
        (dissoc :latent))

    "Fahrenheit <temp>"
    [#"华氏" (dim :temperature) #"度|°"]
    (-> %2
        (assoc-in [:val :unit] "F")
        (dissoc :latent))

    ; ;; Distance

    ; latent distance
    "number as distance"
    (dim :number)
    {:dim :distance
     :latent true
     :val {:distance (:val %1)}}

    "<latent dist> km"
    [(dim :distance) #"km?|公里"]
    (-> %1
        (dissoc  :latent)
        (assoc-in [:val :distance] (* 1000 (-> %1 :val :distance)))
        (assoc-in [:val :unit] "meters"))

    "<dist> meters"
    [(dim :distance) #"m|米"]
    (-> %1
        (assoc-in [:val :unit] "meters")
        (dissoc :latent))

    "<dist> miles"
    [(dim :distance) #"英里"]
    (-> %1
        (assoc-in [:val :unit] "miles")
        (dissoc :latent))
)