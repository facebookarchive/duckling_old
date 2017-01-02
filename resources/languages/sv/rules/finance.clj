(

"intersect (X cents)" ;
[(dim :amount-of-money) (dim :amount-of-money #(= (:unit %) "öre"))]
(compose-money %1 %2)

"intersect (and X cents)" ;
[(dim :amount-of-money) #"(?i)och" (dim :amount-of-money #(= (:unit %) "öre"))]
(compose-money %1 %3)

"intersect" ;
[(dim :amount-of-money) (dim :number)]
(compose-money %1 %2)

"intersect (and number)" ;
[(dim :amount-of-money) #"(?i)och" (dim :number)]
(compose-money %1 %3)

; #(not (:number-prefixed %)

"$"
#"\$|dollar(s)?"
{:dim :unit
 :unit "$"} ; ambiguous

"€"
#"(?i)€|([e€]uro?s?)"
{:dim :unit
 :unit "EUR"} ; not ambiguous

"£"
#"(?i)£|pund?"
{:dim :unit
 :unit "£"}

;Australian Dollar Currency
"AUD"
#"(?i)AUD"
{:dim :unit
 :unit "AUD"}

"USD"
#"(?i)US[D\$]"
{:dim :unit
 :unit "USD"}

"NOK"
#"(?i)NOK|norska kronor|nkr"
{:dim :unit
 :unit "NOK"}

"SEK"
#"(?i)SEK|kronor|kr"
{:dim :unit
 :unit "SEK"}

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

 "öre"
#"(?i)öre" ; to do:localize the corpus and rules per language
{:dim :unit
 :unit "öre"}

;Indian Currency
"INR"
#"(?i)INR|Rs(. )?|(R|r)upees?"
{:dim :unit
 :unit "INR"}

;Emirates Currency
"AED"
#"(?i)AED|(D|d)irhams?"
{:dim :unit
 :unit "AED"}

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
[#"(?i)omkring|cirka|runt|ca" (dim :amount-of-money)]
(assoc %2 :precision "approximate")

"exactly <amount-of-money>"
[#"(?i)exakt|precis" (dim :amount-of-money)]
(assoc %2 :precision "exact")

)
