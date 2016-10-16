(

"intersect" ;
[(dim :amount-of-money) (dim :number)]
(compose-money %1 %2) 

; #(not (:number-prefixed %)

"$"
#"\$|dolar?"
{:dim :unit
 :unit "$"} ; ambiguous
 
"€"
#"(?i)€|([e€]uro?s?)"
{:dim :unit
 :unit "EUR"} ; not ambiguous

"£"
#"(?i)£|pound(sterling)?"
{:dim :unit
 :unit "£"}

"USD"
#"(?i)US[D\$]"
{:dim :unit
 :unit "USD"}

"SGD"
#"(?i)SG[D\$]"
{:dim :unit
 :unit "SGD"}

"GBP"
#"(?i)GBP"
{:dim :unit
 :unit "GBP"}

"JPY"
#"(?i)JPY|¥(. )?|(Y|y)en"
{:dim :unit
 :unit "JPY"}

"PTS"
#"(?i)pta?s?"
{:dim :unit
 :unit "PTS"}

;Indian Currency
"INR"
#"(?i)INR|Rs(. )?|(R|r)upee"
{:dim :unit
 :unit "INR"}

;Indonesian Currency
"IDR"
#"(?i)IDR|Rp(. )?|(R|r)upiah"
{:dim :unit
 :unit "IDR"}

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
