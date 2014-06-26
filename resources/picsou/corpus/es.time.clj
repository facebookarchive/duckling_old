(; Context map
  ; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:reference-time (time/local-date-time [2013 2 12 4 30])}

  "ahora"
  "ya"
  "ahorita"
  (datetime 2013 2 12 4 30 00 - 01)
  
  "hoy"
  "en este momento"
  (datetime 2013 2 12 - 13)

  "ayer"
  (datetime 2013 2 11 - 12)

  "anteayer"
  "antier"
  (datetime 2013 2 10 - 11)

  "mañana"
  (datetime 2013 2 13 - 14)

  "pasado mañana"
  (datetime 2013 2 14 - 15)

  "lunes"
  "lu"
  "lun."
  "este lunes"
  (datetime 2013 2 18 - 19)

  "martes"
  "ma"
  "ma."
  (datetime 2013 2 19 - 20)

  "miercoles"
  "miércoles"
  "mx"
  "mié."
  (datetime 2013 2 13 - 14)

  "este weekend"
  "este fin de semana"
  (datetime 2013 2 15 18 - 18 0)

  "el domingo pasado"
  "el domingo de la semana pasada"
  (datetime 2013 2 10 - 11)

  "el martes pasado"
  (datetime 2013 2 5 - 6)

  "el 5 de mayo"
  "el cinco de mayo"
  "el cinco de mayo de 2013"
  "mayo 5 de 2013" ; in part of latin america
  "5-5-2013"
  (datetime 2013 5 5 - 6)

  "el 4 de julio"
  "4-7-2013"
  "el 4/7"
  (datetime 2013 7 4 - 5)

  "el 3 de marzo"
  "3 de marzo"
  "el 3-3"
  "3-3-13"
  "3-3-2013"
  (datetime 2013 3 3 - 4)

  "el 5 de abril"
  "5 de abril"
  (datetime 2013 4 5 - 6)

  "el 1 de marzo"
  "1 de marzo"
  "el primero de marzo"
  "el uno de marzo"
  "primero de marzo"
  "uno de marzo"
  "1-3-2013"
  "1 mar 2013"
  "1.2.2013"
  (datetime 2013 3 1 - 2)

  "el 16"
  "16 de febrero"
  (datetime 2013 2 16 - 17)

  "el 17"
  "17 de febrero"
  "2-17"
  "el 02/17"
  (datetime 2013 2 17 - 18)

  "el 20"
  "20 de febrero"
  "20/2"
  (datetime 2013 2 20 - 21)
  
  "31/10/1974"
  "31/10/74" ; smart two-digit year resolution
  (datetime 1974 10 31 - 11 1)

  "el martes que viene" ; when today is Tuesday, "mardi prochain" is a week from now
  (datetime 2013 2 19 - 20)

  "miércoles que viene" ; when today is Tuesday, "mercredi prochain" is tomorrow
  (datetime 2013 2 13 - 14)

  "el miércoles de la semana que viene"
  "miercoles de la próxima semana"
  (datetime 2013 2 20 - 21)

  "esta semana"
  (datetime 2013 2 11 - 18)

  "el lunes de esta semana"
  (datetime 2013 2 11 - 12)

  "martes de esta semana"
  (datetime 2013 2 12 - 13)

  "el miércoles de esta semana"
  (datetime 2013 2 13 - 14)
  
  "la semana pasada"
  (datetime 2013 2 4 - 11)
  
  "la semana que viene"
  "la proxima semana"
  "dentro de una semana"
  (datetime 2013 2 18 - 25)
  
  "el pasado mes"
  (datetime 2013 1 - 2)

  "el mes que viene"
  "el proximo mes"
  (datetime 2013 3 - 4)
  
  "el año pasado"
  (datetime 2012 - 2013)
  
  "este ano"
  (datetime 2013 - 2014)
  
  "el año que viene"
  "el proximo ano"
  (datetime 2014 - 2015)
  
  "pasados 2 segundos"
  (datetime 2013 2 12 4 29 58 - 4 30 0)

  "proximos 3 segundos"
  (datetime 2013 2 12 4 30 01 - 04)

  "pasados 2 minutos"
  (datetime 2013 2 12 4 28 - 30)

  "proximos 3 minutos"
  (datetime 2013 2 12 4 31 - 34)

  "pasadas 2 horas"
  (datetime 2013 2 12 2 - 4)

  "proximas 3 horas"
  (datetime 2013 2 12 5 - 8)

  "pasados 2 dias"
  (datetime 2013 2 10 - 12)

  "proximos 3 dias"
  (datetime 2013 2 13 - 16)
    
  "pasadas dos semanas"
  (datetime 2013 1 28 - 2 11)

  "3 proximas semanas"
  "3 semanas que vienen"
  (datetime 2013 2 18 - 3 11)

  "pasados 2 meses"
  "dos pasados meses"
  (datetime 2012 12 1 - 2013 2 1)

  "3 próximos meses"
  "proximos tres meses"
  "tres meses que vienen"
  (datetime 2013 3 1 - 6 1)

  "pasados 2 anos"
  "dos pasados años"
  (datetime 2011 - 2013)
  
  "3 próximos años"
  "proximo tres años"
  "3 años que vienen"
  (datetime 2014 - 2017)

  "a las tres de la tarde"
  "a las tres"
  "a las 3 pm"
  "a las 15 horas"
  (datetime 2013 2 12 15 - 16)

  "a las ocho de la tarde"
  (datetime 2013 2 12 20 - 21)

  "15:00"
  "15.00"
  (datetime 2013 2 12 15 00 - 01)

  "medianoche"
  (datetime 2013 2 13 00 - 01)

  "mediodía"
  "las doce"
  "las 12 des medianoche"
  (datetime 2013 2 12 12 - 13)

  "las doce y cuarto"
  (datetime 2013 2 12 12 15 - 16)

  "las doce menos cinco"
  (datetime 2013 2 12 11 55 - 56)

  "las doce y media"
  (datetime 2013 2 12 12 30 - 31)

  "las doce y tres de la tarde"
  (datetime 2013 2 12 12 03 - 04)

  "a las tres y quince"
  "a las 3 y cuarto"
  "a las tres y cuarto de la tarde"
  "15:15"
  "15.15"
  (datetime 2013 2 12 15 15 - 16)

  "a las tres y cuarto mañana por la tarde"
  (datetime 2013 2 13 15 15 - 16)

  "a las tres y media"
  "a las 3 y treinta"
  "a las tres y media de la tarde"
  "15:30"
  "15.30"
  (datetime 2013 2 12 15 30 - 31)

  "las doce menos cuarto"
  "11:45"
  "las once y cuarenta y cinco"
  "hoy a 11:45"
  (datetime 2013 2 12 11 45 - 46)
  
  "miércoles a las once de la mañana"
  "mañana a las once"
  "mañana a 11"
  (datetime 2013 2 13 11 - 12)

  "jueves a las once"
  "pasado mañana a las once"
  "pasado mañana a 11"
  (datetime 2013 2 14 11 - 12)

  "viernes a las doce"
  (datetime 2013 2 15 12 - 13)
  
  "viernes a las 12:00 horas"
  (datetime 2013 2 15 12 00 - 01)
  
  "esta noche"
  (datetime 2013 2 12 18 - 13 00)

  "mañana por la noche"
  "miércoles por la noche"
  (datetime 2013 2 13 18 - 14 00)
  
  "ayer por la noche"
  (datetime 2013 2 11 18 - 12 00)
  
  "en un segundo"
  (datetime 2013 2 12 4 30 1 - 2)
  
  "en un minuto"
  "en 1 min"
  (datetime 2013 2 12 4 31 - 32)
  
  "en 2 minutos"
  "en dos minutos"
  (datetime 2013 2 12 4 32 - 33)
  
  "en 60 minutos"
  (datetime 2013 2 12 5 30 - 31)
  
  "en una hora"
  (datetime 2013 2 12 5 30 - 6 30)

  "hace dos horas"
  (datetime 2013 2 12 2 30 - 3 30)
  
  "en 24 horas"
  "en veinticuatro horas"
  (datetime 2013 2 13 4 30 - 5 30)
  
  "en un dia"
  (datetime 2013 2 13 4 30 - 14 4 30)
  
  "en 7 dias"
  (datetime 2013 2 19 4 30 - 20 4 30)
  
  "en una semana"
  (datetime 2013 2 19 4 30 - 26 4 30)
  
  "hace tres semanas"
  (datetime 2013 1 22  4 30 - 29 4 30)
  
  "en dos meses"
  (datetime 2013 4 12 4 30 - 5 12 4 30)
  
  "hace tres meses"
  (datetime 2012 11 12 4 30 - 12 12 4 30)

  "en un ano"
  "en 1 año"
  (datetime 2014 2 12 4 30 - 2015 2 12 4 30)
  
  "hace dos años"
  (datetime 2011 2 12 4 30 - 2012 2 12 4 30)
  
  "a las cuatro PST"
  (datetime-withzone "PST" 2013 2 12 4 - 5)

)
