(; Context map
; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:reference-time (time/t -2 2013 2 12 4 30 0)
   :min            (time/t -2 1900)
   :max            (time/t -2 2100)}

  "sad"
  "sada"
  "upravo sad"
  "ovaj tren"
  (datetime 2013 2 12 4 30 00)

  "danas"
  (datetime 2013 2 12)

  "jucer"
  "jučer"
  (datetime 2013 2 11)

  "sutra"
  (datetime 2013 2 13)

  "ponedjeljak"
  "pon."
  "ovaj ponedjeljak"
  (datetime 2013 2 18 :day-of-week 1)

  "ponedjeljak, 18. veljace"
  "ponedjeljak, 18. veljače"
  (datetime 2013 2 18 :day-of-week 1 :day 18 :month 2)

  "utorak"
  "utorak 19."
  (datetime 2013 2 19)

  "cetvrtak"
  "četvrtak"
  "čet"
  "cet."
  (datetime 2013 2 14)

  "petak"
  "pet"
  "pet."
  (datetime 2013 2 15)

  "subota"
  "sub"
  "sub."
  (datetime 2013 2 16)

  "nedjelja"
  "ned"
  "ned."
  (datetime 2013 2 17)

  "1. ozujak"
  "1. ožujak"
  "prvi ozujka"
  (datetime 2013 3 1 :day 1 :month 3)

  "treci ozujka"
  "treci ožujka"
  (datetime 2013 3 3 :day 3 :month 3)

  ;  "martovske ide"
  ;  (datetime 2013 3 15 :month 3)

  "3. ozujka 2015"
  "treci ozujka 2015"
  "3/3/2015"
  "3/3/15"
  "2015-3-3"
  "2015-03-03"
  (datetime 2015 3 3 :day 3 :month 3 :year 2015)

  "15ti drugi"
  (datetime 2013 2 15 :day 15)

  "15. veljace"
  "15. veljače"
  "15/02"
  (datetime 2013 2 15 :day 15 :month 2)

  "8. kolovoza"
  "8. kolovoz"
  (datetime 2013 8 8 :day 8 :month 8)

  "listopad 2014"
  (datetime 2014 10 :year 2014 :month 10)

  "31/10/1974"
  "31/10/74"
  "74-10-31"
  (datetime 1974 10 31 :day 31 :month 10 :year 1974)

  "14travanj 2015"
  "14. travnja, 2015"
  "14. travanj 15"
  (datetime 2015 4 14 :day 14 :month 4 :years 2015)

  "sljedeci utorak"
  "sljedeceg utorka"
  (datetime 2013 2 19 :day-of-week 2)

  "petak nakon sljedeceg"
  (datetime 2013 2 22 :day-of-week 2)

  "sljedeci ozujak"
  (datetime 2013 3)

  "ozujak nakon sljedeceg"
  (datetime 2014 3)

  "nedjelja, 10. veljace"
  "nedjelja, 10. veljače"
  (datetime 2013 2 10 :day-of-week 7 :day 10 :month 2)

  "Sri, 13. velj"
  (datetime 2013 2 13 :day-of-week 3 :day 13 :month 2)

  "ponedjeljak, veljaca 18."
  "Pon, 18. veljace"
  (datetime 2013 2 18 :day-of-week 1 :day 18 :month 2)

  ;   ;; Cycles

  "ovaj tjedan"
  (datetime 2013 2 11 :grain :week)

  "prosli tjedan"
  "prošli tjedan"
  "prethodni tjedan"
  (datetime 2013 2 4 :grain :week)

  "sljedeci tjedan"
  (datetime 2013 2 18 :grain :week)

  "prethodni mjesec"
  (datetime 2013 1)

  "sljedeci mjesec"
  (datetime 2013 3)

  "ovaj kvartal"
  "ovo tromjesecje"
  (datetime 2013 1 1 :grain :quarter)

  "sljedeci kvartal"
  (datetime 2013 4 1 :grain :quarter)

  "treci kvartal"
  "3. kvartal"
  "trece tromjesecje"
  "3. tromjesečje"
  (datetime 2013 7 1 :grain :quarter)

  "4. kvartal 2018"
  "četvrto tromjesečje 2018"
  (datetime 2018 10 1 :grain :quarter)

  "prošla godina"
  "prethodna godina"
  (datetime 2012)

  "ova godina"
  (datetime 2013)

  "sljedece godina"
  (datetime 2014)

  "prosle nedjelje"
  "prosli tjedan u nedjelju"
  (datetime 2013 2 10 :day-of-week 7)

  "prosli utorak"
  (datetime 2013 2 5 :day-of-week 2)

  "sljedeci utorak" ; when today is Tuesday, "mardi prochain" is a week from now
  (datetime 2013 2 19 :day-of-week 2)

  "sljedecu srijedu" ; when today is Tuesday, "mercredi prochain" is tomorrow
  (datetime 2013 2 13 :day-of-week 3)

  "sljedeci tjedan u srijedu"
  "srijeda sljedeci tjedan"
  (datetime 2013 2 20 :day-of-week 3)

  "sljedeci petak"
  (datetime 2013 2 15 :day-of-week 5)

  "ovaj tjedan u ponedjeljak"
  (datetime 2013 2 11 :day-of-week 1)

  "ovaj utorak"
  (datetime 2013 2 19 :day-of-week 2)

  "ova srijeda"
  "ovaj tjedan u srijedu"
  (datetime 2013 2 13 :day-of-week 3)

  "prekosutra"
  (datetime 2013 2 14)

  "prekosutra u 5 popodne"
  "prekosutra u 17"
  (datetime 2013 2 14 17)

  "prekjucer"
  "prekjučer"
  (datetime 2013 2 10)

  "prekjučer u 8"
  "prekjučer u 8 sati"
  (datetime 2013 2 10 8)

  "zadnji ponedjeljak u ozujku"
  (datetime 2013 3 25 :day-of-week 1)

  "zadnja nedjelja u ozujku 2014"
  (datetime 2014 3 30 :day-of-week 7)

  "treci dan u listopadu"
  (datetime 2013 10 3)

  "prvi tjedan u listopadu 2014"
  (datetime 2014 10 6 :grain :week)

  "zadnji dan u listopadu 2015"
  (datetime 2015 10 31)

  "zadnji tjedan u rujnu 2014"
  (datetime 2014 9 22 :grain :week)


  ;; nth of
  "prvi utorak u listopadu"
  (datetime 2013 10 1)

  "treci utorak u rujnu 2014"
  (datetime 2014 9 16)

  "prva srijeda u listopadu 2014"
  (datetime 2014 10 1)

  "druga srijeda u listopadu 2014"
  (datetime 2014 10 8)

  ;; nth after

  "treci utorak poslije Bozica 2014"
  (datetime 2015 1 13)


  ;; Hours

  "3 u noci"
  "u 3 ujutro"
  "u tri sata u noci"
  (datetime 2013 2 13 3)

  "3:18 rano"
  (datetime 2013 2 12 3 18)

  "u 3 poslijepodne"
  "@ 15"
  "15"
  "15 sati poslijepodne"
  (datetime 2013 2 12 15 :hour 3 :meridiem :pm)

  "oko 3 poslijepodne" ;; FIXME pm overrides precision
  "otprilike u 3 poslijepodne"
  "cca 3 poslijepodne"
  "cca 15"
  (datetime 2013 2 12 15 :hour 3 :meridiem :pm) ;; :precision "approximate"

  "15 i 15"
  "3:15 poslijepodne"
  "15:15"
  (datetime 2013 2 12 15 15 :hour 3 :minute 15 :meridiem :pm)

  "cetvrt nakon 3 poslijepodne"
  (datetime 2013 2 12 15 15 :hour 3 :minute 15 :meridiem :pm :grain :second)

  "3 i 20 popodne"
  "3:20 poslijepodne"
  "3:20 popodne"
  "dvadeset nakon 3 popodne"
  "15:20"
  (datetime 2013 2 12 15 20 :hour 3 :minute 20 :meridiem :pm)

  "tri i po popodne"
  "pola 4 popodne"
  "15:30"
  "pola cetiri popodne"
  (datetime 2013 2 12 15 30 :hour 3 :minute 30)

  "15:23:24"
  (datetime 2013 2 12 15 23 24 :hour 15 :minute 23 :second 24)

  "petnaest do podne"
  "11:45"
  "četvrt do podneva" ; Ambiguous with interval
  (datetime 2013 2 12 11 45 :hour 11 :minute 45)

  "8 navecer"
  "osam sati navecer"
  "danas 8 navecer"
  (datetime 2013 2 12 20)

  ;; Mixing date and time

  "u 7:30 popodne u pet, 20. rujna"
  (datetime 2013 9 20 19 30 :hour 7 :minute 30 :meridiem :pm)

  "9 ujutro u subotu"
  "u subotu u 9 sati ujutro"
  (datetime 2013 2 16 9 :day-of-week 6 :hour 9 :meridiem :am)

  "pet, srp 18., 2014, 19:00"
  "pet, srp 18., 2014 u 19:00"
  (datetime 2014 7 18 19 0 :day-of-week 5 :hour 7 :meridiem :pm)

