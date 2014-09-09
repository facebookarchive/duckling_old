(
  ; Context map
  ; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:reference-time (time/t 2013 2 12 4 30 0)}

  "now"
  "right now"
  "just now"
  (datetime 2013 2 12 4 30 00)
  
  "today"
  "at this time"
  (datetime 2013 2 12)

  "yesterday"
  (datetime 2013 2 11)

  "tomorrow"
  (datetime 2013 2 13)
  
  "monday"
  "mon."
  "this monday"
  (datetime 2013 2 18 :day-of-week 1)

  "Monday, Feb 18"
  "Mon, February 18"
  (datetime 2013 2 18 :day-of-week 1 :day 18 :month 2)

  "tuesday"
  (datetime 2013 2 19)

  "thursday"
  "thu"
  "thu."
  (datetime 2013 2 14)

  "friday"
  "fri"
  "fri."
  (datetime 2013 2 15)

  "saturday"
  "sat"
  "sat."
  (datetime 2013 2 16)

  "sunday"
  "sun"
  "sun."
  (datetime 2013 2 17)
  
  "the 1st of march"
  "first of march"
  "march first"
  (datetime 2013 3 1 :day 1 :month 3)
  
  "march 3"
  (datetime 2013 3 3 :day 3 :month 3)

  "march 3 2015"
  "march 3rd 2015"
  "march third 2015"
  "3/3/2015"
  "3/3/15"
  "2015-3-3"
  "2015-03-03"
  (datetime 2015 3 3 :day 3 :month 3 :year 2015)

  "on the 15"
  "on the 15th"
  (datetime 2013 2 15 :day 15)

  "the 15th of february"
  "15 of february"
  "february the 15th"
  "february 15"
  "2/15"
  "on 2/15"
  "February 15"
  (datetime 2013 2 15 :day 15 :month 2)

  "Aug 8"
  (datetime 2013 8 8 :day 8 :month 8)
  
  "10/31/1974"
  "10/31/74"
  (datetime 1974 10 31 :day 31 :month 10 :year 1974)
  
  "14april 2015"
  "April 14, 2015"
  (datetime 2015 4 14 :day 14 :month 4 :years 2015)

  "next tuesday" ; when today is Tuesday, "mardi prochain" is a week from now
  (datetime 2013 2 19 :day-of-week 2)

  "Sunday, Feb 10"
  (datetime 2019 2 10 :day-of-week 7 :day 10 :month 2) ; with current look-forward default...

  "Wed, Feb13"
  (datetime 2013 2 13 :day-of-week 3 :day 13 :month 2)

  "Monday, Feb 18"
  "Mon, February 18"
  (datetime 2013 2 18 :day-of-week 1 :day 18 :month 2)

