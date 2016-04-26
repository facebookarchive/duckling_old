(
  ; latent distance
  "number as distance"
  (dim :number)
  {:dim :distance
   :latent true
   :value (:value %1)}

  "<dist> meters"
  [(dim :distance) #"(?i)mh?[eé]adai?r"]
  (-> %1
      (dissoc  :latent)
      (merge {:unit "metre"}))

  "<dist> centimeters"
  [(dim :distance) #"(?i)cm|g?ch?eintimh?[eé]adai?r"]
  (-> %1
      (dissoc  :latent)
      (merge {:unit "centimetre"
              :normalized {:value (* 0.01 (-> %1 :value))
                           :unit "metre"}}))

  "<dist> miles"
  [(dim :distance) #"(?i)mh?[íi]lt?e"]
  (-> %1
      (dissoc :latent)
      (merge {:unit "mile"
              :normalized {:value (* 1609 (-> %1 :value))
                           :unit "metre"}}))

  "<latent dist> km"
  [(dim :distance) #"(?i)k\.?m\.?|g?ch?ilim[eé]dai?r"]
  (-> %1
      (dissoc  :latent)
      (merge {:unit "kilometre"
              :normalized {:value (* 1000 (-> %1 :value))
                           :unit "metre"}}))

  "<latent dist> troigh"
  [(dim :distance) #"(?i)('|d?th?roi[tg]he?|tr\.?)"]
  (-> %1
      (dissoc  :latent)
      (merge {:unit "foot"
              :normalized {:value (* 0.3048 (-> %1 :value))
                           :unit "metre"}}))

  "<latent dist> orlach"
  [(dim :distance) #"(?i)(''|([nth]-?)?orl(ach|aigh|a[íi]|\.)"]
  (-> %1
      (dissoc  :latent)
      (merge {:unit "inch"
              :normalized {:value (* 0.0254 (-> %1 :value))
                           :unit "metre"}}))
)
