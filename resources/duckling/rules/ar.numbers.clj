(
 ;;
 ;; Integers
 ;;
  "integer 0"
  #"(?i)(صفر)"
  {:dim :number :integer true :value 0}
  
  "integer 1"
  #"(?i)(واحد|واحدة|واحده)"
  {:dim :number :integer true :value 1}
  
  "integer 2"
  #"(?i)(اثنان|اثنين)"
  {:dim :number :integer true :value 2}
  
  "integer (3..9)"
  #"(?i)(ثلاثة|أربعة|خمسة|ستة|سبعة|ثمانية|تسعة)"
  {:dim :number
   :integer true
   :value (get {"ثلاثة" 3 "أربعة" 4 "خمسة" 5 "ستة" 6 "سبعة" 7 "ثمانية" 8 "تسعة" 9
             }
              (-> %1 :groups first .toLowerCase))}
  
   "integer 10"
  #"(?i)(عشرة|عشر)"
  {:dim :number :integer true :value 10}
  
  "integer (20..90)"
  #"(?i)(عشرون|ثلاثون|أربعون|خمسون|ستون|سبعون|ثمانون|تسعون)"
  {:dim :number
   :integer true
   :value (get {"عشرون" 20 "ثلاثون" 30 "أربعون" 40 "خمسون" 50 "ستون" 60
              "سبعون" 70 "ثمانون" 80 "تسعون" 90}
             (-> %1 :groups first .toLowerCase))}

  "integer (100..900)"
  #"(?i)(مائة|مائتان|ثلاثمائة|أربعمائة|خمسمائة|ستمائة|سبعمائة|ثمانمائة|تسعمائة)"
  {:dim :number
   :integer true
   :value (get {"مائة" 100 "مائتان" 200 "ثلاثمائة" 300 "أربعمائة" 400 "خمسمائة" 500
              "ستمائة" 600 "سبعمائة" 700 "ثمانمائة" 800 "تسعمائة" 900}
             (-> %1 :groups first .toLowerCase))}

  
  "integer (11..19)"
  [(integer 1 9) (integer 10 10)]
  {:dim :number
   :integer true
   :value (+ 10 (:value %1))}
  
  
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
  
  "ordinals (first..10th)"
  #"(?i)(الاول|الثاني|الثالث|الرابع|الخامس|السادس|السابع|الثامن|التاسع|العاشر)"
  {:dim :ordinal
   :value (get {"الاول" 1 "الثاني" 2 "الثالث" 3 "الرابع" 4 "الخامس" 5"
              السادس" 6 "السابع" 7 "الثامن" 8 "التاسع" 9 "العاشر" 10 }
              (-> %1 :groups first .toLowerCase))}

 

  
  )
