(
  ;; generic

  "intersect"
  [(dim :time #(not (:latent %))) (dim :time #(not (:latent %)))] ; sequence of two tokens with a time dimension
  (intersect %1 %2)

  ; FIXME
  ; same thing, with "of" in between like "Sunday of last week"
  ;  "intersect by \"of\", \"from\", \"'s\""
  ;  [(dim :time #(not (:latent %))) #"(?i)of|from|for|'s" (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  ;  (intersect %1 %3)

  ; mostly for January 12, 2005
  ; this is a separate rule, because commas separate very specific tokens
  ; so we want this rule's classifier to learn this
  "intersect by \",\""
  [(dim :time #(not (:latent %))) #"," (dim :time #(not (:latent %)))] ; sequence of two tokens with a time fn
  (intersect %1 %3)

  "ar <date>" ; ar Wed, March 23 ;FIXME
  [#"(?i)ar" (dim :time)]
  %2 ; does NOT dissoc latent

  "on a named-day" ; ar an luan
  [#"(?i)ar an" {:form :day-of-week}]
  %2 ; does NOT dissoc latent

  "dé named-day" ; dé luain
  [#"(?i)d[ée]" {:form :day-of-week}]
  %2 ; does NOT dissoc latent

  "an named-day" ; an luan
  [#"(?i)an" {:form :day-of-week}]
  %2 ; does NOT dissoc latent


  ;;;;;;;;;;;;;;;;;;;
  ;; Named things


  "named-day"
  #"(?i)luai?n|lu\.?"
  (day-of-week 1)

  "named-day"
  #"(?i)mh?[áa]irt|m[áa]?\.?"
  (day-of-week 2)

  "named-day"
  #"(?i)ch?[ée]adaoin|c[ée]\.?"
  (day-of-week 3)

  "named-day"
  #"(?i)d[ée]ardaoin|d[ée]?\.?"
  (day-of-week 4)

  "named-day"
  #"(?i)h?aoine|ao\.?"
  (day-of-week 5)

  "named-day"
  #"(?i)sathai?rn|sa\.?"
  (day-of-week 6)

  "named-day"
  #"(?i)domhnai?[cg]h|do\.?"
  (day-of-week 7)

  "named-month"
  #"(?i)(mh?[íi] )?(an )?t?ean[áa]ir|ean\.?"
  (month 1)

  "named-month"
  #"(?i)(mh?[íi] )?(na )?feabhra|fea\.?"
  (month 2)

  "named-month"
  #"(?i)(mh?[íi] )?(an )?mh?[áa]rta|m[áa]r\.?"
  (month 3)

  "named-month"
  #"(?i)(mh?[íi] )?(an )?t?aibre[áa]i?n|abr\.?"
  (month 4)

  "named-month"
  #"(?i)(mh?[íi] )?(na )?bh?ealtaine|bea\.?"
  (month 5)

  "named-month"
  #"(?i)(mh?[íi] )?(an )?mh?eith(ea|i)mh|mei\.?"
  (month 6)

  "named-month"
  #"(?i)(mh?[íi] )?i[úu]il|i[úu]i\.?"
  (month 7)

  "named-month"
  #"(?i)(mh?[íi] )?(na )?l[úu]nasa|l[úu]n\.?"
  (month 8)

  "named-month"
  #"(?i)(mh?[íi] )?mh?e[áa]n f[óo]mhair|mef?\.?"
  (month 9)

  "named-month"
  #"(?i)(mh?[íi] )?dh?eireadh f[óo]mhair|def?\.?"
  (month 10)

  "named-month"
  #"(?i)(mh?[íi] )?(na )?samh(ain|na)|sam\.?"
  (month 11)

  "named-month"
  #"(?i)(mh?[íi] )?(na )?nollai?g|nol\.?"
  (month 12)

  ; Holiday TODO: check online holidays
  ; or define dynamic rule (last thursday of october..)

  "An Nollaig" ; "Mí na Nollag" is literally "month of Christmas", so collision with month
  [#"(?i)(l[áa] |an )?(nollai?g)"]
  (month-day 12 25)

  "Nollaig na mBan"
  [#"(?i)(l[áa] |an )?nollaig (bheag|na mban)"]
  (month-day 1 6)

  "Lá Fhéile Vailintín"
  [#"(?i)(l[áa] )?(fh[eé]ile|'?le) vailint[íi]n"]
  (month-day 2 14)

  "Lá Fhéile Pádraig"
  [#"(?i)(l[áa] )?(fh[eé]ile|'?le) ph?[áa]draig"]
  (month-day 3 17)

  "Lá Fhéile Bríde"
  [#"(?i)(l[áa] )?(fh[eé]ile|'?le) bh?r[íi]de"]
  (month-day 2 1)

  "Lá na nAithreacha"; third Sunday of June
  [#"(?i)l[áa] na naithreacha"]
  (intersect (day-of-week 7) (month 6) (cycle-nth-after :week 2 (month-day 6 1)))

;  "Mother's Day"; fourth Sunday of Lent. Need Lent.
;  #"(?i)mother'?s? day"
;  (intersect (day-of-week 7) (month 5) (cycle-nth-after :week 1 (month-day 5 1)))
  "absorption of , after named day"
  [{:form :day-of-week} #","]
  %1

  "anois"
  #"(?i)anois|(ag an (t-?)?am seo)"
  (cycle-nth :second 0)

  "inniu"
  #"(?i)inniu"
  (cycle-nth :day 0)

  "amárach"
  #"(?i)am[áa]rach"
  (cycle-nth :day 1)

  "arú amárach"
  #"(?i)ar[úu] am[áa]rach"
  (cycle-nth :day 2)

  "inné"
  #"(?i)inn[ée]"
  (cycle-nth :day -1)

  "arú inné"
  #"(?i)ar[úu] inn[ée]"
  (cycle-nth :day -2)

  "<time> seo chugainn"
  [(dim :time #(not (:latent %))) #"(?i)seo (chugainn|at[aá] ag teacht)"]
  (pred-nth-not-immediate %1 0)

  "<time> seo chaite"
  [(dim :time) #"(?i)seo ch?aite"]
  (pred-nth %1 -1)

  "<time> seo"
  [(dim :time) #"(?i)seo"]
  (pred-nth %1 0)

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
  ; - an 5ú
  ; - 6ú Feabhra
  ; - 6ú lá de Feabhra
  ; - dd/mm (and other numerical formats like yyyy-mm-dd etc.)
  ; In general we are flexible and accept both ordinals (3rd) and numbers (3)

  "an <day-of-month> (ordinal)" ; this one is not latent
  [#"(?i)an|na" (dim :ordinal #(<= 1 (:value %) 31))]
  (day-of-month (:value %2))

  "<day-of-month> (ordinal)" ; this one is latent
  [(dim :ordinal #(<= 1 (:value %) 31))]
  (assoc (day-of-month (:value %1)) :latent true)

  "an <day-of-month> (non ordinal)" ; this one is latent
  [#"(?i)an|na" (integer 1 31)]
  (assoc (day-of-month (:value %2)) :latent true)

  "<day-of-month>(ordinal) <named-month>" ; 12ú feabhra
  [(dim :ordinal #(<= 1 (:value %) 31)) {:form :month}]
  (intersect %2 (day-of-month (:value %1)))

  "<day-of-month>(ordinal) <named-month> year" ; 12ú feabhra 2012
  [(dim :ordinal #(<= 1 (:value %) 31)) {:form :month} #"(\d{2,4})"]
  (intersect %2 (day-of-month (:value %1)) (year (Integer/parseInt(first (:groups %3)))))

  ; Formatted dates and times

  "dd/mm/yyyy"
  #"(3[01]|[12]\d|0?[1-9])[-/](0?[1-9]|1[0-2])[/-](\d{2,4})"
  (parse-dmy (first (:groups %1)) (second (:groups %1)) (nth (:groups %1) 2) true)

  "yyyy-mm-dd"
  #"(\d{2,4})-(0?[1-9]|1[0-2])-(3[01]|[12]\d|0?[1-9])"
  (parse-dmy (nth (:groups %1) 2) (second (:groups %1)) (first (:groups %1)) true)

  "dd/mm"
  #"(3[01]|[12]\d|0?[1-9])/(0?[1-9]|1[0-2])"
  (parse-dmy (first (:groups %1)) (second (:groups %1)) nil true)

)
