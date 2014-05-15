(; Context map
  ; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:reference-time (time/local-date-time [2013 2 12 4 30])}

  "maintenant"
  "tout de suite"
  (datetime 2013 2 12 4 30 00 - 01)
  
  "aujourd'hui"
  "ce jour"
  "dans la journée"
  "en ce moment"
  (datetime 2013 2 12 - 13)

  "hier"
  (datetime 2013 2 11 - 12)

  "avant-hier"
  (datetime 2013 2 10 - 11)

  "demain"
  (datetime 2013 2 13 - 14)

  "après-demain"
  (datetime 2013 2 14 - 15)

  "lundi"
  "lun."
  "ce lundi"
  (datetime 2013 2 18 - 19)

  "mardi"
  (datetime 2013 2 19 - 20)

  "mercredi"
  (datetime 2013 2 13 - 14)

  "ce week-end"
  (datetime 2013 2 15 18 - 18 0)

  "dimanche dernier"
  "dimanche la semaine dernière"
  "dimanche de la semaine derniere"
  "dimanche la semaine passée"
  (datetime 2013 2 10 - 11)

  "mardi dernier"
  (datetime 2013 2 5 - 6)

  "le 1er mars"
  "premier mars"
  "le premier mars 2013"
  "le 1 mars"
  "1/3/2013"
  "2013-03-01"
  (datetime 2013 3 1 - 2)

  "le 2 mars"
  "2 mars"
  "2/3/2013"
  "le 2/3"
  (datetime 2013 3 2 - 3)

  "le 3 mars"
  "3 mars"
  "le 3/3"
  (datetime 2013 3 3 - 4)

  "le 5 avril"
  "5 avril"
  (datetime 2013 4 5 - 6)

  "le 3 mars 2015"
  "3 mars 2015"
  "3/3/2015"
  "2015-3-3"
  "2015-03-03"
  (datetime 2015 3 3 - 4)

  "le 15 février"
  "15 février"
  "le 15"
  "15/02/2013"
  "15 fev 2013"
  (datetime 2013 2 15 - 16)

  "le 16"
  "16 février"
  (datetime 2013 2 16 - 17)

  "le 17"
  "17 février"
  "17/2"
  "17/02"
  "le 17/02"
  (datetime 2013 2 17 - 18)

  "le 20"
  "20 février"
  "20/2"
  "20/02/2013"
  "20/2/2013"
  "20/02/13"
  (datetime 2013 2 20 - 21)
  
  "31/10/1974"
  "31/10/74" ; smart two-digit year resolution
  (datetime 1974 10 31 - 11 1)

  "mardi prochain" ; when today is Tuesday, "mardi prochain" is a week from now
  (datetime 2013 2 19 - 20)

  "mercredi prochain" ; when today is Tuesday, "mercredi prochain" is tomorrow
  (datetime 2013 2 13 - 14)

  "mercredi la semaine prochaine"
  "mercredi de la semaine prochaine"
  (datetime 2013 2 20 - 21)

  "lundi en huit" ;
  "lundi en 8"
  (datetime 2013 2 25 - 26)

  "mardi en huit" ; today is Tuesday, so "mardi en huit" is in 2 weeks
  "mardi en 8"
  (datetime 2013 2 26 - 27)

  "mercredi en huit" ;
  "mercredi en 8"
  (datetime 2013 2 20 - 21)

  "lundi en quinze" ;
  "lundi en 15"
  (datetime 2013 3 4 - 5)

  "mardi en quinze" ; today is Tuesday, so "mardi en huit" is in 2 weeks
  "mardi en 15"
  (datetime 2013 3 5 - 6)

  "mercredi en quinze" ;
  "mercredi en 15"
  (datetime 2013 2 27 - 28)

  "cette semaine"
  "dans la semaine"
  (datetime 2013 2 11 - 18)

  "lundi cette semaine"
  (datetime 2013 2 11 - 12)

  "mardi cette semaine"
  (datetime 2013 2 12 - 13)

  "mercredi cette semaine"
  (datetime 2013 2 13 - 14)
  
  "la semaine dernière"
  (datetime 2013 2 4 - 11)
  
  "la semaine prochaine"
  (datetime 2013 2 18 - 25)
  
  "le mois dernier"
  (datetime 2013 1 - 2)

  "le mois prochain"
  (datetime 2013 3 - 4)
  
  "l'année dernière"
  (datetime 2012 - 2013)
  
  "cette année"
  (datetime 2013 - 2014)
  
  "l'année prochaine"
  (datetime 2014 - 2015)
  
  "2 dernières secondes"
  "deux dernières secondes"
  (datetime 2013 2 12 4 29 58 - 4 30 0)

  "3 prochaines secondes"
  "trois prochaines secondes"
  (datetime 2013 2 12 4 30 01 - 04)

  "2 dernières minutes"
  "deux dernières minutes"
  (datetime 2013 2 12 4 28 - 30)

  "3 prochaines minutes"
  "trois prochaines minutes"
  (datetime 2013 2 12 4 31 - 34)

  "2 dernières heures"
  "deux dernières heures"
  (datetime 2013 2 12 2 - 4)

  "3 prochaines heures"
  "trois prochaines heures"
  (datetime 2013 2 12 5 - 8)

  "2 derniers jours"
  "deux derniers jours"
  (datetime 2013 2 10 - 12)

  "3 prochains jours"
  "trois prochains jours"
  (datetime 2013 2 13 - 16)
    
  "2 dernières semaines"
  "deux dernières semaines"
  (datetime 2013 1 28 - 2 11)

  "3 prochaines semaines"
  "trois prochaines semaines"
  (datetime 2013 2 18 - 3 11)

  "2 derniers mois"
  "deux derniers mois"
  (datetime 2012 12 1 - 2013 2 1)

  "3 prochains mois"
  "trois prochains mois"
  (datetime 2013 3 1 - 6 1)

  "2 dernières années"
  "deux dernières années"
  (datetime 2011 - 2013)
  
  "3 prochaines années"
  "trois prochaines années"
  (datetime 2014 - 2017)

  "à quinze heures"
  "à 15 heures"
  "à 3 heures cet après-midi"
  "15h"
  "15H"
  (datetime 2013 2 12 15 - 16)

  "15:00"
  "15h00"
  "15H00"
  (datetime 2013 2 12 15 00 - 01)

  "minuit"
  (datetime 2013 2 13 00 - 01)

  "midi"
  "aujourd'hui à midi"
  (datetime 2013 2 12 12 - 13)

  "midi et quart"
  "midi quinze"
  (datetime 2013 2 12 12 15 - 16)

  "midi moins cinq"
  (datetime 2013 2 12 11 55 - 56)

  "midi et demi"
  "midi trente"
  (datetime 2013 2 12 12 30 - 31)

  "minuit trois"
  (datetime 2013 2 13 00 03 - 04)

  "aujourd'hui à minuit trois"
  (datetime 2013 2 12 00 03 - 04)

  "à quinze heures quinze"
  "à quinze heures et quinze minutes"
  "15h passé de 15 minutes"
  "à trois heures et quart cet après-midi"
  "15:15"
  "15h15"
  (datetime 2013 2 12 15 15 - 16)

  "à trois heures et quart demain après-midi"
  (datetime 2013 2 13 15 15 - 16)

  "à quinze heures trente"
  "à quinze heures passé de trente minutes"
  "à trois heures et demi cet après-midi"
  "15:30"
  "15h30"
  (datetime 2013 2 12 15 30 - 31)

  "midi moins le quart"
  "11h45"
  "onze heures trois quarts"
  "aujourd'hui à 11h45"
  (datetime 2013 2 12 11 45 - 46)
  
  "mercredi à 11h"
  "demain à 11 heures"
  "demain à 11H"
  (datetime 2013 2 13 11 - 12)

  "jeudi à 11h"
  "après-demain à 11 heures"
  "après-demain à 11H"
  (datetime 2013 2 14 11 - 12)

  "vendredi à midi"
  "vendredi à 12h"
  (datetime 2013 2 15 12 - 13)
  
  "ce soir"
  (datetime 2013 2 12 18 - 13 00)

  "demain soir"
  "mercredi soir"
  (datetime 2013 2 13 18 - 14 00)
  
  "hier soir"
  (datetime 2013 2 11 18 - 12 00)
  
  "dans une seconde"
  (datetime 2013 2 12 4 30 1 - 2)
  
  "dans une minute"
  "dans 1 min"
  (datetime 2013 2 12 4 31 - 32)
  
  "dans 2 minutes"
  "dans deux min"
  (datetime 2013 2 12 4 32 - 33)
  
  "dans 60 minutes"
  (datetime 2013 2 12 5 30 - 31)
  
  "dans une heure"
  (datetime 2013 2 12 5 30 - 6 30)

  "il y a deux heures"
  (datetime 2013 2 12 2 30 - 3 30)
  
  "dans 24 heures"
  "dans vingt quatre heures"
  (datetime 2013 2 13 4 30 - 5 30)
  
  "dans un jour"
  (datetime 2013 2 13 4 30 - 14 4 30)
  
  "dans 7 jours"
  (datetime 2013 2 19 4 30 - 20 4 30)
  
  "dans 1 semaine"
  "dans une semaine"
  (datetime 2013 2 19 4 30 - 26 4 30)
  
  "il y a trois semaines"
  (datetime 2013 1 22  4 30 - 29 4 30)
  
  "dans deux mois"
  (datetime 2013 4 12 4 30 - 5 12 4 30)
  
  "il y a trois mois"
  (datetime 2012 11 12 4 30 - 12 12 4 30)

  "dans une année"
  "dans 1 an"
  (datetime 2014 2 12 4 30 - 2015 2 12 4 30)
  
  "il y a deux ans"
  (datetime 2011 2 12 4 30 - 2012 2 12 4 30)
  
  "à seize heures PST"
  (datetime-withzone "PST" 2013 2 12 16 - 17)

)
