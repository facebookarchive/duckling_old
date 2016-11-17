(
  ;; generic

  "intersect"
  [(dim :time #(not (:latent %))) (dim :time #(not (:latent %)))] ; sequence of two tokens with a time dimension
  (intersect %1 %2)

  ; mostly for January 12, 2005
  ; this is a separate rule, because commas separate very specific tokens
  ; so we want this rule's classifier to learn this
  "intersect by \",\""
  [(dim :time #(not (:latent %))) #"," (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)

  "<date>에" ; on Wed, March 23
  [(dim :time) #"에"]
  %1 ; does NOT dissoc latent

  "<named-day>에" ; on a sunday
  [{:form :day-of-week} #"에"]
  %1 ; does NOT dissoc latent

  "<named-month>에" ; in September
  [{:form :month} #"에"]
  %1 ; does NOT dissoc latent

  ;;;;;;;;;;;;;;;;;;;
  ;; Named things

  "day-of-week"
  #"(월|화|수|목|금|토|일)(요일|욜)"
  (->> (-> %1 :groups first)
       (get {"월" 1 "화" 2 "수" 3 "목" 4 "금" 5 "토" 6 "일" 7})
       day-of-week)

  "month" ; 1월..12월
  [(integer 1 12) #"월"]
  (month (:value %1))

  "day" ; 1일..31일
  [(integer 1 31) #"일"]
  (day-of-month (:value %1))

  "day with korean number - 십일..삼십일일" ;
  #"([이|삼]?십[일|이|삼|사|오|육|칠|팔|구]?)일"
  (let [map-number #(or (get {\일 1 \이 2 \삼 3 \사 4 \오 5
                              \육 6 \칠 7 \팔 8 \구 9 \십 1} %) 0)
        number (re-matches #"(.*십)?(.*)?" (first (:groups %1)))]
    (day-of-month (+ (* 10 (map-number (first (number 1))))
                     (map-number (first (number 2))))))

  "day with korean number - 일일..구일" ;
  #"([일|이|삼|사|오|육|칠|팔|구])일"
  (day-of-month (get {"일" 1 "이" 2 "삼" 3 "사" 4 "오" 5
                      "육" 6 "칠" 7 "팔" 8 "구" 9} (first (:groups %1))))

  ; Holiday TODO: check online holidays
  ; or define dynamic rule (last thursday of october..)

  "New Year's Day"
  #"신정|설날"
  (month-day 1 1)

  "Independence Movement Day"
  #"삼일절"
  (month-day 3 1)

  "Children's Day"
  #"어린이날"
  (month-day 5 5)

  "Memorial Day"
  #"현충일"
  (month-day 6 6)

  "Constitution Day"
  #"제헌절"
  (month-day 6 17)

  "Liberation Day"
  #"광복절"
  (month-day 8 15)

  "National Foundation Day"
  #"개천절"
  (month-day 10 3)

  "Hangul Day"
  #"한글날"
  (month-day 10 9)

  "christmas eve"
  #"(크리스마스)?이브"
  (month-day 12 24)

  "christmas"
  #"크리스마스"
  (month-day 12 25)

  "absorption of , after named day"
  [{:form :day-of-week} #","]
  %1

  "now"
  #"방금|지금|방금|막"
  (cycle-nth :second 0)

  "today"
  #"오늘|당일|금일"
  (cycle-nth :day 0)

  "tomorrow"
  #"내일|명일|낼"
  (cycle-nth :day 1)

  "yesterday"
  #"어제"
  (cycle-nth :day -1)

  "end of <time>"
  [(dim :time) #"말"]
  (pred-nth %1 1)

  ;; This, Next, Last

  "this <day-of-week>"
  [#"이번주?|금주" {:form :day-of-week}]
  (pred-nth %2 0)

  ;; for other preds, it can be immediate:
  ;; "this month" => now is part of it
  ; See also: cycles in en.cycles.clj
  "this <time>"
  [#"이번" (dim :time)]
  (pred-nth %2 0)

  "next <time>"
  [#"다음|오는" (dim :time #(not (:latent %)))]
  (pred-nth %2 1)

  "last <time>"
  [#"전|저번|지난" (dim :time)]
  (pred-nth %2 -1)

  "<time> after next"
  [(dim :time) #"(?i)after next"]
  (pred-nth-not-immediate %1 1)

  "<time> 마지막 <day-of-week>"
  [(dim :time) #"마지막" {:form :day-of-week}]
  (pred-last-of %3 %1)

  "<time> 마지막 <cycle>"
  [(dim :time) #"마지막" (dim :cycle)]
  (cycle-last-of %3 %1)

  ; Ordinals
  "<time> nth <time> - 3월 첫째 화요일"
  [(dim :time) (dim :ordinal) (dim :time)]
  (pred-nth (intersect %1 %3) (dec (:value %2)))

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

  "year"
  [(integer 1) #"년"]
  (year (:value %1))

  ;; Hours and minutes (absolute time)

  "time-of-day (latent)"
  (integer 0 23)
  (assoc (hour (:value %1) true) :latent true)

  "time-of-day"
  [(integer 0 24) #"시"]
  (hour (:value %1) true)

  "<time-of-day>에" ; at four
  [{:form :time-of-day} #"에"]
  (dissoc %2 :latent)


  "<time-of-day> 정각"
  [{:form :time-of-day} #"정각"]
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


  ; "hhmm (military)"
  ; #"(?i)((?:[01]?\d)|(?:2[0-3]))([0-5]\d)"
  ; (-> (hour-minute (Integer/parseInt (first (:groups %1)))
  ;                  (Integer/parseInt (second (:groups %1)))
  ;                  false) ; not a 12-hour clock)
  ;     (assoc :latent true))

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

  "am|pm <time-of-day>"
  [#"오전|아침|오후|저녁" {:form :time-of-day}]
  (let [[p meridiem] (if (or (= "오전" (:text %1)) (= "아침" (:text %1)))
                       [[(hour 0) (hour 12) false] :am]
                       [[(hour 12) (hour 0) false] :pm])]
    (-> (intersect %2 (apply interval p))
        (assoc :form :time-of-day)))

  "noon"
  #"정오"
  (hour 12 false)

  "midnight|EOD|end of day"
  #"자정"
  (hour 0 false)

  "half (relative minutes)"
  #"반"
  {:relative-minutes 30}

  "number (as relative minutes)"
  [(integer 1 59) #"분"]
  {:relative-minutes (:value %1)}

  "<hour-of-day> <integer> (as relative minutes)"
  [(dim :time :full-hour) #(:relative-minutes %)]
  (hour-relativemin (:full-hour %1) (:relative-minutes %2) true)

  "<integer> (hour-of-day) relative minutes 전"
  [(dim :time :full-hour) #(:relative-minutes %) #"전"]
  (hour-relativemin (:full-hour %1) (- (:relative-minutes %2)) true)

  "seconds"
  [(integer 1 59) #"초"]
  (sec (:value %1))

  ; Formatted dates and times

  "mm/dd/yyyy"
  #"(\d{2,4})[-/](0?[1-9]|1[0-2])[/-](3[01]|[12]\d|0?[1-9])"
  (parse-dmy (nth (:groups %1) 2) (second (:groups %1)) (first (:groups %1)) true)

  "yyyy-mm-dd"
  #"(\d{2,4})-(0?[1-9]|1[0-2])-(3[01]|[12]\d|0?[1-9])"
  (parse-dmy (nth (:groups %1) 2) (second (:groups %1)) (first (:groups %1)) true)

  "mm/dd"
  #"(0?[1-9]|1[0-2])/(3[01]|[12]\d|0?[1-9])"
  (parse-dmy (second (:groups %1)) (first (:groups %1)) nil true)


  ; Part of day (morning, evening...). They are intervals.

  "morning" ;; TODO "3am this morning" won't work since morning starts at 4...
  #"아침"
  (assoc (interval (hour 4 false) (hour 12 false) false) :form :part-of-day :latent true)

  "afternoon"
  #"오후"
  (assoc (interval (hour 12 false) (hour 19 false) false) :form :part-of-day :latent true)

  "evening|night"
  #"저녁|밤"
  (assoc (interval (hour 18 false) (hour 0 false) false) :form :part-of-day :latent true)

  "lunch"
  #"점심"
  (assoc (interval (hour 12 false) (hour 14 false) false) :form :part-of-day :latent true)

  "in|during the <part-of-day>" ;; removes latent
  [{:form :part-of-day} #"에|동안"]
  (dissoc %1 :latent)

  "after <part-of-day>"
  [{:form :part-of-day} #"지나서|후에"]
  (assoc (intersect (cycle-nth :day 0)
                    (interval (hour (inc (:start %1)) false) (hour (inc (:end %1)) false) false))
         :form :part-of-day) ; no :latent


  "<time> <part-of-day>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [(dim :time) {:form :part-of-day}]
  (intersect %2 %1)

  ; Other intervals: week-end, seasons

  "week-end" ; from Friday 6pm to Sunday midnight
  #"주말"
  (interval (intersect (day-of-week 5) (hour 18 false))
            (intersect (day-of-week 1) (hour 0 false))
            false)

  "season"
  #"여름" ;could be smarter and take the exact hour into account... also some years the day can change
  (interval (month-day 6 21) (month-day 9 23) false)

  "season"
  #"가을"
  (interval (month-day 9 23) (month-day 12 21) false)

  "season"
  #"겨울"
  (interval (month-day 12 21) (month-day 3 20) false)

  "season"
  #"봄"
  (interval (month-day 3 20) (month-day 6 21) false)


  ; Time zones

  "timezone"
  #"(?i)\b(YEKT|YEKST|YAPT|YAKT|YAKST|WT|WST|WITA|WIT|WIB|WGT|WGST|WFT|WEZ|WET|WESZ|WEST|WAT|WAST|VUT|VLAT|VLAST|VET|UZT|UYT|UYST|UTC|ULAT|TVT|TMT|TLT|TKT|TJT|TFT|TAHT|SST|SRT|SGT|SCT|SBT|SAST|SAMT|RET|PYT|PYST|PWT|PT|PST|PONT|PMST|PMDT|PKT|PHT|PHOT|PGT|PETT|PETST|PET|PDT|OMST|OMSST|NZST|NZDT|NUT|NST|NPT|NOVT|NOVST|NFT|NDT|NCT|MYT|MVT|MUT|MST|MSK|MSD|MMT|MHT|MEZ|MESZ|MDT|MAWT|MART|MAGT|MAGST|LINT|LHST|LHDT|KUYT|KST|KRAT|KRAST|KGT|JST|IST|IRST|IRKT|IRKST|IRDT|IOT|IDT|ICT|HOVT|HNY|HNT|HNR|HNP|HNE|HNC|HNA|HLV|HKT|HAY|HAT|HAST|HAR|HAP|HAE|HADT|HAC|HAA|GYT|GST|GMT|GILT|GFT|GET|GAMT|GALT|FNT|FKT|FKST|FJT|FJST|ET|EST|EGT|EGST|EET|EEST|EDT|ECT|EAT|EAST|EASST|DAVT|ChST|CXT|CVT|CST|COT|CLT|CLST|CKT|CHAST|CHADT|CET|CEST|CDT|CCT|CAT|CAST|BTT|BST|BRT|BRST|BOT|BNT|AZT|AZST|AZOT|AZOST|AWST|AWDT|AST|ART|AQTT|ANAT|ANAST|AMT|AMST|ALMT|AKST|AKDT|AFT|AEST|AEDT|ADT|ACST|ACDT)\b"
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
  [{:form :time-of-day} #"정도|쯤"]
  (-> %1
    (dissoc :latent)
    (merge {:precision "approximate"}))

  "about <time-of-day>" ; about
  [#"대충|약" {:form :time-of-day}]
  (-> %2
    (dissoc :latent)
    (merge {:precision "approximate"}))

  "exactly <time-of-day>" ; sharp
  [{:form :time-of-day} #"정각"]
  (-> %1
    (dissoc :latent)
    (merge {:precision "exact"}))


  ; Blocked for :latent time. May need to accept certain latents only, like hours

  "<datetime> - <datetime> (interval)"
  [(dim :time #(not (:latent %))) #"\-|\~|부터" (dim :time #(not (:latent %)))]
  (interval %1 %3 true)

  ; Specific for time-of-day, to help resolve ambiguities
  "<time-of-day> - <time-of-day> (interval)"
  [#(and (= :time-of-day (:form %)) (not (:latent %))) #"\-|\~|부터" {:form :time-of-day}] ; Prevent set alarm 1 to 5pm
  (interval %1 %3 true)

  ; Specific for within duration... Would need to be reworked
  "within <duration>"
  [(dim :duration) #"이내에?"]
  (interval (cycle-nth :second 0) (in-duration (:value %1)) false)

  "by <time> - 까지"; if time is interval, take the start of the interval (by tonight = by 6pm)
  [(dim :time) #"까지"]
  (interval (cycle-nth :second 0) %1 false)

  ; One-sided Intervals

  "<time-of-day>이전"
  [(dim :time) #"이?전"]
  (merge %1 {:direction :before})

  "after <time-of-day>"
  [(dim :time) #"지나서|이?후에?"]
  (merge %1 {:direction :after})

  "since <time-of-day>"
  [(dim :time) #"이래로" ]
  (merge  (pred-nth %2 -1) {:direction :after})

)