;   ;; Cycles
  
  "this week"
  (datetime 2013 2 11 :grain :week)

  "last week"
  (datetime 2013 2 4 :grain :week)
  
  "next week"
  (datetime 2013 2 18 :grain :week)
  
  "last month"
  (datetime 2013 1)

  "next month"
  (datetime 2013 3)
  
  "last year"
  (datetime 2012)
  
  "this year"
  (datetime 2013)
  
  "next year"
  (datetime 2014)
    
  "last sunday"
  "sunday from last week"
  "last week's sunday"
  (datetime 2013 2 10)

  "last tuesday"
  (datetime 2013 2 5)

  "next tuesday" ; when today is Tuesday, "mardi prochain" is a week from now
  (datetime 2013 2 19)

  "next wednesday" ; when today is Tuesday, "mercredi prochain" is tomorrow
  (datetime 2013 2 13)

  "wednesday of next week"
  "wednesday next week"
  (datetime 2013 2 20)

  "monday of this week"
  (datetime 2013 2 11)

  "tuesday of this week"
  (datetime 2013 2 12)

  "wednesday of this week"
  (datetime 2013 2 13)

  "the day after tomorrow"
  (datetime 2013 2 14)
  
  "the day before yesterday"
  (datetime 2013 2 10)

  ;; Hours
  
  "at 3am"
  "at 3 AM"
  "3 oclock am"
  "at three am"
  (datetime 2013 2 12 3)
  
  "3:18am"
  "3:18a"
  (datetime 2013 2 12 3 18)
  
  "at 3pm"
  "3PM"
  "3pm"
  "3 oclock pm"
  "3 o'clock in the afternoon"
  (datetime 2013 2 12 15 :hour 3 :meridiem :pm)

  "at 15 past 3pm"
  "a quarter past 3pm"
  "3:15 in the afternoon"
  "15:15"
  "3:15pm"
  "3:15PM"
  "3:15p"
  (datetime 2013 2 12 15 15 :hour 3 :minute 15 :meridiem :pm)

  "at 20 past 3pm"
  "3:20 in the afternoon"
  "twenty after 3pm"
  "3:20p"
  (datetime 2013 2 12 15 20 :hour 3 :minute 20 :meridiem :pm)

  "at half past three pm"
  "half past 3 pm"
  "15:30"
  "3:30pm"
  "3:30PM"
  (datetime 2013 2 12 15 30 :hour 3 :minute 30 :meridiem :pm)
  
  "3:30"
  "half three"
  (datetime 2013 2 12 15 30 :hour 3 :minute 30)

  "a quarter to noon"
  "11:45am"
  "15 to noon" ; Ambiguous with interval
  (datetime 2013 2 12 11 45 :hour 11 :minute 45)
    
  "8 tonight"
  "eight tonight"
  "8 this evening"
  (datetime 2013 2 12 20)
 
  ;; Mixing date and time
    
  "at 7:30 PM on Fri, Sep 20"
  (datetime 2013 9 20 19 30 :hour 7 :minute 30 :meridiem :pm)
  
  "at 9am on Saturday"
  (datetime 2013 2 16 9 :day-of-week 6 :hour 9 :meridiem :am  )
  
