(
  ; Context map
  ; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:reference-time (time/local-date-time [2013 2 12 4 30])}

  "now"
  "right now"
  "just now"
  (datetime 2013 2 12 4 30 seconds)
  
  "today"
  "at this time"
  (datetime 2013 2 12 days)

  "yesterday"
  (datetime 2013 2 11 days)

  "tomorrow"
  (datetime 2013 2 13 days)
  
  "the day after tomorrow"
  (datetime 2013 2 14 days)
  
  "the day before yesterday"
  (datetime 2013 2 10 days)

  "monday"
  "mon."
  "this monday"
  (datetime 2013 2 18 days)

  "Monday, Feb 18"
  "Mon, February 18"
  (datetime 2013 2 18 days :day-of-week 1 :day 18 :month 2)

  "tuesday"
  (datetime 2013 2 19 days :day-of-week 2)

  "Wed, Feb13"
  (datetime 2013 2 13 days :day-of-week 3 :day 13 :month 2)

  "thursday"
  "thu"
  "thu."
  (datetime 2013 2 14 days :day-of-week 4)

  "friday"
  "fri"
  "fri."
  (datetime 2013 2 15 days :day-of-week 5)

  "saturday"
  "sat"
  "sat."
  (datetime 2013 2 16 days :day-of-week 6)

  "sunday"
  "sun"
  "sun."
  (datetime 2013 2 17 days :day-of-week 7)
  
  "last sunday"
  "sunday from last week"
  "last week's sunday"
  (datetime 2013 2 10 days :day-of-week 7)

  "Sunday, Feb 10"
  (datetime 2019 2 10 days :day-of-week 7 :day 10 :month 2) ; with current look-forward default...

  "last tuesday"
  (datetime 2013 2 5 days :day-of-week 2)

  "the 1st of march"
  "first of march"
  "march first"
  (datetime 2013 3 1 days :day 1 :month 3)
  
  "march 3"
  (datetime 2013 3 3 days :day 3 :month 3)

  "march 3 2015"
  "march 3rd 2015"
  "march third 2015"
  "3/3/2015"
  "3/3/15"
  "2015-3-3"
  "2015-03-03"
  (datetime 2015 3 3 days :day 3 :month 3 :year 2015)

  "on the 15"
  "on the 15th"
  (datetime 2013 2 15 days :day 15)

  "the 15th of february"
  "the 15 of february"
  "february the 15th"
  "february 15"
  "2/15"
  "on 2/15"
  "February 15"
  (datetime 2013 2 15 days :day 15 :month 2)

  "Aug 8"
  (datetime 2013 8 8 days :day 8 :month 8)
  
  "10/31/1974"
  "10/31/74"
  (datetime 1974 10 31 days :day 31 :month 10 :year 1974)
  
  "14april 2015"
  "April 14, 2015"
  (datetime 2015 4 14 days)

  "next tuesday" ; when today is Tuesday, "mardi prochain" is a week from now
  (datetime 2013 2 19 days :day-of-week 2)

  "next wednesday" ; when today is Tuesday, "mercredi prochain" is tomorrow
  (datetime 2013 2 13 days :day-of-week 3)

  "wednesday of next week"
  "wednesday next week"
  (datetime 2013 2 20 days :day-of-week 3)

  "monday of this week"
  (datetime 2013 2 11 days :day-of-week 1)

  "tuesday of this week"
  (datetime 2013 2 12 days :day-of-week 2)

  "wednesday of this week"
  (datetime 2013 2 13 days :day-of-week 3)
  
  ;; Cycles
  
  "this week"
  (datetime 2013 2 11 weeks)

  "last week"
  (datetime 2013 2 4 weeks)
  
  "next week"
  (datetime 2013 2 18 weeks)
  
  "last month"
  (datetime 2013 1 months)

  "next month"
  (datetime 2013 3 months)
  
  "last year"
  (datetime 2012 years)
  
  "this year"
  (datetime 2013 years)
  
  "next year"
  (datetime 2014 years)
    
  ;; Hours
  
  "at 3pm"
  "3PM"
  "3pm"
  "3 oclock pm"
  ;"3 o'clock in the afternoon"
  ; "at 3"
  (datetime 2013 2 12 15 hours :hour 3 :meridiem :pm)

  "at 15 past 3pm"
  "a quarter past 3pm"
 ; "3:15 in the afternoon"
 ; "15:15"
  "3:15pm"
  "3:15PM"
  "3:15p"
  ;"at 3 15"
  (datetime 2013 2 12 15 15 minutes :hour 3 :minute 15 :meridiem :pm)

  "at 20 past 3pm"
