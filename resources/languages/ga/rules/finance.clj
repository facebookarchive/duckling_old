(

  "intersect (X cents)" ;
  [(dim :amount-of-money) (dim :amount-of-money #(= (:unit %) "cent"))]
  (compose-money %1 %2)

  "intersect (and X cents)" ;
  [(dim :amount-of-money) #"(?i)agus|is" (dim :amount-of-money #(= (:unit %) "cent"))]
  (compose-money %1 %3)

  "intersect" ;
  [(dim :amount-of-money) (dim :number)]
  (compose-money %1 %2)

  "intersect (agus number)" ;
  [(dim :amount-of-money) #"(?i)agus|is" (dim :number)]
  (compose-money %1 %3)

  ;precision for "about $15"
  "thart ar <amount-of-money>"
  [#"(?i)thart( ar)?|beagnach|breis (is|agus)" (dim :amount-of-money)]
  (assoc %2 :precision "approximate")

  "<amount-of-money> glan"
  [(dim :amount-of-money) #"(?i)glan|baileach|(go )?d[íi]reach" ]
  (assoc %1 :precision "exact")

  ; #(not (:number-prefixed %)

  "$"
  #"\$|n?dh?oll?ai?rs?"
  {:dim :unit
   :unit "$"} ; ambiguous

  "€"
  #"(?i)€|([e€]uro?s?)"
  {:dim :unit
   :unit "EUR"} ; not ambiguous

  "£"
  #"(?i)£|pounds?|b?ph?unt"
  {:dim :unit
   :unit "£"}

  "USD"
  #"(?i)US[D\$]"
  {:dim :unit
   :unit "USD"}

  "GBP"
  #"(?i)GBP"
  {:dim :unit
   :unit "GBP"}

  "PTS"
  #"(?i)pta?s?"
  {:dim :unit
   :unit "PTS"}

  "cent"
  #"(?i)cents?|g?ch?eint(eanna)?|c|¢" ; to do:localize the corpus and rules per language
  {:dim :unit
   :unit "cent"}

  ;Indian Currency
  "INR"
  #"(?i)INR|Rs(. )?|(R|r)upees?|(R|r)[úu]pa[íi]"
  {:dim :unit
   :unit "INR"}

  "<unit> <amount>"
  [(dim :unit) (dim :number)]
  {:dim :amount-of-money
   :value (:value %2)
   :unit (:unit %1)
   :fields {(:unit %1) (:value %2)}}

  "<amount> <unit>"
  [(dim :number) (dim :unit)]
  {:dim :amount-of-money
   :value (:value %1)
   :unit (:unit %2)
   :fields {(:unit %1) (:value %2)}}

)
