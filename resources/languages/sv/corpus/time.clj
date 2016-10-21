(
  ; Context map
  ; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:reference-time (time/t -2 2013 2 12 4 30 0)
   :min (time/t -2 1900)
   :max (time/t -2 2100)}

  "nu"
  "just nu"
  (datetime 2013 2 12 4 30 00)

  "idag"
  (datetime 2013 2 12)

  "igår"
  (datetime 2013 2 11)

  "imorgon"
  (datetime 2013 2 13)

  "måndag"
  "mån."
  "på måndag"
  (datetime 2013 2 18 :day-of-week 1)

  "Måndagen den 18 februari"
  "Mån 18 februari"
  (datetime 2013 2 18 :day-of-week 1 :day 18 :month 2)

  "tisdag"
  (datetime 2013 2 19)

  "torsdag"
  "tors"
  "tors."
  (datetime 2013 2 14)

  "fredag"
  "fre"
  "fre."
  (datetime 2013 2 15)

  "lördag"
  "lör"
  "lör."
  (datetime 2013 2 16)

  "söndag"
  "sön"
  "sön."
  (datetime 2013 2 17)

  "Den första mars"
  "1. mars"
  "Den 1. mars"
  (datetime 2013 3 1 :day 1 :month 3)

  "3 mars"
  "den tredje mars"
  "den 3. mars"
  (datetime 2013 3 3 :day 3 :month 3)

  "3 mars 2015"
  "tredje mars 2015"
  "3. mars 2015"
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

  "den 15. februari"
  "15. februari"
  "februari 15"
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

  "14 april 2015"
  "April 14, 2015"
  "fjortonde April 15"
  (datetime 2015 4 14 :day 14 :month 4 :years 2015)

  "nästnästa fredag"
  (datetime 2013 2 22 :day-of-week 2)

  "nästa mars"
  (datetime 2013 3)

  "nästnästa mars"
  (datetime 2014 3)

  "Söndag, 10 feb"
  "Söndag 10 Feb"
  (datetime 2013 2 10 :day-of-week 7 :day 10 :month 2)

  "Ons, Feb13"
  "Ons feb13"
  (datetime 2013 2 13 :day-of-week 3 :day 13 :month 2)

  "Måndag, Feb 18"
  "Mån, februari 18"
  (datetime 2013 2 18 :day-of-week 1 :day 18 :month 2)