; ;; Involving periods  
  
  "in a sec"
  "one second from now"
  (datetime 2013 2 12 4 30 1)
  
  "in a minute"
  "in one minute"
  (datetime 2013 2 12 4 31 0)
  
  "in 2 minutes"
  "2 minutes from now"
  (datetime 2013 2 12 4 32 0)
  
  "in 60 minutes"
  (datetime 2013 2 12 5 30 0)
  
  "in one hour"
  (datetime 2013 2 12 5 30)
  
  "in a couple hours"
  "in a couple of hours"
  (datetime 2013 2 12 6 30)

  "in a few hours"
  "in few hours"
  (datetime 2013 2 12 7 30)

  "in 24 hours"
  (datetime 2013 2 13 4 30)
  
  "in a day"
  "a day from now"
  (datetime 2013 2 13 4)

  "in 7 days"
  (datetime 2013 2 19 4)

  "in 1 week"
  "in a week"
  (datetime 2013 2 19)

  "7 days ago"
  (datetime 2013 2 5 4)
  
  "a week ago"
  "one week ago"
  "1 week ago"
  (datetime 2013 2 5)

  "three weeks ago"
  (datetime 2013 1 22)
  
  "three months ago"
  (datetime 2012 11 12)

  "two years ago"
  (datetime 2011 2)
  

  ; Seasons

  "this summer"
  (datetime-interval [2013 6 21] [2013 9 23])

  "this winter"
  (datetime-interval [2012 12 21] [2013 3 20])

  ; US holidays (http://www.timeanddate.com/holidays/us/)

  "xmas"
  "christmas"
  "christmas day"
  (datetime 2013 12 25)

  "new year's eve"
  "new years eve"
  (datetime 2013 12 31)

  "new year's day"
  "new years day"
  (datetime 2014 1 1)

  "valentine's day"
  "valentine day"
  (datetime 2013 2 14)

  "memorial day"
  (datetime 2013 5 26)

  "independence day"
  "4th of July"
  "4 of july"
  (datetime 2013 7 4)

  "labor day"
  (datetime 2013 9 1)

  "halloween"
  (datetime 2013 10 31)

  "thanksgiving day"
  "thanksgiving"
  (datetime 2013 11 27)
  
  ; Part of day (morning, afternoon...)
  
  "this evening"
  "today evening"
  "tonight"
  (datetime-interval [2013 2 12 18] [2013 2 13 00])

  "tomorrow evening"
  ;"Wednesday evening"
  "tomorrow night"
  (datetime-interval [2013 2 13 18] [2013 2 14 00])
  
  "yesterday evening"
  (datetime-interval [2013 2 11 18] [2013 2 12 00])
    
  "this week-end"
  (datetime-interval [2013 2 15 18] [2013 2 18 00])

  "monday morning"
  (datetime-interval [2013 2 18 4] [2013 2 18 12])

  "february the 15th in the morning"
  "15 of february in the morning"
  "morning of the 15th of february"
  (datetime-interval [2013 2 15 4] [2013 2 15 12])

  "8 tonight"
  "eight tonight"
  "8 this evening"
  (datetime 2013 2 12 20)

  
  ; Intervals involving cycles
  
  "last 2 seconds"
  "last two seconds"
  (datetime-interval [2013 2 12 4 29 58] [2013 2 12 4 30 00])

  "next 3 seconds"
  "next three seconds"
  (datetime-interval [2013 2 12 4 30 01] [2013 2 12 4 30 04])

  "last 2 minutes"
  "last two minutes"
  (datetime-interval [2013 2 12 4 28] [2013 2 12 4 30])

  "next 3 minutes"
  "next three minutes"
  (datetime-interval [2013 2 12 4 31] [2013 2 12 4 34])

  "last 1 hour"
  "last one hour"
  (datetime-interval [2013 2 12 3] [2013 2 12 4])

  "next 3 hours"
  "next three hours"
  (datetime-interval [2013 2 12 5] [2013 2 12 8])

  "last 2 days"
  "last two days"
  (datetime-interval [2013 2 10] [2013 2 12])

  "next 3 days"
  "next three days"
  (datetime-interval [2013 2 13] [2013 2 16])
    
  "next few days"
  (datetime-interval [2013 2 13] [2013 2 16])

  "last 2 weeks"
  "last two weeks"
  (datetime-interval [2013 1 28 :grain :week] [2013 2 11 :grain :week])

  "next 3 weeks"
  "next three weeks"
  (datetime-interval [2013 2 18 :grain :week] [2013 3 11 :grain :week])

  "last 2 months"
  "last two months"
  (datetime-interval [2012 12] [2013 02])

  "next 3 months"
  "next three months"
  (datetime-interval [2013 3] [2013 6])

  "last 2 years"
  "last two years"
  (datetime-interval [2011] [2013])
  
  "next 3 years"
  "next three years"
  (datetime-interval [2014] [2017])

  
  ; Explicit intervals

  "July 13-15"
  "July 13 to 15"
  "July 13 thru 15"
  "July 13 through 15"
  "July 13 - July 15"
  (datetime-interval [2013 7 13] [2013 7 16])

  "Aug 8 - Aug 12"
  (datetime-interval [2013 8 8] [2013 8 13])

  "from 9:30 - 11:00 on Thursday"
  "9:30 - 11:00 on Thursday"
  "Thursday from 9:30 to 11:00"
  (datetime-interval [2013 2 14 9 30] [2013 2 14 11 01])

  "Thursday from 9a to 11a"
  (datetime-interval [2013 2 14 9] [2013 2 14 12])
  
  "11:30-1:30" ; go train this rule!
  "11:30-1:30"
  "11:30-1:30"
  "11:30-1:30"
  "11:30-1:30"
  "11:30-1:30"
  "11:30-1:30"
  (datetime-interval [2013 2 12 11 30] [2013 2 12 13 31])

  "1:30 PM on Sat, Sep 21"
  (datetime 2013 9 21 13 30)
  
  ; Timezones
  
  "4pm CET"
  (datetime 2013 2 12 16 :hour 4 :meridiem :pm :timezone "CET")

  "Thursday 8:00 GMT"
  (datetime 2013 2 14 8 00 :timezone "GMT")  
)