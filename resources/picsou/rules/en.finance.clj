(
 
"$"
#"\$|dollars?"
{:dim :currency
 :currency "$"} ; ambiguous
 
"€"
#"(?i)€|([e€]uro?s?)"
{:dim :currency
 :currency "EUR"} ; not ambiguous

"£"
#"(?i)£|pounds?"
{:dim :currency
 :currency "£"}

"USD"
#"(?i)US[D\$]"
{:dim :currency
 :currency "USD"}

"GBP"
#"(?i)GBP"
{:dim :currency
 :currency "GBP"}

"PTS"
#"(?i)pta?s?"
{:dim :currency
 :currency "PTS"}

"unnamed currency"
#"(?i)(buck|balle|pouloute)s?"
{:dim :currency}

"<currency> <amount>"
[(dim :currency) (dim :number)]
{:dim :amount-of-money
 :val {:amount (:val %2)
 	   :currency (:currency %1)}}

"<amount> <currency>"
[(dim :number) (dim :currency)]
{:dim :amount-of-money
 :val {:amount (:val %1)
 	   :currency (:currency %2)}}

)