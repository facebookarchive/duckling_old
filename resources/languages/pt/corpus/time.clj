(; Context map
  ; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:reference-time (time/t -2 2013 2 12 4 30)}

  "agora"
  "já"
  "ja"
  "nesse instante"
  "neste instante"
  (datetime 2013 2 12 4 30 00)

  "hoje"
  "nesse momento"
  "neste momento"
  (datetime 2013 2 12)

  "ontem"
  (datetime 2013 2 11)

  "antes de ontem"
  "anteontem"
  (datetime 2013 2 10)

  "amanhã"
  "amanha"
  (datetime 2013 2 13)

  "depois de amanhã"
  "depois de amanha"
  (datetime 2013 2 14)

  "segunda-feira"
  "segunda feira"
  "segunda"
  "seg."
  "seg"
  "essa segunda-feira"
  "essa segunda feira"
  "essa segunda"
  (datetime 2013 2 18 :day-of-week 1)

  "segunda, 18 de fevereiro"
  (datetime 2013 2 18 :day-of-week 1 :day 18 :month 2)

  "terça-feira"
  "terça feira"
  "terça"
  "terca-feira"
  "terca feira"
  "terca"
  "ter."
  "ter"
  (datetime 2013 2 19 :day-of-week 2)

  "quarta-feira"
  "quarta feira"
  "quarta"
  "qua."
  "qua"
  (datetime 2013 2 13 :day-of-week 3)

  "quinta-feira"
  "quinta feira"
  "quinta"
  "qui."
  "qui"
  (datetime 2013 2 14 :day-of-week 4)

  "sexta-feira"
  "sexta feira"
  "sexta"
  "sex."
  "sex"
  (datetime 2013 2 15 :day-of-week 5)

  "sábado"
  "sabado"
  "sáb."
  "sáb"
  "sab."
  "sab"
  (datetime 2013 2 16 :day-of-week 6)

  "domingo"
  "dom."
  "dom"
  (datetime 2013 2 17 :day-of-week 7)

  "5 de maio"
  "cinco de maio"
  (datetime 2013 5 5 :day 5 :month 5)

  "cinco de maio de 2013"
  "5 de maio de 2013"
  "5/5"
  "5/5/2013"
  (datetime 2013 5 5 :day 5 :month 5 :year 2013)

  "4 de julho"
  "quatro de julho"
  "4/7"
  "4/7/2013"
  (datetime 2013 7 4 :day 4 :month 7)

  "3 de março"
  "três de março"
  "tres de março"
  "3/3"
  "3/3/2013"
  (datetime 2013 3 3 :day 3 :month 3)

  "5 de abril"
  "cinco de abril"
  (datetime 2013 4 5 :day 5 :month 4)

  "1 de março"
  "primeiro de março"
  "um de março"
  (datetime 2013 3 1 :day 1 :month 3)

  "1-3-2013"
  "1.3.2013"
  "1/3/2013"
  (datetime 2013 3 1 :day 1 :month 3 :year 2013)

  "dia 16"
  "16 de fevereiro"
  (datetime 2013 2 16 - 17)

  "dia 17"
  "17 de fevereiro"
  "17/2"
  (datetime 2013 2 17 :day 17 :month 2)

  "dia 20"
  "20 de fevereiro"
  "20/2"
  (datetime 2013 2 20 :day 20 :month 2)

  "31/10/1974"
  "31/10/74" ; smart two-digit year resolution
  (datetime 1974 10 31 :day 31 :month 10 :year 1974)

  "próxima terça-feira" ; when today is Tuesday, "mardi prochain" is a week from now
  "próxima terça feira"
  "próxima terça"
  "proxima terça-feira"
  "proxima terça feira"
  "proxima terça"
  (datetime 2013 2 19 :day-of-week 2)

  "quarta que vem" ; when today is Tuesday, "mercredi prochain" is tomorrow
  "quarta da semana que vem"
  "quarta da próxima semana"
  (datetime 2013 2 20 :day-of-week 3)

  "terça desta semana"
  "terça dessa semana"
  "terça agora"
  (datetime 2013 2 12 :day-of-week 2)

 ;  ;; Cycles

  "esta semana"
  (datetime 2013 2 11 :grain :week)

  "semana passada"
  (datetime 2013 2 4 :grain :week)

  "semana que vem"
  "proxima semana"
  (datetime 2013 2 18 :grain :week)

  "mês passado"
  (datetime 2013 1)

  "mes que vem"
  "próximo mês"
  (datetime 2013 3)

  "ano passado"
  (datetime 2012)

  "este ano"
  (datetime 2013)

  "ano que vem"
  "proximo ano"
  (datetime 2014)

  "domingo passado"
  "domingo da semana passada"
  (datetime 2013 2 10 :day-of-week 7)

  "terça passada"
  (datetime 2013 2 5 :day-of-week 2)

 ;  ;; Hours

  "às tres da tarde"
  "às tres"
  "às 3 pm"
  "às 15 horas"
  (datetime 2013 2 12 15)

  "às oito da noite"
  (datetime 2013 2 12 20)

  "15:00"
  "15.00"
  (datetime 2013 2 12 15 0)

  "meianoite"
  "meia noite"
  (datetime 2013 2 13 00)

  "meio dia"
  "meiodia"
  (datetime 2013 2 12 12)

  "meio dia e quinze"
  (datetime 2013 2 12 12 15 :hour 12 :minute 15)

  "5 para meio dia"
  (datetime 2013 2 12 11 55 :hour 11 :minute 55)

  "meio dia e meia"
  (datetime 2013 2 12 12 30 :hour 12 :minute 30)

  "as seis da manha"
  "as seis pela manha"
  (datetime 2013 2 12 6 :hour 6)

  "às tres e quinze"
  "às tres e quinze da tarde"
  "às tres e quinze pela tarde"
  "15:15"
  "15.15"
  (datetime 2013 2 12 15 15 :hour 15 :minute 15)

  "às tres e meia"
  "às 3 e trinta"
  "às tres e meia da tarde"
  "às 3 e trinta da tarde"
  "15:30"
  "15.30"
  (datetime 2013 2 12 15 30 :hour 15 :minute 30)

  "quinze para meio dia"
  "quinze para o meio dia"
  "11:45"
  "as onze e quarenta e cinco"
  ; "hoy a 11:45"
  "hoje quinze para o meio dia"
  "hoje às onze e quarenta e cinco"
  (datetime 2013 2 12 11 45 :hour 11 :minute 45)

  "5 e quinze"
  (datetime 2013 2 12 5 15 :hour 17 :minute 15)

  "6 da manhã"
  (datetime 2013 2 12 6 - 7)

  "quarta às onze da manhã"
  (datetime 2013 2 13 11 :hour 11 :day-of-week 3)

  "sexta, 12 de setembro de 2014"
  "sexta feira, 12 de setembro de 2014"
  (datetime 2014 9 12 :day-of-week 5 :day 12 :month 9 :year 2014)

  ;; Involving periods  ; look for grain-after-shift

  "em um segundo"
  (datetime 2013 2 12 4 30 1)

  "em um minuto"
  "em 1 min"
  (datetime 2013 2 12 4 31 0)

  "em 2 minutos"
  "em dois minutos"
  (datetime 2013 2 12 4 32 0)

  "em 60 minutos"
  (datetime 2013 2 12 5 30 0)

  "em uma hora"
  (datetime 2013 2 12 5 30)

  "fazem duas horas"
  (datetime 2013 2 12 2 30)

  "em 24 horas"
  "em vinte e quatro horas"
  (datetime 2013 2 13 4 30)

  "em um dia"
  (datetime 2013 2 13 4)

  "em 7 dias"
  (datetime 2013 2 19 4)

  "em uma semana"
  (datetime 2013 2 19)

  "faz tres semanas"
  "faz três semanas"
  (datetime 2013 1 22)

  "em dois meses"
  (datetime 2013 4 12)

  "faz tres meses"
  (datetime 2012 11 12)

  "em um ano"
  "em 1 ano"
  (datetime 2014 2)

  "faz dois anos"
  (datetime 2011 2)

  ; Seasons

  "este verão"
  "este verao"
  (datetime-interval [2013 6 21] [2013 9 24])

  "este inverno"
  (datetime-interval [2012 12 21] [2013 3 21])

  ; ES holidays

  "Natal"
  (datetime 2013 12 25)

  "véspera de ano novo"
  (datetime 2013 12 31)

  "ano novo"
  "reveillon"
  "Reveillon"
  (datetime 2014 1 1)

 ;  ; Part of day (morning, afternoon...)

  "esta noite"
  "essa noite"
  (datetime-interval [2013 2 12 18] [2013 2 13 00])

  "amanhã a noite"
  ;"miércoles por la noche"
  (datetime-interval [2013 2 13 18] [2013 2 14 00])

  "ontem a noite"
  (datetime-interval [2013 2 11 18] [2013 2 12 00])

  "este final de semana"
  "este fim de semana"
  (datetime-interval [2013 2 15 18] [2013 2 18 00])

  "segunda de manhã"
  (datetime-interval [2013 2 18 4] [2013 2 18 12])

  "dia 15 de fevereiro pela manhã"
  "dia 15 de fevereiro de manhã"
  (datetime-interval [2013 2 15 4] [2013 2 15 12])

  "às 8 da noite"
  (datetime 2013 2 12 20)

 ; Intervals involving cycles

  "2 segundos atras"
  (datetime-interval [2013 2 12 4 29 58] [2013 2 12 4 30 00])

  "proximos 3 segundos"
  (datetime-interval [2013 2 12 4 30 01] [2013 2 12 4 30 04])

  "2 minutos atrás"
  (datetime-interval [2013 2 12 4 28] [2013 2 12 4 30])

  "proximos 3 minutos"
  (datetime-interval [2013 2 12 4 31] [2013 2 12 4 34])

  "proximas 3 horas"
  (datetime-interval [2013 2 12 5] [2013 2 12 8])

  "passados 2 dias"
  (datetime-interval [2013 2 10] [2013 2 12])

  "proximos 3 dias"
  (datetime-interval [2013 2 13] [2013 2 16])

  "duas semanas atras"
  (datetime-interval [2013 1 28 :grain :week] [2013 2 11 :grain :week])

  "3 proximas semanas"
  "3 semanas que vem"
  (datetime-interval [2013 2 18 :grain :week] [2013 3 11 :grain :week])

  "passados 2 meses"
  (datetime-interval [2012 12] [2013 02])

  "3 próximos meses"
  "proximos tres meses"
  "tres meses que vem"
  (datetime-interval [2013 3] [2013 6])

  "passados 2 anos"
  (datetime-interval [2011] [2013])

  "3 próximos anos"
  "proximo tres anos"
  "3 anos que vem"
  (datetime-interval [2014] [2017])

  ; Explicit intervals

  "13 a 15 de julho"
  "13 - 15 de julho de 2013"
  (datetime-interval [2013 7 13] [2013 7 16])

  "9:30 - 11:00"
  (datetime-interval [2013 2 12 9 30] [2013 2 12 11])

  "21 de Dez. a 6 de Jan"
  (datetime-interval [2013 12 21] [2014 1 7])

  "dentro de tres horas"
  (datetime-interval [2013 2 12 4 30 0] [2013 2 12 7 30])

  "as quatro da tarde PST"
  (datetime 2013 2 12 16 :hour 16 :timezone "PST")

  "após ao meio dia"
  "depois do meio dia"
  (datetime 2013 2 12 12 :direction :after)

  "antes do meio dia"
  "não mais que meio dia"
  (datetime 2013 2 12 12 :direction :before)

  "amanhã depois das 15hs"
  "amanha após as quinze horas"
  (datetime 2013 2 13 15 :direction :after)

  "antes da meia noite"
  "até a meia noite"
  (datetime 2013 2 13 00 :direction :before)
)
