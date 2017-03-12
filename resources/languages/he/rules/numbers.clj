(
  ; "intersect"
  ; [(dim :number :grain #(> (:grain %) 1)) (dim :number)] ; grain 1 are taken care of by specific rule
  ; (compose-numbers %1 %2)

  "intersect (with and)"
  [(dim :number :grain #(> (:grain %) 1)) #"(?i)ו" (dim :number)] ; grain 1 are taken care of by specific rule
  (compose-numbers %1 %3)

 ;;
 ;; Integers
 ;;

 ;; TODO: Hebrew can make with 1..12
  "integer (0..19)"
  #"(?i)(אפס|כלום|אחד עשר|שתיים עשר|שניים עשר|תרי עשר|שלוש עשר|ארבע עשר|חמש עשר|שש עשר|שבע עשר|שמונה עשר|תשע עשר|אחד|אחת|שני|שניים|שתיים|שלוש|ארבע|חמש|שש|שבע|שמונה|תשע|עשר)"
  ; fourteen must be before four, or it won't work because the regex will stop at four
  {:dim :number
   :integer true
   :value (get {"אפס" 0 "כלום" 0 "אחד" 1 "אחת" 1 "שתיים" 2 "שניים" 2 "שלוש" 3 "שלושה" 3 "ארבע" 4 "חמש" 5
              "שש" 6 "שבע" 7 "שמונה" 8 "תשע" 9 "עשר" 10 "אחד עשר" 11
              "שניים עשר" 12 "שתיים עשר" 12 "תרי עשר" 12 "שלוש עשר" 13 "ארבע עשר" 14 "חמש עשר" 15 "שש עשר" 16
              "שבע עשר" 17 "שמונה עשר" 18 "תשע עשר" 19}
              (-> %1 :groups first clojure.string/lower-case))}

  "ten"
  #"(?i)עשרה?"
  {:dim :number :integer true :value 10 :grain 1}

  "single"
  #"(?i)יחיד"
  {:dim :number :integer true :value 1 :grain 1}

  "couple"
  #"(?i)זוג( של)?"
  {:dim :number :integer true :value 2 :grain 1}

  "hundred"
  #"(?i)(מאה|מאות)"
  {:dim :number :integer true :value 100 :grain 2}

  "thousand"
  #"(?i)(אלף|אלפים)"
  {:dim :number :integer true :value 1000 :grain 3}

  "million"
  #"(?i)(מיליון|מיליונים)"
  {:dim :number :integer true :value 1000000 :grain 6}

  "integer (20..90)"
  #"(?i)(עשרים|שלושים|ארבעים|חמישים|שישים|שבעים|שמונים|תשעים)"
  {:dim :number
   :integer true
   :value (get {"עשרים" 20 "שלושים" 30 "ארבעים" 40 "חמישים" 50 "שישים" 60 "שבעים" 70
              "שמונים" 80 "תשעים" 90}
             (-> %1 :groups first clojure.string/lower-case))
   :grain 1}

  "integer 21..99"
  [(integer 20 90 #(#{20 30 40 50 60 70 80 90} (:value %))) #"(?i)ו" (integer 1 9)]
  {:dim :number
   :integer true
   :value (+  (:value %1) (:value %3))}

  "integer 101..999"
  [(integer 100 900 #(#{100 200 300 400 500 600 700 800 900} (:value %))) (integer 1 99)]
  {:dim :number
   :integer true
   :value (+ (:value %1) (:value %2))}

  "integer (numeric)"
  #"(\d{1,18})"
  {:dim :number
   :integer true
   :value (Long/parseLong (first (:groups %1)))}

  "integer with thousands separator ,"
  #"(\d{1,3}(,\d\d\d){1,5})"
  {:dim :number
   :integer true
   :value (-> (:groups %1)
            first
            (clojure.string/replace #"," "")
            Long/parseLong)}

  ; composition
  ; "special composition for missing hundreds like in one twenty two"
  ; [(integer 1 9) (integer 10 99)] ; grain 1 are taken care of by specific rule
  ; {:dim :number
  ;  :integer true
  ;  :value (+ (* (:value %1) 100) (:value %2))
  ;  :grain 1}

  "number dozen"
  [(integer 1 10) (dim :number #(:grouping %))]
  {:dim :number
   :integer true
   :value (* (:value %1) (:value %2))
   :grain (:grain %2)}

  "number hundreds"
  [(integer 1 99) (integer 100 100)]
  {:dim :number
   :integer true
   :value (* (:value %1) (:value %2))
   :grain (:grain %2)}

  "number thousands"
  [(integer 1 999) (integer 1000 1000)]
  {:dim :number
   :integer true
   :value (* (:value %1) (:value %2))
   :grain (:grain %2)}

  "number millions"
  [(integer 1 99) (integer 1000000 1000000)]
  {:dim :number
   :integer true
   :value (* (:value %1) (:value %2))
   :grain (:grain %2)}

  ;;
  ;; Decimals
  ;;

  "decimal number"
  #"(\d*\.\d+)"
  {:dim :number
   :value (Double/parseDouble (first (:groups %1)))}

  "number dot number"
  [(dim :number #(not (:number-prefixed %))) #"(?i)נקודה" (dim :number #(not (:number-suffixed %)))]
  {:dim :number
   :value (+ (* 0.1 (:value %3)) (:value %1))}


  "decimal with thousands separator"
  #"(\d+(,\d\d\d)+\.\d+)"
  {:dim :number
   :value (-> (:groups %1)
            first
            (clojure.string/replace #"," "")
            Double/parseDouble)}

  ;; negative number
  "numbers prefix with -, negative or minus"
  [#"(?i)-|מינוס\s?" (dim :number #(not (:number-prefixed %)))]
  (let [multiplier -1
        value      (* (:value %2) multiplier)
        int?       (zero? (mod value 1)) ; often true, but we could have 1.1111K
        value      (if int? (long value) value)] ; cleaner if we have the right type
    (assoc %2 :value value
              :integer int?
              :number-prefixed true)) ; prevent "- -3km" to be 3 billions


  ;; suffixes

  ; note that we check for a space-like char after the M, K or G
  ; to avoid matching 3 Mandarins
  ; "numbers suffixes (K, M, G)"
  ; [(dim :number #(not (:number-suffixed %))) #"(?i)([kmg])(?=[\W\$€]|$)"]
  ; (let [multiplier (get {"k" 1000 "m" 1000000 "g" 1000000000}
  ;                       (-> %2 :groups first clojure.string/lower-case))
  ;       value      (* (:value %1) multiplier)
  ;       int?       (zero? (mod value 1)) ; often true, but we could have 1.1111K
  ;       value      (if int? (long value) value)] ; cleaner if we have the right type
  ;   (assoc %1 :value value
  ;             :integer int?
  ;             :number-suffixed true)) ; prevent "3km" to be 3 billions

  ;;
  ;; Ordinal numbers
  ;;

  ; "ordinals (first..31st)"
  ; #"(?i)(first|second|third|fourth|fifth|sixth|seventh|eighth|ninth|tenth|eleventh|twelfth|thirteenth|fourteenth|fifteenth|sixteenth|seventeenth|eighteenth|nineteenth|twentieth|twenty-first|twenty-second|twenty-third|twenty-fourth|twenty-fifth|twenty-sixth|twenty-seventh|twenty-eighth|twenty-ninth|thirtieth|thirty-first)"
  ; {:dim :ordinal
  ;  :value (get {"first" 1 "second" 2 "third" 3 "fourth" 4 "fifth" 5
  ;             "sixth" 6 "seventh" 7 "eighth" 8 "ninth" 9 "tenth" 10 "eleventh" 11
  ;             "twelfth" 12 "thirteenth" 13 "fourteenth" 14 "fifteenth" 15 "sixteenth" 16
  ;             "seventeenth" 17 "eighteenth" 18 "nineteenth" 19 "twentieth" 20 "twenty-first" 21
  ;             "twenty-second" 22 "twenty-third" 23 "twenty-fourth" 24 "twenty-fifth" 25
  ;             "twenty-sixth" 26 "twenty-seventh" 27 "twenty-eighth" 28 "twenty-ninth" 29
  ;             "thirtieth" 30 "thirty-first" 31}
  ;             (-> %1 :groups first clojure.string/lower-case))}

  ; "ordinal (digits)"
  ; #"0*(\d+) ?(st|nd|rd|th)"
  ; {:dim :ordinal
  ;  :value (read-string (first (:groups %1)))}  ; read-string not the safest


  )
