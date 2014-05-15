(
   "near Guerrero street SF"
   #"(?i)near(.+)"
  (pnl (first (:groups %1)) nil)

  "whithin 2 miles"
  [#"(?i)within" {:dim :unit :cat :length}]
  (pnl nil (:val %2))

  "whithin 2 miles of Guerrero street SF"
  [(pnl?) #"(?i)(of|from)(.+)"]
  (pnl (second (:groups %2)) (:n %1))
   )
