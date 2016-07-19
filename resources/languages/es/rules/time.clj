(
  ;; generic

  "intersect"
  [(dim :time #(not (:latent %))) (dim :time #(not (:latent %)))] ; sequence of two tokens with a time dimension
  (intersect %1 %2)

  ; same thing, with "de" in between like "domingo de la semana pasada"
  "intersect by `de`"
  [(dim :time #(not (:latent %))) #"(?i)de" (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)

  ; mostly for lunes, 18 de febrero
  ; this is a separate rule, because commas separate very specific tokens
  ; so we want this rule's classifier to learn this
  "two time tokens separated by \",\""
  [(dim :time #(not (:latent %))) #"," (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)

  ;;

  "named-day"
  #"(?i)lunes|lun?\.?"
  (day-of-week 1)

  "named-day"
  #"(?i)martes|mar?\.?"
  (day-of-week 2)

  "named-day"
  #"(?i)mi(e|é)\.?(rcoles)?|mx|mier?\."
  (day-of-week 3)

  "named-day"
  #"(?i)jueves|jue|jue\."
  (day-of-week 4)

  "named-day"
  #"(?i)viernes|vie|vie\."
  (day-of-week 5)

  "named-day"
  #"(?i)s[áa]bado|s(á|a)b\.?"
  (day-of-week 6)

  "named-day"
  #"(?i)domingo|dom\.?"
  (day-of-week 7)

  "named-month"
  #"(?i)enero|ene\.?"
  (month 1)

  "named-month"
  #"(?i)febrero|feb\.?"
  (month 2)

  "named-month"
  #"(?i)marzo|mar\.?"
  (month 3)

  "named-month"
  #"(?i)abril|abr\.?"
  (month 4)

  "named-month"
  #"(?i)mayo?\.?"
  (month 5)

  "named-month"
  #"(?i)junio|jun\.?"
  (month 6)

  "named-month"
  #"(?i)julio|jul\.?"
  (month 7)

  "named-month"
  #"(?i)agosto|ago\.?"
  (month 8)

  "named-month"
  #"(?i)septiembre|sept?\.?"
  (month 9)

  "named-month"
  #"(?i)octubre|oct\.?"
  (month 10)

  "named-month"
  #"(?i)noviembre|nov\.?"
  (month 11)

  "named-month"
  #"(?i)diciembre|dic\.?"
  (month 12)

; Holiday TODO: check online holidays
  "Navidad"
  #"(?i)(la )?navidad"
  (month-day 12 25)

  "Nochevieja"
  #"(?i)nochevieja"
  (month-day 12 31)

  "ano nuevo"
  #"(?i)a[nñ]o nuevo"
  (month-day 1 1)


  "right now"
  #"ahor(it)?a|ya|en\s?seguida|cuanto antes"
  (cycle-nth :second 0)

  "now"
  #"(?i)(hoy)|(en este momento)"
  (cycle-nth :day 0)

  "tomorrow"
  #"(?i)ma(n|ñ)ana"
  (cycle-nth :day 1)

  "yesterday"
  #"(?i)ayer"
  (cycle-nth :day -1)

  "the day after tomorrow"
  #"(?i)pasado\s?ma(n|ñ)ana"
  (cycle-nth :day 2)

  "the day before yesterday"
  #"(?i)anteayer|antes de (ayer|anoche)|antier"
  (cycle-nth :day -2)

  ;;
  ;; This, Next, Last

  "this <day-of-week>" ; assumed to be in the future
  [#"(?i)este" {:form :named-day}]
  (pred-nth-not-immediate %2 0)

  ;; for other preds, it can be immediate:
  ;; "ce mois" => now is part of it
  ; See also: cycles in en.cycles.clj
  "ce <time>"
  [#"(?i)este" (dim :time)]
  (pred-nth %2 0)

  "<named-month|named-day> next" ; próxim(0|a)
  [(dim :time) #"(?i)que vienen?"]
  (pred-nth %1 1)

  "<named-month|named-day> past"
  [(dim :time) #"(?i)pasad(o|a)"]
  (pred-nth %1 -1)

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

  "del <year>"  ; latin america mostly
  [#"(?i)del( a[ñn]o)?" (integer 1000 2100)]
  (year (:value %2))

  ; Day of month appears in the following context:
  ; - le premier
  ; - le 5
  ; - 5 March
  ; - mm/dd (and other numerical formats like yyyy-mm-dd etc.)
  ; We remove the rule with just (integer 1 31) as it was too messy

  "day of month (1st)"
  [#"(?i)primero|uno|prem\.?|1o"] ; |1º if possible later
  (day-of-month 1)

  "el <day-of-month> (non ordinal)" ; this one is latent
  [#"(?i)el" (integer 1 31)]
  (assoc (day-of-month (:value %2)) :latent true)


  "<day-of-month> de <named-month>" ; 4 de julio
  [(integer 1 31) #"(?i)de" {:form :month}]
  (intersect %3 (day-of-month (:value %1)))

  "el <day-of-month> de <named-month>" ; el 4 de julio
  [#"(?i)el" (integer 1 31) #"(?i)de" {:form :month}]
  (intersect %4 (day-of-month (:value %2)))

  "ultimo <day-of-week> de <time>"
  [#"(?i)[ú|u]ltimo" {:form :day-of-week} #"(?i)de|en" (dim :time)]
  (pred-last-of %2 %4)

  "nth <time> de <time>"
  [(dim :ordinal) (dim :time) #"(?i)de|en" (dim :time)]
  (pred-nth (intersect %4 %2) (dec (:value %1)))

  "nth <time> de <time>"
  [#"(?i)the" (dim :ordinal) (dim :time) #"(?i)de|en" (dim :time)]
  (pred-nth (intersect %5 %3) (dec (:value %2)))

  "<named-month> <day-of-month>" ; mayo 5 in Latin America mostly (this rule removes latency)
  [{:form :month} (integer 1 31)]
  (intersect %1 (day-of-month (:value %2)))

  "<day-of-week> <day-of-month>" ; vendredi 13
  [{:form :day-of-week} (integer 1 31)]
  (intersect %1 (day-of-month (:value %2)))

  ;; hours and minutes (absolute time)
  "time-of-day (latent)"
  (integer 0 23)
  (assoc (hour (:value %1) true) :latent true)

  "noon"
  #"(?i)mediod(í|i)a"
  (hour 12 false)

  "midnight"
  #"(?i)medianoche"
  (hour 0 false)

  "<time-of-day> horas"
  [#(:full-hour %) #"(?i)h\.?(ora)?s?"]
  (dissoc %1 :latent)

  "a las <time-of-day>" ;
  [#"(?i)(al?)( las?)?|las?" {:form :time-of-day}]
  (dissoc %2 :latent)

  "a las <hour-min>(time-of-day)" ; a las 12:00 horas
  [#"(?i)((al?)( las?)?|las?)" {:form :time-of-day} #"horas?"]
  %2

  "hh(:|.|h)mm (time-of-day)"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))[:h\.]([0-5]\d)"
  (hour-minute (Integer/parseInt (first (:groups %1)))
               (Integer/parseInt (second (:groups %1)))
               true)

  ; "hhmm (military time-of-day)" ;; not sure if used a lot and in conflict with year 1954
  ; #"(?i)((?:[01]?\d)|(?:2[0-3]))([0-5]\d)"
  ; (-> (hour-minute (Integer/parseInt (first (:groups %1)))
  ;               (Integer/parseInt (second (:groups %1)))
  ;               false) ; not a 12-hour clock
  ;     (assoc :latent true))

  "<time-of-day> am|pm"
  [{:form :time-of-day} #"(?i)([ap])\.?m?\.?"]
  ;; TODO set_am fn in helpers => add :ampm field
  (let [[p meridiem] (if (= "a" (-> %2 :groups first clojure.string/lower-case))
                       [[(hour 0) (hour 11) true] :am]
                       [[(hour 12) (hour 23) true] :pm])]
    (-> (intersect %1 (apply interval p))
        (assoc :form :time-of-day)))

  "quarter (relative minutes)"
  #"(?i)cuarto"
  {:relative-minutes 15}

  "3 quarter (relative minutes)"
  #"(?i)(3|tres) cuartos?"
  {:relative-minutes 45}

  "half (relative minutes)"
  #"y media"
  {:relative-minutes 30}

  "number (as relative minutes)"
  (integer 1 59)
  {:relative-minutes (:value %1)}

  "<integer> minutes (as relative minutes)"
  [(integer 1 59) #"(?i)min\.?(uto)?s?"]
  {:relative-minutes (:value %1)}

  "<hour-of-day> <integer> (as relative minutes)"
  [(dim :time :full-hour) #(:relative-minutes %)] ;before  [{:for-relative-minutes true} #(:relative-minutes %)]
  (hour-relativemin (:full-hour %1) (:relative-minutes %2) (:twelve-hour-clock? %1))


  "<hour-of-day> minus <integer> (as relative minutes)"
  [(dim :time :full-hour) #"menos\s?" #(:relative-minutes %)]
  (hour-relativemin (:full-hour %1) (- (:relative-minutes %3)) (:twelve-hour-clock? %1))

  "<hour-of-day> and <relative minutes>"
  [(dim :time :full-hour) #"y" #(:relative-minutes %)]
  (hour-relativemin (:full-hour %1) (:relative-minutes %3) (:twelve-hour-clock? %1))

  ;; Formatted dates and times

  "dd[/-.]mm[/-.]yyyy"
  #"(3[01]|[12]\d|0?[1-9])[\./-](0?[1-9]|1[0-2])[\./-](\d{2,4})"
  (parse-dmy (first (:groups %1)) (second (:groups %1)) (nth (:groups %1) 2) true)

  "yyyy-mm-dd"
  #"(\d{2,4})-(0?[1-9]|1[0-2])-(3[01]|[12]\d|0?[1-9])"
  (parse-dmy (nth (:groups %1) 2) (second (:groups %1)) (first (:groups %1)) true)

  "dd[/-]mm"
  #"(3[01]|[12]\d|0?[1-9])[/-](0?[1-9]|1[0-2])"
  (parse-dmy (first (:groups %1)) (second (:groups %1)) nil true)

  ; Part of day (morning, evening...). They are intervals.

  "morning"
  #"(?i)ma(ñ|n)ana"
  (assoc (interval (hour 4 false) (hour 12 false) false) :form :part-of-day :latent true)

  "afternoon"
  #"(?i)tarde"
  (assoc (interval (hour 12 false) (hour 19 false) false) :form :part-of-day :latent true)

  "del mediodía" ;; 12 y media del mediodía should be parsed to 12:30 pm.
  #"(?i)del mediod[ií]a"
  (assoc (interval (hour 12 false) (hour 17 false) false) :form :part-of-day :latent true)

  "evening"
  #"(?i)noche"
  (assoc (interval (hour 18 false) (hour 0 false) false) :form :part-of-day :latent true)

  "in the <part-of-day>" ;; removes latent
  [#"(?i)(a|en|de|por) la" {:form :part-of-day}]
  (dissoc %2 :latent)

  "this <part-of-day>"
  [#"(?i)est(e|a)" {:form :part-of-day}]
  (assoc (intersect (cycle-nth :day 0) %2) :form :part-of-day) ;; removes :latent

; ;specific rule to address "3 in the morning","3h du matin" and extend morning span from 0 to 12
;   "<dim time> du matin"
;   [{:form :time-of-day} #"du mat(in)?"]
;   (intersect %1 (assoc (interval (hour 0 false) (hour 12 false) false) :form :part-of-day :latent true))

  "<time-of-day> <part-of-day>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [(dim :time) {:form :part-of-day}]
  (intersect %1 %2)

  ;specific rule to address the ambiguity of noche/tarde and extend tarde span from 12 to 21
  "<dim time> de la tarde"
  [{:form :time-of-day} #"(a|en|de) la tarde"]
  (intersect %1 (assoc (interval (hour 12 false) (hour 21 false) false) :form :part-of-day :latent true))

  ;specific rule to address the ambiguity of noche/tarde and extend tarde span from 12 to 21
  "<dim time> de la manana"
  [{:form :time-of-day} #"(a|en|de) la ma(ñ|n)ana"]
  (intersect %1 (assoc (interval (hour 0 false) (hour 12 false) false) :form :part-of-day :latent true))

  "<integer> in the <part-of-day>" ; 7 de la manana always means a las 7 de la manana
  [{:form :part-of-day} #"(a|en|de|por) la" (dim :time)]
  (intersect %3 %1)

  ; Other intervals: week-end, seasons

  "week-end"
  #"(?i)week[ -]?end|fin de semana"
  (interval (intersect (day-of-week 5) (hour 18 false))
            (intersect (day-of-week 1) (hour 0 false))
            false)

  "season"
  #"(?i)verano" ;could be smarter and take the exact hour into account... also some years the day can change
  (interval (month-day 6 21) (month-day 9 23) false)

  "season"
  #"(?i)oto[ñn]o"
  (interval (month-day 9 23) (month-day 12 21) false)

  "season"
  #"(?i)invierno"
  (interval (month-day 12 21) (month-day 3 20) false)

  "season"
  #"(?i)primavera"
  (interval (month-day 3 20) (month-day 6 21) false)

  ; a specific version of "el", above, removes :latent for integer as day of month
  ; this one is more general but does not remove latency
  "el <time>"
  [#"(?i)d?el" (dim :time #(not (:latent %)))]
  %2

  ;; Time zones

  "timezone"
  #"(?i)(YEKT|YEKST|YAPT|YAKT|YAKST|WT|WST|WITA|WIT|WIB|WGT|WGST|WFT|WEZ|WET|WESZ|WEST|WAT|WAST|VUT|VLAT|VLAST|VET|UZT|UYT|UYST|UTC|ULAT|TVT|TMT|TLT|TKT|TJT|TFT|TAHT|SST|SRT|SGT|SCT|SBT|SAST|SAMT|RET|PYT|PYST|PWT|PT|PST|PONT|PMST|PMDT|PKT|PHT|PHOT|PGT|PETT|PETST|PET|PDT|OMST|OMSST|NZST|NZDT|NUT|NST|NPT|NOVT|NOVST|NFT|NDT|NCT|MYT|MVT|MUT|MST|MSK|MSD|MMT|MHT|MEZ|MESZ|MDT|MAWT|MART|MAGT|MAGST|LINT|LHST|LHDT|KUYT|KST|KRAT|KRAST|KGT|JST|IST|IRST|IRKT|IRKST|IRDT|IOT|IDT|ICT|HOVT|HNY|HNT|HNR|HNP|HNE|HNC|HNA|HLV|HKT|HAY|HAT|HAST|HAR|HAP|HAE|HADT|HAC|HAA|GYT|GST|GMT|GILT|GFT|GET|GAMT|GALT|FNT|FKT|FKST|FJT|FJST|ET|EST|EGT|EGST|EET|EEST|EDT|ECT|EAT|EAST|EASST|DAVT|ChST|CXT|CVT|CST|COT|CLT|CLST|CKT|CHAST|CHADT|CET|CEST|CDT|CCT|CAT|CAST|BTT|BST|BRT|BRST|BOT|BNT|AZT|AZST|AZOT|AZOST|AWST|AWDT|AST|ART|AQTT|ANAT|ANAST|AMT|AMST|ALMT|AKST|AKDT|AFT|AEST|AEDT|ADT|ACST|ACDT)"
  {:dim :timezone
   :value (-> %1 :groups first clojure.string/upper-case)}

  "<time> timezone"
  [(dim :time) (dim :timezone)]
  (assoc %1 :timezone (:value %2))


  ; "<integer> and <relative minutes>" ;5 y cuarto
  ; [(integer 0 23) #"y" #(:relative-minutes %)]
  ; (hour-relativemin
  ;   (:value %1)
  ;   true
  ;   (:relative-minutes %3))

  ; Intervals
  "dd-dd <month>(interval)"
  [#"(3[01]|[12]\d|0?[1-9])" #"(?i)\-|al?" #"(3[01]|[12]\d|0?[1-9])" #"(?i)de" {:form :month}]
  (interval (intersect %5 (day-of-month (Integer/parseInt (-> %1 :groups first))))
            (intersect %5 (day-of-month (Integer/parseInt (-> %3 :groups first))))
            true)

  "entre dd et dd <month>(interval)"
  [#"entre( el)?" #"(0?[1-9]|[12]\d|3[01])" #"y( el)?" #"(0?[1-9]|[12]\d|3[01])" #"(?i)de" {:form :month}]
  (interval (intersect %6 (day-of-month (Integer/parseInt (-> %2 :groups first))))
            (intersect %6 (day-of-month (Integer/parseInt (-> %4 :groups first))))
            true)

  ; Blocked for :latent time. May need to accept certain latents only, like hours

  "<datetime> - <datetime> (interval)"
  [(dim :time #(not (:latent %))) #"\-|al?" (dim :time #(not (:latent %)))]
  (interval %1 %3 false)

  "de <datetime> - <datetime> (interval)"
  [#"(?i)del?" (dim :time) #"\-|al?" (dim :time)]
  (interval %2 %4 false)

  "entre <datetime> et <datetime> (interval)"
  [#"(?i)entre" (dim :time) #"y" (dim :time)]
  (interval %2 %4 false)

  ; Specific for within duration... Would need to be reworked
  "dentro de <duration>"
  [#"(?i)dentro de" (dim :duration)]
  (interval (cycle-nth :second 0) (in-duration (:value %2)) false)

)
