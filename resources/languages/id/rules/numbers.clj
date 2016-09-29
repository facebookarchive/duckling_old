(
  "intersect"
  [(dim :number :grain #(> (:grain %) 1)) (dim :number)] ; grain 1 are taken care of by specific rule
  (compose-numbers %1 %2)

 ;;
 ;; Integers
 ;;

  "integer (0..9 11)"
  #"(?i)(kosong|nol|satu|dua|tiga|empat|lima|enam|tujuh|delapan|sembilan|sebelas)"
  {:dim :number
   :integer true
   :value (get {"kosong" 0 "nol" 0 "satu" 1 "dua" 2 "tiga" 3 "empat" 4 "lima" 5 "enam" 6 "tujuh" 7 "delapan" 8 "sembilan" 9 "sebelas" 11}
              (-> %1 :groups first clojure.string/lower-case))}

  "ten"
  #"(?i)(se)?puluh"
  {:dim :number :integer true :value 10 :grain 1}

  "teen"
  [(integer 2 9) #"belas"]
  {:dim :number
   :integer true
   :value (+ (:value %1) 10)}

  "dozen"
  #"(?i)(se)?lusin"
  {:dim :number :integer true :value 12 :grain 1 :grouping true} ;;restrict composition and prevent "2 12"

  "hundred"
  #"(?i)(se)?ratus"
  {:dim :number :integer true :value 100 :grain 2}

  "thousand"
  #"(?i)(se)?ribu"
  {:dim :number :integer true :value 1000 :grain 3}

  "million"
  #"(?i)(se)?juta"
  {:dim :number :integer true :value 1000000 :grain 6}

  "some/few/couple" ; TODO set assumption
  #"beberapa"
  {:dim :number :integer true :precision :approximate :value 3}

  "integer 20..90"
  [(integer 2 9) (integer 10 10)]
  {:dim :number
   :integer true
   :value (* (:value %1) (:value %2))
   :grain (:grain %2)}

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

  "integer with thousands separator ."
  #"(\d{1,3}(\.\d\d\d){1,5})"
  {:dim :number
   :integer true
   :value (-> (:groups %1)
            first
            (clojure.string/replace #"\." "")
            Long/parseLong)}

  "number hundreds"
  [(integer 2 99) (integer 100 100)]
  {:dim :number
   :integer true
   :value (* (:value %1) (:value %2))
   :grain (:grain %2)}

  "number thousands"
  [(integer 2 999) (integer 1000 1000)]
  {:dim :number
   :integer true
   :value (* (:value %1) (:value %2))
   :grain (:grain %2)}

  "number millions"
  [(integer 2 99) (integer 1000000 1000000)]
  {:dim :number
   :integer true
   :value (* (:value %1) (:value %2))
   :grain (:grain %2)}

  ;;
  ;; Decimals
  ;;

  "decimal number"
  #"(\d*,\d+)"
  {:dim :number
   :value (-> (:groups %1)
            first
            (clojure.string/replace #"," ".")
            Double/parseDouble)}

  "number comma number"
  [(dim :number #(not (:number-prefixed %))) #"(?i)koma" (dim :number #(not (:number-suffixed %)))]
  {:dim :number
   :value (+ (* 0.1 (:value %3)) (:value %1))}


  "decimal with thousands separator"
  #"(\d+(\.\d\d\d)+,\d+)"
  {:dim :number
   :value (-> (:groups %1)
            first
            (clojure.string/replace #"\." "")
            Double/parseDouble)}

  ;; negative number
  "numbers prefix with -, negative or minus"
  [#"(?i)-|minus\s?|negatif\s?" (dim :number #(not (:number-prefixed %)))]
  (let [multiplier -1
        value      (* (:value %2) multiplier)
        int?       (zero? (mod value 1)) ; often true, but we could have 1.1111K
        value      (if int? (long value) value)] ; cleaner if we have the right type
    (assoc %2 :value value
              :integer int?
              :number-prefixed true)) ; prevent "- -3km" to be 3 billions


  ;; suffixes

  ; note that we check for a space-like char after the M, K or G
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
  #"(?i)(pertama|kedua|ketiga|keempat|kelima|keenam|ketujuh|kedelapan|kesembilan|kesepuluh)"
  {:dim :ordinal 
   :value (get {"pertama" 1 "kedua" 2 "ketiga" 3 "keempat" 4 "kelima" 5 "keenam" 6 "ketujuh" 7 "kedelapan" 8 "kesembilan" 9 "kesepuluh" 10}
              (-> %1 :groups first clojure.string/lower-case))}

  "ordinals (digits)"
  #"ke-0*(\d+)"
  {:dim :ordinal
   :value (read-string (first (:groups %1)))}  ; read-string not the safest

)
