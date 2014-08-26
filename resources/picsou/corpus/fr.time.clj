(; Context map
  ; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:reference-time (time/local-date-time [2013 2 12 4 30])}

  "maintenant"
  "tout de suite"
  (datetime 2013 2 12 4 30 seconds)
  
  "aujourd'hui"
  "ce jour"
  "dans la journée"
  "en ce moment"
  (datetime 2013 2 12 days)

  "hier"
  (datetime 2013 2 11 days)

  "avant-hier"
  (datetime 2013 2 10 days)

  "demain"
  (datetime 2013 2 13 days)

  "après-demain"
  (datetime 2013 2 14 days)

  "lundi"
  "lun."
  "ce lundi"
  (datetime 2013 2 18 days :day-of-week 1)

  "mardi"
  (datetime 2013 2 19 days :day-of-week 2)

  "mercredi 13 février"
  (datetime 2013 2 13 days :day-of-week 3 :day 13 :month 2)

;"ce week-end"
  ;(datetime 2013 2 15 18 - 18 0)

  "dimanche dernier"
  "dimanche la semaine dernière"
  "dimanche de la semaine derniere"
  "dimanche la semaine passée"
  (datetime 2013 2 10 days :day-of-week 7)

  "mardi dernier"
  (datetime 2013 2 5 days :day-of-week 2)

  "le 1er mars"
  "premier mars"
  "le 1 mars"
  (datetime 2013 3 1 days :day 1 :month 3)

  "le premier mars 2013"
  "1/3/2013"
  "2013-03-01"
  (datetime 2013 3 1 days :day 1 :month 3 :year 2013)

  "le 2 mars"
  "2 mars"
  "le 2/3"
  (datetime 2013 3 2 days :day 2 :month 3)

  "le 3 mars"
  "3 mars"
  "le 3/3"
  (datetime 2013 3 3 days :day 3 :month 3)

  "le 5 avril"
  "5 avril"
  (datetime 2013 4 5 days :day 5 :month 4)

  "le 3 mars 2015"
  "3 mars 2015"
  "3/3/2015"
  "2015-3-3"
  "2015-03-03"
  (datetime 2015 3 3 days :day 3 :month 3 :year 2015)

  "le 15 février"
  "15 février"
  (datetime 2013 2 15 days :day 15 :month 2) 
   
  "15/02/2013"
  "15 fev 2013"
  (datetime 2013 2 15 days :day 15 :month 2 :year 2013)

  "le 16"
  (datetime 2013 2 16 days :day 16)

  "17 février"
  "17/2"
  "17/02"
  "le 17/02"
  (datetime 2013 2 17 days :day 17 :month 2)

  "20 février"
  "20/2"
  (datetime 2013 2 20 days :day 20 :month 2)

  "20/02/2013"
  "20/2/2013"
  "20/02/13"
  (datetime 2013 2 20 days :day 20 :month 2 :year 2013)
  
  "31/10/1974"
  "31/10/74" ; smart two-digit year resolution
  (datetime 1974 10 31 days :day 31 :month 10 :year 1974)

  "mardi prochain" ; when today is Tuesday, "mardi prochain" is a week from now
  (datetime 2013 2 19 days :day-of-week 2)

  "mercredi prochain" ; when today is Tuesday, "mercredi prochain" is tomorrow
  (datetime 2013 2 13 days :day-of-week 3)

  "mercredi la semaine prochaine"
  "mercredi de la semaine prochaine"
  (datetime 2013 2 20 days :day-of-week 3)

  "lundi en huit" ;
  "lundi en 8"
  (datetime 2013 2 25 days :day-of-week 1)

  "mardi en huit" ; today is Tuesday, so "mardi en huit" is in 2 weeks
  "mardi en 8"
  (datetime 2013 2 26 days :day-of-week 2)

  "mercredi en huit" ;
  "mercredi en 8"
  (datetime 2013 2 20 days :day-of-week 3)

  "lundi en quinze" ;
  "lundi en 15"
  (datetime 2013 3 4 days :day-of-week 1)

  "mardi en quinze" ; today is Tuesday, so "mardi en huit" is in 2 weeks
  "mardi en 15"
  (datetime 2013 3 5 days :day-of-week 2)

  "mercredi en quinze" ;
  "mercredi en 15"
  (datetime 2013 2 27 days :day-of-week 3)

  "cette semaine"
  "dans la semaine"
  (datetime 2013 2 11 weeks)

  "lundi cette semaine"
  (datetime 2013 2 11 days :day-of-week 1)

  "mardi cette semaine"
  (datetime 2013 2 12 days :day-of-week 2)

  "mercredi cette semaine"
  (datetime 2013 2 13 days :day-of-week 3)

