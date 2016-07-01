(
  ;; generic
  
  "two time tokens in a row"
  [(dim :time #(not (:latent %))) (dim :time #(not (:latent %)))] ; sequence of two tokens with a time dimension
  (intersect %1 %2)

  ; same thing, with "de" in between like "domingo de la semana pasada"
  "two time tokens separated by `di`"
  [(dim :time #(not (:latent %))) #"(?i)de" (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)
  
  ;;

  "named-day"
  #"(?i)luned[ìi]|lun?\."
  (day-of-week 1)

  "named-day"
  #"(?i)marted[iì]|mar?\.?"
  (day-of-week 2)

  "named-day"
  #"(?i)mercoled[iì]|mer?\.?"
  (day-of-week 3)

  "named-day"
  #"(?i)gioved[iì]|gio\.?"
  (day-of-week 4)

  "named-day"
  #"(?i)venerd[iì]|ven\.?"
  (day-of-week 5)

  "named-day"
  #"(?i)sabato|sab\.?"
  (day-of-week 6)

  "named-day"
  #"(?i)domenica|dom\.?"
  (day-of-week 7)

  "named-month"
  #"(?i)gennaio|gene?\.?"
  (month 1)

  "named-month"
  #"(?i)febbraio|feb\.?"
  (month 2)

  "named-month"
  #"(?i)marzo|mar\.?"
  (month 3)

  "named-month"
  #"(?i)aprile|apr\.?"
  (month 4)

  "named-month"
  #"(?i)maggio|mag\.?"
  (month 5)

  "named-month"
  #"(?i)giugno|giu\.?"
  (month 6)

  "named-month"
  #"(?i)luglio|lug\.?"
  (month 7)

  "named-month"
  #"(?i)agosto|ago\.?"
  (month 8)

  "named-month"
  #"(?i)settembre|sett?\.?"
  (month 9)

  "named-month"
  #"(?i)ottobre|ott\.?"
  (month 10)

  "named-month"
  #"(?i)novembre|nov\.?"
  (month 11)

  "named-month"
  #"(?i)dicembre|dic\.?"
  (month 12)

  ; Holiday TODO: check online holidays
  ; or define dynamtc rule (last thursday of october..)
  

  "right now"
  #"(subito|immediatamente|proprio adesso|in questo momento)"
  (cycle-nth :second 0)
  
  "now"
  #"(?i)(ora|al momento|attualmented|adesso|(di )?oggi)"
  (cycle-nth :day 0)

  "tomorrow"
  #"(?i)domani"
  (cycle-nth :day 1)

  "yesterday"
  #"(?i)ieri"
  (cycle-nth :day -1)

  "the day after tomorrow"
  #"(?i)(il giorno )?dopo\s?domani"
  (cycle-nth :day 2)

  "the day before yesterday"
  #"(?i)(l')?altro\s?ieri"
  (cycle-nth :day -2)

  ;;
  ;; This, Next, Last
  
  ;; assumed to be strictly in the future: 
  ;; "this Monday" => next week if today in Monday
  
  "this <day-of-week>" ; assumed to be in the future
  [#"(?i)quest[oaie]" {:form :day-of-week}]
  (pred-nth-not-immediate %2 0)

  ;; for other preds, it can be immediate:
  ;; "this month" => now is part of it
  ; See also: cycles in en.cycles.clj
  "this <time>"
  [#"(?i)quest[oaie]" (dim :time)]
  (pred-nth %2 0)

  "next <time>"
  [#"(?i)prossim[ao]" (dim :time)]
  (pred-nth %2 1)

  "next <time>"
  [(dim :time) #"(?i)dopo|prossim[ao]"]
  (pred-nth %1 1)

  "<time> last"
  [(dim :time) #"(?i)(ultim|scors)[oaie]"]
  (pred-nth %1 -1)

  "last <time> "
  [#"(?i)(ultim|scors)[oaie]" (dim :time)]
  (pred-nth %2 -1)


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

  "day of month (1st)"
  [#"(?i)(prim[oa]|1o|1º)( di)?"];
  (day-of-month 1)

  "the <day-of-month>" ; this one is not latent
  [#"(?i)il" (integer 1 31)]
  (assoc (day-of-month (:value %2)) :latent true)
  
  "<day-of-month> <named-month>" ;  dodici luglio 2010 (this rule removes latency)
  [(integer 1 31) {:form :month}]
  (intersect %2 (day-of-month (:value %1)))

  "il <day-of-month> de <named-month>" ; il dodici luglio 2010 
  [#"(?i)il" (integer 1 31) {:form :month}]
  (intersect %3 (day-of-month (:value %2)))

  ;; hours and minutes (absolute time)
  "<integer> (latent time-of-day)"
  (integer 0 23)
  (assoc (hour (:value %1) true) :latent true)
  
  "noon"
  #"(?i)mezz?ogiorno"
  (hour 12 false)

  "midnight"
  #"(?i)mez?zanott?e"
  (hour 0 false)

  "<time-of-day> ora"
  [#(:full-hour %) #"(?i)or[ea]"]
  (dissoc %1 :latent) 
  
  "at <time-of-day>" ; alle due
  [#"(?i)all[e']|le|a" {:form :time-of-day}]
  (dissoc %2 :latent)


  "hh(:|.|h)mm (time-of-day)"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))[:h]([0-5]\d)"
  (hour-minute (Integer/parseInt (first (:groups %1)))
               (Integer/parseInt (second (:groups %1)))
               true)
  
  "hhmm (military time-of-day)"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))([0-5]\d)"
  (-> (hour-minute (Integer/parseInt (first (:groups %1)))
                (Integer/parseInt (second (:groups %1)))
                false) ; not a 12-hour clock
      (assoc :latent true))
  
  "quarter (relative minutes)"
  #"(?i)quarto"
  {:relative-minutes 15}

  "trois quarts (relative minutes)"
  #"(?i)(3|tre) quarti?"
  {:relative-minutes 45}

  "half (relative minutes)"
  #"mezzo"
  {:relative-minutes 30}

  "number (as relative minutes)"
  (integer 1 59)
  {:relative-minutes (:value %1)}
  
  "<integer> minutes (as relative minutes)"
  [(integer 1 59) #"(?i)min\.?(uto)?s?"]
  {:relative-minutes (:val %1)}

  "<hour-of-day> <integer> (as relative minutes)"
  [(dim :time :full-hour) #(:relative-minutes %)] ;before  [{:for-relative-minutes true} #(:relative-minutes %)]
  (hour-relativemin (:full-hour %1) (:relative-minutes %2) (:twelve-hour-clock? %1))

  "<hour-of-day> minus <integer> (as relative minutes)"
  [(dim :time :full-hour) #"meno( un)?" #(:relative-minutes %)]
  (hour-relativemin (:full-hour %1) (- (:relative-minutes %3)) (:twelve-hour-clock? %1))
  
  "relative minutes to <integer> (as hour-of-day)"
  [#(:relative-minutes %) #"(?i)a" (dim :time :full-hour)]
  (hour-relativemin (:full-hour %3) (- (:relative-minutes %1)) true)
  
  "<hour-of-day> and <relative minutes>"
  [(dim :time :full-hour) #"e( un)?" #(:relative-minutes %)]
  (hour-relativemin (:full-hour %1) (:relative-minutes %3) (:twelve-hour-clock? %1))


  ;; Formatted dates and times

  "dd[/-]mm[/-]yyyy"
  #"(3[01]|[12]\d|0?[1-9])[/-](0?[1-9]|1[0-2])[/-](\d{2,4})"
  (parse-dmy (first (:groups %1)) (second (:groups %1)) (nth (:groups %1) 2) true)

  "yyyy-mm-dd"
  #"(\d{2,4})-(0?[1-9]|1[0-2])-(3[01]|[12]\d|0?[1-9])"
  (parse-dmy (nth (:groups %1) 2) (second (:groups %1)) (first (:groups %1)) true)
  
  "dd[/-]mm"
  #"(3[01]|[12]\d|0?[1-9])[/-](0?[1-9]|1[0-2])"
  (parse-dmy (first (:groups %1)) (second (:groups %1)) nil true)
  
  ; Part of day (morning, evening...). They are intervals.
  "morning"
  #"(?i)mattin[aoe]"
  (assoc (interval (hour 4 false) (hour 12 false) false) :form :part-of-day :latent true)

  "afternoon"
  #"(?i)pomeriggio?"
  (assoc (interval (hour 12 false) (hour 19 false) false) :form :part-of-day :latent true)
  
  "evening"
  #"(?i)ser[ae]"
  (assoc (interval (hour 18 false) (hour 0 false) false) :form :part-of-day :latent true)
  
  "del|di <part-of-day>" ;; removes latent
  [#"(?i)del|di|al" {:form :part-of-day}]
  (dissoc %2 :latent)

  "this <part-of-day>"
  [#"(?i)questo|sta" {:form :part-of-day}]
  (assoc (intersect (cycle-nth :day 0) %2) :form :part-of-day) ;; removes :latent

  "<dim time> <part-of-day>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [(dim :time) {:form :part-of-day}]
  (intersect %1 %2)
 
   "<part-of-day> of <dim time>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [{:form :part-of-day} #"(?i)di" (dim :time)]
  (intersect %1 %3)

  ;specific rule to address "3 in the morning","3h du matin" and extend morning span from 0 to 12
  "<dim time> del mattino" 
  [{:form :time-of-day} #"del mattino"]
  (intersect %1 (assoc (interval (hour 0 false) (hour 12 false) false) :form :part-of-day :latent true))

  ; Other intervals: week-end, seasons
  "week-end"
  #"(?i)week[ -]?end"
  (interval (intersect (day-of-week 5) (hour 18 false))
            (intersect (day-of-week 1) (hour 0 false))
            false)

  ; Absorptions
  
  ; a specific version of "il", above, removes :latent for integer as day of month
  ; this one is more general but does not remove latency
  "il <time>"
  [#"(?i)il" (dim :time #(not (:latent %)))]
  %2

  ;; Time zones
  
  "timezone"
  #"(?i)(YEKT|YEKST|YAPT|YAKT|YAKST|WT|WST|WITA|WIT|WIB|WGT|WGST|WFT|WEZ|WET|WESZ|WEST|WAT|WAST|VUT|VLAT|VLAST|VET|UZT|UYT|UYST|UTC|ULAT|TVT|TMT|TLT|TKT|TJT|TFT|TAHT|SST|SRT|SGT|SCT|SBT|SAST|SAMT|RET|PYT|PYST|PWT|PT|PST|PONT|PMST|PMDT|PKT|PHT|PHOT|PGT|PETT|PETST|PET|PDT|OMST|OMSST|NZST|NZDT|NUT|NST|NPT|NOVT|NOVST|NFT|NDT|NCT|MYT|MVT|MUT|MST|MSK|MSD|MMT|MHT|MEZ|MESZ|MDT|MAWT|MART|MAGT|MAGST|LINT|LHST|LHDT|KUYT|KST|KRAT|KRAST|KGT|JST|IST|IRST|IRKT|IRKST|IRDT|IOT|IDT|ICT|HOVT|HNY|HNT|HNR|HNP|HNE|HNC|HNA|HLV|HKT|HAY|HAT|HAST|HAR|HAP|HAE|HADT|HAC|HAA|GYT|GST|GMT|GILT|GFT|GET|GAMT|GALT|FNT|FKT|FKST|FJT|FJST|ET|EST|EGT|EGST|EET|EEST|EDT|ECT|EAT|EAST|EASST|DAVT|ChST|CXT|CVT|CST|COT|CLT|CLST|CKT|CHAST|CHADT|CET|CEST|CDT|CCT|CAT|CAST|BTT|BST|BRT|BRST|BOT|BNT|AZT|AZST|AZOT|AZOST|AWST|AWDT|AST|ART|AQTT|ANAT|ANAST|AMT|AMST|ALMT|AKST|AKDT|AFT|AEST|AEDT|ADT|ACST|ACDT)"
  {:dim :timezone
   :value (-> %1 :groups first .toUpperCase)}
  
  "<time> timezone"
  [(dim :time) (dim :timezone)]
  (assoc %1 :timezone (:value %2))

  ; Intervals

)
