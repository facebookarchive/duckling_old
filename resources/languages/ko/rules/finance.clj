(

  "intersect (X cents)" ;
  [(dim :amount-of-money) (dim :amount-of-money #(= (:unit %) "cent"))]
  (compose-money %1 %2) 
  
  ; #(not (:number-prefixed %)

  "₩"
  #"\₩|원|(?i)KRW"
  {:dim :unit
   :unit "KRW"}
  
  "$"
  #"\$|달러|불"
  {:dim :unit
   :unit "$"} ; ambiguous
  
  "cent"
  #"(?i)cents?|센[트|츠]|c|¢" ; to do:localize the corpus and rules per language
  {:dim :unit
   :unit "cent"}
   
  "€"
  #"€|유로|(?i)EURO?"
  {:dim :unit
   :unit "EUR"} ; not ambiguous
  
  "£"
  #"£|파운드|영국파운드"
  {:dim :unit
   :unit "£"}
  
  "GBP"
  #"(?i)GBP"
  {:dim :unit
   :unit "GBP"}
  
  ;Australian Dollar Currency
  "AUD"
  #"(?i)AUD|호주달러"
  {:dim :unit
   :unit "AUD"}
  
  "USD"
  #"(?i)US[D\$]"
  {:dim :unit
   :unit "USD"}
  
  "PTS"
  #"(?i)pta?s?"
  {:dim :unit
   :unit "PTS"}
  
  ;Indian Currency
  "INR"
  #"(?i)INR|Rs(. )?|(R|r)upees?|루피|인도루피"
  {:dim :unit
   :unit "INR"}
  
  ;Emirates Currency
  "AED"
  #"(?i)AED|(D|d)irhams?"
  {:dim :unit
   :unit "AED"}
  
  "unnamed currency"
  #"(?i)(buck|balle|pouloute)s?"
  {:dim :unit}
  
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
  
  ;precision for "약 $15"
  "about <amount-of-money>"
  [#"약|대충|얼추" (dim :amount-of-money)]
  (assoc %2 :precision "approximate")
  
  ;precision for "$15 정도"
  "<amount-of-money> about"
  [(dim :amount-of-money) #"정도|쯤"]
  (assoc %1 :precision "approximate")
  
  "exactly <amount-of-money>"
  [#"딱|정확히" (dim :amount-of-money)]
  (assoc %2 :precision "exact")

)
