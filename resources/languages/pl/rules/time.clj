(
  ;; generic

  "intersect"
  [(dim :time #(not (:latent %))) (dim :time #(not (:latent %)))] ; sequence of two tokens with a time dimension
  (intersect %1 %2)

  ; same thing, with "of" in between like "Sunday of last week"
  "intersect by \"of\", \"from\", \"'s\""
  [(dim :time #(not (:latent %))) #"(?i)z" (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)

  ; mostly for January 12, 2005
  ; this is a separate rule, because commas separate very specific tokens
  ; so we want this rule's classifier to learn this
  "intersect by \",\""
  [(dim :time #(not (:latent %))) #"," (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)

  "on <date>" ; on Wed, March 23
  [#"(?i)we?" (dim :time)]
  %2 ; does NOT dissoc latent

  "on a named-day" ; on a sunday
  [#"(?i)we?" {:form :day-of-week}]
  %2 ; does NOT dissoc latent


  ;;;;;;;;;;;;;;;;;;;
  ;; Named things

  "named-day"
  #"(?i)poniedzia(l|ł)(ek|ku|kowi|kiem|kowy)|pon\.?"
  (day-of-week 1)

  "named-day"
  #"(?i)wtorek|wtorku|wtorkowi|wtorkiem|wtr?\.?"
  (day-of-week 2)

  "named-day"
  #"(?i)(Ś|ś|s)rod(a|ą|y|e|ę|zie|owy|o)|(s|ś|Ś)ro?\.?"
  (day-of-week 3)

  "named-day"
  #"(?i)czwartek|czwartku|czwartkowi|czwartkiem|czwr?\.?"
  (day-of-week 4)

  "named-day"
  #"(?i)piątek|piatek|piątku|piatku|piątkowi|piatkowi|piątkiem|piatkiem|pi(ą|a)tkowy|pia\.?"
  (day-of-week 5)

  "named-day"
  #"(?i)sobota|soboty|sobocie|sobotę|sobote|sobotą|sobota|sobocie|soboto|sob\.?"
  (day-of-week 6)

  "named-day"
  #"(?i)niedziela|niedzieli|niedzielę|niedziele|niedziela|niedzielą|niedzieli|niedzielo|nied?z?\.?"
  (day-of-week 7)

  "named-month"
 #"(?i)styczeń|styczen|stycznia|styczniowi|styczniem|styczniu|sty(cz)?\.?"
  (month 1)

  "named-month"
  #"(?i)luty|lutego|lutemu|lut?\.?"
  (month 2)

  "named-month"
  #"(?i)marzec|marca|marcowi|marcem|marcu|marz?\.?"
  (month 3)

  "named-month"
  #"(?i)kwiecień|kwiecien|kwietnia|kwietniowi|kwietniem|kwietniu|kwiet?\.?"
  (month 4)

  "named-month"
  #"(?i)maj|maja|majowi|majem|maju"
  (month 5)

  "named-month"
  #"(?i)czerwiec|czerwca|czerwcowi|czerwcem|czerwcu|czer?\.?"
  (month 6)

  "named-month"
  #"(?i)lipiec|lipca|lipcowi|lipcem|lipcu|lip\.?"
  (month 7)

  "named-month"
  #"(?i)sierpień|sierpien|sierpnia|sierpniowi|sierpniem|sierpniu|sierp\.?|sier\.?|sie\.?"
  (month 8)

  "named-month"
  #"(?i)wrzesień|wrzesien|września|wrzesnia|wrześniowi|wrzesniowi|wrzesień|wrzesien|wrześniem|wrzesniem|wrześniu|wrzesniu|wrz\.?|wrze\.?"
  (month 9)

  "named-month"
  #"(?i)pa(z|ź)dziernik(a|owi|iem|u)?|paź\.?|paz\.?"
  (month 10)

  "named-month"
  #"(?i)listopad|listopada|listopadowi|listopadem|listopadzie|lis\.?|list\.?"
  (month 11)

  "named-month"
  #"(?i)grudzień|grudzien|grudnia|grudniowi|grudniem|grudniu|gru\.?|grud\.?"
  (month 12)

  ; Holiday TODO: check online holidays
  ; or define dynamic rule (last thursday of october..)

  "christmas"
  #"(?i)((Ś|ś|s)wi(e|ę)ta)? ?bo(z|ż)(ym|ego|e) narodzeni(e|a|u)"
 (month-day 12 25)

  "christmas eve"
  #"(?i)(wigilia|wigilii|wigili(e|ę)|wigili(a|ą)|wigilio) ?(bo(z|ż)ego narodzenia)?"
  (month-day 12 24)

  "new year's eve"
  #"(?i)sylwester|nowy rok"
  (month-day 12 31)

  "valentine's day"
  #"(?i)walentynki"
  (month-day 2 14)

  ;; "MLK Day";third Monday of January
  ;;  #"(?i)(MLK|Martin Luther King,?)( Jr.?| Junior)? day"
  ;; (intersect (day-of-week 1) (month 1) (cycle-nth-after :week 3 (month-day 1 1)))

  ;; "memorial day" ;the last Monday of May
  ;; #"(?i)memorial day"
  ;; (pred-last-of (day-of-week 1) (month 5))

  ;; "memorial day weekend" ;the weekend leading to the last Monday of May
  ;; #"(?i)memorial day week(\s|-)?end"
  ;; (interval (intersect (cycle-nth-after :day -3 (pred-last-of (day-of-week 1) (month 5))) (hour 18 false))
  ;;           (intersect (pred-last-of (day-of-week 2) (month 5)) (hour 0 false)) ;need to use Tuesday to include monday
  ;;           false)

  "Polish independence day"
  #"(?i)(s|ś)wiet(a|o) niepodleg(l|ł)o(s|ś)ci|(ś|s)w\.? niepodleg(l|ł)o(s|ś)ci"
  (month-day 11 11)

  ;; "labor day" ;first Monday in September
  ;; #"(?i)labor day"
  ;; (intersect (month 9) (day-of-week 1))

  ;; "labor day weekend" ;weekend before 1st Monday in September
  ;; #"(?i)labor day week(\s|-)?end"
  ;; (interval (intersect (cycle-nth-after :day -3 (intersect (day-of-week 1) (month 9))) (hour 18 false))
  ;;           (intersect (month 9) (day-of-week 2) (hour 0 false)) ;need to use Tuesday to include monday
  ;;           true)

  "Father's Day";third Sunday of June
  #"(?i)dzie(n|ń) ?(taty|ojca)"
  (intersect (day-of-week 7) (month 6) (cycle-nth-after :week 2 (month-day 6 1)))

  "Mother's Day";second Sunday in May.
  #"(?i)dzie(n|ń) ? ma(my|tki|m)"
  (intersect (day-of-week 7) (month 5) (cycle-nth-after :week 1 (month-day 5 1)))

  "halloween day"
  #"(?i)hall?owe?en( day)?"
  (month-day 10 31)

  "thanksgiving day" ; fourth Thursday of November
  #"(?i)((s|ś)wiet(a|o)|(dzie(n|ń)))? ?dzi(e|ę)kczynieni(e|a)"
  (intersect (day-of-week 4) (month 11) (cycle-nth-after :week 4 (month-day 11 1)))

  ;; "black friday"; (the fourth Friday of November),
  ;; #"(?i)black frid?day"
  ;; (intersect (day-of-week 5) (month 11) (cycle-nth-after :week 4 (month-day 11 1)))

  "absorption of , after named day"
  [{:form :day-of-week} #","]
  %1

  "now"
  #"(?i)(w)? ?(tym|tej)? ?(teraz|momencie|chwili|momeńcie)"
  (cycle-nth :second 0)

  "today"
  #"(?i)dzisiejszy|dzisiaj|dziś|dzis|w ten dzień|w ten dzien|w obecny dzień|w obecny dzien|obecnego dnia"
  (cycle-nth :day 0)

  "tomorrow"
  #"(?i)jutr(o|a|u|em|ze(jszy|jsza)?)|jtr|jutr"
 (cycle-nth :day 1)

 "day-after-tomorrow (single-word)"
 #"(?i)(po ?jutr(o|ze))"
 (cycle-nth :day 2)

 "day-before-yesterday (single-word)"
 #"(?i)przedwczoraj"
 (cycle-nth :day 2)

  "yesterday"
  #"(?i)wczoraj(szym|szy)?|wczrj|wczor"
  (cycle-nth :day -1)

  "EOM|End of month"
  #"(?i)(na |w )?(koniec|ko(n|ń)ca|ko(n|ń)cu|ko(n|ń)cowi|ko(n|ń)cem|ko(n|ń)c(o|ó)wke) (miesi(a|ą)ca|msc)"
  (cycle-nth :month 1)

  "EOY|End of year"
  #"(?i)(na |w )?(koniec|ko(n|ń)ca|ko(n|ń)cu|ko(n|ń)cowi|ko(n|ń)cem|ko(n|ń)c(o|ó)wke) (rok(u|owi|iem))"
  (cycle-nth :year 1)

  ;;
  ;; This, Next, Last

  ;; assumed to be strictly in the future:
  ;; "this Monday" => next week if today is Monday
  "this|next <day-of-week>"
  [#"(?i)kolejn(ym|y|ego|emu|e)|nast(e|ę)pn(ym|y|ego|emu|e|(a|ą)|ej|e)" {:form :day-of-week}]
  (pred-nth-not-immediate %2 0)

  ;; for other preds, it can be immediate:
  ;; "this month" => now is part of it
  ; See also: cycles in en.cycles.clj
  "this <time>"
  [#"(?i)te(mu|n|go|j)|ta|to|tym|nadchodz(a|ą)c(ym|y|ego|emu|(a|ą)|ej)" (dim :time)]
  (pred-nth %2 0)

  "next <time>"
  [#"(?i)kolejn(ym|y|ego|emu|(a|ą)|ej|e)|nast(e|ę)pn(ym|y|ego|emu|e|(a|ą)|ej|e)|przysz(l|ł)(ego|emu|ym|(a|ą)|ej|ych|i|ymi|y|e)" (dim :time #(not (:latent %)))]
  (pred-nth-not-immediate %2 0)

  "last <time>"
  [#"(?i)ostatni(ego|ch|emu|mi|m|(a|ą)|ej)?|(po ?)?przedni(ego|ch|emu|e|mi|m|a)?" (dim :time)]
  (pred-nth %2 -1)

  "<time> after next"
  [(dim :time) #"(?i)po kolejnym|po nast(e|ę)pn(ym|y|ego|emu|(a|ą)|ej|e)|po przysz(l|ł)(ym|y|ego|emu|(a|ą)|ej)"]
  (pred-nth-not-immediate %1 1)

   "<time> before last"
  [(dim :time) #"(?i)przed ?ostatni(ego|ch|emu|mi|m|(a|ą)|ej)?"]
  (pred-nth %1 -2)

  "last <day-of-week> of <time>"
  [#"(?i)ostatni(ego|ch|emu|mi|m|(a|ą)|ej)?" {:form :day-of-week} #"(?i)w(e)?|z(e)?" (dim :time)]
  (pred-last-of %2 %4)

  "last <day-of-week> <time>"
  [#"(?i)ostatni(ego|ch|emu|mi|m|(a|ą)|ej)?" {:form :day-of-week} (dim :time)]
  (pred-last-of %2 %3)

 "last <cycle> of <time>"
 [#"(?i)ostatni(ego|ch|emu|mi|m|(a|ą)|ej)?" (dim :cycle) #"(?i)w(e)?|z(e)?" (dim :time)]
 (cycle-last-of %2 %4)

 "last <cycle> <time>"
 [#"(?i)ostatni(ego|ch|emu|mi|m|(a|ą)|ej)?" (dim :cycle) (dim :time)]
 (cycle-last-of %2 %3)

  ; Ordinals
 "nth <time> of <time>"
 [(dim :ordinal) (dim :time) #"(?i)w(e)?|z(e)?" (dim :time)]
 (pred-nth (intersect %4 %2) (dec (:value %1)))

 "nth <time> <time>"
 [(dim :ordinal) (dim :time) (dim :time)]
 (pred-nth (intersect %3 %2) (dec (:value %1)))

  ;; "nth <time> of <time>"
  ;; [#"(?i)the" (dim :ordinal) (dim :time) #"(?i)of|in" (dim :time)]
  ;; (pred-nth (intersect %5 %3) (dec (:value %2)))

  "nth <time> after <time>"
  [(dim :ordinal) (dim :time) #"(?i)po" (dim :time)]
  (pred-nth-after %2 %4 (dec (:value %1)))

  ;; "nth <time> after <time>"
  ;; [#"(?i)the" (dim :ordinal) (dim :time) #"(?i)after" (dim :time)]
  ;; (pred-nth-after %3 %5 (dec (:value %2)))

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

  ;; "the <day-of-month> (ordinal)" ; this one is not latent
  ;; [#"(?i)the" (dim :ordinal #(<= 1 (:value %) 31))]
  ;; (day-of-month (:value %2))

  "<day-of-month> (ordinal)" ; this one is latent
  [(dim :ordinal #(<= 1 (:value %) 31))]
  (assoc (day-of-month (:value %1)) :latent true)

  ;; "the <day-of-month> (non ordinal)" ; this one is latent
  ;; [#"(?i)the" (integer 1 31)]
  ;; (assoc (day-of-month (:value %2)) :latent true)

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

  "the ides of <named-month>" ; the ides of march 13th for most months, but on the 15th for March, May, July, and October
  [#"(?i)the ides? of" {:form :month}]
  (intersect %2 (day-of-month (if (#{3 5 7 10} (:month %2)) 15 13)))

  ;; Hours and minutes (absolute time)
  "<integer> (latent time-of-day)"
  (integer 0 23)
  (assoc (hour (:value %1) true) :latent true)

  "at <time-of-day>" ; o godzinie
  [#"(?i)o|na|@" {:form :time-of-day}]
  (dissoc %2 :latent)


  "<time-of-day> o'clock"
  [{:form :time-of-day} #"(?i)godzina"]
  (dissoc %1 :latent)

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


  "hhmm (military)"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))([0-5]\d)"
  (-> (hour-minute (Integer/parseInt (first (:groups %1)))
                   (Integer/parseInt (second (:groups %1)))
                   false) ; not a 12-hour clock)
      (assoc :latent true))

  "hhmm (military) am|pm" ; hh only from 00 to 12
  [#"(?i)((?:1[012]|0?\d))([0-5]\d)" #"(?i)([ap])\.?m?\.?"]
  ; (-> (hour-minute (Integer/parseInt (first (:groups %1)))
  ;                  (Integer/parseInt (second (:groups %1)))
  ;                  false) ; not a 12-hour clock)
  ;     (assoc :latent true))
  (let [[p meridiem] (if (= "a" (-> %2 :groups first .toLowerCase))
                       [[(hour 0) (hour 12) false] :am]
                       [[(hour 12) (hour 0) false] :pm])]
    (-> (intersect
          (hour-minute (Integer/parseInt (first (:groups %1)))
                       (Integer/parseInt (second (:groups %1)))
                   true)
          (apply interval p))
        (assoc :form :time-of-day)))

  "<time-of-day> rano"
  [{:form :time-of-day} #"(?i)(z )?ran(o|a|u|em)"]
 (let [[p meridiem]
        [[(hour 0) (hour 12) false] :am]]
    (-> (intersect %1 (apply interval p))
        (assoc :form :time-of-day)))

 "<time-of-day> popołudniu/wieczorem/w nocy"
 [{:form :time-of-day} #"(?i)(w )?noc(y|(a|ą))|(po ?)?po(l|ł)udni(em|e|a|u)|wiecz(o|ó)r(i|u|a|owi|em|rze)"]
 (let [[p meridiem]
       [[(hour 12) (hour 0) false] :pm]]
   (-> (intersect %1 (apply interval p))
       (assoc :form :time-of-day)))

 ;;  "<time-of-day> popołudniu/wieczorem/w nocy"
 ;; [{:form :time-of-day} #"(?i)(w )?noc(y|(a|ą))|(po ?)?po(l|ł)udni(em|e|a|u)|wiecz(o|ó)r(i|u|a|owi|em|rze)"]
 ;; (let [[p meridiem]
 ;;       [[(hour 0) (hour 12) true] :pm]]
 ;;   (-> (intersect %1 (apply interval p))
 ;;       (assoc :form :time-of-day)))


 ;; "<ordinal> popołudniu/wieczorem/w nocy"
 ;; [(dim :ordinal #(<= 1 (:value %) 12)) #"(?i)(po ?)?po(l|ł)udni(em|e|a|u)|wiecz(o|ó)r(i|u|a|owi|em|rze)|(w )?noc(y|(a|ą))?"]
 ;; (let [[p meridiem]
 ;;       [[(hour 12) (hour 0) false] :pm]]
 ;;   (-> (intersect (:value %1) (apply interval p))
 ;;       (assoc :form :time-of-day)))

 "<ordinal> (as hour)"
 [(dim :ordinal #(<= 1 (:value %) 24))]
 (hour (:value %1) true)


  ;; "<time-of-day> am|pm"
  ;; [{:form :time-of-day} #"(?i)(in the )?([ap])(\s|\.)?m?\.?"]
  ;; ;; TODO set_am fn in helpers => add :ampm field
  ;; (let [[p meridiem] (if (= "a" (-> %2 :groups second .toLowerCase))
  ;;                      [[(hour 0) (hour 12) false] :am]
  ;;                      [[(hour 12) (hour 0) false] :pm])]
  ;;   (-> (intersect %1 (apply interval p))
  ;;       (assoc :form :time-of-day)))

  "noon"
  #"(?i)po(l|ł)udni(em|e|a|u)" ;;TODO REPLACE
  (hour 12 false)

  ;; "midnight|EOD|end of day"
  ;; #"(?i)północ|polnoc|koniec dnia"
  ;; (hour 0 false)

  "quarter (relative minutes)"
  #"(?i)kwadrans(ie|owi|em|a)?"
  {:relative-minutes 15}

  "half (relative minutes)"
  #"p(o|ó)(l|ł)"
  {:relative-minutes 30}

  "number (as relative minutes)"
  (integer 1 59)
  {:relative-minutes (:value %1)}

  "<hour-of-day> <integer> (as relative minutes)"
  [(dim :time :full-hour) #(:relative-minutes %)]
  (hour-relativemin (:full-hour %1) (:relative-minutes %2) true)

  "relative minutes to|till|before <integer> (hour-of-day)"
  [#(:relative-minutes %) #"(?i)do|przed" (dim :time :full-hour)]
  (hour-relativemin (:full-hour %3) (- (:relative-minutes %1)) true)

  "relative minutes after|past <integer> (hour-of-day)"
  [#(:relative-minutes %) #"(?i)po" (dim :time :full-hour)]
  (hour-relativemin (:full-hour %3) (:relative-minutes %1) true)


 ;;TODO IMPLEMENT W POL DO XX

  ;; "half <integer> (UK style hour-of-day)"
  ;; [#"half" (dim :time :full-hour)]
  ;; (hour-relativemin (:full-hour %2) 30 true)


  ; Formatted dates and times

  "mm/dd/yyyy"
  #"(0?[1-9]|1[0-2])[/-](3[01]|[12]\d|0?[1-9])[-/](\d{2,4})"
  (parse-dmy (second (:groups %1)) (first (:groups %1)) (nth (:groups %1) 2) true)

  "yyyy-mm-dd"
  #"(\d{2,4})-(0?[1-9]|1[0-2])-(3[01]|[12]\d|0?[1-9])"
  (parse-dmy (nth (:groups %1) 2) (second (:groups %1)) (first (:groups %1)) true)

  "mm/dd"
  #"(0?[1-9]|1[0-2])/(3[01]|[12]\d|0?[1-9])"
  (parse-dmy (second (:groups %1)) (first (:groups %1)) nil true)


  ; Part of day (morning, evening...). They are intervals.

  "morning" ;; TODO "3am this morning" won't work since morning starts at 4...
  [#"(?i)rano|poran(ek|ku|ka)|z rana|(s|ś)witem"]
  (assoc (interval (hour 4 false) (hour 12 false) false) :form :part-of-day :latent true)

  "afternoon"
  [#"(?i)po(l|ł)udni(em|e|a|u)"]
  (assoc (interval (hour 12 false) (hour 19 false) false) :form :part-of-day :latent true)

  "evening|night"
  [#"(?i)wiecz(o|ó)r(em|owi|ze|a|u)?|noc(ą)?"]
  (assoc (interval (hour 18 false) (hour 0 false) false) :form :part-of-day :latent true)

  "lunch"
  [#"(?i)(na )?la?u?nc(z|h)|obiad"]
  (assoc (interval (hour 12 false) (hour 14 false) false) :form :part-of-day :latent true)

  "in|during the <part-of-day>" ;; removes latent
  [#"(?i)(w|na) ?(czas(ie)?)" {:form :part-of-day}]
  (dissoc %2 :latent)

  "this <part-of-day>"
  [#"(?i)ten|tego|ta|to" {:form :part-of-day}]
  (assoc (intersect (cycle-nth :day 0) %2) :form :part-of-day) ;; removes :latent

  ;; "tonight"
  ;; #"(?i)toni(ght|gth|te)"
  ;; (assoc (intersect (cycle-nth :day 0)
  ;;                   (interval (hour 18 false) (hour 0 false) false))
  ;;        :form :part-of-day) ; no :latent

  ;; "after lunch"
  ;; #"(?i)after(-|\s)?lunch"
  ;; (assoc (intersect (cycle-nth :day 0)
  ;;                   (interval (hour 13 false) (hour 17 false) false))
  ;;        :form :part-of-day) ; no :latent


  ;; "after work"
  ;; #"(?i)after(-|\s)?work"
  ;; (assoc (intersect (cycle-nth :day 0)
  ;;                   (interval (hour 17 false) (hour 21 false) false))
  ;;        :form :part-of-day) ; no :latent

  "<time> <part-of-day>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [(dim :time) {:form :part-of-day}]
  (intersect %2 %1)

  ;; "<part-of-day> of <time>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  ;; [{:form :part-of-day} #"(?i)of" (dim :time)]
  ;; (intersect %1 %3)


  ; Other intervals: week-end, seasons

  "week-end" ; from Friday 6pm to Sunday midnight
  #"(?i)((wek|week|wik)(\s|-)?end|wkend)"
  (interval (intersect (day-of-week 5) (hour 18 false))
            (intersect (day-of-week 1) (hour 0 false))
            false)

  "season"
  #"(?i)lato|lata|latu|latem|lecie" ;could be smarter and take the exact hour into account... also some years the day can change
  (interval (month-day 6 21) (month-day 9 23) false)

  "season"
  #"(?i)jesień|jesien|jesieni|jesienią|jesienia"
  (interval (month-day 9 23) (month-day 12 21) false)

  "season"
  #"(?i)zima|zimy|zimie|zimę|zime|zimą|zima|zimie|zimo"
  (interval (month-day 12 21) (month-day 3 20) false)

  "season"
  #"(?i)wiosna|wiosny|wiośnie|wiosnie|wiosnę|wiosne|wiosną|wiosna|wiośnie|wiosnie|wiosno"
  (interval (month-day 3 20) (month-day 6 21) false)


  ; Time zones

  "timezone"
  #"(?i)(YEKT|YEKST|YAPT|YAKT|YAKST|WT|WST|WITA|WIT|WIB|WGT|WGST|WFT|WEZ|WET|WESZ|WEST|WAT|WAST|VUT|VLAT|VLAST|VET|UZT|UYT|UYST|UTC|ULAT|TVT|TMT|TLT|TKT|TJT|TFT|TAHT|SST|SRT|SGT|SCT|SBT|SAST|SAMT|RET|PYT|PYST|PWT|PT|PST|PONT|PMST|PMDT|PKT|PHT|PHOT|PGT|PETT|PETST|PET|PDT|OMST|OMSST|NZST|NZDT|NUT|NST|NPT|NOVT|NOVST|NFT|NDT|NCT|MYT|MVT|MUT|MST|MSK|MSD|MMT|MHT|MEZ|MESZ|MDT|MAWT|MART|MAGT|MAGST|LINT|LHST|LHDT|KUYT|KST|KRAT|KRAST|KGT|JST|IST|IRST|IRKT|IRKST|IRDT|IOT|IDT|ICT|HOVT|HNY|HNT|HNR|HNP|HNE|HNC|HNA|HLV|HKT|HAY|HAT|HAST|HAR|HAP|HAE|HADT|HAC|HAA|GYT|GST|GMT|GILT|GFT|GET|GAMT|GALT|FNT|FKT|FKST|FJT|FJST|ET|EST|EGT|EGST|EET|EEST|EDT|ECT|EAT|EAST|EASST|DAVT|ChST|CXT|CVT|CST|COT|CLT|CLST|CKT|CHAST|CHADT|CET|CEST|CDT|CCT|CAT|CAST|BTT|BST|BRT|BRST|BOT|BNT|AZT|AZST|AZOT|AZOST|AWST|AWDT|AST|ART|AQTT|ANAT|ANAST|AMT|AMST|ALMT|AKST|AKDT|AFT|AEST|AEDT|ADT|ACST|ACDT)"
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
  [{:form :time-of-day} #"(?i)o?ko(l|ł)o|mniej wi(e|ę)cej"]
  (-> %1
    (dissoc :latent)
    (merge {:precision "approximate"}))

  "<time-of-day> sharp" ; sharp
  [{:form :time-of-day} #"(?i)r(o|ó)wno|dok(l|ł)adnie"]
  (-> %1
    (dissoc :latent)
    (merge {:precision "exact"}))

  "about <time-of-day>" ; about
  [#"(?i)o?ko(l|ł)o|mniej wi(e|ę)cej|tak o" {:form :time-of-day}]
  (-> %2
    (dissoc :latent)
    (merge {:precision "approximate"}))

  "exactly <time-of-day>" ; sharp
  [#"(?i)(r(o|ó)wno|dok(l|ł)adnie)( o)?" {:form :time-of-day} ]
  (-> %2
    (dissoc :latent)
    (merge {:precision "exact"}))


  ; Intervals

  "<month> dd-dd (interval)"
  [{:form :month} #"(3[01]|[12]\d|0?[1-9])" #"\-|do|po|aż do|az do|aż po|az po" #"(3[01]|[12]\d|0?[1-9])"]
  (interval (intersect %1 (day-of-month (Integer/parseInt (-> %2 :groups first))))
            (intersect %1 (day-of-month (Integer/parseInt (-> %4 :groups first))))
            true)

  ; Blocked for :latent time. May need to accept certain latents only, like hours

  "<datetime> - <datetime> (interval)"
  [(dim :time #(not (:latent %))) #"\-|(p|d)o|a(ż|z) (p|d)o" (dim :time #(not (:latent %)))]
  (interval %1 %3 true)

  "from <datetime> - <datetime> (interval)"
  [#"(?i)od|p(o|ó)(z|ź)niej ni(z|ż)" (dim :time) #"\-|do|po|aż do|az do|aż po|az po|ale przed" (dim :time)]
  (interval %2 %4 true)

  "between <datetime> and <datetime> (interval)"
  [#"(?i)(po|po )?mi(e|ę)dzy" (dim :time) #"a|i" (dim :time)]
  (interval %2 %4 true)

  ; Specific for time-of-day, to help resolve ambiguities

  "<time-of-day> - <time-of-day> (interval)"
  [#(and (= :time-of-day (:form %)) (not (:latent %))) #"\-|:|do|po|aż do|az do|aż po|az po" {:form :time-of-day}] ; Prevent set alarm 1 to 5pm
  (interval %1 %3 true)

  "from <time-of-day> - <time-of-day> (interval)"
  [#"(?i)(niż|niz|od)" {:form :time-of-day} #"((but )?before)|\-|do|po|aż do|az do|aż po|az po" {:form :time-of-day}]
  (interval %2 %4 true)

  "between <time-of-day> and <time-of-day> (interval)"
  [#"(?i)(po|po )?miedzy|między" {:form :time-of-day} #"a|i" {:form :time-of-day}]
  (interval %2 %4 true)

  ; Specific for within duration... Would need to be reworked
  "within <duration>"
  [#"(?i)(w )?ci(a|ą)gu|zakresie|obrębie|obrebie" (dim :duration)]
  (interval (cycle-nth :second 0) (in-duration (:value %2)) false)

  "by <time>"; if time is interval, take the start of the interval (by tonight = by 6pm)
  [#"(?i)(a[zż] )?do" (dim :time)]
  (interval (cycle-nth :second 0) %2 false)

  "by the end of <time>"; in this case take the end of the time (by the end of next week = by the end of next sunday)
  [#"(?i)do (ko[ńn]ca )?(tego)?" (dim :time)]
  (interval (cycle-nth :second 0) %2 true)

  ; One-sided Intervals

  "until <time-of-day>"
  [#"(?i)(a[żz] )?do|przed" (dim :time)]
  (merge %2 {:direction :before})

  "after <time-of-day>"
  [#"(?i)po" (dim :time)]
  (merge %2 {:direction :after})

  ;; In this special case, the upper limit is exclusive
  "<hour-of-day> - <hour-of-day> (interval)"
  [{:form :time-of-day} #"-|do|aż po|po" #(and (= :time-of-day (:form %))
  									  (not (:latent %)))]
  (interval %1 %3 :exclusive)

  "from <hour-of-day> - <hour-of-day> (interval)"
  [#"(?i)od" {:form :time-of-day} #"-|to|th?ru|through|until" #(and (= :time-of-day (:form %))
  									              (not (:latent %)))]
  (interval %2 %4 :exclusive)

  ;; "time => time2 (experiment)"
  ;; (dim :time)
  ;; (assoc %1 :dim :time2)

)
