(

  "intersect"
  [(dim :number :grain #(> (:grain %) 1)) (dim :number)] ; grain 1 are taken care of by specific rule
  (compose-numbers %1 %2)

  "intersect (with and)"
  [(dim :number :grain #(> (:grain %) 1)) #"(?i)and" (dim :number)] ; grain 1 are taken care of by specific rule
  (compose-numbers %1 %3)

 ;;
 ;; Integers
 ;;

  "integer (0..19)"
  #"(?i)(none|zilch|naught|nought|nil|zero|one|two|three|fourteen|four|five|sixteen|six|seventeen|seven|eighteen|eight|nineteen|nine|eleven|twelve|thirteen|fifteen)"
  ; fourteen must be before four, or it won't work because the regex will stop at four
  {:dim :number
   :integer true
   :value (get {"none" 0 "zilch" 0 "naught" 0 "nought" 0 "nil" 0 "zero" 0 "one" 1 "two" 2 "three" 3 "four" 4 "five" 5
              "six" 6 "seven" 7 "eight" 8 "nine" 9 "ten" 10 "eleven" 11
              "twelve" 12 "thirteen" 13 "fourteen" 14 "fifteen" 15 "sixteen" 16
              "seventeen" 17 "eighteen" 18 "nineteen" 19}
              (-> %1 :groups first clojure.string/lower-case))}

  "ten"
  #"(?i)ten"
  {:dim :number :integer true :value 10 :grain 1}

  "single"
  #"(?i)single"
  {:dim :number :integer true :value 1 :grain 1}

  "a pair"
  #"(?i)a pair( of)?"
  {:dim :number :integer true :value 2 :grain 1}

  "dozen"
  #"(?i)dozen"
  {:dim :number :integer true :value 12 :grain 1 :grouping true} ;;restrict composition and prevent "2 12"

  "hundred"
  #"(?i)hundreds?"
  {:dim :number :integer true :value 100 :grain 2}

  "thousand"
  #"(?i)thousands?"
  {:dim :number :integer true :value 1000 :grain 3}

  "million"
  #"(?i)millions?"
  {:dim :number :integer true :value 1000000 :grain 6}

  "couple"
  #"(a )?couple( of)?"
  {:dim :number :integer true :value 2}

  "few" ; TODO set assumption
  #"(a )?few"
  {:dim :number :integer true :precision :approximate :value 3}

  "integer (20..90)"
  #"(?i)(twenty|thirty|fou?rty|fifty|sixty|seventy|eighty|ninety)"
  {:dim :number
   :integer true
   :value (get {"twenty" 20 "thirty" 30 "fourty" 40 "forty" 40 "fifty" 50 "sixty" 60
              "seventy" 70 "eighty" 80 "ninety" 90}
             (-> %1 :groups first clojure.string/lower-case))
   :grain 1}

  "integer 21..99"
  [(integer 10 90 #(#{20 30 40 50 60 70 80 90} (:value %))) (integer 1 9)]
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
  "special composition for missing hundreds like in one twenty two"
  [(integer 1 9) (integer 10 99)] ; grain 1 are taken care of by specific rule
  {:dim :number
   :integer true
   :value (+ (* (:value %1) 100) (:value %2))
   :grain 1}


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
  [(dim :number #(not (:number-prefixed %))) #"(?i)dot|point" (dim :number #(not (:number-suffixed %)))]
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
  [#"(?i)-|minus\s?|negative\s?" (dim :number #(not (:number-prefixed %)))]
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

  "ordinals (first..31st)"
  #"(?i)(first|second|third|fourth|fifth|sixth|seventh|eighth|ninth|tenth|eleventh|twelfth|thirteenth|fourteenth|fifteenth|sixteenth|seventeenth|eighteenth|nineteenth|twentieth|twenty-first|twenty-second|twenty-third|twenty-fourth|twenty-fifth|twenty-sixth|twenty-seventh|twenty-eighth|twenty-ninth|thirtieth|thirty-first)"
  {:dim :ordinal
   :value (get {"first" 1 "second" 2 "third" 3 "fourth" 4 "fifth" 5
              "sixth" 6 "seventh" 7 "eighth" 8 "ninth" 9 "tenth" 10 "eleventh" 11
              "twelfth" 12 "thirteenth" 13 "fourteenth" 14 "fifteenth" 15 "sixteenth" 16
              "seventeenth" 17 "eighteenth" 18 "nineteenth" 19 "twentieth" 20 "twenty-first" 21
              "twenty-second" 22 "twenty-third" 23 "twenty-fourth" 24 "twenty-fifth" 25
              "twenty-sixth" 26 "twenty-seventh" 27 "twenty-eighth" 28 "twenty-ninth" 29
              "thirtieth" 30 "thirty-first" 31}
              (-> %1 :groups first clojure.string/lower-case))}

  "ordinal (digits)"
  #"0*(\d+) ?(st|nd|rd|th)"
  {:dim :ordinal
   :value (read-string (first (:groups %1)))}  ; read-string not the safest


  )
