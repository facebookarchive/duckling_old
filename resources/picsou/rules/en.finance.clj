(
 
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

"unnamed currency"
#"(?i)(buck|balle|pouloute)s?"
{:dim :unit}

"<unit> <amount>"
[(dim :unit) (dim :number)]
{:dim :amount-of-money
 :val {:amount (:val %2)
 	   :unit (:unit %1)
       :fields {(:unit %1) (:val %2)}}}

"<amount> <unit>"
[(dim :number) (dim :unit)]
{:dim :amount-of-money
 :val {:amount (:val %1)
 	   :unit (:unit %2)
       :fields {(:unit %1) (:val %2)}}}

;precision for "about $15"
"about <amount-of-money>"
[#"(?i)about|approx(\.|imately)?|close to|near( to)?|around|almost" (dim :amount-of-money)]
(assoc %2 :precision "approximate")

"exactly <amount-of-money>"
[#"(?i)exactly|precisely" (dim :amount-of-money)]
(assoc %2 :precision "exact")

)