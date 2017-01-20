(
  ;; generic

  "two time tokens in a row"
  [(dim :time #(not (:latent %))) (dim :time #(not (:latent %)))] ; sequence of two tokens with a time dimension
  (intersect %1 %2)

  ; same thing, with "de" in between like "domingo de la semana pasada"
  "two time tokens separated by `di`"
  [(dim :time #(not (:latent %))) #"(?i)di" (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)

  "in <named-month>" ; in September
  [#"(?i)in|del mese( di)?" {:form :month}]
  %2 ; does NOT dissoc latent

  ;;

  "named-day"
  #"(?i)luned[iì]|lun\.?"
  (day-of-week 1)

  "named-day"
  #"(?i)marted[iì]|mar\.?"
  (day-of-week 2)

  "named-day"
  #"(?i)mercoled[iì]|mer\.?"
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
  #"(?i)gennaio|genn?\.?"
  (month 1)

  "named-month"
  #"(?i)febbraio|febb?\.?"
  (month 2)

  "named-month"
  #"(?i)marzo|mar\.?"
  (month 3)

  "named-month"
  #"(?i)aprile|apr\.?"
  (month 4)

  "named-month"
  #"(?i)maggio|magg?\.?"
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
  ; or define dynamyc rule (last thursday of october..)

  "christmas"
  #"(?i)((il )?giorno di )?natale"
  (month-day 12 25)

  "christmas eve"
  #"(?i)((al)?la )?vigig?lia( di natale)?"
  (month-day 12 24)

  "new year's eve"
  #"(?i)((la )?vigig?lia di capodanno|san silvestro)"
  (month-day 12 31)

  "new year's day"
  #"(?i)(capodanno|primo dell' ?anno)"
  (month-day 1 1)

  "epifania"
  #"(?i)(epifania|befana)"
  (month-day 1 6)

  "valentine's day"
  #"(?i)s(an|\.) valentino|festa degli innamorati"
  (month-day 2 14)

  "festa del papà"
  #"(?i)festa del pap[aà]|(festa di )?s(an|\.) giuseppe"
  (month-day 3 19)

  "festa della liberazione"
  #"(?i)((festa|anniversario) della|(al)?la) liberazione"
  (month-day 4 25)

  "festa del lavoro"
  #"(?i)festa del lavoro|(festa|giorno) dei lavoratori"
  (month-day 5 1)

  "festa della repubblica"
  #"(?i)((festa del)?la )?repubblica"
  (month-day 6 2)

  "ferragosto"
  #"(?i)ferragosto|assunzione"
  (month-day 8 15)

  "halloween day"
  #"(?i)hall?owe?en"
  (month-day 10 31)

  "ognissanti"
  #"(?i)(tutti i |ognis|festa dei |([ia]l )?giorno dei )santi"
  (month-day 11 1)

  "commemorazione dei defunti"
  #"(?i)((giorno dei|commemorazione dei|ai) )?(morti|defunti)"
  (month-day 11 2)

  "immacolata concezione"
  #"(?i)(all')?immacolata( concezione)?"
  (month-day 12 8)

  "santo stefano"
  #"(?i)s(anto|\.) stefano"
  (month-day 12 26)

  "Mother's Day";second Sunday in May.
  #"(?i)festa della mamma"
  (intersect (day-of-week 7) (month 5) (cycle-nth-after :week 1 (month-day 5 1)))


  "now"
  #"(?i)subito|(immediata|attual)mente|(proprio )?adesso|(in questo|al) momento|ora"
  (cycle-nth :second 0)

  "today"
  #"(?i)(di )?oggi|in giornata"
  (cycle-nth :day 0)

  "tomorrow"
  #"(?i)domani"
  (cycle-nth :day 1)

  "yesterday"
  #"(?i)ieri"
  (cycle-nth :day -1)

  "EOM|End of month"
  #"(?i)fine del mese"
  (cycle-nth :month 1)

  "EOY|End of year"
  #"(?i)fine dell' ?anno"
  (cycle-nth :year 1)

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
  [#"(?i)quest[oaie']" (dim :time)]
  (pred-nth %2 0)

  "next <time>"
  [#"(?i)prossim[ao]" (dim :time)]
  (pred-nth %2 1)

  "next <time>"
  [(dim :time) #"(?i)dopo|prossim[ao]"]
  (pred-nth %1 1)

  "prossimi <unit-of-duration>"
  [#"(?i)((([nd]e)?i|([nd]el)?le) )?prossim[ie]" (dim :unit-of-duration)]
  (interval (cycle-nth (:grain %2) 1) (cycle-nth (:grain %2) 3) true)

  "<time> last"
  [(dim :time) #"(?i)(ultim|scors|passat)[oaie]"]
  (pred-nth %1 -1)

  "last <time> "
  [#"(?i)(ultim|scors|passat)[oaie]" (dim :time)]
  (pred-nth %2 -1)

  "last <day-of-week> of <time>"
  [#"(?i)(l')ultim[oa]" {:form :day-of-week} #"(?i)di" (dim :time)]
  (pred-last-of %2 %4)

  "last <cycle> of <time>"
  [#"(?i)(l')ultim[oa]" (dim :cycle) #"(?i)di|del(l[oa'])" (dim :time)]
  (cycle-last-of %2 %4)

  ; Ordinals
  "nth <time> of <time>"
  [(dim :ordinal) (dim :time) #"(?i)di|del(l[oa'])|in" (dim :time)]
  (pred-nth (intersect %4 %2) (dec (:value %1)))

  "nth <time> of <time>"
  [#"(?i)il|l'" (dim :ordinal) (dim :time) #"(?i)di|del(l[oa'])|in" (dim :time)]
  (pred-nth (intersect %5 %3) (dec (:value %2)))

  "nth <time> after <time>"
  [(dim :ordinal) (dim :time) #"(?i)dopo" (dim :time)]
  (pred-nth-after %2 %4 (dec (:value %1)))

  "nth <time> after <time>"
  [#"(?i)il|l'" (dim :ordinal) (dim :time) #"(?i)dopo" (dim :time)]
  (pred-nth-after %3 %5 (dec (:value %2)))


  ; Years
  ; Between 1000 and 2100 we assume it's a year
  ; Outside of this, it's safer to consider it's latent

  "year (1000-2100 not latent)"
  (integer 1000 2100)
  (year (:value %1))

  "year (latent)"
  (integer -10000 -1)
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
  [#"(?i)(primo|1o|1º|1°)"];
  (day-of-month 1)

  "il <day-of-month>" ; this one is not latent
  [#"(?i)il|l'" (integer 1 31)]
  (day-of-month (:value %2))

  "<day-of-month> <named-month>" ;  dodici luglio 2010 (this rule removes latency)
  [(integer 1 31) {:form :month}]
  (intersect %2 (day-of-month (:value %1)))

  "<named-day> <day-of-month>" ; Friday 18
  [{:form :day-of-week} (integer 1 31)]
  (intersect %1 (day-of-month (:value %2)))

  "il <day-of-month> <named-month>" ; il dodici luglio 2010
  [#"(?i)il|l'" (integer 1 31) {:form :month}]
  (intersect %3 (day-of-month (:value %2)))

  ;; hours and minutes (absolute time)
  "<integer> (latent time-of-day)"
  (integer 0 24)
  (assoc (hour (:value %1) false) :latent true)

  "le idi di <named-month>" ; the ides of march 13th for most months, but on the 15th for March, May, July, and October
  [#"(?i)(le )?idi di" {:form :month}]
  (intersect %2 (day-of-month (if (#{3 5 7 10} (:month %2)) 15 13)))

  "noon"
  #"(?i)mezz?ogiorno"
  (hour 12 false)

  "midnight"
  #"(?i)mez?zanott?e"
  (hour 0 false)

  "<time-of-day> ora"
  [#"(?i)or[ea]" #(:full-hour %)]
  (dissoc %2 :latent)

  "at <time-of-day>" ; alle due
  [#"(?i)all[e']|l[e']|a" {:form :time-of-day}]
  (dissoc %2 :latent)


  "hh(:|h)mm (time-of-day)"
  #"((?:[01]?\d)|(?:2[0-3]))[:h]([0-5]\d)"
  (hour-minute (Integer/parseInt (first (:groups %1)))
               (Integer/parseInt (second (:groups %1)))
               false)

  "hh(:|h)mm (time-of-day)"
  #"(?i)((?:0?\d)|(?:1[0-2]))[:h]([0-5]\d) d(i|el(la)?) (pomeriggio|(sta)?(sera|notte))"
  (hour-minute (+ 12 (Integer/parseInt (first (:groups %1))))
               (Integer/parseInt (second (:groups %1)))
               false)

  ; specific rule to address "3 in the afternoon", "9 in the evening"
  "<integer 0 12> del <part of day>"
  [(integer 0 12) #"(?i)d(i|el(la)?) (pomeriggio|(sta)?(sera|notte))"]
  (hour (+ 12 (:value %1)) false)

  "hh <relative-minutes> del pomeriggio(time-of-day)"
  [(dim :time :full-hour) #(:relative-minutes %) #"(?i)d(i|el(la)?) (pomeriggio|(sta)?(sera|notte))"]
  (if (< 12 (:full-hour %1))
    (dissoc %1 :latent)
    (hour-relativemin (+ 12 (:full-hour %1)) (:relative-minutes %2) false))

  "hh <relative-minutes> del pomeriggio(time-of-day)"
  [(dim :time :full-hour) #"e" #(:relative-minutes %) #"(?i)d(i|el(la)?)" #"(pomeriggio|(sta)?(sera|notte))"]
  (if (< 12 (:full-hour %1))
    (dissoc %1 :latent)
    (hour-relativemin (+ 12 (:full-hour %1)) (:relative-minutes %3) false))

  "hhmm (military time-of-day)"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))([0-5]\d)"
  (-> (hour-minute (Integer/parseInt (first (:groups %1)))
                (Integer/parseInt (second (:groups %1)))
                false) ; not a 12-hour clock
      (assoc :latent true))

  "una"
  #"(?i)una"
  (assoc (hour 1 true) :latent true)

  "quarter (relative minutes)"
  #"(?i)un quarto"
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
  [(integer 1 59) #"(?i)min(ut[oi]|\.)?"]
  {:relative-minutes (:val %1)}

  "<hour-of-day> <integer> (as relative minutes)"
  [(dim :time :full-hour) #(:relative-minutes %)] ;before  [{:for-relative-minutes true} #(:relative-minutes %)]
  (hour-relativemin (:full-hour %1) (:relative-minutes %2) (:twelve-hour-clock? %1))

  "<hour-of-day> minus <integer> (as relative minutes)"
  [(dim :time :full-hour) #"meno" #(:relative-minutes %)]
  (hour-relativemin (:full-hour %1) (- (:relative-minutes %3)) (:twelve-hour-clock? %1))

  "relative minutes to <integer> (as hour-of-day)"
  [#(:relative-minutes %) #"(?i)a" (dim :time :full-hour)]
  (hour-relativemin (:full-hour %3) (- (:relative-minutes %1)) true)

  "<hour-of-day> and <relative minutes>"
  [(dim :time :full-hour) #"e" #(:relative-minutes %)]
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
  #"(?i)mattin(ata|[aoe])"
  (assoc (interval (hour 4 false) (hour 12 false) false) :form :part-of-day :latent true)

  "lunch"
  #"(?i)(a )?pranzo"
  (assoc (interval (hour 12 false) (hour 14 false) false) :form :part-of-day :latent true)

  "afternoon"
  #"(?i)pomeriggio?"
  (assoc (interval (hour 12 false) (hour 19 false) false) :form :part-of-day :latent true)

  "evening"
  #"(?i)ser(ata|[ae])"
  (assoc (interval (hour 18 false) (hour 0 false) false) :form :part-of-day :latent true)

  "night"
  #"(?i)nott(e|ata)"
  (assoc (intersect (cycle-nth :day 1) (interval (hour 0 false) (hour 4 false) false)) :form :part-of-day :latent true)

  "this <part-of-day>"
  [#"(?i)(que)?st[oa]|in|(al|nel)(la)?|la|il" {:form :part-of-day}]
  (assoc (intersect (cycle-nth :day 0) %2) :form :part-of-day) ;; removes :latent

  "<time> notte"
  [(dim :time) #"(?i)((in|nella|alla) )?nott(e|ata)"]
  (intersect (cycle-nth-after :day 1 %1) (interval (hour 0 false) (hour 4 false) false))

  "<time> notte"
  [#"(?i)((nella|alla) )?nott(e|ata)( d(i|el))" (dim :time)]
  (intersect (cycle-nth-after :day 1 %2) (interval (hour 0 false) (hour 4 false) false))

  "stamattina"
  [#"(?i)stamattina"]
  (assoc (intersect (cycle-nth :day 0) (interval (hour 4 false) (hour 12 false) false)) :form :part-of-day)

  "stasera"
  [#"(?i)stasera"]
  (assoc (intersect (cycle-nth :day 0) (interval (hour 18 false) (hour 0 false) false)) :form :part-of-day)

  "stanotte"
  [#"(?i)(sta|nella )notte|(in|nella) nottata"]
  (assoc (intersect (cycle-nth :day 1) (interval (hour 0 false) (hour 4 false) false)) :form :part-of-day)

  "domattina"
  [#"(?i)domattina"]
  (assoc (intersect (cycle-nth :day 1) (interval (hour 4 false) (hour 12 false) false)) :form :part-of-day)

  "<dim time> <part-of-day>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [(dim :time) {:form :part-of-day}]
  (intersect %1 %2)

  "<part-of-day> of <dim time>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [{:form :part-of-day} #"(?i)d(i|el)" (dim :time)]
  (intersect %1 %3)

  "in the <part-of-day> of <dim time>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [#"(?i)nel(la)?" {:form :part-of-day} #"(?i)d(i|el)" (dim :time)]
  (intersect %2 %4)

  "<dim time> al <part-of-day>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [(dim :time) #"(?i)al|nel(la)?|in|d(i|el(la)?)" {:form :part-of-day}]
  (intersect %1 %3)

  ;specific rule to address "3 in the morning","3h du matin" and extend morning span from 0 to 12
  "<dim time> del mattino"
  [{:form :time-of-day} #"del mattino"]
  (intersect %1 (assoc (interval (hour 0 false) (hour 12 false) false) :form :part-of-day :latent true))

  ; Other intervals: week-end, seasons
  "week-end"
  #"(?i)week[ -]?end|fine ?settimana|we"
  (interval (intersect (day-of-week 5) (hour 18 false))
            (intersect (day-of-week 1) (hour 0 false))
            false)

  "il week-end del <time>"
  [#"(?i)il (week[ -]?end|fine ?settimana|we) del" (dim :time)]
  (interval (intersect (intersect (cycle-nth-after :week 0 %2) (day-of-week 5)) (hour 18 false))
            (intersect (intersect (cycle-nth-after :week 1 %2) (day-of-week 1)) (hour 0 false))
            false)

  "season"
  #"(?i)(in )?estate" ;could be smarter and take the exact hour into account... also some years the day can change
  (interval (month-day 6 21) (month-day 9 23) false)

  "season"
  #"(?i)(in )?autunno"
  (interval (month-day 9 23) (month-day 12 21) false)

  "season"
  #"(?i)(in )?inverno"
  (interval (month-day 12 21) (month-day 3 20) false)

  "season"
  #"(?i)(in )?primavera"
  (interval (month-day 3 20) (month-day 6 21) false)

  ; Absorptions

  ; a specific version of "il", above, removes :latent for integer as day of month
  ; this one is more general but does not remove latency
  "il <time>"
  [#"(?i)il|l'" (dim :time #(not (:latent %)))]
  %2

  ;; Time zones

  "timezone"
  #"(?i)(YEKT|YEKST|YAPT|YAKT|YAKST|WT|WST|WITA|WIT|WIB|WGT|WGST|WFT|WEZ|WET|WESZ|WEST|WAT|WAST|VUT|VLAT|VLAST|VET|UZT|UYT|UYST|UTC|ULAT|TVT|TMT|TLT|TKT|TJT|TFT|TAHT|SST|SRT|SGT|SCT|SBT|SAST|SAMT|RET|PYT|PYST|PWT|PT|PST|PONT|PMST|PMDT|PKT|PHT|PHOT|PGT|PETT|PETST|PET|PDT|OMST|OMSST|NZST|NZDT|NUT|NST|NPT|NOVT|NOVST|NFT|NDT|NCT|MYT|MVT|MUT|MST|MSK|MSD|MMT|MHT|MEZ|MESZ|MDT|MAWT|MART|MAGT|MAGST|LINT|LHST|LHDT|KUYT|KST|KRAT|KRAST|KGT|JST|IST|IRST|IRKT|IRKST|IRDT|IOT|IDT|ICT|HOVT|HNY|HNT|HNR|HNP|HNE|HNC|HNA|HLV|HKT|HAY|HAT|HAST|HAR|HAP|HAE|HADT|HAC|HAA|GYT|GST|GMT|GILT|GFT|GET|GAMT|GALT|FNT|FKT|FKST|FJT|FJST|ET|EST|EGT|EGST|EET|EEST|EDT|ECT|EAT|EAST|EASST|DAVT|ChST|CXT|CVT|CST|COT|CLT|CLST|CKT|CHAST|CHADT|CET|CEST|CDT|CCT|CAT|CAST|BTT|BST|BRT|BRST|BOT|BNT|AZT|AZST|AZOT|AZOST|AWST|AWDT|AST|ART|AQTT|ANAT|ANAST|AMT|AMST|ALMT|AKST|AKDT|AFT|AEST|AEDT|ADT|ACST|ACDT)"
  {:dim :timezone
   :value (-> %1 :groups first clojure.string/upper-case)}

  "<time> timezone"
  [(dim :time) (dim :timezone)]
  (assoc %1 :timezone (:value %2))

  ; Precision
  ; FIXME
  ; - should be applied to all dims not just time-of-day
  ;-  shouldn't remove latency, except maybe -ish

  "<time-of-day> circa" ; 7ish
  [{:form :time-of-day} #"(?i)circa"]
  (-> %1
    (dissoc :latent)
    (merge {:precision "approximate"}))

  "<time-of-day> precise" ; sharp
  [{:form :time-of-day} #"(?i)precise"]
  (-> %1
    (dissoc :latent)
    (merge {:precision "exact"}))

  "circa per le <time-of-day>" ; about
  [#"(?i)(circa )?per( le)?|circa( alle)?|verso( le)?" {:form :time-of-day}]
  (-> %2
    (dissoc :latent)
    (merge {:precision "approximate"}))


  "verso <part-of-day>" ; about
  [#"(?i)verso( la| il)?" {:form :part-of-day}]
  (merge %2 {:precision "approximate"})

  ; Intervals

  "dd-dd <month> (interval)"
  [#"(3[01]|[12]\d|0?[1-9])" #"\-" #"(3[01]|[12]\d|0?[1-9])" {:form :month}]
  (interval (intersect %4 (day-of-month (Integer/parseInt (-> %1 :groups first))))
            (intersect %4 (day-of-month (Integer/parseInt (-> %3 :groups first))))
            true)

  "dal <integer> al <integer> (interval)"
  [#"(?i)dal(?:l')?" (integer 1 31) #"(?i)([fs]ino )?al(l')?" (integer 1 31)]
  (interval (day-of-month (:value %2))
            (day-of-month (:value %4))
            true)

  "tra il <integer> e il <integer> (interval)"
  [#"(?i)tra( (il|l'))?" (integer 1 31) #"(?i)e( (il|l'))?" (integer 1 31)]
  (interval (day-of-month (:value %2))
            (day-of-month (:value %4))
            true)

  ; Blocked for :latent time. May need to accept certain latents only, like hours

  "<datetime> - <datetime> (interval)"
  [(dim :time #(not (:latent %))) #"\-|[fs]ino a(l(l[e'])?)?" (dim :time #(not (:latent %)))]
  (interval %1 %3 true)

  "fino al <datetime> (interval)"
  [#"[fs]ino a(l(l[ae'])?)?" (dim :time #(not (:latent %)))]
  (interval (cycle-nth :second 0) %2 false)

  "dal <datetime> al <datetime> (interval)"
  [#"(?i)da(l(l')?)?" (dim :time #(not (:latent %))) #"([fs]ino )?a(l(l')?)?" (dim :time #(not (:latent %)))]
  (interval %2 %4 true)

  "tra il <datetime> e il <datetime> (interval)"
  [#"(?i)tra( il| l')?" (dim :time #(not (:latent %))) #"e( il| l')?" (dim :time #(not (:latent %)))]
  (interval %2 %4 true)

  "<time-of-day> - <time-of-day> <day-of-month> (interval)"
  [{:form :time-of-day} #"\-" {:form :time-of-day} (dim :time #(not (:latent %)))]
  (interval (intersect %1 %4) (intersect %3 %4) true)

  ; Specific for time-of-day, to help resolve ambiguities

  "dalle <time-of-day> alle <time-of-day> (interval)"
  [#"(?i)da(ll[ae'])?" {:form :time-of-day} #"\-|([fs]ino )?a(ll[ae'])?" {:form :time-of-day}]
  (interval %2 %4 true)

  ; Specific for within duration... Would need to be reworked
  "in <duration>"
  [#"(?i)in|per|entro" (dim :duration)]
  (interval (cycle-nth :second 0) (in-duration (:value %2)) false)

  "entro il <duration>"
  [#"(?i)(entro|durante|per( tutt[ao])?) (il|l[a'])|in|nel(l[a'])?" (dim :unit-of-duration)]
  (interval (cycle-nth :second 0) (cycle-nth (:grain %2) 1) false)

  ; One-sided Intervals

  "entro le <time-of-day>"
  [#"(?i)entro( l[ea'])?|prima d(i|ell['ea])" {:form :time-of-day}]
  (merge %2 {:direction :before})

  "entro <time>"
  [#"(?i)entro( la)?|prima d(i|ella)" (dim :time)]
  (merge %2 {:direction :before})

  "entro il <integer>"
  [#"(?i)entro il|prima del" (integer 1 31)]
  (merge (day-of-month (:value %2)) {:direction :before})

  "dopo le <time-of-day>"
  [#"(?i)dopo( l['ea])?|dal(l['ea])?" {:form :time-of-day}]
  (merge %2 {:direction :after})

  "dopo <time>"
  [#"(?i)dopo|dal?" (dim :time)]
  (merge %2 {:direction :after})

  "dal <integer>"
  [#"(?i)dal" (integer 1 31)]
  (merge (day-of-month (:value %2)) {:direction :after})

  "<time> dopo le <time-of-day>"
  [(dim :time) #"(?i)dopo l[e']|dall['e]" {:form :time-of-day}]
  (merge (intersect %1 %3) {:direction :after})

  "<time> entro le <time-of-day>"
  [(dim :time) #"(?i)entro( l[e'])?|prima d(i|ell['e])" (dim :time)]
  (merge (intersect %1 %3) {:direction :before})
)
