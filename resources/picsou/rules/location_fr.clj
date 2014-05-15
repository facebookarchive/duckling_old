(
  "proche de Guerrero street SF"
  [#"(?i)proche|pr[eè]s|c[oôòó]t[eé]" #"(?i)de" #"(G.*)"] ;; FIXME
  (pnl (first (:groups %3)) nil)

  "a moins de 2 kilomètres"
  [#"(?i)moins?" #"(?i)de" {:dim :unit :cat :length}]
  (pnl nil (:val %3))

  "a moins de 2 kilomètres de Guerrero street SF"
  [(pnl?) #"(?i)(de|du)(.+)"]
  (pnl (second (:groups %2)) (:n %1))
  )

