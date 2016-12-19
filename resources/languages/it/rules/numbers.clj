(
  "number (0..19)"
  #"(?i)(zero|nulla|niente|uno|due|tredici|tre|quattro|cinque|sei|sette|otto|nove|dieci|undici|dodici|quattordici|quindici|sedici|diciassette|diciotto|diciannove|un)"
  {:dim :number
   :integer true
   :value (get {"zero" 0 "nulla" 0 "niente" 0 "uno" 1 "un" 1 "due" 2 "tre" 3 "quattro" 4 "cinque" 5 "sei" 6 "sette" 7 "otto" 8 "nove" 9 "dieci" 10 "undici" 11 "dodici" 12 "tredici" 13 "quattordici" 14 "quindici" 15 "sedici" 16 "diciassette" 17 "diciotto" 18 "diciannove" 19}
              (-> %1 :groups first clojure.string/lower-case))}

  "number (20..90)"
  #"(?i)(venti|trenta|quaranta|cinquanta|sessanta|settanta|ottanta|novanta)"
  {:dim :number
   :integer true
   :value (get {"venti" 20 "trenta" 30 "quaranta" 40 "cinquanta" 50 "sessanta" 60 "settanta" 70 "ottanta" 80 "novanta" 90}
             (-> %1 :groups first clojure.string/lower-case))}

  "number (21..29 31..39 41..49 51..59 61..69 71..79 81..89 91..99)"
  [(integer 20 90 #(#{20 30 40 50 60 70 80 90} (:value %))) #"(?i)e" (integer 1 9)]
  {:dim :number
   :integer true
   :value (+ (:value %1) (:value %3))}

  "number (21..29 31..39 41..49 51..59 61..69 71..79 81..89 91..99)"
  #"(?i)((venti|trenta|quaranta|cinquanta|sessanta|settanta|ottanta|novanta)(due|tre|tré|quattro|cinque|sei|sette|nove))|((vent|trent|quarant|cinquant|sessant|settant|ottant|novant)(uno|otto))"
  {:dim :number
   :integer true
   :value (get {"ventuno" 21 "ventidue" 22 "ventitre" 23 "ventitré" 23 "ventiquattro" 24 "venticinque" 25 "ventisei" 26 "ventisette" 27 "ventotto" 28 "ventinove" 29 "trentuno" 31 "trentadue" 32 "trentatre" 33 "trentatré" 33 "trentaquattro" 34 "trentacinque" 35 "trentasei" 36 "trentasette" 37 "trentotto" 38 "trentanove" 39 "quarantuno" 41 "quarantadue" 42 "quarantatre" 43 "quarantatré" 43 "quarantaquattro" 44 "quarantacinque" 45 "quarantasei" 46 "quarantasette" 47 "quarantotto" 48 "quarantanove" 49 "cinquantuno" 51 "cinquantadue" 52 "cinquantatre" 53  "cinquantatré" 53"cinquantaquattro" 54 "cinquantacinque" 55 "cinquantasei" 56 "cinquantasette" 57 "cinquantotto" 58 "cinquantanove" 59 "sessantuno" 61 "sessantadue" 62 "sessantatre" 63 "sessantatré" 63 "sessantaquattro" 64 "sessantacinque" 65 "sessantasei" 66 "sessantasette" 67 "sessantotto" 68 "sessantanove" 69 "settantuno" 71 "settantadue" 72 "settantatre" 73 "settantatré" 73 "settantaquattro" 74 "settantacinque" 75 "settantasei" 76 "settantasette" 77 "settantotto" 78 "settantanove" 79 "ottantuno" 81 "ottantadue" 82 "ottantatre" 83 "ottantatré" 83 "ottantaquattro" 84 "ottantacinque" 85 "ottantasei" 86 "ottantasette" 87 "ottantotto" 88 "ottantanove" 89 "novantuno" 91 "novantadue" 92 "novantatre" 93 "novantatré" 93 "novantaquattro" 94 "novantacinque" 95 "novantasei" 96 "novantasette" 97 "novantotto" 98 "novantanove" 99}
              (-> %1 :groups first clojure.string/lower-case))}

  "number 100..1000 "
  #"(?i)(due|tre|quattro|cinque|sei|sette|otto|nove)?cento|mil(a|le)"
  {:dim :number
   :integer true
   :value (get {"cento" 100 "duecento" 200 "trecento" 300 "quattrocento" 400 "cinquecento" 500 "seicento" 600 "settecento" 700 "ottocento" 800 "novecento" 900 "mille" 1000" mila" 1000}
              (-> %1 :groups first clojure.string/lower-case))}

  "numbers 200..999"
  [(integer 2 9) (integer 100 100) (integer 0 99)]
  {:dim :number
   :integer true
   :value (+ (* (:value %1) (:value %2)) (:value %3))}

  ;; numeric

  "integer (numeric)"
  #"(\d{1,18})"
  {:dim :number
   :integer true
   :value (Long/parseLong (first (:groups %1)))}

  "integer with thousands separator ."
  #"(\d{1,3}(\.\d\d\d){1,5})"
  {:dim :number
   :integer true
   :value (-> (:groups %1)
            first
            (clojure.string/replace #"\." "")
            Long/parseLong)}

  ;;
  ;; Decimals
  ;;

  "decimal number"
  #"(\d*,\d+)"
  {:dim :number
   :value (parse-number-fr (first (:groups %1)))}

  "decimal with thousands separator"
  #"(\d+(\.\d\d\d)+,\d+)"
  {:dim :number
   :value (-> (:groups %1)
            first
            (clojure.string/replace #"\." "")
            parse-number-fr)}

  ;; prefixes
  "numbers prefix with -, negative or minus"
  [#"(?i)-|meno|negativo" (dim :number #(not (:number-prefixed %)))]
  (let [multiplier -1
        value      (* (:value %2) multiplier)
        int?       (zero? (mod value 1)) ; often true, but we could have 1.1111K
        value      (if int? (long value) value)] ; cleaner if we have the right type
    (assoc %2 :value value
              :integer int?
              :number-prefixed true)) ; prevent "- -3km" to be 3 billions

  ;; suffixes

  "numbers suffixes (K, M, G)"
  [(dim :number #(not (:number-suffixed %))) #"(?i)([kmg])(?=[\W\$€]|$)"]
  (let [multiplier (get {"k" 1000 "m" 1000000 "g" 1000000000}
                        (-> %2 :groups first clojure.string/lower-case))
        value      (* (:value %1) multiplier)
        int?       (zero? (mod value 1)) ; often true, but we could have 1.1111K
        value      (if int? (long value) value)] ; cleaner if we have the right type
    (assoc %1 :value value
              :integer int?
              :number-suffixed true)) ; prevent "3km" to be 3 billions

  ;;
  ;; Ordinal numbers
  ;;

  "ordinals (primo..10)"
  #"(?i)((prim|second|terz|quart|quint|sest|settim|ottav|non|decim)(o|a|i|e))"
  {:dim :ordinal
   :value (get {"primo" 1 "secondo" 2 "terzo" 3 "quarto" 4 "quinto" 5 "sesto" 6 "settimo" 7 "ottavo" 8 "nono" 9 "decimo" 10 "prima" 1 "seconda" 2 "terza" 3 "quarta" 4 "quinta" 5 "sesta" 6 "settima" 7 "ottava" 8 "nona" 9 "decima" 10 "primi" 1 "secondi" 2 "terzi" 3 "quarti" 4 "quinti" 5 "sesti" 6 "settimi" 7 "ottavi" 8 "noni" 9 "decimi" 10 "prime" 1 "seconde" 2 "terze" 3 "quarte" 4 "quinte" 5 "seste" 6 "settime" 7 "ottave" 8 "none" 9 "decime" 10}
              (-> %1 :groups first clojure.string/lower-case))}

  "ordinal (digits)"
  #"0*(\d+) ?[ª°°]"
  {:dim :ordinal
   :value (read-string (first (:groups %1)))}  ; read-string not the safest


)
