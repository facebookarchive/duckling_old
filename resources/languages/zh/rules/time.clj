(

  "intersect"
  [(dim :time #(not (:latent %))) (dim :time #(not (:latent %)))] ; sequence of two tokens with a time dimension
  (intersect %1 %2)

  ; mostly for January 12, 2005
  ; this is a separate rule, because commas separate very specific tokens
  ; so we want this rule's classifier to learn this
  "intersect by \",\""
  [(dim :time #(not (:latent %))) #"," (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)

  ;;;;;;;;;;;;;;;;;;;
  ;; Named things
  "named-day"
  #"星期一|周一|礼拜一|禮拜一|週一"
  (day-of-week 1)

  "named-day"
  #"星期二|周二|礼拜二|禮拜二|週二"
  (day-of-week 2)

  "named-day"
  #"星期三|周三|礼拜三|禮拜三|週三"
  (day-of-week 3)

  "named-day"
  #"星期四|周四|礼拜四|禮拜四|週四"
  (day-of-week 4)

  "named-day"
  #"星期五|周五|礼拜五|禮拜五|週五"
  (day-of-week 5)

  "named-day"
  #"星期六|周六|礼拜六|禮拜六|週六"
  (day-of-week 6)

  "named-day"
  #"星期日|星期天|礼拜天|周日|禮拜天|週日"
  (day-of-week 7)

  "named-month"
  #"一月份?"
  (month 1)

  "named-month"
  #"二月份?"
  (month 2)

  "named-month"
  #"三月份?"
  (month 3)

  "named-month"
  #"四月份?"
  (month 4)

  "named-month"
  #"五月份?"
  (month 5)

  "named-month"
  #"六月份?"
  (month 6)

  "named-month"
  #"七月份?"
  (month 7)

  "named-month"
  #"八月份?"
  (month 8)

  "named-month"
  #"九月份?"
  (month 9)

  "named-month"
  #"十月份?"
  (month 10)

  "named-month"
  #"十一月份?"
  (month 11)

  "named-month"
  #"十二月份?"
  (month 12)

  ; Holiday TODO: check online holidays
  ; or define dynamic rule (last thursday of october..)

  "new year's day"
  #"元旦[节節]?"
  (month-day 1 1)

  "valentine's day"
  #"情人[节節]"
  (month-day 2 14)

  "women's day"
  #"[妇婦]女[节節]"
  (month-day 3 8)

  "labor day"
  #"劳动节|勞動節"
  (month-day 5 1)

  "children's day"
  #"[儿兒]童[节節]"
  (month-day 6 1)

  "army's day"
  #"建(军节|軍節)"
  (month-day 8 1)

  "national day"
  #"(国庆|國慶)[节節]?"
  (month-day 10 1)

  "christmas"
  #"(圣诞|聖誕)[节節]?"
  (month-day 12 25)

  ; Including cycles

  "now"
  #"现在|此时|此刻|当前|現在|此時|當前"
  (cycle-nth :second 0)

  "the day before yesterday"
  #"前天"
  (cycle-nth :day -2)

  "yesterday"
  #"昨天"
  (cycle-nth :day -1)

  "today"
  #"今天"
  (cycle-nth :day 0)

  "tomorrow"
  #"明天"
  (cycle-nth :day 1)

  "the day after tomorrow"
  #"后天|後天"
  (cycle-nth :day 2)

;;
  ;; This, Next, Last

  ;; assumed to be strictly in the future:
  ;; "this Monday" => next week if today in Monday
  "this|next <day-of-week>"
  [#"(?i)今|明|下[个|個]?" {:form :day-of-week}]
  (pred-nth-not-immediate %2 0)

  ;; for other preds, it can be immediate:
  ;; "this month" => now is part of it
  ; See also: cycles in en.cycles.clj
  "this <time>"
  [#"(?i)今|这|這" (dim :time)]
  (pred-nth %2 0)

  "next <time>"
  [#"(?i)明|下[个|個]?" (dim :time)] ;下[个|個]?
  (pred-nth %2 1)

  "last <time>"
  [#"(?i)去" (dim :time)]
  (pred-nth %2 -1)

  ;;Ordinals
  "nth <time> of <time>"
  [(dim :time) (dim :ordinal) (dim :time)]
  (pred-nth (intersect %1 %3) (dec (:value %2)))

  "nth <time> of <time>"
  [(dim :time) #"的" (dim :ordinal) (dim :time)]
  (pred-nth (intersect %1 %4) (dec (:value %3)))

  ;specific for year in chinese
  "last year"
  #"去年"
  (cycle-nth :year -1)

  "this year"
  #"今年"
  (cycle-nth :year 0)

  "next year"
  #"明年"
  (cycle-nth :year 1)

  "this <day-of-week>" ; assumed to be in the future
  [#"这|這" {:form :named-day}]
  (pred-nth %2 0)

  "last tuesday, last july"
  [#"上" (dim :time)]
  (pred-nth %2 -1)


  ; "next tuesday, next july"
  ; [#"下" (dim :time)]
  ; (pred-nth %2 1)


  ;; part of day (morning, evening...)

  "morning" ;; TODO "3am this morning" won't work since morning starts at 4...
  [#"早上|早晨"]
  (assoc (interval (hour 4 false) (hour 12 false) false) :form :part-of-day :latent true)

  "afternoon"
  [#"下午|中午"]
  (assoc (interval (hour 12 false) (hour 19 false) false) :form :part-of-day :latent true)

  "evening|night"
  [#"晚上|晚间"]
  (assoc (interval (hour 18 false) (hour 0 false) false) :form :part-of-day :latent true)

  "<dim time> <part-of-day>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [(dim :time) {:form :part-of-day}]
  (intersect %1 %2)

  "day of month (numeric with day symbol)"
  [(dim :number) #"号|號|日"]
  (assoc (day-of-month (:value %1)) :latent true :form :day-of-month)

  "month (numeric with month symbol)" ;TOBECHECKED
  [(integer 1 12) #"月"]
  (assoc (month (:value %1)) :latent true :form :named-month)

  "year (numeric with year symbol)"
  [(integer 1000 9999) #"年"]
  (year (:value %1))

  "absorption of , after named day"
  [{:form :named-day} #","]
  %1

  "<named-month> <day-of-month>" ; 三月十二号
  [{:form :named-month} {:form :day-of-month}]
  (intersect %1 %2)



  ; ;; Hours and minutes (absolute time)

  "<integer> (latent time-of-day)"
  (integer 0 23)
  (assoc (hour (:value %1) true)
    :form :time-of-day
    :latent true)

  "last night"
  [#"昨晚|昨天晚上"]  ;; special form in Chinese
  (assoc (intersect (cycle-nth :day -1)
                    (interval (hour 18 false) (hour 0 false) false))
         :form :part-of-day) ; no :latent

  "tonight"
  [#"今晚|今天晚上"]
  (assoc (intersect (cycle-nth :day 0)
                    (interval (hour 18 false) (hour 0 false) false))
         :form :part-of-day) ; no :latent

  "tomorrow night"
  [#"明晚|明天晚上"]  ;; special form in Chinese
  (assoc (intersect (cycle-nth :day 1)
                    (interval (hour 18 false) (hour 0 false) false))
         :form :part-of-day) ; no :latent

  "in|during the <part-of-day>" ;; removes latent
  [{:form :part-of-day} #"点|點"]
  (dissoc %1 :latent)

  "<time-of-day> o'clock"
  [{:form :time-of-day} #"點|点"]
  (dissoc %1 :latent)

  "hh:mm (time-of-day)"
  #"(?i)((?:[01]?\d)|(?:2[0-3])):([0-5]\d)"
  (hour-minute (Integer/parseInt (first (:groups %1)))
               (Integer/parseInt (second (:groups %1)))
               true)

  "hhmm (military time-of-day)"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))([0-5]\d)"
  (-> (hour-minute (Integer/parseInt (first (:groups %1)))
                   (Integer/parseInt (second (:groups %1)))
                   false) ; not a 12-hour clock)
      (assoc :latent true))

  "<time-of-day> am|pm"
  [{:form :time-of-day} #"(?i)([ap])(\s|\.)?m?\.?"]
  (let [[p meridiem] (if (= "a" (-> %2 :groups first clojure.string/lower-case))
                       [[(hour 0) (hour 12) false] :am]
                       [[(hour 12) (hour 0) false] :pm])]
    (-> (intersect %1 (apply interval p))
        (assoc :form :time-of-day)))

  "<part-of-day> <dim time>" ; in Chinese, we say `早上8点`, instead of `8点早上`
  [{:form :part-of-day} (dim :time)]
  (intersect %1 %2)

  "noon"
  #"中午"
  (hour 12 false)

  "midnight"
  #"午夜|凌晨"
  (hour 0 false)

  "quarter (relative minutes)"
  #"一刻"
  {:relative-minutes 15}

  "half (relative minutes)"
  #"半"
  {:relative-minutes 30}

  "number (as relative minutes)"
  (integer 1 59)
  {:relative-minutes (:value %1)}

  "relative minutes after|past <integer> (hour-of-day)"
  [(dim :time :full-hour) #"点|點" #(:relative-minutes %)]
  (hour-relativemin (:full-hour %1) (:relative-minutes %3) true)


  "relative minutes to|till|before <integer> (hour-of-day)"
  [(dim :time :full-hour) #"[点點]差" #(:relative-minutes %)]
  (hour-relativemin (:full-hour %1) (- (:relative-minutes %3)) true)


  ; special forms for midnight and noon
  "relative minutes to|till|before noon|midnight"
  [#(:for-relative-minutes %) #"差" #(:relative-minutes %)]
  (hour-relativemin (:value %1) true (- (:relative-minutes %3)))

  "relative minutes after|past noon|midnight"
  [#(:for-relative-minutes %) #"过" #(:relative-minutes %)]
  (hour-relativemin (:value %1) true (:relative-minutes %3))

  ; ;; formatted

  "mm/dd/yyyy"
  #"(0?[1-9]|1[0-2])/(3[01]|[12]\d|0?[1-9])/(\d{2,4})"
  (parse-dmy (second (:groups %1)) (first (:groups %1)) (nth (:groups %1) 2) true)

  "yyyy-mm-dd"
  #"(\d{2,4})-(0?[1-9]|1[0-2])-(3[01]|[12]\d|0?[1-9])"
  (parse-dmy (nth (:groups %1) 2) (second (:groups %1)) (first (:groups %1)) true)

  "mm/dd"
  #"(0?[1-9]|1[0-2])/(3[01]|[12]\d|0?[1-9])"
  (parse-dmy (second (:groups %1)) (first (:groups %1)) nil true)

  ; Other intervals: week-end, seasons

  "week-end"
  #"周末|週末"
  (interval (intersect (day-of-week 5) (hour 18 false))
            (intersect (day-of-week 1) (hour 0 false))
            false)
  ;; Time zones

  "timezone"
  #"(?i)(YEKT|YEKST|YAPT|YAKT|YAKST|WT|WST|WITA|WIT|WIB|WGT|WGST|WFT|WEZ|WET|WESZ|WEST|WAT|WAST|VUT|VLAT|VLAST|VET|UZT|UYT|UYST|UTC|ULAT|TVT|TMT|TLT|TKT|TJT|TFT|TAHT|SST|SRT|SGT|SCT|SBT|SAST|SAMT|RET|PYT|PYST|PWT|PT|PST|PONT|PMST|PMDT|PKT|PHT|PHOT|PGT|PETT|PETST|PET|PDT|OMST|OMSST|NZST|NZDT|NUT|NST|NPT|NOVT|NOVST|NFT|NDT|NCT|MYT|MVT|MUT|MST|MSK|MSD|MMT|MHT|MEZ|MESZ|MDT|MAWT|MART|MAGT|MAGST|LINT|LHST|LHDT|KUYT|KST|KRAT|KRAST|KGT|JST|IST|IRST|IRKT|IRKST|IRDT|IOT|IDT|ICT|HOVT|HNY|HNT|HNR|HNP|HNE|HNC|HNA|HLV|HKT|HAY|HAT|HAST|HAR|HAP|HAE|HADT|HAC|HAA|GYT|GST|GMT|GILT|GFT|GET|GAMT|GALT|FNT|FKT|FKST|FJT|FJST|ET|EST|EGT|EGST|EET|EEST|EDT|ECT|EAT|EAST|EASST|DAVT|ChST|CXT|CVT|CST|COT|CLT|CLST|CKT|CHAST|CHADT|CET|CEST|CDT|CCT|CAT|CAST|BTT|BST|BRT|BRST|BOT|BNT|AZT|AZST|AZOT|AZOST|AWST|AWDT|AST|ART|AQTT|ANAT|ANAST|AMT|AMST|ALMT|AKST|AKDT|AFT|AEST|AEDT|ADT|ACST|ACDT)"
  {:dim :timezone
   :value (-> %1 :groups first clojure.string/upper-case)}

  "<time> timezone"
  [(dim :time) (dim :timezone)]
  (assoc %1 :timezone (:value %2))


)
