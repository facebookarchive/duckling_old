({}

  "$10"
  "10$"
  "deset dolara"
  (money 10 "$")

  ;"under $15"
  ;"no more than 15$"
  ;"no greater than 15$"
  ;"less than fifteen dolars"
  ;(money 15 "$" "<")

  "deset centa"
  (money 10 "cent")

  "$10.000"
  "10K$"
  "$10k"
  (money 10000 "$")

  "USD1,23"
  (money 1.23 "USD")

  "2 dolara and 23 centa"
  "dva dolara 23 centa"
  "2 dolara 23"
  "dva dolara i 23"
  (money 2.23 "$")

  "2 kune i 23 lipe"
  "dvije kune 23 lipe"
  "2 kune 23"
  "dvije kune i 23"
  (money 2.23 "HRK")

  "100 kuna"
  "sto kuna"
  (money 100 "HRK")

  "200 kuna"
  "dvije stotine kuna"
  "dvjesto kuna"
  "dvjesta kuna"
  (money 200 "HRK")

  "20€"
  "20 euros"
  "20 Euro"
  "20 Euros"
  "EUR 20"
  (money 20 "EUR")

  "EUR29,99"
  (money 29.99 "EUR")

  ;Indian Currency
  "Rs. 20"
  "Rs 20"
  "20 Rupija"
  "20Rs"
  "Rs20"
  (money 20 "INR")

  "20 Rupija 43"
  "dvadeset rupija 43"
  (money 20.43 "INR")

  "INR33"
  (money 33 "INR")

  "3 bucks"
  (money 3) ; unknown unit

  "£9"
  "devet funti"
  (money 9 "£")

  "GBP3,01"
  "GBP 3,01"
  (money 3.01 "GBP"))

;around $200
;between $200 and $300
;around 200-500 dolars
;up to 1000
;~50-100
