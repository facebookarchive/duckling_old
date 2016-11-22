(
 ;; Distance
 ;; NEED TO PUSH THE NORMALIZATION IN THE HELPER + CREATE Function to combine distance

 ; "two distance tokens in a row"
 ; [(dim :distance #(not (:latent %))) (dim :distance #(not (:latent %)))] ; sequence of two tokens with a distance dimension
 ; (combine-distance %1 %2)
   
 ;   ; same thing, with "and" in between like "3 feets and 2 inches"
 ; "two distance tokens separated by and"
 ; [(dim :distance #(not (:latent %))) #"(?i)and" (dim :distance #(not (:latent %)))] ; sequence of two tokens with a time fn
 ; (combine-distance %1 %3)

 ; latent distance
 "number as distance"
 (dim :number)
 {:dim :distance
  :latent true
  :value (:value %1)}

 "half"
 #"반"
 {:dim :distance
  :latent true
  :value 0.5 }

 "<latent dist> km"
 [(dim :distance) #"(?i)km|(킬|키)로((미|메)터)?"]
 (-> %1
     (dissoc  :latent)
     (merge {:unit "kilometre"
         :normalized {:value (* 1000 (-> %1 :value))
                :unit "metre"}}))

 "<latent dist> feet"
 [(dim :distance) #"(?i)('|f(oo|ee)?ts?)|피트"]
 (-> %1
     (dissoc  :latent)
     (merge {:unit "foot"
         :normalized {:value (* 0.3048 (-> %1 :value))
                :unit "metre"}}))

 "<latent dist> inch"
 [(dim :distance) #"(?i)(''|inch(es)?)|인치"]
 (-> %1
     (dissoc  :latent)
     (merge {:unit "inch"
         :normalized {:value (* 0.0254 (-> %1 :value))
                :unit "metre"}}))

 ;;temporary hack
 "<latent dist> feet and <latent dist> inch "
 [(dim :distance) #"(?i)('|f(oo|ee)?ts?)|피트" (dim :distance) #"(?i)(''|inch(es)?)|인치"]
 (-> %1
     (dissoc  :latent)
     (merge {:unit "foot"
         :normalized {:value (+ (* 0.3048 (-> %1 :value)) (* 0.0254 (-> %3 :value)))
                :unit "metre"}}))

 "<latent dist> yard"
 [(dim :distance) #"(?i)y(ar)?ds?|야드"]
 (-> %1
     (dissoc  :latent)
     (merge {:unit "yard"
         :normalized {:value (* 0.9144 (-> %1 :value))
                :unit "metre"}}))

 "<dist> meters"
 [(dim :distance) #"(?i)m|(미|메|매)터"]
 (-> %1
     (dissoc  :latent)
     (merge {:unit "metre"}))

 "<dist> centimeters"
 [(dim :distance) #"(?i)cm|센(티|치)((미|메)터)?"]
 (-> %1
     (dissoc  :latent)
     (merge {:unit "centimetre"
         :normalized {:value (* 0.01 (-> %1 :value))
                :unit "metre"}}))

 "<dist> miles"
 [(dim :distance) #"(?i)miles?|마일즈?"]
 (-> %1
     (dissoc :latent)
     (merge {:unit "mile"
         :normalized {:value (* 1609 (-> %1 :value))
                :unit "metre"}}))

 ;; volume

 ; latent volume
 "number as volume"
 (dim :number)
 {:dim :volume
  :latent true
  :value (:value %1)}

  "<latent vol> ml"
 [(dim :volume) #"(?i)ml|(밀|미)리리터"]
 (-> %1
     (dissoc  :latent)
     (merge {:unit "millilitre"
             :normalized {:value (* 0.001 (-> %1 :value))
             :unit "litre"}}))

  "<vol> hectoliters"
 [(dim :volume) #"(핵|헥)토리터"]
 (-> %1
     (dissoc  :latent)
     (merge {:unit "hectolitre"
             :normalized {:value (* 100 (-> %1 :value))
             :unit "litre"}}))

 "<vol> liters"
 [(dim :volume) #"(?i)l|리터"]
 (-> %1
     (dissoc  :latent)
     (merge {:unit "litre"}))
 
 "<latent vol> gallon"
 [(dim :volume) #"(?i)gal(l?ons?)?|갤(런|론)"]
 (-> %1
     (dissoc  :latent)
     (merge {:unit "gallon"
         :normalized {:value (* 3.785 (-> %1 :value))
                :unit "litre"}}))
 ;; Quantity
 
 ; quantity token inherits metadata from product
 
 ; three teaspoons
 "<number> <units>"
 [(dim :number) (dim :leven-unit)]
 {:dim :quantity
  :value (:value %1)
  :unit (:value %2)
  :form :no-product}

 ; 3 pounds of flour - 3파운드의 밀가루
 "<quantity> of product"
 [(dim :quantity #(= :no-product (:form %))) #"의" (dim :leven-product)]
 (-> %1
   (assoc :product (:value %3))
   (dissoc :form))

 ; 3 pounds of flour - 밀가루 3파운드
 "<quantity> of product"
 [(dim :leven-product) (dim :quantity #(= :no-product (:form %)))]
 (-> %2
   (assoc :product (:value %1))
   (dissoc :form))


 ; units
 ;
 "pounds"
 #"파운(드|즈)"
 {:dim :leven-unit :value "pound"}

 "gram"
 #"그(램|람)"
 {:dim :leven-unit :value "그램"}

 "근"
 #"근"
 {:dim :leven-unit :value "근"}

 "개"
 #"개"
 {:dim :leven-unit :value "개"}

 "cup - 컵"
 #"컵"
 {:dim :leven-unit :value "cup"}

 "Bowl - 그릇"
 #"그릇"
 {:dim :leven-unit :value "bowl"}

 "dish - 접시"
 #"그릇"
 {:dim :leven-unit :value "dish"}

 "판 - a pizza => 피자한판"
 #"판"
 {:dim :leven-unit :value "판"}


 ; products
 "삼겹살"
 #"삼겹살"
 {:dim :leven-product :value "삼겹살"}

 ; coke
 "콜라"
 #"콜라"
 {:dim :leven-product :value "콜라"}

)
