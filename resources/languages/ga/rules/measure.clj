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
  [(dim :distance) #"(?i)(c\.?m\.?|g?ch?eintimh?[eé]adai?r)"]
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
  [(dim :distance) #"(?i)(k\.?(m\.?)?|g?ch?ilim[eé]adai?r)"]
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
  [(dim :distance) #"(?i)(''|([nth]-?)?orl(ach|aigh|a[íi]|\.))"]
  (-> %1
      (dissoc  :latent)
      (merge {:unit "inch"
              :normalized {:value (* 0.0254 (-> %1 :value))
                           :unit "metre"}}))

  "<dist> m (ambiguous miles or meters)"
  [(dim :distance) #"(?i)m"]
  (-> %1
      (dissoc :latent)
      (merge {:unit "m"}))

  ;; volume

  ; latent volume
  "number as volume"
  (dim :number)
  {:dim :volume
   :latent true
   :value (:value %1)}

  "<latent vol> ml"
  [(dim :volume) #"(?i)m(l\.?|h?illil[íi]t(ea|i)r)"]
  (-> %1
      (dissoc  :latent)
      (merge {:unit "millilitre"
              :normalized {:value (* 0.001 (-> %1 :value))
                           :unit "litre"}}))

  "<latent vol> kl"
  [(dim :volume) #"(?i)(kl\.?|g?ch?illil[íi]t(ea|i)r)"]
  (-> %1
      (dissoc  :latent)
      (merge {:unit "millilitre"
              :normalized {:value (* 1000 (-> %1 :value))
                           :unit "litre"}}))

  "<vol> heictilítir"
  [(dim :volume) #"(?i)heictil[íi]t(ea|i)r"]
  (-> %1
      (dissoc  :latent)
      (merge {:unit "hectolitre"
              :normalized {:value (* 100 (-> %1 :value))
                           :unit "litre"}}))

  "<vol> lítear"
  [(dim :volume) #"(?i)(l[íi]t(ea|i)r|l\.?)"]
  (-> %1
      (dissoc  :latent)
      (merge {:unit "litre"}))

  "leathlítear"
  [#"(?i)leathl[íi]t(ea|i)r"]
  (-> %1
      (dissoc  :latent)
      (merge {:unit "litre"
              :value 0.5}))

  "<latent vol> galún"
  [(dim :volume) #"(?i)n?gh?al[úu]i?n"]
  (-> %1
      (dissoc  :latent)
      (merge {:unit "gallon"
              :normalized {:value (* 3.785 (-> %1 :value))
                           :unit "litre"}}))

  "leathghalún"
  [#"(?i)leathgh?al[úu]i?n"]
  (-> %1
      (dissoc  :latent)
      (merge {:unit "gallon"
              :value 0.5
              :normalized {:value (* 1.8925 (-> %1 :value))
                           :unit "litre"}}))

)
