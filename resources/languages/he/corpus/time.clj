(
  ; Context map
  ; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:reference-time (time/t -2 2013 2 12 4 30 0)
   :min (time/t -2 1900)
   :max (time/t -2 2100)}

  "עכשיו"
  "מייד"
  (datetime 2013 2 12 4 30 00)

  "היום"
  (datetime 2013 2 12)

  "אתמול"
  (datetime 2013 2 11)

  "מחר"
  (datetime 2013 2 13)

  "ראשון"
  "יום ראשון"
  "בראשון הזה"
  (datetime 2013 2 17 :day-of-week 7)

  "יום שני"
  "שני"
  "שני הזה"
  (datetime 2013 2 18 :day-of-week 1)

  ; "שני השמונה עשרה לפברואר"
  "שני 18 לפברואר"
  (datetime 2013 2 18 :day-of-week 1 :day 18 :month 2)

  "שלישי"
  ; "שלישי ה19"
  "יום שלישי התשעה עשר"
  (datetime 2013 2 19)

  "חמישי"
  (datetime 2013 2 14)

  "שישי"
  (datetime 2013 2 15)

  "שבת"
  (datetime 2013 2 16)

  "ראשון"
  (datetime 2013 2 17)

  ;"הראשון למרץ"
  "1 למרץ"
  ; "ה1 למרץ"
  (datetime 2013 3 1 :day 1 :month 3)

  "במרץ 3"
  (datetime 2013 3 3 :day 3 :month 3)

  "באמצע מרץ"
  (datetime 2013 3 15 :month 3)

  "3 למרץ 2015"
  ; "השלישי למרץ 2015"
  "שלושה במרץ 2015"
  "3/3/2015"
  "3/3/15"
  "2015-3-3"
  "2015-03-03"
  (datetime 2015 3 3 :day 3 :month 3 :year 2015)

  ; "ב15 לחודש"
  ; "חמש עשרה לחודש"
  ; (datetime 2013 2 15 :dבay 15)

  "ה15 בפברואר"
  "15 לפברואר"
  "2/15"
  "ב 2/15"
  "פברואר 15"
  (datetime 2013 2 15 :day 15 :month 2)

  "אוגוסט 8"
  (datetime 2013 8 8 :day 8 :month 8)

  "אוקטובר 2014"
  (datetime 2014 10 :year 2014 :month 10)

  "10/31/1974"
  "10/31/74"
  "10-31-74"
  (datetime 1974 10 31 :day 31 :month 10 :year 1974)

  "14 לאפריל 2015"
  "אפריל 14, 2015"
  ;; "14 לאפריל 15"
  (datetime 2015 4 14 :day 14 :month 4 :years 2015)

  ;; "שלישי הבא" ; when today is Tuesday, "mardi prochain" is a week from now
  ;; (datetime 2013 2 19 :day-of-week 2)

  "שישי הבא"
  (datetime 2013 2 22 :day-of-week 2)

  "מרץ הבא"
  ;; "מרץ שנה הבאה"
  (datetime 2013 3)

  "ראשון, 10 לפברואר"
  (datetime 2013 2 10 :day-of-week 7 :day 10 :month 2)

  ; "יום שני, ה18 לפברואר"
  ; "שני, השמונה עשרה לפברואר"
  ; (datetime 2013 2 18 :day-of-week 1 :day 18 :month 2)

  ;; Cycles

  ;; "השבוע"
  ;; "בשבוע הנוכחי"
  "בשבוע הזה"
  (datetime 2013 2 11 :grain :week)

  "שבוע שעבר"
  "שבוע האחרון"
  ;; "לפני שבוע"
  ;; "שבוע קודם"
  (datetime 2013 2 4 :grain :week)

  "שבוע הבא"
  ;; "בעוד שבוע"
  (datetime 2013 2 18 :grain :week)

  "חודש שעבר"
  ;; "לפני חודש"
  (datetime 2013 1)

  "חודש הבא"
  (datetime 2013 3)

  ;; "רבעון הזה"
  ;; (datetime 2013 1 1 :grain :quarter)

  ;; "רבעון הבא"
  ;; (datetime 2013 4 1 :grain :quarter)

  ;; "שנה נוכחית"
  ;; "בשנה הנוכחית"
  ;; "השנה"
  ;; (datetime 2013)

  "שנה הבאה"
  (datetime 2014)

  ;; "ראשון שעבר"
  ;; "ראשון של שבוע שעבר"
  "ראשון בשבוע שעבר"
  (datetime 2013 2 10 :day-of-week 7)

  "שלישי האחרון"
  ;; "שלישי הקודם"
  (datetime 2013 2 5 :day-of-week 2)

  ;; "שלישי הבא" ; when today is Tuesday, "mardi prochain" is a week from now
  ;; (datetime 2013 2 19 :day-of-week 2)

  ;; "רביעי הבא" ; when today is Tuesday, "mercredi prochain" is tomorrow
  ;; "יום רביעי הבא"
  ;; (datetime 2013 2 13 :day-of-week 3)

  "רביעי שבוע הבא"
  "רביעי הבא"
  (datetime 2013 2 20 :day-of-week 3)

  "שישי הבא"
  (datetime 2013 2 22 :day-of-week 5)

  ;; "שני הזה"
  ;; (datetime 2013 2 11 :day-of-week 1)

  ;; "שלישי הזה"
  ;; (datetime 2013 2 12 :day-of-week 2)

  "רביעי הזה"
  (datetime 2013 2 13 :day-of-week 3)

  ;; "מחרתיים"
  ;; (datetime 2013 2 14)

  ;; "מחרתיים בחמש בצהריים"
  ;; (datetime 2013 2 14 17)

  ;; "שלשום"
  ;; (datetime 2013 2 10)

  ;; "שלשום בשמונה בבוקר"
  (datetime 2013 2 10 8)

  "שני האחרון של מרץ"
  (datetime 2013 3 25 :day-of-week 1)

  "ראשון האחרון של מרץ 2014"
  (datetime 2014 3 30 :day-of-week 7)

  "השלישי באוקטובר"
  (datetime 2013 10 3)

  ;; "שבוע ראשון של אוקטובר 2014"
  ;; (datetime 2014 10 6 :grain :week)

  ;; "בשבוע של השישי לאוקטובר"
  ;; "בשבוע של ה7 לאוקטובר"
  ;; (datetime 2013 10 7 :grain :week)

  ;; "היום האחרון באוקטובר 2015"
  ;; (datetime 2015 10 31)

  ;; "שבוע אחרון של ספטמבר 2014"
  ;; (datetime 2014 9 22 :grain :week)

  ;; nth of
  ; "יום שלישי הראשון של אוקטובר"
  ; (datetime 2013 10 1)

  ;; Hours

  ;; "ב3 בבוקר"
  ;; "3:00"
  ;; "ב3 בלילה"
  ;; "בשלוש"
  ;; "בשלוש בבוקר"
  ;; (datetime 2013 2 13 3)

  "3:18am"
  "3:18a"
  (datetime 2013 2 12 3 18)

  "ב 3pm"
  "3PM"
  "3pm"
  (datetime 2013 2 12 15 :hour 3 :meridiem :pm)

  ;; "באיזור 15:00"
  ; "באיזור שלוש בצהריים"
  ; (datetime 2013 2 12 15 :hour 3 :meridiem :pm) ;; :precision "approximate"

  ; "בשלוש ורבע בצהריים"
  ; "3:15 בצהריים"
  "15:15"
  "3:15pm"
  "3:15PM"
  "3:15p"
  (datetime 2013 2 12 15 15 :hour 3 :minute 15 :meridiem :pm)

  ;; "עשרים דקות אחרי 3 בצהריים"
  ; "3:20 בצהריים"
  ; "3:20 צהריים"
  ; "עשרים אחרי שלוש בצהריים"
  "3:20p"
  (datetime 2013 2 12 15 20 :hour 3 :minute 20 :meridiem :pm)

  ; "בשלוש וחצי בערב"
  ; "שלוש וחצי בצהריים"
  "15:30"
  "3:30pm"
  "3:30PM"
  "330 p.m."
  "3:30 p m"
  (datetime 2013 2 12 15 30 :hour 3 :minute 30 :meridiem :pm)

  "15:23:24"
  (datetime 2013 2 12 15 23 24 :hour 15 :minute 23 :second 24)

  "רבע ל12"
  "11:45am"
  (datetime 2013 2 12 11 45 :hour 11 :minute 45)

  ;; "הלילה ב8"
  ;; "הלילה בשמונה"
  ;; "8 הלילה"
  ;; (datetime 2013 2 12 20)

  ;; Mixing date and time

  ; "בשבע וחצי בערב ביום שישי העשרים לספטמבר"
  ; (datetime 2013 9 20 19 30 :hour 7 :minute 30 :meridiem :pm)

  "בתשע בבוקר בשבת"
  (datetime 2013 2 16 9 :day-of-week 6 :hour 9 :meridiem :am)

  "שישי, יולי 18, 2014 07:00 PM"
  (datetime 2014 7 18 19 0 :day-of-week 5 :hour 7 :meridiem :pm)


  ;; Involving periods

  ;; "עוד שנייה"
  ;; "בעוד שנייה"
  ;; (datetime 2013 2 12 4 30 1)

  ;; "עוד דקה"
  ;; "בעוד דקה"
  ;; (datetime 2013 2 12 4 31 0)

  "בעוד 2 דקות"
  ;; "שתי דקות מעכשיו"
  (datetime 2013 2 12 4 32 0)

  "בעוד 60 דקות"
  (datetime 2013 2 12 5 30 0)

  "בעוד רבע שעה"
  (datetime 2013 2 12 4 45 0)

  "בעוד חצי שעה"
  ;; "עוד חצי שעה"
  (datetime 2013 2 12 5 0 0)

  ;; "בעוד שעתיים וחצי"
  ;; (datetime 2013 2 12 7 0 0)

  ;; "בעוד שעה"
  ;; (datetime 2013 2 12 5 30)

  ;; "בעוד שעתיים"
  ;; (datetime 2013 2 12 6 30)

  ;; "בעוד מספר שעות"
  ;; (datetime 2013 2 12 7 30)

  ;; "בעוד יממה"
  "בעוד 24 שעות"
  "בעוד עשרים וארבע שעות"
  (datetime 2013 2 13 4 30)

  ;; "בעוד יום"
  ;; (datetime 2013 2 13 4)

  ;; "שלוש שנים מהיום"
  ;; (datetime 2016 2)

  "בעוד שבעה ימים"
  (datetime 2013 2 19 4)

  ;; "בעוד שבוע"
  ;; "בעוד שבוע אחד"
  ;; (datetime 2013 2 19)

  "לפני שבעה ימים"
  ;; "לפני שבוע"
  (datetime 2013 2 5 4)

  ;; "לפני שבועיים"
  ;; (datetime 2013 1 29 4)

  ;; "שבוע שעבר"
  ;; "לפני שבוע"
  ;; "בשבוע שעבר"
  ;; (datetime 2013 2 5)

  ; "לפני שלושה חודשים"
  ; (datetime 2012 11 12)

  ;; "לפני שנתיים"
  ;; (datetime 2011 2)

  "1954"
  (datetime 1954)

  ; Seasons
  ;; "בקיץ הזה"
  ;; (datetime-interval [2013 6 21] [2013 9 24])

  ;; "בחורף הזה"
  ;; (datetime-interval [2012 12 21] [2013 3 21])

  ; Part of day (morning, afternoon...)

  "הערב"
  "היום בערב"
  (datetime-interval [2013 2 12 18] [2013 2 13 00])

  "בסופ״ש האחרון"
  (datetime-interval [2013 2 8 18] [2013 2 11 00])

  "מחר בערב"
  (datetime-interval [2013 2 13 18] [2013 2 14 00])

  "מחר בצהריים"
  "מחר צהריים"
  (datetime-interval [2013 2 13 12] [2013 2 13 14])

  "אתמול בערב"
  (datetime-interval [2013 2 11 18] [2013 2 12 00])

  "בסופ״ש הזה"
  (datetime-interval [2013 2 15 18] [2013 2 18 00])

  "שני בבוקר"
  (datetime-interval [2013 2 18 4] [2013 2 18 12])

  ; "monday early in the morning"
  ; "monday early morning"
  ; "monday in the early hours of the morning"
  ; (datetime-interval [2013 2 18 4] [2013 2 18 9])

  ; "february the 15th in the morning"
  ; "15 of february in the morning"
  ; "morning of the 15th of february"
  ; (datetime-interval [2013 2 15 4] [2013 2 15 12])


  ; ; Intervals involving cycles

  ; "last 2 seconds"
  ; "last two seconds"
  ; (datetime-interval [2013 2 12 4 29 58] [2013 2 12 4 30 00])

  ; "next 3 seconds"
  ; "next three seconds"
  ; (datetime-interval [2013 2 12 4 30 01] [2013 2 12 4 30 04])

  ; "last 2 minutes"
  ; "last two minutes"
  ; (datetime-interval [2013 2 12 4 28] [2013 2 12 4 30])

  ; "next 3 minutes"
  ; "next three minutes"
  ; (datetime-interval [2013 2 12 4 31] [2013 2 12 4 34])

  ; "last 1 hour"
  ; "last 1 hr"
  ; "last one hour"
  ; (datetime-interval [2013 2 12 3] [2013 2 12 4])

  ; "last 24 hours"
  ; "last twenty four hours"
  ; "last twenty four hrs"
  ; "last 24 hrs"
  ; "last 24hrs"
  ; (datetime-interval [2013 2 11 4] [2013 2 12 4])

  ; "next 3 hours"
  ; "next three hours"
  ; (datetime-interval [2013 2 12 5] [2013 2 12 8])

  ; "last 2 days"
  ; "last two days"
  ; "past 2 days"
  ; (datetime-interval [2013 2 10] [2013 2 12])

  ; "next 3 days"
  ; "next three days"
  ; (datetime-interval [2013 2 13] [2013 2 16])

  ; "next few days"
  ; (datetime-interval [2013 2 13] [2013 2 16])

  ; "last 2 weeks"
  ; "last two weeks"
  ; "past 2 weeks"
  ; (datetime-interval [2013 1 28 :grain :week] [2013 2 11 :grain :week])

  ; "next 3 weeks"
  ; "next three weeks"
  ; (datetime-interval [2013 2 18 :grain :week] [2013 3 11 :grain :week])

  ; "last 2 months"
  ; "last two months"
  ; (datetime-interval [2012 12] [2013 02])

  ; "next 3 months"
  ; "next three months"
  ; (datetime-interval [2013 3] [2013 6])

  ; "last 2 years"
  ; "last two years"
  ; (datetime-interval [2011] [2013])

  ; "next 3 years"
  ; "next three years"
  ; (datetime-interval [2014] [2017])


  ; Explicit intervals

  ; "July 13-15"
  ; "July 13 to 15"
  ; "July 13 thru 15"
  ; "July 13 through 15"
  ; "July 13 - July 15"
  ; (datetime-interval [2013 7 13] [2013 7 16])

  ; "Aug 8 - Aug 12"
  ; (datetime-interval [2013 8 8] [2013 8 13])

  ; "9:30 - 11:00"
  ; (datetime-interval [2013 2 12 9 30] [2013 2 12 11 1])

  ; "from 9:30 - 11:00 on Thursday"
  ; "between 9:30 and 11:00 on thursday"
  ; "9:30 - 11:00 on Thursday"
  ; "later than 9:30 but before 11:00 on Thursday"
  ; "Thursday from 9:30 to 11:00"
  ; "from 9:30 untill 11:00 on thursday"
  ; "Thursday from 9:30 untill 11:00"
  ; "9:30 till 11:00 on Thursday"
  ; (datetime-interval [2013 2 14 9 30] [2013 2 14 11 1])

  ; "Thursday from 9a to 11a"
  ; (datetime-interval [2013 2 14 9] [2013 2 14 12])

  ; "11:30-1:30" ; go train this rule!
  ; "11:30-1:30"
  ; "11:30-1:30"
  ; "11:30-1:30"
  ; "11:30-1:30"
  ; "11:30-1:30"
  ; "11:30-1:30"
  ; (datetime-interval [2013 2 12 11 30] [2013 2 12 13 31])

  ; "1:30 PM on Sat, Sep 21"
  ; (datetime 2013 9 21 13 30)

  ; "within 2 weeks"
  ; (datetime-interval [2013 2 12 4 30 0] [2013 2 26])

  ; "until 2:00pm"
  ; "through 2:00pm"
  ; (datetime 2013 2 12 14 0 :direction :before)

  ; "by 2:00pm"
  ; (datetime-interval [2013 2 12 4 30 0] [2013 2 12 14])

  ; "by EOD"
  ; (datetime-interval [2013 2 12 4 30 0] [2013 2 13 0])

  ; "by EOM"
  ; (datetime-interval [2013 2 12 4 30 0] [2013 3 1 0])

  ; "by the end of next month"
  ; (datetime-interval [2013 2 12 4 30 0] [2013 4 1 0])
  ; ; Timezones

  ; "4pm CET"
  ; (datetime 2013 2 12 16 :hour 4 :meridiem :pm :timezone "CET")

  ; "Thursday 8:00 GMT"
  ; (datetime 2013 2 14 8 00 :timezone "GMT")

  ; ;; Bookface tests
  ; "today at 2pm"
  ; "at 2pm"
  ; (datetime 2013 2 12 14)

  ; "4/25 at 4:00pm"
  ; (datetime 2013 4 25 16 0)

  ; "3pm tomorrow"
  ; (datetime 2013 2 13 15)

  ; "after 2 pm"
  ; (datetime 2013 2 12 14 :direction :after)

  ; "after 5 days"
  ; (datetime 2013 2 17 4 :direction :after)

  ; "before 11 am"
  ; (datetime 2013 2 12 11 :direction :before)

  ; "in the afternoon"
  ; (datetime-interval [2013 2 12 12] [2013 2 12 19])

  ; "at 1:30pm"
  ; "1:30pm"
  ; (datetime 2013 2 12 13 30)

  ; "in 15 minutes"
  ; (datetime 2013 2 12 4 45 0)

  ; "after lunch"
  ; (datetime-interval [2013 2 12 13] [2013 2 12 17])

  ; "10:30"
  ; (datetime 2013 2 12 10 30)

  ; "morning" ;; how should we deal with fb mornings?
  ; (datetime-interval [2013 2 12 4] [2013 2 12 12])

  ; "next monday"
  ; (datetime 2013 2 18 :day-of-week 1)

  ; "at 12pm"
  ; "at noon"
  ; (datetime 2013 2 12 12)

  ; "at 12am"
  ; "at midnight"
  ; (datetime 2013 2 13 0)

  ; "March"
  ; "in March"
  ; (datetime 2013 3)
)
