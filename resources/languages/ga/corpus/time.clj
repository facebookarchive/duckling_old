(
  ; Context map
  ; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:reference-time (time/t -2 2013 2 12 4 30 0)
   :min (time/t -2 1900)
   :max (time/t -2 2100)}

  "anois"
  (datetime 2013 2 12 4 30 00)

  "inniu"
  (datetime 2013 2 12)

  "inné"
  (datetime 2013 2 11)

  "arú inné"
  (datetime 2013 2 10)

  "amárach"
  (datetime 2013 2 13)

  "arú amárach"
  (datetime 2013 2 14)

  "dé luain"
  "an luan"
  "an luan seo"
  (datetime 2013 2 18 :day-of-week 1)

  "an luan seo chugainn"
  "an luan seo atá ag teacht"
  "dé luain seo chugainn"
  (datetime 2013 2 18 :day-of-week 1)
  
  "18/2/2013"
  (datetime 2013 2 18)
)
