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
  (datetime 2013 2 11)

  "avant-hier"
  (datetime 2013 2 10)

  "demain"
  (datetime 2013 2 13)

  "après-demain"
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
  (datetime 2013 3 1 :day 1 :month 3)

  "le premier mars 2013"
  "1/3/2013"
  "2013-03-01"
  (datetime 2013 3 1 :day 1 :month 3 :year 2013)

  "le 2 mars"
  "2 mars"
  "le 2/3"
  (datetime 2013 3 2 :day 2 :month 3)

  "le 2"
  (datetime 2013 3 2 :day 2)

  "le 3 mars"
  "3 mars"
  "le 3/3"
  (datetime 2013 3 3 :day 3 :month 3)

  "le 5 avril"
  "5 avril"
  (datetime 2013 4 5 :day 5 :month 4)

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

  "17 février"
  "17/2"
  "17/02"
  "le 17/02"
  (datetime 2013 2 17 :day 17 :month 2)

  "20 février"
  "20/2"
  (datetime 2013 2 20 :day 20 :month 2)

  "20/02/2013"
  "20/2/2013"
  "20/02/13"
  (datetime 2013 2 20 :day 20 :month 2 :year 2013)
  
  "31/10/1974"
  "31/10/74" ; smart two-digit year resolution
  (datetime 1974 10 31 :day 31 :month 10 :year 1974)

  "mardi prochain" ; when today is Tuesday, "mardi prochain" is a week from now
  (datetime 2013 2 19 :day-of-week 2)

  "mercredi prochain" ; when today is Tuesday, "mercredi prochain" should be tomorrow (will need assumption here)
  "mercredi la semaine prochaine"
  "mercredi de la semaine prochaine"
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
  (datetime 2013 2 18 :grain :week)
  
  "le mois dernier"
  (datetime 2013 1)

  "le mois prochain"
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
  (datetime 2013 10 3)
  
  "premiere semaine d'octobre 2014"
  (datetime 2014 10 6 :grain :week)
  
  "dernier jour d'octobre 2015"
  (datetime 2015 10 31)
  
  "dernière semaine de septembre 2014"
  (datetime 2014 9 22 :grain :week)

  ;; Hours

  "à quinze heures"
  "à 15 heures"
  ;"à 3 heures cet après-midi"
  "15h"
  "15H"
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
  ;"à trois heures et quart cet après-midi" ALEX Conflict with timezone
  "15:15"
  "15h15"
  (datetime 2013 2 12 15 15 :hour 15 :minute 15)

;  "à trois heures et quart demain après-midi"
;  (datetime 2013 2 13 15 15 - 16)

  "à quinze heures trente"
  "à quinze heures passé de trente minutes"
  ;"à trois heures et demi cet après-midi"
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

 ; "jeudi à 11h"
  "après-demain à 11 heures"
  "après-demain à 11H"
  (datetime 2013 2 14 11 :hour 11)

  "vendredi à midi"
  "vendredi à 12h"
  (datetime 2013 2 15 12 :hour 12 :day-of-week 4)


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


  "jour de l'an"
  "nouvel an"
  "premier janvier"
  (datetime 2014 1 1)

  ; Part of day (morning, afternoon...)

  "ce soir"
  (datetime-interval [2013 2 12 18] [2013 2 13 00])

  "demain soir"
  ;"mercredi soir"
  (datetime-interval [2013 2 13 18] [2013 2 14 00])
  
  "hier soir"
  (datetime-interval [2013 2 11 18] [2013 2 12 00])
    
  "ce week-end"
  (datetime-interval [2013 2 15 18] [2013 2 18 00])

  "lundi matin"
  (datetime-interval [2013 2 18 4] [2013 2 18 12])

  "le 15 février dans la matinée" 
  "matinée du 15 février"
  "le 15 février le matin"
  (datetime-interval [2013 2 15 4] [2013 2 15 12])

  "8 heures ce soir"
  "8h du soir"
  (datetime 2013 2 12 20)
  
  "3 heures du matin"
  "3h du mat"
  (datetime 2013 2 12 3)

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
  (datetime-interval [2013 7 13] [2013 7 16])

  "de 9h30 jusqu'à 11h jeudi"
  "de 9 heures 30 à 11h jeudi"
  "entre 9h30 et 11h jeudi"
  (datetime-interval [2013 2 14 9 30] [2013 2 14 12])

  "9h30 - 11h00 Jeudi"
  (datetime-interval [2013 2 14 9 30] [2013 2 14 11 1])

  ;TO BE ADDRESSED
  ;"à partir de 9h30 jeudi"
  
  "14 - 20 sept. 2014"
  (datetime-interval [2014 9 14] [2014 9 21])
  
  ; Alex: need special rule to say that 11h is not ambiguous, because of midi
  ; "ven., 19 septembre, 11h - midi"
  ; (datetime-interval [2014 9 19 11] [2014 9 19 12])
  ; "11h30-13h30" ; TODO
  ; (datetime-interval [2013 2 12 11 30] [2013 2 12 13 30])

  "d'ici 2 semaines"
  (datetime-interval [2013 2 12 4 30 0] [2013 2 26])

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
  
)
