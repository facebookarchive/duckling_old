(
  ; Context map
  ; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:reference-time (time/t -2 2013 2 12 4 30 0)
   :min (time/t -2 1900)
   :max (time/t -2 2100)}

  "nu"
  "lige nu"
  (datetime 2013 2 12 4 30 00)

  "i dag"
  (datetime 2013 2 12)

  "i går"
  (datetime 2013 2 11)

  "i morgen"
  (datetime 2013 2 13)

  "mandag"
  "man."
  "på mandag"
  (datetime 2013 2 18 :day-of-week 1)

  "Mandag den 18. februar"
  "Man, 18 februar"
  (datetime 2013 2 18 :day-of-week 1 :day 18 :month 2)

  "tirsdag"
  (datetime 2013 2 19)

  "torsdag"
  "tors"
  "tors."
  (datetime 2013 2 14)

  "fredag"
  "fre"
  "fre."
  (datetime 2013 2 15)

  "lørdag"
  "lør"
  "lør."
  (datetime 2013 2 16)

  "søndag"
  "søn"
  "søn."
  (datetime 2013 2 17)

  "Den første marts"
  "1. marts"
  "Den 1. marts"
  (datetime 2013 3 1 :day 1 :month 3)

  "3 marts"
  "den tredje marts"
  "den 3. marts"
  (datetime 2013 3 3 :day 3 :month 3)

  "3 marts 2015"
  "tredje marts 2015"
  "3. marts 2015"
  "3-3-2015"
  "03-03-2015"
  "3/3/2015"
  "3/3/15"
  "2015-3-3"
  "2015-03-03"
  (datetime 2015 3 3 :day 3 :month 3 :year 2015)

  "På den 15."
  "På den 15"
  "Den 15."
  "Den 15"
  (datetime 2013 2 15 :day 15)

  "den 15. februar"
  "15. februar"
  "februar 15"
  "15-02"
  "15/02"
  (datetime 2013 2 15 :day 15 :month 2)

  "8 Aug"
  (datetime 2013 8 8 :day 8 :month 8)

  "Oktober 2014"
  (datetime 2014 10 :year 2014 :month 10)

  "31/10/1974"
  "31/10/74"
  "31-10-74"
  (datetime 1974 10 31 :day 31 :month 10 :year 1974)

  "14april 2015"
  "April 14, 2015"
  "fjortende April 15"
  (datetime 2015 4 14 :day 14 :month 4 :years 2015)

  "næste tirsdag" ; when today is Tuesday, "mardi prochain" is a week from now
  (datetime 2013 2 19 :day-of-week 2)

  "næste fredag igen"
  (datetime 2013 2 22 :day-of-week 2)

  "næste marts"
  (datetime 2013 3)

  "næste marts igen"
  (datetime 2014 3)

  "Søndag, 10 feb"
  "Søndag 10 Feb"
  (datetime 2013 2 10 :day-of-week 7 :day 10 :month 2)

  "Ons, Feb13"
  "Ons feb13"
  (datetime 2013 2 13 :day-of-week 3 :day 13 :month 2)

  "Mandag, Feb 18"
  "Man, februar 18"
  (datetime 2013 2 18 :day-of-week 1 :day 18 :month 2)

