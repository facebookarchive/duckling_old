(
  ;;
  ;; Integers
  ;;
  "integer 0"
  #"(?i)(нуль)"
  {:dim :number :integer true :value 0}

  "integer 1"
  #"(?i)(один|одна|одну|одне|одного)"
  {:dim :number :integer true :value 1}

  "integer 2"
  #"(?i)(два|дві|двоє|пара|пару|парочку|парочка)"
  {:dim :number :integer true :value 2}

  "integer (3..19)"
  #"(?i)(три|чотирнадцять|чотири|п‘ятнадцять|п‘ять|шістнадцять|шість|сімнадцять|сім|вісімнадцять|вісім|дев‘ятнадцять|дев‘ять|десять|одинадцять|дванадцять|тринадцять)"
  {:dim :number
   :integer true
   :value (get {"три" 3 "чотири" 4 "п‘ять" 5 "шість" 6 "сім" 7 "вісім" 8 "дев‘ять" 9 "десять" 10 "одинадцять" 11
                "дванадцять" 12 "тринадцять" 13 "чотирнадцять" 14 "п‘ятнадцять" 15 "шістнадцять" 16
                "сімнадцять" 17 "вісімнадцять" 18 "дев‘ятнадцять" 19}
               (-> %1 :groups first clojure.string/lower-case))}

  "integer (20..90)"
  #"(?i)(двадцять|тридцять|сорок|п‘ятдесят|шістдесят|сімдесят|вісімдесят|дев‘яносто)"
  {:dim :number
   :integer true
   :value (get {"двадцять" 20 "тридцять" 30 "сорок" 40 "п‘ятдесят" 50 "шістдесят" 60
                "сімдесят" 70 "вісімдесят" 80 "дев‘яносто" 90}
               (-> %1 :groups first clojure.string/lower-case))}

  "integer (100..900)"
  #"(?i)(сто|двісті|триста|чотириста|п‘ятсот|шістсот|сімсот|вісімсот|дев‘ятсот)"
  {:dim :number
   :integer true
   :value (get {"сто" 100 "двісті" 200 "триста" 300 "чотириста" 400 "п‘ятсот" 500
                "шістсот" 600 "сімсот" 700 "вісімсот" 800 "дев‘ятсот" 900}
               (-> %1 :groups first clojure.string/lower-case))}

  "integer 21..99"
  [(integer 10 90 #(#{20 30 40 50 60 70 80 90} (:value %))) (integer 1 9)]
  {:dim :number
   :integer true
   :value (+ (:value %1) (:value %2))}

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

  ;;
  ;; Decimals
  ;;

  "decimal number"
  #"(\d*\.\d+)"
  {:dim :number
   :value (Double/parseDouble (first (:groups %1)))}

  "number dot number"
  [(dim :number #(not (:number-prefixed %))) #"(?i)крапка" (dim :number #(not (:number-suffixed %)))]
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
  "numbers prefix with -, minus"
  [#"(?i)-|мінус\s?" (dim :number #(not (:number-prefixed %)))]
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
  [(dim :number #(not (:number-suffixed %))) #"(?i)([кмг]|[КМГ])(?=[\W\$€]|$)"]
  (let [multiplier (get {"к" 1000 "м" 1000000 "г" 1000000000}
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


  "ordinals (first..19th)"
  #"(?i)(перш|друг|трет|четверт|п‘ят|шост|сьом|восьм|дев‘ят|десят|одинадцят|дванадцят|тринадцят|чотирнадцят|п‘ятнадцят|шістнадцят|сімнадцят|вісімнадцят|дев‘ятнадцят|двадцят)(ий|ій|а|я|е|є)"
  {:dim :ordinal
   :value (get {"перш" 1 "друг" 2 "трет" 3 "четверт" 4 "п‘ят" 5
                "шост" 6 "сьом" 7 "восьм" 8 "дев‘ят" 9 "десят" 10 "одинадцят" 11
                "дванадцят" 12 "тринадцят" 13 "чотирнадцят" 14 "п‘ятнадцят" 15
                "шістнадцят" 16 "сімнадцят" 17 "вісімнадцят" 18
                "дев‘ятнадцят" 19 "двадцят" 20}
               (-> %1 :groups first clojure.string/lower-case))}

  "ordinal 21..99"
  [ #"(?i)(двадцять|тридцять|сорок|п‘ятдесят|шістьдесят|сімдесят|вісімдесят|дев‘яносто)"
   #"(?i)(перш|друг|трет|четверт|п‘ят|шост|сьом|восьм|дев‘ят)(ий|ій|а|я|е|є)"]
  {:dim :ordinal
   :value (+ (get {"двадцять" 20 "тридцять" 30 "сорок" 40 "п‘ятдесят" 50 "шістдесят" 60
                   "сімдесят" 70 "вісімдесят" 80 "дев‘яносто" 90}
                  (-> %1 :groups first clojure.string/lower-case))
             (get {"перш" 1 "друг" 2 "трет" 3 "четверт" 4 "п‘ят" 5
                   "шост" 6 "сьом" 7 "восьм" 8 "дев‘ят" 9}
                  (-> %2 :groups first clojure.string/lower-case)))}

  "ordinal (digits)"
  #"0*(\d+)-?(?i)((и|і)?й|а|я|е|є)"
  {:dim :ordinal
   :value (read-string (first (:groups %1)))}  ; read-string not the safest



  )
