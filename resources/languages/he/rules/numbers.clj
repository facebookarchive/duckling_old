(
  "intersect numbers"
  [(dim :number :grain #(> (:grain %) 1)) (dim :number)]
  (compose-numbers %1 %2)

  "intersect (with and)"
  [(dim :number :grain #(> (:grain %) 0)) #"(?i)ו" (dim :number)]
  (compose-numbers %1 %3)

 ;;
 ;; Integers
 ;;

  "integer 0"
  #"(?i)(אפס|כלום)"
  {:dim :number :integer true :value 0 :grain 1}

  "integer 1"
  #"(?i)(אחד|אחת)"
  {:dim :number :integer true :value 1 :grain 1}

  "integer 2"
  #"(?i)(שתיים|שניים)"
  {:dim :number :integer true :value 2 :grain 1}
  
  "integer 3"
  #"(?i)(שלושה?)"
  {:dim :number :integer true :value 3 :grain 1}
  
  "integer 4"
  #"(?i)(ארבעה?)"
  {:dim :number :integer true :value 4 :grain 1}
  
  "integer 5"
  #"(?i)(חמש|חמישה)"
  {:dim :number :integer true :value 5 :grain 1}
  
  "integer 6"
  #"(?i)(ששה?)"
  {:dim :number :integer true :value 6 :grain 1}
  
  "integer 7"
  #"(?i)(שבעה?)"
  {:dim :number :integer true :value 7 :grain 1}
  
  "integer 8"
  #"(?i)(שמונה)"
  {:dim :number :integer true :value 8 :grain 1}
  
  "integer 9"
  #"(?i)(תשעה?)"
  {:dim :number :integer true :value 9 :grain 1}

  "integer 10"
  #"(?i)(עשרה?)"
  {:dim :number :integer true :value 10 :grain 1}

  "integer 10"
  #"(?i)(עשרה?)"
  {:dim :number :integer true :value 10 :grain 1}  

  "integer 12"
  #"(?i)(שניים עשר|תרי עשר)"
  {:dim :number :integer true :value 10 :grain 1}  

  "integer 11..19"
  [(integer 1 9) (integer 10 10)]
  {:dim :number
   :integer true
   :value (+ (:value %1) (:value %2))
   :grain (:grain %1)}

  "single"
  #"(?i)יחיד"
  {:dim :number :integer true :value 1 :grain 1}

  "couple"
  #"(?i)זוג( של)?"
  {:dim :number :integer true :value 2 :grain 1}

  "hundred"
  #"(?i)(מאה|מאות)"
  {:dim :number :integer true :value 100 :grain 2}

  "thousand"
  #"(?i)(אלף|אלפים)"
  {:dim :number :integer true :value 1000 :grain 3}

  "million"
  #"(?i)(מיליון|מיליונים)"
  {:dim :number :integer true :value 1000000 :grain 6}

  "integer (20..90)"
  #"(?i)(עשרים|שלושים|ארבעים|חמישים|שישים|שבעים|שמונים|תשעים)"
  {:dim :number
   :integer true
   :value (get {"עשרים" 20 "שלושים" 30 "ארבעים" 40 "חמישים" 50 "שישים" 60 "שבעים" 70
              "שמונים" 80 "תשעים" 90}
             (-> %1 :groups first clojure.string/lower-case))
   :grain 1}

  ; "integer 21..99"
  ; [(integer 20 90 #(#{20 30 40 50 60 70 80 90} (:value %))) #"(?i)ו" (integer 1 9)]
  ; {:dim :number
  ;  :integer true
  ;  :value (+  (:value %1) (:value %3))}

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

  "number hundreds"
  [(integer 1 99) (integer 100 100)]
  {:dim :number
   :integer true
   :value (* (:value %1) (:value %2))
   :grain (:grain %2)}

  "number thousands"
  [(integer 1 999) (integer 1000 1000)]
  {:dim :number
   :integer true
   :value (* (:value %1) (:value %2))
   :grain (:grain %2)}

  "number millions"
  [(integer 1 99) (integer 1000000 1000000)]
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

  "number dot number"
  [(dim :number #(not (:number-prefixed %))) #"(?i)נקודה" (dim :number #(not (:number-suffixed %)))]
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
  "numbers prefix with -, negative or minus"
  [#"(?i)-|מינוס\s?" (dim :number #(not (:number-prefixed %)))]
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

  "ordinal 1"
  #"(?i)(אחד|ראשון)"
  {:dim :ordinal :integer true :value 1 :grain 1}

  "ordinal 2"
  #"(?i)(שתיים|שניים|שני)"
  {:dim :ordinal :integer true :value 2 :grain 1}
  
  "ordinal 3"
  #"(?i)(שלושה|שלישי)"
  {:dim :ordinal :integer true :value 3 :grain 1}
  
  "ordinal 4"
  #"(?i)(ארבעה|רביעי)"
  {:dim :ordinal :integer true :value 4 :grain 1}
  
  "ordinal 5"
  #"(?i)(חמישי|חמישה)"
  {:dim :ordinal :integer true :value 5 :grain 1}
  
  "ordinal 6"
  #"(?i)(ששה|שישי)"
  {:dim :ordinal :integer true :value 6 :grain 1}
  
  "ordinal 7"
  #"(?i)(שבעה|שביעי)"
  {:dim :ordinal :integer true :value 7 :grain 1}
  
  "ordinal 8"
  #"(?i)(שמונה|שמיני)"
  {:dim :ordinal :integer true :value 8 :grain 1}
  
  "ordinal 9"
  #"(?i)(תשעה|תשיעי)"
  {:dim :ordinal :integer true :value 9 :grain 1}

  "ordinal 10"
  #"(?i)(עשרה|עשירי)"
  {:dim :ordinal :integer true :value 10 :grain 1}

  "ordinal 11"
  #"(?i)(אחד עשרה?)"
  {:dim :ordinal :integer true :value 11 :grain 1}

  "ordinal 12"
  #"(?i)(שניים עשר|תרי עשר)"
  {:dim :ordinal :integer true :value 12 :grain 1}

  "ordinal 13"
  #"(?i)(שלושה? עשרה?)"
  {:dim :ordinal :integer true :value 13 :grain 1}

  "ordinal 14"
  #"(?i)(ארבעה? עשרה?)"
  {:dim :ordinal :integer true :value 14 :grain 1}

  "ordinal 15"
  #"(?i)(חמישה עשר|חמש עשרה?)"
  {:dim :ordinal :integer true :value 15 :grain 1}

  "ordinal 16"
  #"(?i)(ששה? עשרה?)"
  {:dim :ordinal :integer true :value 16 :grain 1}

  "ordinal 17"
  #"(?i)(שבעה? עשרה?)"
  {:dim :ordinal :integer true :value 17 :grain 1}

  "ordinal 18"
  #"(?i)(שמונה עשרה?)"
  {:dim :ordinal :integer true :value 18 :grain 1}

  "ordinal 19"
  #"(?i)(תשעה? עשרה?)"
  {:dim :ordinal :integer true :value 19 :grain 1}

  "ordinal 20..90"
  #"(?i)(עשרים|שלושים|ארבעים|חמישים|שישים|שבעים|שמונים|תשעים)"
  {:dim :ordinal
   :integer true
   :value (get {"עשרים" 20 "שלושים" 30 "ארבעים" 40 "חמישים" 50 "שישים" 60 "שבעים" 70
              "שמונים" 80 "תשעים" 90}
             (-> %1 :groups first clojure.string/lower-case))
   :grain 1}

  "ordinal intersect (with and)"
  [(dim :ordinal) #"(?i)ו" (dim :ordinal)]
  (compose-numbers %1 %3)

  ; "ordinals (אחד..שלושים ואחד)"
  ; #"(?i)(שלושים ואחד|שלושים|עשרים ותשעה|עשרים ושמונה|עשרהה ו)"
  ; #"(?i)(אחד|שניים|שלושה|ארבעה|חמישה|שישה|שבעה|שמונה|תשעה|עשרה|אחד עשר|שניים עשר|שלושה עשר|ארבעה עשר|חמישה עשר|שישה עשר|שבעה עשר|שמונה עשר|תשעה עשר|עשרים|עשרים ואחד|עשרים ושניים|עשרים ושלושה|עשרים וארבעה|עשרים וחמישה|עשרים ושישה|עשרים ושבעה|עשרים ושמונה|עשרים ותשעה|שלושים|שלושים ואחד)"
  ; {:dim :ordinal
  ;  :value (get {"אחד" 1 "שניים" 2 "שלושה" 3 "ארבעה" 4 "חמישה" 5
  ;             "שישה" 6 "שבעה" 7 "שמונה" 8 "תשעה" 9 "עשרה" 10 "אחד עשר" 11
  ;             "שניים עשר" 12 "שלושה עשר" 13 "ארבעה עשר" 14 "חמישה עשר" 15 "שישה עשר" 16
  ;             "שבעה עשר" 17 "שמונה עשר" 18 "תשעה עשר" 19 "עשרים" 20 "עשרים ואחד" 21
  ;             "עשרים ושניים" 22 "עשרים ושלושה" 23 "עשרים וארבעה" 24 "עשרים וחמישה" 25
  ;             "עשרים ושישה" 26 "עשרים ושבעה" 27 "עשרים ושמונה" 28 "עשרים ותשעה" 29
  ;             "שלושים" 30 "שלושים ואחד" 31}
  ;             (-> %1 :groups first clojure.string/lower-case))}

  ; "ordinals (ראשון..שלושים ואחד)"
  ; #"(?i)(ראשון|שני|שלישי|רביעי|חמישי|שישי|שביעי|שמיני|תשיעי|עשירי|אחד עשרה|שתיים עשרה|שלוש עשרה|ארבע עשרה|חמש עשרה|שש עשרה|שבע עשרה|שמונה עשרה|תשע עשרה|עשרים|עשרים ואחד|עשרים ושניים|עשרים ושלושה|עשרים וארבעה|עשרים וחמישה|עשרים ושישה|עשרים ושבעה|עשרים ושמונה|עשרים ותשעה|שלושים|שלושים ואחד)"
  ; {:dim :ordinal
  ;  :value (get {"אחד" 1 "שניים" 2 "שלושה" 3 "ארבעה" 4 "חמישה" 5
  ;             "שישה" 6 "שבעה" 7 "שמונה" 8 "תשעה" 9 "עשרה" 10 "אחד עשר" 11
  ;             "שניים עשר" 12 "שלושה עשר" 13 "ארבעה עשר" 14 "חמישה עשר" 15 "שישה עשר" 16
  ;             "שבעה עשר" 17 "שמונה עשר" 18 "תשעה עשר" 19 "עשרים" 20 "עשרים ואחד" 21
  ;             "עשרים ושניים" 22 "עשרים ושלושה" 23 "עשרים וארבעה" 24 "עשרים וחמישה" 25
  ;             "עשרים ושישה" 26 "עשרים ושבעה" 27 "עשרים ושמונה" 28 "עשרים ותשעה" 29
  ;             "שלושים" 30 "שלושים ואחד" 31}
  ;             (-> %1 :groups first clojure.string/lower-case))}

  "ordinal (digits)"
  #"0*(\d+) "
  {:dim :ordinal
   :value (read-string (first (:groups %1)))}  ; read-string not the safest
)
