(
  ; Context map
  ; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:reference-time (time/local-date-time [2013 2 12 4 30])}

  "subito"
  "immediatamente"
  (datetime 2013 2 12 4 30 00 - 01)
  
  "ora"
  "in questo momento"
  "di oggi"
  "adesso"
  (datetime 2013 2 12 - 13)

  "ieri"
  (datetime 2013 2 11 - 12)

  "domani"
  (datetime 2013 2 13 - 14)
  
  "Il giorno dopo domani"
  "dopodomani"
  (datetime 2013 2 14 - 15)
  
  "l'altro ieri"
  "altroieri"
  (datetime 2013 2 10 - 11)

  "lunedi"
  "lu."
  "lunedi 18 febbraio"
  (datetime 2013 2 18 - 19)

  "Martedì"
  (datetime 2013 2 19 - 20)

  "Mercoledì"
  "mer"
  "mer."
  "mercoledi 13 feb"
  "il 13 febbraio"
  "il 13 febbraio 2013"
  (datetime 2013 2 13 - 14)

  "giovedi"
  "gio"
  (datetime 2013 2 14 - 15)

  "venerdi"
  "venerdì"
  "ven"
  (datetime 2013 2 15 - 16)

  "sabato"
  "sab"
  "sab."
  (datetime 2013 2 16 - 17)

  "domenica"
  "dom"
  "dom."
  (datetime 2013 2 17 - 18)
  
  "questo week-end"
  (datetime 2013 2 15 18 - 18 0)

  "lunedi mattina"
  (datetime 2013 2 18 4 00 - 12 00)

  "ultima domenica"
  "domenica della scorsa settimana"
  (datetime 2013 2 10 - 11)

  "domenica 10 febbraio"
  (datetime 2019 2 10 - 11) ; with current look-forward default...

  "martedì scorso"
  (datetime 2013 2 5 - 6)

  "til 1 marzo"
  "prima di marzo"
  (datetime 2013 3 1 - 2)

  "3 marzo 2015"
  "3/3/2015"
  "3/3/15"
  "2015-3-3"
  "2015-03-03"
  (datetime 2015 3 3 - 4)

  "il 15 febbraio"
  "15/2"
  "il 15/02"
  (datetime 2013 2 15 - 16)
  
  "31/10/1974"
  "31/10/74"
  (datetime 1974 10 31 - 11 1)

  "15 febbraio al mattino"
  "mattino di 15 febbraio"
  (datetime 2013 2 15 4 - 12)

  "martedì prossimo" ; when today is Tuesday, "mardi prochain" is a week from now
  "il martedì dopo"
  (datetime 2013 2 19 - 20)

  "mercoledì prossimo" ; when today is Tuesday, "mercredi prochain" is tomorrow
  (datetime 2013 2 13 - 14)

  "mercoledi fra una settimana"
  (datetime 2013 2 20 - 21)

  "questa settimana"
  (datetime 2013 2 11 - 18)

  ;"tuesday of this week"
  ;(datetime 2013 2 12 - 13)
  
  "la settimana scorsa"
  (datetime 2013 2 4 - 11)
  
  "la settimana prossima"
  (datetime 2013 2 18 - 25)
  
  "il mese scorso"
  (datetime 2013 1 - 2)

  "il mese prossimo"
  (datetime 2013 3 - 4)
  
  "l'anno scorso"
  (datetime 2012 - 2013)
  
  "quest'anno"
  (datetime 2013 - 2014)
  
  "il prossimo anno"
  (datetime 2014 - 2015)
  
  "ultimi 2 secondi" 
  "ultimi due secondi"
  (datetime 2013 2 12 4 29 58 - 4 30 0)

  "prossimi 3 secondi"
  "prossimi tre secondi"
  (datetime 2013 2 12 4 30 01 - 04)

  "ultimi 2 minuti"
  "ultimi due minuti"
  (datetime 2013 2 12 4 28 - 30)

  "ultime 2 ore"
  "ultime due ore"
  (datetime 2013 2 12 2 - 4)

  "prossime 3 ore"
  (datetime 2013 2 12 5 - 8)

  "ultimi 2 giorni"
  (datetime 2013 2 10 - 12)
    
  "ultime due settimane"
  (datetime 2013 1 28 - 2 11)

  "prossime tre settimane"
  (datetime 2013 2 18 - 3 11)

  "ultimi due mes"
  (datetime 2012 12 1 - 2013 2 1)

  "prossimi 3 mesi"
  (datetime 2013 3 1 - 6 1)

  "ultimi 2 anni"
  (datetime 2011 - 2013)
  
  "prossimi tre anni"
  (datetime 2014 - 2017)
  
  "alle 3 di pomeriggio"
  "3:00"
  "03:00"
  "le tre di pomeriggio"
  (datetime 2013 2 12 15 - 16)

  "alle tre e un quarto"
  "3:15 di pomeriggio"
  "15:15"
  "at 15 15"
  (datetime 2013 2 12 15 15 - 16)

  "alle tre e venti"
  "3:20 di pomeriggio"
  (datetime 2013 2 12 15 20 - 21)

  "alle tre e mezzo"
  "15:30"
  "3:30 di pomeriggio"
  (datetime 2013 2 12 15 30 - 31)

  "a mezzogiorno meno un quarto"
  "Un quarto a mezzogiorno"
  "11:45 di pomeriggio"
  (datetime 2013 2 12 11 45 - 46)
  
  "alle 19:30 il venerdì 20 settembre"
  (datetime 2013 9 20 19 30 - 31)
  
)