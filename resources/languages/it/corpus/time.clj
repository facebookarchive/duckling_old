(
  ; Context map
  ; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:reference-time (time/t -2 2013 2 12 4 30 0)}

  "subito"
  "immediatamente"
  "in questo momento"
  "ora"
  "adesso"
  (datetime 2013 2 12 4 30 00)

  "di oggi"
  "oggi"
  "in giornata"
  (datetime 2013 2 12)

  "ieri"
  (datetime 2013 2 11)

  "domani"
  (datetime 2013 2 13)

  "Il giorno dopo domani"
  "dopodomani"
  (datetime 2013 2 14)

  "Lunedì 18 febbraio"
  (datetime 2013 2 18 :day-of-week 1 :day 18 :month 2)

  "martedì"
  "Martedì 19"
  "mar 19"
  "il 19"
  (datetime 2013 2 19)

  "l'altro ieri"
  "altroieri"
  (datetime 2013 2 10)

  "lunedi"
  "lun"
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
  "primo marzo"
  "primo di marzo"
  "il 1º marzo"
  (datetime 2013 3 1 :day 1 :month 3)

  "prima di marzo"
  (datetime 2013 3)

  "le idi di marzo"
  "idi di marzo"
  (datetime 2013 3 15 :month 3)

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

  "ottobre 2014"
  (datetime 2014 10 :year 2014 :month 10)

   ;; Cycles

  "l'ultima ora"
  "nell'ultima ora"
  (datetime 2013 2 12 3)

  "questa settimana"
  (datetime 2013 2 11 :grain :week)

  "la settimana scorsa"
  "la scorsa settimana"
  "nella scorsa settimana"
  "della settimana scorsa"
  (datetime 2013 2 4 :grain :week)

  "la settimana prossima"
  "la prossima settimana"
  "nella prossima settimana"
  "settimana prossima"
  "prossima settimana"
  (datetime 2013 2 18 :grain :week)

  "il mese scorso"
  "nel mese scorso"
  "nel mese passato"
  "lo scorso mese"
  "dello scorso mese"
  (datetime 2013 1)

  "il mese prossimo"
  "il prossimo mese"
  (datetime 2013 3)

  "questo trimestre"
  (datetime 2013 1 1 :grain :quarter)

  "il prossimo trimestre"
  "nel prossimo trimestre"
  (datetime 2013 4 1 :grain :quarter)

  "terzo trimestre"
  "il terzo trimestre"
  (datetime 2013 7 1 :grain :quarter)

  "quarto trimestre 2018"
  "il quarto trimestre 2018"
  "del quarto trimestre 2018"
  (datetime 2018 10 1 :grain :quarter)

  "l'anno scorso"
  (datetime 2012)

  "quest'anno"
  (datetime 2013)

  "il prossimo anno"
  (datetime 2014)

  "ultima domenica"
  ;"domenica della scorsa settimana"
  (datetime 2013 2 10 :day-of-week 7)

  "lunedì di questa settimana"
  (datetime 2013 2 11 :day-of-week 1)

  "martedì di questa settimana"
  (datetime 2013 2 12 :day-of-week 2)

  "mercoledì di questa settimana"
  (datetime 2013 2 13 :day-of-week 3)

  "dopo domani alle 17"
  "dopodomani alle 5 del pomeriggio"
  (datetime 2013 2 14 17)

  "ultimo lunedì di marzo"
  (datetime 2013 3 25 :day-of-week 1)

  "ultima domenica di marzo 2014"
  (datetime 2014 3 30 :day-of-week 7)

  "il terzo giorno di ottobre"
  (datetime 2013 10 3)

  "prima settimana di ottobre 2014"
  (datetime 2014 10 6 :grain :week)

  "la settimana del 6 ottobre"
  "la settimana del 7 ott"
  (datetime 2013 10 7 :grain :week)

  "il weekend del 15"
  "il we del 15 febbraio"
  (datetime-interval [2013 2 15 18] [2013 2 18 00])

  "il week-end del 10 aprile"
  (datetime-interval [2013 4 12 18] [2013 4 15 00])

  "l'ultimo giorno di ottobre 2015"
  "l'ultimo giorno dell'ottobre 2015"
  (datetime 2015 10 31)

  "l'ultima settimana di settembre 2014"
  (datetime 2014 9 22 :grain :week)

  "tra un'ora"
  "tra 1 ora"
  (datetime 2013 2 12 5 30)

  "tra un quarto d'ora"
  (datetime 2013 2 12 4 45 00)

  "tra mezz'ora"
  (datetime 2013 2 12 5 00 00)

  "tra tre quarti d'ora"
  (datetime 2013 2 12 5 15 00)

  ;; nth of
  "primo martedì di ottobre"
  "primo martedì in ottobre"
  "1° martedì del mese di ottobre"
  "1º martedì del mese di ottobre"
  (datetime 2013 10 1)

  "terzo martedì di settembre 2014"
  (datetime 2014 9 16)

  "primo mercoledì di ottobre 2014"
  (datetime 2014 10 1)

  "secondo mercoledì di ottobre 2014"
  (datetime 2014 10 8)

  ;; nth after

  "terzo martedì dopo natale 2014"
  (datetime 2015 1 13)

  "il mese dopo natale 2015"
  (datetime 2016 1)

  ;; Hours

  "alle 3 di pomeriggio"
  "le tre di pomeriggio"
  "alle 3 del pomeriggio"
  "le tre del pomeriggio"
  (datetime 2013 2 12 15)

  "circa alle 3 del pomeriggio" ;; FIXME pm overrides precision
  (datetime 2013 2 12 15 :hour 3) ;; :precision "approximate"

  "per le 15"
  "verso le 15"
  (datetime 2013 2 12 15) ;; :precision "approximate"

  "verso sera"
  (datetime-interval [2013 2 12 18] [2013 2 13 00]) ;; :precision "approximate"

  "3:00"
  "03:00"
  (datetime 2013 2 13 3 0 :hour 3 :minute 0)

  "15:15"
  (datetime 2013 2 12 15 15 :hour 15 :minute 15)

  "3:15 di pomeriggio"
  "3:15 del pomeriggio"
  "3 e un quarto di pomeriggio"
  "tre e un quarto di pomeriggio"
  (datetime 2013 2 12 15 15)

  "alle tre e venti di pomeriggio"
  "alle tre e venti del pomeriggio"
  "3:20 di pomeriggio"
  "3:20 del pomeriggio"
  "15:20 del pomeriggio"
  (datetime 2013 2 12 15 20)

  "alle tre e venti"
  "tre e 20"
  "3 e 20"
  "3:20"
  "3 20"
  (datetime 2013 2 13 3 20 :hour 3 :minute 20)

  "15:30"
  (datetime 2013 2 12 15 30 :hour 15 :minute 30)

  "a mezzogiorno meno un quarto"
  "mezzogiorno meno un quarto"
  "un quarto a mezzogiorno"
  "11:45 del mattino"
  (datetime 2013 2 12 11 45 :hour 11 :minute 45)

  "alle 3 del mattino"
  (datetime 2013 2 13 3 :hour 3)

  "alle 19:30 di venerdì 20 settembre"
  "alle 19:30 venerdì 20 settembre"
  "venerdì 20 settembre alle 19:30"
  "il 20 settembre alle 19:30"
  (datetime 2013 9 20 19 30 :hour 19 :minute 30 :day-of-week 5 :day 20 :month 9)

  ;; Involving periods   ; look for grain-after-shift

  "questo week-end"
  "questo fine settimana"
  "questo finesettimana"
  (datetime-interval [2013 2 15 18] [2013 2 18 00])

  "lunedi mattina"
  (datetime-interval [2013 2 18 4] [2013 2 18 12])

  ; Part of day (morning, afternoon...)

  "15 febbraio al mattino"
  "mattino di 15 febbraio"
  (datetime-interval [2013 2 15 4] [2013 2 15 12])

  "8 di stasera"
  "8 della sera"
  (datetime 2013 2 12 20)

  ;; Mixing date and time

  "venerdì 20 settembre alle 7:30 del pomeriggio"
  (datetime 2013 9 20 19 30 :hour 7 :minute 30 :meridiem :pm)

  "alle 9 di sabato"
  "sabato alle 9"
  (datetime 2013 2 16 9 :day-of-week 6 :hour 9 :meridiem :am)

  ; Seasons

  "quest'estate"
  "questa estate"
  "in estate"
  (datetime-interval [2013 6 21] [2013 9 24])

  "quest'inverno"
  "questo inverno"
  "in inverno"
  (datetime-interval [2012 12 21] [2013 3 21])

  "il prossimo autunno"
  (datetime-interval [2014 9 23] [2014 12 22])

  ; IT holidays

  "natale"
  "il giorno di natale"
  (datetime 2013 12 25)

  "vigilia di natale"
  "alla vigilia"
  "la vigilia"
  (datetime 2013 12 24)

  "vigilia di capodanno"
  "san silvestro"
  (datetime 2013 12 31)

  "notte di san silvestro"
  (datetime-interval [2014 1 1 0] [2014 1 1 4])

  "capodanno"
  "primo dell'anno"
  (datetime 2014 1 1)

  "epifania"
  "befana"
  (datetime 2014 1 6)

  "san valentino"
  "festa degli innamorati"
  (datetime 2013 2 14)

  "festa del papà"
  "festa del papa"
  "festa di san giuseppe"
  "san giuseppe"
  (datetime 2013 3 19)

  "anniversario della liberazione"
  "la liberazione"
  "alla liberazione"
  (datetime 2013 4 25)

  "festa del lavoro"
  "festa dei lavoratori"
  "giorno dei lavoratori"
  "primo maggio"
  (datetime 2013 5 1)

  "festa della mamma"
  (datetime 2013 5 12)

  "festa della repubblica"
  "la repubblica"
  "repubblica"
  (datetime 2013 6 2)

  "ferragosto"
  "assunzione"
  (datetime 2013 8 15)

  "halloween"
  (datetime 2013 10 31)

  "tutti i santi"
  "ognissanti"
  "festa dei santi"
  "il giorno dei santi"
  (datetime 2013 11 1)

  "giorno dei morti"
  "commemorazione dei defunti"
  (datetime 2013 11 2)

  "ai morti alle 2"
  (datetime 2013 11 2 2)

  "immacolata"
  "immacolata concezione"
  (datetime 2013 12 8)

  "all'immacolata alle 18"
  (datetime 2013 12 8 18)

  "santo stefano"
  (datetime 2013 12 26)

  ; Part of day (morning, afternoon...)

  "questa sera"
  "sta sera"
  "stasera"
  "nella sera"
  "la sera"
  "alla sera"
  "in serata"
  "la serata"
  "nella serata"
  (datetime-interval [2013 2 12 18] [2013 2 13 00])

  "domani mattina"
  "domattina"
  (datetime-interval [2013 2 13 4] [2013 2 13 12])

  "in settimana"
  "per la settimana"
  (datetime-interval [2013 2 12 4 30 00] [2013 2 18])

  "stanotte"
  "nella notte"
  "in nottata"
  (datetime-interval [2013 2 13 0] [2013 2 13 04])

  "ultimo weekend"
  (datetime-interval [2013 2 8 18] [2013 2 11 00])

  "domani in serata"
  "domani sera"
  "nella serata di domani"
  (datetime-interval [2013 2 13 18] [2013 2 14 00])

  "domani notte"
  "domani in nottata"
  "nella nottata di domani"
  "nella notte di domani"
  (datetime-interval [2013 2 14 00] [2013 2 14 04])

  "domani a pranzo"
  (datetime-interval [2013 2 13 12] [2013 2 13 14])

  "ieri sera"
  (datetime-interval [2013 2 11 18] [2013 2 12 00])

  "questo weekend"
  "questo week-end"
  (datetime-interval [2013 2 15 18] [2013 2 18 00])

  "lunedì mattina"
  "nella mattinata di lunedì"
  "lunedì in mattinata"
  "lunedì nella mattina"
  (datetime-interval [2013 2 18 4] [2013 2 18 12])

  "il 15 febbraio in mattinata"
  "mattina del 15 febbraio"
  "15 febbraio mattina"
  (datetime-interval [2013 2 15 4] [2013 2 15 12])

  ; Intervals involving cycles

  "gli ultimi 2 secondi"
  "gli ultimi due secondi"
  "i 2 secondi passati"
  "i due secondi passati"
  (datetime-interval [2013 2 12 4 29 58] [2013 2 12 4 30 00])

  "i prossimi 3 secondi"
  "i prossimi tre secondi"
  "nei prossimi tre secondi"
  (datetime-interval [2013 2 12 4 30 01] [2013 2 12 4 30 04])

  "gli ultimi 2 minuti"
  "gli ultimi due minuti"
  "i 2 minuti passati"
  "i due minuti passati"
  (datetime-interval [2013 2 12 4 28] [2013 2 12 4 30])

  "i prossimi 3 minuti"
  "nei prossimi 3 minuti"
  "i prossimi tre minuti"
  (datetime-interval [2013 2 12 4 31] [2013 2 12 4 34])

  "le ultime 2 ore"
  "le ultime due ore"
  "nelle ultime due ore"
  "le scorse due ore"
  "le due ore scorse"
  "le scorse 2 ore"
  "le 2 ore scorse"
  "nelle 2 ore scorse"
  (datetime-interval [2013 2 12 2] [2013 2 12 4])

  "le ultime 24 ore"
  "le ultime ventiquattro ore"
  "le 24 ore passate"
  "nelle 24 ore scorse"
  "le ventiquattro ore passate"
  (datetime-interval [2013 2 11 4] [2013 2 12 4])

  "le prossime 3 ore"
  "prossime tre ore"
  "nelle prossime 3 ore"
  (datetime-interval [2013 2 12 5] [2013 2 12 8])

  "gli ultimi 2 giorni"
  "gli ultimi due giorni"
  "negli ultimi 2 giorni"
  "i 2 giorni passati"
  "i due giorni passati"
  "nei due giorni passati"
  "gli scorsi due giorni"
  "i 2 giorni scorsi"
  "i due giorni scorsi"
  (datetime-interval [2013 2 10] [2013 2 12])

  "prossimi 3 giorni"
  "i prossimi tre giorni"
  "nei prossimi 3 giorni"
  (datetime-interval [2013 2 13] [2013 2 16])

  "i prossimi giorni"
  "nei prossimi giorni"
  "prossimi giorni"
  (datetime-interval [2013 2 13] [2013 2 16])

  "le ultime 2 settimane"
  "le ultime due settimane"
  "le 2 ultime settimane"
  "le due ultime settimane"
  "nelle 2 ultime settimane"
  (datetime-interval [2013 1 28 :grain :week] [2013 2 11 :grain :week])

  "prossime 3 settimane"
  "le prossime tre settimane"
  "le 3 prossime settimane"
  "nelle prossime 3 settimane"
  "le tre prossime settimane"
  (datetime-interval [2013 2 18 :grain :week] [2013 3 11 :grain :week])

  "gli ultimi 2 mesi"
  "gli ultimi due mesi"
  "i 2 mesi passati"
  "nei 2 mesi passati"
  "i due mesi passati"
  "i due mesi scorsi"
  "i 2 mesi scorsi"
  "negli scorsi due mesi"
  "gli scorsi due mesi"
  "gli scorsi 2 mesi"
  (datetime-interval [2012 12] [2013 02])

  "i prossimi 3 mesi"
  "i prossimi tre mesi"
  "prossimi 3 mesi"
  "i 3 prossimi mesi"
  "i tre prossimi mesi"
  "nei prossimi tre mesi"
  (datetime-interval [2013 3] [2013 6])

  "gli ultimi 2 anni"
  "gli ultimi due anni"
  "negli ultimi 2 anni"
  "i 2 anni passati"
  "i due anni passati"
  "i 2 anni scorsi"
  "i due anni scorsi"
  "gli scorsi due anni"
  "gli scorsi 2 anni"
  (datetime-interval [2011] [2013])

  "i prossimi 3 anni"
  "i prossimi tre anni"
  "nei tre prossimi anni"
  (datetime-interval [2014] [2017])

  ; Explicit intervals

  "13-15 luglio"
  "dal 13 al 15 luglio"
  "tra il 13 e il 15 luglio"
  "tra 13 e 15 luglio"
  "dal tredici al quindici luglio"
  "13 luglio - 15 luglio"
  (datetime-interval [2013 7 13] [2013 7 16])

  "dal 3 al 5"
  "tra il 3 e il 5"
  "dal tre al cinque"
  (datetime-interval [2013 3 3] [2013 3 6])

  "8 ago - 12 ago"
  (datetime-interval [2013 8 8] [2013 8 13])

  "da domani a giovedì"
  "da domani a dopodomani"
  (datetime-interval [2013 2 13] [2013 2 15])

  "9:30 - 11:00"
  (datetime-interval [2013 2 12 9 30] [2013 2 12 11 1])

  "dalle 9:30 alle 11:00 di giovedì"
  "tra le 9:30 e le 11:00 di giovedì"
  "9:30 - 11:00 giovedì"
  "giovedì dalle 9:30 alle 11:00"
  "giovedì tra le 9:30 e le 11:00"
  (datetime-interval [2013 2 14 9 30] [2013 2 14 11 1])

  "dalle 9 alle 11 di giovedì"
  "tra le 9 e le 11 di giovedì"
  "9 - 11 giovedì"
  "giovedì dalle nove alle undici"
  "giovedì tra le nove e le undici"
  (datetime-interval [2013 2 14 9] [2013 2 14 12])

  "dalle tre all'una di giovedì"
  (datetime-interval [2013 2 14 3] [2013 2 14 14])

  "dalla mezzanotte alle 2"
  (datetime-interval [2013 2 13 0] [2013 2 13 3])

  "domani dalle 15:00 alle 17:00"
  (datetime-interval [2013 2 13 15 00] [2013 2 13 17 01])

  "11:30-13:30" ; go train this rule!
  "11:30-13:30"
  "11:30-13:30"
  "11:30-13:30"
  "11:30-13:30"
  "11:30-13:30"
  "11:30-13:30"
  (datetime-interval [2013 2 12 11 30] [2013 2 12 13 31])

  "13:30 di sabato 21 settembre"
  "13:30 del 21 settembre"
  (datetime 2013 9 21 13 30)

  "in due settimane"
  "per due settimane"
  (datetime-interval [2013 2 12 4 30 0] [2013 2 26])

  "fino alle 14:00"
  (datetime-interval [2013 2 12 4 30 0] [2013 2 12 14 00])

  "entro le 14:00"
  (datetime 2013 2 12 14 0 :direction :before)

  "entro la fine del mese"
  (datetime 2013 3 :direction :before)

  "entro la fine dell'anno"
  (datetime 2014 :direction :before)

  "fino alla fine del mese"
  (datetime-interval [2013 2 12 4 30 0] [2013 3 1 0])

  "fino alla fine dell'anno"
  (datetime-interval [2013 2 12 4 30 0] [2014 1 1])

  ; Timezones

  "4 CET"
  (datetime 2013 2 12 4 :hour 4 :timezone "CET")

  "16 CET"
  (datetime 2013 2 12 16 :hour 16 :timezone "CET")

  "giovedì alle 8:00 GMT"
  (datetime 2013 2 14 8 00 :timezone "GMT")

  ;; Bookface tests
  "domani alle 14"
  (datetime 2013 2 13 14)

  "alle 14"
  "alle 2 del pomeriggio"
  (datetime 2013 2 12 14)

  "25/4 alle 16:00"
  (datetime 2013 4 25 16 0)

  "3 del pomeriggio di domani"
  "15 del pomeriggio di domani"
  (datetime 2013 2 13 15)

  "dopo le 14"
  "dalle 14"
  (datetime 2013 2 12 14 :direction :after)

  "dalla mezzanotte"
  (datetime 2013 2 13 0 :direction :after)

  "domani dopo le 14"
  "domani dalle 14"
  (datetime 2013 2 13 14 :direction :after)

  "prima delle 11"
  (datetime 2013 2 12 11 :direction :before)

  "dopodomani prima delle 11"
  (datetime 2013 2 14 11 :direction :before)

  "giovedì entro mezzogiorno"
  (datetime 2013 2 14 12 :direction :before)

  "da dopodomani"
  "da giovedì"
  (datetime 2013 2 14 :direction :after)

  "dal primo"
  (datetime 2013 3 1 :direction :after)

  "dal 20"
  (datetime 2013 2 20 :direction :after)

  "entro il 15"
  (datetime 2013 2 15 :direction :before)

  "prima del 20 aprile"
  (datetime 2013 4 20 :direction :before)

  "nel pomeriggio"
  (datetime-interval [2013 2 12 12] [2013 2 12 19])

  "alle 13:30"
  "13:30"
  "1:30 del pomeriggio"
  (datetime 2013 2 12 13 30)

  "in 15 minuti"
  (datetime-interval [2013 2 12 4 30 0] [2013 2 12 4 45 0])

  "tra 15 minuti"
  (datetime 2013 2 12 4 45 0)

  "10:30"
  (datetime 2013 2 12 10 30)

  "mattina"
  "mattinata"
  "mattino"
  (datetime-interval [2013 2 12 4] [2013 2 12 12])

  "prossimo lunedì"
  (datetime 2013 2 25 :day-of-week 1)

  "alle 12"
  "a mezzogiorno"
  (datetime 2013 2 12 12)

  "alle 24"
  "a mezzanotte"
  (datetime 2013 2 13 0)

  "marzo"
  "in marzo"
  (datetime 2013 3)
)
