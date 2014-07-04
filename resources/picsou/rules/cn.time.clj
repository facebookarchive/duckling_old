(

  "two time tokens in a row"
  [(dim :time #(not (:latent %))) (dim :time #(not (:latent %)))] ; sequence of two tokens with a time dimension
  (intersect %1 %2)
  
  "now"
  #"现在|此时|此刻|当前|現在|此時|當前"
  (this-cycle seconds 0)

  "the day before yesterday"
  #"前天"
  (this-cycle days -2)

  "yesterday"
  #"昨天"
  (this-cycle days -1)

  "today"
  #"今天"
  (this-cycle days 0)

  "tomorrow"
  #"明天"
  (this-cycle days 1)

  "the day after tomorrow"
  #"后天|後天"
  (this-cycle days 2)

  "last year"
  #"去年"
  (this-cycle years -1)

  "this year"
  #"今年"
  (this-cycle years 0)

  "next year"
  #"明年"
  (this-cycle years 1)

  "named-day"
  #"星期一|周一|礼拜一|禮拜一|週一"
  (assoc (day-of-week 1) :not-immediate true :form :named-day)

  "named-day"
  #"星期二|周二|礼拜二|禮拜二|週二"
  (assoc (day-of-week 2) :not-immediate true :form :named-day)

  "named-day"
  #"星期三|周三|礼拜三|禮拜三|週三"
  (assoc (day-of-week 3) :not-immediate true :form :named-day)

  "named-day"
  #"星期四|周四|礼拜四|禮拜四|週四"
  (assoc (day-of-week 4) :not-immediate true :form :named-day)

  "named-day"
  #"星期五|周五|礼拜五|禮拜五|週五"
  (assoc (day-of-week 5) :not-immediate true :form :named-day)

  "named-day"
  #"星期六|周六|礼拜六|禮拜六|週六"
  (assoc (day-of-week 6) :not-immediate true :form :named-day)

  "named-day"
  #"星期日|星期天|礼拜天|周日|禮拜天|週日"
  (assoc (day-of-week 7) :not-immediate true :form :named-day)

  "week-end"
  #"周末|週末"
  (assoc (between-days-of-weeks-hours 5 18 1 0) :form :named-day)

  "this <day-of-week>" ; assumed to be in the future
  [#"这|這" {:form :named-day}]
  (this-pred %2 1)

  "last tuesday, last july"
  [#"上" (dim :time)]
  (this-pred %2 -1)

  "next tuesday, next july"
  [#"下" (dim :time)]
  (this-pred %2 1)

  ;; part of day (morning, evening...)

  "morning" ;; TODO "3am this morning" won't work since morning starts at 4...
  [#"早上|早晨"]
  (assoc (between-hours 4 12) :form :part-of-day :latent true)

  "afternoon"
  [#"下午|中午"]
  (assoc (between-hours 12 19) :form :part-of-day :latent true)

  "evening|night"
  [#"晚上|晚间"]
  (assoc (between-hours 18 0) :form :part-of-day :latent true)

  "<dim time> <part-of-day>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [(dim :time) {:form :part-of-day}]
  (intersect %1 %2)

  "last tuesday, last july"
  [#"上" (dim :time)]
  (this-pred %2 -1)

  "next tuesday, next july"
  [#"下" (dim :time)]
  (this-pred %2 1)

  "last tuesday, last july"
  [#"(?i)last" (dim :time)]
  (this-pred %2 -1)

  "named-month"
  #"一月份?"
  (assoc (month-of-year 1) :form :named-month)

  "named-month"
  #"二月份?"
  (assoc (month-of-year 2) :form :named-month)

  "named-month"
  #"三月份?"
  (assoc (month-of-year 3) :form :named-month)

  "named-month"
  #"四月份?"
  (assoc (month-of-year 4) :form :named-month)

  "named-month"
  #"五月份?"
  (assoc (month-of-year 5) :form :named-month)

  "named-month"
  #"六月份?"
  (assoc (month-of-year 6) :form :named-month)

  "named-month"
  #"七月份?"
  (assoc (month-of-year 7) :form :named-month)

  "named-month"
  #"八月份?"
  (assoc (month-of-year 8) :form :named-month)

  "named-month"
  #"九月份?"
  (assoc (month-of-year 9) :form :named-month)

  "named-month"
  #"十月份?"
  (assoc (month-of-year 10) :form :named-month)

  "named-month"
  #"十一月份?"
  (assoc (month-of-year 11) :form :named-month)

  "named-month"
  #"十二月份?"
  (assoc (month-of-year 12) :form :named-month)

  "day of month (numeric with day symbol)"
  [(dim :number) #"号|號|日"]
  (assoc (day-of-month (:val %1)) :latent true :form :day-of-month)

  "month (numeric with month symbol)"
  [(integer 1 12) #"月"]
  (assoc (month-of-year (:val %1)) :latent true :form :named-month)

  "year (numeric with year symbol)"
  [(integer 1000 9999) #"年"]
  (year (:val %1))
  
  "absorption of , after named day"
  [{:form :named-day} #","]
  %1

  "<named-month> <day-of-month>" ; 三月十二号
  [{:form :named-month} {:form :day-of-month}]
  (intersect %1 %2)

  "new year's day"
  #"元旦|元旦节"
  (parse-dmy "1" "1" nil true)

  "valentine's day"
  #"情人节"
  (parse-dmy "14" "2" nil true) 

  "women's day"
  #"妇女节"
  (parse-dmy "8" "3" nil true)

  "labor day"
  #"劳动节"
  (parse-dmy "1" "5" nil true)

  "children's day"
  #"儿童节"
  (parse-dmy "1" "6" nil true)

  "army's day"
  #"建军节"
  (parse-dmy "1" "8" nil true)

  "national day"
  #"国庆节?"
  (parse-dmy "1" "10" nil true)

  "christmas"
  #"圣诞节?"
  (parse-dmy "25" "12" nil true)

  ; ;; Hours and minutes (absolute time)

  "<integer> (latent time-of-day)"
  (integer 0 23)
  (assoc (hour (:val %1) true)
    :form :time-of-day
    :latent true)

  "morning" ;; TODO "3am this morning" won't work since morning starts at 4...
  [#"早上|早晨"]
  (assoc (between-hours 4 12) :form :part-of-day :latent true)

  "afternoon"
  [#"下午|中午"]
  (assoc (between-hours 12 19) :form :part-of-day :latent true)

  "evening|night"
  [#"晚上|晚间"]
  (assoc (between-hours 18 0) :form :part-of-day :latent true)

  "last night"
  [#"昨晚|昨天晚上"]  ;; special form in Chinese
  (assoc (intersect (this-cycle days -1) (between-hours 18 0)) :form :part-of-day) ;; removes :latent

  "tonight"
  [#"今晚|今天晚上"]
  (assoc (intersect (this-cycle days 0) (between-hours 18 0)) :form :part-of-day) ;; removes :latent

  "tomorrow night"
  [#"明晚|明天晚上"]  ;; special form in Chinese
  (assoc (intersect (this-cycle days 1) (between-hours 18 0)) :form :part-of-day) ;; removes :latent

  "in|during the <part-of-day>" ;; removes latent
  [{:form :part-of-day} #"点|點"]
  (dissoc %1 :latent)

  "hh:mm (time-of-day)"
  #"(?i)((?:[01]?\d)|(?:2[0-3])):([0-5]\d)"
  (hour-min (Integer/parseInt (first (:groups %1)))
            true
            (Integer/parseInt (second (:groups %1))))
  
  "hhmm (military time-of-day)"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))([0-5]\d)"
  (-> (hour-min (Integer/parseInt (first (:groups %1)))
                true
                (Integer/parseInt (second (:groups %1))))
      (assoc :latent true))
  
  "<time-of-day> am|pm"
  [{:form :time-of-day} #"(?i)([ap])\.?m?\.?"]
  (-> (intersect %1 (apply between-hours
                       (if (= "a" (-> %2 :groups first .toLowerCase))
                         [0 12]
                         [12 0])))
      (assoc :form :time-of-day))

  "<part-of-day> <dim time>" ; in Chinese, we say `早上8点`, instead of `8点早上`
  [{:form :part-of-day} (dim :time)]
  (intersect %1 %2)

  "noon"
  #"中午"
  (-> (hour 12 false)
      (assoc :form :time-of-day
             :for-relative-minutes true :val 12))

  "midnight"
  #"午夜|凌晨"
  (-> (hour 0 false)
      (assoc :form :time-of-day
             :for-relative-minutes true :val 0))

  "quarter (relative minutes)"
  #"一刻"
  {:relative-minutes 15}

  "half (relative minutes)"
  #"半"
  {:relative-minutes 30}

  "number (as relative minutes)"
  (integer 1 59)
  {:relative-minutes (:val %1)}

  "relative minutes after|past <integer> (hour-of-day)"
  [(integer 0 23) #"点|點" #(:relative-minutes %)]
  (hour-relativemin (:val %1) true (:relative-minutes %3))
  
  "relative minutes to|till|before <integer> (hour-of-day)"
  [(integer 0 23) #"[点點]差" #(:relative-minutes %)]
  (hour-relativemin (:val %1) true (- (:relative-minutes %3)))

  ; special forms for midnight and noon
  "relative minutes to|till|before noon|midnight"
  [#(:for-relative-minutes %) #"差" #(:relative-minutes %)]
  (hour-relativemin (:val %1) true (- (:relative-minutes %3)))
  
  "relative minutes after|past noon|midnight"
  [#(:for-relative-minutes %) #"过" #(:relative-minutes %)]
  (hour-relativemin (:val %1) true (:relative-minutes %3))

  ; ;; formatted

  "mm/dd/yyyy"
  #"(0?\d|10|11|12)/([012]?\d|30|31)/(\d{2,4})"
  (parse-dmy (second (:groups %1)) (first (:groups %1)) (nth (:groups %1) 2) true)

  "yyyy-mm-dd"
  #"(\d{2,4})-(0?\d|10|11|12)-([012]?\d|30|31)"
  (parse-dmy (nth (:groups %1) 2) (second (:groups %1)) (first (:groups %1)) true)
  
  "mm/dd"
  #"(0?\d|10|11|12)/([012]?\d|30|31)"
  (parse-dmy (second (:groups %1)) (first (:groups %1)) nil true)

  ;; Time zones
  
  "timezone"
  #"(?i)(YEKT|YEKST|YAPT|YAKT|YAKST|WT|WST|WITA|WIT|WIB|WGT|WGST|WFT|WEZ|WET|WESZ|WEST|WAT|WAST|VUT|VLAT|VLAST|VET|UZT|UYT|UYST|UTC|ULAT|TVT|TMT|TLT|TKT|TJT|TFT|TAHT|SST|SRT|SGT|SCT|SBT|SAST|SAMT|RET|PYT|PYST|PWT|PT|PST|PONT|PMST|PMDT|PKT|PHT|PHOT|PGT|PETT|PETST|PET|PDT|OMST|OMSST|NZST|NZDT|NUT|NST|NPT|NOVT|NOVST|NFT|NDT|NCT|MYT|MVT|MUT|MST|MSK|MSD|MMT|MHT|MEZ|MESZ|MDT|MAWT|MART|MAGT|MAGST|LINT|LHST|LHDT|KUYT|KST|KRAT|KRAST|KGT|JST|IST|IRST|IRKT|IRKST|IRDT|IOT|IDT|ICT|HOVT|HNY|HNT|HNR|HNP|HNE|HNC|HNA|HLV|HKT|HAY|HAT|HAST|HAR|HAP|HAE|HADT|HAC|HAA|GYT|GST|GMT|GILT|GFT|GET|GAMT|GALT|FNT|FKT|FKST|FJT|FJST|ET|EST|EGT|EGST|EET|EEST|EDT|ECT|EAT|EAST|EASST|DAVT|ChST|CXT|CVT|CST|COT|CLT|CLST|CKT|CHAST|CHADT|CET|CEST|CDT|CCT|CAT|CAST|BTT|BST|BRT|BRST|BOT|BNT|AZT|AZST|AZOT|AZOST|AWST|AWDT|AST|ART|AQTT|ANAT|ANAST|AMT|AMST|ALMT|AKST|AKDT|AFT|AEST|AEDT|ADT|ACST|ACDT)"
  {:dim :timezone
   :value (-> %1 :groups first .toUpperCase)}
  
  "<time> timezone"
  [(dim :time) (dim :timezone)]
  (assoc %1 :timezone (:value %2))


)
