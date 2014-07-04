(
  ;; generic
  
  "two time tokens in a row"
  [(dim :time #(not (:latent %))) (dim :time #(not (:latent %)))] ; sequence of two tokens with a time dimension
  (intersect %1 %2)

  ; same thing, with "de" in between like "domingo de la semana pasada"
  "two time tokens separated by `de`"
  [(dim :time #(not (:latent %))) #"(?i)de" (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)
  
  ;;

  "named-day"
  #"(?i)lunes|lun?|lun\."
  (assoc (day-of-week 1) :not-immediate true :form :named-day)

  "named-day"
  #"(?i)martes|ma|mar\."
  (assoc (day-of-week 2) :not-immediate true :form :named-day)

  "named-day"
  #"(?i)mi(e|é)(rcoles)?|mx|mier?\."
  (assoc (day-of-week 3) :not-immediate true :form :named-day)

  "named-day"
  #"(?i)jueves|jue|jue\."
  (assoc (day-of-week 4) :not-immediate true :form :named-day)

  "named-day"
  #"(?i)viernes|vie|vie\."
  (assoc (day-of-week 5) :not-immediate true :form :named-day)

  "named-day"
  #"(?i)sábado|s(á|a)b\.?"
  (assoc (day-of-week 6) :not-immediate true :form :named-day)

  "named-day"
  #"(?i)domingo|dom\.?"
  (assoc (day-of-week 7) :not-immediate true :form :named-day)

  "named-month"
  #"(?i)enero|ene\.?"
  (assoc (month-of-year 1) :form :named-month)

  "named-month"
  #"(?i)febrero|feb\.?"
  (assoc (month-of-year 2) :form :named-month)

  "named-month"
  #"(?i)marzo|mar\.?"
  (assoc (month-of-year 3) :form :named-month)

  "named-month"
  #"(?i)abril|abr\.?"
  (assoc (month-of-year 4) :form :named-month)

  "named-month"
  #"(?i)mayo?\.?"
  (assoc (month-of-year 5) :form :named-month)

  "named-month"
  #"(?i)junio|jun\.?"
  (assoc (month-of-year 6) :form :named-month)

  "named-month"
  #"(?i)julio|jul\.?"
  (assoc (month-of-year 7) :form :named-month)

  "named-month"
  #"(?i)agosto|ago\.?"
  (assoc (month-of-year 8) :form :named-month)

  "named-month"
  #"(?i)septiembre|sept?\.?"
  (assoc (month-of-year 9) :form :named-month)

  "named-month"
  #"(?i)octubre|oct\.?"
  (assoc (month-of-year 10) :form :named-month)

  "named-month"
  #"(?i)noviembre|nov\.?"
  (assoc (month-of-year 11) :form :named-month)

  "named-month"
  #"(?i)diciembre|dic\.?"
  (assoc (month-of-year 12) :form :named-month)

  "right now"
  #"ahor(it)?a|ya|en\s?seguida|cuanto antes"
  (this-cycle seconds 0)
  
  "now"
  #"(?i)(hoy)|(en este momento)"
  (this-cycle days 0)

  "tomorrow"
  #"(?i)ma(n|ñ)ana"
  (this-cycle days 1)

  "yesterday"
  #"(?i)ayer"
  (this-cycle days -1)

  "the day after tomorrow"
  #"(?i)pasado\s?ma(n|ñ)ana"
  (this-cycle days 2)

  "the day before yesterday"
  #"(?i)anteayer|antes de anoche|antier"
  (this-cycle days -2)

  "week-end"
  #"(?i)week[ -]?end|fin de semana"
  (assoc (between-days-of-weeks-hours 5 18 1 0) :form :named-day)
  
  "this <day-of-week>" ; assumed to be in the future
  [#"(?i)este" {:form :named-day}]
  (this-pred %2 1)

  "<named-month|named-day> next" ; próxim(0|a)
  [(dim :time) #"(?i)que vienen?"]
  (this-pred %1 1)

  "<named-month|named-day> past"
  [(dim :time) #"(?i)pasad(o|a)"]
  (this-pred %1 -1)

  ;;
  
  "day of month (numeric)"
  [(integer 1 31)]
  (assoc (day-of-month (:val %1)) :latent true :form :day-of-month)
  
  "day of month (1st)"
  [#"(?i)primero|uno|prem\.?|1o"] ; |1º if possible later
  (assoc (day-of-month 1) :latent true :form :day-of-month)

  "the <day-of-month>" ; this one is not latent
  [#"(?i)el" {:form :day-of-month}]
  (dissoc %2 :latent)

  "month (numeric)"
  (integer 1 12)
  (assoc (month-of-year (:val %1)) :latent true)

  "year (four digit)"
  (integer 1000 9999)
  (year (:val %1))
  
  "<day-of-month> of <named-month>" ; 4 de julio (this rule removes latency)
  [{:form :day-of-month} #"(?i)de" {:form :named-month}]
  (intersect %1 %3)

  "<named-month> <day-of-month>" ; mayo 5 in Latin America mostly (this rule removes latency)
  [{:form :named-month} {:form :day-of-month}]
  (intersect %2 %1)

  "morning"
  #"(?i)ma(ñ|n)ana"
  (assoc (between-hours 4 12) :form :part-of-day :latent true
  	:extended (between-hours 0 12))

  "afternoon"
  #"(?i)tarde"
  (assoc (between-hours 12 19) :form :part-of-day :latent true
  	:extended (between-hours 12 0))

  "evening"
  #"(?i)noche"
  (assoc (between-hours 18 0) :form :part-of-day :latent true
  	:extended (between-hours 12 0))

  "in the <part-of-day>" ;; removes latent
  [#"(?i)(de|por) la" {:form :part-of-day}]
  (dissoc %2 :latent)
  
  "this <part-of-day>"
  [#"(?i)est(e|a)" {:form :part-of-day}]
  (intersect (this-cycle days 0) %2) ;; removes :latent
  
  "<dim time> <part-of-day>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [(dim :time) {:form :part-of-day}]
  (intersect %1 %2)

  "<time-of-day> <part-of-day>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [{:form :time-of-day} {:form :part-of-day}]
  (intersect %1 (:extended %2))

  "<integer> hours (time-of-day)"
  [(integer 0 23) #"(?i)h\.?(ora)?s?"]
  (assoc (hour (:val %1) true)
    :form :time-of-day
    :for-relative-minutes true
    :val (:val %1))
  
  "at <time-of-day>" ; absorption
  [#"(?i)al?( las?)?" {:form :time-of-day}]
  %2

  "a las <integer>(time-of-day)" ; a las tres
  [#"(?i)(al?( las?)?|las?)" (integer 0 23)]
  (assoc (hour (:val %2) true)
    :form :time-of-day
    :for-relative-minutes true
    :val (:val %2))

  "a las <hour-min>(time-of-day)" ; a las 12:00 horas
  [#"(?i)(al?( las?)?|las?)" {:form :time-of-day} #"horas"]
  %2

  "hh(:|.|h)mm (time-of-day)"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))[:h\.]([0-5]\d)"
  (hour-min (read-string (first (:groups %1)))
            true
            (read-string (second (:groups %1))))
  
  "hhmm (military time-of-day)"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))([0-5]\d)"
  (-> (hour-min (read-string (first (:groups %1)))
                true
                (read-string (second (:groups %1))))
      (assoc :latent true))
  
  "<time-of-day> am|pm"
  [{:form :time-of-day} #"(?i)([ap])\.?m?\.?"]
  (-> (intersect %1 (apply between-hours
                       (if (= "a" (-> %2 :groups first .toLowerCase))
                         [0 12]
                         [12 0])))
      (assoc :form :time-of-day))
    
  "noon"
  #"(?i)mediod(í|i)a"
  (-> (hour 12 false)
      (assoc :form :time-of-day
             :for-relative-minutes true :val 12))

  "midnight"
  #"(?i)medianoche"
  (-> (hour 0 false)
      (assoc :form :time-of-day
             :for-relative-minutes true :val 0))

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
  {:relative-minutes (:val %1)}
  
  "<integer> minutes (as relative minutes)"
  [(integer 1 59) #"(?i)min\.?(uto)?s?"]
  {:relative-minutes (:val %1)}

  "<hour-of-day> <integer> (as relative minutes)"
  [{:for-relative-minutes true} #(:relative-minutes %)]
  (hour-relativemin 
    (:val %1) 
    (:ambiguous-am-pm %1)
    (:relative-minutes %2))

  "<hour-of-day> minus <integer> (as relative minutes)"
  [{:for-relative-minutes true} #"menos\s?" #(:relative-minutes %)]
  (hour-relativemin 
    (:val %1)
    (:ambiguous-am-pm %1)
    (- (:relative-minutes %3)))
  
  "<hour-of-day> and <relative minutes>"
  [{:for-relative-minutes true} #"y" #(:relative-minutes %)]
  (hour-relativemin 
    (:val %1)
    (:ambiguous-am-pm %1)
    (:relative-minutes %3))

  "<integer> and <relative minutes>" ;5 y cuarto
  [(integer 0 23) #"y" #(:relative-minutes %)]
  (hour-relativemin 
    (:val %1)
    true
    (:relative-minutes %3))

  "<integer> in the <part-of-day>" ; 7 de la manana always means a las 7 de la manana
  [(integer 0 23) #"(?i)(de|por) la" {:form :part-of-day}]
  (intersect (assoc (hour (:val %1) true)
    :form :time-of-day
    :for-relative-minutes true
    :val (:val %1)) (:extended %3))

  ;; hours and minutes (absolute time);

  ;; formatted

  "dd[/-.]mm[/-.]yyyy"
  #"([012]?\d|30|31)[\./-](0?\d|10|11|12)[\./-](\d{2,4})"
  (parse-dmy (first (:groups %1)) (second (:groups %1)) (nth (:groups %1) 2) true)

  "yyyy-mm-dd"
  #"(\d{2,4})-(0?\d|10|11|12)-([012]?\d|30|31)"
  (parse-dmy (nth (:groups %1) 2) (second (:groups %1)) (first (:groups %1)) true)
  
  "dd[/-]mm"
  #"([012]?\d|30|31)[/-](0?\d|10|11|12)"
  (parse-dmy (first (:groups %1)) (second (:groups %1)) nil true)
  
  ;; Time zones
  
  "timezone"
  #"(?i)(YEKT|YEKST|YAPT|YAKT|YAKST|WT|WST|WITA|WIT|WIB|WGT|WGST|WFT|WEZ|WET|WESZ|WEST|WAT|WAST|VUT|VLAT|VLAST|VET|UZT|UYT|UYST|UTC|ULAT|TVT|TMT|TLT|TKT|TJT|TFT|TAHT|SST|SRT|SGT|SCT|SBT|SAST|SAMT|RET|PYT|PYST|PWT|PT|PST|PONT|PMST|PMDT|PKT|PHT|PHOT|PGT|PETT|PETST|PET|PDT|OMST|OMSST|NZST|NZDT|NUT|NST|NPT|NOVT|NOVST|NFT|NDT|NCT|MYT|MVT|MUT|MST|MSK|MSD|MMT|MHT|MEZ|MESZ|MDT|MAWT|MART|MAGT|MAGST|LINT|LHST|LHDT|KUYT|KST|KRAT|KRAST|KGT|JST|IST|IRST|IRKT|IRKST|IRDT|IOT|IDT|ICT|HOVT|HNY|HNT|HNR|HNP|HNE|HNC|HNA|HLV|HKT|HAY|HAT|HAST|HAR|HAP|HAE|HADT|HAC|HAA|GYT|GST|GMT|GILT|GFT|GET|GAMT|GALT|FNT|FKT|FKST|FJT|FJST|ET|EST|EGT|EGST|EET|EEST|EDT|ECT|EAT|EAST|EASST|DAVT|ChST|CXT|CVT|CST|COT|CLT|CLST|CKT|CHAST|CHADT|CET|CEST|CDT|CCT|CAT|CAST|BTT|BST|BRT|BRST|BOT|BNT|AZT|AZST|AZOT|AZOST|AWST|AWDT|AST|ART|AQTT|ANAT|ANAST|AMT|AMST|ALMT|AKST|AKDT|AFT|AEST|AEDT|ADT|ACST|ACDT)"
  {:dim :timezone
   :value (-> %1 :groups first .toUpperCase)}
  
  "<time> timezone"
  [(dim :time) (dim :timezone)]
  (assoc %1 :timezone (:value %2))

)
