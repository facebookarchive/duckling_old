(
  ;; generic

  "intersect"
  [(dim :time #(not (:latent %))) (dim :time #(not (:latent %)))] ; sequence of two tokens with a time dimension
  (intersect %1 %2)

  ; same thing, with "de" in between like "mardi de la semaine dernière"
  "intersect by 'de' or ','"
  [(dim :time #(not (:latent %))) #"(?i)de|," (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)

  ; same thing, with "mais/par exemple/plutôt/" in between like "mardi, mais à 14 heures"
  "intersect by 'mais/par exemple/plutôt'"
  [(dim :time #(not (:latent %))) #"(?i)mais|par exemple|plutôt" (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)

  "en <named-month>" ; en mars
  [#"(?i)en|au mois de?'?" {:form :month}]
  %2 ; does NOT dissoc latent

   ;;;;;;;;;;;;;;;;;;;
  ;; Named things

  "named-day"
  #"(?i)lun\.?(di)?"
  (day-of-week 1)

  "named-day"
  #"(?i)mar\.?(di)?"
  (day-of-week 2)

  "named-day"
  #"(?i)mer\.?(credi)?"
  (day-of-week 3)

  "named-day"
  #"(?i)jeu\.?(di)?"
  (day-of-week 4)

  "named-day"
  #"(?i)ven\.?(dredi)?"
  (day-of-week 5)

  "named-day"
  #"(?i)sam\.?(edi)?"
  (day-of-week 6)

  "named-day"
  #"(?i)dim\.?(anche)?"
  (day-of-week 7)

  "named-month"
  #"(?i)janvier|janv\.?"
  (month 1)

  "named-month"
  #"(?i)fevrier|février|fev|fév\.?"
  (month 2)

  "named-month"
  #"(?i)mars|mar\.?"
  (month 3)

  "named-month"
  #"(?i)avril|avr\.?"
  (month 4)

  "named-month"
  #"(?i)mai"
  (month 5)

  "named-month"
  #"(?i)juin|jun\.?"
  (month 6)

  "named-month"
  #"(?i)juillet|juil?\."
  (month 7)

  "named-month"
  #"(?i)aout|août|aou\.?"
  (month 8)

  "named-month"
  #"(?i)septembre|sept?\.?"
  (month 9)

  "named-month"
  #"(?i)octobre|oct\.?"
  (month 10)

  "named-month"
  #"(?i)novembre|nov\.?"
  (month 11)

  "named-month"
  #"(?i)décembre|decembre|déc\.?|dec\.?"
  (month 12)

  ; Holiday TODO: check online holidays
  "noel"
  #"(?i)(jour de )?no[eë]l"
  (month-day 12 25)

  "soir de noël"
  #"(?i)soir(ée)? de no[eë]l"
  (interval (intersect (month-day 12 24) (hour 18)) (intersect (month-day 12 25) (hour 00)) false)

  "jour de l'an"
  #"(?i)(jour de l'|nouvel )an"
  (month-day 1 1)

  "toussaint"
  #"(?i)((la |la journée de la |jour de la )?toussaint|jour des morts)"
  (month-day 11 1)

  "1er mai"
  #"(?i)f(e|ê)te du travail"
  (month-day 5 1)

  "maintenant"
  #"maintenant|(tout de suite)"
  (cycle-nth :second 0)

  "aujourd'hui"
  #"(?i)(aujourd'? ?hui)|(ce jour)|(dans la journ[ée]e?)|(en ce moment)"
  (cycle-nth :day 0)

  "demain"
  #"(?i)(demain)|(le lendemain)"
  (cycle-nth :day 1)

  "hier"
  #"(?i)hier|la veille"
  (cycle-nth :day -1)

  "fin du mois"
  #"(?i)(([aà] )?la )?fin (du|de) mois"
  (assoc (interval (cycle-nth-after :day -10 (cycle-nth :month 1))
                   (cycle-nth :month 1) false)
          :form :part-of-day :latent true)

  "après-demain"
  #"(?i)apr(e|è)s[- ]?demain"
  (cycle-nth :day 2)

  "avant-hier"
  #"(?i)avant[- ]?hier"
  (cycle-nth :day -2)

  ;;
  ;; This, Next, Last

  "ce <day-of-week>" ; assumed to be in the future "ce dimanche"
  [#"(?i)ce" {:form :day-of-week}]
  (pred-nth-not-immediate %2 0)

  ;; for other preds, it can be immediate:
  ;; "ce mois" => now is part of it
  ; See also: cycles in en.cycles.clj
  "ce <time>"
  [#"(?i)ce" (dim :time)]
  (pred-nth %2 0)

  "<day-of-week> prochain" ; assumed to be in the future "dimanche prochain"
  [{:form :day-of-week} #"(?i)prochain"]
  (pred-nth-not-immediate %1 0)

  "<named-month> prochain"
  [(dim :time) #"(?i)prochain"]
  (pred-nth %1 1)

  "<named-month|named-day> suivant|d'après"
  [(dim :time) #"(?i)suivante?s?|d'apr[eéè]s"]
  (pred-nth %1 1)

  "<named-month|named-day> dernier|passé"
  [(dim :time) #"(?i)derni[eéè]re?|pass[ée]e?"]
  (pred-nth %1 -1)

  "<named-day> en huit" ; would need assumption to handle 1 or 2 weeks depending on the day-of-week
  [{:form :day-of-week} #"(?i)en (huit|8)"]
  (pred-nth %1 1)

  "<named-day> en quinze" ; would need assumption to handle 2 or 3 weeks depending on the day-of-week
  [{:form :day-of-week} #"(?i)en (quinze|15)"]
  (pred-nth %1 2)

  "dernier <day-of-week> de <time> (latent)" ;; TODO without a 'le', this is latent.
  [#"(?i)derni[eéè]re?" {:form :day-of-week} #"(?i)d['e]" (dim :time)]
  (pred-last-of %2 %4)

  "dernier <cycle> de <time> (latent)" ;; TODO without a 'le', this is latent.
  [#"(?i)derni[eéè]re?" (dim :cycle) #"(?i)d['e]" (dim :time)]
  (cycle-last-of %2 %4)

  "<ordinal> week-end de <time>"
  [(dim :ordinal) #"week(\s|-)?end (d['eu]|en|du mois de)" {:form :month}]
  (pred-nth (intersect %3 (interval
              (intersect (day-of-week 5) (hour 18 false))
              (intersect (day-of-week 1) (hour 0 false)) false) ) (dec (:value %1)))

  "dernier week-end de <time>"
  [#"(?i)dernier week(\s|-)?end (d['eu]|en|du mois de)" {:form :month}]
  (pred-last-of (interval (intersect (day-of-week 5) (hour 18 false))
                          (intersect (day-of-week 1) (hour 0 false)) false)  %2)

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
  ; - le premier
  ; - le 5
  ; - 5 March
  ; - mm/dd (and other numerical formats like yyyy-mm-dd etc.)
  ; We remove the rule with just (integer 1 31) as it was too messy

  "day of month (premier)"
  [#"(?i)premier|prem\.?|1er|1 er"]
  (day-of-month 1)

  "le <day-of-month> (non ordinal)"
  [#"(?i)le" (integer 1 31)]
  (day-of-month (:value %2))

  ; "le 16 à 18h" is a datetime. "16 à 18h" is an interval
  "le <day-of-month> à <datetime>"
  [#"(?i)le" (integer 1 31) #"(?i)[aà]" (dim :time)]
  (intersect (day-of-month (:value %2)) %4)

  "<day-of-month> <named-month>" ; 12 mars
  [(integer 1 31) {:form :month}]
  (intersect %2 (day-of-month (:value %1)))

  ;use only the integer part, which is usually a better fit for an human point of view
  ; ie "jeudi 31" where the 31 is mercredi will give "mercredi 31" - which is better than jeudi 31 [year + 1])
  "<day-of-week> <day-of-month>" ; vendredi 13
  [{:form :day-of-week} (integer 1 31)]
  (day-of-month (:value %2))

  "<day-of-week> <day-of-month> à <time-of-day>)"
  [{:form :day-of-week} (integer 1 31) {:form :time-of-day}]
  (intersect (day-of-month (:value %2)) %3)

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

  "midi"
  #"(?i)midi"
  (hour 12 false)

  "minuit"
  #"(?i)minuit"
  (hour 0 false)

  "<time-of-day> heures"
  [#(:full-hour %) #"(?i)h\.?(eure)?s?"]
  (dissoc %1 :latent)

  "à|vers <time-of-day>" ; absorption
  [#"(?i)(vers|autour de|[aà] environ|aux alentours de|[aà])" {:form :time-of-day}]
  (dissoc %2 :latent)

  "hh(:|h)mm (time-of-day)"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))[:h]([0-5]\d)"
  (let [h (Integer/parseInt (first (:groups %1)))]
    (hour-minute h
                 (Integer/parseInt (second (:groups %1)))
                 (< h 12)))

  "hhmm (military time-of-day)"
  #"(?i)((?:[01]?\d)|(?:2[0-3]))([0-5]\d)"
  (-> (hour-minute (Integer/parseInt (first (:groups %1)))
                (Integer/parseInt (second (:groups %1)))
                false) ; not a 12-hour clock
      (assoc :latent true))

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
  {:relative-minutes (:value %1)}

  "number minutes (as relative minutes)"
  [(integer 1 59) #"(?i)min\.?(ute)?s?"]
  {:relative-minutes (:value %1)}

  "<hour-of-day> <integer> (as relative minutes)"
  [(dim :time :full-hour) #(:relative-minutes %)] ;before  [{:for-relative-minutes true} #(:relative-minutes %)]
  (hour-relativemin (:full-hour %1) (:relative-minutes %2) (:twelve-hour-clock? %1))

  "<hour-of-day> moins <integer> (as relative minutes)"
  [(dim :time :full-hour) #"moins( le)?" #(:relative-minutes %)]
  (hour-relativemin (:full-hour %1) (- (:relative-minutes %3)) (:twelve-hour-clock? %1))

  "<hour-of-day> et|passé de <relative minutes>"
  [(dim :time :full-hour) #"et|(pass[ée]e? de)" #(:relative-minutes %)]
  (hour-relativemin (:full-hour %1) (:relative-minutes %3) (:twelve-hour-clock? %1))


  ;; Formatted dates and times

  "dd/-mm/-yyyy"
  #"(3[01]|[12]\d|0?[1-9])[/-](1[0-2]|0?[1-9])[-/](\d{2,4})"
  (parse-dmy (first (:groups %1)) (second (:groups %1)) (nth (:groups %1) 2) true)

  "yyyy-mm-dd"
  #"(\d{2,4})-(1[0-2]|0?[1-9])-(3[01]|[12]\d|0?[1-9])"
  (parse-dmy (nth (:groups %1) 2) (second (:groups %1)) (first (:groups %1)) true)

  "dd/-mm"
  #"(3[01]|[12]\d|0?[1-9])[/-](1[0-2]|0?[1-9])"
  (parse-dmy (first (:groups %1)) (second (:groups %1)) nil true)

  "dd mm yyyy"
  #"(3[01]|[12]\d|0?[1-9]) (1[0-2]|0?[1-9]) (\d{2,4})"
  (parse-dmy (first (:groups %1)) (second (:groups %1)) (nth (:groups %1) 2) true)

  "dd mm"
  #"(3[01]|[12]\d|0?[1-9]) (1[0-2]|0?[1-9])"
  (parse-dmy (first (:groups %1)) (second (:groups %1)) nil true)


  ; Part of day (morning, evening...). They are intervals.

  "matin"
  #"(?i)mat(in[ée]?e?)?"
  (assoc (interval (hour 4 false) (hour 12 false) false) :form :part-of-day :latent true)

  "début de matinée"
  #"(?i)(le matin (tr[eè]s )?t[ôo]t|(tr[eè]s )?t[ôo]t le matin|d[ée]but de matin[ée]e)"
  (assoc (interval (hour 4 false) (hour 9 false) false) :form :part-of-day :latent true)

  "milieu de matinée"
  #"(?i)milieu de matin[ée]e"
  (assoc (interval (hour 9 false) (hour 11 false) false) :form :part-of-day :latent true)

  "fin de matinée"
  #"(?i)fin de matin[ée]e"
  (assoc (interval (hour 10 false) (hour 12 false) false) :form :part-of-day :latent true)

  "au déjeuner"
  #"(?i)([àa] l(')?heure du|pendant( le)?|au)? d[eéè]jeuner"
  (assoc (interval (hour 12 false) (hour 14 false) false) :form :part-of-day :latent true)

  "après le déjeuner"
  #"(?i)apr[eè]s (le )?d[eéè]jeuner"
  (assoc (intersect (cycle-nth :day 0)
                    (interval (hour 13 false) (hour 17 false) false))
         :form :part-of-day) ; no :latent

  "avant le déjeuner"
  #"(?i)avant (le )?d[eéè]jeuner"
  (assoc (intersect (cycle-nth :day 0)
                    (interval (hour 10 false) (hour 12 false) false))
         :form :part-of-day)

   "après le travail"
   #"(?i)apr[eè]s (le )?travail"
   (assoc (intersect (cycle-nth :day 0)
                     (interval (hour 17 false) (hour 21 false) false))
          :form :part-of-day)

  "après-midi"
  #"(?i)apr[eéè]s?[ \-]?midi"
  (assoc (interval (hour 12 false) (hour 19 false) false) :form :part-of-day :latent true)

  "début d'après-midi"
  #"(?i)d[ée]but d'apr[eéè]s?[ \-]?midi"
  (assoc (interval (hour 12 false) (hour 14 false) false) :form :part-of-day :latent true)

  "milieu d'après-midi"
  #"(?i)milieu d'apr[eéè]s?[ \-]?midi"
  (assoc (interval (hour 15 false) (hour 17 false) false) :form :part-of-day :latent true)

  "fin d'après-midi"
  #"(?i)fin d'apr[eéè]s?[ \-]?midi"
  (assoc (interval (hour 17 false) (hour 19 false) false) :form :part-of-day :latent true)

  "début de journée"
  #"(?i)d[ée]but de journ[ée]e"
  (assoc (interval (hour 6 false) (hour 10 false) false) :form :part-of-day :latent true)

  "milieu de journée"
  #"(?i)milieu de journ[ée]e"
  (assoc (interval (hour 11 false) (hour 16 false) false) :form :part-of-day :latent true)

  "fin de journée"
  #"(?i)fin de journ[ée]e"
  (assoc (interval (hour 17 false) (hour 21 false) false) :form :part-of-day :latent true)

  "soir"
  #"(?i)soir[ée]?e?"
  (assoc (interval (hour 18 false) (hour 0 false) false) :form :part-of-day :latent true)

  "début de soirée"
  #"(?i)d[ée]but de soir[ée]e?"
  (assoc (interval (hour 18 false) (hour 21 false) false) :form :part-of-day :latent true)

  "fin de soirée"
  #"(?i)fin de soir[ée]e?"
  (assoc (interval (hour 21 false) (hour 0 false) false) :form :part-of-day :latent true)

  "du|dans le <part-of-day>" ;; removes latent
  [#"(?i)du|dans l[ae']? ?|au|en|l[ae' ]|dès l?[ae']? ?" {:form :part-of-day}]
  (dissoc %2 :latent)

  "ce <part-of-day>"
  [#"(?i)cet?t?e?" {:form :part-of-day}]
  (assoc (intersect (cycle-nth :day 0) %2) :form :part-of-day) ;; removes :latent

  "<dim time> <part-of-day>" ; since "morning" "evening" etc. are latent, general time+time is blocked
  [(dim :time) {:form :part-of-day}]
  (intersect %1 %2)

  ;specific rule to address "3 in the morning","3h du matin" and extend morning span from 0 to 12
  "<dim time> du matin"
  [{:form :time-of-day} #"(?i)((du|dans|de) )?((au|le|la) )?mat(in[ée]?e?)?"]
  (intersect %1 (assoc (interval (hour 0 false) (hour 12 false) false) :form :part-of-day :latent true))

  ;specific rule to extend evening span from 16 to 0
  "<dim time> du soir"
  [{:form :time-of-day} #"(?i)((du|dans|de) )?((au|le|la) )?soir[ée]?e?"]
  (intersect %1 (assoc (interval (hour 16 false) (hour 0 false) false) :form :part-of-day :latent true))

   "<part-of-day> du <dim time>"
   [{:form :part-of-day} #"(?i)du" (dim :time)]
   (intersect %3 %1)

  ; Other intervals: week-end, seasons
  "week-end"
  #"(?i)week(\s|-)?end"
  (interval (intersect (day-of-week 5) (hour 18 false))
            (intersect (day-of-week 1) (hour 0 false))
            false)

  "début de semaine"
  [#"(?i)(en |au )?début de (cette |la )?semaine"]
  (interval (day-of-week 1) (day-of-week 2) false)

  "milieu de semaine"
  [#"(?i)(en |au )?milieu de (cette |la )?semaine"]
  (interval (day-of-week 3) (day-of-week 4) false)

  "fin de semaine"
  [#"(?i)(en |à la )?fin de (cette |la )?semaine"]
    (interval (day-of-week 4) (day-of-week 7) false)

  "en semaine"
  [#"(?i)(pendant la |en )?semaine"]
    (interval (day-of-week 1) (day-of-week 5) false)

  "season"
  #"(?i)(cet )?été" ;could be smarter and take the exact hour into account... also some years the day can change
  (interval (month-day 6 21) (month-day 9 23) false)

  "season"
  #"(?i)(cet )?automne"
  (interval (month-day 9 23) (month-day 12 21) false)

  "season"
  #"(?i)(cet )?hiver"
  (interval (month-day 12 21) (month-day 3 20) false)

  "season"
  #"(?i)(ce )?printemps"
  (interval (month-day 3 20) (month-day 6 21) false)

  ; Absorptions

  ; a specific version of "le", above, removes :latent for integer as day of month
  ; this one is more general but does not remove latency
  "le <time>"
  [#"(?i)l[ea]" (dim :time #(not (:latent %)))]
  %2

  ; Time zones

  "timezone"
  #"((?i)(YEKT|YEKST|YAPT|YAKT|YAKST|WT|WST|WITA|WIT|WIB|WGT|WGST|WFT|WEZ|WET|WESZ|WEST|WAT|WAST|VUT|VLAT|VLAST|VET|UZT|UYT|UYST|UTC|ULAT|TVT|TMT|TLT|TKT|TJT|TFT|TAHT|SST|SRT|SGT|SCT|SBT|SAST|SAMT|RET|PYT|PYST|PWT|PT|PST|PONT|PMST|PMDT|PKT|PHT|PHOT|PGT|PETT|PETST|PET|PDT|OMST|OMSST|NZST|NZDT|NUT|NST|NPT|NOVT|NOVST|NFT|NDT|NCT|MYT|MVT|MUT|MST|MSK|MSD|MMT|MHT|MEZ|MESZ|MDT|MAWT|MART|MAGT|MAGST|LINT|LHST|LHDT|KUYT|KST|KRAT|KRAST|KGT|JST|IST|IRST|IRKT|IRKST|IRDT|IOT|IDT|ICT|HOVT|HNY|HNT|HNR|HNP|HNE|HNC|HNA|HLV|HKT|HAY|HAT|HAST|HAR|HAP|HAE|HADT|HAC|HAA|GYT|GST|GMT|GILT|GFT|GET|GAMT|GALT|FNT|FKT|FKST|FJT|FJST|EST|EGT|EGST|EET|EEST|EDT|ECT|EAT|EAST|EASST|DAVT|ChST|CXT|CVT|CST|COT|CLT|CLST|CKT|CHAST|CHADT|CET|CEST|CDT|CCT|CAT|CAST|BTT|BST|BRT|BRST|BOT|BNT|AZT|AZST|AZOT|AZOST|AWST|AWDT|AST|ART|AQTT|ANAT|ANAST|AMT|AMST|ALMT|AKST|AKDT|AFT|AEST|AEDT|ADT|ACST|ACDT)|(?-i)ET)"
  {:dim :timezone
   :value (-> %1 :groups first clojure.string/upper-case)}

  "<time> timezone"
  [(dim :time) (dim :timezone)]
  (set-timezone %1 (:value %2))

  ; Intervals

  "dd-dd <month>(interval)"
  [#"(3[01]|[12]\d|0?[1-9])" #"\-|au|jusqu'au" #"(3[01]|[12]\d|0?[1-9])" {:form :month}]
  (interval (intersect %4 (day-of-month (Integer/parseInt (-> %1 :groups first))))
            (intersect %4 (day-of-month (Integer/parseInt (-> %3 :groups first))))
            true)

  "<datetime>-dd <month>(interval)"
  [{:dim :time} #"\-|au|jusqu'au" #"(3[01]|[12]\d|0?[1-9])" {:form :month}]
  (interval (intersect %4 %1)
    (intersect %4 (day-of-month (Integer/parseInt (-> %3 :groups first))))
    true)

  "<datetime>-<day-of-week> dd <month>(interval)"
  [{:dim :time} #"\-|au|jusqu'au" {:form :day-of-week} #"(3[01]|[12]\d|0?[1-9])" {:form :month}]
  (interval (intersect %5 %1)
    (intersect %5 (day-of-month (Integer/parseInt (-> %4 :groups first))))
    true)

  "<day-of-week> 1er-<day-of-week> dd <month>(interval)"
  [{:form :day-of-week} #"premier|prem\.?|1er|1 er" #"\-|au|jusqu'au" {:form :day-of-week} #"(3[01]|[12]\d|0?[1-9])" {:form :month}]
  (interval (intersect %6 (day-of-month 1))
    (intersect %6 (day-of-month (Integer/parseInt (-> %5 :groups first))))
    true)

  "du dd-<day-of-week> dd <month>(interval)"
  [#"du" #"(3[01]|[12]\d|0?[1-9])" #"\-|au|jusqu'au" {:form :day-of-week} #"(3[01]|[12]\d|0?[1-9])" {:form :month}]
  (interval (intersect %6 (day-of-month (Integer/parseInt (-> %2 :groups first))))
    (intersect %6 (day-of-month (Integer/parseInt (-> %5 :groups first))))
    true)

  "du <datetime>-<day-of-week> dd <month>(interval)"
  [#"du" {:dim :time} #"\-|au|jusqu'au" {:form :day-of-week} #"(3[01]|[12]\d|0?[1-9])" {:form :month}]
  (interval (intersect %6 %2)
    (intersect %6 (day-of-month (Integer/parseInt (-> %5 :groups first))))
    true)

  "entre dd et dd <month>(interval)"
  [#"entre( le)?" #"(3[01]|[12]\d|0?[1-9])" #"et( le)?" #"(3[01]|[12]\d|0?[1-9])" {:form :month}]
  (interval (intersect %5 (day-of-month (Integer/parseInt (-> %2 :groups first))))
            (intersect %5 (day-of-month (Integer/parseInt (-> %4 :groups first))))
            true)

  "du dd au dd(interval)"
  [#"du" #"(3[01]|[12]\d|0?[1-9])" #"au|jusqu'au" #"(3[01]|[12]\d|0?[1-9])"]
  (interval (day-of-month (Integer/parseInt (-> %2 :groups first)))
    (day-of-month (Integer/parseInt (-> %4 :groups first)))
    true)

  "fin <named-month>(interval)"
  [#"fin( du mois d[e']? ?)?" {:form :month}]
  (interval (intersect %2 (day-of-month 25))
         (cycle-last-of  {:dim :cycle :grain :day} %2)
          true)

  "début <named-month>(interval)"
  [#"début( du mois d[e'] ?)?" {:form :month}]
  (interval (intersect %2 (day-of-month 1))
        (intersect %2 (day-of-month 5))
        true)

  "première quinzaine de <named-month>(interval)"
  [#"(premi[èe]re|1 ?[èe]re) (quinzaine|15 ?aine) d[e']" {:form :month}]
  (interval (intersect %2 (day-of-month 1))
        (intersect %2 (day-of-month 14))
              true)

  "deuxième quinzaine de <named-month>(interval)"
  [#"(deuxi[èe]me|2 ?[èe]me) (quinzaine|15 ?aine) d[e']" {:form :month}]
  (interval (intersect %2 (day-of-month 15))
        (cycle-last-of {:dim :cycle :grain :day} %2)
              true)
  "<named-month>"
  [#"(?i)mi[- ]" {:form :month}]
  (interval (intersect %2 (day-of-month 10))
            (intersect %2 (day-of-month 19))
             false)

  ; Blocked for :latent time. May need to accept certain latents only, like hours

  "<datetime> - <datetime> (interval)"
  [(dim :time #(not (:latent %))) #"\-|au|jusqu'(au|[aà])" (dim :time #(not (:latent %)))]
  (interval %1 %3 true)

  "de <datetime> - <datetime> (interval)"
  [#"(?i)de|depuis|du" (dim :time) #"\-|au|jusqu'(au|[aà])" (dim :time)]
  (interval %2 %4 true)

  "entre <datetime> et <datetime> (interval)"
  [#"(?i)entre" (dim :time) #"et" (dim :time)]
  (interval %2 %4 true)

  ; Specific for time-of-day, to help resolve ambiguities

  "<time-of-day> - <time-of-day> (interval)"
  [{:form :time-of-day} #"\-|[aà]|au|jusqu'(au|[aà])" {:form :time-of-day}]
  (interval %1 %3 true)

  "de <time-of-day> - <time-of-day> (interval)"
  [#"(?i)(midi )?de" {:form :time-of-day} #"\-|[aà]|au|jusqu'(au|[aà])" {:form :time-of-day}] ;hack to fix midi + interval intersection
  (interval %2 %4 true)

  "entre <time-of-day> et <time-of-day> (interval)"
  [#"(?i)entre" {:form :time-of-day} #"et" {:form :time-of-day}]
  (interval %2 %4 true)

  ; this should be a one-sided interval
  ;"avant <time-of-day>(interval)"
  ;[#"(?i)avant" {:form :time-of-day}]
  ;(interval (cycle-nth :second 0) %2 false)

  ; Specific for within duration... Would need to be reworked to adapt the grain
  "d'ici <duration>"
  [#"(?i)d'ici|dans l('|es?)" (dim :duration)]
  (interval (cycle-nth :second 0) (in-duration (:value %2)) false)

  ; One-sided Intervals
  "avant <time-of-day>"
  [#"(?i)(n[ ']importe quand )?(avant|jusqu'(a|à))" (dim :time)]
  (merge %2 {:direction :before})

  "après <time-of-day>"
  [#"(?i)(apr(e|è)s|(a|à) partir de)" (dim :time)]
  (merge %2 {:direction :after})

  "après le <day-of-month>"
  [#"(?i)(apr(e|è)s le|(a|à) partir du)" (integer 1 31)]
  (merge (day-of-month (:value %2)) {:direction :after})

)