;   ;; Cycles

  "denne uge"
  (datetime 2013 2 11 :grain :week)

  "sidste uge"
  (datetime 2013 2 4 :grain :week)

  "næste uge"
  (datetime 2013 2 18 :grain :week)

  "sidste måned"
  (datetime 2013 1)

  "næste måned"
  (datetime 2013 3)

  "dette kvartal"
  (datetime 2013 1 1 :grain :quarter)

  "næste kvartal"
  (datetime 2013 4 1 :grain :quarter)

  "tredje kvartal"
  "3. kvartal"
  (datetime 2013 7 1 :grain :quarter)

  "4. kvartal 2018"
  "fjerde kvartal 2018"
  (datetime 2018 10 1 :grain :quarter)

  "sidste år"
  (datetime 2012)

  "i år"
  "dette år"
  (datetime 2013)

  "næste år"
  (datetime 2014)

  "sidste søndag"
  "søndag i sidste uge"
  (datetime 2013 2 10 :day-of-week 7)

  "sidste tirsdag"
  (datetime 2013 2 5 :day-of-week 2)

  "næste tirsdag" ; when today is Tuesday, "mardi prochain" is a week from now
  (datetime 2013 2 19 :day-of-week 2)

  "næste onsdag" ; when today is Tuesday, "mercredi prochain" is tomorrow
  (datetime 2013 2 13 :day-of-week 3)

  "onsdag i næste uge"
  "onsdag næste uge"
  "næste onsdag igen"
  (datetime 2013 2 20 :day-of-week 3)

  "næste fredag igen"
  (datetime 2013 2 22 :day-of-week 5)

  "mandag i denne uge"
  (datetime 2013 2 11 :day-of-week 1)

  "tirsdag i denne uge"
  (datetime 2013 2 12 :day-of-week 2)

  "onsdag i denne uge"
  (datetime 2013 2 13 :day-of-week 3)

  "i overmorgen"
  (datetime 2013 2 14)

  "i forgårs"
  (datetime 2013 2 10)

  "sidste mandag i marts"
  "sidste mandag i Marts"
  (datetime 2013 3 25 :day-of-week 1)

  "sidste søndag i marts 2014"
  "sidste søndag i Marts 2014"
  (datetime 2014 3 30 :day-of-week 7)

  "tredje dag i oktober"
  "tredje dag i Oktober"
  (datetime 2013 10 3)

  "første uge i oktober 2014"
  "første uge i Oktober 2014"
  (datetime 2014 10 6 :grain :week)

  ;"the week of october 6th"
  ;"the week of october 7th"
  ;(datetime 2013 10 7 :grain :week)

  "sidste dag i oktober 2015"
  "sidste dag i Oktober 2015"
  (datetime 2015 10 31)

  "sidste uge i september 2014"
  "sidste uge i September 2014"
  (datetime 2014 9 22 :grain :week)


  ;; nth of
  "første tirsdag i oktober"
  "første tirsdag i Oktober"
  (datetime 2013 10 1)

  "tredje tirsdag i september 2014"
  "tredje tirsdag i September 2014"
  (datetime 2014 9 16)

  "første onsdag i oktober 2014"
  "første onsdag i Oktober 2014"
  (datetime 2014 10 1)

  "anden onsdag i oktober 2014"
  "anden onsdag i Oktober 2014"
  (datetime 2014 10 8)

  ;; Hours

  "klokken 3"
  "kl. 3"
  (datetime 2013 2 13 3)

  "3:18"
  (datetime 2013 2 13 3 18)

  "klokken 15"
  "kl. 15"
  "15h"
  (datetime 2013 2 12 15 :hour 3 :meridiem :pm)

  "ca. kl. 15" ;; FIXME pm overrides precision
  "cirka kl. 15"
  "omkring klokken 15"
  (datetime 2013 2 12 15 :hour 3 :meridiem :pm) ;; :precision "approximate"

  "imorgen klokken 17 sharp" ;; FIXME precision is lost
  "imorgen kl. 17 præcis"
  (datetime 2013 2 13 17 :hour 5 :meridiem :pm) ;; :precision "exact"

  "kvarter over 15"
  "kvart over 15"
  "15:15"
  (datetime 2013 2 12 15 15 :hour 3 :minute 15 :meridiem :pm)

  "kl. 20 over 15"
  "klokken 20 over 15"
  "kl. 15:20"
  "15:20"
  (datetime 2013 2 12 15 20 :hour 3 :minute 20 :meridiem :pm)

  "15:30"
  (datetime 2013 2 12 15 30 :hour 3 :minute 30 :meridiem :pm)

  "15:23:24"
  (datetime 2013 2 12 15 23 24 :hour 15 :minute 23 :second 24)

  "kvarter i 12"
  "kvart i 12"
  "11:45"
  (datetime 2013 2 12 11 45 :hour 11 :minute 45)

  ;; Mixing date and time

  "klokken 9 på lørdag"
  (datetime 2013 2 16 9 :day-of-week 6 :hour 9 :meridiem :am)

  "Fre, Jul 18, 2014 19:00"
  (datetime 2014 7 18 19 0 :day-of-week 5 :hour 7 :meridiem :pm)

  "kl. 19:30, Lør, 20 sep"
  (datetime 2014 9 20 19 30 :day-of-week 6 :hour 7 :minute 30 :meridiem :pm)