;   ;; Cycles

  "denna vecka"
  (datetime 2013 2 11 :grain :week)

  "förra vecka"
  (datetime 2013 2 4 :grain :week)

  "nästa vecka"
  (datetime 2013 2 18 :grain :week)

  "förra månad"
  (datetime 2013 1)

  "nästa månad"
  (datetime 2013 3)

  "detta kvartal"
  (datetime 2013 1 1 :grain :quarter)

  "nästa kvartal"
  (datetime 2013 4 1 :grain :quarter)

  "tredje kvartalet"
  "3. kvartalet"
  (datetime 2013 7 1 :grain :quarter)

  "4. kvartal 2018"
  "fjärde kvartalet 2018"
  (datetime 2018 10 1 :grain :quarter)

  "förra år"
  (datetime 2012)

  "i fjol"
  (datetime 2012)

  "iår"
  "detta år"
  (datetime 2013)

  "nästa år"
  (datetime 2014)

  "förra söndag"
  "söndag förra veckan"
  (datetime 2013 2 10 :day-of-week 7)

  "förra tisdag"
  (datetime 2013 2 5 :day-of-week 2)

  "nästa tisdag" ; when today is Tuesday, "nästa tirsdag" (next tuesday) is a week from now
  (datetime 2013 2 19 :day-of-week 2)

  "nästa onsdag" ; when today is Tuesday, "nästa onsdag" (next wednesday) is tomorrow
  (datetime 2013 2 13 :day-of-week 3)

  "onsdag i nästa vecka"
  "onsdag nästa vecka"
  "nästnästa onsdag"
  (datetime 2013 2 20 :day-of-week 3)

  "nästnästa fredag"
  (datetime 2013 2 22 :day-of-week 5)

  "måndag denna vecka"
  (datetime 2013 2 11 :day-of-week 1)

  "tisdag denna vecka"
  (datetime 2013 2 12 :day-of-week 2)

  "onsdag denna vecka"
  (datetime 2013 2 13 :day-of-week 3)

  "i överimorgon"
  (datetime 2013 2 14)

  "i förrgår"
  (datetime 2013 2 10)

  "sista måndagen i mars"
	"sista måndagen i Mars"
  (datetime 2013 3 25 :day-of-week 1)

	"sista söndagen i mars 2014"
	"sista söndagen i Mars 2014"
  (datetime 2014 3 30 :day-of-week 7)

  "första veckan i oktober 2014"
  "första veckan i Oktober 2014"
  (datetime 2014 10 6 :grain :week)

  ;"the week of october 6th"
  ;"the week of october 7th"
  ;(datetime 2013 10 7 :grain :week)

  "sista dagen i oktober 2015"
  "sista dagen i Oktober 2015"
  (datetime 2015 10 31)

  "sista veckan i september 2014"
  "sista veckan i September 2014"
  (datetime 2014 9 22 :grain :week)


  ;; nth of
  "första tisdagen i oktober"
  "första tisdagen i Oktober"
  (datetime 2013 10 1)

  "tredje tisdagen i september 2014"
  "tredje tisdagen i September 2014"
  (datetime 2014 9 16)

  "första onsdagen i oktober 2014"
  "första onsdagen i Oktober 2014"
  (datetime 2014 10 1)

  "andra onsdagen i oktober 2014"
  "andra onsdagen i Oktober 2014"
  (datetime 2014 10 8)

  ;; Hours

  "klockan 3"
  "kl. 3"
  (datetime 2013 2 13 3)

  "3:18"
  (datetime 2013 2 13 3 18)

  "klockan 15"
  "kl. 15"
  "15h"
  (datetime 2013 2 12 15 :hour 3 :meridiem :pm)

  "ca. kl. 15" ;; FIXME pm overrides precision
  "cirka kl. 15"
  "runt klockan 15"
  (datetime 2013 2 12 15 :hour 3 :meridiem :pm) ;; :precision "approximate"

  "imorgon klockan 17 prick" ;; FIXME precision is lost
  "imorgon kl. 17 exakt"
  (datetime 2013 2 13 17 :hour 5 :meridiem :pm) ;; :precision "exact"

  "kvart över 15"
  "femton över 15"
  "15:15"
  (datetime 2013 2 12 15 15 :hour 3 :minute 15 :meridiem :pm)

  "kl. 20 över 15"
  "klockan 20 över 15"
  "tjugo över 15"
  "15 tjugo"
  "kl. 15:20"
  "15:20"
  (datetime 2013 2 12 15 20 :hour 3 :minute 20 :meridiem :pm)

  "15:30"
  (datetime 2013 2 12 15 30 :hour 3 :minute 30 :meridiem :pm)

  "15:23:24"
  (datetime 2013 2 12 15 23 24 :hour 15 :minute 23 :second 24)

  "kvart i 12"
  "femton i 12"
  "11:45"
  (datetime 2013 2 12 11 45 :hour 11 :minute 45)

  ;; Mixing date and time

  "klockan 9 på lördag"
  (datetime 2013 2 16 9 :day-of-week 6 :hour 9 :meridiem :am)

  "Fre, Jul 18, 2014 19:00"
  (datetime 2014 7 18 19 0 :day-of-week 5 :hour 7 :meridiem :pm)

  "kl. 19:30, Lör, 20 sep"
  (datetime 2014 9 20 19 30 :day-of-week 6 :hour 7 :minute 30 :meridiem :pm)


