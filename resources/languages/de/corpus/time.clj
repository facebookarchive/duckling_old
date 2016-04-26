(
  ; Context map
  ; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:reference-time (time/t -2 2013 2 12 4 30 0)
   :min (time/t -2 1900)
   :max (time/t -2 2100)}

  "jetzt"
  "genau jetzt"
  "gerade eben"
  (datetime 2013 2 12 4 30 00)

  "heute"
  "zu dieser zeit"
  (datetime 2013 2 12)

  "gestern"
  (datetime 2013 2 11)

  "morgen"
  (datetime 2013 2 13)

  "montag"
  "mo."
  "diesen montag"
  (datetime 2013 2 18 :day-of-week 1)

  "Montag, Feb 18"
  "Montag, Februar 18"
  (datetime 2013 2 18 :day-of-week 1 :day 18 :month 2)

  "dienstag"
  (datetime 2013 2 19)

  "donnerstag"
  "do"
  "do."
  (datetime 2013 2 14)

  "freitag"
  "fr."
  (datetime 2013 2 15)

  "samstag"
  "sa."
  (datetime 2013 2 16)

  "sonntag"
  "so."
  (datetime 2013 2 17)

  "1 märz"
  "erster märz"
  (datetime 2013 3 1 :day 1 :month 3)

  "märz 3"
  (datetime 2013 3 3 :day 3 :month 3)


  "märz 3 2015"
  (datetime 2015 3 3 :day 3 :month 3 :year 2015)

  "am 15ten"
  (datetime 2013 2 15 :day 15)

  "15. februar"
  "februar 15"
  "15te februar"
  "15.2."
  "am 15.2."
  "februar 15"
  (datetime 2013 2 15 :day 15 :month 2)

  "Aug 8"
  (datetime 2013 8 8 :day 8 :month 8)

  "Oktober 2014"
  (datetime 2014 10 :year 2014 :month 10)

  "31.10.1974"
  "31.10.74"
  (datetime 1974 10 31 :day 31 :month 10 :year 1974)

  "14 april 2015"
  "April 14, 2015"
  "14te April 15"
  (datetime 2015 4 14 :day 14 :month 4 :years 2015)

  "nächsten dienstag" ; when today is Tuesday, "mardi prochain" is a week from now
  (datetime 2013 2 19 :day-of-week 2)

  "übernächsten freitag"
  (datetime 2013 2 22 :day-of-week 2)

  "nächsten märz"
  (datetime 2013 3)

  "übernächsten märz"
  (datetime 2014 3)

  "Sonntag, Feb 10"
  (datetime 2013 2 10 :day-of-week 7 :day 10 :month 2)

  "Mittwoch, Feb 13"
  (datetime 2013 2 13 :day-of-week 3 :day 13 :month 2)

  "Montag, Feb 18"
  (datetime 2013 2 18 :day-of-week 1 :day 18 :month 2)

