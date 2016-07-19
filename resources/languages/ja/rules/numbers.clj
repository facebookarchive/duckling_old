(
 ;;
 ;; Integers
 ;;

  "integer (0..10)"
  #"ゼロ|零|一|二|三|四|五|六|七|八|九|十"
  {:dim :number
   :integer true
   :value (get {"ゼロ" 0 "零" 0 "一" 1 "二" 2 "三" 3 "四" 4
              "五" 5 "六" 6 "七" 7 "八" 8 "九" 9 "十" 10}
              (:text %1))}

  "integer (11..19)"
  [#"十" (integer 1 9)]
  {:dim :number
   :integer true
   :value (+ 10 (:value %2))}

  "integer (20..90)"
  [(integer 2 9) #"十"]
  {:dim :number
   :integer true
   :value (* (:value %1) 10)}

   "integer 21..99"
  [(integer 20 90 #(#{20 30 40 50 60 70 80 90} (:value %))) (integer 1 9)]
  {:dim :number
   :integer true
   :value (+ (:value %1) (:value %2))}

  "integer (100)"
  #"百"
  {:dim :number
   :integer true
   :value (get {"百" 100}
              (:text %1))}

  "integer (100..199)"
  [#"百" (integer 1 99)]
  {:dim :number
   :integer true
   :value (+ 100 (:value %2))}

  "integer (200..900)"
  [(integer 2 9) #"百"]
  {:dim :number
   :integer true
   :value (* (:value %1) 100)}

   "integer 201..999"
  [(integer 200 900 #(#{200 300 400 500 600 700 800 900} (:value %))) (integer 1 99)]
  {:dim :number
   :integer true
   :value (+ (:value %1) (:value %2))}

  "integer (1000)"
  #"千"
  {:dim :number
   :integer true
   :value (get {"千" 1000}
              (:text %1))}

  "integer (1000..1999)"
  [#"千" (integer 1 999)]
  {:dim :number
   :integer true
   :value (+ 1000 (:value %2))}

  "integer (2000..9000)"
  [(integer 2 9) #"千"]
  {:dim :number
   :integer true
   :value (* (:value %1) 1000)}

   "integer 2001..9999"
  [(integer 2000 9000 #(#{2000 3000 4000 5000 6000 7000 8000 9000} (:value %))) (integer 1 999)]
  {:dim :number
   :integer true
   :value (+ (:value %1) (:value %2))}

  "integer (10000)"
  #"万"
  {:dim :number
   :integer true
   :value (get {"万" 10000}
              (:text %1))}

  "integer (10000..19999)"
  [#"万" (integer 1 9999)]
  {:dim :number
   :integer true
   :value (+ 10000 (:value %2))}

  "integer (20000..90000)"
  [(integer 2 9) #"万"]
  {:dim :number
   :integer true
   :value (* (:value %1) 10000)}

   "integer 20001..99999"
  [(integer 20000 90000 #(#{20000 30000 40000 50000 60000 70000 80000 90000} (:value %))) (integer 1 9999)]
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

  "<number>个" ; any number followed by this is a number
  [(dim :number) #"个"]
  %1

  ;;
  ;; Decimals
  ;;

  "decimal number"
  #"(\d*\.\d+)"
  {:dim :number
   :value (Double/parseDouble (first (:groups %1)))}

  "decimal with thousands separator"
  #"(\d+(,\d\d\d)+\.\d+)"
  {:dim :number
   :value (-> (:groups %1)
            first
            (clojure.string/replace #"," "")
            Double/parseDouble)}

  ;; negative number
  "numbers prefix with -, negative or minus"
  [#"(?i)-|マイナス\s?|負\s?" (dim :number #(not (:number-prefixed %)))]
  (let [multiplier -1
        value      (* (:value %2) multiplier)
        int?       (zero? (mod value 1)) ; often true, but we could have 1.1111K
        value      (if int? (long value) value)] ; cleaner if we have the right type
    (assoc %2 :value value
              :integer int?
              :number-prefixed true)) ; prevent "- -3km" to be 3 billions


  ;; suffixes

  "numbers suffixes (K, M, G, 千, 万)"
  [(dim :number #(not (:number-suffixed %))) #"(?i)([kmg])"]
  (let [multiplier (get {"k" 1000 "m" 1000000 "g" 1000000000 "千" 1000 "万" 10000}
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

  "ordinal (digits)"
  [#"第" (dim :number)]
  {:dim :ordinal
   :value (:value %2)}

  ;; Already covered by rules above

  ;; number with quantity

  "integer (0..10)"
  #"[ゼロ|零|一|二|三|四|五|六|七|八|九|十][番]"
  {:dim :number
   :integer true
   :value (get {"零番" 0 "一番" 1 "二番" 2
              "三番" 3 "四番" 4
              "五番" 5 "六番" 6 "七番" 7 "八番" 8
              "九番" 9 "十番" 10}
              (:text %1))}


  )