; ;; Involving periods

  "om 1 sekund"
  "om en sekund"
  "en sekund från nu"
  "1 sekund från nu"
  (datetime 2013 2 12 4 30 1)

  "om 1 minut"
  "om en minut"
  (datetime 2013 2 12 4 31 0)

  "om 2 minuter"
  "om två minuter"
  "2 minuter från nu"
  "två minuter från nu"
  (datetime 2013 2 12 4 32 0)

  "om 60 minuter"
  (datetime 2013 2 12 5 30 0)

  "om en halvtimme"
  (datetime 2013 2 12 5 0 0)

  "om 2,5 timmar"
  "om 2 och en halv timme"
  "om två och en halv timme"
  (datetime 2013 2 12 7 0 0)

  "om en timme"
  "om 1 timme"
  "om 1 tim"
  "om 1t"
  "om 1h"
  (datetime 2013 2 12 5 30)

  "om ett par timmar"
  (datetime 2013 2 12 6 30)

  "om 24 timmar"
  (datetime 2013 2 13 4 30)

  "om en dag"
	"om 1 dag"
  (datetime 2013 2 13 4)

  "3 år från idag"
  (datetime 2016 2)

  "om 7 dagar"
  (datetime 2013 2 19 4)

  "om en vecka"
  (datetime 2013 2 19)

  "om ca. en halvtimme" ;; FIXME precision is lost
  "om cirka en halvtimme"
  (datetime 2013 2 12 5 0 0) ;; :precision "approximate"

  "7 dagar sen"
  "sju dagar sedan"
  (datetime 2013 2 5 4)

  "14 dagar sedan"
  "fjorton dagar sen"
  (datetime 2013 1 29 4)

  "en vecka seden"
  "en vecka sen"
  "1 vecka sen"
  (datetime 2013 2 5)

  "3 veckor sen"
  "tre veckor sedan"
  (datetime 2013 1 22)

  "3 månader sedan"
  "tre månader sedan"
  (datetime 2012 11 12)

  "två år sen"
  "2 år sedan"
  (datetime 2011 2)

  "1954"
  (datetime 1954)

  "1 år efter julafton"
  "ett år efter julafton"
  (datetime 2013 12) ; resolves as after last Xmas...


  ; Seasons

  "i sommar"
  "denna sommar"
  (datetime-interval [2013 6 21] [2013 9 24])

  "i vinter"
  "denna vinter"
  (datetime-interval [2012 12 21] [2013 3 21])

  "i jul"
  "denna jul"
  (datetime-interval [2012 12 24] [2013 3 26]) ; https://sv.wikipedia.org/wiki/Svenska_h%C3%B6gtider_och_traditioner

  ; SV holidays (http://www.timeanddate.com/holidays/sweden/)

  "juldag"
  "juldagen"
  (datetime 2013 12 25)

  "nyårsafton"
  (datetime 2013 12 31)

  "nyårsdag"
  (datetime 2014 1 1)

  ; Part of day (morning, afternoon...)

  "ikväll"
  (datetime-interval [2013 2 12 18] [2013 2 13 00])

  "förra helgen"
  (datetime-interval [2013 2 8 18] [2013 2 11 00])

  "imorgon kväll"
  (datetime-interval [2013 2 13 18] [2013 2 14 00])

  "imorgon lunch"
  (datetime-interval [2013 2 13 12] [2013 2 13 14])

  "igår kväll"
  (datetime-interval [2013 2 11 18] [2013 2 12 00])

  "denna helgen"
	"denna helg"
  "på helgen"
  "i helgen"
  (datetime-interval [2013 2 15 18] [2013 2 18 00])

  "måndag morgon"
  (datetime-interval [2013 2 18 4] [2013 2 18 12])

  ; Intervals involving cycles

  "sista 2 sekunder"
  "sista två sekunder"
  (datetime-interval [2013 2 12 4 29 58] [2013 2 12 4 30 00])

  "nästa 3 sekunder"
  "nästa tre sekunder"
  (datetime-interval [2013 2 12 4 30 01] [2013 2 12 4 30 04])

  "sista 2 minuter"
  "sista två minuter"
  (datetime-interval [2013 2 12 4 28] [2013 2 12 4 30])

  "nästa 3 minuter"
  "nästa tre minuter"
  (datetime-interval [2013 2 12 4 31] [2013 2 12 4 34])

  "senaste timme"
	"sista timmen"
  "senaste timmen"
  (datetime-interval [2013 2 12 3] [2013 2 12 4])

  "nästa 3 timmar"
  "nästa tre timmar"
  (datetime-interval [2013 2 12 5] [2013 2 12 8])

  "sista 2 dagar"
  "sista två dagar"
  "senaste 2 dagar"
  (datetime-interval [2013 2 10] [2013 2 12])

  "nästa 3 dagar"
  "nästa tre dagar"
  (datetime-interval [2013 2 13] [2013 2 16])

  "sista 2 veckor"
  "sista två veckorna"
  "senaste två veckor"
  (datetime-interval [2013 1 28 :grain :week] [2013 2 11 :grain :week])

  "nästa 3 veckor"
  "nästa tre veckor"
  (datetime-interval [2013 2 18 :grain :week] [2013 3 11 :grain :week])

  "sista 2 månader"
  "sista två månaderna"
  "senaste två månader"
  (datetime-interval [2012 12] [2013 02])

  "nästa 3 månaderna"
  "nästa tre månader"
  (datetime-interval [2013 3] [2013 6])

  "sista 2 år"
  "senaste två åren"
  "senaste 2 år"
  (datetime-interval [2011] [2013])

  "sista året"
  "senaste året"
  "senaste år"
  (datetime-interval [2012] [2013])

  "nästa 3 år"
  "nästa tre år"
  (datetime-interval [2014] [2017])


  ; Explicit intervals

  "13-15 juli"
  "13-15 Juli"
  "13 till 15 Juli"
  "13 juli till 15 juli"
  "mellan 13 juli och 15 juli"
  (datetime-interval [2013 7 13] [2013 7 16])

  "8 Aug - 12 Aug"
  "8 Aug - 12 aug"
  "8 aug - 12 aug"
  "8 augusti - 12 augusti"
  (datetime-interval [2013 8 8] [2013 8 13])

  "9:30 - 11:00"
  "9:30 mellan 11:00"
  "9:30 till 11:00"
  (datetime-interval [2013 2 12 9 30] [2013 2 12 11 1])

  "från 9:30 - 11:00 på torsdag"
  "från 9:30 till 11:00 på torsdag"
  "mellan 9:30 och 11:00 på torsdag"
  "9:30 - 11:00 på torsdag"
  "9:30 till 11:00 på torsdag"
  "efter 9:30 men före 11:00 på torsdag"
  "torsdag från 9:30 till 11:00"
  "torsdag mellan 9:30 och 11:00"
  "från 9:30 till 11:00 på torsdagen"
  (datetime-interval [2013 2 14 9 30] [2013 2 14 11 1])

  "torsdag från 9 till 11"
  (datetime-interval [2013 2 14 9] [2013 2 14 12])

  "11:30-13:30" ; go train this rule!
  "11:30-13:30"
  "11:30-13:30"
  "11:30-13:30"
  "11:30-13:30"
  "11:30-13:30"
  (datetime-interval [2013 2 12 11 30] [2013 2 12 13 31])

  "inom 2 veckor"
  (datetime-interval [2013 2 12 4 30 0] [2013 2 26])

  "innan kl. 14"
  "innan klockan 14"
  (datetime 2013 2 12 14 :direction :before)

  ; Timezones

  "16 CET"
  "kl. 16 CET"
  "klockan 16 CET"
  (datetime 2013 2 12 16 :hour 4 :meridiem :pm :timezone "CET")

  "torsdag kl. 8:00 GMT"
  "torsdag klockan 8:00 GMT"
  "torsdag 08:00 GMT"
  (datetime 2013 2 14 8 00 :timezone "GMT")

  ;; Bookface tests
  "idag kl. 14"
  "idag klockan 14"
  "kl. 14"
  "klockan 14"
  (datetime 2013 2 12 14)

  "25/4 kl. 16:00"
  "25/4 klockan 16:00"
  "25-04 klockan 16:00"
  "25-4 kl. 16:00"
  (datetime 2013 4 25 16 0)

  "15:00 imorgon"
  "kl. 15:00 imorgon"
  "klockan 15:00 imorgon"
  (datetime 2013 2 13 15 0)

  "efter kl. 14"
  "efter klockan 14"
  (datetime 2013 2 12 14 :direction :after)

  "efter 5 dagar"
  "efter fem dagar"
  (datetime 2013 2 17 4 :direction :after)

  "om 5 dagar"
  "om fem dagar"
  (datetime 2013 2 17 4)

  "efter imorgon kl. 14"
  "efter imorgon klockan 14"
  "imorgon efter kl. 14" ;; FIXME this is actually not ambiguous it's 2pm - midnight.
  "imorgon efter klockan 14"
  (datetime 2013 2 13 14 :direction :after)

  "före kl. 11"
  "före klockan 11"
  (datetime 2013 2 12 11 :direction :before)

  "imorgon före kl. 11" ;; FIXME this is actually not ambiguous. it's midnight to 11 am
  "imorgon före klockan 11"
  (datetime 2013 2 13 11 :direction :before)

  "under eftermiddagen"
  (datetime-interval [2013 2 12 12] [2013 2 12 19])

  "kl. 13:30"
  "klockan 13:30"
  (datetime 2013 2 12 13 30)

  "om 15 minuter"
  (datetime 2013 2 12 4 45 0)

  "efter frokost"
  (datetime-interval [2013 2 12 13] [2013 2 12 17])

  "10:30"
  (datetime 2013 2 12 10 30)

  "morgon" ;; how should we deal with fb mornings?
  (datetime-interval [2013 2 12 4] [2013 2 12 12])

  "nästa måndag"
  (datetime 2013 2 18 :day-of-week 1)

  "morsdag"
  (datetime 2013 5 26)

  "farsdag"
  (datetime 2013 11 10)
)
