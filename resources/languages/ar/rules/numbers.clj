(
 ;;
 ;; Integers
 ;;
  "integer 0"
  #"(?i)(صفر)"
  {:dim :number :integer true :value 0}

  "integer 1"
  #"(?i)(واحدة|واحده|واحد)"
  {:dim :number :integer true :value 1}

  "integer 2"
  #"(?i)(اثنان|اثنين)"
  {:dim :number :integer true :value 2}
  
  "integer 3"
  #"(?i)(ثلاث|ثلاثة)"
  {:dim :number :integer true :value 3}
  
  "integer 4"
  #"(?i)(أربع)"
  {:dim :number :integer true :value 4}
  
  "integer 4"
  #"(?i)(أربعة)"
  {:dim :number :integer true :value 4}
  
  "integer 5"
  #"(?i)(خمس)"
  {:dim :number :integer true :value 5}
  
  "integer 5"
  #"(?i)(خمسة)"
  {:dim :number :integer true :value 5}
  
  "integer 6"
  #"(?i)(ستة)"
  {:dim :number :integer true :value 6}
  
  "integer 6"
  #"(?i)(ست)"
  {:dim :number :integer true :value 6}
  
  "integer 7"
  #"(?i)(سبعة|سبع)"
  {:dim :number :integer true :value 7}
  
  "integer 8"
  #"(?i)(ثمانية|ثمان)"
  {:dim :number :integer true :value 8}
  
  "integer 9"
  #"(?i)(تسعة|تسع)"
  {:dim :number :integer true :value 9}

  "integer 10"
  #"(?i)(عشرة|عشر)"
  {:dim :number :integer true :value 10}
  
  
  "integer 11"
  #"(?i)(إحدى عشرة)"
  {:dim :number :integer true :value 11}
  
  "integer 11"
  #"(?i)(إحدى عشر)"
  {:dim :number :integer true :value 11}
  
  "integer 12"
  #"(?i)(إثنتى عشر)"
  {:dim :number :integer true :value 12}
  
  "integer 12"
  #"(?i)(إثنى عشر)"
  {:dim :number :integer true :value 12}

  "integer (20..90)"
  #"(?i)(عشرون|ثلاثون|أربعون|خمسون|ستون|سبعون|ثمانون|تسعون)"
  {:dim :number
   :integer true
   :value (get {"عشرون" 20 "ثلاثون" 30 "أربعون" 40 "خمسون" 50 "ستون" 60
              "سبعون" 70 "ثمانون" 80 "تسعون" 90}
             (-> %1 :groups first clojure.string/lower-case))}

  "integer (100..900)"
  #"(?i)(مائة|مائتان|ثلاثمائة|أربعمائة|خمسمائة|ستمائة|سبعمائة|ثمانمائة|تسعمائة)"
  {:dim :number
   :integer true
   :value (get {"مائة" 100 "مائتان" 200 "ثلاثمائة" 300 "أربعمائة" 400 "خمسمائة" 500
              "ستمائة" 600 "سبعمائة" 700 "ثمانمائة" 800 "تسعمائة" 900}
             (-> %1 :groups first clojure.string/lower-case))}


  "integer (13..19)"
  [ (integer 3 9) (integer 10 10) ]
  {:dim :number
   :integer true
   :value (+ (:value %1) 10)}


  "integer 21..99"
  [ (integer 1 9) #"(?i)و" (integer 20 90 #(#{20 30 40 50 60 70 80 90} (:value %)))]
  {:dim :number
   :integer true
   :value (+  (:value %1) (:value %3))}

  "integer 101..999"
  [(integer 100 900 #(#{100 200 300 400 500 600 700 800 900} (:value %)))  #"(?i)و" (integer 1 99)]
  {:dim :number
   :integer true
   :value (+ (:value %1) (:value %3))}

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
  [(dim :number #(not (:number-prefixed %))) #"(?i)فاصلة" (dim :number #(not (:number-suffixed %)))]
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
  [#"(?i)-" (dim :number #(not (:number-prefixed %)))]
  (let [multiplier -1
        value      (* (:value %2) multiplier)
        int?       (zero? (mod value 1))
        value      (if int? (long value) value)] ;
    (assoc %2 :value value
              :integer int?
              :number-prefixed true)) ;


  ;; suffixes

 ; composition

  "hundreds"
  #"(?i)(مائة|مئات)"
  {:dim :number :integer true :value 100 :grain 2}


  "number hundreds"
  [ (integer 1 99) (integer 100 100)]
  {:dim :number
   :integer true
   :value (* (:value %1) (:value %2))
   :grain (:grain %2)}


  "thousands"
  #"(?i)(ألف|الف|آلاف)"
   {:dim :number :integer true :value 1000 :grain 3}


  "number thousands"
  [ (integer 1 999) (integer 1000 1000)]
  {:dim :number
   :integer true
   :value (* (:value %1) (:value %2))
   :grain (:grain %2)}


  "million"
  #"(?i)ملايين?"
  {:dim :number :integer true :value 1000000 :grain 6}


  "number millions"
  [ (integer 1 999) (integer 1000000 1000000)]
  {:dim :number
   :integer true
   :value (* (:value %1) (:value %2))
   :grain (:grain %2)}

  ;;
  ;; Ordinal numbers
  ;;

  "ordinals first"
  #"(?i)(أول|الأول|أولى|الأولى)"
  {:dim :ordinal :integer true :value 1}
  
  "ordinals second"
  #"(?i)(ثاني|ثانية|الثاني|الثانية)"
  {:dim :ordinal :integer true :value 2}
  
  "ordinals first"
  #"(?i)(ثالث|ثالثة|الثالث|الثالثة)"
  {:dim :ordinal :integer true :value 3}
  
  "ordinals first"
  #"(?i)(رابع|رابعة | الرابع|الرابعة)"
  {:dim :ordinal :integer true :value 4}
  
  "ordinals first"
  #"(?i)(خامس | الخامس | خامسة | الخامسة)"
  {:dim :ordinal :integer true :value 5}
  
  "ordinals first"
  #"(?i)(سادس | سادسة | السادس | السادسة)"
  {:dim :ordinal :integer true :value 6}
  
  "ordinals 7th"
  #"(?i)(سابع | سابعة | السابع | السابعة)"
  {:dim :ordinal :integer true :value 7}
  
  "ordinals 8th"
  #"(?i)(ثامن | ثامنة | الثامن | الثامنة)"
  {:dim :ordinal :integer true :value 8}
  
  "ordinals 9th"
  #"(?i)(تاسع | تاسعة | التاسع | التاسعة)"
  {:dim :ordinal :integer true :value 9}
  
  "ordinals 10th"
  #"(?i)(عاشر | عاشرة | العاشر | العاشرة)"
  {:dim :ordinal :integer true :value 10}
  

			  
  




  )
