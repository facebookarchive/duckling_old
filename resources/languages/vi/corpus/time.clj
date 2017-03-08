(
  ; Context map
  ; Thursday Feb 2, 2017 at 3:55am is now for the test
  {:reference-time (time/t -2 2017 2 2 3 55 0)
   :min (time/t -2 1900)
   :max (time/t -2 2100)}

  "bây giờ"
  "ngay bây giờ"
  "ngay lúc này"
  (datetime 2017 2 2 3 55 00)

  "hôm nay"
  "ngày hôm nay"
  "bữa nay"
  (datetime 2017 2 2)

  "hôm qua"
  "ngày hôm qua"
  (datetime 2017 2 1)

  "ngày mai"
  (datetime 2017 2 3)

  "hôm kia"
  "ngày hôm kia"
  (datetime 2017 1 31)

  "thứ 2"
  "thứ hai"
  (datetime 2017 2 6 :day-of-week 1)

  "thứ 2 ngày 6 tháng 2"
  "thứ 2 mồng 6 tháng 2"
  "thứ hai ngày 6 tháng 2"
  (datetime 2017 2 6 :day-of-week 1 :day 6 :month 2)

  "thứ 3"
  "thứ ba"
  (datetime 2017 2 7)

  "chủ nhật"
  (datetime 2017 2 5)

  "tháng 6"
  "tháng sáu"
  (datetime 2017 6)

  "ngày đầu tiên của tháng ba"
  "ngày đầu tiên của tháng 3"
  (datetime 2017 3 1 :day 1 :month 3)

  "mồng 3 tháng ba"
  "mồng 3 tháng 3"
  (datetime 2017 3 3 :day 3 :month 3)

  "ngày mồng 3 tháng 3 năm 2017"
  "ngày 3 tháng 3 năm 2017"
  "3/3/2017"
  "3/3/17"
  "03/03/2017"
  (datetime 2017 3 3 :day 3 :month 3 :year 2017)

  "ngày mồng 7 tháng 3"
  "ngày 7 tháng ba"
  "7/3"
  "07/03"
  (datetime 2017 3 7 :day 7 :month 3)

  "tháng 10 năm 2017"
  "tháng mười năm 2017"
  (datetime 2017 10 :year 2017 :month 10)

  "03/09/1991"
  "3/9/91"
  "3/9/1991"
  (datetime 1991 9 3 :day 3 :month 9 :year 1991)

  "12 tháng 10 năm 2017"
  "ngày 12 tháng 10 năm 2017"
  (datetime 2017 10 12 :day 12 :month 10 :year 2017)

  "thứ năm tuần tới"
  "thứ 5 tuần sau"
  (datetime 2017 2 9 :day-of-week 4)

  "tháng 3 tới"
  (datetime 2017 3)

  "chủ nhật ngày mồng 9 tháng 4"
  "chủ nhật ngày 9 tháng 4"
  (datetime 2017 4 9 :day-of-week 7 :day 9 :month 4)

  "thứ 2 ngày 6 tháng 2"
  "thứ 2 ngày mồng 6 tháng 2"
  "thứ hai ngày mồng 6 tháng 2"
  (datetime 2017 2 6 :day-of-week 1 :day 6 :month 2)

  "thứ 3 ngày 3 tháng 4 năm 2018"
  (datetime 2018 4 3 :day-of-week 2 :day 3 :month 4 :year 2018)

  ;; Cycles
  "tuần này"
  (datetime 2017 1 30 :grain :week)

  "tuần trước"
  (datetime 2017 1 23 :grain :week)

  "tuần sau"
  (datetime 2017 2 6 :grain :week)

  "tháng trước"
  (datetime 2017 1)

  "tháng sau"
  (datetime 2017 3)

  "quý này"
  (datetime 2017 1 1 :grain :quarter)

  "quý sau"
  (datetime 2017 4 1 :grain :quarter)

  "quý 3"
  "quý ba"
  (datetime 2017 7 1 :grain :quarter)

  "quý 4 năm 2018"
  (datetime 2018 10 1 :grain :quarter)

  "năm trước"
  "năm ngoái"
  (datetime 2016)

  "năm nay"
  (datetime 2017)

  "năm sau"
  (datetime 2018)

  "quý này"
  "quý nay"
  "quý hiện tại"
  (datetime 2017 1 1 :grain :quarter)

  "quý tới"
  "quý tiếp"
  (datetime 2017 4 1 :grain :quarter)

  "quý ba"
  "quý 3"
  (datetime 2017 7 1 :grain :quarter)

  "quý 4 của năm 2018"
  (datetime 2018 10 1 :grain :quarter)

  "năm ngoái"
  "năm trước"
  (datetime 2016)

  "năm nay"
  (datetime 2017)

  "năm tiếp theo"
  "năm kế tiếp"
  "năm tới"
  (datetime 2018)

  "thứ ba vừa rồi"
  (datetime 2017 1 31 :day-of-week 2)

  "thứ ba tới"
  (datetime 2017 2 7 :day-of-week 2)

  "thứ sáu tới" ; when today is Tuesday, "mercredi prochain" is tomorrow
  (datetime 2017 2 3 :day-of-week 5)

  "thứ tư tuần tới"
  "thứ tư của tuần tới"
  (datetime 2017 2 8 :day-of-week 3)

  "thứ sáu tuần này"
  "thứ 6 tuần này"
  "thứ 6 của tuần này"
  (datetime 2017 2 3 :day-of-week 5)

  "thứ năm tuần này"
  "thứ 5 của tuần này"
  (datetime 2017 2 2 :day-of-week 4)

  "tuần đầu tiên của tháng 9 năm 2017"
  (datetime 2017 9 4 :grain :week)

  ;; Hours

  "vào lúc 2 giờ sáng"
  "lúc 2 giờ sáng"
  (datetime 2017 2 3 2)

  "1:18 sáng"
  (datetime 2017 2 2 1 18)

  "lúc 3 giờ tối"
  "vào lúc 3 giờ chiều"
  "vào đúng 3 giờ chiều"
  (datetime 2017 2 2 15 :hour 3 :meridiem :pm)

  "vào khoảng 3 giờ chiều"
  "khoảng 3 giờ chiều"
  (datetime 2017 2 2 15 :hour 3 :meridiem :pm) ;; :precision "approximate"

  "3 giờ rưỡi chiều"
  "3:30 chiều"
  "ba giờ rưỡi chiều"
  (datetime 2017 2 2 15 30 :hour 3 :minute 30 :meridiem :pm)

  "2:30"
  "hai giờ rưỡi"
  (datetime 2017 2 2 14 30 :hour 2 :minute 30)

  "15:23:24"
  (datetime 2017 2 2 15 23 24 :hour 15 :minute 23 :second 24)

  "11 giờ kém 15"
  "10 giờ 45 phút"
  "10:45"
  "10 giờ 45"
  "10h45"
  "10g45"
  (datetime 2017 2 2 10 45 :hour 10 :minute 45)

  "8 giờ tối nay"
  (datetime 2017 2 2 20)

  ;; Mixing date and time

  "vào lúc 7:30 chiều ngày 20 tháng 4 năm 2017"
  "7:30 chiều ngày 20/4/2017"
  (datetime 2017 4 20 19 30 :hour 7 :minute 30 :meridiem :pm)

  ; Seasons

  "mùa hè này"
  "mùa hè năm nay"
  (datetime-interval [2017 6 21] [2017 9 24])

  "mùa đông này"
  (datetime-interval [2016 12 21] [2017 3 21])

  ; Part of day (morning, afternoon...)

  "tối nay"
  "tối hôm nay"
  (datetime-interval [2017 2 2 18] [2017 2 3 00])

  "tối mai"
  "tối ngày mai"
  (datetime-interval [2017 2 3 18] [2017 2 4 00])

  "trưa mai"
  "trưa ngày mai"
  (datetime-interval [2017 2 3 12] [2017 2 3 14])

  "tối qua"
  "tối hôm qua"
  (datetime-interval [2017 2 1 18] [2017 2 2 00])

  "sáng chủ nhật"
  (datetime-interval [2017 2 5 4] [2017 2 5 12])

  ; Intervals involving cycles

  "2 giây vừa rồi"
  (datetime-interval [2017 2 2 3 54 58] [2017 2 2 3 55 00])

  "3 giây tới"
  "3 giây tiếp theo"
  "3 s tiếp theo"
  (datetime-interval [2017 2 2 3 55 01] [2017 2 2 3 55 04])

  "2 phút vừa rồi"
  (datetime-interval [2017 2 2 3 53] [2017 2 2 3 55])

  "3 phút tới"
  "3 phút tiếp theo"
  (datetime-interval [2017 2 2 3 56] [2017 2 2 3 59])

  "một tiếng vừa rồi"
  "1 giờ vừa qua"
  (datetime-interval [2017 2 2 2] [2017 2 2 3])

  "3 tiếng tiếp theo"
  "3 giờ tới"
  (datetime-interval [2017 2 2 4] [2017 2 2 7])

  "2 ngày vừa rồi"
  "2 ngày vừa qua"
  (datetime-interval [2017 1 31] [2017 2 2])

  "3 ngày tới"
  "3 ngày tiếp theo"
  (datetime-interval [2017 2 3] [2017 2 6])

  "2 tháng vừa rồi"
  "2 tháng qua"
  (datetime-interval [2016 12] [2017 02])

  "3 tháng tới"
  "ba tháng tiếp theo"
  (datetime-interval [2017 3] [2017 6])

  "2 năm vừa rồi"
  (datetime-interval [2015] [2017])

  "3 năm tới"
  "3 năm tiếp theo"
  (datetime-interval [2018] [2021])

  ; Timezones

  "4pm CET"
  (datetime 2017 2 2 16 :hour 4 :meridiem :pm :timezone "CET")

  ;; Bookface tests
  "hôm nay lúc 2 giờ chiều"
  "lúc 2 giờ chiều"
  (datetime 2017 2 2 14)

  "lúc 4:00 chiều ngày 23/4"
  (datetime 2017 4 23 16 0)

  "lúc 4 giờ chiều ngày 23 tháng 4"
  (datetime 2017 4 23 16)

  "3 giờ chiều ngày mai"
  (datetime 2017 2 3 15)

  "lúc 1:30 chiều"
  "lúc 1 giờ 30 chiều"
  (datetime 2017 2 2 13 30)

  "sau bữa trưa"
  (datetime-interval [2017 2 2 13] [2017 2 2 17])

  "10:30"
  (datetime 2017 2 2 10 30)

  "buổi sáng" ;; how should we deal with fb mornings?
  (datetime-interval [2017 2 2 4] [2017 2 2 12])

  "thứ hai tới"
  "thứ 2 tới"
  (datetime 2017 2 6 :day-of-week 1)

  "tháng 4"
  "tháng tư"
  (datetime 2017 4)
)
