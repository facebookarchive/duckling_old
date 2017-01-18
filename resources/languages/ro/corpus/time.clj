(
  ; Context map
  ; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:reference-time (time/t -2 2013 2 12 4 30 0)
   :min (time/t -2 1900)
   :max (time/t -2 2100)}

  "acum"
  "chiar acum"
  (datetime 2013 2 12 4 30 00)

  "azi"
  "astazi"
  "astăzi"
  (datetime 2013 2 12)

  "ieri"
  (datetime 2013 2 11)

  "maine"
  "mâine"
  (datetime 2013 2 13)

  "luni"
  "lunea asta"
  "lunea aceasta"
  (datetime 2013 2 18 :day-of-week 1)

  "Luni, 18 Feb"
  "Luni, 18 Februarie"
  (datetime 2013 2 18 :day-of-week 1 :day 18 :month 2)

  "marti"
  "marți"
  "Marti 19"
  "Marti pe 19"
  "Marți 19"
  (datetime 2013 2 19)

  "joi"
  "jo"
  (datetime 2013 2 14)

  "vineri"
  "vi"
  (datetime 2013 2 15)

  "sambata"
  "sâmbătă"
  "sam"
  "sa"
  (datetime 2013 2 16)

  "duminica"
  "duminică"
  "du"
  "dum"
  (datetime 2013 2 17)

  "1 martie"
  "intai martie"
  (datetime 2013 3 1 :day 1 :month 3)
)
