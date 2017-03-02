(
	;; generic

  "intersect"
  [(dim :time #(not (:latent %))) (dim :time #(not (:latent %)))] ; sequence of two tokens with a time dimension
  (intersect %2 %1)

  "intersect by \"of\", \"from\", \"'s\""
  [(dim :time #(not (:latent %))) #"(?i)của|trong" (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %3 %1)

  ;;;;;;;;;;;;;;;;;;;
  ;; Named things
  "named-day"
  #"thứ 2|thứ hai"
  (day-of-week 1)

  "named-day"
  #"thứ 3|thứ ba"
  (day-of-week 2)

  "named-day"
  #"thứ 4|thứ bốn|thứ tư"
  (day-of-week 3)

  "named-day"
  #"thứ 5|thứ năm"
  (day-of-week 4)

  "named-day"
  #"thứ 6|thứ sáu"
  (day-of-week 5)

  "named-day"
  #"thứ 7|thứ bảy|thứ bẩy"
  (day-of-week 6)

  "named-day"
  #"chủ nhật"
  (day-of-week 7)

  "named-month"
  #"tháng giêng|tháng một"
  (month 1)

  "named-month"
  #"tháng hai"
  (month 2)

  "named-month"
  #"tháng ba"
  (month 3)

  "named-month"
  #"tháng tư|tháng bốn"
  (month 4)

  "named-month"
  #"tháng năm"
  (month 5)

  "named-month"
  #"tháng sáu"
  (month 6)

  "named-month"
  #"tháng bảy"
  (month 7)

  "named-month"
  #"tháng tám"
  (month 8)

  "named-month"
  #"tháng chín"
  (month 9)

  "named-month"
  #"tháng mười"
  (month 10)

  "named-month"
  #"tháng mười một"
  (month 11)

  "named-month"
  #"tháng mười hai"
  (month 12)

  ; Holiday
  "giáng sinh"
  #"(?i)(ngày )(xmas|christmas|giáng sinh)?"
  (month-day 12 25)

  "tết dương"
  #"(?i)(tết dương)(lịch)?"
  (month-day 1 1)

  "lễ tình nhân"
  #"(?i)(ngày )?(lễ)? tình nhân"
  (month-day 2 14)

  "quốc khánh"
  #"(?i)quốc khánh"
  (month-day 9 3)

  "cách mạng tháng 8"
  #"(?i)cách mạng tháng (8|tám)"
  (month-day 8 19)

  "quốc tế lao động"
  #"(?i)(ngày )?quốc tế lao đông"
  (month-day 5 1)

  "bây giờ"
  #"(?i)(ngay )?(bây giờ|lúc này)"
  (cycle-nth :second 0)

  "hôm nay"
  #"(?i)(hôm nay|bữa nay|ngay hôm nay)"
  (cycle-nth :day 0)

  "ngày mai"
  #"(?i)ngày mai|mai"
  (cycle-nth :day 1)

  "ngày hôm qua"
  #"(?i)(ngày )?(hôm qua|qua)"
  (cycle-nth :day -1)

  "ngày hôm kia"
  #"(?i)(ngày )?hôm kia"
  (cycle-nth :day -2)

  "cuối tháng"
  #"(?i)cuối tháng" ; TO BE IMPROVED
  (cycle-nth :month 1)

  "cuối năm"
  #"(?i)cuối năm"
  (cycle-nth :year 1)

  ;;
  ;; This, Next, Last (Này, Tới, Trước)

  "<day-of-week> tới"
  [{:form :day-of-week} #"(?i)tới"]
  (pred-nth-not-immediate %1 0)

  ;; for other preds, it can be immediate:
  ;; "this month" => now is part of it
  ; See also: cycles in en.cycles.clj
  "<time> này"
  [(dim :time) #"(?i)này|nay"]
  (pred-nth %1 0)

  "<time> tới"
  [(dim :time #(not (:latent %))) #"(?i)tới"]
  (pred-nth-not-immediate %1 0)

  "<time> trước"
  [(dim :time) #"(?i)trước|vừa rồi"]
  (pred-nth %1 -1)

  "<day-of-week> cuối cùng của <time>"
  [{:form :day-of-week} #"(?i)cuối cùng|cuối" #"(?i)của|trong|vào" (dim :time)]
  (pred-last-of %1 %4)

  "<cycle> cuối cùng của <time>"
  [(dim :cycle) #"(?i)cuối cùng|cuối" #"(?i)của|trong|vào" (dim :time)]
  (cycle-last-of %1 %4)

  ; Day of month
  "day of month (numeric with day symbol)"
  [#"(ngày|mồng)( mồng)?" (dim :number)]
  (assoc (day-of-month (:value %2)) :form :day-of-month)

  ; Month
  "month (numeric with month symbol)" ;TOBECHECKED
  [#"tháng" (integer 1 12)]
  (assoc (month (:value %2)) :form :named-month)

  ; Year
  "year (numeric with year symbol)"
  [#"năm" (integer 1000 9999)]
  (year (:value %2))

  ; Day and month
  "<day-of-month> <named-month>" ; ngày 15 tháng 3
  [{:form :day-of-month} {:form :named-month}]
  (intersect %2 %1)

  "<day-of-month> <named-month>" ; 15 tháng 3
  [(dim :number) {:form :named-month}]
  (intersect %2 (day-of-month (:value %1)))

  ; Hours and minutes)
  "time-of-day (latent)"
  (integer 0 23)
  (assoc (hour (:value %1) true) :latent true)

  "time-of-day (latent)"
  [#"(?i)(lúc|vào)( lúc)?" (integer 0 23)]
  (assoc (hour (:value %2) true) :latent true)

  "time-of-day giờ"
  [{:form :time-of-day} #"(?i)giờ"]
  (dissoc %1 :latent)

  "<time-of-day> giờ đúng"
  [{:form :time-of-day} #"(?i)giờ đúng"]
  (dissoc %1 :latent)

  "hh:mm"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))[:.hg]([0-5]\d)"
  (hour-minute (Integer/parseInt (first (:groups %1)))
               (Integer/parseInt (second (:groups %1)))
               true)

  "at hh:mm"
  [#"(?i)(lúc|vào)( lúc)?" #"(?i)((?:[01]?\d)|(?:2[0-3]))[:.hg]([0-5]\d)"]
  (hour-minute (Integer/parseInt (first (:groups %2)))
               (Integer/parseInt (second (:groups %2)))
               true)

  "hh:mm:ss"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))[:.]([0-5]\d)[:.]([0-5]\d)"
  (hour-minute-second (Integer/parseInt (first (:groups %1)))
               (Integer/parseInt (second (:groups %1)))
               (Integer/parseInt (second (next (:groups %1))))
               true)

  ; "hhmm (military)"
  ; #"(?i)((?:[01]?\d)|(?:2[0-3]))([0-5]\d)"
  ; (-> (hour-minute (Integer/parseInt (first (:groups %1)))
  ;                  (Integer/parseInt (second (:groups %1)))
  ;                  false) ; not a 12-hour clock)
  ;     (assoc :latent true))

  "hhmm (military) am|pm" ; hh only from 00 to 12 - Apply for "en"
  [#"(?i)((?:1[012]|0?\d))([0-5]\d)" #"(?i)([ap])\.?m?\.?"]
  ; (-> (hour-minute (Integer/parseInt (first (:groups %1)))
  ;                  (Integer/parseInt (second (:groups %1)))
  ;                  false) ; not a 12-hour clock)
  ;     (assoc :latent true))
  (let [[p meridiem] (if (= "a" (-> %2 :groups first clojure.string/lower-case))
                       [[(hour 0) (hour 12) false] :am]
                       [[(hour 12) (hour 0) false] :pm])]
    (-> (intersect
          (hour-minute (Integer/parseInt (first (:groups %1)))
                       (Integer/parseInt (second (:groups %1)))
                   true)
          (apply interval p))
        (assoc :form :time-of-day)))

  "hhmm (military) sáng|chiều|tối" ; hh from 00 to 12 - Apply for "vi"
  [#"(?i)((?:1[012]|0?\d))([0-5]\d)" #"(?i)(sáng|chiều|tối)"]
  ; (-> (hour-minute (Integer/parseInt (first (:groups %1)))
  ;                  (Integer/parseInt (second (:groups %1)))
  ;                  false) ; not a 12-hour clock)
  ;     (assoc :latent true))
  (let [[p meridiem] (if (= "sáng" (-> %2 :groups first clojure.string/lower-case))
                       [[(hour 0) (hour 12) false] :am]
                       [[(hour 12) (hour 0) false] :pm])]
    (-> (intersect
          (hour-minute (Integer/parseInt (first (:groups %1)))
                       (Integer/parseInt (second (:groups %1)))
                   true)
          (apply interval p))
        (assoc :form :time-of-day)))

  "<time-of-day> am|pm"
  [{:form :time-of-day} #"(?i)(in the )?([ap])(\s|\.)?m?\.?"]
  ;; TODO set_am fn in helpers => add :ampm field
  (let [[p meridiem] (if (= "a" (-> %2 :groups second clojure.string/lower-case))
                       [[(hour 0) (hour 12) false] :am]
                       [[(hour 12) (hour 0) false] :pm])]
    (-> (intersect %1 (apply interval p))
        (assoc :form :time-of-day)))

   "<time-of-day> sáng|chiều|tối"
  [{:form :time-of-day} #"(?i)(sáng|chiều|tối)"]
  ;; TODO set_am fn in helpers => add :ampm field
  (let [[p meridiem] (if (= "sáng" (-> %2 :groups first clojure.string/lower-case))
                       [[(hour 0) (hour 12) false] :am]
                       [[(hour 12) (hour 0) false] :pm])]
    (-> (intersect %1 (apply interval p))
        (assoc :form :time-of-day)))

  "noon" ;tối
  #"(?i)(buổi )?(tối|đêm)"
  (hour 12 false)

  "(hour-of-day) half (relative minutes)" ; 12 giờ rưỡi = 12:30
  [(dim :time :full-hour) #"rưỡi"]
  (hour-relativemin (:full-hour %1) 30 true)

  "(hour-of-day) quarter (relative minutes)" ; 12 giờ kém 20 = 11:40
  [(dim :time :full-hour) #"(?i)kém" (dim :number)]
  (hour-relativemin (:full-hour %1) (- (:value %3)) true)

  "number (as relative minutes)" ;phút
  (integer 1 59)
  {:relative-minutes (:value %1)}

  "<hour-of-day> <integer> (as relative minutes)"
  [(dim :time :full-hour) #(:relative-minutes %)]
  (hour-relativemin (:full-hour %1) (:relative-minutes %2) true)

  "<hour-of-day> <integer> (as relative minutes) phút" ; 10 giờ 45 phút
  [(dim :time :full-hour) #(:relative-minutes %) #"(?i)phút"]
  (hour-relativemin (:full-hour %1) (:relative-minutes %2) true)

  ; Formatted dates and times
  "dd/mm/yyyy"
  #"(3[01]|[12]\d|0?[1-9])[-/](0?[1-9]|1[0-2])[/-](\d{2,4})"
  (parse-dmy (first (:groups %1)) (second (:groups %1)) (nth (:groups %1) 2) true)

  "ngày dd/mm/yyyy"
  [#"(?i)ngày" #"(3[01]|[12]\d|0?[1-9])[-/](0?[1-9]|1[0-2])[/-](\d{2,4})"]
  (parse-dmy (first (:groups %2)) (second (:groups %2)) (nth (:groups %2) 2) true)

  "yyyy-mm-dd"
  #"(\d{2,4})-(0?[1-9]|1[0-2])-(3[01]|[12]\d|0?[1-9])"
  (parse-dmy (nth (:groups %1) 2) (second (:groups %1)) (first (:groups %1)) true)

  "dd/mm"
  #"(3[01]|[12]\d|0?[1-9])/(0?[1-9]|1[0-2])"
  (parse-dmy (first (:groups %1)) (second (:groups %1)) nil true)

  "ngày dd/mm"
  [#"(?i)ngày" #"(3[01]|[12]\d|0?[1-9])/(0?[1-9]|1[0-2])"]
  (parse-dmy (first (:groups %2)) (second (:groups %2)) nil true)

  ; Part of day (morning, evening...). They are intervals.

  "morning" ;;sáng
  [#"(?i)(buổi )?sáng"]
  (assoc (interval (hour 4 false) (hour 12 false) false) :form :part-of-day :latent true)

  "afternoon" ;;chiều
  [#"(?i)(buổi )?chiều"]
  (assoc (interval (hour 12 false) (hour 19 false) false) :form :part-of-day :latent true)

  "noon" ;;buổi tối
  [#"(?i)(buổi )?(tối|đêm)"]
  (assoc (interval (hour 18 false) (hour 0 false) false) :form :part-of-day :latent true)

  "lunch" ;;trưa
  [#"(?i)(buổi )?trưa"]
  (assoc (interval (hour 12 false) (hour 14 false) false) :form :part-of-day :latent true)

  "<part-of-day> (hôm )?nay"
  [#"(?i)this" {:form :part-of-day}]
  (assoc (intersect (cycle-nth :day 0) %1) :form :part-of-day) ;; removes :latent

  "tonight" ;;tối nay
  #"(?i)(tối|đêm)( hôm)? nay"
  (assoc (intersect (cycle-nth :day 0)
                    (interval (hour 18 false) (hour 0 false) false))
         :form :part-of-day) ; no :latent

  "after lunch" ;;sau buổi trưa|qua buổi trưa|qua trưa|sau bữa trưa
  #"(?i)(sau|qua) (buổi |bữa )?trưa"
  (assoc (intersect (cycle-nth :day 0)
                    (interval (hour 13 false) (hour 17 false) false))
         :form :part-of-day) ; no :latent

  "after work" ;;sau giờ làm|sau giờ tan tầm|lúc tan tầm
  #"(?i)(sau giờ làm|sau giờ tan tầm|lúc tan tầm)"
  (assoc (intersect (cycle-nth :day 0)
                    (interval (hour 17 false) (hour 21 false) false))
         :form :part-of-day) ; no :latent

  "<part-of-day> <time>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [{:form :part-of-day} (dim :time)]
  (intersect %1 %2)

  ; Other intervals: week-end, seasons

  "week-end" ; from Friday 6pm to Sunday midnight - Cuối tuần
  #"(?i)(cuối|hết) tuần"
  (interval (intersect (day-of-week 5) (hour 18 false))
            (intersect (day-of-week 1) (hour 0 false))
            false)

  "season" ;; mùa
  #"(?i)mùa? (hè|hạ)" ;could be smarter and take the exact hour into account... also some years the day can change
  (interval (month-day 6 21) (month-day 9 23) false)

  "season" ;; mùa
  #"(?i)mùa? thu"
  (interval (month-day 9 23) (month-day 12 21) false)

  "season" ;; mùa
  #"(?i)mùa? đông"
  (interval (month-day 12 21) (month-day 3 20) false)

  "season" ;; mùa
  #"(?i)mùa? xuân"
  (interval (month-day 3 20) (month-day 6 21) false)

  ; Time zones

  "timezone"
  #"(?i)\b(YEKT|YEKST|YAPT|YAKT|YAKST|WT|WST|WITA|WIT|WIB|WGT|WGST|WFT|WEZ|WET|WESZ|WEST|WAT|WAST|VUT|VLAT|VLAST|VET|UZT|UYT|UYST|UTC|ULAT|TVT|TMT|TLT|TKT|TJT|TFT|TAHT|SST|SRT|SGT|SCT|SBT|SAST|SAMT|RET|PYT|PYST|PWT|PT|PST|PONT|PMST|PMDT|PKT|PHT|PHOT|PGT|PETT|PETST|PET|PDT|OMST|OMSST|NZST|NZDT|NUT|NST|NPT|NOVT|NOVST|NFT|NDT|NCT|MYT|MVT|MUT|MST|MSK|MSD|MMT|MHT|MEZ|MESZ|MDT|MAWT|MART|MAGT|MAGST|LINT|LHST|LHDT|KUYT|KST|KRAT|KRAST|KGT|JST|IST|IRST|IRKT|IRKST|IRDT|IOT|IDT|ICT|HOVT|HNY|HNT|HNR|HNP|HNE|HNC|HNA|HLV|HKT|HAY|HAT|HAST|HAR|HAP|HAE|HADT|HAC|HAA|GYT|GST|GMT|GILT|GFT|GET|GAMT|GALT|FNT|FKT|FKST|FJT|FJST|ET|EST|EGT|EGST|EET|EEST|EDT|ECT|EAT|EAST|EASST|DAVT|ChST|CXT|CVT|CST|COT|CLT|CLST|CKT|CHAST|CHADT|CET|CEST|CDT|CCT|CAT|CAST|BTT|BST|BRT|BRST|BOT|BNT|AZT|AZST|AZOT|AZOST|AWST|AWDT|AST|ART|AQTT|ANAT|ANAST|AMT|AMST|ALMT|AKST|AKDT|AFT|AEST|AEDT|ADT|ACST|ACDT)\b"
  {:dim :timezone
   :value (-> %1 :groups first clojure.string/upper-case)}

  "<time> timezone"
  [(dim :time) (dim :timezone)]
  (set-timezone %1 (:value %2))


  ; Precision
  ; FIXME
  ; - should be applied to all dims not just time-of-day
  ;-  shouldn't remove latency, except maybe -ish

  "<time-of-day> approximately" ; 7ish - ... gần
  [{:form :time-of-day} #"(?i)gần"]
  (-> %1
    (dissoc :latent)
    (merge {:precision "approximate"}))

  "<time-of-day> sharp" ; sharp - ... đúng
  [{:form :time-of-day} #"(?i)đúng"]
  (-> %1
    (dissoc :latent)
    (merge {:precision "exact"}))

  "about <time-of-day>" ; about - khoảng
  [#"(?i)(vào )?khoảng" {:form :time-of-day}]
  (-> %2
    (dissoc :latent)
    (merge {:precision "approximate"}))

  "exactly <time-of-day>" ; sharp - đúng
  [#"(?i)(vào )?đúng" {:form :time-of-day} ]
  (-> %2
    (dissoc :latent)
    (merge {:precision "exact"}))
)
