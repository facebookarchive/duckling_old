(
  ;; generic
  
  "two time tokens in a row"
  [(dim :time #(not (:latent %))) (dim :time #(not (:latent %)))] ; sequence of two tokens with a time dimension
  (intersect %1 %2)

  ; same thing, with "of" in between like "Sunday of last week"
  "two time tokens separated by 'of, from, 's"
  [(dim :time #(not (:latent %))) #"(?i)of|from|'s" (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)

  ;;
  
  "named-day"
  #"(?i)monday|mon\.?"
  (assoc (day-of-week 1) :not-immediate true :form :named-day)

  "named-day"
  #"(?i)tuesday|tue\.?"
  (assoc (day-of-week 2) :not-immediate true :form :named-day)

  "named-day"
  #"(?i)wednesday|wed\.?"
  (assoc (day-of-week 3) :not-immediate true :form :named-day)

  "named-day"
  #"(?i)thursday|thu\.?"
  (assoc (day-of-week 4) :not-immediate true :form :named-day)

  "named-day"
  #"(?i)friday|fri\.?"
  (assoc (day-of-week 5) :not-immediate true :form :named-day)

  "named-day"
  #"(?i)saturday|sat\.?"
  (assoc (day-of-week 6) :not-immediate true :form :named-day)

  "named-day"
  #"(?i)sunday|sun\.?"
  (assoc (day-of-week 7) :not-immediate true :form :named-day)

  "named-month"
  #"(?i)january|jan\.?"
  (assoc (month-of-year 1) :form :named-month)

  "named-month"
  #"(?i)february|feb\.?"
  (assoc (month-of-year 2) :form :named-month)

  "named-month"
  #"(?i)march|mar\.?"
  (assoc (month-of-year 3) :form :named-month)

  "named-month"
  #"(?i)april|apr\.?"
  (assoc (month-of-year 4) :form :named-month)

  "named-month"
  #"(?i)may"
  (assoc (month-of-year 5) :form :named-month)

  "named-month"
  #"(?i)june|jun\.?"
  (assoc (month-of-year 6) :form :named-month)

  "named-month"
  #"(?i)july|jul?\."
  (assoc (month-of-year 7) :form :named-month)

  "named-month"
  #"(?i)august|aug\.?"
  (assoc (month-of-year 8) :form :named-month)

  "named-month"
  #"(?i)september|sept?\.?"
  (assoc (month-of-year 9) :form :named-month)

  "named-month"
  #"(?i)october|oct\.?"
  (assoc (month-of-year 10) :form :named-month)

  "named-month"
  #"(?i)november|nov\.?"
  (assoc (month-of-year 11) :form :named-month)

  "named-month"
  #"(?i)december|dec\.?"
  (assoc (month-of-year 12) :form :named-month)
  
  "absorption of , after named day"
  [{:form :named-day} #","]
  %1

  "now"
  #"(?i)(just|right)? ?now"
  (this-cycle seconds 0)
  
  "today"
  #"(?i)today|(at this time)"
  (this-cycle days 0)

  "tomorrow"
  #"(?i)tomorrow"
  (this-cycle days 1)

  "yesterday"
  #"(?i)yesterday"
  (this-cycle days -1)
  
  "week-end"
  #"(?i)week-?end"
  (assoc (between-days-of-weeks-hours 5 18 1 0) :form :named-day)
  
  "this <day-of-week>" ; assumed to be in the future
  [#"(?i)this" {:form :named-day}]
  (this-pred %2 1)

  "next tuesday, next july"
  [#"(?i)next" (dim :time)]
  (this-pred %2 1)

  "last tuesday, last july"
  [#"(?i)last" (dim :time)]
  (this-pred %2 -1)

  ;;

  "day of month (ordinal)"
  (dim :ordinal #(<= 1 (:val %) 31))
  (assoc (day-of-month (:val %1)) :latent true :form :day-of-month)

  "the <day-of-month>+suffix" ; this one is not latent
  [#"(?i)the" (dim :ordinal #(<= 1 (:val %) 31))]
  (assoc (day-of-month (:val %2)) :form :day-of-month)

  "day of month (numeric)"
  [(integer 1 31)]
  (assoc (day-of-month (:val %1)) :latent true :form :day-of-month)

  "month (numeric)"
  (integer 1 12)
  (assoc (month-of-year (:val %1)) :latent true)

  "year (four digit)"
  (integer 1000 9999)
  (year (:val %1))
  
  "<named-month> <day-of-month>" ; march 12
  [{:form :named-month} {:form :day-of-month}]
  (intersect %1 %2)
  
  "<named-month> the <day-of-month>"
  [{:form :named-month} #"(?i)the" {:form :day-of-month}]
  (intersect %1 %3)

  "<day-of-month> of <named-month>"
  [{:form :day-of-month} #"(?i)of" {:form :named-month}]
  (intersect %1 %3)

  "<day-of-month> <named-month>" ; 12 mars (this rule removes latency)
  [{:form :day-of-month} {:form :named-month}]
  (intersect %1 %2)

  "on the <day-of-month>"
  [#"(?i)on the" {:form :day-of-month}]
  (dissoc %2 :latent)
  
  "on <date>" ; on Wed, March 23
  [#"(?i)on" (dim :time)]
  %2 ; does NOT dissoc latent

  ;; part of day (morning, evening...)

  "morning" ;; TODO "3am this morning" won't work since morning starts at 4...
  [#"(?i)morning"]
  (assoc (between-hours 4 12) :form :part-of-day :latent true)

  "afternoon"
  [#"(?i)after ?noon"]
  (assoc (between-hours 12 19) :form :part-of-day :latent true)

  "evening|night"
  [#"(?i)evening|night"]
  (assoc (between-hours 18 0) :form :part-of-day :latent true)
  
  "in|during the <part-of-day>" ;; removes latent
  [#"(?i)(in|during) the" {:form :part-of-day}]
  (dissoc %2 :latent)
  
  "this <part-of-day>"
  [#"(?i)this" {:form :part-of-day}]
  (assoc (intersect (this-cycle days 0) %2) :form :part-of-day) ;; removes :latent

  "tonight"
  #"(?i)toni(ght|te)"
  (assoc (intersect (this-cycle days 0) (between-hours 18 0)) :form :part-of-day) ;; removes :latent
    
  "<dim time> <part-of-day>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [(dim :time) {:form :part-of-day}]
  (intersect %1 %2)

  "<time-of-day> <part-of-day>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [{:form :time-of-day} {:form :part-of-day}]
  (intersect %1 %2)

  "<part-of-day> of <dim time>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [{:form :part-of-day} #"(?i)of" (dim :time)]
  (intersect %1 %3)

  ; ;; Hours and minutes (absolute time)
  
  "<integer> (latent time-of-day)"
  (integer 0 23)
  (assoc (hour (:val %1) true)
    :form :time-of-day
    :latent true)
  
  "at|around <time-of-day>" ; at four
  [#"(?i)at|around" {:form :time-of-day}]
  (dissoc %2 :latent)
  
  "<time-of-day> oclock"
  [{:form :time-of-day} #"(?i)o.?clock"]
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
  
  "noon"
  #"(?i)noon"
  (-> (hour 12 false)
      (assoc :form :time-of-day
             :for-relative-minutes true :val 12))

  "midnight"
  #"(?i)midni(ght|te)"
  (-> (hour 0 false)
      (assoc :form :time-of-day
             :for-relative-minutes true :val 0))

  "quarter (relative minutes)"
  #"(?i)(a|one)? ?quarter"
  {:relative-minutes 15}

  "half (relative minutes)"
  #"half"
  {:relative-minutes 30}

  "number (as relative minutes)"
  (integer 1 59)
  {:relative-minutes (:val %1)}

  "<hour-of-day> <integer> (as relative minutes)"
  [(integer 0 23) #(:relative-minutes %)]
  (hour-relativemin (:val %1) true (:relative-minutes %2))

  "relative minutes to|till|before <integer> (hour-of-day)"
  [#(:relative-minutes %) #"(?i)to|till|before|of" (integer 0 23)]
  (hour-relativemin (:val %3) true (- (:relative-minutes %1)))
  
  "relative minutes after|past <integer> (hour-of-day)"
  [#(:relative-minutes %) #"(?i)after|past" (integer 0 23)]
  (hour-relativemin (:val %3) true (:relative-minutes %1))

  ; special forms for midnight and noon
  "relative minutes to|till|before noon|midnight"
  [#(:relative-minutes %) #"(?i)to|till|before|of" #(:for-relative-minutes %)]
  (hour-relativemin (:val %3) true (- (:relative-minutes %1)))
  
  "relative minutes after|past noon|midnight"
  [#(:relative-minutes %) #"(?i)after|past" #(:for-relative-minutes %)]
  (hour-relativemin (:val %3) true (:relative-minutes %1))

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
