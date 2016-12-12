(; Context map
  ; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:reference-time (time/t -2 2013 2 12 4 30 0)}

  "maintenant"
  "tout de suite"
  (datetime 2013 2 12 4 30 00)

  "aujourd'hui"
  "ce jour"
  "dans la journée"
  "en ce moment"
  (datetime 2013 2 12)

  "hier"
  "le jour d'avant"
  "le jour précédent"
  "la veille"
  (datetime 2013 2 11)

  "avant-hier"
  (datetime 2013 2 10)

  "demain"
  "jour suivant"
  "le jour d'après"
  "le lendemain"
  "un jour après"
  (datetime 2013 2 13)

  "après-demain"
  "le lendemain du 13 février"
  (datetime 2013 2 14)

  "lundi"
  "lun."
  "ce lundi"
  (datetime 2013 2 18 :day-of-week 1)

  "lundi 18 février"
  (datetime 2013 2 18 :day-of-week 1 :day 18 :month 2)

  "mardi"
  (datetime 2013 2 19 :day-of-week 2)

  "mercredi 13 février"
  (datetime 2013 2 13 :day-of-week 3 :day 13 :month 2)

  "jeudi"
  "deux jours plus tard"
  "deux jours après"
  (datetime 2013 2 14)

  "vendredi"
  (datetime 2013 2 15)

  "samedi"
  (datetime 2013 2 16)

  "dimanche"
  (datetime 2013 2 17)

  "le 1er mars"
  "premier mars"
  "le 1 mars"
  "vendredi 1er mars"
  (datetime 2013 3 1 :day 1 :month 3)

  "le premier mars 2013"
  "1/3/2013"
  "2013-03-01"
  (datetime 2013 3 1 :day 1 :month 3 :year 2013)

  "le 2 mars"
  "2 mars"
  "le 2/3"
  (datetime 2013 3 2 :day 2 :month 3)

  "le 2 mars à 5h"
  "2 mars à 5h"
  "le 2/3 à 5h"
  "le 2 mars à 5h du matin"
  "le 2 mars vers 5h" ;; FIXME precision is lost
  "2 mars vers 5h"
  "2 mars à environ 5h"
  "2 mars aux alentours de 5h"
  "2 mars autour de 5h"
  "le 2/3 vers 5h"
  (datetime 2013 3 2 5 :day 2 :hour 5)

  "le 2"
  (datetime 2013 3 2 :day 2)

  "le 2 à 5h"
  "le 2 vers 5h"
  "le 2 à 5h du mat"
  (datetime 2013 3 2 5 :day 2 :hour 5)

  "le 3 mars"
  "3 mars"
  "le 3/3"
  (datetime 2013 3 3 :day 3 :month 3)

  "le 5 avril"
  "5 avril"
  (datetime 2013 4 5 :day 5 :month 4)

  ;"le 5 avril à 2h de l'après-midi"
  ;(datetime 2013 4 5 14 :day 5 :month 4 :hour 14)

  "le 3 mars 2015"
  "3 mars 2015"
  "3/3/2015"
  "2015-3-3"
  "2015-03-03"
  (datetime 2015 3 3 :day 3 :month 3 :year 2015)

  "le 15 février"
  "15 février"
  (datetime 2013 2 15 :day 15 :month 2)

  "15/02/2013"
  "15 fev 2013"
  (datetime 2013 2 15 :day 15 :month 2 :year 2013)

  "le 16"
  (datetime 2013 2 16 :day 16)

  "le 16 à 18h"
  "le 16 vers 18h"
  "le 16 plutôt vers 18h"
  "le 16 à 6h du soir"
  "le 16 vers 6h du soir"
  "le 16 vers 6h dans la soirée"
  "samedi 16 à 18h"
  (datetime 2013 2 16 18 :day 16 :hour 18)

  "17 février"
  "le 17 février"
  "17/2"
  "17/02"
  "le 17/02"
  "17 02"
  "17 2"
  "le 17 02"
  "le 17 2"
  (datetime 2013 2 17 :day 17 :month 2)

  "mercredi 13" ; when today is Tuesday 12, "mercredi 13" should be tomorrow
  (datetime 2013 2 13 :day-of-week 3)

  "20/02/2014"
  "20/2/2014"
  "20/02/14"
  "le 20/02/14"
  "le 20/2/14"
  "20 02 2014"
  "20 02 14"
  "20 2 2014"
  "20 2 14"
  "le 20 02 2014"
  "le 20 02 14"
  "le 20 2 2014"
  "le 20 2 14"
  (datetime 2014 2 20 :day 20 :month 2 :year 2014)

  "31 octobre"
  "le 31 octobre"
  "31/10"
  "le 31/10"
  "31 10"
  "le 31 10"
  (datetime 2013 10 31 :day 31 :month 10)

  "24/12/2014"
  "24/12/14"
  "le 24/12/14"
  "24 12 2014"
  "24 12 14"
  "le 24 12 2014"
  "le 24 12 14"
  (datetime 2014 12 24 :day 24 :month 12 :year 2014)

  "31/10/1974"
  "31/10/74" ; smart two-digit year resolution
  (datetime 1974 10 31 :day 31 :month 10 :year 1974)

  "lundi prochain" ; when today is Tuesday, "lundi prochain" is a week from now
  "lundi la semaine prochaine"
  "lundi de la semaine prochaine"
  (datetime 2013 2 18 :day-of-week 1)

  "mardi prochain" ; when today is Tuesday, "mardi prochain" is a week from now
  "mardi suivant"
  "mardi d'après"
  "mardi la semaine prochaine"
  "mardi de la semaine prochaine"
  "mardi la semaine suivante"
  "mardi de la semaine suivante"
  "mardi la semaine d'après"
  "mardi de la semaine d'après"
  (datetime 2013 2 19 :day-of-week 2)

  "mercredi prochain" ; when today is Tuesday, "mercredi prochain" should be tomorrow
  (datetime 2013 2 13 :day-of-week 3)

  "mercredi suivant"
  "mercredi d'après"
  "mercredi la semaine prochaine"
  "mercredi de la semaine prochaine"
  "mercredi la semaine suivante"
  "mercredi de la semaine suivante"
  "mercredi la semaine d'après"
  "mercredi de la semaine d'après"
  (datetime 2013 2 20 :day-of-week 3)

  "lundi en huit" ;
  "lundi en 8"
  (datetime 2013 2 25 :day-of-week 1)

  "mardi en huit" ; today is Tuesday, so "mardi en huit" is in 2 weeks
  "mardi en 8"
  (datetime 2013 2 19 :day-of-week 2)

  "mercredi en huit" ;
  "mercredi en 8"
  (datetime 2013 2 20 :day-of-week 3)

  "lundi en quinze" ;
  "lundi en 15"
  (datetime 2013 3 4 :day-of-week 1)

  "mardi en quinze" ; today is Tuesday, so "mardi en huit" is in 2 weeks
  "mardi en 15"
  (datetime 2013 2 26  :day-of-week 2)

  "mercredi en quinze" ;
  "mercredi en 15"
  (datetime 2013 2 27 :day-of-week 3)

  "lundi cette semaine"
  (datetime 2013 2 11 :day-of-week 1)

  "mardi cette semaine"
  (datetime 2013 2 12 :day-of-week 2)

  "mercredi cette semaine"
  (datetime 2013 2 13 :day-of-week 2)

  ;; Cycles

  "cette semaine"
  "dans la semaine"
  (datetime 2013 2 11 :grain :week)

  "la semaine dernière"
  (datetime 2013 2 4 :grain :week)

  "la semaine prochaine"
  "la semaine suivante"
  "la semaine qui suit"
  (datetime 2013 2 18 :grain :week)

  "le mois dernier"
  (datetime 2013 1)

  "le mois prochain"
  "le mois suivant"
  (datetime 2013 3)

  "l'année dernière"
  (datetime 2012)

  "cette année"
  (datetime 2013)

  "l'année prochaine"
  (datetime 2014)

  "dimanche dernier"
  "dimanche de la semaine dernière"
  (datetime 2013 2 10 :day-of-week 7)

  "3eme jour d'octobre"
  "le 3eme jour d'octobre"
  (datetime 2013 10 3)

  "premiere semaine d'octobre 2014"
  "la premiere semaine d'octobre 2014"
  (datetime 2014 10 6 :grain :week)

  "la semaine du 6 octobre"
  "la semaine du 7 octobre"
  (datetime 2013 10 7 :grain :week)

  "dernier jour d'octobre 2015"
  "le dernier jour d'octobre 2015"
  (datetime 2015 10 31)

  "dernière semaine de septembre 2014"
  "la dernière semaine de septembre 2014"
  (datetime 2014 9 22 :grain :week)

  ;; Hours

  "à quinze heures"
  "à 15 heures"
  "à 3 heures cet après-midi"
  "15h"
  "15H"
  "vers 15 heures"
  "à environ 15 heures"
  (datetime 2013 2 12 15)

  "15:00"
  "15h00"
  "15H00"
  (datetime 2013 2 12 15 0)

  "minuit"
  (datetime 2013 2 13 00)

  "midi"
  "aujourd'hui à midi"
  (datetime 2013 2 12 12)

  "midi et quart"
  "midi quinze"
  (datetime 2013 2 12 12 15 :hour 12 :minute 15)

  "midi moins cinq"
  (datetime 2013 2 12 11 55 :hour 11 :minute 55)

  "midi et demi"
  "midi trente"
  (datetime 2013 2 12 12 30 :hour 12 :minute 30)

  "minuit trois"
  (datetime 2013 2 13 00 03 :hour 0 :minute 3)

  "aujourd'hui à minuit trois"
  (datetime 2013 2 12 00 03 :hour 0 :minute 3)

  "à quinze heures quinze"
  "à quinze heures et quinze minutes"
  "15h passé de 15 minutes"
  "à trois heures et quart cet après-midi"
  "15:15"
  "15h15"
  (datetime 2013 2 12 15 15 :hour 15 :minute 15)

  "à trois heures et quart demain après-midi"
  (datetime 2013 2 13 15 15 :hour 15 :minute 15)

  "à quinze heures trente"
  "à quinze heures passé de trente minutes"
  "à trois heures et demi cet après-midi"
  "15:30"
  "15h30"
  (datetime 2013 2 12 15 30 :hour 15 :minute 30)

  "midi moins le quart"
  "11h45"
  "onze heures trois quarts"
  "aujourd'hui à 11h45"
  (datetime 2013 2 12 11 45 :hour 11 :minute 45)

  "mercredi à 11h"
  (datetime 2013 2 13 11 :hour 11 :day-of-week 3)

  "demain à 11 heures"
  "demain à 11H"
  (datetime 2013 2 13 11 :hour 11)

  "jeudi à 11h"
  "après-demain à 11 heures"
  "après-demain à 11H"
  (datetime 2013 2 14 11 :hour 11)

  "vendredi à midi"
  "vendredi à 12h"
  (datetime 2013 2 15 12 :hour 12 :day-of-week 4)

  "vendredi quinze à seize heures"
  "vendredi 15 à 16h"
  "vendredi quinze à 16h"
  (datetime 2013 2 15 16 :hour 16 :day-of-week 4)


  ;; Involving periods   ; look for grain-after-shift
  "dans une seconde"
  (datetime 2013 2 12 4 30 1)

  "dans une minute"
  "dans 1 min"
  (datetime 2013 2 12 4 31 0)

  "dans 2 minutes"
  "dans deux min"
  (datetime 2013 2 12 4 32 0)

  "dans 60 minutes"
  (datetime 2013 2 12 5 30 0)

  "dans une heure"
  (datetime 2013 2 12 5 30)

  "il y a deux heures"
  (datetime 2013 2 12 2 30)

  "dans 24 heures"
  "dans vingt quatre heures"
  (datetime 2013 2 13 4 30)

  "dans un jour"
  (datetime 2013 2 13 4)

  ;"dans 1 jour à 10h"
  ;(datetime 2013 2 13 10)

  ;"dans 2 jours au soir"
  ;(datetime 2013 2 14 10)

  "dans 7 jours"
  (datetime 2013 2 19 4)

  "dans 1 semaine"
  "dans une semaine"
  (datetime 2013 2 19)

  "il y a trois semaines"
  (datetime 2013 1 22)

  "dans deux mois"
  (datetime 2013 4 12)

  "il y a trois mois"
  (datetime 2012 11 12)

  "dans une année"
  "dans 1 an"
  (datetime 2014 2)

  "il y a deux ans"
  (datetime 2011 2)

  ; Seasons

  "cet été"
  (datetime-interval [2013 6 21] [2013 9 24])

  "cet hiver"
  (datetime-interval [2012 12 21] [2013 3 21])

  ; FR holidays

  "Noel"
  "noël"
  "jour de noel"
  (datetime 2013 12 25)

  "le soir de noël"
  (datetime-interval [2013 12 24 18] [2013 12 25 00])

  "jour de l'an"
  "nouvel an"
  "premier janvier"
  (datetime 2014 1 1)

  "la toussaint"
  "le jour de la toussaint"
  "la journée de la toussaint"
  "toussaint"
  "le jour des morts"
  (datetime 2013 11 1)

  "fête du travail"
  (datetime 2013 05 1)

  ; Part of day (morning, afternoon...)

  "cet après-midi"
  "l'après-midi"
  (datetime-interval [2013 2 12 12] [2013 2 12 19])

  "en milieu d'après-midi"
  (datetime-interval [2013 2 12 15] [2013 2 12 17])

  "en début de matinée"
  "très tôt le matin"
  "tôt le matin"
  "le matin tôt"
  "le matin très tôt"
  (datetime-interval [2013 2 12 4] [2013 2 12 9])

  "milieu de matinée"
  (datetime-interval [2013 2 12 9] [2013 2 12 11])

  "en fin de matinée"
  (datetime-interval [2013 2 12 10] [2013 2 12 12])

  "après déjeuner"
  (datetime-interval [2013 2 12 13] [2013 2 12 17])

  "avant déjeuner"
  (datetime-interval [2013 2 12 10] [2013 2 12 12])

  "pendant le déjeuner"
  "à l'heure du déjeuner"
  (datetime-interval [2013 2 12 12] [2013 2 12 14])

  "après le travail"
  (datetime-interval [2013 2 12 17] [2013 2 12 21])

  "dès le matin"
  "dès la matinée"
  (datetime-interval [2013 2 12 4] [2013 2 12 12])

  "en début d'après-midi"
  (datetime-interval [2013 2 12 12] [2013 2 12 14])

  "en fin d'après-midi"
  (datetime-interval [2013 2 12 17] [2013 2 12 19])

  "en début de journée"
  (datetime-interval [2013 2 12 6] [2013 2 12 10])

  "milieu de journée"
  (datetime-interval [2013 2 12 11] [2013 2 12 16])

  "en fin de journée"
  (datetime-interval [2013 2 12 17] [2013 2 12 21])

  "ce soir"
  (datetime-interval [2013 2 12 18] [2013 2 13 00])

  "en début de soirée"
  (datetime-interval [2013 2 12 18] [2013 2 12 21])

  "en fin de soirée"
  (datetime-interval [2013 2 12 21] [2013 2 13 00])

  "demain soir"
  "mercredi soir"
  "mercredi en soirée"
  (datetime-interval [2013 2 13 18] [2013 2 14 00])

  "hier soir"
  "la veille au soir"
  (datetime-interval [2013 2 11 18] [2013 2 12 00])

  "ce week-end"
  (datetime-interval [2013 2 15 18] [2013 2 18 00])

  "en début de semaine"
  (datetime-interval [2013 2 11] [2013 2 13])

  "en milieu de semaine"
  (datetime-interval [2013 2 13] [2013 2 15])

  "en fin de semaine"
  (datetime-interval [2013 2 14] [2013 2 18])

  "en semaine"
  (datetime-interval [2013 2 11] [2013 2 16])

  "à la fin du mois"
  (datetime-interval [2013 2 19] [2013 3 01])

  ; TODO
  ;"en début de semaine prochaine"
  ;(datetime-interval [2013 2 18] [2013 2 20])

  "le premier week-end de septembre"
  (datetime-interval [2013 9 6 18] [2013 9 9 00])

  "le deuxième week-end de septembre"
  (datetime-interval [2013 9 13 18] [2013 9 16 00])

  "le dernier week-end de septembre"
  (datetime-interval [2013 9 27 18] [2013 9 30 00])

  "lundi matin"
  (datetime-interval [2013 2 18 4] [2013 2 18 12])

  "lundi après-midi"
  "lundi dans l'après-midi"
  (datetime-interval [2013 2 18 12] [2013 2 18 19])

  "lundi fin d'après-midi"
  "lundi en fin d'après-midi"
  (datetime-interval [2013 2 18 17] [2013 2 18 19])

  "le 15 février dans la matinée"
  "matinée du 15 février"
  "le 15 février le matin"
  (datetime-interval [2013 2 15 4] [2013 2 15 12])

  "8 heures ce soir"
  "8h du soir"
  (datetime 2013 2 12 20)

  "3 heures du matin"
  "3h du mat"
  (datetime 2013 2 13 3)

 ; Intervals involving cycles

  "2 dernières secondes"
  "deux dernieres secondes"
  (datetime-interval [2013 2 12 4 29 58] [2013 2 12 4 30 00])

  "3 prochaines secondes"
  "trois prochaines secondes"
  (datetime-interval [2013 2 12 4 30 01] [2013 2 12 4 30 04])

  "2 dernieres minutes"
  "deux dernières minutes"
  (datetime-interval [2013 2 12 4 28] [2013 2 12 4 30])

  "3 prochaines minutes"
  "trois prochaines minutes"
  (datetime-interval [2013 2 12 4 31] [2013 2 12 4 34])

  "3 prochaines heures"
  "3 heures suivantes"
  (datetime-interval [2013 2 12 5] [2013 2 12 8])

  "2 dernier jours"
  "deux derniers jour"
  (datetime-interval [2013 2 10] [2013 2 12])

  "3 prochains jours"
  (datetime-interval [2013 2 13] [2013 2 16])

  "2 dernieres semaines"
  "2 semaines passées"
  (datetime-interval [2013 1 28 :grain :week] [2013 2 11 :grain :week])

  "3 prochaines semaines"
  (datetime-interval [2013 2 18 :grain :week] [2013 3 11 :grain :week])

  "2 derniers mois"
  (datetime-interval [2012 12] [2013 02])

  "3 prochains mois"
  "3 mois suivant"
  (datetime-interval [2013 3] [2013 6])

  "2 dernieres annees"
  "2 années passées"
  (datetime-interval [2011] [2013])

  "3 prochaines années"
  (datetime-interval [2014] [2017])

  ; Explicit intervals

  "13-15 juillet"
  "13 au 15 juillet"
  "13 jusqu'au 15 juillet"
  "13 juillet au 15 juillet"
  "13 juillet - 15 juillet"
  "entre le 13 et le 15 juillet"
  "samedi 13 au dimanche 15 juillet"
  "du samedi 13 au dimanche 15 juillet"
  "du 13 au dimanche 15 juillet"
  (datetime-interval [2013 7 13] [2013 7 16])

  "1er au 10 juillet"
  "lundi 1er au mercredi 10 juillet"
  "lundi 1 au mercredi 10 juillet"
  "du lundi 1er au mercredi 10 juillet"
  "du 1er au mercredi 10 juillet"
  (datetime-interval [2013 7 1] [2013 7 11])

  ;"du 10 au 15" FIXME
  ;(datetime-interval [2013 3 10] [2013 3 16])

  "du 13 au 18"
  "entre le 13 et le 18"
  (datetime-interval [2013 2 13] [2013 2 19])

  "10 juin au 1er juillet"
  "entre le 10 juin et le 1er juillet"
  "du 10 juin au 1er juillet"
  (datetime-interval [2013 6 10] [2013 7 2])

  "de 9h30 jusqu'à 11h jeudi"
  "de 9 heures 30 à 11h jeudi"
  "de 9 heures 30 a 11h jeudi"
  "entre 9h30 et 11h jeudi"
  "jeudi mais entre 9h30 et 11h"
  "jeudi par exemple entre 9h30 et 11h"
  (datetime-interval [2013 2 14 9 30] [2013 2 14 12])

  "9h30 - 11h00 Jeudi"
  (datetime-interval [2013 2 14 9 30] [2013 2 14 11 1])

  "à partir du 8"
  "à partir du 8 mars"
  (datetime 2013 3 8 :direction :after)

  "à partir de 9h30 jeudi"
  "jeudi après 9h30"
  "jeudi matin à partir de 9 heures 30"
  (datetime 2013 2 14 9 30 :direction :after) ; FIXME should be :
  ;(datetime-interval [2013 2 14 9 30] [2013 2 15])

  "après 16h le 1er novembre"
  (datetime 2013 11 1 16 :direction :after) ; FIXME should be :
  ;(datetime-interval [2013 11 1 16] [2013 11 1 24])

  "après le 1er novembre"
  (datetime 2013 11 1 :direction :after)

  "avant 16h"
  "n'importe quand avant 16h"
  (datetime 2013 2 12 16 :direction :before)

  "demain jusqu'à 16h"
  (datetime-interval [2013 2 13 0] [2013 2 13 17])

  "le 20 à partir de 10h"
  (datetime 2013 2 20 10 :direction :after) ; FIXME should be :
  ;(datetime-interval [2013 2 20 10] [2013 2 21])

  "vendredi à partir de midi"
  (datetime 2013 2 15 12 :direction :after) ; FIXME should be :
  ;(datetime-interval [2013 2 15 12] [2013 2 16])

  "le 20 jusqu'à 18h"
  (datetime-interval [2013 2 20] [2013 2 20 19])

  "14 - 20 sept. 2014"
  (datetime-interval [2014 9 14] [2014 9 21])

  ; Alex: need special rule to say that 11h is not ambiguous, because of midi
  ; "ven., 19 septembre, 11h - midi"
  ; (datetime-interval [2014 9 19 11] [2014 9 19 12])
  ; "11h30-13h30" ; TODO
  ; (datetime-interval [2013 2 12 11 30] [2013 2 12 13 30])

  "d'ici 2 semaines"
  (datetime-interval [2013 2 12 4 30 0] [2013 2 26])

  ; 15j != 2 semaines
  "dans les 15 jours"
  (datetime-interval [2013 2 12 4 30 0] [2013 2 27 4]) ;TODO day grain

  "de 5 à 7"
  (datetime-interval [2013 2 12 5] [2013 2 12 8])

  "jeudi de 9h à 11h"
  (datetime-interval [2013 2 14 9] [2013 2 14 12])

  "entre midi et 2"
  (datetime-interval [2013 2 12 12] [2013 2 12 15])

  "11h30-1h30"
  "de 11h30 à 1h30"
  "de 11h30 jusqu'à 1h30"
  (datetime-interval [2013 2 12 11 30] [2013 2 12 13 31])

  "13h30 samedi 21 septembre"
   (datetime 2013 9 21 13 30)

  "à seize heures PST"
  (datetime 2013 2 12 16 :hour 16 :timezone "PST")

  "fin mars"
  "fin du mois de mars"
  (datetime-interval [2013 3 25] [2013 4 1])

  "début avril"
  "début du mois d'avril"
  (datetime-interval [2013 4 1] [2013 4 6])

  "la première quinzaine d'avril"
  (datetime-interval [2013 4 1] [2013 4 15])

  "la deuxième quinzaine d'avril"
  (datetime-interval [2013 4 15] [2013 5 01])

  "début avril"
  "début du mois d'avril"
  (datetime-interval [2013 4 1] [2013 4 6])

  "mi-décembre"
  (datetime-interval [2013 12 10] [2013 12 20])

  "mars"
  "en mars"
  "au mois de mars"
  "le mois de mars"
  (datetime 2013 3)

  "dans un quart d'heure"
  "environ un quart d'heure"
  "dans 1/4h"
  "dans 1/4 h"
  "dans 1/4 heure"
  (datetime 2013 2 12 4 45 0)

  "dans une demi heure"
  "dans 1/2h"
  "dans 1/2 h"
  "dans 1/2 heure"
  (datetime 2013 2 12 5 0 0)

  "dans trois quarts d'heure"
  "dans 3/4h"
  "dans 3/4 h"
  "dans 3/4 heure"
  (datetime 2013 2 12 5 15 0)
)
