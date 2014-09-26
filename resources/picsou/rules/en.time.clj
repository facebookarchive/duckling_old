(
  ;; generic
  
  "two time tokens in a row"
  [(dim :time #(not (:latent %))) (dim :time #(not (:latent %)))] ; sequence of two tokens with a time dimension
  (intersect %1 %2)

  ; same thing, with "of" in between like "Sunday of last week"
  "two time tokens separated by \"of\", \"from\", \"'s\""
  [(dim :time #(not (:latent %))) #"(?i)of|from|'s" (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)

  ; mostly for January 12, 2005
  ; this is a separate rule, because commas separate very specific tokens
  ; so we want this rule's classifier to learn this
  "two time tokens separated by \",\""
  [(dim :time #(not (:latent %))) #"," (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)
  
  "on <date>" ; on Wed, March 23
  [#"(?i)on" (dim :time)]
  %2 ; does NOT dissoc latent

  
  ;;;;;;;;;;;;;;;;;;;
  ;; Named things
  
  "named-day"
  #"(?i)monday|mon\.?"
  (day-of-week 1)

  "named-day"
  #"(?i)tuesday|tues?\.?"
  (day-of-week 2)

  "named-day"
  #"(?i)wednesday|wed\.?"
  (day-of-week 3)

  "named-day"
  #"(?i)thursday|thu\.?"
  (day-of-week 4)

  "named-day"
  #"(?i)friday|fri\.?"
  (day-of-week 5)

  "named-day"
  #"(?i)saturday|sat\.?"
  (day-of-week 6)

  "named-day"
  #"(?i)sunday|sun\.?"
  (day-of-week 7)

  "named-month"
  #"(?i)january|jan\.?"
  (month 1)

  "named-month"
  #"(?i)february|feb\.?"
  (month 2)

  "named-month"
  #"(?i)march|mar\.?"
  (month 3)

  "named-month"
  #"(?i)april|apr\.?"
  (month 4)

  "named-month"
  #"(?i)may"
  (month 5)

  "named-month"
  #"(?i)june|jun\.?"
  (month 6)

  "named-month"
  #"(?i)july|jul\.?"
  (month 7)

  "named-month"
  #"(?i)august|aug\.?"
  (month 8)

  "named-month"
  #"(?i)september|sept?\.?"
  (month 9)

  "named-month"
  #"(?i)october|oct\.?"
  (month 10)

  "named-month"
  #"(?i)november|nov\.?"
  (month 11)

  "named-month"
  #"(?i)december|dec\.?"
  (month 12)


  ; Holiday TODO: check online holidays
  ; or define dynamic rule (last thursday of october..)
  
  "christmas"
  #"(?i)(xmas|christmas)( day)?"
  (month-day 12 25)

  "christmas eve"
  #"(?i)(xmas|christmas)( day)?('s)? eve"
  (month-day 12 24)
  
  "new year's eve"
  #"(?i)new year'?s? eve"
  (month-day 12 31)

  "new year's day"
  #"(?i)new year'?s?( day)?"
  (month-day 1 1)
             
  "valentine's day"
  #"(?i)valentine'?s?( day)?"
  (month-day 2 14)

  "MLK Day";third Monday of January
   #"(?i)(MLK|Martin Luther King) day"
  (intersect (day-of-week 1) (month 1) (cycle-nth-after :week 3 (month-day 1 1)))

  "memorial day" ;the last Monday of May
  #"(?i)memorial day"
  (pred-last-of (day-of-week 1) (month 5))

  "memorial day weekend" ;the weekend leading to the last Monday of May
  #"(?i)memorial day week(\s|-)?end"
  (interval (intersect (cycle-nth-after :day -3 (pred-last-of (day-of-week 1) (month 5))) (hour 18 false))
            (intersect (pred-last-of (day-of-week 2) (month 5)) (hour 0 false)) ;need to use Tuesday to include monday
            true)

  "independence day"
  #"(?i)independence day"
  (month-day 7 4)

  "labor day" ;first Monday in September
  #"(?i)labor day"
  (intersect (month 9) (day-of-week 1))

  "labor day weekend" ;weekend before 1st Monday in September
  #"(?i)labor day week(\s|-)?end"
  (interval (intersect (cycle-nth-after :day -3 (intersect (day-of-week 1) (month 9))) (hour 18 false))
            (intersect (month 9) (day-of-week 2) (hour 0 false)) ;need to use Tuesday to include monday
            true)

  "Father's Day";third Sunday of June
  #"(?i)father'?s?'?( day)?"
  (intersect (day-of-week 7) (month 6) (cycle-nth-after :week 2 (month-day 6 1)))

  "Mother's Day";second Sunday in May.
  #"(?i)mother'?s?( day)?"
  (intersect (day-of-week 7) (month 5) (cycle-nth-after :week 1 (month-day 5 1)))
              
  "halloween day"
  #"(?i)hall?owe?en( day)?"
  (month-day 10 31)

  "thanksgiving day" ; fourth Thursday of November
  #"(?i)thanks?giving( day)?"
  (intersect (day-of-week 4) (month 11) (cycle-nth-after :week 4 (month-day 11 1)))

  "black fridday"; (the fourth Friday of November),
  #"(?i)black friday"
  (intersect (day-of-week 5) (month 11) (cycle-nth-after :week 4 (month-day 11 1)))

  "absorption of , after named day"
  [{:form :day-of-week} #","]
  %1

  "now"
  #"(?i)(just|right)? ?now|immediately"
  (cycle-nth :second 0)
  
  "today"
  #"(?i)today|(at this time)"
  (cycle-nth :day 0)

  "tomorrow"
  #"(?i)tomm?or?row"
  (cycle-nth :day 1)

  "yesterday"
  #"(?i)yesterday"
  (cycle-nth :day -1)

  "day after tomorrow"
  #"(?i)(the )?day after tomm?or?row"
  (cycle-nth :day 2)

  "day before yesterday"
  #"(?i)(the )?day before yesterday"
  (cycle-nth :day -2)
    
  ;;
  ;; This, Next, Last
  
  ;; assumed to be strictly in the future: 
  ;; "this Monday" => next week if today in Monday
  "this|next <day-of-week>"
  [#"(?i)this|next" {:form :day-of-week}]
  (pred-nth-not-immediate %2 0)

  ;; for other preds, it can be immediate:
  ;; "this month" => now is part of it
  ; See also: cycles in en.cycles.clj
  "this <time>"
  [#"(?i)this|coming" (dim :time)]
  (pred-nth %2 0)

  "next <time>"
  [#"(?i)next" (dim :time)]
  (pred-nth %2 1)

  "last <time>"
  [#"(?i)last" (dim :time)]
  (pred-nth %2 -1)

  "<time> after next"
  [(dim :time) #"(?i)after next"]
  (pred-nth %1 2)

   "<time> before last"
  [(dim :time) #"(?i)before last"]
  (pred-nth %1 -2)
  
  "last <day-of-week> of <time>"
  [#"(?i)last" {:form :day-of-week} #"(?i)of" (dim :time)]
  (pred-last-of %2 %4)
  
  ; Years
  ; Between 1000 and 2100 we assume it's a year
  ; Outside of this, it's safer to consider it's latent
  
  "year (1000-2100 not latent)"
  (integer 1000 2100)
  (year (:value %1))

  "year (latent)"
  (integer -10000 999)
  (assoc (year (:value %1)) :latent true)

  "year (latent)"
  (integer 2101 10000)
  (assoc (year (:value %1)) :latent true)

    ; Day of month appears in the following context:
  ; - the nth
  ; - March nth
  ; - nth of March
  ; - mm/dd (and other numerical formats like yyyy-mm-dd etc.)
  ; In general we are flexible and accept both ordinals (3rd) and numbers (3)

  "the <day-of-month> (ordinal)" ; this one is not latent
  [#"(?i)the" (dim :ordinal #(<= 1 (:value %) 31))]
  (day-of-month (:value %2))

  "<day-of-month> (ordinal)" ; this one is latent
  [(dim :ordinal #(<= 1 (:value %) 31))]
  (assoc (day-of-month (:value %1)) :latent true)

  "the <day-of-month> (non ordinal)" ; this one is latent
  [#"(?i)the" (integer 1 31)]
  (assoc (day-of-month (:value %2)) :latent true)

  "<named-month> <day-of-month> (ordinal)" ; march 12th
  [{:form :month} (dim :ordinal #(<= 1 (:value %) 31))]
  (intersect %1 (day-of-month (:value %2)))
  
  "<named-month> <day-of-month> (non ordinal)" ; march 12
  [{:form :month} (integer 1 31)]
  (intersect %1 (day-of-month (:value %2)))

  "<day-of-month> (ordinal) of <named-month>"
  [(dim :ordinal #(<= 1 (:value %) 31)) #"(?i)of|in" {:form :month}]
  (intersect %3 (day-of-month (:value %1)))

  "<day-of-month> (non ordinal) of <named-month>"
  [(integer 1 31) #"(?i)of|in" {:form :month}]
  (intersect %3 (day-of-month (:value %1)))

  "<day-of-month> (non ordinal) <named-month>" ; 12 mars
  [(integer 1 31) {:form :month}]
  (intersect %2 (day-of-month (:value %1)))

  "<day-of-month>(ordinal) <named-month>" ; 12nd mars
  [(dim :ordinal #(<= 1 (:value %) 31)) {:form :month}]
  (intersect %2 (day-of-month (:value %1)))

  "<day-of-month>(ordinal) <named-month> year" ; 12nd mars 12
  [(dim :ordinal #(<= 1 (:value %) 31)) {:form :month} #"(\d{2,4})"]
  (intersect %2 (day-of-month (:value %1)) (year (Integer/parseInt(first (:groups %3)))))

  ; ; ;; Hours and minutes (absolute time)
  
  "<integer> (latent time-of-day)"
  (integer 0 23)
  (assoc (hour (:value %1) true) :latent true)
  
  "at <time-of-day>" ; at four
  [#"(?i)at|@" {:form :time-of-day}]
  (dissoc %2 :latent)


  "<time-of-day> oclock"
  [{:form :time-of-day} #"(?i)o.?clock"]
  (dissoc %1 :latent)

  "hh:mm (time-of-day)"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))[:.]([0-5]\d)"
  (hour-minute (Integer/parseInt (first (:groups %1)))
               (Integer/parseInt (second (:groups %1)))
               true)
  
  "hhmm (military time-of-day)"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))([0-5]\d)"
  (-> (hour-minute (Integer/parseInt (first (:groups %1)))
                   (Integer/parseInt (second (:groups %1)))
                   false) ; not a 12-hour clock)
      (assoc :latent true))
  
  "hhmm (military time-of-day) am|pm" ; hh only from 00 to 12
  [#"(?i)((?:1[012]|0?\d))([0-5]\d)" #"(?i)([ap])\.?m?\.?"]
  ; (-> (hour-minute (Integer/parseInt (first (:groups %1)))
  ;                  (Integer/parseInt (second (:groups %1)))
  ;                  false) ; not a 12-hour clock)
  ;     (assoc :latent true))
  (let [[p meridiem] (if (= "a" (-> %2 :groups first .toLowerCase))
                       [[(hour 0) (hour 12) false] :am]
                       [[(hour 12) (hour 0) false] :pm])]
    (-> (intersect 
          (hour-minute (Integer/parseInt (first (:groups %1)))
                       (Integer/parseInt (second (:groups %1)))
                   true) 
          (apply interval p))
        (assoc :form :time-of-day)))

  "<time-of-day> am|pm"
  [{:form :time-of-day} #"(?i)([ap])(\s|\.)?m?\.?"]
  ;; TODO set_am fn in helpers => add :ampm field
  (let [[p meridiem] (if (= "a" (-> %2 :groups first .toLowerCase))
                       [[(hour 0) (hour 12) false] :am]
                       [[(hour 12) (hour 0) false] :pm])]
    (-> (intersect %1 (apply interval p))
        (assoc :form :time-of-day)))
  
  "noon"
  #"(?i)noon"
  (hour 12 false)

  "midnight"
  #"(?i)midni(ght|te)"
  (hour 0 false)

  "quarter (relative minutes)"
  #"(?i)(a|one)? ?quarter"
  {:relative-minutes 15}

  "half (relative minutes)"
  #"half"
  {:relative-minutes 30}

  "number (as relative minutes)"
  (integer 1 59)
  {:relative-minutes (:value %1)}

  "<hour-of-day> <integer> (as relative minutes)"
  [(dim :time :full-hour) #(:relative-minutes %)]
  (hour-relativemin (:full-hour %1) (:relative-minutes %2) true)

  "relative minutes to|till|before <integer> (hour-of-day)"
  [#(:relative-minutes %) #"(?i)to|till|before|of" (dim :time :full-hour)]
  (hour-relativemin (:full-hour %3) (- (:relative-minutes %1)) true)
  
  "relative minutes after|past <integer> (hour-of-day)"
  [#(:relative-minutes %) #"(?i)after|past" (dim :time :full-hour)]
  (hour-relativemin (:full-hour %3) (:relative-minutes %1) true)

  "half <integer> (UK style hour-of-day)"
  [#"half" (dim :time :full-hour)]
  (hour-relativemin (:full-hour %2) 30 true)

  
  ; Formatted dates and times

  "mm/dd/yyyy"
  #"(0?\d|10|11|12)/([012]?\d|30|31)/(\d{2,4})"
  (parse-dmy (second (:groups %1)) (first (:groups %1)) (nth (:groups %1) 2) true)

  "yyyy-mm-dd"
  #"(\d{2,4})-(0?\d|10|11|12)-([012]?\d|30|31)"
  (parse-dmy (nth (:groups %1) 2) (second (:groups %1)) (first (:groups %1)) true)
  
  "mm/dd"
  #"(0?\d|10|11|12)/([012]?\d|30|31)"
  (parse-dmy (second (:groups %1)) (first (:groups %1)) nil true)
  
  
  ; Part of day (morning, evening...). They are intervals.

  "morning" ;; TODO "3am this morning" won't work since morning starts at 4...
  [#"(?i)morning"]
  (assoc (interval (hour 4 false) (hour 12 false) false) :form :part-of-day :latent true)

  "afternoon"
  [#"(?i)after ?noon"]
  (assoc (interval (hour 12 false) (hour 19 false) false) :form :part-of-day :latent true)

  "evening|night"
  [#"(?i)evening|night"]
  (assoc (interval (hour 18 false) (hour 0 false) false) :form :part-of-day :latent true)
  
  "in|during the <part-of-day>" ;; removes latent
  [#"(?i)(in|during)( the)?" {:form :part-of-day}]
  (dissoc %2 :latent)
  
  "this <part-of-day>"
  [#"(?i)this" {:form :part-of-day}]
  (assoc (intersect (cycle-nth :day 0) %2) :form :part-of-day) ;; removes :latent

  "tonight"
  #"(?i)toni(ght|gth|te)"
  (assoc (intersect (cycle-nth :day 0)
                    (interval (hour 18 false) (hour 0 false) false)) 
         :form :part-of-day) ; no :latent

  "after work"
  #"(?i)after(-|\s)?work"
  (assoc (intersect (cycle-nth :day 0)
                    (interval (hour 17 false) (hour 21 false) false)) 
         :form :part-of-day) ; no :latent
    
  "<dim time> <part-of-day>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [(dim :time) {:form :part-of-day}]
  (intersect %2 %1)

  ; not sure we need the following rule:
  ; "<time-of-day> <part-of-day>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  ; [{:form :time-of-day} {:form :part-of-day}]
  ; (assoc (intersect %1 %2) :type :value)

  "<part-of-day> of <dim time>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [{:form :part-of-day} #"(?i)of" (dim :time)]
  (intersect %1 %3)

  
  ; Other intervals: week-end, seasons
  
  "week-end" ; from Friday 6pm to Sunday midnight
  #"(?i)week(\s|-)?end"
  (interval (intersect (day-of-week 5) (hour 18 false))
            (intersect (day-of-week 1) (hour 0 false))
            false)

  "season"
  #"(?i)summer" ;could be smarter and take the exact hour into account... also some years the day can change
  (interval (month-day 6 21) (month-day 9 23) false)

  "season"
  #"(?i)fall|autumn"
  (interval (month-day 9 23) (month-day 12 21) false)

  "season"
  #"(?i)winter"
  (interval (month-day 12 21) (month-day 3 20) false)

  "season"
  #"(?i)spring"
  (interval (month-day 3 20) (month-day 6 21) false)

  
  ; Time zones
  
  "timezone"
  #"(?i)(YEKT|YEKST|YAPT|YAKT|YAKST|WT|WST|WITA|WIT|WIB|WGT|WGST|WFT|WEZ|WET|WESZ|WEST|WAT|WAST|VUT|VLAT|VLAST|VET|UZT|UYT|UYST|UTC|ULAT|TVT|TMT|TLT|TKT|TJT|TFT|TAHT|SST|SRT|SGT|SCT|SBT|SAST|SAMT|RET|PYT|PYST|PWT|PT|PST|PONT|PMST|PMDT|PKT|PHT|PHOT|PGT|PETT|PETST|PET|PDT|OMST|OMSST|NZST|NZDT|NUT|NST|NPT|NOVT|NOVST|NFT|NDT|NCT|MYT|MVT|MUT|MST|MSK|MSD|MMT|MHT|MEZ|MESZ|MDT|MAWT|MART|MAGT|MAGST|LINT|LHST|LHDT|KUYT|KST|KRAT|KRAST|KGT|JST|IST|IRST|IRKT|IRKST|IRDT|IOT|IDT|ICT|HOVT|HNY|HNT|HNR|HNP|HNE|HNC|HNA|HLV|HKT|HAY|HAT|HAST|HAR|HAP|HAE|HADT|HAC|HAA|GYT|GST|GMT|GILT|GFT|GET|GAMT|GALT|FNT|FKT|FKST|FJT|FJST|ET|EST|EGT|EGST|EET|EEST|EDT|ECT|EAT|EAST|EASST|DAVT|ChST|CXT|CVT|CST|COT|CLT|CLST|CKT|CHAST|CHADT|CET|CEST|CDT|CCT|CAT|CAST|BTT|BST|BRT|BRST|BOT|BNT|AZT|AZST|AZOT|AZOST|AWST|AWDT|AST|ART|AQTT|ANAT|ANAST|AMT|AMST|ALMT|AKST|AKDT|AFT|AEST|AEDT|ADT|ACST|ACDT)"
  {:dim :timezone
   :value (-> %1 :groups first .toUpperCase)}
  
  "<time> timezone"
  [(dim :time) (dim :timezone)]
  (set-timezone %1 (:value %2))

  ; Precision
  ; only with time of day , may need to open to time token
  ; "<time> approximately" ; 7ish
  ; [(dim :time #(not (:precision %))) #"(?i)(-?ish|approximately)" ]
  ; (-> %1
  ;   (merge {:precision "approximate"}))

  ; "<time> sharp" ; 7pm sharp
  ; [(dim :time #(not (:precision %))) #"(?i)(sharp|exactly)" ]
  ; (-> %1
  ;   (merge {:precision "exact"}))
  
  "<time-of-day> approximately" ; 7ish
  [{:form :time-of-day} #"(?i)(-?ish|approximately)"]
  (-> %1
    (dissoc :latent)
    (merge {:precision "approximate"}))

  "<time-of-day> sharp" ; sharp
  [{:form :time-of-day} #"(?i)(sharp|exactly)"]
  (-> %1
    (dissoc :latent)
    (merge {:precision "exact"}))

  "about <time-of-day>" ; about
  [#"(?i)(about|around|approximately)" {:form :time-of-day}]
  (-> %2
    (dissoc :latent)
    (merge {:precision "approximate"}))

  "exactly <time-of-day>" ; sharp
  [#"(?i)exactly" {:form :time-of-day} ]
  (-> %2
    (dissoc :latent)
    (merge {:precision "exact"}))
  


  ; Intervals

  "<month> dd-dd (interval)"
  [{:form :month} #"([012]?\d|30|31)" #"\-|to|th?ru|through|until" #"([012]?\d|30|31)"]
  (interval (intersect %1 (day-of-month (Integer/parseInt (-> %2 :groups first))))
            (intersect %1 (day-of-month (Integer/parseInt (-> %4 :groups first))))
            true)

  ; Blocked for :latent time. May need to accept certain latents only, like hours

  "<datetime> - <datetime> (interval)"
  [(dim :time #(not (:latent %))) #"\-|to|th?ru|through" (dim :time #(not (:latent %)))]
  (interval %1 %3 true)

  "from <datetime> - <datetime> (interval)"
  [#"(?i)from" (dim :time) #"\-|to|th?ru|through|until" (dim :time)]
  (interval %2 %4 true)

  "between <datetime> and <datetime> (interval)"
  [#"(?i)between" (dim :time) #"and" (dim :time)]
  (interval %2 %4 true)

  ; Specific for time-of-day, to help resolve ambiguities

  "<time-of-day> - <time-of-day> (interval)"
  [{:form :time-of-day} #"\-|to|th?ru|through" {:form :time-of-day}]
  (interval %1 %3 true)

  "from <time-of-day> - <time-of-day> (interval)"
  [#"(?i)(later than|from)" {:form :time-of-day} #"((but )?before)|\-|to|th?ru|through|until" {:form :time-of-day}]
  (interval %2 %4 true)

  "between <time-of-day> and <time-of-day> (interval)"
  [#"(?i)between" {:form :time-of-day} #"and" {:form :time-of-day}]
  (interval %2 %4 true)

  "until <time-of-day>(interval)"
  [#"(?i)before|until|up to" {:form :time-of-day}]
  (interval (cycle-nth :second 0) %2 false)

  ; Specific for within duration... Would need to be reworked
  "within <duration>"
  [#"(?i)within" (dim :duration)]
  (interval (cycle-nth :second 0) (in-duration (:value %2)) false)

    ; ;; In this special case, the upper limit is exclusive
  ; "<hour-of-day> - <hour-of-day> (interval)"
  ; [{:form :time-of-day} #"-|to|th?ru|through|until" #(and (= :time-of-day (:form %))
  ; 									  (not (:latent %)))]
  ; (interval %1 %3 :exclusive)

  ; "from <hour-of-day> - <hour-of-day> (interval)"
  ; [#"(?i)from" {:form :time-of-day} #"-|to|th?ru|through|until" #(and (= :time-of-day (:form %))
  ; 									              (not (:latent %)))]
  ; (interval %2 %4 :exclusive)
)
