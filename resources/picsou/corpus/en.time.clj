(
  ; Context map
  ; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:reference-time (time/local-date-time [2013 2 12 4 30])}

  "now"
  "right now"
  "just now"
  (datetime 2013 2 12 4 30 00 - 01)
  
  "today"
  "at this time"
  (datetime 2013 2 12 - 13)

  "yesterday"
  (datetime 2013 2 11 - 12)

  "tomorrow"
  (datetime 2013 2 13 - 14)
  
  "the day after tomorrow"
  (datetime 2013 2 14 - 15)
  
  "the day before yesterday"
  (datetime 2013 2 10 - 11)

  "monday"
  "mon."
  "this monday"
  "Monday, Feb 18"
  "Mon, February 18"
  (datetime 2013 2 18 - 19)

  "tuesday"
  (datetime 2013 2 19 - 20)

  "wednesday"
  "wed"
  "wed."
  "Wednesday, Feb13"
  (datetime 2013 2 13 - 14)

  "thursday"
  "thu"
  "thu."
  (datetime 2013 2 14 - 15)

  "friday"
  "fri"
  "fri."
  (datetime 2013 2 15 - 16)

  "saturday"
  "sat"
  "sat."
  (datetime 2013 2 16 - 17)

  "sunday"
  "sun"
  "sun."
  (datetime 2013 2 17 - 18)
  
  "this week-end"
  (datetime 2013 2 15 18 - 18 0)

  "monday morning"
  (datetime 2013 2 18 4 00 - 12 00)

  "last sunday"
  "sunday from last week"
  "last week's sunday"
  (datetime 2013 2 10 - 11)

  "Sunday, Feb 10"
  (datetime 2019 2 10 - 11) ; with current look-forward default...

    "last tuesday"
  (datetime 2013 2 5 - 6)

  "the 1st of march"
  "first of march"
  "march first"
  (datetime 2013 3 1 - 2)

  "march 3 2015"
  "march 3rd 2015"
  "march third 2015"
  "3/3/2015"
  "3/3/15"
  "2015-3-3"
  "2015-03-03"
  (datetime 2015 3 3 - 4)

  "the 15th of february"
  "on the 15"
  "on the 15th"
  "the 15 of february"
  "february the 15th"
  "february 15"
  "2/15"
  "on 2/15"
  (datetime 2013 2 15 - 16)
  
  "10/31/1974"
  "10/31/74"
  (datetime 1974 10 31 - 11 1)

  "february the 15th in the morning"
  "15 of february in the morning"
  "morning of the 15th of february"
  (datetime 2013 2 15 4 - 12)

  "next tuesday" ; when today is Tuesday, "mardi prochain" is a week from now
  (datetime 2013 2 19 - 20)

  "next wednesday" ; when today is Tuesday, "mercredi prochain" is tomorrow
  (datetime 2013 2 13 - 14)

  "wednesday of next week"
  "wednesday next week"
  (datetime 2013 2 20 - 21)

  "this week"
  (datetime 2013 2 11 - 18)

  "monday of this week"
  (datetime 2013 2 11 - 12)

  "tuesday of this week"
  (datetime 2013 2 12 - 13)

  "wednesday of this week"
  (datetime 2013 2 13 - 14)
  
  "last week"
  (datetime 2013 2 4 - 11)
  
  "next week"
  (datetime 2013 2 18 - 25)
  
  "last month"
  (datetime 2013 1 - 2)

  "next month"
  (datetime 2013 3 - 4)
  
  "last year"
  (datetime 2012 - 2013)
  
  "this year"
  (datetime 2013 - 2014)
  
  "next year"
  (datetime 2014 - 2015)
  
  "last 2 seconds"
  "last two seconds"
  (datetime 2013 2 12 4 29 58 - 4 30 0)

  "next 3 seconds"
  "next three seconds"
  (datetime 2013 2 12 4 30 01 - 04)

  "last 2 minutes"
  "last two minutes"
  (datetime 2013 2 12 4 28 - 30)

  "next 3 minutes"
  "next three minutes"
  (datetime 2013 2 12 4 31 - 34)

  "last 2 hours"
  "last two hours"
  (datetime 2013 2 12 2 - 4)

  "next 3 hours"
  "next three hours"
  (datetime 2013 2 12 5 - 8)

  "last 2 days"
  "last two days"
  (datetime 2013 2 10 - 12)

  "next 3 days"
  "next three days"
  (datetime 2013 2 13 - 16)
    
  "last 2 weeks"
  "last two weeks"
  (datetime 2013 1 28 - 2 11)

  "next 3 weeks"
  "next three weeks"
  (datetime 2013 2 18 - 3 11)

  "last 2 months"
  "last two months"
  (datetime 2012 12 1 - 2013 2 1)

  "next 3 months"
  "next three months"
  (datetime 2013 3 1 - 6 1)

  "last 2 years"
  "last two years"
  (datetime 2011 - 2013)
  
  "next 3 years"
  "next three years"
  (datetime 2014 - 2017)
  
  "at 3pm"
  "3PM"
  "3pm"
  "3 oclock pm"
  "3 o'clock in the afternoon"
  "at 3"
  (datetime 2013 2 12 15 - 16)

  "at 15 past 3pm"
  "a quarter past 3pm"
  "3:15 in the afternoon"
  "15:15"
  "3:15pm"
  "3:15PM"
  "3:15p"
  "at 3 15"
  (datetime 2013 2 12 15 15 - 16)

  "at 20 past 3pm"
  "3:20 in the afternoon"
  "twenty after 3pm"
  "3:20p"
  "at three twenty"
  (datetime 2013 2 12 15 20 - 21)

  "half past 3"
  "at half past three pm"
  "half past 3 pm"
  "15:30"
  "3:30pm"
  "3:30PM"
  "at 3 30"
  (datetime 2013 2 12 15 30 - 31)

  "a quarter to noon"
  "11:45am"
  "15 to noon"
  (datetime 2013 2 12 11 45 - 46)
  
  "at 7:30 PM on Fri, Sep 20"
  (datetime 2013 9 20 19 30 - 31)
  
  "at 9am on Saturday"
  (datetime 2013 2 16 9 - 10)
  
  "this evening"
  "today evening"
  "tonight"
  (datetime 2013 2 12 18 - 13 00)

  "tomorrow evening"
  "Wednesday evening"
  "tomorrow night"
  (datetime 2013 2 13 18 - 14 00)
  
  "yesterday evening"
  (datetime 2013 2 11 18 - 12 00)
  
  "in a sec"
  "one second from now"
  (datetime 2013 2 12 4 30 1 - 2)
  
  "in a minute"
  "in one minute"
  (datetime 2013 2 12 4 31 - 32)
  
  "in 2 minutes"
  "2 minutes from now"
  (datetime 2013 2 12 4 32 - 33)
  
  "in 60 minutes"
  (datetime 2013 2 12 5 30 - 31)
  
  "in one hour"
  (datetime 2013 2 12 5 30 - 6 30)

  "in 24 hours"
  (datetime 2013 2 13 4 30 - 5 30)
  
  "in a day"
  "a day from now"
  (datetime 2013 2 13 4 30 - 14 4 30)

  "in 7 days"
  (datetime 2013 2 19 4 30 - 20 4 30)

  "in 1 week"
  "in a week"
  (datetime 2013 2 19 4 30 - 26 4 30)

  "7 days ago"
  (datetime 2013 2 5 4 30 - 6 4 30)
  
  "a week ago"
  "one week ago"
  "1 week ago"
  (datetime 2013 2 5 4 30 - 12 4 30)

  "three weeks ago"
  (datetime 2013 1 22 4 30 - 29 4 30)
  
  "three months ago"
  (datetime 2012 11 12 4 30 - 12 12 4 30)

  "two years ago"
  (datetime 2011 2 12 4 30 - 2012 2 12 4 30)
  
  "4pm PST"
  (datetime-withzone "PST" 2013 2 12 16 - 17)

  "14april 2015"
  (datetime 2015 4 14 - 15)

  "8 tonight"
  "eight tonight"
  "8 this evening"
  (datetime 2013 2 12 20 - 21)
)