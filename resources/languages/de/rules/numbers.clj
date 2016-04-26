(

  "numbers und"
[(integer 1 9) #"und" (integer 20 90 #(#{20 30 40 50 60 70 80 90} (:value %)))]
  {:dim :number
  :integer true
  :value (+ (:value %1) (:value %3))}

   ;;
 ;; Integers
 ;;
 
  "integer (0..19)"
  #"(?i)(keine?|keine?s|keiner?|keinen|null|nichts|eins?(er)?|zwei|dreizehn|drei|vierzehn|vier|fünf|sechzehn|sechs|siebzehn|sieben|achtzehn|acht|neunzehn|neun|elf|zwölf|füfzehn)"
  ; fourteen must be before four, or it won't work because the regex will stop at four
  {:dim :number
   :integer true
   :value (get {"keines" 0 "keiner" 0 "keinen" 0 "null" 0 "nichts" 0 "ein" 1 "eins" 1 "eine" 1 "einer" 1 "zwei" 2 "drei" 3 "vier" 4 "fünf" 5
              "sechs" 6 "sieben" 7 "acht" 8 "neun" 9 "zehn" 10 "elf" 11
              "zwölf" 12 "dreizehn" 13 "vierzehn" 14 "fünfzehn" 15 "sechzehn" 16
              "siebzehn" 17 "achtzehn" 18 "neunzehn" 19}
              (-> %1 :groups first .toLowerCase))}
  
  "ten"
  #"(?i)zehn"
  {:dim :number :integer true :value 10 :grain 1}
  
  "dozen"
  #"(?i)dutzend"
  {:dim :number :integer true :value 12 :grain 1 :grouping true} ;;restrict composition and prevent "2 12"

  "hundred"
  #"(?i)hunderte?"
  {:dim :number :integer true :value 100 :grain 2}

  "thousand"
  #"(?i)tausende?"
  {:dim :number :integer true :value 1000 :grain 3}

  "million"
  #"(?i)million(en)?"
  {:dim :number :integer true :value 1000000 :grain 6}

  "couple"
  #"(?i)(ein )?paar"
  {:dim :number :integer true :value 2}
  
  "few" ; TODO set assumption
  #"(?i)mehrere"
  {:dim :number :integer true :precision :approximate :value 3}

  "integer (20..90)"
  #"(?i)(zwanzig|dreissig|vierzig|fünfzig|sechzig|siebzig|achtzig|neunzig)"
  {:dim :number
   :integer true
   :value (get {"zwanzig" 20 "dreissig" 30 "vierzig" 40 "fünfzig" 50 "sechzig" 60
              "siebzig" 70 "achtzig" 80 "neunzig" 90}
             (-> %1 :groups first .toLowerCase))
   :grain 1}

 "integer ([2-9][1-9])"
 #"(?i)(ein|zwei|drei|vier|fünf|sechs|sieben|acht|neun)und(zwanzig|dreissig|vierzig|fünfzig|sechzig|siebzig|achtzig|neunzig)"
 {:dim :number
  :integer true
  :value (+ (get {"ein" 1 "zwei" 2 "drei" 3 "vier" 4 "fünf" 5
                  "sechs" 6 "sieben" 7 "acht" 8 "neun" 9}
                 (-> %1 :groups first .toLowerCase))
            (get {"zwanzig" 20 "dreissig" 30 "vierzig" 40 "fünfzig" 50
                  "sechzig" 60 "siebzig" 70 "achtzig" 80 "neunzig" 90}
                 (-> %1 :groups second .toLowerCase)))}

 ; "integer 21..99"
 ; [(integer 10 90 #(#{20 30 40 50 60 70 80 90} (:value %))) (integer 1 9)]
 ; {:dim :number
 ;  :integer true
 ;  :value (+ (:value %1) (:value %2))}

  "integer (numeric)"
  #"(\d{1,18})"
  {:dim :number
   :integer true
   :value (Long/parseLong (first (:groups %1)))}
  
  "integer with thousands separator ,"
  #"(\d{1,3}(,\d\d\d){1,5})" ;Check me PATRICK PLEASE REPLACE , WITH A . BECAUSE THATS WHAT THEY USE IN GERMAN
  {:dim :number
   :integer true
   :value (-> (:groups %1)
            first
            (clojure.string/replace #"," "")
            Long/parseLong)}
  
  ; composition
 ; "number dozen"
 ; [(integer 1 10) (dim :number #(:grouping %))]
 ; {:dim :number
 ;  :integer true
 ;  :value (* (:value %1) (:value %2))
 ;  :grain (:grain %2)}


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
  [(dim :number #(not (:number-prefixed %))) #"(?i)dot|point" (dim :number #(not (:number-suffixed %)))]
  {:dim :number
   :value (+ (* 0.1 (:value %3)) (:value %1))}
   

  "decimal with thousands separator";Check me NOT SURE WHAT THIS ONE IS, MIGHT REQUIRE , INSTEAD OF . ;)
  #"(\d+(,\d\d\d)+\.\d+)"
  {:dim :number
   :value (-> (:groups %1)
            first
            (clojure.string/replace #"," "")
            Double/parseDouble)}

  ;; negative number
  "numbers prefix with -, negative or minus"
  [#"(?i)-|minus|negativ" (dim :number #(not (:number-prefixed %)))]
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
                        (-> %2 :groups first .toLowerCase))
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
  #"(?i)(erster?|zweiter|dritter|vierter|fuenfter|sechster|siebter|achter|neunter|zehnter|elfter|zwölfter|dreizenter|vierzehnter|fünfzehnter|sechzenter|siebzehnter|achtzehnter|neunzehnter)"
  {:dim :ordinal
:value (get {"erste" 1 "erster" 1 "zweiter" 2 "dritter" 3 "vierter" 4 "fünfter" 5
              "sechster" 6 "siebter" 7 "achter" 8 "neunter" 9 "zehnter" 10 "elfter" 11
              "zwölfter" 12 "dreizehnter" 13 "vierzehnter" 14 "fünfzehnter" 15 "sechzehnter" 16
              "siebzehnter" 17 "achtzehnter" 18 "neunzehnter" 19}
              (-> %1 :groups first .toLowerCase))}

  "ordinal (digits)"
  #"0*(\d+)(\.| ?(te(n|r|s)?)|(ste(n|r|s)?))"
  {:dim :ordinal
   :value (read-string (first (:groups %1)))}  ; read-string not the safest

  
  )
