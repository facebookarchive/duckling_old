(
  ;; Temperature

  ; latent temperature
  "number as temp"
  (dim :number)
  {:dim :temperature
   :latent true
   :value (:value %1)}

  "<latent temp> céim"
  [(dim :temperature) #"(?i)g?ch?[ée]im(e(anna)?)?|°"]
  (-> %1
      (dissoc :latent)
      (merge {:unit "degree"}))

  "<temp> Celsius"
  [(dim :temperature) #"(?i)ceinteagr[áa]d|c(el[cs]?(ius)?)?\.?"]
  (-> %1
      (dissoc :latent)
      (merge {:unit "celsius"}))

  "<temp> Fahrenheit"
  [(dim :temperature) #"(?i)f(ah?reh?n(h?eit)?)?\.?"]
  (-> %1
      (dissoc :latent)
      (merge {:unit "fahrenheit"}))

  "<latent temp> faoi bhun náid"
  [(dim :temperature) #"(?i)faoi bhun (0|n[aá]id)"]
  (-> %1
      (dissoc :latent)
      (merge {:value (* -1 (-> %1 :value))}))
)