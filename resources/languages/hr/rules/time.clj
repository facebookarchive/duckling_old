(;; generic

  "intersect"
  [(dim :time #(not (:latent %))) (dim :time #(not (:latent %)))] ; sequence of two tokens with a time dimension
  (intersect %1 %2)

  ; same thing, with "of" in between like "Sunday of last week"
  "intersect by \"of\", \"from\", \"'s\""
  [(dim :time #(not (:latent %))) #"(?i)(u|i|od)" (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)

;  "intersect by \"of\", \"from\", \"'s\""
;  [ #"za" (dim :time #(not (:latent %))) (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
;  (intersect %2 %3)


  ; mostly for January 12, 2005
  ; this is a separate rule, because commas separate very specific tokens
  ; so we want this rule's classifier to learn this
  "intersect by \",\""
  [(dim :time #(not (:latent %))) #"(,)" (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)

  ;  "u <date>" ; on Wed, March 23
  ;  [#"(?i)u" (dim :time)]
  ;  %2 ; does NOT dissoc latent

  "u <named-day>" ; on a sunday
  [#"(?i)u" {:form :day-of-week}]
  %2 ; does NOT dissoc latent

  "u <named-month>" ; in September
  [#"(?i)u" {:form :month}]
  %2 ; does NOT dissoc latent

  ;;;;;;;;;;;;;;;;;;;
  ;; Named things

  "named-day"
  #"(?i)ponedjelja?ka?|pon\.?"
  (day-of-week 1)

  "named-day"
  #"(?i)utora?ka?|uto?\.?"
  (day-of-week 2)

  "named-day"
  #"(?i)srijed(a|e|u)|sri\.?"
  (day-of-week 3)

  "named-day"
  #"(?i)(č|c)etvrta?ka?|(č|c)et\.?"
  (day-of-week 4)

  "named-day"
  #"(?i)peta?ka?|pet\.?"
  (day-of-week 5)

  "named-day"
  #"(?i)subot(a|e|u)|sub?\.?"
  (day-of-week 6)

  "named-day"
  #"(?i)nedjelj(a|e|u)|ned\.?"
  (day-of-week 7)

  "named-month"
  #"(?i)sije(c|č)a?nj(a|u)?|januar(a|u)?|jan\.?|sij?\.?|prv(i|a|o(ga?)?)"
  (month 1)

  "named-month"
  #"(?i)(ve)?lja(c|č)(a|e|i)|februar(a|u)?|feb\.?|ve(lj)?\.?|drug(i|a|o(ga?)?)"
  (month 2)

  "named-month"
  #"(?i)o(z|ž)uja?k(a|u)?|mart(a|u)?|mar\.?|o(z|ž)u?\.?|tre(c|ć)(i|a|e(ga?)?)"
  (month 3)

  "named-month"
  #"(?i)trava?nj(a|u)?|april(a|u)?|apr\.?|tra\.?|(č|c)etvrt(i|a|o(ga?)?)"
  (month 4)

  "named-month"
  #"(?i)sviba?nj(a|u)?|maj|svi\.?|pet(i|a|o(ga?)?)"
  (month 5)

  "named-month"
  #"(?i)lipa?nj(a|u)?|jun(i|u|a)?|jun\.?|lip?\.?|(š|s)est(i|a|o(ga?)?)"
  (month 6)

  "named-month"
  #"(?i)srpa?nj(a|u)?|jul(i|u|a)?|jul\.?|srp\.?|sedm(i|a|o(ga?)?)"
  (month 7)

  "named-month"
  #"(?i)kolovoz(a|u)?|august(a|u)?|aug\.?|kol\.?|osm(i|a|o(ga?)?)"
  (month 8)

  "named-month"
  #"(?i)ruja?n(a|u)?|septemba?r(a|u)?|sept?\.?|ruj\.?|devet(i|a|o(ga?)?)"
  (month 9)

  "named-month"
  #"(?i)listopad(a|u)?|oktobar(a|u)?|okt\.?|lis\.?|deset(i|a|o(ga?)?)"
  (month 10)

  "named-month"
  #"(?i)studen(i|oga?|om)|novemba?r(a|u)?|nov\.?|stu\.?|jedanaest(i|a|o(ga?)?)"
  (month 11)

  "named-month"
  #"(?i)prosina?c(a|u)?|decemba?r(a|u)?|dec\.?|pros?\.?|dvanaest(i|a|o(ga?)?)"
  (month 12)

  ; Holiday TODO: check online holidays
  ; or define dynamic rule (last thursday of october..)

  "christmas"
  #"(?i)(zi(c|ć)bo|bo(z|ž)i(c|ć))(a|u|ni|na)?"
  (month-day 12 25)

  "christmas eve"
  #"(?i)badnjaka?"
  (month-day 12 24)

  "new year's eve"
  #"(?i)star(a|u|e) godin(a|e|u)"
  (month-day 12 31)

  "new year's day"
  #"(?i)nov(a|u|e) godin(a|e|u)"
  (month-day 1 1)

  "valentine's day"
  #"(?i)valentinov(og?|a|)"
  (month-day 2 14)


  "Father's Day";third Sunday of June
  #"(?i)dan (o(c|č)eva|tata)"
  (intersect (day-of-week 7) (month 6) (cycle-nth-after :week 2 (month-day 6 1)))

  "Mother's Day";second Sunday in May.
  #"(?i)maj(c|č)in dan"
  (intersect (day-of-week 7) (month 5) (cycle-nth-after :week 1 (month-day 5 1)))

  "halloween day"
  #"(?i)no(c|ć) vje(s|š)tica"
  (month-day 10 31)

  "absorption of , after named day"
  [{:form :day-of-week} #","]
  %1

  "now"
  #"(?i)((upravo|ov(aj?|og?|e|i))? ?sada?|trenutak|upravo|trenutno)|(ov(aj|og) trena?)"
  (cycle-nth :second 0)

  "today"
  #"(?i)danas?|(dana(s|š)nj(i|eg) dana?) "
  (cycle-nth :day 0)

  "tomorrow"
  #"(?i)(sutra(dan)?)"
  (cycle-nth :day 1)

  "day after tomorrow"
  #"(?i)(preko?sutra)"
  (cycle-nth :day 2)

  "day before yesterday"
  #"(?i)(prekju(c|č)er)"
  (cycle-nth :day -2)

  "day before day before yesterday"
  #"(?i)(prek\s?prekju(c|č)er)"
  (cycle-nth :day -3)

  "yesterday"
  #"(?i)(ju(c|č)er)"
  (cycle-nth :day -1)

  "EOM|End of month"
  #"(?i)(the )?(EOM|(do )? kraja mjeseca)" ; TO BE IMPROVED
  (cycle-nth :month 1)

  "EOY|End of year"
  #"(?i)(the )?(EOY|(do )? kraja? godine)"
  (cycle-nth :year 1)

  ;;
  ;; This, Next, Last

  ;; assumed to be strictly in the future:
  ;; "this Monday" => next week if today is Monday
  "this|next <day-of-week>"
  [#"(?i)ov(aj?|og?|e)|sljede(c|ć)(i|u|a|e(ga?)?)" {:form :day-of-week}]
  (pred-nth-not-immediate %2 0)

  ;; for other preds, it can be immediate:
  ;; "this month" => now is part of it
  ; See also: cycles in en.cycles.clj
  "this <time>"
  [#"(?i)ov(aj?|og?|e)(sad)?" (dim :time)]
  (pred-nth %2 0)

  "next <time>"
  [#"(?i)sljede(c|ć)(i|u|a|e(ga?)?)" (dim :time #(not (:latent %)))]
  (pred-nth-not-immediate %2 0)

  "last <time>"
  [#"(?i)(prethodn(i|u|a|e|o(ga?)?)|pro(s|š)l(ih?|u|a|e|o(ga?)?))" (dim :time)]
  (pred-nth %2 -1)

  "<time> after next"
  [(dim :time) #"(?i)nakon sljede(ć|c)(i|e|a)(ga?)?"]
  (pred-nth-not-immediate %1 1)

  "after next <time>"
  [#"(?i)nakon sljede(ć|c)(i|e|a)(ga?)?" (dim :time)]
  (pred-nth-not-immediate %2 1)

  "<time> before last"
  [(dim :time) #"(?i)prije (prethodn(e|o(ga?)?)|pro(s|š)l(ih?|u|a|e|o(ga?)?))"]
  (pred-nth %1 -2)

  "before last<time>"
  [#"(?i)prije (prethodn(i|u|a|e|o(ga?)?)|pro(s|š)l(ih?|a|e|o(ga?)?))" (dim :time)]
  (pred-nth %2 -2)

  "last <day-of-week> <time>"
  [#"(?i)zadnj(ih?|a|e(ga?)?)" {:form :day-of-week} #"(?i)u" (dim :time)]
  (pred-last-of %2 %4)

  "last <day-of-week> <time>"
  [#"(?i)zadnj(ih?|a|e(ga?)?)" {:form :day-of-week} (dim :time)]
  (pred-last-of %2 %3)

  "last <cycle> of <time>"
  [#"(?i)zadnj(ih?|a|e(ga?)?)" (dim :cycle) #"(?i)u" (dim :time)]
  (cycle-last-of %2 %4)

  ; Ordinals
  "nth <time> of <time>"
  [(dim :ordinal) (dim :time) #"(?i)u" (dim :time)]
  (pred-nth (intersect %4 %2) (dec (:value %1)))

  ;  "nth <time> of <time>"
  ;  [#"(?i)the" (dim :ordinal) (dim :time) #"(?i)u" (dim :time)]
  ;  (pred-nth (intersect %5 %3) (dec (:value %2)))

  "nth <time> after <time>"
  [(dim :ordinal) (dim :time) #"(?i)poslije|nakon|iza" (dim :time)]
  (pred-nth-after %2 %4 (dec (:value %1)))

  ;  "nth <time> after <time>"
  ;  [#"(?i)the" (dim :ordinal) (dim :time) #"(?i)poslije|nakon|iz" (dim :time)]
  ;  (pred-nth-after %3 %5 (dec (:value %2)))

  ; Years
  ; Between 1000 and 2100 we assume it's a year
  ; Outside of this, it's safer to consider it's latent

  "year"
  (integer 1000 2100)
  (year (:value %1))

  "year"
  [(dim :ordinal #(<= 1000 (:value %) 2100)) #"\.|e"]
  (year (:value %2))


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

  "<day-of-month>. (ordinal)" ; this one is not latent
  [(dim :ordinal #(<= 1 (:value %) 31)) #"\.|i|og"]
  (day-of-month (:value %2))

  ;  "<day-of-month> (ordinal)" ; this one is latent
  ;  [(dim :ordinal #(<= 1 (:value %) 31))]
  ;  (assoc (day-of-month (:value %1)) :latent true)

  ;  "<day-of-month> (non ordinal)" ; this one is latent
  ;  [(integer 1 31)]
  ;  (assoc (day-of-month (:value %1)) :latent true)

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

  ;  "the ides of <named-month>" ; the ides of march 13th for most months, but on the 15th for March, May, July, and October
  ;  [#"(?i)the ides? of" {:form :month}]
  ;  (intersect %2 (day-of-month (if (#{3 5 7 10} (:month %2)) 15 13)))

  ;; Hours and minutes (absolute time)

  "time-of-day (latent)"
  (integer 0 23)
  (assoc (hour (:value %1) true) :latent true)

  "at <time-of-day>" ; at four
  [#"(?i)u|@" {:form :time-of-day}]
  (dissoc %2 :latent)

  "<time-of-day> o'clock"
  [{:form :time-of-day} #"(?i)sat(i|a)?"]
  (dissoc %1 :latent)

  "hh:mm"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))[(:.\si]+([0-5]\d)"
  (hour-minute (Integer/parseInt (first (:groups %1)))
               (Integer/parseInt (second (:groups %1)))
               true)

  "hh:mm:ss"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))[:.]([0-5]\d)[:.]([0-5]\d)"
  (hour-minute-second (Integer/parseInt (first (:groups %1)))
                      (Integer/parseInt (second (:groups %1)))
                      (Integer/parseInt (second (next (:groups %1))))
                      true)


  "hhmm (military, prefixed with m to avoid ambiguity with years)"
  #"(?i)m((?:[01]?\d)|(?:2[0-3]))([0-5]\d)"
  (->
    (hour-minute (Integer/parseInt (first (:groups %1)))
                 (Integer/parseInt (second (:groups %1)))
                 false)
    ; not a 12-hour clock)
    (assoc :latent true))

  ;  "hhmm (military) am|pm" ; hh only from 00 to 12
  ;  [#"(?i)((?:1[012]|0?\d))([0-5]\d)" #"(?i)([ap])\.?m?\.?"]
  ; (-> (hour-minute (Integer/parseInt (first (:groups %1)))
  ;                  (Integer/parseInt (second (:groups %1)))
  ;                  false) ; not a 12-hour clock)
  ;     (assoc :latent true))
  ;  (let [[p meridiem] (if (= "a" (-> %2 :groups first clojure.string/lower-case))
  ;                       [[(hour 0) (hour 12) false] :am]
  ;                       [[(hour 12) (hour 0) false] :pm])]
  ;    (-> (intersect
  ;          (hour-minute (Integer/parseInt (first (:groups %1)))
  ;                       (Integer/parseInt (second (:groups %1)))
  ;                   true)
  ;          (apply interval p))
  ;        (assoc :form :time-of-day)))

  ;    "<time-of-day> am|pm"
  ;    [{:form :time-of-day} #"(?i)((|prije|poslije)podne)|((u )|u)?jutro|(u )?no(c|ć)i"]
  ;    ;; TODO set_am fn in helpers => add :ampm field
  ;    (let [[p meridiem] (if (not "poslijepodne" (-> %2 :groups second clojure.string/lower-case))
  ;                         [[(hour 0) (hour 12) false] :am]
  ;                         [[(hour 12) (hour 0) false] :pm])]
  ;      (-> (intersect %1 (apply interval p))
  ;          (assoc :form :time-of-day)))

  "noon"
  #"(?i)(u )?podne(va)?"
  (hour 12 false)

  "midnight|EOD|end of day"
  #"(?i)(u )?pono(c|ć)i?|(the )?(EOD|((do )? kraja? dana))"
  (hour 0 false)

  "quarter (relative minutes)"
  #"(?i)kvarata?|(c|č)etvrt|frtalj"
  {:relative-minutes 15}

  "half (relative minutes)"
  #"(?i)i pol?a?"
  {:relative-minutes 30}

  "number (as relative minutes)"
  (integer 1 59)
  {:relative-minutes (:value %1)}

  "<hour-of-day> <integer> (as relative minutes)"
  [(dim :time :full-hour) #(:relative-minutes %)]
  (hour-relativemin (:full-hour %1) (:relative-minutes %2) true)

  "relative minutes to|till|before <integer> (hour-of-day)"
  [#(:relative-minutes %) #"(?i)do" (dim :time :full-hour)]
  (hour-relativemin (:full-hour %3) (- (:relative-minutes %1)) true)

  "za relative minutes <integer> (hour-of-day)"
  [#"za " #(:relative-minutes %) (dim :time :full-hour)]
  (hour-relativemin (:full-hour %3) (- (:relative-minutes %2)) true)


  "relative minutes after|past <integer> (hour-of-day)"
  [#(:relative-minutes %) #"(?i)poslije|nakon" (dim :time :full-hour)]
  (hour-relativemin (:full-hour %3) (:relative-minutes %1) true)

  "half <integer> (HR style hour-of-day)"
  [#"pol?a?" (dim :time :full-hour)]
  (hour-relativemin (- (:full-hour %2) 1) 30 true)

  ; Formatted dates and times

  ;  "mm/dd/yyyy"
  ;  #"(0?[1-9]|1[0-2])[/-](3[01]|[12]\d|0?[1-9])[-/](\d{2,4})"
  ;  (parse-dmy (second (:groups %1)) (first (:groups %1)) (nth (:groups %1) 2) true)

  "dd/mm/yyyy"
  #"(3[01]|[12]\d|0?[1-9])[\/](0?[1-9]|1[0-2])[\/](\d{2,4})"
  (parse-dmy (first (:groups %1)) (second (:groups %1)) (nth (:groups %1) 2) true)

  "yyyy-mm-dd"
  #"(\d{2,4})-(0?[1-9]|1[0-2])-(3[01]|[12]\d|0?[1-9])"
  (parse-dmy (nth (:groups %1) 2) (second (:groups %1)) (first (:groups %1)) true)

  "dd.mm"
  #"(3[01]|[12]\d|0?[1-9])\/(0?[1-9]|1[0-2])"
  (parse-dmy (first (:groups %1)) (second (:groups %1)) nil true)


  ; Part of day (morning, evening...). They are intervals.

  "morning" ;; TODO "3am this morning" won't work since morning starts at 4...
  [#"(?i)(u )?u?jutros?"]
  (assoc (interval (hour 4 false) (hour 12 false) false) :form :part-of-day :latent true)

  "early morning"
  [#"(?i)(rano( (u )?u?jutros?)?)|(u ran(im|e) jutarnj(im|e) sat(ima|e))"]
  (assoc (interval (hour 3 false) (hour 9 false) false) :form :part-of-day :latent true)

;  intentionally overlaps with night
  "afternoon"
  [#"(?i)po(slije)?podne"]
  (assoc (interval (hour 12 false) (hour 20 false) false) :form :part-of-day :latent true)

  "evening|night"
  [#"(?i)(na)?ve(c|č)er(i|as)?"]
  (assoc (interval (hour 18 false) (hour 0 false) false) :form :part-of-day :latent true)

  "late night"
  [#"(?i)(((u|po)\s)?no(c|ć)(i|as|u)?|u?jutros?)"]
  (assoc (interval (hour 0 false) (hour 4 false) false) :form :part-of-day :latent true)

  "lunch"
  [#"(?i)(((za )|(u vrijeme )) )?ru(c|č)a?k(a|om)?"]
  (assoc (interval (hour 12 false) (hour 14 false) false) :form :part-of-day :latent true)

  "in|during the <part-of-day>" ;; removes latent
  [#"(?i)(u|tokom)" {:form :part-of-day}]
  (dissoc %2 :latent)

  "this <part-of-day>"
  [#"(?i)ov(aj?|og?|i|e)" {:form :part-of-day}]
  (assoc (intersect (cycle-nth :day 0) %2) :form :part-of-day) ;; removes :latent

  "tonight"
  #"(?i)(na)?ve(c|č)er(as)?"
  (assoc
    (intersect (cycle-nth :day 0)
               (interval (hour 18 false) (hour 0 false) false))
    :form :part-of-day)
  ; no :latent

  "after lunch"
  #"(?i)poslije ru(c|č)ka"
  (assoc
    (intersect (cycle-nth :day 0)
               (interval (hour 13 false) (hour 17 false) false))
    :form :part-of-day)
  ; no :latent


  "after work"
  #"(?i)poslije (posla|kraja radnog vremena)"
  (assoc
    (intersect (cycle-nth :day 0)
               (interval (hour 17 false) (hour 21 false) false))
    :form :part-of-day)
  ; no :latent

  "<time> <part-of-day>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [(dim :time) {:form :part-of-day}]
  (intersect %2 %1)

  ;  "<part-of-day> of <time>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  ;  [{:form :part-of-day} #"(?i)od" (dim :time)]
  ;  (intersect %1 %3)


  ; Other intervals: week-end, seasons

  "week-end" ; from Friday 6pm to Sunday midnight
  #"(?i)(za )?vikenda?"
  (interval (intersect (day-of-week 5) (hour 18 false))
            (intersect (day-of-week 1) (hour 0 false))
            false)

  "season"
  #"(?i)ljet(os?|a)" ;could be smarter and take the exact hour into account... also some years the day can change
  (interval (month-day 6 21) (month-day 9 23) false)

  "season"
  #"(?i)jesen(i|as)?"
  (interval (month-day 9 23) (month-day 12 21) false)

  "season"
  #"(?i)zim(a|e|us)?"
  (interval (month-day 12 21) (month-day 3 20) false)

  "season"
  #"(?i)prolje(c|ć)(e|a)"
  (interval (month-day 3 20) (month-day 6 21) false)


  ; Time zones

  "timezone"
  #"(?i)\b(YEKT|YEKST|YAPT|YAKT|YAKST|WT|WST|WITA|WIT|WIB|WGT|WGST|WFT|WEZ|WET|WESZ|WEST|WAT|WAST|VUT|VLAT|VLAST|VET|UZT|UYT|UYST|UTC|ULAT|TVT|TMT|TLT|TKT|TJT|TFT|TAHT|SST|SRT|SGT|SCT|SBT|SAST|SAMT|RET|PYT|PYST|PWT|PT|PST|PONT|PMST|PMDT|PKT|PHT|PHOT|PGT|PETT|PETST|PET|PDT|OMST|OMSST|NZST|NZDT|NUT|NST|NPT|NOVT|NOVST|NFT|NDT|NCT|MYT|MVT|MUT|MST|MSK|MSD|MMT|MHT|MEZ|MESZ|MDT|MAWT|MART|MAGT|MAGST|LINT|LHST|LHDT|KUYT|KST|KRAT|KRAST|KGT|JST|IST|IRST|IRKT|IRKST|IRDT|IOT|IDT|ICT|HOVT|HNY|HNT|HNR|HNP|HNE|HNC|HNA|HLV|HKT|HAY|HAT|HAST|HAR|HAP|HAE|HADT|HAC|HAA|GYT|GST|GMT|GILT|GFT|GET|GAMT|GALT|FNT|FKT|FKST|FJT|FJST|ET|EST|EGT|EGST|EET|EEST|EDT|ECT|EAT|EAST|EASST|DAVT|ChST|CXT|CVT|CST|COT|CLT|CLST|CKT|CHAST|CHADT|CET|CEST|CDT|CCT|CAT|CAST|BTT|BST|BRT|BRST|BOT|BNT|AZT|AZST|AZOT|AZOST|AWST|AWDT|AST|ART|AQTT|ANAT|ANAST|AMT|AMST|ALMT|AKST|AKDT|AFT|AEST|AEDT|ADT|ACST|ACDT)\b"
  {:dim :timezone :value (-> %1 :groups first clojure.string/upper-case)}

  "<time> timezone"
  [(dim :time) (dim :timezone)]
  (set-timezone %1 (:value %2))


  ; Precision
  ; FIXME
  ; - should be applied to all dims not just time-of-day
  ;-  shouldn't remove latency, except maybe -ish

  "<time-of-day> approximately" ; 7ish
  [{:form :time-of-day} #"(?i)(-?ak)"]
  (-> %1
      (dissoc :latent)
      (merge {:precision "approximate"}))

  ;  "<time-of-day> sharp" ; sharp
  ;  [{:form :time-of-day} #"(?i)(to(c|č)no( u)?)"]
  ;  (-> %1
  ;      (dissoc :latent)
  ;      (merge {:precision "exact"}))

  "about <time-of-day>" ; about
  [#"(?i)(oko|cca|otprilike)" {:form :time-of-day}]
  (-> %2
      (dissoc :latent)
      (merge {:precision "approximate"}))

  "exactly <time-of-day>" ; sharp
  [#"(?i)to(c|č)no( u)?" {:form :time-of-day}]
  (-> %2
      (dissoc :latent)
      (merge {:precision "exact"}))


  ; Intervals

  "<month> dd-dd (interval)"
  [{:form :month} #"(3[01]|[12]\d|0?[1-9])" #"\-|do" #"(3[01]|[12]\d|0?[1-9])"]
  (interval (intersect %1 (day-of-month (Integer/parseInt (-> %2 :groups first))))
            (intersect %1 (day-of-month (Integer/parseInt (-> %4 :groups first))))
            true)

  ; Blocked for :latent time. May need to accept certain latents only, like hours

  "<datetime> - <datetime> (interval)"
  [(dim :time #(not (:latent %))) #"\-" (dim :time #(not (:latent %)))]
  (interval %1 %3 true)

  "from <datetime> - <datetime> (interval)"
  [#"(?i)od|izme(dj|đ)u" (dim :time) #"\-" (dim :time)]
  (interval %2 %4 true)

  "between <datetime> and <datetime> (interval)"
  [#"(?i)od|izme(dj|đ)u" (dim :time) #"do|i" (dim :time)]
  (interval %2 %4 true)

  ; Specific for time-of-day, to help resolve ambiguities

  "<time-of-day> - <time-of-day> (interval)"
  [#(and (= :time-of-day (:form %)) (not (:latent %))) #"\-|:" {:form :time-of-day}] ; Prevent set alarm 1 to 5pm
  (interval %1 %3 true)

  "from <time-of-day> - <time-of-day> (interval)"
  [#"(?i)od" {:form :time-of-day} #"\-" {:form :time-of-day}]
  (interval %2 %4 true)

  "between <time-of-day> and <time-of-day> (interval)"
  [#"(?i)od|izme(dj|đ)u" {:form :time-of-day} #"do|i" {:form :time-of-day}]
  (interval %2 %4 true)

  ; Specific for within duration... Would need to be reworked
  "within <duration>"
  [#"(?i)(u|za )vrijeme" (dim :duration)]
  (interval (cycle-nth :second 0) (in-duration (:value %2)) false)

  "by <time>"; if time is interval, take the start of the interval (by tonight = by 6pm)
  [#"(?i)do" (dim :time)]
  (interval (cycle-nth :second 0) %2 false)

  "by the end of <time>"; in this case take the end of the time (by the end of next week = by the end of next sunday)
  [#"(?i)(do )(kraja|isteka)? " (dim :time)]
  (interval (cycle-nth :second 0) %2 true)

  ; One-sided Intervals

  "until <time-of-day>"
  [#"(?i)((nekad|najkasnije) )?(prije|do)" (dim :time)]
  (merge %2 {:direction :before})

  "after <time-of-day>"
  [#"(?i)(nekad |najranije )?(prije|od)" (dim :time)]
  (merge %2 {:direction :after})

  "since <time-of-day>"
  [#"(?i)od" (dim :time)]
  (merge (pred-nth %2 -1) {:direction :after}))

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
; (assoc %1 :dim :time2))
