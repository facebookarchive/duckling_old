(; Context map
  ; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:reference-time (time/t -2 2013 2 12 4 30)}

  "ahora"
  "ya"
  "ahorita"
  "cuanto antes"
  (datetime 2013 2 12 4 30 00)
  
  "hoy"
  "en este momento"
  (datetime 2013 2 12)

  "ayer"
  (datetime 2013 2 11)

  "anteayer"
  "antier"
  (datetime 2013 2 10)

  "mañana"
  (datetime 2013 2 13)

  "pasado mañana"
  (datetime 2013 2 14)

  "lunes"
  "lu"
  "lun."
  "este lunes"
  (datetime 2013 2 18 :day-of-week 1)

  "lunes, 18 de febrero"
  (datetime 2013 2 18 :day-of-week 1 :day 18 :month 2)

  "martes"
  "ma"
  "ma."
  (datetime 2013 2 19 :day-of-week 2)

  "miercoles"
  "miércoles"
  "mx"
  "mié."
  (datetime 2013 2 13 :day-of-week 3)

  "jueves"
  (datetime 2013 2 14 :day-of-week 4)

  "viernes"
  (datetime 2013 2 15 :day-of-week 5)

  "sabado"
  (datetime 2013 2 16 :day-of-week 6)

  "domingo"
  (datetime 2013 2 17 :day-of-week 7)

  "el 5 de mayo"
  "el cinco de mayo"
  (datetime 2013 5 5 :day 5 :month 5)
  
  "el cinco de mayo de 2013"
  "mayo 5 del 2013" ; in part of latin america
  "5-5-2013"
  (datetime 2013 5 5 :day 5 :month 5 :year 2013)

  "el 4 de julio"
  "el 4/7"
  (datetime 2013 7 4 :day 4 :month 7)

  "el 3 de marzo"
  "3 de marzo"
  "el 3-3"
  (datetime 2013 3 3 :day 3 :month 3)

  "el 5 de abril"
  "5 de abril"
  (datetime 2013 4 5 :day 5 :month 4)

  "el 1 de marzo"
  "1 de marzo"
  "el primero de marzo"
  "el uno de marzo"
  "primero de marzo"
  "uno de marzo"
  (datetime 2013 3 1 :day 1 :month 3)

  "1-3-2013"
  "1.3.2013"
  "1/3/2013"
  (datetime 2013 3 1 :day 1 :month 3 :year 2013)

  "el 16"
  "16 de febrero"
  (datetime 2013 2 16 - 17)

  "el 17"
  "17 de febrero"
  "17-2"
  "el 17/2"
  (datetime 2013 2 17 :day 17 :month 2)

  "el 20"
  "20 de febrero"
  "20/2"
  (datetime 2013 2 20 :day 20 :month 2)
  
  "31/10/1974"
  "31/10/74" ; smart two-digit year resolution
  (datetime 1974 10 31 :day 31 :month 10 :year 1974)

  "el martes que viene" ; when today is Tuesday, "mardi prochain" is a week from now
  (datetime 2013 2 19 :day-of-week 2)

  "miércoles que viene" ; when today is Tuesday, "mercredi prochain" is tomorrow
  "el miércoles de la semana que viene"
  "miercoles de la próxima semana"
  (datetime 2013 2 20 :day-of-week 3)

  "el lunes de esta semana"
  (datetime 2013 2 11 :day-of-week 1)

  "martes de esta semana"
  (datetime 2013 2 12 :day-of-week 2)

  "el miércoles de esta semana"
  (datetime 2013 2 13 :day-of-week 2)
  
 ;  ;; Cycles
  
  "esta semana"
  (datetime 2013 2 11 :grain :week)

  "la semana pasada"
  (datetime 2013 2 4 :grain :week)
  
  "la semana que viene"
  "la proxima semana"
  (datetime 2013 2 18 :grain :week)
  
  "el pasado mes"
  (datetime 2013 1)

  "el mes que viene"
  "el proximo mes"
  (datetime 2013 3)
  
  "el año pasado"
  (datetime 2012)
  
  "este ano"
  (datetime 2013)
  
  "el año que viene"
  "el proximo ano"
  (datetime 2014)

  "el domingo pasado"
  "el domingo de la semana pasada"
  (datetime 2013 2 10 :day-of-week 7)

  "el martes pasado"
  (datetime 2013 2 5 :day-of-week 2)

 ;  ;; Hours

  "a las tres de la tarde"
  "a las tres"
  "a las 3 pm"
  "a las 15 horas"
  (datetime 2013 2 12 15)

  "a las ocho de la tarde"
  (datetime 2013 2 12 20)

  "15:00"
  "15.00"
  (datetime 2013 2 12 15 0)

  "medianoche"
  (datetime 2013 2 13 00)

  "mediodía"
  "las doce"
  (datetime 2013 2 12 12)

  "las doce y cuarto"
  (datetime 2013 2 12 12 15 :hour 12 :minute 15)

  "las doce menos cinco"
  (datetime 2013 2 12 11 55 :hour 11 :minute 55)

  "las doce y media"
  (datetime 2013 2 12 12 30 :hour 12 :minute 30)

  "las tres de la manana"
  "las tres en la manana"
  (datetime 2013 2 13 3 :hour 3)

  "a las tres y quince"
  "a las 3 y cuarto"
  "a las tres y cuarto de la tarde"
  "a las tres y cuarto en la tarde"
  "15:15"
  "15.15"
  (datetime 2013 2 12 15 15 :hour 15 :minute 15)

  ; "a las tres y cuarto mañana por la tarde" ;ALEX
  ; (datetime 2013 2 13 15 15 :hour 15 :minute 15)

  "a las tres y media"
  "a las 3 y treinta"
  "a las tres y media de la tarde"
  "a las 3 y treinta del mediodía"
  "15:30"
  "15.30"
  (datetime 2013 2 12 15 30 :hour 15 :minute 30)

  "las doce menos cuarto"
  "11:45"
  "las once y cuarenta y cinco"
  ; "hoy a 11:45"
  "hoy a las doce menos cuarto"
  "hoy a las once y cuarenta y cinco"
  (datetime 2013 2 12 11 45 :hour 11 :minute 45)

  "5 y cuarto"
  (datetime 2013 2 12 5 15 :hour 17 :minute 15)

  "6 de la mañana"
  (datetime 2013 2 12 6 - 7)
  
  "miércoles a las once de la mañana"
  (datetime 2013 2 13 11 :hour 11 :day-of-week 3)
  
  "mañana a las once"
  "mañana a 11"
  (datetime 2013 2 13 11 :hour 11)

  ;"viernes a las doce"
  ;(datetime 2013 2 15 12 :hour 12 :day-of-week 5)
  
  ;"viernes a las 12:00 horas"
  ;(datetime 2013 2 15 12 0 :hour 12 :day-of-week 5 :minute 0)

  "viernes, el 12 de septiembre de 2014"
  (datetime 2014 9 12 :day-of-week 5 :day 12 :month 9 :year 2014)

  ;; Involving periods  ; look for grain-after-shift

  "en un segundo"
  (datetime 2013 2 12 4 30 1)
  
  "en un minuto"
  "en 1 min"
  (datetime 2013 2 12 4 31 0)
  
  "en 2 minutos"
  "en dos minutos"
  (datetime 2013 2 12 4 32 0)
  
  "en 60 minutos"
  (datetime 2013 2 12 5 30 0)
  
  "en una hora"
  (datetime 2013 2 12 5 30)

  "hace dos horas"
  (datetime 2013 2 12 2 30)
  
  "en 24 horas"
  "en veinticuatro horas"
  (datetime 2013 2 13 4 30)
  
  "en un dia"
  (datetime 2013 2 13 4)
  
  "en 7 dias"
  (datetime 2013 2 19 4)
  
  "en una semana"
  (datetime 2013 2 19)
  
  "hace tres semanas"
  (datetime 2013 1 22)
  
  "en dos meses"
  (datetime 2013 4 12)
  
  "hace tres meses"
  (datetime 2012 11 12)

  "en un ano"
  "en 1 año"
  (datetime 2014 2)
  
  "hace dos años"
  (datetime 2011 2)

  ; Seasons

  "este verano"
  (datetime-interval [2013 6 21] [2013 9 24])

  "este invierno"
  (datetime-interval [2012 12 21] [2013 3 21])

  ; ES holidays 

  "Navidad"
  "la Navidad"
  (datetime 2013 12 25)

  "Nochevieja"
  (datetime 2013 12 31)

  "ano nuevo"
  "año nuevo"
  (datetime 2014 1 1)

 ;  ; Part of day (morning, afternoon...)

  "esta noche"
  (datetime-interval [2013 2 12 18] [2013 2 13 00])

  "mañana por la noche"
  ;"miércoles por la noche"
  (datetime-interval [2013 2 13 18] [2013 2 14 00])
  
  "ayer por la noche"
  (datetime-interval [2013 2 11 18] [2013 2 12 00])  
  
  "este weekend"
  "este fin de semana"
  (datetime-interval [2013 2 15 18] [2013 2 18 00])

  "lunes por la mañana"
  (datetime-interval [2013 2 18 4] [2013 2 18 12])

  "el 15 de febrero por la mañana" 
  (datetime-interval [2013 2 15 4] [2013 2 15 12])

  "a las 8 de la tarde"
  (datetime 2013 2 12 20)

 ; Intervals involving cycles

  "pasados 2 segundos"
  (datetime-interval [2013 2 12 4 29 58] [2013 2 12 4 30 00])

  "proximos 3 segundos"
  (datetime-interval [2013 2 12 4 30 01] [2013 2 12 4 30 04])

  "pasados 2 minutos"
  (datetime-interval [2013 2 12 4 28] [2013 2 12 4 30])

  "proximos 3 minutos"
  (datetime-interval [2013 2 12 4 31] [2013 2 12 4 34])

  "proximas 3 horas"
  (datetime-interval [2013 2 12 5] [2013 2 12 8])

  "pasados 2 dias"
  (datetime-interval [2013 2 10] [2013 2 12])

  "proximos 3 dias"
  (datetime-interval [2013 2 13] [2013 2 16])
    
  "pasadas dos semanas"
  (datetime-interval [2013 1 28 :grain :week] [2013 2 11 :grain :week])

  "3 proximas semanas"
  "3 semanas que vienen"
  (datetime-interval [2013 2 18 :grain :week] [2013 3 11 :grain :week])

  "pasados 2 meses"
  "dos pasados meses"
  (datetime-interval [2012 12] [2013 02])

  "3 próximos meses"
  "proximos tres meses"
  "tres meses que vienen"
  (datetime-interval [2013 3] [2013 6])

  "pasados 2 anos"
  "dos pasados años"
  (datetime-interval [2011] [2013])
  
  "3 próximos años"
  "proximo tres años"
  "3 años que vienen"
  (datetime-interval [2014] [2017]) 
  
  ; Explicit intervals

  "13 a 15 de julio"
  "13 - 15 de julio de 2013"
  (datetime-interval [2013 7 13] [2013 7 16])

  "9:30 - 11:00"
  (datetime-interval [2013 2 12 9 30] [2013 2 12 11])

  "21 de Dic. a 6 de Ene"
  (datetime-interval [2013 12 21] [2014 1 7])

  "dentro de tres horas"
  (datetime-interval [2013 2 12 4 30 0] [2013 2 12 7 30])

  "a las cuatro de la tarde PST"
  (datetime 2013 2 12 16 :hour 16 :timezone "PST")


)