;  "3:20 in the afternoon"
  "twenty after 3pm"
  "3:20p"
 ; "at three twenty"
  (datetime 2013 2 12 15 20 minutes :hour 3 :minute 20 :meridiem :pm)

;  "half past 3"
  "at half past three pm"
  "half past 3 pm"
;  "15:30"
  "3:30pm"
  "3:30PM"
;  "at 3 30"
  (datetime 2013 2 12 15 30 minutes :hour 3 :minute 30 :meridiem :pm)

  "a quarter to noon"
  "11:45am"
 ; "15 to noon" ; Ambiguous with interval
  (datetime 2013 2 12 11 45 minutes :hour 11 :minute 45)
  
  "4pm PST"
  (datetime 2013 2 12 16 hours :timezone "PST")
  
  "8 tonight"
  "eight tonight"
  "8 this evening"
  (datetime 2013 2 12 20 hours)
  
  ;; Mixing date and time
    
  "at 7:30 PM on Fri, Sep 20"
  (datetime 2013 9 20 19 30 minutes :hour 7 :minute 30 :meridiem :pm)
  
  "at 9am on Saturday"
  (datetime 2013 2 16 9 hours :day-of-week 6 :hour 9 :meridiem :am  )
  
;; Involving durations  
  
  "in a sec"
  "one second from now"
  (datetime 2013 2 12 4 30 1 seconds)
  
  "in a minute"
  "in one minute"
  (datetime 2013 2 12 4 31 minutes)
  
  "in 2 minutes"
  "2 minutes from now"
  (datetime 2013 2 12 4 32 minutes)
  
  "in 60 minutes"
  (datetime 2013 2 12 5 30 minutes)
  
  "in one hour"
  (datetime 2013 2 12 5 30 hours)

  "in 24 hours"
  (datetime 2013 2 13 4 30 hours)
  
  "in a day"
  "a day from now"
  (datetime 2013 2 13 4 30 days)

  "in 7 days"
  (datetime 2013 2 19 4 30 days)

  "in 1 week"
  "in a week"
  (datetime 2013 2 19 4 30 weeks)

  "7 days ago"
  (datetime 2013 2 5 4 30 days)
  
  "a week ago"
  "one week ago"
  "1 week ago"
  (datetime 2013 2 5 4 30 weeks)

  "three weeks ago"
  (datetime 2013 1 22 4 30 weeks)
  
  "three months ago"
  (datetime 2012 11 12 4 30 months)

  "two years ago"
  (datetime 2011 2 12 4 30 years)
  
;; Seasons

  "this summer"
  (datetime-interval
    (datetime 2013 6 21 days)
    (datetime 2013 9 23 days))

  "this winter"
  (datetime-interval
    (datetime 2013 12 21 days)
    (datetime 2014 3 20 days))

