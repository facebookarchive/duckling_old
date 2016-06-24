(
  ;; generic

  "intersect"
  [(dim :time #(not (:latent %))) (dim :time #(not (:latent %)))] ; sequence of two tokens with a time dimension
  (intersect %1 %2)

  ; same thing, with "da" in between like "domingo da semana passada"
  "intersect by `da` or `de`"
  [(dim :time #(not (:latent %))) #"(?i)d[ae]" (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)

  ; mostly for segunda, 18 de fevereiro
  ; this is a separate rule, because commas separate very specific tokens
  ; so we want this rule's classifier to learn this
  "two time tokens separated by \",\""
  [(dim :time #(not (:latent %))) #"," (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)

  "n[ao] <date>" ; on Wed, March 23
  [#"(?i)n[ao]" (dim :time)]
  %2 ; does NOT dissoc latent

  "n[ao] named-day" ; on a sunday
  [#"(?i)n[ao]" {:form :day-of-week}]
  %2 ; does NOT dissoc latent
  ;;

  "named-day"
  #"(?i)segunda((\s|\-)feira)?|seg\.?"
  (day-of-week 1)

  "named-day"
  #"(?i)ter(ç|c)a((\s|\-)feira)?|ter\.?"
  (day-of-week 2)

  "named-day"
  #"(?i)quarta((\s|\-)feira)?|qua\.?"
  (day-of-week 3)

  "named-day"
  #"(?i)quinta((\s|\-)feira)?|qui\.?"
  (day-of-week 4)

  "named-day"
  #"(?i)sexta((\s|\-)feira)?|sex\.?"
  (day-of-week 5)

  "named-day"
  #"(?i)s[áa]bado|s(á|a)b\.?"
  (day-of-week 6)

  "named-day"
  #"(?i)domingo|dom\.?"
  (day-of-week 7)

  "named-month"
  #"(?i)janeiro|jan\.?"
  (month 1)

  "named-month"
  #"(?i)fevereiro|fev\.?"
  (month 2)

  "named-month"
  #"(?i)mar(ç|c)o|mar\.?"
  (month 3)

  "named-month"
  #"(?i)abril|abr\.?"
  (month 4)

  "named-month"
  #"(?i)maio|mai\.?"
  (month 5)

  "named-month"
  #"(?i)junho|jun\.?"
  (month 6)

  "named-month"
  #"(?i)julho|jul\.?"
  (month 7)

  "named-month"
  #"(?i)agosto|ago\.?"
  (month 8)

  "named-month"
  #"(?i)setembro|set\.?"
  (month 9)

  "named-month"
  #"(?i)outubro|out\.?"
  (month 10)

  "named-month"
  #"(?i)novembro|nov\.?"
  (month 11)

  "named-month"
  #"(?i)dezembro|dez\.?"
  (month 12)

; (Brazilian)? holidays
; TODO: Solve Moveable holidays, like Carnaval, Sexta-Feira Santa
; and Corpus Christi That depend on easter

  "natal"
  #"(?i)natal"
  (month-day 12 25)

  "vespera de ano novo"
  #"(?i)v[ée]spera d[eo] ano[\s\-]novo"
  (month-day 12 31)

  "ano novo"
  #"(?i)ano novo|reveillon"
  (month-day 1 1)

  "Tiradentes"
  #"(?i)tiradentes"
  (month-day 4 21)

  "Dia do trabalhador"
  #"(?i)dia do trabalh(o|ador)"
  (month-day 5 1)

  "Independecia"
  #"(?i)independ[êe]ncia"
  (month-day 9 7)

  "Nossa Senhora Aparecida"
  #"(?i)nossa senhora (aparecida)?"
  (month-day 10 12)

  "Finados"
  #"(?i)finados|dia dos mortos"
  (month-day 11 2)

  "Proclamação da República"
  #"(?i)proclama[cç][aã]o da rep[úu]blica"
  (month-day 11 15)

  "right now"
  #"agora|j[áa]|(nesse|neste) instante"
  (cycle-nth :second 0)

  "now"
  #"(?i)(hoje)|(neste|nesse) momento"
  (cycle-nth :day 0)

  "tomorrow"
  #"(?i)amanh[ãa]"
  (cycle-nth :day 1)

  "yesterday"
  #"(?i)ontem"
  (cycle-nth :day -1)

  "the day after tomorrow"
  #"(?i)depois de amanh[ãa]"
  (cycle-nth :day 2)

  "the day before yesterday"
  #"(?i)anteontem|antes de ontem"
  (cycle-nth :day -2)

  ;;
  ;; This, Next, Last

  ; assumed to be strictly in the future:
  ; "this Monday" => next week if today is Monday

  "this|next <day-of-week>"
  [#"(?i)es[ts][ae]|pr[óo]xim[ao]" {:form :day-of-week}]
  (pred-nth-not-immediate %2 0)

  ;; for other preds, it can be immediate:
  ;; "this month" => now is part of it
  ; See also: cycles in en.cycles.clj

  "this <time>"
  [#"(?i)es[ts][ae]" (dim :time)]
  (pred-nth %2 0)

  "next <time>"
  [#"(?i)(d[ao]) pr[óo]xim[ao]s?" (dim :time #(not (:latent %)))]
  (pred-nth-not-immediate %2 0)

  "next <time>"
  [(dim :time #(not (:latent %))) #"(?i)que vem"]
  (pred-nth-not-immediate %1 0)

  "last <time>"
  [#"(?i)[úu]ltim[ao]s?" (dim :time)]
  (pred-nth %2 -1)

  "<named-month|named-day> past"
  [(dim :time #(not (:latent %))) #"(?i)(da semana)? passad(o|a)"]
  (pred-nth %1 -1)

  "<named-month|named-day> next" ; próxim(0|a)
  [(dim :time) #"(?i)(da pr[oó]xima semana)|(da semana)? que vem"]
  (pred-nth %1 1)

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

  "de <year>"  ; latin america mostly
  [#"(?i)de|do ano" (integer 1000 2100)]
  (year (:value %2))

  ; Day of month appears in the following context:
  ; - le premier
  ; - le 5
  ; - 5 March
  ; - mm/dd (and other numerical formats like yyyy-mm-dd etc.)
  ; We remove the rule with just (integer 1 31) as it was too messy

  "day of month (1st)"
  [#"(?i)primeiro|um|1o"] ; |1º if possible later
  (day-of-month 1)

  "dia <day-of-month> (non ordinal)" ; this one is latent
  [#"(?i)dia" (integer 1 31)]
  (assoc (day-of-month (:value %2)) :latent true)


  "<day-of-month> de <named-month>" ; 4 de julho
  [(integer 1 31) #"(?i)de|\/" {:form :month}]
  (intersect %3 (day-of-month (:value %1)))

  "dia <day-of-month> de <named-month>" ; dia 4 de julho
  [#"(?i)dia" (integer 1 31) #"(?i)de|\/" {:form :month}]
  (intersect %4 (day-of-month (:value %2)))

  ;; hours and minutes (absolute time)
  "time-of-day (latent)"
  (integer 0 23)
  (assoc (hour (:value %1) true) :latent true)

  "noon"
  #"(?i)meio[\s\-]?dia"
  (hour 12 false)

  "midnight"
  #"(?i)meia[\s\-]?noite"
  (hour 0 false)

  "<time-of-day> horas"
  [#(:full-hour %) #"(?i)h\.?(ora)?s?"]
  (dissoc %1 :latent)

  "às <time-of-day>" ;
  [#"(?i)[àa]s?" {:form :time-of-day}]
  (dissoc %2 :latent)

  "às <hour-min>(time-of-day)" ; as 12:00 horas
  [#"(?i)[àa]s?" {:form :time-of-day} #"(?i)horas?"]
  %2

  "às <hour-min> <time-of-day>" ; as tres e meia da tarde
  [#"(?i)[àa]s" (dim :time #(not (:latent %))) #"(?i)da" {:form :time-of-day} ]
  (intersect %2 %4)

  "<day-of-week> às <hour-min>" ; quarta as tres e meia da tarde
  [{:form :time-of-day} #"(?i)[àa]s" (dim :time #(not (:latent %))) #"(?i)da|pela" {:form :time-of-day} ]
  (intersect %1 %3 %5)

  "hh(:|.|h)mm (time-of-day)"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))[:h\.]([0-5]\d)"
  (hour-minute (Integer/parseInt (first (:groups %1)))
               (Integer/parseInt (second (:groups %1)))
               true)

  "hhmm (military time-of-day)"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))([0-5]\d)"
  (-> (hour-minute (Integer/parseInt (first (:groups %1)))
                (Integer/parseInt (second (:groups %1)))
                false) ; not a 12-hour clock
      (assoc :latent true))

  "<time-of-day> am|pm"
  [{:form :time-of-day} #"(?i)([ap])\.?m?\.?"]
  ;; TODO set_am fn in helpers => add :ampm field
  (let [[p meridiem] (if (= "a" (-> %2 :groups first clojure.string/lower-case))
                       [[(hour 0) (hour 11) true] :am]
                       [[(hour 12) (hour 23) true] :pm])]
    (-> (intersect %1 (apply interval p))
        (assoc :form :time-of-day)))

  "quarter (relative minutes)"
  #"(?i)e quinze"
  {:relative-minutes 15}

  "3 quarter (relative minutes)"
  #"(?i)e quarenta e cinco"
  {:relative-minutes 45}

  "half (relative minutes)"
  #"(?i)e (meia|trinta)"
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

  "<integer> para as <hour-of-day> (as relative minutes)"
  [#(:relative-minutes %) #"(?i)para ([oaà]s?)?" (dim :time :full-hour) ]
  (hour-relativemin (:full-hour %3) (- (:relative-minutes %1)) (:twelve-hour-clock? %3))

  "<hour-of-day> and <relative minutes>"
  [(dim :time :full-hour) #"(?i)e" #(:relative-minutes %)]
  (hour-relativemin (:full-hour %1) (:relative-minutes %3) (:twelve-hour-clock? %1))

  ;; Formatted dates and times

  "dd[/-.]mm[/-.]yyyy"
  [#"(3[01]|[12]\d|0?[1-9])[\.\/\-](0?[1-9]|1[0-2])[\.\/\-](\d{2,4})"]
  (parse-dmy (first (:groups %1)) (second (:groups %1)) (nth (:groups %1) 2) true)

  "yyyy-mm-dd"
  [#"(\d{2,4})-(0?[1-9]|1[0-2])-(3[01]|[12]\d|0?[1-9])"]
  (parse-dmy (nth (:groups %1) 2) (second (:groups %1)) (first (:groups %1)) true)

  "dd[/-]mm"
  [#"(3[01]|[12]\d|0?[1-9])[\/\-](0?[1-9]|1[0-2])"]
  (parse-dmy (first (:groups %1)) (second (:groups %1)) nil true)

  ; Part of day (morning, evening...). They are intervals.

  "morning"
  #"(?i)manh[ãa]"
  (assoc (interval (hour 4 false) (hour 12 false) false) :form :part-of-day :latent true)

  "afternoon"
  #"(?i)tarde"
  (assoc (interval (hour 12 false) (hour 19 false) false) :form :part-of-day :latent true)

  "evening"
  #"(?i)noite"
  (assoc (interval (hour 18 false) (hour 0 false) false) :form :part-of-day :latent true)

  "in the <part-of-day>" ;; removes latent
  [#"(?i)(de|pela)" {:form :part-of-day}]
  (dissoc %2 :latent)

  "this <part-of-day>"
  [#"(?i)es[ts]a" {:form :part-of-day}]
  (assoc (intersect (cycle-nth :day 0) %2) :form :part-of-day) ;; removes :latent

  "<part-of-day> dessa semana"
  [(dim :time #(not (:latent %))) #"(?i)(d?es[ts]a semana)|agora"]
  (assoc (intersect (cycle-nth :day 0) %1) :form :part-of-day) ;; removes :latent

; ;specific rule to address "3 in the morning","3h du matin" and extend morning span from 0 to 12
;   "<dim time> du matin"
;   [{:form :time-of-day} #"du mat(in)?"]
;   (intersect %1 (assoc (interval (hour 0 false) (hour 12 false) false) :form :part-of-day :latent true))

  "<time-of-day> <part-of-day>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [(dim :time) #"(?i)(da|na|pela)" {:form :part-of-day}]
  (intersect %1 %3)

  ; specific rule to address the ambiguity of noite/tarde and extend tarde span from 12 to 18
  "<dim time> da tarde"
  [(dim :time) #"(?i)(da|na|pela) tarde"]
  (intersect %1 (assoc (interval (hour 12 false) (hour 18 false) false) :form :part-of-day :latent true))

  "<dim time> da manha"
  [(dim :time) #"(?i)(da|na|pela) manh[ãa]"]
  (intersect %1 (assoc (interval (hour 4 false) (hour 12 false) false) :form :part-of-day :latent true))

    ;specific rule to address the ambiguity of noite/tarde and extend tarde span from 12 to 21
  "<dim time> da madrugada"
  [(dim :time) #"(?i)(da|na|pela) madruga(da)?"]
  (intersect %1 (assoc (interval (hour 1 false) (hour 4 false) false) :form :part-of-day :latent true))

  "amanhã pela <part-of-day>"
  [(dim :time) #"(?i)(da|na|pela|a)" {:form :part-of-day}]
  (intersect %1 %3)

  ; Other intervals: week-end, seasons

  "week-end"
  #"(?i)final de semana|fim de semana|fds"
  (interval (intersect (day-of-week 5) (hour 18 false))
            (intersect (day-of-week 1) (hour 0 false))
            false)

  "season"
  #"(?i)ver[ãa]o" ;could be smarter and take the exact hour into account... also some years the day can change
  (interval (month-day 6 21) (month-day 9 23) false)

  "season"
  #"(?i)outono"
  (interval (month-day 9 23) (month-day 12 21) false)

  "season"
  #"(?i)inverno"
  (interval (month-day 12 21) (month-day 3 20) false)

  "season"
  #"(?i)primavera"
  (interval (month-day 3 20) (month-day 6 21) false)

  ; a specific version of "el", above, removes :latent for integer as day of month
  ; this one is more general but does not remove latency
  ; "el <time>"
  ; [#"(?i)d?el" (dim :time #(not (:latent %)))]
  ; %2

  ;; Time zones

  "timezone"
  #"(?i)(YEKT|YEKST|YAPT|YAKT|YAKST|WT|WST|WITA|WIT|WIB|WGT|WGST|WFT|WEZ|WET|WESZ|WEST|WAT|WAST|VUT|VLAT|VLAST|VET|UZT|UYT|UYST|UTC|ULAT|TVT|TMT|TLT|TKT|TJT|TFT|TAHT|SST|SRT|SGT|SCT|SBT|SAST|SAMT|RET|PYT|PYST|PWT|PT|PST|PONT|PMST|PMDT|PKT|PHT|PHOT|PGT|PETT|PETST|PET|PDT|OMST|OMSST|NZST|NZDT|NUT|NST|NPT|NOVT|NOVST|NFT|NDT|NCT|MYT|MVT|MUT|MST|MSK|MSD|MMT|MHT|MEZ|MESZ|MDT|MAWT|MART|MAGT|MAGST|LINT|LHST|LHDT|KUYT|KST|KRAT|KRAST|KGT|JST|IST|IRST|IRKT|IRKST|IRDT|IOT|IDT|ICT|HOVT|HNY|HNT|HNR|HNP|HNE|HNC|HNA|HLV|HKT|HAY|HAT|HAST|HAR|HAP|HAE|HADT|HAC|HAA|GYT|GST|GMT|GILT|GFT|GET|GAMT|GALT|FNT|FKT|FKST|FJT|FJST|ET|EST|EGT|EGST|EET|EEST|EDT|ECT|EAT|EAST|EASST|DAVT|ChST|CXT|CVT|CST|COT|CLT|CLST|CKT|CHAST|CHADT|CET|CEST|CDT|CCT|CAT|CAST|BTT|BST|BRT|BRST|BOT|BNT|AZT|AZST|AZOT|AZOST|AWST|AWDT|AST|ART|AQTT|ANAT|ANAST|AMT|AMST|ALMT|AKST|AKDT|AFT|AEST|AEDT|ADT|ACST|ACDT)"
  {:dim :timezone
   :value (-> %1 :groups first clojure.string/upper-case)}

  "<time> timezone"
  [(dim :time) (dim :timezone)]
  (assoc %1 :timezone (:value %2))


  ; Intervals
  "dd-dd <month>(interval)"
  [#"(3[01]|[12]\d|0?[1-9])" #"(?i)\-|a" #"(3[01]|[12]\d|0?[1-9])" #"(?i)de" {:form :month}]
  (interval (intersect %5 (day-of-month (Integer/parseInt (-> %1 :groups first))))
            (intersect %5 (day-of-month (Integer/parseInt (-> %3 :groups first))))
            true)

  "entre dd et dd <month>(interval)"
  [#"(?i)entre" #"(0?[1-9]|[12]\d|3[01])" #"(?i)e?" #"(0?[1-9]|[12]\d|3[01])" #"(?i)de" {:form :month}]
  (interval (intersect %6 (day-of-month (Integer/parseInt (-> %2 :groups first))))
            (intersect %6 (day-of-month (Integer/parseInt (-> %4 :groups first))))
            true)

  ; Blocked for :latent time. May need to accept certain latents only, like hours

  "<datetime> - <datetime> (interval)"
  [(dim :time #(not (:latent %))) #"\-|a" (dim :time #(not (:latent %)))]
  (interval %1 %3 false)

  "de <datetime> - <datetime> (interval)"
  [#"(?i)de?" (dim :time) #"\-|a" (dim :time)]
  (interval %2 %4 false)

  "entre <datetime> e <datetime> (interval)"
  [#"(?i)entre" (dim :time) #"e" (dim :time)]
  (interval %2 %4 false)

  ; Specific for within duration... Would need to be reworked
  "dentro de <duration>"
  [#"(?i)(dentro de)|em" (dim :duration)]
  (interval (cycle-nth :second 0) (in-duration (:value %2)) false)

  "ultimo <time>"
  [#"(?i)[uú]ltimo" (dim :time)]
  (pred-nth %2 -1)

  ; One-sided Intervals

  "antes das <time-of-day>"
  [#"(?i)(antes|at[eé]|n[aã]o mais que) (d?[oaà]s?)?" (dim :time)]
  (merge %2 {:direction :before})

  "depois das <time-of-day>"
  [#"(?i)(depois|ap[óo]s) d?([aáà][so]?|os?)" (dim :time)]
  (merge %2 {:direction :after})

)
