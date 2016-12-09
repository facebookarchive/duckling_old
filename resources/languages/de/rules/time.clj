(
  ;; generic

  "intersect"
  [(dim :time #(not (:latent %))) (dim :time #(not (:latent %)))] ; sequence of two tokens with a time dimension
  (intersect %1 %2)

  ; same thing, with "of" in between like "Sunday of last week"
  "intersect by 'of', 'from', 's"
  [(dim :time #(not (:latent %))) #"(?i)von|der|im" (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)

  ; mostly for January 12, 2005
  ; this is a separate rule, because commas separate very specific tokens
  ; so we want this rule's classifier to learn this
  "intersect by ','"
  [(dim :time #(not (:latent %))) #"," (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)

  "on <date>" ; on Wed, March 23
  [#"(?i)am" (dim :time)]
  %2 ; does NOT dissoc latent

  "on a named-day" ; on a sunday
  [#"(?i)an einem" {:form :day-of-week}]
  %2 ; does NOT dissoc latent

  ;;;;;;;;;;;;;;;;;;;
  ;; Named things

  "named-day"
  #"(?i)montags?|mo\.?"
  (day-of-week 1)

  "named-day"
  #"(?i)die?nstags?|di\.?"
  (day-of-week 2)

  "named-day"
  #"(?i)mittwochs?|mi\.?"
  (day-of-week 3)

  "named-day"
  #"(?i)donn?erstags?|do\.?"
  (day-of-week 4)

  "named-day"
  #"(?i)freitags?|fr\.?"
  (day-of-week 5)

  "named-day"
  #"(?i)samstags?|sa\.?"
  (day-of-week 6)

  "named-day"
  #"(?i)sonntags?|so\.?"
  (day-of-week 7)

  "named-month"
  #"(?i)januar|jan\.?"
  (month 1)

  "named-month"
  #"(?i)februar|feb\.?"
  (month 2)

  "named-month"
  #"(?i)märz|mär\.?"
  (month 3)

  "named-month"
  #"(?i)april|apr\.?"
  (month 4)

  "named-month"
  #"(?i)mai"
  (month 5)

  "named-month"
  #"(?i)juni|jun\.?"
  (month 6)

  "named-month"
  #"(?i)juli|jul\.?"
  (month 7)

  "named-month"
  #"(?i)august|aug\.?"
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
  #"(?i)dezember|dez\.?"
  (month 12)

  ; Holiday TODO: check online holidays
  ; or define dynamic rule (last thursday of october..)

  "christmas"
  #"(?i)weih?nacht(en|stag)?"
  (month-day 12 25)

  "christmas eve"
  #"(?i)heilig(er)? abend"
  (month-day 12 24)

  "new year's eve"
  #"(?i)silvester"
  (month-day 12 31)

  "new year's day"
  #"(?i)neujahr(s?tag)?"
  (month-day 1 1)

  "valentine's day"
  #"(?i)valentin'?stag"
  (month-day 2 14)

  "Tag der Deutschen Einheit"
  #"(?i)tag (der)? deutsc?hen? einheit"
  (month-day 10 3)

  "Österreichischer Nationalfeiertag"
  #"(österreichischer?)? nationalfeiertag|national feiertag"
  (month-day 10 26)

  "Schweizer Bundesfeiertag"
  #"(?i)schweiz(er)? (bundes)?feiertag|bundes feiertag"
  (month-day 8 1)

  "Father's Day";third Sunday of June
  #"(?i)vatt?ertag|vatt?er (tag)?"
  (intersect (day-of-week 7) (month 6) (cycle-nth-after :week 2 (month-day 6 1)))

  "Mother's Day";second Sunday in May.
  #"(?i)mutt?ertag|mutt?er (tag)?"
  (intersect (day-of-week 7) (month 5) (cycle-nth-after :week 1 (month-day 5 1)))

  "halloween day"
  #"(?i)hall?owe?en?"
  (month-day 10 31)

  "Allerheiligen"
  #"(?i)allerheiligen?|aller heiligen?"
  (month-day 11 1)

  "Nikolaus"
  #"(?i)nikolaus(tag)?|nikolaus tag|nikolo"
  (month-day 12 6)

  "absorption of , after named day"
  [{:form :day-of-week} #","]
  %1

  "now"
  #"(?i)(genau)? ?jetzt|diesen moment|in diesem moment|gerade eben"
  (cycle-nth :second 0)

  "today"
  #"(?i)heute|(um diese zeit|zu dieser zeit|um diesen zeitpunkt|zu diesem zeitpunkt)"
  (cycle-nth :day 0)

  "tomorrow"
  #"(?i)morgen"
  (cycle-nth :day 1)

  "after tomorrow"
  #"(?i)übermorgen"
  (cycle-nth :day 2)

  "yesterday"
  #"(?i)gestern"
  (cycle-nth :day -1)

  "before yesterday"
  #"(?i)vorgestern"
  (cycle-nth :day -2)

  "EOM|End of month"
  #"(?i)(das )?ende des monats?"
  (cycle-nth :month 1)

  "EOY|End of year"
  #"(?i)(das )?(EOY|jahr(es)? ?ende|ende (des )?jahr(es)?)"
  (cycle-nth :year 1)

  ;;
  ;; This, Next, Last

  ;; assumed to be strictly in the future:
  ;; "this Monday" => next week if today in Monday
  "this|next <day-of-week>"
  [#"(?i)diese(n|r)|kommenden|nächsten" {:form :day-of-week}]
  (pred-nth-not-immediate %2 0)

  ;; for other preds, it can be immediate:
  ;; "this month" => now is part of it
  ; See also: cycles in en.cycles.clj
  "this <time>"
  [#"(?i)diese(n|r|s)?|(im )?laufenden" (dim :time)]
  (pred-nth %2 0)

  "next <time>"
  [#"(?i)nächsten?|nächstes|kommenden?|kommendes" (dim :time)]
  (pred-nth-not-immediate %2 0)

  "last <time>"
  [#"(?i)letzten?|letztes" (dim :time)]
  (pred-nth %2 -1)

  "after next <time>"
  [#"(?i)übernächsten?|über ?nächstes?" (dim :time)]
  (pred-nth-not-immediate %2 1)

  "<time> after next"
  [ (dim :time) #"(?i)nach dem nächsten"]
  (pred-nth-not-immediate %1 1)

  "<time> before last"
  [#"(?i)vorletzten?|vor ?letztes?" (dim :time)]
  (pred-nth %2 -2)

  "last <day-of-week> of <time>"
  [#"(?i)letzte(r|n|s)?" {:form :day-of-week} #"(?i)um|im" (dim :time)];Check me OF
  (pred-last-of %2 %4)

  "last <cycle> of <time>"
  [#"(?i)letzte(r|n|s)?" (dim :cycle) #"(?i)um|im" (dim :time)];Check me OF
  (cycle-last-of %2 %4)

  ; Ordinals
  "nth <time> of <time>"
  [(dim :ordinal) (dim :time) #"(?i)im" (dim :time)];Check me OF
  (pred-nth (intersect %4 %2) (dec (:value %1)))

  "nth <time> of <time>"
  [#"(?i)der|die|das" (dim :ordinal) (dim :time) #"(?i)im" (dim :time)];Check me OF
  (pred-nth (intersect %5 %3) (dec (:value %2)))

  "nth <time> after <time>"
  [(dim :ordinal) (dim :time) #"(?i)nach" (dim :time)];CHeck me
  (pred-nth-after %2 %4 (dec (:value %1)))

  "nth <time> after <time>"
  [#"(?i)der|das" (dim :ordinal) (dim :time) #"(?i)nach" (dim :time)];Check me
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
  [#"(?i)der" (dim :ordinal #(<= 1 (:value %) 31))]
  (day-of-month (:value %2))

  "<day-of-month> (ordinal)" ; this one is latent
  [(dim :ordinal #(<= 1 (:value %) 31))]
  (assoc (day-of-month (:value %1)) :latent true)

  "the <day-of-month> (non ordinal)" ; this one is latent
  [#"(?i)der" (integer 1 31)]
  (assoc (day-of-month (:value %2)) :latent true)

  "<named-month> <day-of-month> (ordinal)" ; march 12th
  [{:form :month} (dim :ordinal #(<= 1 (:value %) 31))]
  (intersect %1 (day-of-month (:value %2)))

  "<named-month> <day-of-month> (non ordinal)" ; march 12
  [{:form :month} (integer 1 31)]
  (intersect %1 (day-of-month (:value %2)))

  "<day-of-month> (non ordinal) of <named-month>"
  [(integer 1 31) #"(?i)vom|von" {:form :month}];Check me DO WE NEED THIS
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
  [#"(?i)die iden (des?)" {:form :month}]
  (intersect %2 (day-of-month (if (#{3 5 7 10} (:month %2)) 15 13)))


   ; Hours and minutes (absolute time)
  ;
  ; Assumptions:
  ; - 0 is midnight
  ; - 1..11 is ambiguous am or pm
  ; - 12 is noon (whereas in English it is ambiguous)
  ; - 13..23 is pm

  "time-of-day (latent)"
  (integer 0 23)
  (assoc (hour (:value %1) (< (:value %1) 12)) :latent true)

  "<time-of-day>  o'clock"
  [#(:full-hour %) #"(?:(?i)uhr|h)(?:\p{P}|\p{Z}|$)"]
  (dissoc %1 :latent)

  "at <time-of-day>" ; absorption
  [#"(?i)um|@" {:form :time-of-day}]
  (dissoc %2 :latent)

  "hh:mm"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))[:.]([0-5]\d)(?:(?i)uhr|h)?"
  (-> (hour-minute (Integer/parseInt (first (:groups %1)))
                   (Integer/parseInt (second (:groups %1)))
                   false)
      (assoc :form :time-of-day))

  "hhmm (military)"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))([0-5]\d)"
  (-> (hour-minute (Integer/parseInt (first (:groups %1)))
                   (Integer/parseInt (second (:groups %1)))
                   false) ; not a 12-hour clock)
      (assoc :latent true))

  "hhmm (military) am|pm" ; hh only from 00 to 12
  [#"(?i)((?:1[012]|0?\d))([0-5]\d)" #"(?i)([ap])\.?m\.?(?:\p{P}|\p{Z}|$)"]
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
  [{:form :time-of-day} #"(?i)([ap])\.?m\.?(?:\p{P}|\p{Z}|$)"];Check me DO WE NEED THIS
  ;; TODO set_am fn in helpers => add :ampm field
  (let [[p meridiem] (if (= "a" (-> %2 :groups first clojure.string/lower-case))
                       [[(hour 0) (hour 12) false] :am]
                       [[(hour 12) (hour 0) false] :pm])]
    (-> (intersect %1 (apply interval p))
        (assoc :form :time-of-day)))

  "noon"
  #"(?i)mittags?|zwölf (uhr)?"
  (hour 12 false)

  "midnight|EOD|end of day"
  #"(?i)mitternacht|EOD|tagesende|ende (des)? tag(es)?"
  (hour 0 false)

  "quarter (relative minutes)"
  #"(?i)vie?rtel"
  {:relative-minutes 15}

  "half (relative minutes)"
  #"halbe?"
  {:relative-minutes 30}

  "number (as relative minutes)"
  (integer 1 59)
  {:relative-minutes (:value %1)}

  "<hour-of-day> <integer> (as relative minutes)"
  [(dim :time :full-hour) #(:relative-minutes %)]
  (hour-relativemin (:full-hour %1) (:relative-minutes %2) true)

  "relative minutes to|till|before <integer> (hour-of-day)"
  [#(:relative-minutes %) #"(?i)vor" (dim :time :full-hour)]
  (hour-relativemin (:full-hour %3) (- (:relative-minutes %1)) true)

  "relative minutes after|past <integer> (hour-of-day)"
  [#(:relative-minutes %) #"(?i)nach" (dim :time :full-hour)]
  (hour-relativemin (:full-hour %3) (:relative-minutes %1) true)

  "half <integer> (german style hour-of-day)"
  [#"halb" (dim :time :full-hour)];Check me CHANGE CODE TO MATCH GERMAN USAGE
  (hour-relativemin (:full-hour %2) -30 true)


  ; Formatted dates and times

  "mm/dd/yyyy"
  #"([012]?[1-9]|10|20|30|31)\.(0?[1-9]|10|11|12)\.(\d{2,4})"
  (parse-dmy (first (:groups %1)) (second (:groups %1)) (nth (:groups %1) 2) true)

  "yyyy-mm-dd"
  #"(\d{2,4})-(0?[1-9]|10|11|12)-([012]?[1-9]|10|20|30|31)"
  (parse-dmy (nth (:groups %1) 2) (second (:groups %1)) (first (:groups %1)) true)

  "mm/dd"
  #"([012]?[1-9]|10|20|30|31)\.(0?[1-9]|10|11|12)\."
  (parse-dmy (first (:groups %1)) (second (:groups %1)) nil true)


  ; Part of day (morning, evening...). They are intervals.

  "morning" ;; TODO "3am this morning" won't work since morning starts at 4...
  [#"(?i)morgens|(in der )?früh|vor ?mittags?|am morgen"]
  (assoc (interval (hour 3 false) (hour 12 false) false) :form :part-of-day :latent true)

  "afternoon"
  [#"(?i)nach ?mittags?"]
  (assoc (interval (hour 12 false) (hour 19 false) false) :form :part-of-day :latent true)


  "evening"
  [#"(?i)abends?"]
  (assoc (interval (hour 18 false) (hour 0 false) false) :form :part-of-day :latent true)


  "night"
  [#"(?i)nachts?"]
  (assoc (interval (hour 0 false) (hour 4 false) false) :form :part-of-day :latent true)

  "lunch"
  [#"(?i)(am |zu )?mittags?"]
  (assoc (interval (hour 12 false) (hour 14 false) false) :form :part-of-day :latent true)

  "in|during the <part-of-day>" ;; removes latent
  [#"(?i)(in|an|am|wäh?rend)( der| dem| des)?" {:form :part-of-day}]
  (dissoc %2 :latent)

  "this <part-of-day>"
  [#"(?i)diesen?|dieses|heute" {:form :part-of-day}]
  (assoc (intersect (cycle-nth :day 0) %2) :form :part-of-day) ;; removes :latent

  "tonight"
  #"(?i)heute? (am)? abends?"
  (assoc (intersect (cycle-nth :day 0)
                    (interval (hour 18 false) (hour 0 false) false))
         :form :part-of-day) ; no :latent

  "after lunch"
  #"(?i)nach dem mittagessen|nachmittags?"
  (assoc (intersect (cycle-nth :day 0)
                    (interval (hour 13 false) (hour 17 false) false))
         :form :part-of-day) ; no :latent


  "after work"
  #"(?i)nach (der)? arbeit|(am)? feier ?abend"
  (assoc (intersect (cycle-nth :day 0)
                    (interval (hour 17 false) (hour 21 false) false))
         :form :part-of-day) ; no :latent

  "<time> <part-of-day>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [(dim :time) {:form :part-of-day}]
  (intersect %2 %1)

  "<part-of-day> of <time>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [{:form :part-of-day} #"(?i)des|von|vom|am" (dim :time)];Check me
  (intersect %1 %3)


  ; Other intervals: week-end, seasons

  "week-end" ; from Friday 6pm to Sunday midnight
  #"(?i)wochen ?ende?"
  (interval (intersect (day-of-week 5) (hour 18 false))
            (intersect (day-of-week 1) (hour 0 false))
            false)

  "season"
  #"(?i)sommer" ;could be smarter and take the exact hour into account... also some years the day can change
  (interval (month-day 6 21) (month-day 9 23) false)

  "season"
  #"(?i)herbst"
  (interval (month-day 9 23) (month-day 12 21) false)

  "season"
  #"(?i)winter"
  (interval (month-day 12 21) (month-day 3 20) false)

  "season"
  #"(?i)frühling|frühjahr"
  (interval (month-day 3 20) (month-day 6 21) false)


  ; Time zones

  "timezone"
  #"(?i)(YEKT|YEKST|YAPT|YAKT|YAKST|WT|WST|WITA|WIT|WIB|WGT|WGST|WFT|WEZ|WET|WESZ|WEST|WAT|WAST|VUT|VLAT|VLAST|VET|UZT|UYT|UYST|UTC|ULAT|TVT|TMT|TLT|TKT|TJT|TFT|TAHT|SST|SRT|SGT|SCT|SBT|SAST|SAMT|RET|PYT|PYST|PWT|PT|PST|PONT|PMST|PMDT|PKT|PHT|PHOT|PGT|PETT|PETST|PET|PDT|OMST|OMSST|NZST|NZDT|NUT|NST|NPT|NOVT|NOVST|NFT|NDT|NCT|MYT|MVT|MUT|MST|MSK|MSD|MMT|MHT|MEZ|MESZ|MDT|MAWT|MART|MAGT|MAGST|LINT|LHST|LHDT|KUYT|KST|KRAT|KRAST|KGT|JST|IST|IRST|IRKT|IRKST|IRDT|IOT|IDT|ICT|HOVT|HNY|HNT|HNR|HNP|HNE|HNC|HNA|HLV|HKT|HAY|HAT|HAST|HAR|HAP|HAE|HADT|HAC|HAA|GYT|GST|GMT|GILT|GFT|GET|GAMT|GALT|FNT|FKT|FKST|FJT|FJST|ET|EST|EGT|EGST|EET|EEST|EDT|ECT|EAT|EAST|EASST|DAVT|ChST|CXT|CVT|CST|COT|CLT|CLST|CKT|CHAST|CHADT|CET|CEST|CDT|CCT|CAT|CAST|BTT|BST|BRT|BRST|BOT|BNT|AZT|AZST|AZOT|AZOST|AWST|AWDT|AST|ART|AQTT|ANAT|ANAST|AMT|AMST|ALMT|AKST|AKDT|AFT|AEST|AEDT|ADT|ACST|ACDT)"
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
  [{:form :time-of-day} #"(?i)(um )?zirka|ungefähr|etwa"]
  (-> %1
    (dissoc :latent)
    (merge {:precision "approximate"}));Check me NO TRANSLATION NECESSARY

  "<time-of-day> sharp" ; sharp
  [{:form :time-of-day} #"(?i)genau|exakt|pünktlich|punkt( um)?"]
  (-> %1
    (dissoc :latent)
    (merge {:precision "exact"}));Check me NO TRANSLATION NECESSARY

  "about <time-of-day>" ; about
  [#"(?i)(um )?zirka|ungefähr|etwa" {:form :time-of-day}]
  (-> %2
    (dissoc :latent)
    (merge {:precision "approximate"}));Check me NO TRANSLATION NECESSARY

  "exactly <time-of-day>" ; sharp
  [#"(?i)genau|exakt|pünktlich|punkt( um)?" {:form :time-of-day}]
  (-> %2
    (dissoc :latent)
    (merge {:precision "exact"}));Check me NO TRANSLATION NECESSARY


  ; Intervals

  "<month> dd-dd (interval)"
  [ #"([012]?\d|30|31)(ter|\.)?" #"\-|bis" #"([012]?\d|30|31)(ter|\.)?" {:form :month}]
  (interval (intersect %4 (day-of-month (Integer/parseInt (-> %1 :groups first))))
            (intersect %4 (day-of-month (Integer/parseInt (-> %3 :groups first))))
            true)

  ; Blocked for :latent time. May need to accept certain latents only, like hours

  "<datetime> - <datetime> (interval)"
  [(dim :time #(not (:latent %))) #"\-|bis" (dim :time #(not (:latent %)))]
  (interval %1 %3 true)

  "from <datetime> - <datetime> (interval)"
  [#"(?i)vo[nm]" (dim :time) #"\-|bis" (dim :time)]
  (interval %2 %4 true)

  "between <datetime> and <datetime> (interval)"
  [#"(?i)zwischen" (dim :time) #"und" (dim :time)]
  (interval %2 %4 true)

  ; Specific for time-of-day, to help resolve ambiguities

  "<time-of-day> - <time-of-day> (interval)"
  [#(and (= :time-of-day (:form %)) (not (:latent %))) #"\-|bis" {:form :time-of-day}] ; Prevent set alarm 1 to 5pm
  (interval %1 %3 true)

  "from <time-of-day> - <time-of-day> (interval)"
  [#"(?i)(von|nach|ab|frühestens (um)?)" {:form :time-of-day} #"((noch|aber|jedoch)? vor)|\-|bis" {:form :time-of-day}]
  (interval %2 %4 true)

  "between <time-of-day> and <time-of-day> (interval)"
  [#"(?i)zwischen" {:form :time-of-day} #"und" {:form :time-of-day}]
  (interval %2 %4 true)

  ; Specific for within duration... Would need to be reworked
  "within <duration>"
  [#"(?i)binnen|innerhalb( von)?" (dim :duration)]
  (interval (cycle-nth :second 0) (in-duration (:value %2)) false)


  "by the end of <time>"; in this case take the end of the time (by the end of next week = by the end of next sunday)
  [#"(?i)bis (zum)? ende (von)?|(noch)? vor" (dim :time)];Check me CODE OK?
  (interval (cycle-nth :second 0) %2 true)

  ; One-sided Intervals

  "until <time-of-day>"
  [#"(?i)vor|bis( zu[rm]?)?" (dim :time)]
  (merge %2 {:direction :before})

  "after <time-of-day>"
  [#"(?i)nach" (dim :time)]
  (merge %2 {:direction :after}))

  ; ;; In this special case, the upper limit is exclusive
  ; "<hour-of-day> - <hour-of-day> (interval)"
  ; [{:form :time-of-day} #"-|to|th?ru|through|until" #(and (= :time-of-day (:form %))
  ; 									  (not (:latent %)))]
  ; (interval %1 %3 :exclusive)

  ; "from <hour-of-day> - <hour-of-day> (interval)"
  ; [#"(?i)from" {:form :time-of-day} #"-|to|th?ru|through|until" #(and (= :time-of-day (:form %))
  ; 									              (not (:latent %)))]
  ; (interval %2 %4 :exclusive)

  ; "time => time2 (experiment)"
  ; (dim :time)
  ; (assoc %1 :dim :time2)