;; US holidays (http://www.timeanddate.com/holidays/us/)

  "xmas"
  "christmas"
  "christmas day"
  (datetime 2013 12 25 days)

  "new year's eve"
  "new years eve"
  (datetime 2013 12 31 days)

  "new year's day"
  "new years day"
  (datetime 2014 1 1 days)

  "valentine's day"
  "valentine day"
  (datetime 2013 2 14 days)

  "memorial day"
  (datetime 2013 5 26 days)

  "independence day"
  "4th of July"
  "4 of july"
  (datetime 2013 7 4 days)

  "labor day"
  (datetime 2013 9 1 days)

  "halloween"
  (datetime 2013 10 31 days)

  "thanksgiving day"
  "thanksgiving"
  (datetime 2013 11 27 days)
  
  ;; Part of day (morning, afternoon...)
  
  "this evening"
  "today evening"
  "tonight"
  (datetime-interval
    (datetime 2013 2 12 18 hours)
    (datetime 2013 2 13 00 hours))

  "tomorrow evening"
  "Wednesday evening"
  "tomorrow night"
  (datetime-interval
    (datetime 2013 2 13 18 hours)
    (datetime 2013 2 14 00 hours))
  
  "yesterday evening"
  (datetime-interval
    (datetime 2013 2 11 18 hours)
    (datetime 2013 2 12 00 hours))
    
  "this week-end"
  (datetime-interval
    (datetime 2013 2 15 18 hours)
    (datetime 2013 2 18 00 hours))

  "monday morning"
  (datetime-interval
    (datetime 2013 2 18 4 hours)
    (datetime 2013 2 18 12 hours))

  "february the 15th in the morning"
  "15 of february in the morning"
  "morning of the 15th of february"
  (datetime-interval
    (datetime 2013 2 15 4 hours)
    (datetime 2013 2 15 12 hours))

  ;; Intervals involving durations
  
  "last 2 seconds"
  "last two seconds"
  (datetime-interval
    (datetime 2013 2 12 4 29 58 seconds)
    (datetime 2013 2 12 4 30 00 seconds))

  "next 3 seconds"
  "next three seconds"
  (datetime-interval
    (datetime 2013 2 12 4 30 01 seconds)
    (datetime 2013 2 12 4 30 04 seconds))

  "last 2 minutes"
  "last two minutes"
  (datetime-interval
    (datetime 2013 2 12 4 28 minutes)
    (datetime 2013 2 12 4 30 minutes))

  "next 3 minutes"
  "next three minutes"
  (datetime-interval
    (datetime 2013 2 12 4 31 minutes)
    (datetime 2013 2 12 4 34 minutes))

  "last 2 hours"
  "last two hours"
  (datetime-interval
    (datetime 2013 2 12 2 hours)
    (datetime 2013 2 12 4 hours))

  "next 3 hours"
  "next three hours"
  (datetime-interval
    (datetime 2013 2 12 5 hours)
    (datetime 2013 2 12 8 hours))

  "last 2 days"
  "last two days"
  (datetime-interval
    (datetime 2013 2 10 days)
    (datetime 2013 2 12 days))

  "next 3 days"
  "next three days"
  (datetime-interval
    (datetime 2013 2 13 days)
    (datetime 2013 2 16 days))
    
  "last 2 weeks"
  "last two weeks"
  (datetime-interval
    (datetime 2013 1 28 weeks)
    (datetime 2013 2 11 weeks))

  "next 3 weeks"
  "next three weeks"
  (datetime-interval
    (datetime 2013 2 18 weeks)
    (datetime 2013 3 11 weeks))

  "last 2 months"
  "last two months"
  (datetime-interval
    (datetime 2012 12 months)
    (datetime 2013 02 months))

  "next 3 months"
  "next three months"
  (datetime-interval
    (datetime 2013 3 months)
    (datetime 2013 6 months))

  "last 2 years"
  "last two years"
  (datetime-interval
    (datetime 2011 years)
    (datetime 2013 years))
  
  "next 3 years"
  "next three years"
  (datetime-interval
    (datetime 2014 years)
    (datetime 2017 years))

  ; ;; Explicit intervals

  "July 13-15"
  "July 13 to 15"
  "July 13 thru 15"
  "July 13 through 15"
  "July 13 - July 15"
  (datetime-interval
    (datetime 2013 7 13 days)
    (datetime 2013 7 16 days))

  "Aug 8 - Aug 12"
  (datetime-interval
    (datetime 2013 8 8 days)
    (datetime 2013 8 13 days))

  "from 9:30 - 11:00 on Thursday"
  "9:30 - 11:00 on Thursday"
  "Thursday from 9:30 to 11:00"
  (datetime-interval
    (datetime 2013 2 14 9 30 minutes)
    (datetime 2013 2 14 11 00 minutes))

  "Thursday from 9:30a to 11a"
  (datetime-interval
    (datetime 2013 2 14 9 30 minutes)
    (datetime 2013 2 14 11 hours))
)