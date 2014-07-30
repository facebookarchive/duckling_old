(
  ;; Quantity
  
  ; three teaspoons
  "<number> <units>"
  [(dim :number) (dim :leven-unit)]
  {:dim :quantity
   :value (:val %1)
   :unit (:value %2)
   :form :no-product}
 
  ; a pound
  "a <unit>"
  [#"(?i)an?" (dim :leven-unit)]
  {:dim :quantity
   :value 1
   :unit (:value %2)
   :form :no-product}

   ; 3 pounds of flour
  "<quantity> of product"
  [(dim :quantity #(= :no-product (:form %))) #"(?i)of" (dim :leven-product)]
  (-> %1 
      (assoc :product (:value %3))
      (dissoc :no-product))
  
  ; 2 apples
  "<number> product"
  [(dim :number) (dim :leven-product)]
  {:dim :quantity
   :value (:val %1)
   :product (:value %2)}
   
  ; an apple
  "a <product>"
  [#"(?i)an?" (dim :leven-product)]
  {:dim :quantity
   :value 1
   :product (:value %2)}

  ; Stubs for corpus
  "pounds"
  #"pounds?"
  {:dim :leven-unit :value "pound"}
  
  "meat"
  #"meat"
  {:dim :leven-product :value "meat"}
  
)