;   ;; Cycles

  "diese woche"
  "kommende woche"
  (datetime 2013 2 11 :grain :week)

  "letzte woche"
  (datetime 2013 2 4 :grain :week)

  "nächste woche"
  (datetime 2013 2 18 :grain :week)

  "letzten monat"
  (datetime 2013 1)

  "nächsten monat"
  (datetime 2013 3)

  "dieses quartal"
  (datetime 2013 1 1 :grain :quarter)

  "nächstes quartal"
  (datetime 2013 4 1 :grain :quarter)

  "drittes quartal"
  (datetime 2013 7 1 :grain :quarter)

  "4tes quartal 2018"
  (datetime 2018 10 1 :grain :quarter)

  "letztes jahr"
  (datetime 2012)

  "dieses jahr"
  (datetime 2013)

  "nächstes jahr"
  (datetime 2014)

  "letzten sonntag"
  "sonntag der letzten woche"
  "sonntag letzte woche"
  (datetime 2013 2 10 :day-of-week 7)

  "letzten dienstag"
  (datetime 2013 2 5 :day-of-week 2)

  "nächsten dienstag" ; when today is Tuesday, "mardi prochain" is a week from now
  (datetime 2013 2 19 :day-of-week 2)

  "nächsten mittwoch" ; when today is Tuesday, "mercredi prochain" is tomorrow
  (datetime 2013 2 13 :day-of-week 3)

  "mittwoch der nächsten woche"
  "mittwoch nächste woche"
  "mittwoch nach dem nächsten"
  (datetime 2013 2 20 :day-of-week 3)

  "freitag nach dem nächsten"
  (datetime 2013 2 22 :day-of-week 5)

  "montag dieser woche"
  (datetime 2013 2 11 :day-of-week 1)

  "dienstag dieser woche"
  (datetime 2013 2 12 :day-of-week 2)

  "mittwoch dieser woche"
  (datetime 2013 2 13 :day-of-week 3)

  "übermorgen"
  (datetime 2013 2 14)

  "vorgestern"
  (datetime 2013 2 10)

  "letzter montag im märz"
  (datetime 2013 3 25 :day-of-week 1)

  "letzter sonntag im märz 2014"
  (datetime 2014 3 30 :day-of-week 7)

  "dritter tag im oktober"
  (datetime 2013 10 3)

  "erste woche im oktober 2014"
  (datetime 2014 10 6 :grain :week)

  "letzter tag im oktober 2015"
  (datetime 2015 10 31)

  "letzte woche im september 2014"
  (datetime 2014 9 22 :grain :week)


  ;; nth of
  "erster dienstag im oktober"
  (datetime 2013 10 1)

  "dritter dienstag im september 2014"
  (datetime 2014 9 16)

  "erster mittwoch im oktober 2014"
  (datetime 2014 10 1)

  "zweiter mittwoch im oktober 2014"
  (datetime 2014 10 8)

  ;; nth after

  "dritter dienstag nach weihnachten 2014"
  (datetime 2015 1 13)


  ;; Hours

  "um 3"
  "um 3 in der früh"
  "3 uhr"
  "um drei"
  (datetime 2013 2 13 3)

  "3:18"
  "3:18 früh"
  (datetime 2013 2 12 3 18)

  "um 3 am nachmittag"
  "um 15"
  "um 15 uhr"
  "15 uhr"
  (datetime 2013 2 12 15 :hour 3 :meridiem :pm)

  "zirka 15 uhr" ;; FIXME pm overrides precision
  "zirka 3 uhr am nachmittag"
  "um ungefähr 15 uhr"
  (datetime 2013 2 12 15 :hour 3 :meridiem :pm) ;; :precision "approximate"

  "pünktlich um 17 uhr morgen" ;; FIXME precision is lost
  (datetime 2013 2 13 17 :hour 5 :meridiem :pm) ;; :precision "exact"

  "um viertel nach 3"
  "viertel nach drei Uhr"
  "3:15 am nachmittag"
  "15:15"
  (datetime 2013 2 12 15 15 :hour 3 :minute 15 :meridiem :pm)

  "um 20 nach 3"
  "3:20 am nachmittag"
  "3:20 uhr nachmittags"
  "zwanzig nach 3"
  "15:20"
  (datetime 2013 2 12 15 20 :hour 3 :minute 20 :meridiem :pm)

  "um halb 4"
  "halb vier uhr nachmittags"
  "halb vier am nachmittag"
  "15:30"
  (datetime 2013 2 12 15 30 :hour 3 :minute 30 :meridiem :pm)

  "3:30"
  "halb vier"
  (datetime 2013 2 12 15 30 :hour 3 :minute 30)

  "viertel vor 12"
  "11:45"
  "15 minuten vor 12" ; Ambiguous with interval
  (datetime 2013 2 12 11 45 :hour 11 :minute 45)

  "8 uhr am abend"
  "heute abend um 20 Uhr"
  "heute um 20:00"
  (datetime 2013 2 12 20)

  ;; Mixing date and time

  "um 19:30 am fr, 20. Sept."
  (datetime 2013 9 20 19 30 :hour 7 :minute 30 :meridiem :pm)

  "am samstag um 9 Uhr"
  (datetime 2013 2 16 9 :day-of-week 6 :hour 9 :meridiem :am)

  "Fr, 18. Juli 2014 07:00 abends"
  (datetime 2014 7 18 19 0 :day-of-week 5 :hour 7 :meridiem :pm)


