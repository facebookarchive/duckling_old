(
  "intersect"
  [(dim :time #(not (:latent %))) (dim :time #(not (:latent %)))] ; sequence of two tokens with a time dimension
  (intersect %1 %2)

  "intersect by \"van\" \"voor\""
  [(dim :time #(not (:latent %))) #"(?i)van|voor" (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)

  "intersect by \",\""
  [(dim :time #(not (:latent %))) #"," (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)

  "on <date>" ; on Wed, March 23
  [#"(?i)op" (dim :time)]
  %2 ; does NOT dissoc latent

  "on a <named-day>" ; on a sunday
  [#"(?i)op een" {:form :day-of-week}]
  %2 ; does NOT dissoc latent

  "in <named-month>" ; in September
  [#"(?i)in" {:form :month}]
  %2 ; does NOT dissoc latent

  "named-day"
  #"(?i)maandag|ma"
  (day-of-week 1)

  "named-day"
  #"(?i)dinsdag|di"
  (day-of-week 2)

  "named-day"
  #"(?i)woensdag|wo(e)?"
  (day-of-week 3)

  "named-day"
  #"(?i)donderdag|do"
  (day-of-week 4)

  "named-day"
  #"(?i)vrijdag|vr(ij)?"
  (day-of-week 5)

  "named-day"
  #"(?i)zaterdag|za(t)?"
  (day-of-week 6)

  "named-day"
  #"(?i)zondag|zo"
  (day-of-week 7)

  "named-month"
  #"(?i)januari|jan\.?"
  (month 1)

  "named-month"
  #"(?i)februari|feb\.?"
  (month 2)

  "named-month"
  #"(?i)maart|mrt\.?"
  (month 3)

  "named-month"
  #"(?i)april|apr\.?"
  (month 4)

  "named-month"
  #"(?i)mei"
  (month 5)

  "named-month"
  #"(?i)juni|jun\.?"
  (month 6)

  "named-month"
  #"(?i)juli|jul\.?"
  (month 7)

  "named-month"
  #"(?i)augustus|aug\.?"
  (month 8)

  "named-month"
  #"(?i)september|sep(t)?\.?"
  (month 9)

  "named-month"
  #"(?i)oktober|okt\.?"
  (month 10)

  "named-month"
  #"(?i)november|nov\.?"
  (month 11)

  "named-month"
  #"(?i)december|dec\.?"
  (month 12)

  "now"
  #"(?i)nu|op dit moment|zo snel mogelijk"
  (cycle-nth :second 0)

  "today"
  #"(?i)vandaag|momenteel"
  (cycle-nth :day 0)

  "tomorrow"
  #"(?i)morgen"
  (cycle-nth :day 1)

  "tomorrow night"
  #"(?i)morgenavond"
  (assoc (intersect (cycle-nth :day 1)
    (interval (hour 18 false) (hour 0 false) false))
    :form :part-of-day :latent true)

  "tomorrow lunch"
  [#"(?i)morgenmiddag eten"]
  (assoc (intersect (cycle-nth :day 1)
    (interval (hour 12 false) (hour 14 false) false))
    :form :part-of-day :latent true)
      
  "tomorrow morning"
  #"(?i)morgenochtend"
  (assoc (intersect (cycle-nth :day 1)
    (interval (hour 4 false) (hour 12 false) false))
    :form :part-of-day :latent true)

  "yesterday"
  #"(?i)gisteren?"
  (cycle-nth :day -1)

  "<time-of-day> uur"
  [#(:full-hour %) #"(?i)uur?"]
  (dissoc %1 :latent)

  "at <time-of-day>" ;
  [#"(?i)om?" {:form :time-of-day}]
  (dissoc %2 :latent)

  "at <hour-min>(time-of-day)" ; om 12 uur
  [#"(?i)om?" {:form :time-of-day} #"(?i)uur?"]
  %2

  "season summer"
  #"(?i)deze zomer" ;could be smarter and take the exact hour into account... also some years the day can change
  (interval (month-day 6 21) (month-day 9 23) false)

  "season autumn"
  #"(?i)herfst"
  (interval (month-day 9 23) (month-day 12 21) false)

  "season"
  #"(?i)deze winter"
  (interval (month-day 12 21) (month-day 3 20) false)

  "season spring"
  #"(?i)lente"
  (interval (month-day 3 20) (month-day 6 21) false)

  "christmas"
  #"(?i)kerstmis"
  (month-day 12 25)

  "new year's eve"
  #"(?i)oudejaarsavond"
  (month-day 12 31)

  "new year's day"
  #"(?i)nieuwjaarsdag"
  (month-day 1 1)

  "the day after tomorrow"
  #"(?i)overmorgen"
  (cycle-nth :day 2)

  "the day before yesterday"
  #"(?i)eergisteren"
  (cycle-nth :day -2)

  "evening"
  [#"(?i)avond|vanavond"]
  (assoc (interval (hour 18 false) (hour 0 false) false) :form :part-of-day :latent true)

  "week-end" ; from Friday 6pm to Sunday midnight
  #"(?i)dit weekend"
  (interval (intersect (day-of-week 5) (hour 18 false))
           (intersect (day-of-week 1) (hour 0 false))
           false)

  "this <time>"
  [#"(?i)deze|dit" (dim :time)]
  (pred-nth %2 0)

  "next <time>"
  [#"(?i)over|volgende" (dim :time #(not (:latent %)))]
  (pred-nth-not-immediate %2 0)

  "last <time>"
  [#"(?i)afgelopen|laatste" (dim :time)]
  (pred-nth %2 -1)

  "half (relative minutes)"
  #"half"
  {:relative-minutes 30}

  "quarter (relative minutes)"
  #"(?i)kwart over"
  {:relative-minutes 15}

  "quarter (relative minutes)"
  #"(?i)kwart voor"
  {:relative-minutes 45}

  "quarter (relative hours)"
  #"(?i)kwart voor"
  {:relative-hours -1}

  "number (as relative minutes)"
  (integer 1 59)
  {:relative-minutes (:value %1)}

  "time-of-day (latent)"
  (integer 0 23)
  (assoc (hour (:value %1) true) :latent true)

  "hh:mm:ss"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))[:.]([0-5]\d)[:.]([0-5]\d)"
  (hour-minute-second (Integer/parseInt (first (:groups %1)))
               (Integer/parseInt (second (:groups %1)))
               (Integer/parseInt (second (next (:groups %1))))
               true)

  "hh:mm"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))[:.]([0-5]\d)"
  (hour-minute (Integer/parseInt (first (:groups %1)))
              (Integer/parseInt (second (:groups %1)))
              true)

  "<time-of-day> o'clock"
  [{:form :time-of-day} #"(?i)uur"]
  (dissoc %1 :latent)

  "<dim time> night"
  [{:form :time-of-day} #"(?i)'s avonds|in de avond|avond"]
  (intersect %1 (assoc (interval (hour 12 false) (hour 21 false) false) :form :part-of-day :latent true))

  ;specific rule to address the ambiguity of noche/tarde and extend tarde span from 12 to 21
  "morning" ;; TODO "3am this morning" won't work since morning starts at 4...
  [#"(?i)ochtend(s?)"]
  (assoc (interval (hour 4 false) (hour 12 false) false) :form :part-of-day :latent true)

  "yesterday morning"
  [#"(?i)gisterochtend"]
  (assoc (intersect (cycle-nth :day -1)
    (interval (hour 4 false) (hour 12 false) false))
    :form :part-of-day :latent true)

  "yesterday evening"
  [#"(?i)gisteravond"]
  (assoc (intersect (cycle-nth :day -1)
    (interval (hour 18 false) (hour 0 false) false))
    :form :part-of-day :latent true)

  "midnight|EOD|end of day"
  #"(?i)middernacht"
  (hour 0 false)

  "half <integer> (NL style hour-of-day)"
  [#"half" (dim :time :full-hour)]
  (hour-relativemin (- (:full-hour %2) 1) 30 true)

  ; Formatted dates and times

  "mm/dd/yyyy"
  #"(0?[1-9]|1[0-2])[/-](3[01]|[12]\d|0?[1-9])[-/](\d{2,4})"
  (parse-dmy (second (:groups %1)) (first (:groups %1)) (nth (:groups %1) 2) true)

  "yyyy-mm-dd"
  #"(\d{2,4})-(0?[1-9]|1[0-2])-(3[01]|[12]\d|0?[1-9])"
  (parse-dmy (nth (:groups %1) 2) (second (:groups %1)) (first (:groups %1)) true)

  "mm/dd"
  #"(0?[1-9]|1[0-2])/(3[01]|[12]\d|0?[1-9])"
  (parse-dmy (second (:groups %1)) (first (:groups %1)) nil true)

  "afternoon"
  [#"(?i)(middag|'s middags)"]
  (assoc (interval (hour 12 false) (hour 19 false) false) :form :part-of-day :latent true)

  "relative minutes <integer> (hour-of-day)"
  [#(:relative-minutes %) (dim :time :full-hour)]
  (hour-relativemin (:full-hour %2) (:relative-minutes %1) true)

  "relative minutes <integer> (hour-of-day)"
  [#(:relative-minutes %) (dim :time :full-hour)]
  (let [[h m]
    (if (= (-> %1 :relative-minutes ) 45)
        [(- (-> %2 :full-hour ) 1) (-> %1 :relative-minutes )]
        [(-> %2 :full-hour) (-> %1 :relative-minutes )]
    )]
    (-> (hour-relativemin h m)))

  "relative minutes <integer> (hour-of-day)"
  [#(:relative-minutes %) #"(?i)voor" (dim :time :full-hour)]
  (hour-relativemin (- (:full-hour %3) 1) (:relative-minutes %1) true)

  "relative minutes to|till|before <integer> (hour-of-day)"
  [#(:relative-minutes %) #"(?i)tot|en|voor|tussen|of" (dim :time :full-hour)]
  (hour-relativemin (:full-hour %3) (- (:relative-minutes %1)) true)

  "the <day-of-month> (ordinal)" ; this one is not latent
  [#"(?i)op( de)?" (dim :ordinal #(<= 1 (:value %) 31))]
  (day-of-month (:value %2))

  "<day-of-month> (ordinal)" ; this one is latent
  [(dim :ordinal #(<= 1 (:value %) 31))]
  (assoc (day-of-month (:value %1)) :latent true)

  "the <day-of-month> (non ordinal)" ; this one is latent
  [#"(?i)op( de)?" (integer 1 31)]
  (assoc (day-of-month (:value %2)) :latent true)

  "<named-day> <day-of-month> (ordinal)" ; Friday 18th
  [{:form :day-of-week} (dim :ordinal #(<= 1 (:value %) 31))]
  (intersect %1 (day-of-month (:value %2)))

  "<named-month> <day-of-month> (ordinal)" ; march 12th
  [{:form :month} (dim :ordinal #(<= 1 (:value %) 31))]
  (intersect %1 (day-of-month (:value %2)))

  "<named-month> <day-of-month> (non ordinal)" ; march 12
  [{:form :month} (integer 1 31)]
  (intersect %1 (day-of-month (:value %2)))

  "<day-of-month> (ordinal) of <named-month>"
  [(dim :ordinal #(<= 1 (:value %) 31)) #"(?i)de" {:form :month}]
  (intersect %3 (day-of-month (:value %1)))

  "<day-of-month> (non ordinal) of <named-month>"
  [(integer 1 31) #"(?i)de" {:form :month}]
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

  "lunch"
  [#"(?i)(tijdens de lunch|middageten)"]
  (assoc (interval (hour 12 false) (hour 14 false) false) :form :part-of-day :latent true)

  "after lunch"
  #"(?i)na de(-|\s)?lunch"
  (assoc (intersect (cycle-nth :day 0)
                    (interval (hour 13 false) (hour 17 false) false))
         :form :part-of-day) ; no :latent

  "<time> <part-of-day>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [(dim :time) {:form :part-of-day}]
  (intersect %2 %1)

  "<part-of-day> of <time>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [{:form :part-of-day} #"(?i)om" (dim :time)]
  (intersect %1 %3)

  ; Intervals

  "<month> dd-dd (interval)"
  [{:form :month} #"(3[01]|[12]\d|0?[1-9])" #"\-|tot|en|tot en met?" #"(3[01]|[12]\d|0?[1-9])"]
  (interval (intersect %1 (day-of-month (Integer/parseInt (-> %2 :groups first))))
            (intersect %1 (day-of-month (Integer/parseInt (-> %4 :groups first))))
            true)

  ; Years
  ; Between 1000 and 2100 we assume it's a year
  ; Outside of this, it's safer to consider it's latent

  "year"
  (integer 1000 2100)
  (year (:value %1))

  "year (latent)"
  (integer -10000 999)
  (assoc (year (:value %1)) :latent true)

  "year (latent)"
  (integer 2101 10000)
  (assoc (year (:value %1)) :latent true)

  "absorption of , after named day"
  [{:form :day-of-week} #","]
  %1

  ; One-sided Intervals

  "after <time-of-day>"
  [#"(?i) na" (dim :time)]
  (merge %2 {:direction :after})

  "from <hour-of-day> - <hour-of-day> (interval)"
  [#"(?i)van" {:form :time-of-day} #"tot en met" #(and (= :time-of-day (:form %))
                                                                      (not (:latent %)))]
  (interval %2 %4 :exclusive)

  "<datetime> - <datetime> (interval)"
  [(dim :time #(not (:latent %))) #"\-|en|tot|tot en met?" (dim :time #(not (:latent %)))]
  (interval %1 %3 true)

  "from <datetime> - <datetime> (interval)"
  [#"(?i)van" (dim :time) #"\-|tot|tot en met?" (dim :time)]
  (interval %2 %4 true)

  "between <datetime> and <datetime> (interval)"
  [#"(?i)tussen" (dim :time) #"en" (dim :time)]
  (interval %2 %4 true)

  "between <time-of-day> and <time-of-day> (interval)"
  [#"(?i)tussen" {:form :time-of-day} #"en" {:form :time-of-day}]
  (interval %2 %4 true)

  "from <time-of-day> - <time-of-day> (interval)"
  [#"(?i)(later dan|van)" {:form :time-of-day} #"((maar )?voor)|\-|tot|tot en met?" {:form :time-of-day}]
  (interval %2 %4 true)

  ; Time zones

  "timezone"
  #"(?i)\b(YEKT|YEKST|YAPT|YAKT|YAKST|WT|WST|WITA|WIT|WIB|WGT|WGST|WFT|WEZ|WET|WESZ|WEST|WAT|WAST|VUT|VLAT|VLAST|VET|UZT|UYT|UYST|UTC|ULAT|TVT|TMT|TLT|TKT|TJT|TFT|TAHT|SST|SRT|SGT|SCT|SBT|SAST|SAMT|RET|PYT|PYST|PWT|PT|PST|PONT|PMST|PMDT|PKT|PHT|PHOT|PGT|PETT|PETST|PET|PDT|OMST|OMSST|NZST|NZDT|NUT|NST|NPT|NOVT|NOVST|NFT|NDT|NCT|MYT|MVT|MUT|MST|MSK|MSD|MMT|MHT|MEZ|MESZ|MDT|MAWT|MART|MAGT|MAGST|LINT|LHST|LHDT|KUYT|KST|KRAT|KRAST|KGT|JST|IST|IRST|IRKT|IRKST|IRDT|IOT|IDT|ICT|HOVT|HNY|HNT|HNR|HNP|HNE|HNC|HNA|HLV|HKT|HAY|HAT|HAST|HAR|HAP|HAE|HADT|HAC|HAA|GYT|GST|GMT|GILT|GFT|GET|GAMT|GALT|FNT|FKT|FKST|FJT|FJST|ET|EST|EGT|EGST|EET|EEST|EDT|ECT|EAT|EAST|EASST|DAVT|ChST|CXT|CVT|CST|COT|CLT|CLST|CKT|CHAST|CHADT|CET|CEST|CDT|CCT|CAT|CAST|BTT|BST|BRT|BRST|BOT|BNT|AZT|AZST|AZOT|AZOST|AWST|AWDT|AST|ART|AQTT|ANAT|ANAST|AMT|AMST|ALMT|AKST|AKDT|AFT|AEST|AEDT|ADT|ACST|ACDT)\b"
  {:dim :timezone
   :value (-> %1 :groups first clojure.string/upper-case)}

  "<time> timezone"
  [(dim :time) (dim :timezone)]
  (set-timezone %1 (:value %2))

  "hhmm (military) am|pm" ; hh only from 00 to 12
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

  "<time-of-day> am|pm"
  [{:form :time-of-day} #"(?i)(in the )?([ap])(\s|\.)?m?\.?"]

  ;; TODO set_am fn in helpers => add :ampm field
  (let [[p meridiem] (if (= "a" (-> %2 :groups second clojure.string/lower-case))
                       [[(hour 0) (hour 12) false] :am]
                       [[(hour 12) (hour 0) false] :pm])]
    (-> (intersect %1 (apply interval p))
        (assoc :form :time-of-day)))

)
