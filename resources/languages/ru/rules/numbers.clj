(
 ;;
 ;; Integers
 ;;
  "integer 0"
  #"(?i)(ноль)"
  {:dim :number :integer true :value 0}

  "integer 1"
  #"(?i)(один|одна|одну)"
  {:dim :number :integer true :value 1}

  "integer 2"
  #"(?i)(два|две|двое|пара|пару|парочку|парочка)"
  {:dim :number :integer true :value 2}

  "integer (3..19)"
  #"(?i)(три|четырнадцать|четыре|пятнадцать|пять|шестнадцать|шесть|семнадцать|семь|восемнадцать|восемь|девятнадцать|девять|десять|одинадцать|двенадцать|тринадцать)"
  {:dim :number
   :integer true
   :value (get {"три" 3 "четыре" 4 "пять" 5 "шесть" 6 "семь" 7 "восемь" 8 "девять" 9 "десять" 10 "одинадцать" 11
              "двенадцать" 12 "тринадцать" 13 "четырнадцать" 14 "пятнадцать" 15 "шестнадцать" 16
              "семнадцать" 17 "восемнадцать" 18 "девятнадцать" 19}
              (-> %1 :groups first clojure.string/lower-case))}

  "integer (20..90)"
  #"(?i)(двадцать|тридцать|сорок|пятьдесят|шестьдесят|семьдесят|восемьдесят|девяносто)"
  {:dim :number
   :integer true
   :value (get {"двадцать" 20 "тридцать" 30 "сорок" 40 "пятьдесят" 50 "шестьдесят" 60
              "семьдесят" 70 "восемьдесят" 80 "девяносто" 90}
             (-> %1 :groups first clojure.string/lower-case))}

  "integer (100..900)"
  #"(?i)(сто|двести|тристо|четыресто|пятьсот|шестьсот|семьсот|восемьсот|девятьсот)"
  {:dim :number
   :integer true
   :value (get {"сто" 100 "двести" 200 "тристо" 300 "четыресто" 400 "пятьсот" 500
              "шестьсот" 600 "семьсот" 700 "восемьсот" 800 "девятьсот" 900}
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
  [(dim :number #(not (:number-prefixed %))) #"(?i)точка" (dim :number #(not (:number-suffixed %)))]
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
  [#"(?i)-|минус\s?" (dim :number #(not (:number-prefixed %)))]
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
  #"(?i)(перв|втор|трет|четверт|пят|шест|седьм|восьм|девят|десят|одинадцат|двенадцат|тринадцат|четырнадцат|пятнадцат|шестнадцат|семнадцат|восемнадцат|девятнадцат|двадцат)(ый|ой|ий|ая|ое)"
  {:dim :ordinal
   :value (get {"перв" 1 "втор" 2 "трет" 3 "четверт" 4 "пят" 5
              "шест" 6 "седьм" 7 "восьм" 8 "девят" 9 "десят" 10 "одинадцат" 11
              "двенадцат" 12 "тринадцат" 13 "четырнадцат" 14 "пятнадцат" 15
              "шестнадцат" 16 "семнадцат" 17 "восемнадцат" 18
              "девятнадцат" 19 "двадцат" 20}
              (-> %1 :groups first clojure.string/lower-case))}

  "ordinal 21..99"
  [ #"(?i)(двадцать|тридцать|сорок|пятьдесят|шестьдесят|семьдесят|восемьдесят|девяносто)"
    #"(?i)(перв|втор|трет|четверт|пят|шест|седьм|восьм|девят)(ый|ой|ий|ая|ое)"]
  {:dim :ordinal
   :value (+ (get {"двадцать" 20 "тридцать" 30 "сорок" 40 "пятьдесят" 50 "шестьдесят" 60
              "семьдесят" 70 "восемьдесят" 80 "девяносто" 90}
              (-> %1 :groups first clojure.string/lower-case))
              (get {"перв" 1 "втор" 2 "трет" 3 "четверт" 4 "пят" 5
              "шест" 6 "седьм" 7 "восьм" 8 "девят" 9}
              (-> %2 :groups first clojure.string/lower-case)))}

  "ordinal (digits)"
  #"0*(\d+)-?(?i)((ы|о|и)?й|ая|ое)"
  {:dim :ordinal
   :value (read-string (first (:groups %1)))}  ; read-string not the safest



)
