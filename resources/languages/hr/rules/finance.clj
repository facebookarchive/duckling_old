("intersect (X cents)" ;
  [(dim :amount-of-money) (dim :amount-of-money #(= (:unit %) "cent"))]
  (compose-money %1 %2)

  "intersect (and X cents)" ;
  [(dim :amount-of-money) #"(?i)and" (dim :amount-of-money #(= (:unit %) "cent"))]
  (compose-money %1 %3)

  "intersect (X lipa)" ;
  [(dim :amount-of-money) (dim :amount-of-money #(= (:unit %) "lipa"))]
  (compose-money %1 %2)

  "intersect (i X lipa)" ;
  [(dim :amount-of-money) #"(?i)i" (dim :amount-of-money #(= (:unit %) "lipa"))]
  (compose-money %1 %3)


  "intersect" ;
  [(dim :amount-of-money) (dim :number)]
  (compose-money %1 %2)

  "intersect (and number)" ;
  [(dim :amount-of-money) #"(?i)i" (dim :number)]
  (compose-money %1 %3)

  ; #(not (:number-prefixed %)

  "HRK"
  #"(?i)hrk|kn|hrvatsk(a|ih|e) kun(a|e)|kun(a|e)"
  {:dim :unit :unit "HRK"}

  "lipa"
  #"(?i)lip(a|e)|lp"
  {:dim :unit :unit "lipa"}

  "$"
  #"\$|dolar(a|i|e)?"
  {:dim :unit :unit "$"} ; ambiguous

  "€"
  #"(?i)€|([e€]uro?s?)"
  {:dim :unit :unit "EUR"} ; not ambiguous

  "£"
  #"(?i)£|funt(a|e|i)?"
  {:dim :unit :unit "£"}

  ;Australian Dollar Currency
  "AUD"
  #"(?i)AUD"
  {:dim :unit :unit "AUD"}

  "USD"
  #"(?i)US[D\$]"
  {:dim :unit :unit "USD"}

  "GBP"
  #"(?i)GBP"
  {:dim :unit :unit "GBP"}

  "PTS"
  #"(?i)pta?s?"
  {:dim :unit :unit "PTS"}

  "cent"
  #"(?i)cent(i|a)?|penij(i|a)?|c|¢" ; to do:localize the corpus and rules per language
  {:dim :unit :unit "cent"}

  ;Indian Currency
  "INR"
  #"(?i)INR|Rs(. )?|(R|r)upija?"
  {:dim :unit :unit "INR"}

  ;Emirates Currency
  "AED"
  #"(?i)AED|(D|d)rahma?"
  {:dim :unit :unit "AED"}

  ;Saudi riyal
  "SAR"
  #"(?i)SAR|(S|s)audijskirijal|(S|s)audi rijal?"
  {:dim :unit :unit "SAR"}

  ;qatari riyal
  "QAR"
  #"(?i)QAR|(k|K)atarskiirijal|(k|K)atarski rijal ?"
  {:dim :unit :unit "QAR"}

  ;Egyptian Pound
  "EGP"
  #"(?i)EGP|(E|e)gipatskafunta|(E|e)gipatska funta ?"
  {:dim :unit :unit "EGP"}

  ;Lebanese Pound
  "LBP"
  #"(?i)LBP|(L|l)ibanonskafunta|(L|l)ibanonska Funta ?"
  {:dim :unit :unit "LBP"}

  ;Kuwaiti Dinar
  "KWD"
  #"(?i)KWD|(K|k)uvajtskidinar|(K|k)uvajtski Dinar ?"
  {:dim :unit :unit "KWD"}

  "unnamed currency"
  #"(?i)(buck|balle|pouloute)s?"
  {:dim :unit}

  "<unit> <amount>"
  [(dim :unit) (dim :number)]
  {:dim    :amount-of-money
   :value  (:value %2)
   :unit   (:unit %1)
   :fields {(:unit %1) (:value %2)}}

  "<amount> <unit>"
  [(dim :number) (dim :unit)]
  {:dim    :amount-of-money
   :value  (:value %1)
   :unit   (:unit %2)
   :fields {(:unit %1) (:value %2)}}

  ;precision for "about $15"
  "about <amount-of-money>"
  [#"(?i)about|approx(\.|imately)?|close to|near( to)?|around|almost" (dim :amount-of-money)]
  (assoc %2 :precision "approximate")

  "exactly <amount-of-money>"
  [#"(?i)exactly|precisely" (dim :amount-of-money)]
  (assoc %2 :precision "exact"))
