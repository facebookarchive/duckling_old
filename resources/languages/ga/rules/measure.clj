(
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
  [(dim :distance) #"(?i)km|g?ch?ilim[eé]dai?r"]
  (-> %1
      (dissoc  :latent)
      (merge {:unit "kilometre"
              :normalized {:value (* 1000 (-> %1 :value))
                           :unit "metre"}}))

)
