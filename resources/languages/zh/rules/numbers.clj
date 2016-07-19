(
 ;;
 ;; Integers
 ;;

  "integer (0..10)"
  #"〇|零|一|二|两|兩|三|四|五|六|七|八|九|十"
  {:dim :number
   :integer true
   :value (get {"〇" 0 "零" 0 "一" 1 "二" 2 "两" 2 "兩" 2 "三" 3 "四" 4
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
  [#"(?i)-|负\s?|負\s?" (dim :number #(not (:number-prefixed %)))]
  (let [multiplier -1
        value      (* (:value %2) multiplier)
        int?       (zero? (mod value 1)) ; often true, but we could have 1.1111K
        value      (if int? (long value) value)] ; cleaner if we have the right type
    (assoc %2 :value value
              :integer int?
              :number-prefixed true)) ; prevent "- -3km" to be 3 billions


  ;; suffixes

  "numbers suffixes (K, M, G)"
  [(dim :number #(not (:number-suffixed %))) #"(?i)([kmg])"]
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

  "ordinal (digits)"
  [#"第" (dim :number)]
  {:dim :ordinal
   :value (:value %2)}

  ;; Already covered by rules above

  ;; number with quantity

  "integer (0..10)"
  #"[〇|零|一|二|两|兩|三|四|五|六|七|八|九|十][个個]"
  {:dim :number
   :integer true
   :value (get {"〇个" 0 "〇個" 0 "零个" 0 "零個" 0 "一个" 1 "一個" 1 "二个" 2 "二個" 2
              "两个" 2 "两個" 2 "兩个" 2 "兩個" 2 "三个" 3 "三個" 3 "四个" 4 "四個" 4
              "五个" 5 "五個" 5 "六个" 6 "六個" 6 "七个" 7 "七個" 7 "八个" 8 "八個" 8
              "九个" 9 "九個" 9 "十个" 10 "十個" 10}
              (:text %1))}


  )