;; Cycles
  
  "la semaine dernière"
  (datetime 2013 2 4 weeks)
  
  "la semaine prochaine"
  (datetime 2013 2 18 weeks)
  
  "le mois dernier"
  (datetime 2013 1 months)

  "le mois prochain"
  (datetime 2013 3 months)
  
  "l'année dernière"
  (datetime 2012 years)
  
  "cette année"
  (datetime 2013 years)
  
  "l'année prochaine"
  (datetime 2014 years)

;; Hours

  "à quinze heures"
  "à 15 heures"
  ;"à 3 heures cet après-midi"
  "15h"
  "15H"
  (datetime 2013 2 12 15 hours :hour 15)

  "15:00"
  "15h00"
  "15H00"
  (datetime 2013 2 12 15 minutes :hour 15 :minute 0)

  ;"minuit"
  ;(datetime 2013 2 13 00 - 01)

  ;"midi"
  ;"aujourd'hui à midi"
  ;(datetime 2013 2 12 12 - 13)

  "midi et quart"
  "midi quinze"
  (datetime 2013 2 12 12 15 minutes :hour 12 :minute 15)

  "midi moins cinq"
  (datetime 2013 2 12 11 55 minutes :hour 11 :minute 55)

  "midi et demi"
  "midi trente"
  (datetime 2013 2 12 12 30 minutes :hour 12 :minute 30)

  "minuit trois"
  (datetime 2013 2 13 00 03 minutes :hour 0 :minute 3)

  "aujourd'hui à minuit trois"
  (datetime 2013 2 12 00 03 minutes :hour 0 :minute 3)

  "à quinze heures quinze"
  "à quinze heures et quinze minutes"
  "15h passé de 15 minutes"
  ;"à trois heures et quart cet après-midi"
  "15:15"
  "15h15"
  (datetime 2013 2 12 15 15 minutes :hour 15 :minute 15)

;  "à trois heures et quart demain après-midi"
;  (datetime 2013 2 13 15 15 - 16)

  "à quinze heures trente"
  "à quinze heures passé de trente minutes"
  ;"à trois heures et demi cet après-midi"
  "15:30"
  "15h30"
  (datetime 2013 2 12 15 30 minutes :hour 15 :minute 30)

  "midi moins le quart"
  "11h45"
  "onze heures trois quarts"
  "aujourd'hui à 11h45"
  (datetime 2013 2 12 11 45 minutes :hour 11 :minute 45)
  
  "mercredi à 11h"
  (datetime 2013 2 13 11 hours :hour 11 :day-of-week 3)

  "demain à 11 heures"
  "demain à 11H"
  (datetime 2013 2 13 11 hours :hour 11)

 ; "jeudi à 11h"
  "après-demain à 11 heures"
  "après-demain à 11H"
  (datetime 2013 2 14 11 hours :hour 11)

  "vendredi à midi"
  "vendredi à 12h"
  (datetime 2013 2 15 12 hours :hour 12 :day-of-week 4)


;; Involving durations  
  "dans une seconde"
  (datetime 2013 2 12 4 30 1 seconds)
  
  "dans une minute"
  "dans 1 min"
  (datetime 2013 2 12 4 31 minutes)
  
  "dans 2 minutes"
  "dans deux min"
  (datetime 2013 2 12 4 32 minutes)
  
  "dans 60 minutes"
  (datetime 2013 2 12 5 30 minutes)
  
  "dans une heure"
  (datetime 2013 2 12 5 30 hours)

  "il y a deux heures"
  (datetime 2013 2 12 2 30 hours)
  
  "dans 24 heures"
  "dans vingt quatre heures"
  (datetime 2013 2 13 4 30 hours)
  
  "dans un jour"
  (datetime 2013 2 13 4 30 days)
  
  "dans 7 jours"
  (datetime 2013 2 19 4 30 days)
  
  "dans 1 semaine"
  "dans une semaine"
  (datetime 2013 2 19 4 30 weeks)
  
  "il y a trois semaines"
  (datetime 2013 1 22  4 30 weeks)
  
  "dans deux mois"
  (datetime 2013 4 12 4 30 months)
  
  "il y a trois mois"
  (datetime 2012 11 12 4 30 months)

  "dans une année"
  "dans 1 an"
  (datetime 2014 2 12 4 30 years)
  
  "il y a deux ans"
  (datetime 2011 2 12 4 30 years)
  
;; Part of day (morning, afternoon...)

  "ce soir"
  (datetime-interval
    (datetime 2013 2 12 18 hours)
    (datetime 2013 2 13 00 hours))

  "demain soir"
  ;"mercredi soir"
  (datetime-interval
    (datetime 2013 2 13 18 hours)
    (datetime 2013 2 14 00 hours))
  
  "hier soir"
  (datetime-interval
    (datetime 2013 2 11 18 hours)
    (datetime 2013 2 12 00 hours))
  
  "à seize heures PST"
  (datetime 2013 2 12 16 hours :hour 16 :timezone "PST")
  
)
