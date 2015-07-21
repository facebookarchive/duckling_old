(

"intersect (X cents)" ;
[(dim :amount-of-money) (dim :amount-of-money #(= (:unit %) "cent"))]
(compose-money %1 %2) 

"intersect (and X cents)" ;
[(dim :amount-of-money) #"(?i)and" (dim :amount-of-money #(= (:unit %) "cent"))]
(compose-money %1 %3) 

"intersect" ;
[(dim :amount-of-money) (dim :number)]
(compose-money %1 %2) 

"intersect (and number)" ;
[(dim :amount-of-money) #"(?i)and" (dim :number)]
(compose-money %1 %3) 

; #(not (:number-prefixed %)

"$"
#"\$|dollars?"
{:dim :unit
 :unit "$"} ; ambiguous
 
"€"
#"(?i)€|([e€]uro?s?)"
{:dim :unit
 :unit "EUR"} ; not ambiguous

"£"
#"(?i)£|pounds?"
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
#"(?i)cents?|penn(y|ies)|c|¢" ; to do:localize the corpus and rules per language
{:dim :unit
 :unit "cent"}

;Indian Currency
"INR"
#"(?i)INR|Rs(. )?|(R|r)upees?"
{:dim :unit
 :unit "INR"}

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

;precision for "about $15"
"about <amount-of-money>"
[#"(?i)about|approx(\.|imately)?|close to|near( to)?|around|almost" (dim :amount-of-money)]
(assoc %2 :precision "approximate")

"exactly <amount-of-money>"
[#"(?i)exactly|precisely" (dim :amount-of-money)]
(assoc %2 :precision "exact")

)
