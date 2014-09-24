(
 ;;
 ;; Integers
 ;;
 
  "integer (0..19)"
  #"(?i)(naught|nought|zero|one|two|three|fourteen|four|five|sixteen|six|seventeen|seven|eighteen|eight|nineteen|nine|ten|eleven|twelve|thirteen|fifteen)"
  ; fourteen must be before four, or it won't work because the regex will stop at four
  {:dim :number
   :integer true
   :val (get {"naught" 0 "nought" 0 "zero" 0 "one" 1 "two" 2 "three" 3 "four" 4 "five" 5
              "six" 6 "seven" 7 "eight" 8 "nine" 9 "ten" 10 "eleven" 11
              "twelve" 12 "thirteen" 13 "fourteen" 14 "fifteen" 15 "sixteen" 16
              "seventeen" 17 "eighteen" 18 "nineteen" 19}
              (-> %1 :groups first .toLowerCase))}

  "integer (20..90)"
  #"(?i)(twenty|thirty|fou?rty|fifty|sixty|seventy|eighty|ninety)"
  {:dim :number
   :integer true
   :val (get {"twenty" 20 "thirty" 30 "fourty" 40 "forty" 40 "fifty" 50 "sixty" 60
              "seventy" 70 "eighty" 80 "ninety" 90}
             (-> %1 :groups first .toLowerCase))}

  "integer 21..99"
  [(integer 10 90 #(#{20 30 40 50 60 70 80 90} (:val %))) (integer 1 9)]
  {:dim :number
   :integer true
   :val (+ (:val %1) (:val %2))}

  "integer (numeric)"
  #"(\d{1,18})"
  {:dim :number
   :integer true
   :val (Long/parseLong (first (:groups %1)))}
  
  "integer with thousands separator ,"
  #"(\d{1,3}(,\d\d\d){1,5})"
  {:dim :number
   :integer true
   :val (-> (:groups %1)
            first
            (clojure.string/replace #"," "")
            Long/parseLong)}
  
  ;;
  ;; Decimals
  ;;
  
  "decimal number"
  #"(\d*\.\d+)"
  {:dim :number
<<<<<<< Updated upstream
   :val (Double/parseDouble (first (:groups %1)))}
=======
   :value (Double/parseDouble (first (:groups %1)))}

  "number dot number"
  [(dim :number #(not (:number-prefixed %))) #"(?i)dot|point" (dim :number #(not (:number-suffixed %)))]
  {:dim :number
   :value (+ (* 0.1 (:value %3)) (:value %1))}
>>>>>>> Stashed changes

  "decimal with thousands separator"
  #"(\d+(,\d\d\d)+\.\d+)"
  {:dim :number
   :val (-> (:groups %1)
            first
            (clojure.string/replace #"," "")
            Double/parseDouble)}

  ;; negative number
  "numbers prefix with -, negative or minus"
  [#"(?i)-|minus\s?|negative\s?" (dim :number #(not (:number-prefixed %)))]
  (let [multiplier -1
        value      (* (:val %2) multiplier)
        int?       (zero? (mod value 1)) ; often true, but we could have 1.1111K
        value      (if int? (long value) value)] ; cleaner if we have the right type
    (assoc %2 :val value
              :integer int?
              :number-prefixed true)) ; prevent "- -3km" to be 3 billions


  ;; suffixes

  ; note that we check for a space-like char after the M, K or G
  ; to avoid matching 3 Mandarins
  "numbers suffixes (K, M, G)"
  [(dim :number #(not (:number-suffixed %))) #"(?i)([kmg])(?=[\W\$€]|$)"]
  (let [multiplier (get {"k" 1000 "m" 1000000 "g" 1000000000}
                        (-> %2 :groups first .toLowerCase))
        value      (* (:val %1) multiplier)
        int?       (zero? (mod value 1)) ; often true, but we could have 1.1111K
        value      (if int? (long value) value)] ; cleaner if we have the right type
    (assoc %1 :val value
              :integer int?
              :number-suffixed true)) ; prevent "3km" to be 3 billions

  ;;
  ;; Ordinal numbers
  ;;
  
  "ordinals (first..19th)"
  #"(?i)(first|second|third|fourth|fifth|sixth|seventh|eighth|ninth|tenth|eleventh|twelfth|thirteenth|fourteenth|fifteenth|sixteenth|seventeenth|eighteenth|nineteenth)"
  {:dim :ordinal
   :val (get {"first" 1 "second" 2 "third" 3 "fourth" 4 "fifth" 5
              "sixth" 6 "seventh" 7 "eighth" 8 "ninth" 9 "tenth" 10 "eleventh" 11
              "twelfth" 12 "thirteenth" 13 "fourteenth" 14 "fifteenth" 15 "sixteenth" 16
              "seventeenth" 17 "eighteenth" 18 "nineteenth" 19}
              (-> %1 :groups first .toLowerCase))}

  "ordinal (digits)"
  #"0*(\d+) ?(st|nd|rd|th)"
  {:dim :ordinal
   :val (read-string (first (:groups %1)))}  ; read-string not the safest

  
  )
