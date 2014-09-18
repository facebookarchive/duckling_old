(
;; Temperature
  
; latent temperature
"number as temp"
(dim :number)
{:dim :temperature
 :latent true
 :value {:temperature (:value %1)}}

"<latent temp> degrees"
[(dim :temperature) #"(?i)(deg(r[éeè])?s?\.?)|°"]
(dissoc %1 :latent)

"<temp> Celsius"
[(dim :temperature) #"(?i)c(el[cs]?(ius)?)?\.?"]
(-> %1
    (assoc-in [:value :unit] "C")
    (dissoc :latent))

"<temp> Fahrenheit"
[(dim :temperature) #"(?i)f(ah?reh?n(h?eit)?)?\.?"]
(-> %1
    (assoc-in [:value :unit] "F")
    (dissoc :latent))

;; Distance

;; Distance

; latent distance
"number as distance"
(dim :number)
{:dim :distance
 :latent true
 :value {:distance (:value %1)}}

"<latent dist> km"
[(dim :distance) #"(?i)k(ilo)?m?([eéè]tre)?s?"]
(-> %1
    (dissoc  :latent)
    (assoc-in [:value :distance] (* 1000 (-> %1 :value :distance)))
    (assoc-in [:value :unit] "meters"))

"<dist> meters"
[(dim :distance) #"(?i)m([eéè]tres?)?"]
(-> %1
    (assoc-in [:value :unit] "meters")
    (dissoc :latent))

"<dist> miles"
[(dim :distance) #"(?i)miles?"]
(-> %1
    (assoc-in [:value :unit] "miles")
    (dissoc :latent))

)