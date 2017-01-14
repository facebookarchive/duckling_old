(
  ;; generic

  "intersect"
  [(dim :time #(not (:latent %))) (dim :time #(not (:latent %)))] ; sequence of two tokens with a time dimension
  (intersect %1 %2)

  ;; same thing, with "of" in between like "Sunday of last week"
  ;"intersect by \"of\", \"from\", \"'s\""
  ;[(dim :time #(not (:latent %))) #"(?i)of|from|for|'s" (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  ;(intersect %1 %3)

  ; mostly for January 12, 2005
  ; this is a separate rule, because commas separate very specific tokens
  ; so we want this rule's classifier to learn this
  "intersect by \",\""
  [(dim :time #(not (:latent %))) (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %2)

  "pe <date>" ; on Wed, March 23
  [#"(?i)pe" (dim :time)]
  %2 ; does NOT dissoc latent

  "intr-o <named-day>" ; on a sunday
  [#"(?i)([iî]n(tr)?(-o|o)?)" {:form :day-of-week}]
  %2 ; does NOT dissoc latent

  "in <named-month>" ; in September
  [#"(?i)[iî]n" {:form :month}]
  %2 ; does NOT dissoc latent

  ;;;;;;;;;;;;;;;;;;;
  ;; Named things

  "named-day"
  #"(?i)lun(ea|i)|lun|lu"
  (day-of-week 1)

  "named-day"
  #"(?i)mar[tț](ea|i)|mar|ma"
  (day-of-week 2)

  "named-day"
  #"(?i)miercur(ea|i)|mie|mi"
  (day-of-week 3)

  "named-day"
  #"(?i)jo(ia|i)|jo"
  (day-of-week 4)

  "named-day"
  #"(?i)viner(ea|i)|vin|vi"
  (day-of-week 5)

  "named-day"
  #"(?i)s[aâ]mb[aă]t[aă]|s[aâ]m|s[aâ]"
  (day-of-week 6)

  "named-day"
  #"(?i)duminic[aă]|dum|du"
  (day-of-week 7)

  "named-month"
  #"(?i)ianuarie|ian"
  (month 1)

  "named-month"
  #"(?i)februarie|feb"
  (month 2)

  "named-month"
  #"(?i)martie|mar"
  (month 3)

  "named-month"
  #"(?i)aprilie|apr"
  (month 4)

  "named-month"
  #"(?i)mai"
  (month 5)

  "named-month"
  #"(?i)iunie|iun"
  (month 6)

  "named-month"
  #"(?i)iulie|iul"
  (month 7)

  "named-month"
  #"(?i)august|aug"
  (month 8)

  "named-month"
  #"(?i)septembrie|sept"
  (month 9)

  "named-month"
  #"(?i)octombrie|oct"
  (month 10)

  "named-month"
  #"(?i)noiembrie|noi"
  (month 11)

  "named-month"
  #"(?i)decembrie|dec"
  (month 12)

  ; Holiday TODO: check online holidays
  ; or define dynamic rule (last thursday of october..)

  "craciun"
  #"(?i)(ziua de )? cr[aă]ciun"
  (month-day 12 25)

  "christmas eve"
  #"(?i)(ajun(ul)? )? (de )? cr[aă]ciun"
  (month-day 12 24)

  "new year's eve"
  #"(?i)(ajun(ul)? )? (de )? an(ul)? nou"
  (month-day 12 31)

  "new year's day"
  #"(?i)(siua de )? an(ul)? nou"
  (month-day 1 1)

  "valentine's day"
  #"(?i)sf\.?([aâ]ntul)? Valentin"
  (month-day 2 14)

  "Mother's Day";second Sunday in May.
  #"(?i)ziua (mamei|memeii)"
  (intersect (day-of-week 7) (month 5) (cycle-nth-after :week 1 (month-day 5 1)))

  "halloween day"
  #"(?i)hall?owe?en"
  (month-day 10 31)

  "black friday"; (the fourth Friday of November),
  #"(?i)black frid?day"
  (intersect (day-of-week 5) (month 11) (cycle-nth-after :week 4 (month-day 11 1)))

  "absorption of , after named day"
  [{:form :day-of-week} #","]
  %1

  "acum"
  #"(?i)(chiar)? ?acum|imediat"
  (cycle-nth :second 0)

  "azi"
  #"(?i)azi|ast[aă]zi"
  (cycle-nth :day 0)

  "maine"
  #"(?i)m[aâ]ine"
  (cycle-nth :day 1)

  "ieri"
  #"(?i)ieri"
  (cycle-nth :day -1)

  "EOM|End of month"
  #"(?i)sf[aâ]r[sș]itul lunii" ; TO BE IMPROVED
  (cycle-nth :month 1)

  "EOY|End of year"
  #"(?i)sf[aâ]r[sș]itul anului"
  (cycle-nth :year 1)

  ;;
  ;; This, Next, Last

  ;; assumed to be strictly in the future:
  ;; "this Monday" => next week if today is Monday
  "this|next <day-of-week>"
  [{:form :day-of-week} #"(?i)aceasta|[aă]sta|urm[aă]toare"]
  (pred-nth-not-immediate %1 0)

  ;; for other preds, it can be immediate:
  ;; "this month" => now is part of it
  ; See also: cycles in en.cycles.clj
  "<time> (aceasta|acesta|[aă]sta)"
  [(dim :time) #"(?i)aceasta|[aă]sta|urm[aă]toare"]
  (pred-nth %1 0)

  "<time> urm[aă]to(are|r)"
  [#"(?i)next" (dim :time #(not (:latent %)))]
  (pred-nth-not-immediate %2 0)

  "<time> trecut[aă]?"
  [(dim :time) #"(?i)(trecut[aă]?)"]
  (pred-nth %2 -1)

  "last <day-of-week> of <time>"
  [#"(?i)ultima" {:form :day-of-week} #"(?i)din" (dim :time)]
  (pred-last-of %2 %4)

  "last <cycle> of <time>"
  [#"(?i)ultim(ul|a)" (dim :cycle) #"(?i)din" (dim :time)]
  (cycle-last-of %2 %4)

  ; Ordinals
  "nth <time> of <time>"
  [(dim :ordinal) (dim :time) #"(?i)din" (dim :time)]
  (pred-nth (intersect %4 %2) (dec (:value %1)))

  "nth <time> after <time>"
  [(dim :ordinal) (dim :time) #"(?i)dup[aă]" (dim :time)]
  (pred-nth-after %2 %4 (dec (:value %1)))

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

  "the <day-of-month> (number)" ; this one is not latent
  [#"pe" (dim :number #(<= 1 (:value %) 31))]
  (day-of-month (:value %2))

  "<day-of-month> (number)" ; this one is latent
  [(integer 1 31)]
  (assoc (day-of-month (:value %1)) :latent true)

  "pe <day-of-month> (non ordinal)" ; this one is latent
  [#"pe" (integer 1 31)]
  (assoc (day-of-month (:value %2)) :latent true)

  "<named-day> <day-of-month> (number)" ; Friday 18th
  [{:form :day-of-week} (dim :number #(<= 1 (:value %) 31))]
  (intersect %1 (day-of-month (:value %2)))

  "<named-day> pe <day-of-month> (number)" ; Friday 18th
  [{:form :day-of-week} #"pe" (dim :number #(<= 1 (:value %) 31))]
  (intersect %1 (day-of-month (:value %3)))

  "<named-month> <day-of-month> (non ordinal)" ; march 12
  [{:form :month} (integer 1 31)]
  (intersect %1 (day-of-month (:value %2)))

  "<day-of-month> (number) of <named-month>"
  [(dim :number #(<= 1 (:value %) 31)) #"(?i)din" {:form :month}]
  (intersect %3 (day-of-month (:value %1)))

  "<day-of-month> (non ordinal) of <named-month>"
  [(integer 1 31) #"(?i)din" {:form :month}]
  (intersect %3 (day-of-month (:value %1)))

  "<day-of-month> (non ordinal) <named-month>" ; 12 mars
  [(integer 1 31) {:form :month}]
  (intersect %2 (day-of-month (:value %1)))

  "<day-of-month>(number) <named-month>" ; 12nd mars
  [(dim :number #(<= 1 (:value %) 31)) {:form :month}]
  (intersect %2 (day-of-month (:value %1)))

  "<day-of-month>(number) <named-month> year" ; 12nd mars 12
  [(dim :number #(<= 1 (:value %) 31)) {:form :month} #"(\d{2,4})"]
  (intersect %2 (day-of-month (:value %1)) (year (Integer/parseInt(first (:groups %3)))))

  ;; Hours and minutes (absolute time)

  "time-of-day (latent)"
  (integer 0 23)
  (assoc (hour (:value %1) true) :latent true)

  "la <time-of-day>" ; at four
  [#"(?i)la|@ (ora)?" {:form :time-of-day}]
  (dissoc %2 :latent)


  "hh:mm"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))[:.]([0-5]\d)"
  (hour-minute (Integer/parseInt (first (:groups %1)))
               (Integer/parseInt (second (:groups %1)))
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

  "noon"
  #"(?i)noon"
  (hour 12 false)

  "sfert (relative minutes)"
  #"(?i)(un)? ?sfert"
  {:relative-minutes 15}

  "jumatate (relative minutes)"
  #"jum[aă]tate|jumate"
  {:relative-minutes 30}

  "number (as relative minutes)"
  (integer 1 59)
  {:relative-minutes (:value %1)}

  "<hour-of-day> <integer> (as relative minutes)"
  [(dim :time :full-hour) #(:relative-minutes %)]
  (hour-relativemin (:full-hour %1) (:relative-minutes %2) true)

  ; Formatted dates and times

  "dd/mm/yyyy"
    #"(3[01]|[12]\d|0?[1-9])[-/](0?[1-9]|1[0-2])[/-](\d{2,4})"
    (parse-dmy (second (:groups %1)) (first (:groups %1)) (nth (:groups %1) 2) true)

  "mm/dd/yyyy"
  #"(0?[1-9]|1[0-2])[/-](3[01]|[12]\d|0?[1-9])[-/](\d{2,4})"
  (parse-dmy (second (:groups %1)) (first (:groups %1)) (nth (:groups %1) 2) true)

  "yyyy-mm-dd"
  #"(\d{2,4})-(0?[1-9]|1[0-2])-(3[01]|[12]\d|0?[1-9])"
  (parse-dmy (nth (:groups %1) 2) (second (:groups %1)) (first (:groups %1)) true)

  "mm/dd"
  #"(0?[1-9]|1[0-2])/(3[01]|[12]\d|0?[1-9])"
  (parse-dmy (second (:groups %1)) (first (:groups %1)) nil true)

    "dd/mm"
    #"(3[01]|[12]\d|0?[1-9])/(0?[1-9]|1[0-2])"
    (parse-dmy (second (:groups %1)) (first (:groups %1)) nil true)

  ; Part of day (morning, evening...). They are intervals.

  "diminea[tț][aă]" ;; TODO "3am this morning" won't work since morning starts at 4...
  [#"(?i)morning"]
  (assoc (interval (hour 4 false) (hour 12 false) false) :form :part-of-day :latent true)

  "dupamiaza"
  [#"(?i)dupamiaz[aă]|dup[aă] amiaz[aă]"]
  (assoc (interval (hour 12 false) (hour 19 false) false) :form :part-of-day :latent true)

  "sear[aă] noapte"
  [#"(?i)sear[aă]|noapte"]
  (assoc (interval (hour 18 false) (hour 0 false) false) :form :part-of-day :latent true)

  "<part-of-day> asta"
  [{:form :part-of-day} #"(?i)asta"]
  (assoc (intersect (cycle-nth :day 0) %1) :form :part-of-day) ;; removes :latent

  "diseara"
  #"(?i)disear[aă]|([iî]n aceas[aă])? sear[aă]"
  (assoc (intersect (cycle-nth :day 0)
                    (interval (hour 18 false) (hour 0 false) false))
         :form :part-of-day) ; no :latent

  "<time> <part-of-day>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [(dim :time) {:form :part-of-day}]
  (intersect %2 %1)

  ; Other intervals: week-end, seasons

  "week-end" ; from Friday 6pm to Sunday midnight
  #"(?i)(week(\s|-)?end|wkend)"
  (interval (intersect (day-of-week 5) (hour 18 false))
            (intersect (day-of-week 1) (hour 0 false))
            false)

  "sezon anotimp"
  #"(?i)var[aă]" ;could be smarter and take the exact hour into account... also some years the day can change
  (interval (month-day 6 21) (month-day 9 23) false)

  "season"
  #"(?i)toamn[aă]"
  (interval (month-day 9 23) (month-day 12 21) false)

  "season"
  #"(?i)iarn[aă]"
  (interval (month-day 12 21) (month-day 3 20) false)

  "season"
  #"(?i)primavar[aă]"
  (interval (month-day 3 20) (month-day 6 21) false)


  ; Time zones

  "fus orar"
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

  "<time-of-day> approximately" ; 7ish
  [{:form :time-of-day} #"(?i)(-?ish|approximately)"]
  (-> %1
    (dissoc :latent)
    (merge {:precision "approximate"}))

  "<time-of-day> fix" ; sharp
  [{:form :time-of-day} #"(?i)(fix|exact)"]
  (-> %1
    (dissoc :latent)
    (merge {:precision "exact"}))

  "about <time-of-day>" ; about
  [#"(?i)(cam|aproximativ|[iî]n jur de)" {:form :time-of-day}]
  (-> %2
    (dissoc :latent)
    (merge {:precision "approximate"}))

  ; Intervals

  "<month> dd-dd (interval)"
  [{:form :month} #"(3[01]|[12]\d|0?[1-9])" #"\-|to|th?ru|through|(un)?til(l)?" #"(3[01]|[12]\d|0?[1-9])"]
  (interval (intersect %1 (day-of-month (Integer/parseInt (-> %2 :groups first))))
            (intersect %1 (day-of-month (Integer/parseInt (-> %4 :groups first))))
            true)

  ; Blocked for :latent time. May need to accept certain latents only, like hours

  "<datetime> - <datetime> (interval)"
  [(dim :time #(not (:latent %))) #"\-|to|th?ru|through|(un)?til(l)?" (dim :time #(not (:latent %)))]
  (interval %1 %3 true)

  "from <datetime> - <datetime> (interval)"
  [#"(?i)from" (dim :time) #"\-|to|th?ru|through|(un)?til(l)?" (dim :time)]
  (interval %2 %4 true)

  "intre <datetime> si <datetime> (interval)"
  [#"(?i)[iî]nre" (dim :time) #"[sș]i" (dim :time)]
  (interval %2 %4 true)

  ; Specific for time-of-day, to help resolve ambiguities

  "<time-of-day> - <time-of-day> (interval)"
  [#(and (= :time-of-day (:form %)) (not (:latent %))) #"\-|:|to|th?ru|through|(un)?til(l)?" {:form :time-of-day}] ; Prevent set alarm 1 to 5pm
  (interval %1 %3 true)

  "from <time-of-day> - <time-of-day> (interval)"
  [#"(?i)(dup[aă]|[iî]ncep[aâ]nd cu)" {:form :time-of-day} #"(dar |[sș]i )?([iî]nainte|p[aâ]n[aă] la (de)?)" {:form :time-of-day}]
  (interval %2 %4 true)

  "between <time-of-day> and <time-of-day> (interval)"
  [#"(?i)[iî]ntre" {:form :time-of-day} #"[sș]i" {:form :time-of-day}]
  (interval %2 %4 true)

  ; Specific for within duration... Would need to be reworked
  "[iî]n <duration>"
  [#"(?i)[iî]n" (dim :duration)]
  (interval (cycle-nth :second 0) (in-duration (:value %2)) false)

  "by <time>"; if time is interval, take the start of the interval (by tonight = by 6pm)
  [#"(?i)p[aâ]n[aă] (la|[iî]n)" (dim :time)]
  (interval (cycle-nth :second 0) %2 false)

  "by the end of <time>"; in this case take the end of the time (by the end of next week = by the end of next sunday)
  [#"(?i)p[aî]n[aă] ([iî]n|la)" (dim :time)]
  (interval (cycle-nth :second 0) %2 true)

  ; One-sided Intervals

  "until <time-of-day>"
  [#"(?i)p[aî]n[aă] ([iî]n|la)" (dim :time)]
  (merge %2 {:direction :before})

  "after <time-of-day>"
  [#"(?i)dup[aă]" (dim :time)]
  (merge %2 {:direction :after})

  "since <time-of-day>"
  [#"(?i)de|din" (dim :time)]
  (merge  (pred-nth %2 -1) {:direction :after})

  ; ;; In this special case, the upper limit is exclusive
  ; "<hour-of-day> - <hour-of-day> (interval)"
  ; [{:form :time-of-day} #"-|to|th?ru|through|until" #(and (= :time-of-day (:form %))
  ;                                                         (not (:latent %)))]
  ; (interval %1 %3 :exclusive)

  ; "from <hour-of-day> - <hour-of-day> (interval)"
  ; [#"(?i)from" {:form :time-of-day} #"-|to|th?ru|through|until" #(and (= :time-of-day (:form %))
  ;                                                                     (not (:latent %)))]
  ; (interval %2 %4 :exclusive)

  ; "time => time2 (experiment)"
  ; (dim :time)
  ; (assoc %1 :dim :time2)

)
