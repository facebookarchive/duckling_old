(
  ; Context map
  ; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:reference-time (time/t -2 2013 2 12 4 30 0)
   :min (time/t -2 1900)
   :max (time/t -2 2100)}

  "방금"
  "지금"
  (datetime 2013 2 12 4 30 00)

  "오늘"
  (datetime 2013 2 12)

  "어제"
  (datetime 2013 2 11)

  "내일"
  (datetime 2013 2 13)

  "월요일"
  "이번주 월요일"
  (datetime 2013 2 18 :day-of-week 1)

  "2월18일 월요일"
  (datetime 2013 2 18 :day-of-week 1 :day 18 :month 2)

  "화요일"
  "19일 화요일"
  (datetime 2013 2 19)

  "목요일"
  (datetime 2013 2 14)

  "금요일"
  (datetime 2013 2 15)

  "토요일"
  (datetime 2013 2 16)

  "일요일"
  (datetime 2013 2 17)

  "3월 1일"
  (datetime 2013 3 1 :day 1 :month 3)

  "3월 3일"
  (datetime 2013 3 3 :day 3 :month 3)

  "2015년 3월 3일"
  "이천십오년 삼월 삼일"
  "2015/3/3"
  "2015-3-3"
  "2015-03-03"
  (datetime 2015 3 3 :day 3 :month 3 :year 2015)

  "15일에"
  (datetime 2013 2 15 :day 15)

  "2월 15일"
  "2/15"
  (datetime 2013 2 15 :day 15 :month 2)

  "8월 8일"
  (datetime 2013 8 8 :day 8 :month 8)

  "2014년 10월"
  (datetime 2014 10 :year 2014 :month 10)

  "1974/10/31"
  "74/10/31"
  (datetime 1974 10 31 :day 31 :month 10 :year 1974)

  "2015년 4월 14일"
  (datetime 2015 4 14 :day 14 :month 4 :years 2015)

  "다음주 화요일"
  "다음 화요일"
  (datetime 2013 2 19 :day-of-week 2)

  "다음 3월"
  (datetime 2014 3)

  "2월 18일 월요일"
  (datetime 2013 2 18 :day-of-week 1 :day 18 :month 2)

;; Cycles

  "이번주"
  "금주"
  (datetime 2013 2 11 :grain :week)

  "저번주"
  "전주"
  (datetime 2013 2 4 :grain :week)

  "다음주"
  "오는주"
  (datetime 2013 2 18 :grain :week)

  "저번달"
  (datetime 2013 1)

  "다음달"
  (datetime 2013 3)

  "이번분기"
  (datetime 2013 1 1 :grain :quarter)

  "다음분기"
  (datetime 2013 4 1 :grain :quarter)

  "삼분기"
  (datetime 2013 7 1 :grain :quarter)

  "2018년 4분기"
  (datetime 2018 10 1 :grain :quarter)

  "작년"
  (datetime 2012)

  "올해"
  (datetime 2013)

  "내년"
  (datetime 2014)

  "저번주 일요일"
  "지난주 일요일"
  "지난 일요일"
  "저번 일요일"
  (datetime 2013 2 10 :day-of-week 7)

  "저번주 화요일"
  (datetime 2013 2 5 :day-of-week 2)

  "다음주 화요일" ; when today is Tuesday, "mardi prochain" is a week from now
  (datetime 2013 2 19 :day-of-week 2)

  "다음주 수요일"
  (datetime 2013 2 20 :day-of-week 3)

  "다음주 금요일"
  (datetime 2013 2 22 :day-of-week 5)

  "이번주 월요일"
  (datetime 2013 2 18 :day-of-week 1)

  "이번주 화요일"
  (datetime 2013 2 12 :day-of-week 2)

  "이번주 수요일"
  (datetime 2013 2 13 :day-of-week 3)

  "내일모래"
  "모래"
  (datetime 2013 2 14)

  "내일 저녁다섯시"
  (datetime 2013 2 13 17)

  "엊그제"
  "그제"
  (datetime 2013 2 10)

  "엊그제 아침8시"
  "엊그제 오전8시"
  (datetime 2013 2 10 8)

  "3월 마지막 월요일"
  (datetime 2013 3 25 :day-of-week 1)

  "2014년 3월 마지막일요일"
  (datetime 2014 3 30 :day-of-week 7)

  "10월 3일"
  (datetime 2013 10 3)

  "2014년 10월 첫번째주"
  (datetime 2014 10 6 :grain :week)

  "2015년 10월 마지막날"
  (datetime 2015 10 31)

  "2014년 9월 마지막주"
  (datetime 2014 9 22 :grain :week)


  ;; nth of
  "10월 첫째화요일"
  (datetime 2013 10 1)

  "2014년 9월 셋째화요일"
  "2014년 9월 세번째화요일"
  (datetime 2014 9 16)

  "2014년 10월 첫번째 수요일"
  "2014년 10월 첫째 수요일"
  (datetime 2014 10 1)

  "2014년 10월 두번째 수요일"
  "2014년 10월 둘째 수요일"
  (datetime 2014 10 8)

  ;; Hours

  "아침 3시"
  "오전 세시"
  "3AM"
  (datetime 2013 2 13 3)

  "3:18am"
  "3:18a"
  (datetime 2013 2 12 3 18)

  "오후 세시"
  "3PM"
  (datetime 2013 2 12 15 :hour 3 :meridiem :pm)

  "오후세시이십분"
  "3:20p"
  "15:20"
  "3:20pm"
  "3:20PM"
  (datetime 2013 2 12 15 20 :hour 3 :minute 20 :meridiem :pm)

  "오후세시반"
  "15:30"
  "3:30pm"
  "3:30PM"
  (datetime 2013 2 12 15 30 :hour 3 :minute 30 :meridiem :pm)

  "네시십오분전"
  (datetime 2013 2 12 15 45 :hour 3 :minute 45)

  "3:30"
  "세시반"
  (datetime 2013 2 12 15 30 :hour 3 :minute 30)

  "15:23:24"
  "세시이십삼분이십사초"
  (datetime 2013 2 12 15 23 24 :hour 15 :minute 23 :second 24)

  "오늘밤 8시"
  "저녁 8시"
  (datetime 2013 2 12 20)

  ;; Mixing date and time

  "9월 20일 저녁 7시 30분"
  (datetime 2013 9 20 19 30 :hour 7 :minute 30 :meridiem :pm)

  "토요일 9시"
  (datetime 2013 2 16 9 :day-of-week 7 :hour 9 :meridiem :am)

  "2014년 7월 18일 금요일 오후 7시"
  (datetime 2014 7 18 19 :day-of-week 5 :hour 7 :meridiem :pm)


