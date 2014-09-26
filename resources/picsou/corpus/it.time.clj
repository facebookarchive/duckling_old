(
  ; Context map
  ; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:reference-time (time/t -2 2013 2 12 4 30 0)}

  "subito"
  "immediatamente"
  "in questo momento"
  (datetime 2013 2 12 4 30 00)
  
  "ora"
  "di oggi"
  "adesso"
  (datetime 2013 2 12)

  "ieri"
  (datetime 2013 2 11)

  "domani"
  (datetime 2013 2 13)
  
  "Il giorno dopo domani"
  "dopodomani"
  (datetime 2013 2 14)
  
  "l'altro ieri"
  "altroieri"
  (datetime 2013 2 10)

  "lunedi"
  "lu."
  (datetime 2013 2 18 :day-of-week 1)

  "lunedi 18 febbraio"
  (datetime 2013 2 18 :day-of-week 1 :day 18 :month 2)

  "Martedì"
  (datetime 2013 2 19 :day-of-week 2)

  "Mercoledì"
  "mer"
  "mer."
  (datetime 2013 2 13 :day-of-week 3)
  
  "mercoledi 13 feb"
  "il 13 febbraio"
  (datetime 2013 2 13 :day-of-week 3 :day 13 :month 2)

  "il 13 febbraio 2013"
  (datetime 2013 2 13 :day-of-week 3 :day 13 :month 2 :year 2013)

  "giovedi"
  "gio"
  (datetime 2013 2 14)

  "venerdi"
  "venerdì"
  "ven"
  (datetime 2013 2 15)

  "sabato"
  "sab"
  "sab."
  (datetime 2013 2 16)

  "domenica"
  "dom"
  "dom."
  (datetime 2013 2 17)
  
  "domenica 10 febbraio"
  (datetime 2013 2 10 :day-of-week 7 :day 13 :month 2) ; with current look-forward default...
  
  "il 1 marzo"
  "prima di marzo"
  (datetime 2013 3 1 :day 1 :month 3)

  "3 marzo 2015"
  "3/3/2015"
  "3/3/15"
  "2015-3-3"
  "2015-03-03"
  (datetime 2015 3 3 :day 3 :month 3 :year 2015)

  "il 15 febbraio"
  "15/2"
  "il 15/02"
  (datetime 2013 2 15 :day 15 :month 2)
  
  "31/10/1974"
  "31/10/74"
  (datetime 1974 10 31 :day 31 :month 10 :year 1974)

  "martedì scorso"
  (datetime 2013 2 5 :day-of-week 2)

  "martedì prossimo" 
  "il martedì dopo"
  (datetime 2013 2 19 :day-of-week 2)

  "mercoledì prossimo" 
  ;"mercoledi fra una settimana"
  (datetime 2013 2 20 :day-of-week 3)

   ;; Cycles

  "questa settimana"
  (datetime 2013 2 11 :grain :week)
  
  "la settimana scorsa"
  (datetime 2013 2 4 :grain :week)
  
  "la settimana prossima"
  (datetime 2013 2 18 :grain :week)
  
  "il mese scorso"
  (datetime 2013 1)

  "il mese prossimo"
  (datetime 2013 3)
  
  "l'anno scorso"
  (datetime 2012)
  
  "quest'anno"
  (datetime 2013)
  
  "il prossimo anno"
  (datetime 2014)

  "ultima domenica"
  ;"domenica della scorsa settimana"
  (datetime 2013 2 10 :day-of-week 7)

  ;; Hours

  "alle 3 di pomeriggio"
  "le tre di pomeriggio"
  (datetime 2013 2 12 15)

  "3:00"
  "03:00"
  (datetime 2013 2 12 15 0 :hour 15 :minute 0)

  "alle tre e un quarto"
  "3:15 di pomeriggio"
  "15:15"
  (datetime 2013 2 12 15 15 :hour 15 :minute 15)

  "alle tre e venti"
  "3:20 di pomeriggio"
  (datetime 2013 2 12 15 20 :hour 3 :minute 20)

  ; "alle tre week-end mezzo"
  ; "3:30 di pomeriggio"

  "15:30"
  (datetime 2013 2 12 15 30 :hour 15 :minute 30)

  "a mezzogiorno meno un quarto"
  "quarto a mezzogiorno"
  "11:45 del mattino"
  (datetime 2013 2 12 11 45 :hour 11 :minute 45)

  "alle 3 del mattino"
  (datetime 2013 2 13 3 :hour 3 :minute 45)
  
  "alle 19:30 il venerdì 20 settembre"
  (datetime 2013 9 20 19 30 :hour 19 :minute 30 :day-of-week 5 :day 20 :month 9)

  ;; Involving periods   ; look for grain-after-shift

  "questo week-end"
  (datetime-interval [2013 2 15 18] [2013 2 18 00])

  "lunedi mattina"
  (datetime-interval [2013 2 18 4] [2013 2 18 12])

  ; Part of day (morning, afternoon...)

  "15 febbraio al mattino"
  "mattino di 15 febbraio"
  (datetime-interval [2013 2 15 4] [2013 2 15 12])

   ; Intervals involving cycles
  
  "ultimi 2 secondi" 
  "ultimi due secondi"
  (datetime-interval [2013 2 12 4 29 58] [2013 2 12 4 30 00])

  "prossimi 3 secondi"
  "prossimi tre secondi"
  (datetime-interval [2013 2 12 4 30 01] [2013 2 12 4 30 04])

  "ultimi 2 minuti"
  "ultimi due minuti"
  (datetime-interval [2013 2 12 4 28] [2013 2 12 4 30])

  "ultime 2 ore"
  "ultime due ore"
  (datetime-interval [2013 2 12 2] [2013 2 12 4])

  "prossime 3 ore"
  (datetime-interval [2013 2 12 5] [2013 2 12 8])

  "ultimi 2 giorni"
  (datetime-interval [2013 2 10] [2013 2 12])
    
  "ultime due settimane"
  (datetime-interval [2013 1 28 :grain :week] [2013 2 11 :grain :week])

  "prossime tre settimane"
  (datetime-interval [2013 2 18 :grain :week] [2013 3 11 :grain :week])

  "ultimi due mesi"
  (datetime-interval [2012 12] [2013 02])

  "prossimi 3 mesi"
  (datetime-interval [2013 3] [2013 6])

  "ultimi 2 anni"
  (datetime-interval [2011] [2013])
  
  "prossimi tre anni"
  (datetime-interval [2014] [2017]) 
  
)