; ;; Involving periods

  "in einer sekunde"
  (datetime 2013 2 12 4 30 1)

  "in einer minute"
  (datetime 2013 2 12 4 31 0)

  "in 2 minuten"
  (datetime 2013 2 12 4 32 0)

  "in 60 minuten"
  (datetime 2013 2 12 5 30 0)

  "in einer halben stunde"
  "in 30 minuten"
  (datetime 2013 2 12 5 0 0)

  "in 2.5 stunden"
  "in zweieinhalb stunden"
  (datetime 2013 2 12 7 0 0)

  "in einer stunde"
  (datetime 2013 2 12 5 30)

  "in zwei stunden"
  (datetime 2013 2 12 6 30)

  "in ein paar stunden"
  (datetime 2013 2 12 7 30)

  "in 24 stunden"
  (datetime 2013 2 13 4 30)

  "morgen"
  (datetime 2013 2 13)

  "in 3 Jahren"
  (datetime 2016 2)

  "in 7 tagen"
  (datetime 2013 2 19 4)

  "in 1 woche"
  "in einer woche"
  (datetime 2013 2 19)

  "in zirka einer halben stunde" ;; FIXME precision is lost
  (datetime 2013 2 12 5 0 0) ;; :precision "approximate"

  "vor 7 tagen"
  (datetime 2013 2 5 4)

  "vor 14 tagen"
  "vor zwei wochen"
  (datetime 2013 1 29 4)

  "vor einer woche"
  (datetime 2013 2 5)

  "vor drei wochen"
  (datetime 2013 1 22)

  "vor drei monaten"
  (datetime 2012 11 12)

  "vor zwei jahren"
  (datetime 2011 2)

  "in 7 tagen"
  (datetime 2013 2 19 4)

  "ein jahr nach weihnachten"
  (datetime 2013 12) ; resolves as after last Xmas...


  ; Seasons

  "diesen sommer"
  (datetime-interval [2013 6 21] [2013 9 24])

  "diesen winter"
  (datetime-interval [2012 12 21] [2013 3 21])

  ; US holidays (http://www.timeanddate.com/holidays/us/)

  "Weihnachten"
  "Weihnachtstag"
  (datetime 2013 12 25)

  "Silvester"
  (datetime 2013 12 31)

  "Neujahrstag"
  "Neujahr"
  (datetime 2014 1 1)

  "Valentinstag"
  (datetime 2013 2 14)

  "Muttertag"
  (datetime 2013 5 12)

  "Vatertag"
  (datetime 2013 6 16)

  "Tag der Deutschen Einheit"
  "3. Oktober"
  (datetime 2013 10 3)

  "Halloween"
  (datetime 2013 10 31)

  "Allerheiligen"
  (datetime 2013 11 1)

  "Nikolaus"
  "Nikolaustag"
  (datetime 2013 12 06)

  ; Part of day (morning, afternoon...)

  "heute abend"
  "am abend"
  (datetime-interval [2013 2 12 18] [2013 2 13 00])

  "morgen abend"
  ;"Mittwoch abend"
  (datetime-interval [2013 2 13 18] [2013 2 14 00])

  "morgen mittag"
  "morgen zu mittag"
  (datetime-interval [2013 2 13 12] [2013 2 13 14])

  "gestern abend"
  (datetime-interval [2013 2 11 18] [2013 2 12 00])

  "dieses wochenende"
  (datetime-interval [2013 2 15 18] [2013 2 18 00])

  "montag morgen"
  (datetime-interval [2013 2 18 4] [2013 2 18 12])

  "morgens am 15. februar"
  "15. februar morgens"
  "am morgen des 15. februar"
  (datetime-interval [2013 2 15 4] [2013 2 15 12])


  ; Intervals involving cycles

  "in den letzten 2 sekunden"
  "die letzten zwei sekunden"
  (datetime-interval [2013 2 12 4 29 58] [2013 2 12 4 30 00])

  "in den nächsten 3 sekunden"
  "die nächsten drei sekunden"
  (datetime-interval [2013 2 12 4 30 01] [2013 2 12 4 30 04])

  "in den letzten 2 minuten"
  "die letzten zwei minuten"
  (datetime-interval [2013 2 12 4 28] [2013 2 12 4 30])

  "in den nächsten 3 minuten"
  "die nächsten drei minuten"
  (datetime-interval [2013 2 12 4 31] [2013 2 12 4 34])

  "in der letzten stunde"
  "in der vergangenen stunde"
  (datetime-interval [2013 2 12 3] [2013 2 12 4])

  "in den nächsten 3 stunden"
  "die nächsten drei stunden"
  (datetime-interval [2013 2 12 5] [2013 2 12 8])

  "in den letzten 2 tagen"
  "die letzten zwei tage"
  "die vergangenen zwei tage"
  (datetime-interval [2013 2 10] [2013 2 12])

  "in den nächsten 3 tagen"
  "die nächsten drei tage"
  "in den kommenden drei tagen"
  (datetime-interval [2013 2 13] [2013 2 16])

  "in den nächsten tagen"
  "in den kommenden tagen"
  (datetime-interval [2013 2 13] [2013 2 16])

  "in den letzten 2 wochen"
  "letzte zwei wochen"
  "vergangenen 2 wochen"
  (datetime-interval [2013 1 28 :grain :week] [2013 2 11 :grain :week])

  "in den nächsten 3 wochen"
  "nächste drei wochen"
  "in den kommenden drei wochen"
  (datetime-interval [2013 2 18 :grain :week] [2013 3 11 :grain :week])

  "in den letzten 2 monaten"
  "letzte zwei monate"
  "in den vergangenen zwei monaten"
  (datetime-interval [2012 12] [2013 02])

  "in den nächsten 3 monaten"
  "nächste drei monate"
  "in den kommenden drei monaten"
  (datetime-interval [2013 3] [2013 6])

  "in den letzten 2 jahren"
  "letzten zwei jahre"
  "in den vergangenen zwei jahren"
  (datetime-interval [2011] [2013])

  "in den nächsten 3 jahren"
  "in den kommenden drei jahren"
  "nächste drei jahre"
  (datetime-interval [2014] [2017])


  ; Explicit intervals

  "13. - 15. Juli"
  "13ter bis 15ter Juli"
  "13 bis 15 Juli"
  "13 - 15 Juli"
  "Juli 13 - Juli 15"
  (datetime-interval [2013 7 13] [2013 7 16])

  "Aug 8 - Aug 12"
  (datetime-interval [2013 8 8] [2013 8 13])

  "9:30 - 11:00"
  (datetime-interval [2013 2 12 9 30] [2013 2 12 11 1])

  "am Donnerstag von 9:30 - 11:00 Uhr"
  "zwischen 9:30 und 11:00 am Donnerstag"
  "Donnerstags 9:30 - 11:00"
  "nach 9:30 aber vor 11:00 am Donnerstag"
  "Donnerstag von 9:30 bis 11:00"
  (datetime-interval [2013 2 14 9 30] [2013 2 14 11 1])

  "Donnerstag Vormittag von 9 bis 11"
  (datetime-interval [2013 2 14 9] [2013 2 14 12])

  "11:30-1:30" ; go train this rule!
  "11:30-1:30"
  "11:30-1:30"
  "11:30-1:30"
  "11:30-1:30"
  "11:30-1:30"
  "11:30-1:30"
  (datetime-interval [2013 2 12 11 30] [2013 2 12 13 31])

  "1:30 am Sa, 21. Sept"
  (datetime 2013 9 21 13 30)

  "binnen 2 wochen"
  "innerhalb von 2 wochen"
  (datetime-interval [2013 2 12 4 30 0] [2013 2 26])

  "bis 2:00 Uhr nachmittag"
  (datetime 2013 2 12 14 0 :direction :before)

  "bis 2:00 Uhr nachmittag"
  (datetime-interval [2013 2 12 4 30 0] [2013 2 12 14])

  "bis zum ende des tages"
  (datetime-interval [2013 2 12 4 30 0] [2013 2 13 0])

  "bis zum ende des monats"
  "bis ende des laufenden monats"
  (datetime-interval [2013 2 12 4 30 0] [2013 3 1 0])

  ; Timezones

  "16 Uhr CET"
  (datetime 2013 2 12 16 :hour 4 :meridiem :pm :timezone "CET")

  "donnerstag 8:00 GMT"
  (datetime 2013 2 14 8 00 :timezone "GMT")

  ;; Bookface tests
  "heute um 2 Uhr"
  "um 2"
  (datetime 2013 2 12 14)

  "25.4. um 4"
  (datetime 2013 4 25 16 0)

  "morgen um 3 Uhr"
  "morgen um 3"
  (datetime 2013 2 13 15)

  "nach 2 Uhr"
  "nach 14h"
  "nach 2"
  (datetime 2013 2 12 14 :direction :after)


  "vor 11 uhr"
  "vor 11h vormittags"
  "vor 11 am vormittag"
  (datetime 2013 2 12 11 :direction :before)

  
  "morgen bis 11 uhr"
  "morgen vor 11 uhr" ;; FIXME this is actually not ambiguous. it's midnight to 11 am
  (datetime 2013 2 13 11 :direction :before)

  "am nachmittag"
  (datetime-interval [2013 2 12 12] [2013 2 12 19])

  "um 1:30 am nachmittag"
  "nachmittags um 1:30"
  "13:30"
  (datetime 2013 2 12 13 30)

  "in 15 minuten"
  (datetime 2013 2 12 4 45 0)

  "nach dem mittagessen"
  (datetime-interval [2013 2 12 13] [2013 2 12 17])

  "10:30"
  (datetime 2013 2 12 10 30)

  "in der früh"
  "am morgen" ;; how should we deal with fb mornings?
  (datetime-interval [2013 2 12 4] [2013 2 12 12])

  "nächsten montag"
  "kommenden montag"
  (datetime 2013 2 18 :day-of-week 1)
)