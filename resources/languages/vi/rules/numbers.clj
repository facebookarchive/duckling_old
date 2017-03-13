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
  #"(?i)(không|một|linh một|lẻ một|hai|linh hai|lẻ hai|ba|linh ba|lẻ ba|bốn|linh bốn|lẻ bốn|năm|linh năm|lẻ năm|sáu|lẻ sáu|linh sáu|bảy|lẻ bảy|linh bảy|tám|linh tám|lẻ tám|chín|linh chín|lẻ chín|mười một|mười hai|mười ba|mười bốn|mười lăm|mười sáu|mười bảy|mười tám|mười chín|mười|linh mười)"
  ; mười một... must be before mười, or it won't work because the regex will stop at mười
  {:dim :number
   :integer true
   :value (get {"không" 0 "một" 1 "linh một" 1 "lẻ một" 1 "hai" 2 "linh hai" 2 "lẻ hai" 2 "ba" 3 "linh ba" 3 "lẻ" 3
                "bốn" 4 "linh bốn" 4 "lẻ bốn" 4 "năm" 5 "lẻ năm" 5 "linh năm" 5
                "sáu" 6 "linh sáu" 6 "lẻ sáu" 6 "bảy" 7 "linh bảy" 7 "lẻ bảy" 7
                "tám" 8 "linh tám" 8 "lẻ tám" 8 "chín" 9 "linh chín" 9 "lẻ chín" 9
                "mười một" 11
                "mười hai" 12 "mười ba" 13 "mười bốn" 14 "mười lăm" 15 "mười sáu" 16
                "mười bảy" 17 "mười tám" 18 "mười chín" 19
                "mười" 10 "linh mười" 10 "lẻ mười" 10}
                (-> %1 :groups first clojure.string/lower-case))}



  "tá"
  #"(?i)tá"
  {:dim :number :integer true :value 12 :grain 1 :grouping true} ;;restrict composition and prevent "2 12"

  "trăm"
  #"(?i)trăm?"
  {:dim :number :integer true :value 100 :grain 2}

  "nghìn"
  #"(?i)nghìn?"
  {:dim :number :integer true :value 1000 :grain 3}

  "triệu"
  #"(?i)triệu?"
  {:dim :number :integer true :value 1000000 :grain 6}

  "tỷ"
  #"(?i)tỷ?"
  {:dim :number :integer true :value 1000000000 :grain 9}

  "numbers 21 31 41 51 61 71 81 91"
  [(integer 20 90  #(#{20 30 40 50 60 70 80 90} (:value %))) #"(?i)mốt"]
  {:dim :number
   :integer true
   :value (+ (:value %1) 1)}

  "numbers 25 35 45 55 65 75 85 95"
  [(integer 20 90  #(#{20 30 40 50 60 70 80 90} (:value %))) #"(?i)lăm"]
  {:dim :number
   :integer true
   :value (+ (:value %1) 5)}

  "integer (20..90)"
  #"(?i)(hai mươi|ba mươi|bốn mươi|năm mươi|sáu mươi|bảy mươi|tám mươi|chín mươi)"
  {:dim :number
   :integer true
   :value (get {"hai mươi" 20 "ba mươi" 30 "bốn mươi" 40 "năm mươi" 50 "sáu mươi" 60
              "bảy mươi" 70 "tám mươi" 80 "chín mươi" 90}
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


  "number tá"
  [(integer 1 10) (dim :number #(:grouping %))]
  {:dim :number
   :integer true
   :value (* (:value %1) (:value %2))
   :grain (:grain %2)}


  "number trăm"
  [(integer 0 9) (integer 100 100)]
  {:dim :number
   :integer true
   :value (* (:value %1) (:value %2))
   :grain (:grain %2)}

  "number nghìn"
  [(integer 1 999) (integer 1000 1000)]
  {:dim :number
   :integer true
   :value (* (:value %1) (:value %2))
   :grain (:grain %2)}

  "number triệu"
  [(integer 1 999) (integer 1000000 1000000)]
  {:dim :number
   :integer true
   :value (* (:value %1) (:value %2))
   :grain (:grain %2)}

  "number tỷ"
  [(integer 1 9999999999) (integer 1000000000 1000000000)]
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

  "number dot 1 9"
  [(dim :number #(not (:number-prefixed %))) #"(?i)chấm|phẩy" (integer 1 9)]
  {:dim :number
   :value (+ (* 0.1 (:value %3)) (:value %1))}

   "number dot 10 99"
  [(dim :number #(not (:number-prefixed %))) #"(?i)chấm|phẩy" (integer 10 99)]
  {:dim :number
   :value (+ (* 0.01 (:value %3)) (:value %1))}

   "number dot 100 999"
  [(dim :number #(not (:number-prefixed %))) #"(?i)chấm|phẩy" (integer 100 999)]
  {:dim :number
   :value (+ (* 0.001 (:value %3)) (:value %1))}

   "number dot 1000 9999"
  [(dim :number #(not (:number-prefixed %))) #"(?i)chấm|phẩy" (integer 1000 9999)]
  {:dim :number
   :value (+ (* 0.0001 (:value %3)) (:value %1))}

   "number dot 10000 99999"
  [(dim :number #(not (:number-prefixed %))) #"(?i)chấm|phẩy" (integer 10000 99999)]
  {:dim :number
   :value (+ (* 0.00001 (:value %3)) (:value %1))}

  "decimal with thousands separator"
  #"(\d+(,\d\d\d)+\.\d+)"
  {:dim :number
   :value (-> (:groups %1)
            first
            (clojure.string/replace #"," "")
            Double/parseDouble)}

  ;; negative number
  "numbers prefix with -, âm"
  [#"(?i)-|âm\s?" (dim :number #(not (:number-prefixed %)))]
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
  "ordinals"
  #"(?i)(đầu tiên|thứ nhất|thứ 1)"
  {:dim :ordinal
   :value (get {"đầu tiên" 1 "thứ nhất" 1 "thứ 1" 1}
              (-> %1 :groups first clojure.string/lower-case))}


  )
