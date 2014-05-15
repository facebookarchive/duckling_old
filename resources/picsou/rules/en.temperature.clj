(
;; Temperature 

; latent temperature
"number as temp"
(dim :number)
{:dim :temperature
 :latent true
 :val {:temperature (:val %1)}}

"<latent temp> degrees"
[(dim :temperature) #"(?i)(deg(ree?)?s?\.?)|°"]
(dissoc %1 :latent)

"<temp> Celcius"
[(dim :temperature) #"(?i)c(el[cs]?(ius)?)?\.?"]
(-> %1
    (assoc-in [:val :unit] "C")
    (dissoc :latent))

"<temp> Fahrenheit"
[(dim :temperature) #"(?i)f(ah?rh?eh?n(h?eit)?)?\.?"]
(-> %1
    (assoc-in [:val :unit] "F")
    (dissoc :latent))

;; Distance

; latent distance
"number as distance"
(dim :number)
{:dim :distance
 :latent true
 :val {:distance (:val %1)}}

"<latent dist> km"
[(dim :distance) #"(?i)k(ilo)?m?(eter)?s?"]
(-> %1
    (dissoc  :latent)
    (assoc-in [:val :distance] (* 1000 (-> %1 :val :distance)))
    (assoc-in [:val :unit] "meters"))

"<dist> meters"
[(dim :distance) #"(?i)meters?"]
(-> %1
    (assoc-in [:val :unit] "meters")
    (dissoc :latent))

"<dist> miles"
[(dim :distance) #"(?i)miles?"]
(-> %1
    (assoc-in [:val :unit] "miles")
    (dissoc :latent))

"<dist> m (ambiguous miles or meters)"
[(dim :distance) #"(?i)m"]
(-> %1
    (assoc-in [:val :unit] "m")
    (dissoc :latent))
)