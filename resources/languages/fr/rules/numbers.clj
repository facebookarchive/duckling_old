(
  "number (0..16)"
  #"(?i)(z[eé]ro|une?|deux|trois|quatre|cinq|six|sept|huit|neuf|dix|onze|douze|treize|quatorze|quinze|seize)"
  {:dim :number
   :integer true
   :value (get {"zéro" 0 "zero" 0 "un" 1 "une" 1 "deux" 2 "trois" 3 "quatre" 4 "cinq" 5
              "six" 6 "sept" 7 "huit" 8 "neuf" 9 "dix" 10 "onze" 11
              "douze" 12 "treize" 13 "quatorze" 14 "quinze" 15 "seize" 16}
              (-> %1 :groups first clojure.string/lower-case))}

  "number (20..60)"
  #"(?i)(vingt|trente|quarante|cinquante|soixante)"
  {:dim :number
   :integer true
   :value (get {"vingt" 20 "trente" 30 "quarante" 40 "cinquante" 50 "soixante" 60}
             (-> %1 :groups first clojure.string/lower-case))}

  "number (17..19)"
  [(integer 10 10) (integer 7 9)]
  {:dim :number
   :integer true
   :value (+ 10 (:value %2))}

  "number 80" ; separate rule to avoid too many entries in the 'get' map above
  [#"(?i)quatre" #"(?i)vingts?"]
  {:dim :number
   :integer true
   :value 80 }

  "numbers 21 31 41 51"
  [(integer 20 50  #(#{20 30 40 50} (:value %))) #"(?i)et" (integer 1 1)]
  {:dim :number
   :integer true
   :value (+ (:value %1) (:value %3))}

  "numbers 22..29 32..39 .. 52..59"
  [(integer 20 50 #(#{20 30 40 50} (:value %))) (integer 2 9)]
  {:dim :number
   :integer true
   :value (+ (:value %1) (:value %2))}

  "numbers 61 71"
  [(integer 60 60) #"(?i)-?et-?" (integer 1 11 #(#{1 11} (:value %)))]
  {:dim :number
   :integer true
   :value (+ (:value %1) (:value %3))}

  "numbers 81 91"
  [(integer 80 80) (integer 1 11 #(#{1 11} (:value %)))]
  {:dim :number
   :integer true
   :value (+ (:value %1) (:value %2))}

  "numbers 62..69 .. 92..99"
  [(integer 60 80 #(#{60 80} (:value %))) (integer 2 19)]
  {:dim :number
   :integer true
   :value (+ (:value %1) (:value %2))}

  ;; Numeric

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
  [#"(?i)-|moins" (dim :number #(not (:number-prefixed %)))]
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

  "ordinals (premier..seizieme)"
  #"(?i)(premi(ere?|ère)|(deux|trois|quatr|cinqu|six|sept|huit|neuv|dix|onz|douz|treiz|quatorz|quinz|seiz)i[eè]me)"
  {:dim :ordinal
   :value (get {"premier" 1 "premiere" 1 "première" 1 "deuxieme" 2 "troisieme" 3 "quatrieme" 4 "cinquieme" 5 "sixieme" 6 "septieme" 7 "huitieme" 8 "neuvieme" 9 "dixieme" 10 "onzieme" 11 "douzieme" 12 "treizieme" 13 "quatorzieme" 14 "quinzieme" 15 "seizieme" 16 "deuxième" 2 "troisième" 3 "quatrième" 4 "cinquième" 5 "sixième" 6 "septième" 7 "huitième" 8 "neuvième" 9 "dixième" 10 "onzième" 11 "douzième" 12 "treizième" 13 "quatorzième" 14 "quinzième" 15 "seizième" 16}
              (-> %1 :groups first clojure.string/lower-case))}


  "ordinal (digits)"
  #"0*(\d+) ?(ere?|ère|ème|eme)"
  {:dim :ordinal
   :value (read-string (first (:groups %1)))}  ; read-string not the safest


  )
