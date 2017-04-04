(;; Distance
;; NEED TO PUSH THE NORMALIZATION IN THE HELPER + CREATE Function to combine distance

; "two distance tokens in a row"
; [(dim :distance #(not (:latent %))) (dim :distance #(not (:latent %)))] ; sequence of two tokens with a distance dimension
; (combine-distance %1 %2)

; 	; same thing, with "and" in between like "3 feets and 2 inches"
; "two distance tokens separated by and"
; [(dim :distance #(not (:latent %))) #"(?i)and" (dim :distance #(not (:latent %)))] ; sequence of two tokens with a time fn
; (combine-distance %1 %3)

; latent distance
  "number as distance"
  (dim :number)
  {:dim :distance :latent true :value (:value %1)}

  "<latent dist> km"
  [(dim :distance) #"(?i)k(ilo)?m?(eta?r)?a?"]
  (-> %1
      (dissoc :latent)
      (merge
        {:unit       "kilometre"
         :normalized {:value (* 1000 (-> %1 :value)) :unit "metre"}}))

  "<dist> metar"
  [(dim :distance) #"(?i)metara?"]
  (-> %1
      (dissoc :latent)
      (merge {:unit "metre"}))

  "<dist> centimetar"
  [(dim :distance) #"(?i)cm|centimeta?ra?"]
  (-> %1
      (dissoc :latent)
      (merge
        {:unit       "centimetre"
         :normalized {:value (* 0.01 (-> %1 :value)) :unit "metre"}}))

  "<dist> milja"
  [(dim :distance) #"(?i)milja"]
  (-> %1
      (dissoc :latent)
      (merge
        {:unit       "mile"
         :normalized {:value (* 1609 (-> %1 :value)) :unit "metre"}}))

  "<dist> m (ambiguous miles or meters)"
  [(dim :distance) #"(?i)m"]
  (-> %1
      (dissoc :latent)
      (merge {:unit "m"}))

  ;; volume

  ; latent volume
  "number as volume"
  (dim :number)
  {:dim :volume :latent true :value (:value %1)}

  "<latent vol> ml"
  [(dim :volume) #"(?i)m(l|ililita?ra?)"]
  (-> %1
      (dissoc :latent)
      (merge
        {:unit       "millilitre"
         :normalized {:value (* 0.001 (-> %1 :value)) :unit "litre"}}))

  "<vol> hektolitar"
  [(dim :volume) #"(?i)hektolita?ra?"]
  (-> %1
      (dissoc :latent)
      (merge
        {:unit       "hectolitre"
         :normalized {:value (* 100 (-> %1 :value)) :unit "litre"}}))

  "<vol> litra"
  [(dim :volume) #"(?i)l(it(a)?r(a|e)?)?"]
  (-> %1
      (dissoc :latent)
      (merge {:unit "litre"}))

  "pola litre"
  [#"(?i)pola litre"]
  (-> %1
      (dissoc :latent)
      (merge
        {:unit "litre" :value 0.5}))

  "<latent vol> galon"
  [(dim :volume) #"(?i)gal(ona?)?"]
  (-> %1
      (dissoc :latent)
      (merge
        {:unit       "gallon"
         :normalized {:value (* 3.785 (-> %1 :value)) :unit "litre"}}))

  ;; Quantity

  ; qantity token inherits metadata from product

  ; three teaspoons
  "<number> <units>"
  [(dim :number) (dim :leven-unit)]
  {:dim :quantity :value (:value %1) :unit (:value %2) :form :no-product}

  ; kila
  "<unit>"
  [(dim :leven-unit)]
  {:dim :quantity :value 1 :unit (:value %1) :form :no-product}

  ; 3 pounds of flour
  "<quantity> product"
  [(dim :quantity #(= :no-product (:form %))) (dim :leven-product)]
  (-> %1
      (assoc :product (:value %2))
      (dissoc :form))

  ; 2 apples
  "<number> product"
  [(dim :number) (dim :leven-product)]
  {:dim :quantity :value (:value %1) :product (:value %2)}

  ;	; an apple
  ;	"a <product>"
  ;	[#"(?i)an?" (dim :leven-product)]
  ;	{:dim :quantity
  ;	 :value 1
  ;	 :product (:value %2)}

  ; apple
  "<product>"
  [(dim :leven-product)]
  {:dim :quantity :value 1 :product (:value %1)}

  ; Stubs for corpus
  "kilogram"
  #"k(il(o|e|a))?(g(rama?)?)?"
  {:dim :leven-unit :value "kilogram"}

  "meso"
  #"mes(o|a)"
  {:dim :leven-product :value "meso"}

  "sol"
  #"soli?"
  {:dim :leven-product :value "sol"})