(
;; Temperature
  
; latent temperature
"number as temp"
(dim :number)
{:dim :temperature
 :latent true
 :val {:temperature (:val %1)}}

"<latent temp> degrees"
[(dim :temperature) #"(?i)(deg(r[éeè])?s?\.?)|°"]
(dissoc %1 :latent)

"<temp> Celsius"
[(dim :temperature) #"(?i)c(el[cs]?(ius)?)?\.?"]
(-> %1
    (assoc-in [:val :unit] "C")
    (dissoc :latent))

"<temp> Fahrenheit"
[(dim :temperature) #"(?i)f(ah?reh?n(h?eit)?)?\.?"]
(-> %1
    (assoc-in [:val :unit] "F")
    (dissoc :latent))

;; Distance

;; Distance

; latent distance
"number as distance"
(dim :number)
{:dim :distance
 :latent true
 :val {:distance (:val %1)}}

"<latent dist> km"
[(dim :distance) #"(?i)k(ilo)?m?([eéè]tre)?s?"]
(-> %1
    (dissoc  :latent)
    (assoc-in [:val :distance] (* 1000 (-> %1 :val :distance)))
    (assoc-in [:val :unit] "meters"))

"<dist> meters"
[(dim :distance) #"(?i)m([eéè]tres?)?"]
(-> %1
    (assoc-in [:val :unit] "meters")
    (dissoc :latent))

"<dist> miles"
[(dim :distance) #"(?i)miles?"]
(-> %1
    (assoc-in [:val :unit] "miles")
    (dissoc :latent))

)