;  TODO reported as not found even tough it passes
;  "pet, srp 18., 2014, 19 sati 10 minuta"
;  (datetime 2014 7 18 19 10 :day-of-week 5 :hour 7 :meridiem :pm)

  ; ;; Involving periods

  "za jednu sekundu"
  (datetime 2013 2 12 4 30 1)

  "za jednu minutu"
  (datetime 2013 2 12 4 31 0)

  "za 2 minute"
  "za jos 2 minute"
  "2 minute od sad"
  (datetime 2013 2 12 4 32 0)

  "za 60 minuta"
  (datetime 2013 2 12 5 30 0)

  "oko cetvrt sata"
  "oko 1/4h"
  "oko 1/4 h"
  "oko 1/4 sata"
  (datetime 2013 2 12 4 45 0)

  "za pola sata"
  "za pol sata"
  "za 1/2h"
  "za 1/2 h"
  "za 1/2 sata"
  (datetime 2013 2 12 5 0 0)

  "za tri-cetvrt sata"
  "za 3/4h"
  "za 3/4 h"
  "za 3/4 sata"
  (datetime 2013 2 12 5 15 0)

;  TODO reported as not found,
;  "za dva i pol sata"
  "za 2.5 sata"
  (datetime 2013 2 12 7 0 0)

  "za jedan sat"
  "za 1h"
  (datetime 2013 2 12 5 30)

  "za par sati"
  (datetime 2013 2 12 6 30)

  "za nekoliko sati"
  (datetime 2013 2 12 7 30)

  "za 24 sata"
  "za 24h"
  (datetime 2013 2 13 4 30)

  "za 1 dan"
  "za jedan dan"
  (datetime 2013 2 13 4)

  "3 godine od danasnjeg dana"
  (datetime 2016 2)

  "za 7 dana"
  (datetime 2013 2 19 4)

  "za 1 tjedan"
  (datetime 2013 2 19)

  "za oko pola sata" ;; FIXME precision is lost
  (datetime 2013 2 12 5 0 0) ;; :precision "approximate"

  "prije 7 dana"
  (datetime 2013 2 5 4)

  "prije 14 dana"
  (datetime 2013 1 29 4)

  "prije jedan tjedan"
  "prije jednog tjedna"
  (datetime 2013 2 5)

  "prije tri tjedna"
  (datetime 2013 1 22)

  "prije tri mjeseca"
  (datetime 2012 11 12)

  "prije dvije godine"
  (datetime 2011 2)

  "1954"
  (datetime 1954)

  "za 7 dana"
  (datetime 2013 2 19 4)

  "za 14 dana"
  (datetime 2013 2 26 4)

  "za jedan tjedan"
  (datetime 2013 2 19)

  "za tri tjedna"
  (datetime 2013 3 5)

  "za tri mjeseca"
  (datetime 2013 5 12)

  "za dvije godine"
  (datetime 2015 2)

  "jednu godinu poslije Bozica"
  (datetime 2013 12) ; resolves as after last Xmas...

  ; Seasons

  "ovog ljeta"
  "ovo ljeto"
  "ljetos"
  (datetime-interval [2013 6 21] [2013 9 24])

  "ove zime"
  "zimus"
  (datetime-interval [2012 12 21] [2013 3 21])

  ; US holidays (http://www.timeanddate.com/holidays/us/)

  "Bozic"
  "zicbo"
  (datetime 2013 12 25)

  "stara godina"
  (datetime 2013 12 31)

  "nova godina"
  (datetime 2014 1 1)

  "valentinovo"
  (datetime 2013 2 14)

  "majcin dan"
  (datetime 2013 5 12)

  "dan oceva"
  (datetime 2013 6 16)

  "noc vjestica"
  (datetime 2013 10 31)

  ; Part of day (morning, afternoon...)

  "veceras"
  "ove veceri"
  "danas navecer"
  (datetime-interval [2013 2 12 18] [2013 2 13 00])

  "prosli vikend"
  (datetime-interval [2013 2 8 18] [2013 2 11 00])

  "sutra navecer"
  ;"Wednesday evening"
  (datetime-interval [2013 2 13 18] [2013 2 14 00])

  "sutra rucak"
  (datetime-interval [2013 2 13 12] [2013 2 13 14])

  "jucer navecer"
  "prethodne veceri"
  (datetime-interval [2013 2 11 18] [2013 2 12 00])

  "ovaj vikend"
  "ovog vikenda"
  (datetime-interval [2013 2 15 18] [2013 2 18 00])

  "ponedjeljak ujutro"
  (datetime-interval [2013 2 18 4] [2013 2 18 12])

  "ponedjeljak rano ujutro"
  "ponedjeljak rano"
  "ponedjeljak u rane jutarnje sate"
  (datetime-interval [2013 2 18 3] [2013 2 18 9])

  "15. veljace ujutro"
  (datetime-interval [2013 2 15 4] [2013 2 15 12])


  ; Intervals involving cycles

  "prosle 2 sekunde"
  "prethodne dvije sekunde"
  (datetime-interval [2013 2 12 4 29 58] [2013 2 12 4 30 00])

  "sljedece 3 sekunde"
  "sljedece tri sekunde"
  (datetime-interval [2013 2 12 4 30 01] [2013 2 12 4 30 04])

  "prosle 2 minute"
  "prethodne dvije minute"
  (datetime-interval [2013 2 12 4 28] [2013 2 12 4 30])

  "sljedece 3 minute"
  "sljedece tri minute"
  (datetime-interval [2013 2 12 4 31] [2013 2 12 4 34])

  "prethodni jedan sat"
  (datetime-interval [2013 2 12 3] [2013 2 12 4])

  "prethodna 24 sata"
  "prethodna dvadeset i cetiri sata"
  "prethodna dvadeset i cetiri sata"
  "prethodna 24h"
  (datetime-interval [2013 2 11 4] [2013 2 12 4])

  "sljedeca 3 sata"
  "sljedeca tri sata"
  (datetime-interval [2013 2 12 5] [2013 2 12 8])

  "prethodna dva dana"
  "prethodna 2 dana"
  "prosla 2 dana"
  (datetime-interval [2013 2 10] [2013 2 12])

  "sljedeca 3 dana"
  "sljedeca tri dana"
  (datetime-interval [2013 2 13] [2013 2 16])

  "sljedecih nekoliko dana"
  (datetime-interval [2013 2 13] [2013 2 16])

  "prethodna 2 tjedna"
  "prethodna dva tjedna"
  "prosla 2 tjedna"
  (datetime-interval [2013 1 28 :grain :week] [2013 2 11 :grain :week])

  "sljedeca 3 tjedna"
  "sljedeca tri tjedna"
  (datetime-interval [2013 2 18 :grain :week] [2013 3 11 :grain :week])

  "prethodna 2 mjeseca"
  "prethodna dva mjeseca"
  (datetime-interval [2012 12] [2013 02])

  "sljedeca 3 mjeseca"
  "sljedeca tri mjeseca"
  (datetime-interval [2013 3] [2013 6])

  "prethodne 2 godine"
  "prethodne dvije godine"
  (datetime-interval [2011] [2013])

  "sljedece 3 godine"
  "sljedece tri godine"
  (datetime-interval [2014] [2017])


  ; Explicit intervals

  "srpanj 13-15"
  "srpanj 13 do 15"
  "srpanj 13 - srpanj 15"
  (datetime-interval [2013 7 13] [2013 7 16])

  "kol 8 - kol 12"
  (datetime-interval [2013 8 8] [2013 8 13])

  "9:30 - 11:00"
  (datetime-interval [2013 2 12 9 30] [2013 2 12 11 1])

  "od 9:30 - 11:00 u cetvrtak"
  "između 9:30 i 11:00 u cetvrtak"
  "9:30 - 11:00 u cetvrtak"
  "izmedju 9:30 i 11:00 u cetvrtak"
  "cetvrtak od 9:30 do 11:00"
  "od 9:30 do 11:00 u cetvrtak"
  "cetvrtak od 9:30 do 11:00"
  (datetime-interval [2013 2 14 9 30] [2013 2 14 11 1])

  "cetvrtak od 9 do 11 ujutro"
  (datetime-interval [2013 2 14 9] [2013 2 14 12])

  "11:30-1:30" ; go train this rule!
  "11:30-1:30"
  "11:30-1:30"
  "11:30-1:30"
  "11:30-1:30"
  "11:30-1:30"
  "11:30-1:30"
  (datetime-interval [2013 2 12 11 30] [2013 2 12 13 31])

  "1:30 poslijepodne u sub, ruj 21."
  (datetime 2013 9 21 13 30)

  "sljedeca 2 tjedna"
  (datetime-interval [2013 2 18 :grain :week] [2013 3 4 :grain :week])

  "do 2 poslijepodne"
  (datetime 2013 2 12 14 :direction :before)

  "do kraja ovog dana"
  (datetime-interval [2013 2 12 4 30 0] [2013 2 13 0])

  "do kraja dana"
  (datetime 2013 2 13 0)

  "do kraja ovog mjeseca"
  (datetime-interval [2013 2 12 4 30 0] [2013 3 1 0])

  "do kraja sljedeceg mjeseca"
  (datetime-interval [2013 2 12 4 30 0] [2013 4 1 0])
  ; Timezones

  "4 poslijepodne CET"
  (datetime 2013 2 12 16 :hour 4 :meridiem :pm :timezone "CET")

  "cetvrtak 8:00 GMT"
  (datetime 2013 2 14 8 00 :timezone "GMT")

  ;; Bookface tests
  "danas u 14"
  "u 2 poslijepodne"
  (datetime 2013 2 12 14)

  "25/4 U 16 sati"
  (datetime 2013 4 25 16)

  "15 sati sutra"
  (datetime 2013 2 13 15)

  ; winner is picked but has a different hash, strange
  ;  Expected {:start #object[org.joda.time.DateTime 0x701d4ca1 "2013-02-12T14:00:00.000-02:00"], :grain :hour}
  ;  Got      {:start #object[org.joda.time.DateTime 0x78ce5efb "2013-02-12T14:00:00.000-02:00"], :grain :hour}
  ;  "nakon 14 sati"
  ;  "iza 14 sati"
  ;  (datetime 2013 2 12 14 :direction :after)

  "nakon 5 dana"
  (datetime 2013 2 17 4 :direction :after)

  "prije 11"
  (datetime 2013 2 12 11 :direction :before)

  "poslijepodne"
  "popodne"
  (datetime-interval [2013 2 12 12] [2013 2 12 20])

  "u 13:30"
  "13:30"
  (datetime 2013 2 12 13 30)

  "za 15 minuta"
  (datetime 2013 2 12 4 45 0)

  "poslije rucka"
  (datetime-interval [2013 2 12 13] [2013 2 12 17])

  "10:30"
  (datetime 2013 2 12 10 30)

  "jutro" ;; how should we deal with fb mornings?
  (datetime-interval [2013 2 12 4] [2013 2 12 12])

  "sljedeci ponedjeljak"
  (datetime 2013 2 18 :day-of-week 1)

  "u 12"
  "u podne"
  (datetime 2013 2 12 12)

  "u 12 u noci"
  "u ponoc"
  (datetime 2013 2 13 0)

  "ozujak"
  "u ozujku"
  (datetime 2013 3))