; ;; Involving periods

  "1초안에"
  (datetime 2013 2 12 4 30 1)

  "일분안에"
  "일분내에"
  (datetime 2013 2 12 4 31 0)

  "이분안에"
  "이분내에"
  (datetime 2013 2 12 4 32 0)

  "한시간안에"
  "한시간내"
  (datetime 2013 2 12 5 30)

  "한시간반안"
  "한시간반내"
  (datetime 2013 2 12 6 0 0)

  "두시간반안"
  "두시간반내"
  (datetime 2013 2 12 7 0 0)

  "몇시간안"
  "몇시간내"
  "몇시간후"
  "몇시간이후"
  (datetime 2013 2 12 7 30)

  "24시간안에"
  "24시간내"
  (datetime 2013 2 13 4 30)

  "하루안에"
  "하루내"
  (datetime 2013 2 13 4)

  "삼년안에"
  "삼년내"
  (datetime 2016 2)

  "7일안에"
  "7일내"
  (datetime 2013 2 19 4)

  "1주일안에"
  "1주일내"
  (datetime 2013 2 19)

  "약 한시간반 안에"
  (datetime 2013 2 12 6 0 0)

  "7일전"
  (datetime 2013 2 5 4)

  "14일전"
  "14일전에"
  (datetime 2013 1 29 4)

  "3주전"
  "3주이전"
  (datetime 2013 1 22)

  "2년전"
  "2년이전"
  (datetime 2011 2)

  "1954"
  (datetime 1954)

  ; Seasons

  "이번여름"
  (datetime-interval [2013 6 21] [2013 9 24])

  "이번겨울"
  (datetime-interval [2012 12 21] [2013 3 21])

  ; holidays (https://en.wikipedia.org/wiki/List_of_public_holidays_in_South_Korea)

  "크리스마스"
  (datetime 2013 12 25)

  "크리스마스이브"
  (datetime 2013 12 24)

  "신정"
  "설날"
  (datetime 2014 1 1)

  "삼일절"
  (datetime 2013 3 1)

  "어린이날"
  (datetime 2013 5 5)

  ;"석가탄신일" ;skip this as it uses lunar calander
  ;(datetime 2013 5 5)
  
  "현충일"
  (datetime 2013 6 6)

  "제헌절"
  (datetime 2013 6 17)

  "광복절"
  (datetime 2013 8 15)

  ;"추석" ;skip this as it uses lunar calander
  ;(datetime 2013 8 15)

  "개천절"
  (datetime 2013 10 3)

  "한글날"
  (datetime 2013 10 9)

  ; Part of day (morning, afternoon...)

  "오늘저녁"
  "오늘밤"
  (datetime-interval [2013 2 12 18] [2013 2 13 00])

  "저번주말"
  "지난주말"
  (datetime-interval [2013 2 8 18] [2013 2 11 00])

  "내일저녁"
  "내일밤"
  (datetime-interval [2013 2 13 18] [2013 2 14 00])

  "내일점심"
  (datetime-interval [2013 2 13 12] [2013 2 13 14])

  "어제저녁"
  (datetime-interval [2013 2 11 18] [2013 2 12 00])

  "이번주말"
  (datetime-interval [2013 2 15 18] [2013 2 18 00])

  "월요일 아침"
  (datetime-interval [2013 2 18 4] [2013 2 18 12])

  "2월 15일 아침"
  (datetime-interval [2013 2 15 4] [2013 2 15 12])


  ; Intervals involving cycles

  "지난 2초"
  "지난 이초"
  (datetime-interval [2013 2 12 4 29 58] [2013 2 12 4 30 00])

  "다음 3초"
  "다음 삼초"
  (datetime-interval [2013 2 12 4 30 01] [2013 2 12 4 30 04])

  "지난 2분"
  "지난 이분"
  (datetime-interval [2013 2 12 4 28] [2013 2 12 4 30])

  "다음 3분"
  "다음 삼분"
  (datetime-interval [2013 2 12 4 31] [2013 2 12 4 34])

  "지난 1시간"
  "지난 한시간"
  (datetime-interval [2013 2 12 3] [2013 2 12 4])

  "지난 24시간"
  "지난 스물네시간"
  (datetime-interval [2013 2 11 4] [2013 2 12 4])

  "다음 3시간"
  (datetime-interval [2013 2 12 5] [2013 2 12 8])

  "지난 2일"
  (datetime-interval [2013 2 10] [2013 2 12])

  "다음 3일"
  (datetime-interval [2013 2 13] [2013 2 16])

  "다음 몇일"
  (datetime-interval [2013 2 13] [2013 2 16])

  "지난 2주"
  "지난 이주"
  (datetime-interval [2013 1 28 :grain :week] [2013 2 11 :grain :week])

  "다음 3주"
  "다음 삼주"
  (datetime-interval [2013 2 18 :grain :week] [2013 3 11 :grain :week])

  "지난 2달"
  "지난 두달"
  (datetime-interval [2012 12] [2013 02])

  "다음 3달"
  "다음 세달"
  (datetime-interval [2013 3] [2013 6])

  "지난 2년"
  "지난 이년"
  (datetime-interval [2011] [2013])

  "다음 3년"
  "다음 삼년"
  (datetime-interval [2014] [2017])

;
  ; Explicit intervals

  "7월 13일 - 15일"
  "7월 13일 부터 15일"
  (datetime-interval [2013 7 13] [2013 7 16])

  "8월 8일 - 8월 13일"
  "8월 8일부터 8월 13일"
  (datetime-interval [2013 8 8] [2013 8 14])

  "9:30 부터 11:00"
  "9:30 ~ 11:00"
  (datetime-interval [2013 2 12 9 30] [2013 2 12 11 1])

  "목요일 9:30 부터 11:00"
  "목요일 9:30 ~ 11:00"
  (datetime-interval [2013 2 14 9 30] [2013 2 14 11 1])

  "목요일 오전9시 부터 오전11시"
  (datetime-interval [2013 2 14 9] [2013 2 14 12])

  "11:30-1:30"
  (datetime-interval [2013 2 12 11 30] [2013 2 12 13 31])

  "2주 이내에"
  (datetime-interval [2013 2 12 4 30 0] [2013 2 26])

  "오후 2시까지"
  (datetime-interval [2013 2 12 4 30 0] [2013 2 12 14])

;  "다음달말"
;  (datetime 2013 4 1)
;
;  "다음달 말까지"
;  (datetime-interval [2013 2 12 4 30 0] [2013 4 1 0])

; Timezones

  "4pm CET"
  (datetime 2013 2 12 16 :hour 4 :meridiem :pm :timezone "CET")

  "목요일 8:00 GMT"
  (datetime 2013 2 14 8 00 :timezone "GMT")

  ;; Bookface tests
  "오늘 오후두시에"
  "오후두시에"
  (datetime 2013 2 12 14)

  "4/25 오후4시에"
  (datetime 2013 4 25 16)

  "내일 3pm"
  (datetime 2013 2 13 15)

  "오후2시 이후"
  (datetime 2013 2 12 14 :direction :after)

  "5일 후"
  (datetime 2013 2 17 4 :direction :after)

  "오전11시 전"
  "오전11시 이전"
  (datetime 2013 2 12 11 :direction :before)

  "오후에"
  (datetime-interval [2013 2 12 12] [2013 2 12 19])

  "15분안"
  (datetime 2013 2 12 4 45 0)

  "점심이후"
  (datetime-interval [2013 2 12 12] [2013 2 12 14])

  "10:30"
  (datetime 2013 2 12 10 30)

  "아침" ;; how should we deal with fb mornings?
  (datetime-interval [2013 2 12 4] [2013 2 12 12])

  "오후12시"
  "정오"
  (datetime 2013 2 12 12)

  "오전12시"
  "자정"
  (datetime 2013 2 13 0)

  "3월"
  "3월에"
  (datetime 2013 3)
)
