(
  ;; generic

  "intersect"
  [(dim :time #(not (:latent %))) (dim :time #(not (:latent %)))] ; sequence of two tokens with a time dimension
  (intersect %1 %2)

  ; same thing, with "of" in between like "Sunday of last week"
  "intersect by \"of\", \"from\", \"'s\""
  [(dim :time #(not (:latent %))) #"(?i)i" (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)

  ; mostly for January 12, 2005
  ; this is a separate rule, because commas separate very specific tokens
  ; so we want this rule's classifier to learn this
  "intersect by \",\""
  [(dim :time #(not (:latent %))) #"," (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)

  "on <date>" ; on Wed, March 23
  [#"(?i)den|på|under" (dim :time)]
  %2 ; does NOT dissoc latent

  "on a named-day" ; on a sunday
  [#"(?i)på en" {:form :day-of-week}]
  %2 ; does NOT dissoc latent


  ;;;;;;;;;;;;;;;;;;;
  ;; Named things

  "named-day"
  #"(?i)måndag(en)?|mån\.?"
  (day-of-week 1)

  "named-day"
  #"(?i)tisdag(en)?|tis?\.?"
  (day-of-week 2)

  "named-day"
  #"(?i)onsdag(en)?|ons\.?"
  (day-of-week 3)

  "named-day"
  #"(?i)torsdag(en)?|tors?\.?"
  (day-of-week 4)

  "named-day"
  #"(?i)fredag(en)?|fre\.?"
  (day-of-week 5)

  "named-day"
  #"(?i)lördag(en)?|lör\.?"
  (day-of-week 6)

  "named-day"
  #"(?i)söndag(en)?|sön\.?"
  (day-of-week 7)

  "named-month"
  #"(?i)januari|jan\.?"
  (month 1)

  "named-month"
  #"(?i)februari|feb\.?"
  (month 2)

  "named-month"
  #"(?i)mars|mar\.?"
  (month 3)

  "named-month"
  #"(?i)april|apr\.?"
  (month 4)

  "named-month"
  #"(?i)maj"
  (month 5)

  "named-month"
  #"(?i)juni|jun\.?"
  (month 6)

  "named-month"
  #"(?i)juli|jul\.?"
  (month 7)

  "named-month"
  #"(?i)augusti|aug\.?"
  (month 8)

  "named-month"
  #"(?i)september|sept?\.?"
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

  ; Holiday TODO: check online holidays
  ; or define dynamic rule (last thursday of october..)

  "christmas"
  #"(?i)juldag(en)?"
  (month-day 12 25)

  "christmas eve"
  #"(?i)julafton?"
  (month-day 12 24)

  "new year's eve"
  #"(?i)nyårsafton?"
  (month-day 12 31)

  "new year's day"
  #"(?i)nyårsdag(en)?"
  (month-day 1 1)

  "absorption of , after named day"
  [{:form :day-of-week} #","]
  %1

  "now"
  #"(?i)just nu|nu|(i )?detta ögonblick"
  (cycle-nth :second 0)

  "today"
  #"(?i)i dag|idag"
  (cycle-nth :day 0)

  "tomorrow"
  #"(?i)i morgon|imorgon"
  (cycle-nth :day 1)

  "the day after tomorrow"
  #"(?i)i överimorgon"
  (cycle-nth :day 2)

  "yesterday"
  #"(?i)i går|igår"
  (cycle-nth :day -1)

  "the day before yesterday"
  #"(?i)i förrgår"
  (cycle-nth :day -2)

  "EOM|End of month"
  #"(?i)EOM" ; TO BE IMPROVED
  (cycle-nth :month 1)

  "EOY|End of year"
  #"(?i)EOY"
  (cycle-nth :year 1)

  "Last year"
  #"(?i)i fjol|ifjol"
  (cycle-nth :year -1)

  ;;
  ;; This, Next, Last

  ;; assumed to be strictly in the future:
  ;; "this Monday" => next week if today is Monday
  "this|next <day-of-week>"
  [#"(?i)(kommande|nästa)" {:form :day-of-week}]
  (pred-nth-not-immediate %2 0)

  ;; for other preds, it can be immediate:
  ;; "this month" => now is part of it
  ; See also: cycles in en.cycles.clj
  "this <time>"
  [#"(?i)(denna|detta|i|den här)" (dim :time)]
  (pred-nth %2 0)

  "next <time>"
  [#"(?i)nästa|kommande" (dim :time #(not (:latent %)))]
  (pred-nth-not-immediate %2 0)

  "last <time>"
  [#"(?i)(sista|förra|senaste)" (dim :time)]
  (pred-nth %2 -1)

  "<time> after next"
  [#"(?i)nästa" (dim :time) #"(?i)igen"]
  (pred-nth-not-immediate %2 1)

   "<time> before last"
  [#"(?i)sista" (dim :time) #"(?i)igen"]
  (pred-nth %2 -2)

  "last <day-of-week> of <time>"
  [#"(?i)sista" {:form :day-of-week} #"(?i)av|i" (dim :time)]
  (pred-last-of %2 %4)

  "last <cycle> of <time>"
  [#"(?i)sista" (dim :cycle) #"(?i)av|i" (dim :time)]
  (cycle-last-of %2 %4)

  ; Ordinals
  "nth <time> of <time>"
  [(dim :ordinal) (dim :time) #"(?i)av|i" (dim :time)]
  (pred-nth (intersect %4 %2) (dec (:value %1)))

  "nth <time> of <time>"
  [#"(?i)den" (dim :ordinal) (dim :time) #"(?i)av|i" (dim :time)]
  (pred-nth (intersect %5 %3) (dec (:value %2)))

  "nth <time> after <time>"
  [(dim :ordinal) (dim :time) #"(?i)efter" (dim :time)]
  (pred-nth-after %2 %4 (dec (:value %1)))

  "nth <time> after <time>"
  [#"(?i)den" (dim :ordinal) (dim :time) #"(?i)efter" (dim :time)]
  (pred-nth-after %3 %5 (dec (:value %2)))

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

    ; Day of month appears in the following context:
  ; - the nth
  ; - March nth
  ; - nth of March
  ; - mm/dd (and other numerical formats like yyyy-mm-dd etc.)
  ; In general we are flexible and accept both ordinals (3rd) and numbers (3)

  "the <day-of-month> (ordinal)" ; this one is not latent
  [#"(?i)den" (dim :ordinal #(<= 1 (:value %) 31))]
  (day-of-month (:value %2))

  "<day-of-month> (ordinal)" ; this one is latent
  [(dim :ordinal #(<= 1 (:value %) 31))]
  (assoc (day-of-month (:value %1)) :latent true)

  "the <day-of-month> (non ordinal)" ; this one is latent
  [#"(?i)den" (integer 1 31)]
  (assoc (day-of-month (:value %2)) :latent true)

  "<named-month> <day-of-month> (ordinal)" ; march 12th
  [{:form :month} (dim :ordinal #(<= 1 (:value %) 31))]
  (intersect %1 (day-of-month (:value %2)))

  "<named-month> <day-of-month> (non ordinal)" ; march 12
  [{:form :month} (integer 1 31)]
  (intersect %1 (day-of-month (:value %2)))

  "<day-of-month> (ordinal) of <named-month>"
  [(dim :ordinal #(<= 1 (:value %) 31)) #"(?i)av|i" {:form :month}]
  (intersect %3 (day-of-month (:value %1)))

  "<day-of-month> (non ordinal) of <named-month>"
  [(integer 1 31) #"(?i)av|i" {:form :month}]
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

  "the ides of <named-month>" ; the ides of march 13th for most months, but on the 15th for March, May, July, and October
  [#"(?i)mitten av" {:form :month}]
  (intersect %2 (day-of-month (if (#{3 5 7 10} (:month %2)) 15 13)))

  ;; Hours and minutes (absolute time)

  "time-of-day (latent)"
  (integer 0 23)
  (assoc (hour (:value %1) false) :latent true)

  "<time-of-day> o'clock"
  [#(:full-hour %) #"(?i)( )?h"]
  (dissoc %1 :latent)

  "at <time-of-day>" ; at four
  [#"(?i)klockan|kl.|kl|@" {:form :time-of-day}]
  (dissoc %2 :latent)

  "hh:mm"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))[:.]([0-5]\d)"
  (hour-minute (Integer/parseInt (first (:groups %1)))
               (Integer/parseInt (second (:groups %1)))
               false)

  "hh:mm:ss"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))[:.]([0-5]\d)[:.]([0-5]\d)"
  (hour-minute-second (Integer/parseInt (first (:groups %1)))
               (Integer/parseInt (second (:groups %1)))
               (Integer/parseInt (second (next (:groups %1))))
               false)

  ; "hhmm (military)" not sure if used and in conflict with year 1954
  ; #"(?i)((?:[01]?\d)|(?:2[0-3]))([0-5]\d)"
  ; (-> (hour-minute (Integer/parseInt (first (:groups %1)))
  ;       (Integer/parseInt (second (:groups %1)))
  ;       false) ; not a 12-hour clock)
  ;   (assoc :latent true))

  "noon"
  #"(?i)middag|(kl(\.|ockan)?)? tolv"
  (hour 12 false)

  "midnight|EOD|end of day"
  #"(?i)midnatt|EOD"
  (hour 0 false)

  "quarter (relative minutes)"
  #"(?i)(en)? ?(kvart)(er)?"
  {:relative-minutes 15}

  "half (relative minutes)"
  #"halvtimme"
  {:relative-minutes 30}

  "number (as relative minutes)"
  (integer 1 59)
  {:relative-minutes (:value %1)}

  "<hour-of-day> <integer> (as relative minutes)"
  [(dim :time :full-hour) #(:relative-minutes %)]
  (hour-relativemin (:full-hour %1) (:relative-minutes %2) true)

  "relative minutes to|till|before <integer> (hour-of-day)"
  [#(:relative-minutes %) #"(?i)i" (dim :time :full-hour)]
  (hour-relativemin (:full-hour %3) (- (:relative-minutes %1)) true)

  "relative minutes after|past <integer> (hour-of-day)"
  [#(:relative-minutes %) #"(?i)över" (dim :time :full-hour)]
  (hour-relativemin (:full-hour %3) (:relative-minutes %1) true)

  ; Formatted dates and times

  "dd/mm/yyyy"
  #"(3[01]|[12]\d|0?[1-9])[\/-](0?[1-9]|1[0-2])[\/-](\d{2,4})"
  (parse-dmy (first (:groups %1)) (second (:groups %1)) (nth (:groups %1) 2) true)

  "yyyy-mm-dd"
  #"(\d{2,4})-(0?[1-9]|1[0-2])-(3[01]|[12]\d|0?[1-9])"
  (parse-dmy (nth (:groups %1) 2) (second (:groups %1)) (first (:groups %1)) true)

  "dd/mm"
  #"(3[01]|[12]\d|0?[1-9])[\/-](0?[1-9]|1[0-2])"
  (parse-dmy (first (:groups %1)) (second (:groups %1)) nil true)


  ; Part of day (morning, evening...). They are intervals.

  "morning" ;; TODO "3am this morning" won't work since morning starts at 4...
  [#"(?i)morgon(en)?"]
  (assoc (interval (hour 4 false) (hour 12 false) false) :form :part-of-day :latent true)

  "afternoon"
  [#"(?i)eftermiddag(en)?"]
  (assoc (interval (hour 12 false) (hour 19 false) false) :form :part-of-day :latent true)

  "evening"
  [#"(?i)kväll(en)?"]
  (assoc (interval (hour 18 false) (hour 0 false) false) :form :part-of-day :latent true)

  "night"
  [#"(?i)natt(en)?"]
  (assoc (interval (hour 0 false) (hour 4 false) false) :form :part-of-day :latent true)

  "lunch"
  [#"(?i)lunch(en)?"]
  (assoc (interval (hour 12 false) (hour 14 false) false) :form :part-of-day :latent true)

  "in|during the <part-of-day>" ;; removes latent
  [#"(?i)om|i" {:form :part-of-day}]
  (dissoc %2 :latent)

  "in|during the <part-of-day>" ;; removes latent
  [#"(?i)om|i" {:form :part-of-day} #"(?i)en|ten" ]
  (dissoc %2 :latent)

  "this <part-of-day>"
  [#"(?i)i|denna" {:form :part-of-day}]
  (assoc (intersect (cycle-nth :day 0) %2) :form :part-of-day) ;; removes :latent

  "tonight"
  #"(?i)ikväll"
  (assoc (intersect (cycle-nth :day 0)
                    (interval (hour 18 false) (hour 0 false) false))
         :form :part-of-day) ; no :latent

  "after lunch"
  #"(?i)efter (lunch)"
  (assoc (intersect (cycle-nth :day 0)
                    (interval (hour 13 false) (hour 17 false) false))
         :form :part-of-day) ; no :latent


  "after work"
  #"(?i)efter jobbet"
  (assoc (intersect (cycle-nth :day 0)
                    (interval (hour 17 false) (hour 21 false) false))
         :form :part-of-day) ; no :latent

  "<time> <part-of-day>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [(dim :time) {:form :part-of-day}]
  (intersect %2 %1)

  "<part-of-day> of <time>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [{:form :part-of-day} #"(?i)(en |ten )?den" (dim :time)]
  (intersect %1 %3)


  ; Other intervals: week-end, seasons

  "week-end" ; from Friday 6pm to Sunday midnight
  #"(?i)((week(\s|-)?end)|helg)(en)?"
  (interval (intersect (day-of-week 5) (hour 18 false))
            (intersect (day-of-week 1) (hour 0 false))
            false)

  "season"
  #"(?i)sommar(en)" ;could be smarter and take the exact hour into account... also some years the day can change
  (interval (month-day 6 21) (month-day 9 23) false)

  ; "season"
  ; #"(?i)etterår"
  ; (interval (month-day 9 23) (month-day 12 21) false)

  "season"
  #"(?i)vinter(n)"
  (interval (month-day 12 21) (month-day 3 20) false)

  ; "season"
  ; #"(?i)forår"
  ; (interval (month-day 3 20) (month-day 6 21) false)


  ; Time zones

  "timezone"
  #"(?i)\b(YEKT|YEKST|YAPT|YAKT|YAKST|WT|WST|WITA|WIT|WIB|WGT|WGST|WFT|WEZ|WET|WESZ|WEST|WAT|WAST|VUT|VLAT|VLAST|VET|UZT|UYT|UYST|UTC|ULAT|TVT|TMT|TLT|TKT|TJT|TFT|TAHT|SST|SRT|SGT|SCT|SBT|SAST|SAMT|RET|PYT|PYST|PWT|PT|PST|PONT|PMST|PMDT|PKT|PHT|PHOT|PGT|PETT|PETST|PET|PDT|OMST|OMSST|NZST|NZDT|NUT|NST|NPT|NOVT|NOVST|NFT|NDT|NCT|MYT|MVT|MUT|MST|MSK|MSD|MMT|MHT|MEZ|MESZ|MDT|MAWT|MART|MAGT|MAGST|LINT|LHST|LHDT|KUYT|KST|KRAT|KRAST|KGT|JST|IST|IRST|IRKT|IRKST|IRDT|IOT|IDT|ICT|HOVT|HNY|HNT|HNR|HNP|HNE|HNC|HNA|HLV|HKT|HAY|HAT|HAST|HAR|HAP|HAE|HADT|HAC|HAA|GYT|GST|GMT|GILT|GFT|GET|GAMT|GALT|FNT|FKT|FKST|FJT|FJST|ET|EST|EGT|EGST|EET|EEST|EDT|ECT|EAT|EAST|EASST|DAVT|ChST|CXT|CVT|CST|COT|CLT|CLST|CKT|CHAST|CHADT|CET|CEST|CDT|CCT|CAT|CAST|BTT|BST|BRT|BRST|BOT|BNT|AZT|AZST|AZOT|AZOST|AWST|AWDT|AST|ART|AQTT|ANAT|ANAST|AMT|AMST|ALMT|AKST|AKDT|AFT|AEST|AEDT|ADT|ACST|ACDT)\b"
  {:dim :timezone
   :value (-> %1 :groups first .toUpperCase)}

  "<time> timezone"
  [(dim :time) (dim :timezone)]
  (set-timezone %1 (:value %2))


  ; Precision
  ; FIXME
  ; - should be applied to all dims not just time-of-day
  ;-  shouldn't remove latency, except maybe -ish

  "<time-of-day> approximately" ; 7ish
  [{:form :time-of-day} #"(?i)(cirka|ca\.|-?ish)"]
  (-> %1
    (dissoc :latent)
    (merge {:precision "approximate"}))

  "<time-of-day> sharp" ; sharp
  [{:form :time-of-day} #"(?i)(sharp|precis|exakt)"]
  (-> %1
    (dissoc :latent)
    (merge {:precision "exact"}))

  "about <time-of-day>" ; about
  [#"(?i)(omkring|cirka|vid|runt|ca\.)( kl\.| klockan)?" {:form :time-of-day}]
  (-> %2
    (dissoc :latent)
    (merge {:precision "approximate"}))

  "exactly <time-of-day>" ; sharp
  [#"(?i)(precis|exakt)( kl.| klockan)?" {:form :time-of-day} ]
  (-> %2
    (dissoc :latent)
    (merge {:precision "exact"}))


  ; Intervals

  "<month> dd-dd (interval)"
  [ #"([012]?\d|30|31)(er|\.)?" #"\-|till" #"([012]?\d|30|31)(er|\.)?" {:form :month}]
  (interval (intersect %4 (day-of-month (Integer/parseInt (-> %1 :groups first))))
            (intersect %4 (day-of-month (Integer/parseInt (-> %3 :groups first))))
            true)

  ; Blocked for :latent time. May need to accept certain latents only, like hours

  "<datetime> - <datetime> (interval)"
  [(dim :time #(not (:latent %))) #"\-|till|till och med|tom" (dim :time #(not (:latent %)))]
  (interval %1 %3 true)

  "from <datetime> - <datetime> (interval)"
  [#"(?i)från" (dim :time) #"\-|till|till och med|tom" (dim :time)]
  (interval %2 %4 true)

  "between <datetime> and <datetime> (interval)"
  [#"(?i)mellan" (dim :time) #"och" (dim :time)]
  (interval %2 %4 true)

  ; Specific for time-of-day, to help resolve ambiguities

  "<time-of-day> - <time-of-day> (interval)"
  [#(and (= :time-of-day (:form %)) (not (:latent %))) #"\-|till|till och med|tom" {:form :time-of-day}] ; Prevent set alarm 1 to 5pm
  (interval %1 %3 true)

  "from <time-of-day> - <time-of-day> (interval)"
  [#"(?i)(efter|från)" {:form :time-of-day} #"((men )?före)|\-|till|till och med|tom" {:form :time-of-day}]
  (interval %2 %4 true)

  "between <time-of-day> and <time-of-day> (interval)"
  [#"(?i)mellan" {:form :time-of-day} #"och" {:form :time-of-day}]
  (interval %2 %4 true)

  ; Specific for within duration... Would need to be reworked
  "within <duration>"
  [#"(?i)(innanför|inom)" (dim :duration)]
  (interval (cycle-nth :second 0) (in-duration (:value %2)) false)

  "by the end of <time>"; in this case take the end of the time (by the end of next week = by the end of next sunday)
  [#"(?i)i slutet av" (dim :time)]
  (interval (cycle-nth :second 0) %2 true)

  ; One-sided Intervals

  "until <time-of-day>"
  [#"(?i)innan|före|intill" (dim :time)]
  (merge %2 {:direction :before})

  "after <time-of-day>"
  [#"(?i)efter" (dim :time)]
  (merge %2 {:direction :after})

  ; ;; In this special case, the upper limit is exclusive
  ; "<hour-of-day> - <hour-of-day> (interval)"
  ; [{:form :time-of-day} #"-|to|th?ru|through|until" #(and (= :time-of-day (:form %))
  ;                     (not (:latent %)))]
  ; (interval %1 %3 :exclusive)

  ; "from <hour-of-day> - <hour-of-day> (interval)"
  ; [#"(?i)from" {:form :time-of-day} #"-|to|th?ru|through|until" #(and (= :time-of-day (:form %))
  ;                                 (not (:latent %)))]
  ; (interval %2 %4 :exclusive)

  ; "time => time2 (experiment)"
  ; (dim :time)
  ; (assoc %1 :dim :time2)

)
