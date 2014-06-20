(
 ;;
 ;; Integers
 ;;
 
  "integer (0..10)"
  #"〇|零|一|二|三|四|五|六|七|八|九|十"
  {:dim :number
   :integer true
   :val (get {"〇" 0 "零" 0 "一" 1 "二" 2 "三" 3 "四" 4
              "五" 5 "六" 6 "七" 7 "八" 8 "九" 9 "十" 10}
              (:text %1))}

  "integer (11..19)"
  [#"十" (integer 1 9)]
  {:dim :number
   :integer true
   :val (+ 10 (:val %2))}

  "integer (20..90)"
  [(integer 2 9) #"十"]
  {:dim :number
   :integer true
   :val (* (:val %1) 10)}

   "integer 21..99"
  [(integer 20 90 #(#{20 30 40 50 60 70 80 90} (:val %))) (integer 1 9)]
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
   :val (Double/parseDouble (first (:groups %1)))}

  "decimal with thousands separator"
  #"(\d+(,\d\d\d)+\.\d+)"
  {:dim :number
   :val (-> (:groups %1)
            first
            (clojure.string/replace #"," "")
            Double/parseDouble)}

  ;; negative number
  "numbers prefix with -, negative or minus"
  [#"(?i)-|负\s?|負\s?" (dim :number #(not (:number-prefixed %)))]
  (let [multiplier -1
        value      (* (:val %2) multiplier)
        int?       (zero? (mod value 1)) ; often true, but we could have 1.1111K
        value      (if int? (long value) value)] ; cleaner if we have the right type
    (assoc %2 :val value
              :integer int?
              :number-prefixed true)) ; prevent "- -3km" to be 3 billions


  ;; suffixes

  "numbers suffixes (K, M, G)"
  [(dim :number #(not (:number-suffixed %))) #"(?i)([kmg])"]
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

  ;; Already covered by rules above

  
  )
