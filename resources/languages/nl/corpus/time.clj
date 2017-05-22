(
  ;Context map
  ; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:reference-time (time/t -2 2017 1 27 4 30)}

  ; dagen
  "nu"
  "op dit moment"
  "zo snel mogelijk"
  (datetime 2017 1 27 4 30 00)

  "vandaag"
  "momenteel"
  (datetime 2017 1 27)

  "gisteren"
  (datetime 2017 1 26)

  "morgen"
  (datetime 2017 1 28)

  "morgenavond"
  (datetime-interval [2017 1 28 18] [2017 1 29 00])

  "morgenochtend"
  (datetime-interval [2017 1 28 4] [2017 1 28 12])

  "eergisteren"
  (datetime 2017 1 25)

  "overmorgen"
  (datetime 2017 1 29)

  "maandag ochtend"
  (datetime-interval [2017 1 30 04] [2017 1 30 12])

  "gisterochtend"
  (datetime-interval [2017 1 26 4] [2017 1 26 12])

  "gisteravond"
  (datetime-interval [2017 1 26 18] [2017 1 27 00])

  ; seasons
  "deze zomer"
  (datetime-interval [2017 6 21] [2017 9 24])

  "deze winter"
  (datetime-interval [2016 12 21] [2017 3 21])

  ; holidays

  "kerstmis"
  (datetime 2017 12 25)

  "oudejaarsavond"
  (datetime 2017 12 31)

  "nieuwjaarsdag"
  (datetime 2017 1 1)

  ; Part of day (morning, afternoon...)

  "vanavond"
  (datetime-interval [2017 1 27 18] [2017 1 28 00])

  "dit weekend"
  (datetime-interval [2017 1 27 18] [2017 1 30 00])

  "morgenmiddag eten"
  "morgen tijdens de lunch"
  (datetime-interval [2017 1 28 12] [2017 1 28 14])

  "na de lunch"
  (datetime-interval [2017 1 27 13] [2017 1 27 17])

  ; Intervals involving cycles

  "laatste twee seconden"
  "laatste 2 seconden"
  (datetime-interval [2017 1 27 4 29 58] [2017 1 27 4 30 00])

  "over drie seconden"
  "over 3 seconden"
  (datetime-interval [2017 1 27 4 30 01] [2017 1 27 4 30 04])

  "laatste twee minuten"
  "laatste 2 minuten"
  (datetime-interval [2017 1 27 4 28] [2017 1 27 4 30])

  "over drie minuten"
  "over 3 minuten"
  (datetime-interval [2017 1 27 4 31] [2017 1 27 4 34])

  "over drie uur"
  "over 3 uur"
  (datetime-interval [2017 1 27 5] [2017 1 27 8])

  "deze week"
  (datetime 2017 1 23 :grain :week)

  "afgelopen week"
  (datetime 2017 1 16 :grain :week)

  "volgende week"
  (datetime 2017 1 30 :grain :week)

  "vorige maand"
  (datetime 2016 12)

  "volgende maand"
  (datetime 2017 2)

  "vorig jaar"
  (datetime 2016)

  "dit jaar"
  (datetime 2017)

  "volgend jaar"
  (datetime 2018)

  "zondag"
  (datetime 2017 1 29 :day-of-week 7)

  "dinsdag"
  (datetime 2017 1 31 :day-of-week 2)

  ;; Hours

  "om drie uur"
  "om 3 uur"
  (datetime 2017 1 27 15)

  "om 8 uur in de avond"
  "om 8 uur 's avonds"
  "om acht uur in de avond"
  (datetime 2017 1 27 20)

  "15:00"
  "15.00"
  (datetime 2017 1 27 15 0)

  "half één"
  "half een"
  (datetime 2017 1 27 12 30 :hour 12 :minute 30)

  "kwart over drie"
  "15:15"
  "15.15"
  (datetime 2017 1 27 15 15 :hour 15 :minute 15)

  "half vier"
  (datetime 2017 1 27 15 30 :hour 15 :minute 30)

  "11:45"
  "kwart voor twaalf"
  (datetime 2017 1 27 11 45 :hour 11 :minute 45)

  "kwart over vijf"
  (datetime 2017 1 27 5 15 :hour 17 :minute 15)

  "middernacht"
  (datetime 2017 1 28 00)

  "middag"
  (datetime-interval [2017 1 27 12] [2017 1 27 19])

  "kwart over twaalf"
  (datetime 2017 1 27 12 15 :hour 12 :minute 15)

  "maandag"
  "ma"
  "deze maandag"
  (datetime 2017 1 30 :day-of-week 1)

  "ma, feb 27"
  "ma, februari 27"
  (datetime 2017 2 27 :day-of-week 1 :day 27 :month 2)

  "dinsdag"
  (datetime 2017 1 31)

  "woensdag"
  "wo"
  "woe"
  (datetime 2017 2 1)

  "donderdag"
  "do"
  (datetime 2017 2 02)

  "vrijdag"
  "vr"
  "vrij"
  (datetime 2017 2 03)

  "zaterdag"
  "za"
  "zat"
  (datetime 2017 1 28)

  "zondag"
  "zo"
  (datetime 2017 1 29)

  "een maart"
  "één maart"
  (datetime 2017 3 1 :day 1 :month 3)

  "maart 3"
  (datetime 2017 3 3 :day 3 :month 3)

  "maart 3 2015"
  "maart 3de 2015"
  "maart derde 2015"
  "3/3/2015"
  "3/3/15"
  "2015-3-3"
  "2015-03-03"
  (datetime 2015 3 3 :day 3 :month 3 :year 2015)

  "15 februari"
  "2/15"
  "op 2/15"
  "februari 15"
  (datetime 2017 2 15 :day 15 :month 2)

  "aug 8"
  (datetime 2017 8 8 :day 8 :month 8)

  "oktober 2014"
  (datetime 2014 10 :year 2014 :month 10)

  "10/31/1974"
  "10/31/74"
  "10-31-74"
  (datetime 1974 10 31 :day 31 :month 10 :year 1974)

  "morgen om elf uur"
  "morgen om 11 uur"
  (datetime 2017 1 28 11 :hour 11)

  "vrijdag middag"
  (datetime-interval [2017 1 27 12] [2017 1 27 19])

  "vrijdag, 12 september 2014"
  (datetime 2014 9 12 :day-of-week 5 :day 12 :month 9 :year 2014)

  "volgende dinsdag"
  (datetime 2017 1 31 :day-of-week 2)

  "volgende woensdag"
  (datetime 2017 2 1 :day-of-week 3)

  "maandag van deze week"
  (datetime 2017 1 23 :day-of-week 1)

  "dinsdag van deze week"
  (datetime 2017 1 24 :day-of-week 2)

  "woensdag van deze week"
  (datetime 2017 1 25 :day-of-week 2)

  ;; Involving periods  ; look for grain-after-shift

  "1 uur geleden"
  "een uur geleden"
  "één uur geleden"
  (datetime 2017 1 27 3 30)

  "24 uur geleden"
  "vierentwintig uur geleden"
  (datetime 2017 1 26 4 30)

  "in 3 uur"
  "in drie uur"
  (datetime 2017 1 27 7 30)

  "2 dagen geleden"
  "twee dagen geleden"
  (datetime 2017 1 25 4)

  "in 3 dagen"
  "in drie dagen"
  (datetime 2017 1 30 4)

  "2 weken geleden"
  "twee weken geleden"
  (datetime 2017 1 13)

  "in 3 weken"
  "in drie weken"
  (datetime 2017 2 17)

  "2 maanden geleden"
  "twee maanden geleden"
  (datetime 2016 11 27)

  "in 3 maanden"
  "in drie maanden"
  (datetime  2017 4 27)

  "2 jaar geleden"
  "twee jaar geleden"
  (datetime 2015 1)

  "in 3 jaar"
  "in drie jaar"
  (datetime 2020 1)

  "van 9:30 - 11:00 op donderdag"
  "tussen 9:30 en 11:00 op donderdag"
  "9:30 - 11:00 op donderdag"
  "later dan 9:30 maar voor 11:00 op donderdag"
  "donderdag van 9:30 tot 11:00"
  "van 9:30 tot en met 11:00 op donderdag"
  "donderdag van 9:30 tot en met 11:00"
  "9:30 tot 11:00 op donderdag"
  (datetime-interval [2017 2 2 9 30] [2017 2 2 11 1])

  "4pm CET"
  (datetime 2017 1 27 16 :hour 4 :meridiem :pm :timezone "CET")

  "donderdag 8:00 GMT"
  (datetime 2017 2 2 8 00 :timezone "GMT")

  ;; Mixing date and time

  "om 7:30 PM op vr, sep 22"
  (datetime 2017 9 22 19 30 :hour 7 :minute 30 :meridiem :pm)

  "om 9am op zaterdag"
  "op zaterdag voor 9am"
  (datetime 2017 1 28 9 :day-of-week 6 :hour 9 :meridiem :am)

  "vr, jul 18, 2014 07:00 PM"
  (datetime 2014 7 18 19 0 :day-of-week 5 :hour 7 :meridiem :pm)

)
