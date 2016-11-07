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

  "half - 반"
  #"반"
  {:dim :number
   :value 0.5}

  "few 몇" ; TODO set assumption
  #"몇"
  {:dim :number :integer true :precision :approximate :value 3}

  "integer - TYPE 1"
  #"[일|이|삼|사|오|육|칠|팔|구|십|백|천|만|억|조]+"
  (let [map-number #(or (get {\일 1 \이 2 \삼 3 \사 4 \오 5 \육 6 \칠 7 \팔 8 \구 9 \천 1 \백 1 \십 1} %) 0)
        get-number #(let [number (re-matches #"(.*천)?(.*백)?(.*십)?(.*)?" (or % ""))]
                      (+ (* 1000 (map-number (first (number 1))))
                         (* 100 (map-number (first (number 2))))
                         (* 10 (map-number (first (number 3))))
                         (map-number (first (number 4)))))
        splited (re-matches #"(.*조)?(.*억)?(.*만)?(.*)?" (:text %1))]
  {:dim :number
   :integer true
   :value (+ (* 1000000000000 (get-number(splited 1)))
             (* 100000000 (get-number(splited 2)))
             (* 10000 (if (= (splited 3) "만") 1 (get-number (splited 3)))) ;sometimes 만is used alone
             (get-number(splited 4)))})

  "integer (1..10) - TYPE 2"
  #"하나|둘|셋|넷|다섯|여섯|일곱|여덟|아홉"
  {:dim :number
   :integer true
   :value (get {"하나" 1 "둘" 2 "셋" 3 "넷" 4 "다섯" 5
                "여섯" 6 "일곱" 7 "여덟" 8 "아홉" 9}
               (:text %1))}

  "integer (1..4) - for ordinals"
  #"한|첫|두|세|네"
  {:dim :number
   :integer true
   :value (get {"한" 1 "첫" 1 "두" 2 "세" 3 "네" 4 }
               (:text %1))}

  "integer (20..90) - TYPE 2 and ordinals"
  #"열|스물|서른|마흔|쉰|예순|일흔|여든|아흔"
  {:dim :number
   :integer true
   :value (get {"열" 10 "스물" 20 "서른" 30 "마흔" 40 "쉰" 50
               "예순" 60 "일흔" 70 "여든" 80 "아흔" 90}
               (:text %1))}

  "integer (21..99) - TYPE 2"
  [(integer 10 90 #(#{10 20 30 40 50 60 70 80 90} (:value %))) (integer 1 9)]
  {:dim :number
   :integer true
   :value (+ (:value %1) (:value %2))}

  ;;
  ;; Decimals
  ;;

  "decimal number"
  #"(\d*\.\d+)"
  {:dim :number
   :value (Double/parseDouble (first (:groups %1)))}

  "number dot number - 삼점사"
  [(dim :number #(not (:number-prefixed %))) #"(점|쩜)([일|이|삼|사|오|육|칠|팔|구|영]+)"]
  {:dim :number
   :value (->> (-> %2 :groups second)
               vec
               (map #(get {\일 "1" \이 "2" \삼 "3" \사 "4" \오 "5"
                           \육 "6" \칠 "7" \팔 "8" \구 "9" \영 "0"} %))
               clojure.string/join
               (str "0.")
               Double/parseDouble
               (+ (:value %1)))}


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

  ;;
  ;; Ordinal numbers
  ;;

  "ordinals (첫번째)"
  [(dim :number) #"번째|째|째번"]
  {:dim :ordinal
   :value (:value %1)}

  ;; fraction
  "fraction"
  [(dim :number #(not (:number-prefixed %))) #"분(의|에)" (dim :number #(not (:number-suffixed %)))]
  {:dim :number
   :value (/ (:value %3) (:value %1))}

  "fraction"
  [(dim :number #(not (:number-prefixed %))) #"/" (dim :number #(not (:number-suffixed %)))]
  {:dim :number
   :value (/ (:value %1) (:value %3))}

)