; ;; Involving periods

  "om 1 sekund"
  "om ét sekund"
  "om et sekund"
  "ét sekund fra nu"
  "et sekund fra nu"
  (datetime 2013 2 12 4 30 1)

  "om 1 minut"
  "om ét minut"
  "om et minut"
  (datetime 2013 2 12 4 31 0)

  "om 2 minutter"
  "om to minutter"
  "om 2 minutter mere"
  "om to minutter mere"
  "2 minutter fra nu"
  "to minutter fra nu"
  (datetime 2013 2 12 4 32 0)

  "om 60 minutter"
  (datetime 2013 2 12 5 30 0)

  "om en halv time"
  (datetime 2013 2 12 5 0 0)

  "om 2,5 time"
  "om 2 og en halv time"
  "om to og en halv time"
  (datetime 2013 2 12 7 0 0)

  "om én time"
  "om 1 time"
  "om 1t"
  (datetime 2013 2 12 5 30)

  "om et par timer"
  (datetime 2013 2 12 6 30)

  "om 24 timer"
  (datetime 2013 2 13 4 30)

  "om en dag"
  (datetime 2013 2 13 4)

  "3 år fra i dag"
  (datetime 2016 2)

  "om 7 dage"
  (datetime 2013 2 19 4)

  "om en uge"
  "om én uge"
  (datetime 2013 2 19)

  "om ca. en halv time" ;; FIXME precision is lost
  "om cirka en halv time"
  (datetime 2013 2 12 5 0 0) ;; :precision "approximate"

  "7 dage siden"
  "syv dage siden"
  (datetime 2013 2 5 4)

  "14 dage siden"
  "fjorten dage siden"
  (datetime 2013 1 29 4)

  "en uge siden"
  "én uge siden"
  "1 uge siden"
  (datetime 2013 2 5)

  "3 uger siden"
  "tre uger siden"
  (datetime 2013 1 22)

  "3 måneder siden"
  "tre måneder siden"
  (datetime 2012 11 12)

  "to år siden"
  "2 år siden"
  (datetime 2011 2)

  "1954"
  (datetime 1954)

  "et år efter juleaften"
  "ét år efter juleaften"
  (datetime 2013 12) ; resolves as after last Xmas...


  ; Seasons

  "denne sommer"
  "den her sommer"
  (datetime-interval [2013 6 21] [2013 9 24])

  "denne vinter"
  "den her vinter"
  (datetime-interval [2012 12 21] [2013 3 21])

  ; US holidays (http://www.timeanddate.com/holidays/us/)

  "1 juledag"
  "1. juledag"
  "første juledag"
  (datetime 2013 12 25)

  "nytårsaftensdag"
  "nytårsaften"
  (datetime 2013 12 31)

  "nytårsdag"
  (datetime 2014 1 1)

  ; Part of day (morning, afternoon...)

  "i aften"
  (datetime-interval [2013 2 12 18] [2013 2 13 00])

  "sidste weekend"
  (datetime-interval [2013 2 8 18] [2013 2 11 00])

  "i morgen aften"
  (datetime-interval [2013 2 13 18] [2013 2 14 00])

  "i morgen middag"
  (datetime-interval [2013 2 13 12] [2013 2 13 14])

  "i går aftes"
  (datetime-interval [2013 2 11 18] [2013 2 12 00])

  "denne weekend"
  "i weekenden"
  (datetime-interval [2013 2 15 18] [2013 2 18 00])

  "mandag morgen"
  (datetime-interval [2013 2 18 4] [2013 2 18 12])

  ; Intervals involving cycles

  "sidste 2 sekunder"
  "sidste to sekunder"
  (datetime-interval [2013 2 12 4 29 58] [2013 2 12 4 30 00])

  "næste 3 sekunder"
  "næste tre sekunder"
  (datetime-interval [2013 2 12 4 30 01] [2013 2 12 4 30 04])

  "sidste 2 minutter"
  "sidste to minutter"
  (datetime-interval [2013 2 12 4 28] [2013 2 12 4 30])

  "næste 3 minutter"
  "næste tre minutter"
  (datetime-interval [2013 2 12 4 31] [2013 2 12 4 34])

  "sidste 1 time"
  "seneste 1 time"
  (datetime-interval [2013 2 12 3] [2013 2 12 4])

  "næste 3 timer"
  "næste tre timer"
  (datetime-interval [2013 2 12 5] [2013 2 12 8])

  "sidste 2 dage"
  "sidste to dage"
  "seneste 2 dage"
  (datetime-interval [2013 2 10] [2013 2 12])

  "næste 3 dage"
  "næste tre dage"
  (datetime-interval [2013 2 13] [2013 2 16])

  "sidste 2 uger"
  "sidste to uger"
  "seneste to uger"
  (datetime-interval [2013 1 28 :grain :week] [2013 2 11 :grain :week])

  "næste 3 uger"
  "næste tre uger"
  (datetime-interval [2013 2 18 :grain :week] [2013 3 11 :grain :week])

  "sidste 2 måneder"
  "sidste to måneder"
  "seneste to måneder"
  (datetime-interval [2012 12] [2013 02])

  "næste 3 måneder"
  "næste tre måneder"
  (datetime-interval [2013 3] [2013 6])

  "sidste 2 år"
  "sidste to år"
  "seneste 2 år"
  (datetime-interval [2011] [2013])

  "næste 3 år"
  "næste tre år"
  (datetime-interval [2014] [2017])


  ; Explicit intervals

  "13-15 juli"
  "13-15 Juli"
  "13 til 15 Juli"
  "13 juli til 15 juli"
  (datetime-interval [2013 7 13] [2013 7 16])

  "8 Aug - 12 Aug"
  "8 Aug - 12 aug"
  "8 aug - 12 aug"
  "8 august - 12 august"
  (datetime-interval [2013 8 8] [2013 8 13])

  "9:30 - 11:00"
  "9:30 til 11:00"
  (datetime-interval [2013 2 12 9 30] [2013 2 12 11 1])

  "fra 9:30 - 11:00 på torsdag"
  "fra 9:30 til 11:00 på torsdag"
  "mellem 9:30 og 11:00 på torsdag"
  "9:30 - 11:00 på torsdag"
  "9:30 til 11:00 på torsdag"
  "efter 9:30 men før 11:00 på torsdag"
  "torsdag fra 9:30 til 11:00"
  "torsdag mellem 9:30 og 11:00"
  "fra 9:30 til 11:00 på torsdag"
  (datetime-interval [2013 2 14 9 30] [2013 2 14 11 1])

  "torsdag fra 9 til 11"
  (datetime-interval [2013 2 14 9] [2013 2 14 12])

  "11:30-13:30" ; go train this rule!
  "11:30-13:30"
  "11:30-13:30"
  "11:30-13:30"
  "11:30-13:30"
  "11:30-13:30"
  (datetime-interval [2013 2 12 11 30] [2013 2 12 13 31])

  "indenfor 2 uger"
  (datetime-interval [2013 2 12 4 30 0] [2013 2 26])

  "inden kl. 14"
  "indtil kl. 14"
  "inden klokken 14"
  (datetime 2013 2 12 14 :direction :before)

  ; Timezones

  "16 CET"
  "kl. 16 CET"
  "klokken 16 CET"
  (datetime 2013 2 12 16 :hour 4 :meridiem :pm :timezone "CET")

  "torsdag kl. 8:00 GMT"
  "torsdag klokken 8:00 GMT"
  "torsdag 08:00 GMT"
  (datetime 2013 2 14 8 00 :timezone "GMT")

  ;; Bookface tests
  "idag kl. 14"
  "idag klokken 14"
  "kl. 14"
  "klokken 14"
  (datetime 2013 2 12 14)

  "25/4 kl. 16:00"
  "25/4 klokken 16:00"
  "25-04 klokken 16:00"
  "25-4 kl. 16:00"
  (datetime 2013 4 25 16 0)

  "15:00 i morgen"
  "kl. 15:00 i morgen"
  "klokken 15:00 i morgen"
  (datetime 2013 2 13 15 0)

  "efter kl. 14"
  "efter klokken 14"
  (datetime 2013 2 12 14 :direction :after)

  "efter 5 dage"
  "efter fem dage"
  (datetime 2013 2 17 4 :direction :after)

  "om 5 dage"
  "om fem dage"
  (datetime 2013 2 17 4)

  "efter i morgen kl. 14"
  "efter i morgen klokken 14"
  "i morgen efter kl. 14" ;; FIXME this is actually not ambiguous it's 2pm - midnight.
  "i morgen efter klokken 14"
  (datetime 2013 2 13 14 :direction :after)

  "før kl. 11"
  "før klokken 11"
  (datetime 2013 2 12 11 :direction :before)

  "i morgen før kl. 11" ;; FIXME this is actually not ambiguous. it's midnight to 11 am
  "i morgen før klokken 11"
  (datetime 2013 2 13 11 :direction :before)

  "om eftermiddagen"
  (datetime-interval [2013 2 12 12] [2013 2 12 19])

  "kl. 13:30"
  "klokken 13:30"
  (datetime 2013 2 12 13 30)

  "om 15 minutter"
  (datetime 2013 2 12 4 45 0)

  "efter frokost"
  (datetime-interval [2013 2 12 13] [2013 2 12 17])

  "10:30"
  (datetime 2013 2 12 10 30)

  "morgen" ;; how should we deal with fb mornings?
  (datetime-interval [2013 2 12 4] [2013 2 12 12])

  "næste mandag"
  (datetime 2013 2 18 :day-of-week 1)
)
