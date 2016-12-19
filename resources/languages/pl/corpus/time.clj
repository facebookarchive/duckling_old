(
  ; Context map
  ; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:reference-time (time/t -2 2013 2 12 4 30 0)
   :min (time/t -2 1900)
   :max (time/t -2 2100)}

  "teraz"
  "w tej chwili"
  "w tym momencie"
  (datetime 2013 2 12 4 30 00)

 "dziś"
 "dzis"
 "dzisiaj"
 "obecnego dnia"
 "tego dnia"
 (datetime 2013 2 12)

  "wczoraj"
  (datetime 2013 2 11)

  "jutro"
  (datetime 2013 2 13)

 "pojutrze"
 "po jutrze"
 (datetime 2013 2 14)

 "poniedziałek"
 "pon."
 "ten poniedziałek"
 (datetime 2013 2 18 :day-of-week 1)

 "Poniedziałek, 18 Luty"
 "Poniedziałek, Luty 18"
 "Poniedziałek 18tego Lutego"
 "Poniedziałek 18-tego Lutego"
 "Poniedziałek, 18-tego Lutego"
 "poniedzialek, 18go Lutego"
 "Pon, 18 Luty"
 (datetime 2013 2 18 :day-of-week 1 :day 18 :month 2)

 "Sobota, 2ego Lutego"
 (datetime 2013 2 2 :day-of-week 2 :day 2 :month 2)

 "Wtorek"
 "nastepny wtorek"
 "wt."
 "wtr."
 (datetime 2013 2 19)

  "czwartek"
  "ten czwartek"
  "czw"
  "czw."
  (datetime 2013 2 14)

  "piatek"
  "ten piatek"
  "pia"
  "pia."
  (datetime 2013 2 15)

  "sobota"
  "ta sobota"
  "sob"
  "sob."
  (datetime 2013 2 16)

  "niedziela"
  "ta niedziela"
  "niedz"
  "niedz."
  (datetime 2013 2 17)

 "pierwszy marca"
 "pierwszego marca"
 "marzec pierwszy"
 "1szy marca"
 "1szy marzec"
  (datetime 2013 3 1 :day 1 :month 3)

 "marzec 3"
 "marzec 3ci"
 "3go marca"
 (datetime 2013 3 3 :day 3 :month 3)

 ;; ;;coto
 ;; "the ides of march"
 ;; (datetime 2013 3 15 :month 3)

 "3ci marca 2015"
 "marzec 3ci 2015"
 "3 marzec 2015"
 "marzec 3 2015"
 "trzeci marca 2015"
 "3/3/2015"
 "3/3/15"
 "2015-3-3"
 "2015-03-03"
 (datetime 2015 3 3 :day 3 :month 3 :year 2015)

 ;; TODO FIX??
 ;; "na 15"
 ;; "na 15tego"
 ;; (datetime 2013 2 15 :day 15)

 "15 Luty"
 "15 Lutego"
 "Luty 15"
 "15-tego Lutego"
 "2/15"
 ;;"on 2/15" POLISH PLZ
 "Pietnastego Lutego"
 "Piętnasty Luty"
 "Luty Piętnastego"
 (datetime 2013 2 15 :day 15 :month 2)

 "Sierpień 8"
 "Sie 8"
 "Sier 8"
 "Sierp. 8"
 "8 Sie."
 "Ósmy Sie."
 "Osmego Sie."
 (datetime 2013 8 8 :day 8 :month 8)

 "Październik 2014"
 "Pazdziernika 2014"
 (datetime 2014 10 :year 2014 :month 10)

 "10/31/1974"
 "10/31/74"
 "10-31-74"
 (datetime 1974 10 31 :day 31 :month 10 :year 1974)

 "14kwiecien 2015"
 "Kwiecień 14, 2015"
 "14tego Kwietnia 15"
 "14-tego Kwietnia 15"
 "14-ty Kwietnia 15"
 "Czternasty Kwietnia 15"
 "Czternastego Kwietnia 15"
 (datetime 2015 4 14 :day 14 :month 4 :years 2015)

 "nastepny wtorek"
 "kolejny wtorek"
 "kolejnego wtorku"
 "nastepnego wtorku"
 "wtorek w przyszłym tygodniu"
 "wtorek za tydzień"
 (datetime 2013 2 19 :day-of-week 2)

 "piatek po nastepnym" ;;DO PPL SAY IT THAT WAY? - [NOPE THEY DON'T]
 (datetime 2013 2 22 :day-of-week 2)

 "nastepny Marzec"
 (datetime 2013 3)

 "Marzec po nastepnym" ;; [REMOVE]
 (datetime 2014 3)

 "Niedziela, 10 Luty"
 "Niedziela, Luty 10"
 "Niedziela, 10tego Luty"
 "Niedziela, 10-tego Luty"
 "Niedziela, 10-ty Lutego"
 "Niedziela, 10tego Lutego"
 (datetime 2013 2 10 :day-of-week 7 :day 10 :month 2)

 "Śr., Luty13"
 "Śr., 13Luty"
 "sr, 13Luty"
 "sr, 13tego Lutego"
 "Śro., 13Lutego"
 "Środa trzynastego lutego"
 (datetime 2013 2 13 :day-of-week 3 :day 13 :month 2)

 "Poniedziałek, Luty 18"
 "Poniedziałek, 18 Lutego"
 "Pon, Luty 18"
 (datetime 2013 2 18 :day-of-week 1 :day 18 :month 2)

 ;; ;   ;; Cycles

 "ten tydzien"
 "ten tydzień"
 "ten tyg"
 "tym tygodniu"
 (datetime 2013 2 11 :grain :week)

 "ostatni tydzien"
 "poprzedniego tygodnia"
 (datetime 2013 2 4 :grain :week)

 "nastepny tydzien"
 "nastepnego tygodnia"
 (datetime 2013 2 18 :grain :week)

 "ostatni miesiac"
 "poprzedni miesiac"
 "poprzedniego miesiąca"
 "po przedniego miesiąca"
 "ostatniego miesiaca"
 (datetime 2013 1)

 "nastepnego miesiaca"
 (datetime 2013 3)

 "ten kwartał"
 "tego kwartału"
 "tym kwartale"
 (datetime 2013 1 1 :grain :quarter)

 "nastepny kwartał"
 "następny kwartal"
 "kolejnym kwartale"
 (datetime 2013 4 1 :grain :quarter)

 "trzeci kwartał"
 (datetime 2013 7 1 :grain :quarter)

  "4ty kwartał 2018"
  (datetime 2018 10 1 :grain :quarter)

 "poprzedni rok"
 "ostatni rok"
 (datetime 2012)

 "ten rok"
 "tym roku"
 "obecny rok"
 "w obecny rok"
 "w obecnym roku"
 (datetime 2013)

 "w kolejnym roku"
 "kolejny rok"
 (datetime 2014)

 "poprzednia niedziela"
 "niedziela z ostatniego tygodnia" ;; [I can see someone say that]
 "niedziela ostatniego tygodnia"
 "niedziela poprzedniego tygodnia"
 "ostatnia niedziela"
 (datetime 2013 2 10 :day-of-week 7)

 "ostatni wtorek"
 "poprzedni wtorek"
 (datetime 2013 2 5 :day-of-week 2)

  "nastepny wtorek" ; when today is Tuesday, "mardi prochain" is a week from now
  (datetime 2013 2 19 :day-of-week 2)

  "nastepna środa" ; when today is Tuesday, "mercredi prochain" is tomorrow
  (datetime 2013 2 13 :day-of-week 3)

  ;;"wednesday of next week"
  "sroda nastepnego tygodnia"
  "środa w przyszłym tygodniu"
  "środa za tydzień"
  (datetime 2013 2 20 :day-of-week 3)

 "piatek nastepnego tygodnia"
 (datetime 2013 2 22 :day-of-week 5)

 "poniedzialek tego tygodnia"
 (datetime 2013 2 11 :day-of-week 1)

 "wtorek tego tygodnia"
 "wtorek w tym tygodniu"
 "ten wtorek"
  (datetime 2013 2 12 :day-of-week 2)

  "środa w tym tygodniu"
  "ta środa"
  (datetime 2013 2 13 :day-of-week 3)

 "pojutrze"
 "po jutrze"
 "dzień po jutrze"
 (datetime 2013 2 14)

  "dzień przed wczoraj"
  (datetime 2013 2 10)

  "ostatni Poniedziałek Marca"
  (datetime 2013 3 25 :day-of-week 1)

 "ostatnia Niedziela w Marcu 2014"
 "ostatnia Niedziela marca 2014"
  (datetime 2014 3 30 :day-of-week 7)

 "trzeci dzień października"
 "trzeci dzień w październiku"
 (datetime 2013 10 3)

 "pierwszy tydzień października 2014"
 "pierwszy tydzien w październiku 2014"
 (datetime 2014 10 6 :grain :week)

 ;; TODO JAK TO BEDZIE PO PL? "Tydzien w ktorym jest 6ty paź"?
 ;;   "the week of october 6th"
 ;;   "the week of october 7th"
 ;;   (datetime 2013 10 7 :grain :week)

 "ostatni dzień w październiku 2015"
 "ostatni dzień października 2015"
 (datetime 2015 10 31)

 "ostatni tydzień we wrześniu 2014"
 "ostatni tydzień września 2014"
 (datetime 2014 9 22 :grain :week)


  ;; nth of
  "pierwszy wtorek w październiku"
  "pierwszy wtorek października"
  (datetime 2013 10 1)

 "trzeci wtorek we wrześniu 2014"
 "trzeci wtorek września 2014"
  (datetime 2014 9 16)

 "pierwsza środa w październiku 2014"
 "pierwsza środa października 2014"
 (datetime 2014 10 1)

 "druga środa w październiku 2014"
 "druga środa października 2014"
 (datetime 2014 10 8)

;; Hours

 "o 3 rano"
 "3 rano"
 "3 z rana"
 "o trzeciej rano"
 "o trzeciej z rana"
 (datetime 2013 2 13 3)

 "3:18 rano"
 (datetime 2013 2 12 3 18)

 "o trzeciej"
 "o 3 po południu"
 "3 po południu"
 "3 popołudniu"
 "trzecia popoludniu"
 "o trzeciej popoludniu"
 "piętnasta godzina"
 "15sta godzina"
 "o piętnastej"
 "o 15stej"
 (datetime 2013 2 12 15 :hour 3 :meridiem :pm)

 "6 po południu"
 "6 popołudniu"
 "szósta popoludniu"
 "o szostej popoludniu"
 "o 18stej"
 "osiemnasta godzina"
 "o osiemnastej"
 (datetime 2013 2 12 18 :hour 6 :meridiem :pm)

 "7 po południu"
 "7 popołudniu"
 "siódma popoludniu"
 "o siodmej popoludniu"
 "o dziewiętnastej"
 "dziewietnasta godzina"
 (datetime 2013 2 12 19 :hour 7 :meridiem :pm)

 "8 wieczorem"
 "8 popołudniu"
 "osma w nocy"
 "ósma wieczorem"
 "dwudziesta godzina"
 (datetime 2013 2 12 20 :hour 8 :meridiem :pm)

 "dziewiata wieczorem"
 "dziewiąta popołudniu"
 "dziewiata po południu"
 "dziewiąta wieczorem"
 "dziewiąta nocą"
 "dziewiąta w nocy"
 "9 wieczorem"
 "9 popołudniu"
 "9 po południu"
 "9 wieczorem"
 "9 nocą"
 "9 w nocy"
 "o dziewiatej w nocy"
 "dwudziesta pierwsza godzina"
 "dwudziestapierwsza godzina"
 (datetime 2013 2 12 21 :hour 9 :meridiem :pm)

 "dziesiąta wieczorem"
 "dziesiata popołudniu"
 "dziesiata po południu"
 "dziesiata wieczorem"
 "dziesiata nocą"
 "10 w nocy"
 "o dziesiatej w nocy"
 "o dwudziestej drugiej"
 (datetime 2013 2 12 22 :hour 10 :meridiem :pm)

 "jedenasta wieczorem"
 "jedenasta w nocy"
 "11 w nocy"
 "11 wieczorem"
 "o jedenastej wieczorem"
 "o dwudziestejtrzeciej"
 (datetime 2013 2 12 23 :hour 11 :meridiem :pm)

 "jutro o drugiej"
 (datetime 2013 2 13 2)

 "po jutrze o drugiej"
 (datetime 2013 2 14 2)

 ;;   "3ish pm" ;; FIXME pm overrides precision
 ;;   "3pm approximately"
 "około 3 po południu"
 "około trzeciej"
 "koło trzeciej"
 "o koło trzeciej"
 "mniej wiecej o 3"
 "tak o 15stej"
 ;;   "at about 3pm"
 (datetime 2013 2 12 15 :hour 3 :meridiem :pm) ;; :precision "approximate"

 ;;   "tomorrow 5pm sharp" ;; FIXME precision is lost
 "jutro równo o piątej popołudniu"
 "jutro równo o 17-stej"
 (datetime 2013 2 13 17 :hour 5 :meridiem :pm) ;; :precision "exact"

 ;;   "at 15 past 3pm"
 "piętnaście po trzeciej"
 "15 po trzeciej"
 "kwadrans po 3"
 "trzecia piętnaście"
 ;;   "3:15 in the afternon"
 "15:15"
 ;; "3:15pm"
 ;; "3:15PM"
 ;; "3:15p"
 (datetime 2013 2 12 15 15 :hour 3 :minute 15 :meridiem :pm)

 "20 po 3"
 "3:20"
 "3:20 w poludnie"
 "trzecia dwadzieścia"
 (datetime 2013 2 12 15 20 :hour 3 :minute 20 :meridiem :pm)

 ;;   "at half past three pm"
 "w pół do szesnastej"
 "pol po trzeciej"
 "15:30"
 ;;   "3:30pm"
 ;;   "3:30PM"
 ;;   "330 p.m."
 ;;   "3:30 p m"
 (datetime 2013 2 12 15 30 :hour 3 :minute 30 :meridiem :pm)

  "3:30"
;;   "half three"
 (datetime 2013 2 12 15 30 :hour 3 :minute 30)

  "15:23:24"
 (datetime 2013 2 12 15 23 24 :hour 15 :minute 23 :second 24)

 "kwadrans do południa"
 "kwadrans przed południem"
 "kwadrans do 12stej"
 "11:45"
 (datetime 2013 2 12 11 45 :hour 11 :minute 45)

  ;;"8 dziś wieczorem" FIX Should produce both the interval and time?
  "8 wieczorem"
  "8 tego wieczora"
  (datetime 2013 2 12 20)

  ;; Mixing date and time

 "o 7:30 popołudniu Piatek, 20 Wrzesień"
 "o 7:30 popołudniu Piatek, Wrzesień 20"
 (datetime 2013 9 20 19 30 :hour 7 :minute 30 :meridiem :pm)

  "o 9 rano w Sobote"
  "w Sobote na 9 rano"
  (datetime 2013 2 16 9 :day-of-week 6 :hour 9 :meridiem :am)

  "Pia, Lip 18, 2014 19:00"
  (datetime 2014 7 18 19 0 :day-of-week 5 :hour 7 :meridiem :pm)


;; ; ;; Involving periods

 "w sekundę"
 "za sekundę"
 "sekunde od teraz"
 (datetime 2013 2 12 4 30 1)

 "za minutę"
 "za jedną minutę"
 "przez minutę"
 (datetime 2013 2 12 4 31 0)

 "w 2 minuty"
 "za jeszcze 2 minuty"
 "2 minuty od teraz"
 (datetime 2013 2 12 4 32 0)

 "w 60 minut"
 (datetime 2013 2 12 5 30 0)

 "w pół godziny"
 (datetime 2013 2 12 5 0 0)

 "w 2.5 godziny"
 "w 2 i pół godziny"
 (datetime 2013 2 12 7 0 0)

 "w godzinę"
 "w 1h"
 "w przeciągu godziny"
 (datetime 2013 2 12 5 30)


 "w kilka godzin"
 (datetime 2013 2 12 7 30)

 "w 24 godziny"
 (datetime 2013 2 13 4 30)

 "w jeden dzień"
 "dzień od dziś"
 (datetime 2013 2 13 4)

 "3 lata od dziś"
 ;;"za trzy lata od dzisiaj" Gives the correct result but produces two
 ;;identical winners
 (datetime 2016 2)

 "w 7 dni"
 (datetime 2013 2 19 4)

 "w jeden tydzień"
 "w tydzień"
 (datetime 2013 2 19)

 "za około pół godziny" ;; FIXME precision is lost
 "za jakieś pół godziny"
 (datetime 2013 2 12 5 0 0) ;; :precision "approximate"

  "7 dni temu"
  (datetime 2013 2 5 4)

  "14 dni temu"
  ;;"a fortnight ago"
  (datetime 2013 1 29 4)

  "tydzien temu"
  "jeden tydzień temu"
  "1 tydzień temu"
  (datetime 2013 2 5)

  "trzy tygodnie temu"
  (datetime 2013 1 22)

  "trzy miesiące temu"
  (datetime 2012 11 12)

  "dwa lata temu"
  (datetime 2011 2)

  "7 dni potem"
  (datetime 2013 2 19 4)

  "14 dni później"
  ;;"a fortnight hence"
  (datetime 2013 2 26 4)

  "tydzień później"
  "jeden tydzień później"
  "1 tydzień później"
  (datetime 2013 2 19)

  "trzy tygodnie później"
  (datetime 2013 3 5)

  "trzy miesiące później"
  (datetime 2013 5 12)

  "dwa lata później"
  (datetime 2015 2)

;;   ; Seasons

  "to lato"
  "w to lato"
  (datetime-interval [2013 6 21] [2013 9 24])

  "ta zima"
  "tej zimy"
  (datetime-interval [2012 12 21] [2013 3 21])

;;   ; US holidays (http://www.timeanddate.com/holidays/us/)
  "Wigilia Bożego Narodzenia"
  "Wigilia"
  (datetime 2013 12 24)

  "święta Bożego Narodzenia"
  "boże narodzenie"
  (datetime 2013 12 25)

  "sylwester"
  (datetime 2013 12 31)

 "walentynki"
  (datetime 2013 2 14)

;;   "memorial day"
;;   (datetime 2013 5 27)

  "Dzień Mamy"
  (datetime 2013 5 12)

  "Dzień Taty"
  (datetime 2013 6 16)

 ;;   "memorial day week-end"
;;   (datetime-interval [2013 5 24 18] [2013 5 28 0])

;;   "independence day"
;;   "4th of July"
;;   "4 of july"
;;   (datetime 2013 7 4)

;;   "labor day"
;;   (datetime 2013 9 2)

  "halloween"
  (datetime 2013 10 31)

  "dzień dziękczynienia"
  "dziękczynienie"
  (datetime 2013 11 28)

 ;;   ; Part of day (morning, afternoon...)

 "ten wieczór"
 "dzisiejszy wieczór"
 (datetime-interval [2013 2 12 18] [2013 2 13 00])

 "jutrzejszy wieczór"
 "Środowy wieczór"
 "jutrzejsza noc"
 (datetime-interval [2013 2 13 18] [2013 2 14 00])

 ;;   "tomorrow lunch"
 ;;   "tomorrow at lunch"
 ;;   (datetime-interval [2013 2 13 12] [2013 2 13 14])

   "wczorajszy wieczór"
   (datetime-interval [2013 2 11 18] [2013 2 12 00])

 "ten week-end"
 "ten weekend"
 "ten wekend"
 (datetime-interval [2013 2 15 18] [2013 2 18 00])

  "poniedziałkowy poranek"
  (datetime-interval [2013 2 18 4] [2013 2 18 12])


 ;;  TODO
 ;; "luty 15tego o poranku"
 ;; "15 lutego o poranku"
 ;; "poranek 15tego lutego"
 ;; (datetime-interval [2013 2 15 4] [2013 2 15 12])


;;   ; Intervals involving cycles

 "ostatnie 2 sekundy"
 "ostatnie dwie sekundy"
 (datetime-interval [2013 2 12 4 29 58] [2013 2 12 4 30 00])

 "kolejne 3 sekundy"
 "kolejne trzy sekundy"
 (datetime-interval [2013 2 12 4 30 01] [2013 2 12 4 30 04])

 "ostatnie 2 minuty"
 "ostatnie dwie minuty"
 (datetime-interval [2013 2 12 4 28] [2013 2 12 4 30])

 "kolejne 3 minuty"
 "nastepne trzy minuty"
  (datetime-interval [2013 2 12 4 31] [2013 2 12 4 34])

 "ostatnia 1 godzina"
 "poprzednia jedna godzina"
 (datetime-interval [2013 2 12 3] [2013 2 12 4])

  "kolejne 3 godziny"
  "kolejne trzy godziny"
  (datetime-interval [2013 2 12 5] [2013 2 12 8])

 "ostatnie 2 dni"
 "ostatnie dwa dni"
 "poprzednie 2 dni"
 (datetime-interval [2013 2 10] [2013 2 12])

  "nastepne 3 dni"
  "nastepne trzy dni"
  (datetime-interval [2013 2 13] [2013 2 16])

  "nastepne kilka dni"
  (datetime-interval [2013 2 13] [2013 2 16])

  "ostatnie 2 tygodnie"
  "ostatnie dwa tygodnie"
  "poprzednie 2 tygodnie"
  (datetime-interval [2013 1 28 :grain :week] [2013 2 11 :grain :week])

  "nastepne 3 tygodnie"
  "nastepne trzy tygodnie"
  (datetime-interval [2013 2 18 :grain :week] [2013 3 11 :grain :week])

  "ostatnie 2 miesiace"
  "ostatnie dwa miesiące"
  (datetime-interval [2012 12] [2013 02])

  "nastepne trzy miesiące"
  (datetime-interval [2013 3] [2013 6])

  "ostatnie 2 lata"
  "ostatnie dwa lata"
  (datetime-interval [2011] [2013])

 "nastepne 3 lata"
 "kolejne trzy lata"
 (datetime-interval [2014] [2017])


;;   ; Explicit intervals

 "Lipiec 13-15"
 "Lipca 13 do 15"
 ;; "Lipca 13tego do 15tego" ;;FIX gives hours instaed of dates
 "Lipiec 13 - Lipiec 15"
 (datetime-interval [2013 7 13] [2013 7 16])

  "Sie 8 - Sie 12"
  (datetime-interval [2013 8 8] [2013 8 13])

  "9:30 - 11:00"
  (datetime-interval [2013 2 12 9 30] [2013 2 12 11 1])

  "od 9:30 - 11:00 w Czwartek"
  "miedzy 9:30 a 11:00 w czwartek"
  "9:30 - 11:00 w czwartek"
  "pozniej niż 9:30 ale przed 11:00 w Czwartek"
  "Czwartek od 9:30 do 11:00"
  (datetime-interval [2013 2 14 9 30] [2013 2 14 11 1])

  "Czwartek od 9 rano do 11 rano"
  (datetime-interval [2013 2 14 9] [2013 2 14 12])

  "11:30-1:30" ; go train this rule!
  "11:30-1:30"
  "11:30-1:30"
  "11:30-1:30"
  "11:30-1:30"
  "11:30-1:30"
  "11:30-1:30"
  (datetime-interval [2013 2 12 11 30] [2013 2 12 13 31])

;;   "1:30 PM on Sat, Sep 21"
;;   (datetime 2013 9 21 13 30)

 "w ciągu 2 tygodni"
 "w ciągu dwóch tygodni"
 (datetime-interval [2013 2 12 4 30 0] [2013 2 26])

 "przed drugą po południu"
 "przed drugą"
 (datetime 2013 2 12 14 :direction :before)

 ;;   "by 2:00pm"
 ;;   (datetime-interval [2013 2 12 4 30 0] [2013 2 12 14])

 ;;   "by EOD"
 ;;   (datetime-interval [2013 2 12 4 30 0] [2013 2 13 0])

 ;;   "by EOM"
 ;;   (datetime-interval [2013 2 12 4 30 0] [2013 3 1 0])

 ;;   "by the end of next month"
 ;;   (datetime-interval [2013 2 12 4 30 0] [2013 4 1 0])
 ;;   ; Timezones

 ;;   "4pm CET"
 ;;   (datetime 2013 2 12 16 :hour 4 :meridiem :pm :timezone "CET")

 ;;   "Thursday 8:00 GMT"
 ;;   (datetime 2013 2 14 8 00 :timezone "GMT")

  ;; Bookface tests
 "dziś o drugiej w południe"
 "o drugiej popołudniu"
 (datetime 2013 2 12 14)

 "4/25 o 4 popołudniu"
 "4/25 o 16"
 "4/25 o szesnastej"
 (datetime 2013 4 25 16)

 "3 popoludniu jutro"
 (datetime 2013 2 13 15)

 "po drugiej po poludniu"
 (datetime 2013 2 12 14 :direction :after)

 "po pięciu dniach"
 (datetime 2013 2 17 4 :direction :after)

 "po drugiej po południu"
 (datetime 2013 2 12 14 :direction :after)

 "przed 11 rano"
 (datetime 2013 2 12 11 :direction :before)

 "jutro przed 11 rano" ;; FIXME this is actually not ambiguous. it's midnight to 11 am
  (datetime 2013 2 13 11 :direction :before)

  "w południe"
  (datetime-interval [2013 2 12 12] [2013 2 12 19])

;;   "at 1:30pm"
;;   "1:30pm"
;;   (datetime 2013 2 12 13 30)

 "w 15 minut"
 "w piętnaście minut"
 (datetime 2013 2 12 4 45 0)

;;   "after lunch"
;;   (datetime-interval [2013 2 12 13] [2013 2 12 17])

 "10:30"
 (datetime 2013 2 12 10 30)

;;   "morning" ;; how should we deal with fb mornings?
;;   (datetime-interval [2013 2 12 4] [2013 2 12 12])

 "nastepny pon"
 "kolejny poniedziałek"
  (datetime 2013 2 18 :day-of-week 1)
)
