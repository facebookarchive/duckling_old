(
  ;; generic

  "intersect"
  [(dim :time #(not (:latent %))) (dim :time #(not (:latent %)))] ; sequence of two tokens with a time dimension
  (intersect %1 %2)

  ; same thing, with "of" in between like "Sunday of last week"
  ; "intersect by \"of\", \"from\", \"'s\""
  ; [(dim :time #(not (:latent %))) #"(?i)ה|של" (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  ; (intersect %1 %3)

  ; mostly for January 12, 2005
  ; this is a separate rule, because commas separate very specific tokens
  ; so we want this rule's classifier to learn this
  "intersect by \",\""
  [(dim :time #(not (:latent %))) #"," (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)

  "ב <date>" ; on Wed, March 23
  [#"(?i)ב" (dim :time)]
  %2 ; does NOT dissoc latent

  "ב <named-day>" ; on a sunday
  [#"(?i)ב" {:form :day-of-week}]
  %2 ; does NOT dissoc latent

  "in <named-month>" ; in September
  [#"(?i)ב" {:form :month}]
  %2 ; does NOT dissoc latent

  ;;;;;;;;;;;;;;;;;;;
  ;; Named things
  "named-day"
  #"(?i)(יום )?שני"
  (day-of-week 1)

  "named-day"
  #"(?i)(יום )?שלישי"
  (day-of-week 2)

  "named-day"
  #"(?i)(יום )?רביעי"
  (day-of-week 3)

  "named-day"
  #"(?i)(יום )?חמישי"
  (day-of-week 4)

  "named-day"
  #"(?i)(יום )?שישי"
  (day-of-week 5)

  "named-day"
  #"(?i)(יום )?שבת"
  (day-of-week 6)

  "named-day"
  #"(?i)(יום )?ראשון"
  (day-of-week 7)

  "named-month"
  #"(?i)ינואר"
  (month 1)

  "named-month"
  #"(?i)פברואר"
  (month 2)

  "named-month"
  #"(?i)מרץ"
  (month 3)

  "named-month"
  #"(?i)אפריל"
  (month 4)

  "named-month"
  #"(?i)מאי"
  (month 5)

  "named-month"
  #"(?i)יוני"
  (month 6)

  "named-month"
  #"(?i)יולי"
  (month 7)

  "named-month"
  #"(?i)אוגוסט"
  (month 8)

  "named-month"
  #"(?i)ספטמבר"
  (month 9)

  "named-month"
  #"(?i)אוקטובר"
  (month 10)

  "named-month"
  #"(?i)נובמבר"
  (month 11)

  "named-month"
  #"(?i)דצמבר"
  (month 12)

  "absorption of , after named day"
  [{:form :day-of-week} #","]
  %1

  "now"
  #"(?i)עכשיו|מייד"
  (cycle-nth :second 0)

  "today"
  #"(?i)היום"
  (cycle-nth :day 0)

  "tomorrow"
  #"(?i)(מחר|למחרת)"
  (cycle-nth :day 1)

  "yesterday"
  #"(?i)(אתמול|אמש)"
  (cycle-nth :day -1)

  "End of month"
  #"(?i)סוף ה?חודש"
  (cycle-nth :month 1)

  "End of year"
  #"(?i)סוף ה?שנה"
  (cycle-nth :year 1)

  ;;
  ;; This, Next, Last

  "current <day-of-week>"
  [{:form :day-of-week} #"(?i)(הזה|הזאת|הקרובה?)"]
  (pred-nth-not-immediate %1 0)

  "next <day-of-week>"
  [{:form :day-of-week} #"(?i)(הבאה?)"]
  (pred-nth-not-immediate %1 1)

  "last <day-of-week>"
  [{:form :day-of-week} #"(?i)(שעברה?|הקודמת|הקודם)"]
  (pred-nth-not-immediate %1 0)

  ;; for other preds, it can be immediate:
  ;; "this month" => now is part of it
  ; See also: cycles in en.cycles.clj
  "this <time>"
  [(dim :time) #"(?i)הקרוב|הזה"]
  (pred-nth %1 0)

  "next <time>"
  [(dim :time #(not (:latent %))) #"(?i)הבאה?" ]
  (pred-nth-not-immediate %1 0)

  "last <time>"
  [(dim :time) #"(?i)שעבר|ה?קודם|ה?אחרון"]
  (pred-nth %1 -1)

  ; "<time> after next"
  ; [(dim :time) #"(?i)after next"]
  ; (pred-nth-not-immediate %1 1)

  ; "<time> before last"
  ; [(dim :time) #"(?i)before last"]
  ; (pred-nth %1 -2)

  "last <day-of-week> of <time>"
  [{:form :day-of-week} #"(?i)האחרון של" (dim :time)]
  (pred-last-of %1 %3)

  ; "last <cycle> of <time>"
  ; [(dim :cycle) #"(?i)of|in" (dim :time)]
  ; (cycle-last-of %2 %4)

  ; ; Ordinals
  "nth <time> of <time>"
  [(dim :time) (dim :ordinal) #"(?i)של|ב" (dim :time)]
  (pred-nth (intersect %4 %1) (dec (:value %2)))

  ; "nth <time> of <time>"
  ; [#"(?i)the" (dim :ordinal) (dim :time) #"(?i)of|in" (dim :time)]
  ; (pred-nth (intersect %5 %3) (dec (:value %2)))

  "nth <time> after <time>"
  [(dim :ordinal) (dim :time) #"(?i)אחרי" (dim :time)]
  (pred-nth-after %2 %4 (dec (:value %1)))

  ; "nth <time> after <time>"
  ; [#"(?i)the" (dim :ordinal) (dim :time) #"(?i)after" (dim :time)]
  ; (pred-nth-after %3 %5 (dec (:value %2)))

  ; Years
  ; Between 1000 and 2100 we assume it's a year
  ; Outside of this, it's safer to consider it's latent

  "year"
  (integer 1000 2100)
  (year (:value %1))

  ; Day of month appears in the following context:
  ; - the nth
  ; - March nth
  ; - nth of March
  ; - mm/dd (and other numerical formats like yyyy-mm-dd etc.)
  ; In general we are flexible and accept both ordinals (3rd) and numbers (3)

  "the <day-of-month> (ordinal)" ; this one is not latent
  [#"(?i)ה" (dim :ordinal #(<= 1 (:value %) 31))]
  (day-of-month (:value %2))

  "<day-of-month> (ordinal)" ; this one is latent
  [(dim :ordinal #(<= 1 (:value %) 31))]
  (assoc (day-of-month (:value %1)) :latent true)

  "the <day-of-month> (non ordinal)" ; this one is latent
  [#"(?i)ה/S" (integer 1 31)]
  (assoc (day-of-month (:value %2)) :latent true)

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
  [(dim :ordinal #(<= 1 (:value %) 31)) #"(?i)של|ב|ל" {:form :month}]
  (intersect %3 (day-of-month (:value %1)))

  "<day-of-month> (non ordinal) of <named-month>"
  [(integer 1 31) #"(?i)של|ב|ל" {:form :month}]
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
  [#"(?i)באמצע" {:form :month}]
  (intersect %2 (day-of-month (if (#{3 5 7 10} (:month %2)) 15 13)))

  ; ;; Hours and minutes (absolute time)

  "time-of-day (latent)"
  (integer 0 23)
  (assoc (hour (:value %1) true) :latent true)

  "at <time-of-day>"
  [#"(?i)ב" {:form :time-of-day}]
  (dissoc %2 :latent)

  "at hour <time-of-day>"
  [#"(?i)בשעה" {:form :time-of-day}]
  (dissoc %2 :latent)

  "<time-of-day> o'clock"
  [{:form :time-of-day} #"(?i)o.?clock"]
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
  #"(?i)ב?צהריים"
  (hour 12 false)

  "midnight|EOD|end of day"
  #"(?i)ב?חצות"
  (hour 0 false)

  "quarter (relative minutes)"
  #"(?i)רבעי?"
  {:relative-minutes 15}

  "half (relative minutes)"
  #"חצי|מחצית"
  {:relative-minutes 30}

  "number (as relative minutes)"
  (integer 1 59)
  {:relative-minutes (:value %1)}

  "<hour-of-day> <integer> (as relative minutes)"
  [(dim :time :full-hour) #(:relative-minutes %)]
  (hour-relativemin (:full-hour %1) (:relative-minutes %2) true)

  "<hour-of-day> and <integer> (as relative minutes)"
  [(dim :time :full-hour) #"(?i)ו" #(:relative-minutes %)]
  (hour-relativemin (:full-hour %1) (:relative-minutes %3) true)

  "relative minutes to|till|before <integer> (hour-of-day)"
  [#(:relative-minutes %) #"(?i)לפני|ל" (dim :time :full-hour)]
  (hour-relativemin (:full-hour %3) (- (:relative-minutes %1)) true)

  "relative minutes after|past <integer> (hour-of-day)"
  [#(:relative-minutes %) #"(?i)אחרי" (dim :time :full-hour)]
  (hour-relativemin (:full-hour %3) (:relative-minutes %1) true)

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
  [#"(?i)ב?בוקר"]
  (assoc (interval (hour 4 false) (hour 12 false) false) :form :part-of-day :latent true)

  ; "early morning"
  ; [#"(?i)early ((in|hours of) the )?morning"]
  ; (assoc (interval (hour 4 false) (hour 9 false) false) :form :part-of-day :latent true)

  "afternoon"
  [#"(?i)אחה״?צ|אחר הצהריים"]
  (assoc (interval (hour 12 false) (hour 19 false) false) :form :part-of-day :latent true)

  "evening|night"
  [#"(?i)ב?ערב"]
  (assoc (interval (hour 18 false) (hour 0 false) false) :form :part-of-day :latent true)

  "lunch"
  [#"(?i)ב?צהריים"]
  (assoc (interval (hour 12 false) (hour 14 false) false) :form :part-of-day :latent true)

  ; "in|during the <part-of-day>" ;; removes latent
  ; [#"(?i)(in|during)( the)?" {:form :part-of-day}]
  ; (dissoc %2 :latent)

  ; "this <part-of-day>"
  ; [#"(?i)this" {:form :part-of-day}]
  ; (assoc (intersect (cycle-nth :day 0) %2) :form :part-of-day) ;; removes :latent

  "this evening"
  #"(?i)הערב"
  (assoc (intersect (cycle-nth :day 0)
                    (interval (hour 18 false) (hour 0 false) false))
         :form :part-of-day) ; no :latent

  "<time> <part-of-day>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [(dim :time){:form :part-of-day}]
  (intersect %2 %1)

  "<time> of <part-of-day>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [(dim :time) #"(?i)ב" {:form :part-of-day}]
  (intersect %3 %1)

  "<part-of-day> of <time>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [#"(?i)ב" {:form :part-of-day} #"(?i)של" (dim :time)]
  (intersect %2 %4)

  ; ; Other intervals: week-end, seasons

  "week-end" ; from Friday 6pm to Sunday midnight
  #"(?i)(סופ״ש|סוף השבוע)"
  (interval (intersect (day-of-week 5) (hour 18 false))
            (intersect (day-of-week 1) (hour 0 false))
            false)

  ; ; Intervals

  ; "<month> dd-dd (interval)"
  ; [{:form :month} #"(3[01]|[12]\d|0?[1-9])" #"\-|to|th?ru|through|(un)?til(l)?" #"(3[01]|[12]\d|0?[1-9])"]
  ; (interval (intersect %1 (day-of-month (Integer/parseInt (-> %2 :groups first))))
  ;           (intersect %1 (day-of-month (Integer/parseInt (-> %4 :groups first))))
  ;           true)

  ; ; Blocked for :latent time. May need to accept certain latents only, like hours

  "<datetime> - <datetime> (interval)"
  [(dim :time #(not (:latent %))) #"\-|עד?" (dim :time #(not (:latent %)))]
  (interval %1 %3 true)

  "from <datetime> - <datetime> (interval)"
  [#"(?i)מ|משעה" (dim :time) #"\-|עד" (dim :time)]
  (interval %2 %4 true)

  "between <datetime> and <datetime> (interval)"
  [#"(?i)בין" (dim :time) #"ל" (dim :time)]
  (interval %2 %4 true)

  ; ; Specific for time-of-day, to help resolve ambiguities

  ; "<time-of-day> - <time-of-day> (interval)"
  ; [#(and (= :time-of-day (:form %)) (not (:latent %))) #"\-|:|to|th?ru|through|(un)?til(l)?" {:form :time-of-day}] ; Prevent set alarm 1 to 5pm
  ; (interval %1 %3 true)

  ; "from <time-of-day> - <time-of-day> (interval)"
  ; [#"(?i)(later than|from)" {:form :time-of-day} #"((but )?before)|\-|to|th?ru|through|(un)?til(l)?" {:form :time-of-day}]
  ; (interval %2 %4 true)

  "between <time-of-day> and <time-of-day> (interval)"
  [#"(?i)בין" {:form :time-of-day} #"ל" {:form :time-of-day}]
  (interval %2 %4 true)

  ; ; Specific for within duration... Would need to be reworked
  ; "within <duration>"
  ; [#"(?i)within" (dim :duration)]
  ; (interval (cycle-nth :second 0) (in-duration (:value %2)) false)

  ; "by <time>"; if time is interval, take the start of the interval (by tonight = by 6pm)
  ; [#"(?i)by" (dim :time)]
  ; (interval (cycle-nth :second 0) %2 false)

  ; "by the end of <time>"; in this case take the end of the time (by the end of next week = by the end of next sunday)
  ; [#"(?i)by (the )?end of" (dim :time)]
  ; (interval (cycle-nth :second 0) %2 true)

  ; ; One-sided Intervals

  "until <time-of-day>"
  [#"(?i)עד" (dim :time)]
  (merge %2 {:direction :before})

  "after <time-of-day>"
  [#"(?i)אחרי" (dim :time)]
  (merge %2 {:direction :after})

  "since <time-of-day>"
  [#"(?i)מ" (dim :time)]
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
