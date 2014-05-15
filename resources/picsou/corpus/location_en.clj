(
  ; Context map
  ; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:current-position {:lat 34 :lng 234}}

  "near Guerrero street SF"
  "Near Guerrero street SF"
  (place " Guerrero street SF" nil)

  "within 4 miles of Guerrero street SF"
  "Within 4 mile OF Guerrero street SF"
  (place " Guerrero street SF" 6437.36)

  )
