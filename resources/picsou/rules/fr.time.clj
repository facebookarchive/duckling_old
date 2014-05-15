(
  ;; generic
  
  "two time tokens in a row"
  [(dim :time #(not (:latent %))) (dim :time #(not (:latent %)))] ; sequence of two tokens with a time dimension
  (intersect %1 %2)

  ; same thing, with "de" in between like "mardi de la semaine dernière"
  "two time tokens separated by `de`"
  [(dim :time #(not (:latent %))) #"(?i)de" (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)
  
  ;;

  "named-day"
  #"(?i)lundi|lun|lun\."
  (assoc (day-of-week 1) :not-immediate true :form :named-day)

  "named-day"
  #"(?i)mardi|mar|mar\."
  (assoc (day-of-week 2) :not-immediate true :form :named-day)

  "named-day"
  #"(?i)mercredi|mer|mer\."
  (assoc (day-of-week 3) :not-immediate true :form :named-day)

  "named-day"
  #"(?i)jeudi|jeu|jeu\."
  (assoc (day-of-week 4) :not-immediate true :form :named-day)

  "named-day"
  #"(?i)vendredi|ven|ven\."
  (assoc (day-of-week 5) :not-immediate true :form :named-day)

  "named-day"
  #"(?i)samedi|sam|sam\."
  (assoc (day-of-week 6) :not-immediate true :form :named-day)

  "named-day"
  #"(?i)dimanche|dim|dim\."
  (assoc (day-of-week 7) :not-immediate true :form :named-day)

  "named-month"
  #"(?i)janvier|janv\.?"
  (assoc (month-of-year 1) :form :named-month)

  "named-month"
  #"(?i)fevrier|février|fev|fév\.?"
  (assoc (month-of-year 2) :form :named-month)

  "named-month"
  #"(?i)mars|mar\.?"
  (assoc (month-of-year 3) :form :named-month)

  "named-month"
  #"(?i)avril|avr\.?"
  (assoc (month-of-year 4) :form :named-month)

  "named-month"
  #"(?i)mai"
  (assoc (month-of-year 5) :form :named-month)

  "named-month"
  #"(?i)juin|jun\.?"
  (assoc (month-of-year 6) :form :named-month)

  "named-month"
  #"(?i)juillet|juil?\."
  (assoc (month-of-year 7) :form :named-month)

  "named-month"
  #"(?i)aout|août|aou\.?"
  (assoc (month-of-year 8) :form :named-month)

  "named-month"
  #"(?i)septembre|sept?\.?"
  (assoc (month-of-year 9) :form :named-month)

  "named-month"
  #"(?i)octobre|oct\.?"
  (assoc (month-of-year 10) :form :named-month)

  "named-month"
  #"(?i)novembre|nov\.?"
  (assoc (month-of-year 11) :form :named-month)

  "named-month"
  #"(?i)décembre|decembre|déc\.?|dec\.?"
  (assoc (month-of-year 12) :form :named-month)

  "maintenant"
  #"maintenant|(tout de suite)"
  (this-cycle seconds 0)
  
  "aujourd'hui"
  #"(?i)(aujourd'? ?hui)|(ce jour)|(dans la journ[ée]e?)|(en ce moment)"
  (this-cycle days 0)

  "demain"
  #"(?i)demain"
  (this-cycle days 1)

  "hier"
  #"(?i)hier"
  (this-cycle days -1)

  "après-demain"
  #"(?i)apr(e|è)s[- ]?demain"
  (this-cycle days 2)

  "avant-hier"
  #"(?i)avant[- ]?hier"
  (this-cycle days -2)

  "week-end"
  #"(?i)week-?end"
  (assoc (between-days-of-weeks-hours 5 18 1 0) :form :named-day)
  
  "ce <day-of-week>" ; assumed to be in the future
  [#"(?i)ce" {:form :named-day}]
  (this-pred %2 1)

  "<named-month|named-day> prochain"
  [(dim :time) #"(?i)prochain"]
  (this-pred %1 1)

  "<named-month|named-day> dernier|passé"
  [(dim :time) #"(?i)dernier|pass[ée]e?"]
  (this-pred %1 -1)

  "<named-day> en huit"
  [{:form :named-day} #"(?i)en (huit|8)"]
  (this-pred %1 2)

  "<named-day> en quinze"
  [{:form :named-day} #"(?i)en (quinze|15)"]
  (this-pred %1 3)

  ;;
  
  "day of month (numeric)"
  [(integer 1 31)]
  (assoc (day-of-month (:val %1)) :latent true :form :day-of-month)
  
  "day of month (premier)"
  [#"(?i)premier|prem\.?|1er|1 er"]
  (assoc (day-of-month 1) :latent true :form :day-of-month)

  "le <day-of-month>" ; this one is not latent
  [#"(?i)le" {:form :day-of-month}]
  (dissoc %2 :latent)

  "month (numeric)"
  (integer 1 12)
  (assoc (month-of-year (:val %1)) :latent true)

  "year (four digit)"
  (integer 1000 9999)
  (year (:val %1))
  
  "<day-of-month> <named-month>" ; 12 mars (this rule removes latency)
  [{:form :day-of-month} {:form :named-month}]
  (intersect %1 %2)

  "matin"
  #"(?i)matin[ée]?e?"
  (assoc (between-hours 4 12) :form :part-of-day :latent true)

  "après-midi"
  #"(?i)apr[eéè]s?[ \-]?midi"
  (assoc (between-hours 12 19) :form :part-of-day :latent true)

  "soir"
  #"(?i)soir[ée]?e?"
  (assoc (between-hours 18 0) :form :part-of-day :latent true)

  "dans le <part-of-day>" ;; removes latent
  [#"(?i)dans l[ae']? ?" {:form :part-of-day}]
  (dissoc %2 :latent)
  
  "ce <part-of-day>"
  [#"(?i)cet?t?e?" {:form :part-of-day}]
  (intersect (this-cycle days 0) %2) ;; removes :latent
  
  "<dim time> <part-of-day>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [(dim :time) {:form :part-of-day}]
  (intersect %1 %2)

  ;; hours and minutes (absolute time)

  "<integer> heures (time-of-day)"
  [(integer 0 23) #"(?i)h\.?(eure)?s?"]
  (assoc (hour (:val %1) true)
    :form :time-of-day
    :for-relative-minutes true
    :val (:val %1))
  
  "à|vers <time-of-day>" ; absorption
  [#"(?i)[aà]|vers" {:form :time-of-day}]
  %2

  "hh(:|h)mm (time-of-day)"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))[:h]([0-5]\d)"
  (hour-min (read-string (first (:groups %1)))
            true
            (read-string (second (:groups %1))))
  
  "hhmm (military time-of-day)"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))([0-5]\d)"
  (-> (hour-min (read-string (first (:groups %1)))
                true
                (read-string (second (:groups %1))))
      (assoc :latent true))
    
  "midi"
  #"(?i)midi"
  (-> (hour 12 false)
      (assoc :form :time-of-day
             :for-relative-minutes true :val 12))

  "minuit"
  #"(?i)minuit"
  (-> (hour 0 false)
      (assoc :form :time-of-day
             :for-relative-minutes true :val 0))

  "quart (relative minutes)"
  #"(?i)quart"
  {:relative-minutes 15}

  "trois quarts (relative minutes)"
  #"(?i)(3|trois) quarts?"
  {:relative-minutes 45}

  "demi (relative minutes)"
  #"demie?"
  {:relative-minutes 30}

  "number (as relative minutes)"
  (integer 1 59)
  {:relative-minutes (:val %1)}
  
  "<integer> minutes (as relative minutes)"
  [(integer 1 59) #"(?i)min\.?(ute)?s?"]
  {:relative-minutes (:val %1)}

  "<hour-of-day> <integer> (as relative minutes)"
  [{:for-relative-minutes true} #(:relative-minutes %)]
  (hour-relativemin 
    (:val %1) 
    (:ambiguous-am-pm %1)
    (:relative-minutes %2))

  "<hour-of-day> moins <integer> (as relative minutes)"
  [{:for-relative-minutes true} #"moins( le)?" #(:relative-minutes %)]
  (hour-relativemin 
    (:val %1)
    (:ambiguous-am-pm %1)
    (- (:relative-minutes %3)))
  
  "<hour-of-day> et|passé de <relative minutes>"
  [{:for-relative-minutes true} #"et|(pass[ée]e? de)" #(:relative-minutes %)]
  (hour-relativemin 
    (:val %1)
    (:ambiguous-am-pm %1)
    (:relative-minutes %3))

  ;; formatted

  "dd/mm/yyyy"
  #"([012]?\d|30|31)/(0?\d|10|11|12)/(\d{2,4})"
  (parse-dmy (first (:groups %1)) (second (:groups %1)) (nth (:groups %1) 2) true)

  "yyyy-mm-dd"
  #"(\d{2,4})-(0?\d|10|11|12)-([012]?\d|30|31)"
  (parse-dmy (nth (:groups %1) 2) (second (:groups %1)) (first (:groups %1)) true)
  
  "dd/mm"
  #"([012]?\d|30|31)/(0?\d|10|11|12)"
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
