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
  [#"(?i)dé" {:form :day-of-week}]
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
)
