(

"intersect (X cents)" ;
[(dim :amount-of-money) (dim :amount-of-money #(= (:unit %) "cent"))]
(compose-money %1 %2)

"intersect (and X cents)" ;
[(dim :amount-of-money) #"(?i)(si|și)" (dim :amount-of-money #(= (:unit %) "cent"))]
(compose-money %1 %3)

"intersect (X bani)" ;
[(dim :amount-of-money) (dim :amount-of-money #(= (:unit %) "ban"))]
(compose-money %1 %2)

"intersect (and X bani)" ;
[(dim :amount-of-money) #"(?i)(si|și)" (dim :amount-of-money #(= (:unit %) "ban"))]
(compose-money %1 %3)

"intersect" ;
[(dim :amount-of-money) (dim :number)]
(compose-money %1 %2)

"intersect (and number)" ;
[(dim :amount-of-money) #"(?i)(si|și)" (dim :number)]
(compose-money %1 %3)

; #(not (:number-prefixed %)

"RON"
#"(?i)(roni?|lei)"
{:dim :unit
 :unit "RON"} ; ambiguous

"ban"
#"(?i)(bani?)"
{:dim :unit
 :unit "ban"} ; ambiguous

"$"
#"\$|dolari?"
{:dim :unit
 :unit "$"} ; ambiguous

"€"
#"(?i)€|([e€]uro?)"
{:dim :unit
 :unit "EUR"} ; not ambiguous

"£"
#"(?i)£|lire?"
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

"GBP"
#"(?i)GBP"
{:dim :unit
 :unit "GBP"}

"PTS"
#"(?i)pta?s?"
{:dim :unit
 :unit "PTS"}

"cent"
#"(?i)cen[tț]i?|c|¢" ; to do:localize the corpus and rules per language
{:dim :unit
 :unit "cent"}

;Indian Currency
"INR"
#"(?i)INR|Rs(. )?|(R|r)upii?"
{:dim :unit
 :unit "INR"}

;Emirates Currency
"AED"
#"(?i)AED|(D|d)irhami?"
{:dim :unit
 :unit "AED"}

;Saudi riyal
"SAR"
#"(?i)SAR|rial saudit?"
{:dim :unit
 :unit "SAR"}

 ;qatari riyal
"QAR"
#"(?i)QAR|rial watarian?"
{:dim :unit
 :unit "QAR"}

 ;Egyptian Pound
 "EGP"
#"(?i)EGP|lir[aă] egiptian[aă] ?"
{:dim :unit
 :unit "EGP"}

 ;Lebanese Pound
 "LBP"
#"(?i)LBP|lir[aă] lbanez[aă] ?"
{:dim :unit
 :unit "LBP"}

  ;Kuwaiti Dinar
 "KWD"
#"(?i)KWD|dinar kuweitian ?"
{:dim :unit
 :unit "KWD"}

;"unnamed currency"
;#"(?i)(buck|balle|pouloute)s?"
;{:dim :unit}

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
[#"(?i)cam|aprox(\.|imativ)?|aproape|[iî]n jur (de)?" (dim :amount-of-money)]
(assoc %2 :precision "approximate")

"exactly <amount-of-money>"
[#"(?i)exact" (dim :amount-of-money)]
(assoc %2 :precision "exact")

)
