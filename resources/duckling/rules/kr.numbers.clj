(
 ;; Numbers (kr)
 ;; Korean number system exactly follows the form of Chinese number system -
 ;; e.g. 20 = "two tens" or 951 = "nine hundreds five tens one".
 ;;
 ;; However, for numbers 00~99 an alternative counting scheme exists! There
 ;; are *two* methods of counting the numbers between 00~99 in Korean, both of
 ;; used frequently. We arbitrarily distinguish to two scheme as "TYPE 1" and
 ;; "TYPE 2" where "TYPE 1" is that of the Chinese number system.
 ;;
 ;; It possible, though unnatural, to "mix" the two counting schemes - e.g.
 ;; 11 = "ten and one" where "ten" is derived from counting scheme and
 ;; "one" from the other. The following rules allow such mix.

 ;;
 ;; Integers
 ;;

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

  "integer 0"
  #"영|공|빵"
  {:dim :number
   :integer true
   :value 0}

  "integer (1..10) - TYPE 1"
  #"일|이|삼|사|오|육|칠|팔|구|십"
  {:dim :number
   :integer true
   :value (get {"일" 1 "이" 2 "삼" 3 "사" 4 "오" 5
                "육" 6 "칠" 7 "팔" 8 "구" 9 "십" 10}
               (:text %1))}

  "integer (1..10) - TYPE 2"
  #"하나|둘|셋|넷|다섯|여섯|일곱|여덟|아홉|열"
  {:dim :number
   :integer true
   :value (get {"하나" 1 "둘" 2 "셋" 3 "넷" 4 "다섯" 5
                "여섯" 6 "일곱" 7 "여덟" 8 "아홉" 9 "열" 10}
               (:text %1))}

  "integer (11..19) - TYPE 1/2"
  [#"십|열" (integer 1 9)]
  {:dim :number
   :integer true
   :value (+ 10 (:value %2))}

  "integer (20..90) - TYPE 1"
  [(integer 2 9) #"십"]
  {:dim :number
   :integer true
   :value (* (:value %1) 10)}

  "integer (20..90) - TYPE 2"
  #"스물|서른|마흔|쉰|예순|일흔|여든|아흔"
  {:dim :number
   :integer true
   :value (get {"스물" 20 "서른" 30 "마흔" 40 "쉰" 50
               "예순" 60 "일흔" 70 "여든" 80 "아흔" 90}
               (:text %1))}

  "integer (21..99)"
  [(integer 20 90 #(#{20 30 40 50 60 70 80 90} (:value %))) (integer 1 9)]
  {:dim :number
   :integer true
   :value (+ (:value %1) (:value %2))}

  "integer 100 1000 10000 100000000"
  #"백|천|만|억"
  {:dim :number
   :integer true
   :value (get {"백" 100 "천" 1000 "만" 10000 "억" 100000000} (:text %1))}

  "integer (200..900) (2000..9000)"
  [(integer 2 9)
   (integer 100 1000 #(#{100 1000} (:value %)))]
  {:dim :number
   :integer true
   :value (* (:value %1) (:value %2))}

  "integer (101..999)"
  [(integer 100 900 #((set (for [n (range 1 10)] (* 100 n))) (:value %)))
   (integer 1 99)]
  {:dim :number
   :integer true
   :value (+ (:value %1) (:value %2))}

  "integer (1001..9999)"
  [(integer 1000 9000 #((set (for [n (range 1 10)] (* 1000 n))) (:value %)))
   (integer 1 999)]
  {:dim :number
   :integer true
   :value (+ (:value %1) (:value %2))}

  "integer (10000..90000) (100000000..900000000)"
  [(integer 1 9)
   (integer 10000 100000000 #(#{10000 100000000} (:value %)))]
  {:dim :number
   :integer true
   :value (* (:value %1) (:value %2))}

  "intger (10001..19999) without the optional prefix"
  [#"만" (integer 1 9999)]
  {:dim :number
   :integer true
   :value (+ 10000 (:value %2))}

  "integer (10001..99999999)"
  [(integer 1 9999) #"만" (integer 1 9999)]
  {:dim :number
   :integer true
   :value (+ (* 10000 (:value %1)) (:value %3))}

  "integer (100000001..999999999999)"
  [(integer 1 9999) #"억" (integer 1 99999999)]
  {:dim :number
   :integer true
   :value (+ (* 100000000 (:value %1)) (:value %3))}

  ;;
  ;; Decimals
  ;;

  "decimal number"
  #"(\d*\.\d+)"
  {:dim :number
   :value (Double/parseDouble (first (:groups %1)))}

  "number dot number (limited to one decimal place)"
  [(dim :number #(not (:number-prefixed %))) #"점" (dim :number #(not (:number-suffixed %)))]
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
  "numbers prefix with -, 마이너스, or 마이나스"
  [#"-|마이너스\s?|마이나스\s?" (dim :number #(not (:number-prefixed %)))]
  (let [multiplier -1
        value      (* (:value %2) multiplier)
        int?       (zero? (mod value 1)) ; often true, but we could have 1.1111K
        value      (if int? (long value) value)] ; cleaner if we have the right type
    (assoc %2 :value value
              :integer int?
              :number-prefixed true)) ; prevent "- -3km" to be 3 billions
)
