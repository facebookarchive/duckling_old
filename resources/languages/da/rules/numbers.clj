(

  "intersect"
  [(dim :number :grain #(> (:grain %) 1)) (dim :number)] ; grain 1 are taken care of by specific rule
  (compose-numbers %1 %2)

  "numbers og"
  [(integer 1 9) #"og" (integer 20 90 #(#{20 30 40 50 60 70 80 90} (:value %)))]
  {:dim :number
   :integer true
   :value (+ (:value %1) (:value %3))}

 ;;
 ;; Integers
 ;;
 
  "integer (0..19)"
  #"(?i)(intet|ingen|nul|en|et|én|ét|to|tretten|tre|fire|femten|fem|seksten|seks|syv|otte|nitten|ni|ti|elleve|tolv|fjorten|sytten|atten)"
  ; fourteen must be before four, or it won't work because the regex will stop at four
  {:dim :number
   :integer true
   :value (get {"intet" 0 "ingen" 0 "nul" 0 "en" 1 "et" 1 "én" 1 "ét" 1 "to" 2 "tre" 3 "fire" 4 "fem" 5
              "seks" 6 "syv" 7 "otte" 8 "ni" 9 "ti" 10 "elleve" 11
              "tolv" 12 "tretten" 13 "fjorten" 14 "femten" 15 "seksten" 16
              "sytten" 17 "atten" 18 "nitten" 19}
              (-> %1 :groups first .toLowerCase))}
  
  "ten"
  #"(?i)ti"
  {:dim :number :integer true :value 10 :grain 1}

  "single"
  #"(?i)enkelt"
  {:dim :number :integer true :value 1 :grain 1}

  "a pair"
  #"(?i)et par"
  {:dim :number :integer true :value 2 :grain 1}
  
  "dozen"
  #"(?i)dusin"
  {:dim :number :integer true :value 12 :grain 1 :grouping true} ;;restrict composition and prevent "2 12"

  "hundred"
  #"(?i)hundrede?"
  {:dim :number :integer true :value 100 :grain 2}

  "thousand"
  #"(?i)tusinde?"
  {:dim :number :integer true :value 1000 :grain 3}

  "million"
  #"(?i)million(er)?"
  {:dim :number :integer true :value 1000000 :grain 6}

  "couple"
  #"et par"
  {:dim :number :integer true :value 2}
  
  "few" ; TODO set assumption
  #"(nogle )?få"
  {:dim :number :integer true :precision :approximate :value 3}

  "integer (20..90)"
  #"(?i)(tyve|tredive|fyrre|halvtreds|tres|halvfjerds|firs|halvfems)"
  {:dim :number
   :integer true
   :value (get {"tyve" 20 "tredive" 30 "fyrre" 40 "halvtreds" 50 "tres" 60
              "halvfjerds" 70 "firs" 80 "halvfems" 90}
             (-> %1 :groups first .toLowerCase))
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

  "integer with thousands separator ."
  #"(\d{1,3}(\.\d\d\d){1,5})"
  {:dim :number
   :integer true
   :value (-> (:groups %1)
            first
            (clojure.string/replace #"\." "")
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
  #"(\d*,\d+)"
  {:dim :number
   :value (-> (:groups %1)
            first
            (clojure.string/replace #"," ".")
            Double/parseDouble)}

  "number dot number"
  [(dim :number #(not (:number-prefixed %))) #"(?i)komma" (dim :number #(not (:number-suffixed %)))]
  {:dim :number
   :value (+ (* 0.1 (:value %3)) (:value %1))}
   

  "decimal with thousands separator"
  #"(\d+(\.\d\d\d)+\,\d+)"
  {:dim :number
   :value (-> (:groups %1)
            first
            (clojure.string/replace #"\." "")
            Double/parseDouble)}

  ;; negative number
  "numbers prefix with -, negative or minus"
  [#"(?i)-|minus\s?|negativ\s?" (dim :number #(not (:number-prefixed %)))]
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
  
  "ordinals (first..31st)"
  #"(?i)(første|anden|tredje|fjerde|femte|sjette|syvende|ottende|niende|tiende|elfte|tolvte|trettende|fjortende|femtende|sekstende|syttende|attende|nittende|tyvende|tenogtyvende|toogtyvende|treogtyvende|fireogtyvende|femogtyvende|seksogtyvende|syvogtyvende|otteogtyvende|niogtyvende|tredivte|enogtredivte)"
  {:dim :ordinal
   :value (get {"første" 1 "anden" 2 "tredje" 3 "fjerde" 4 "femte" 5
              "sjette" 6 "syvende" 7 "ottende" 8 "niende" 9 "tiende" 10 "elfte" 11
              "tolvte" 12 "trettende" 13 "fjortende" 14 "femtende" 15 "sekstende" 16
              "syttende" 17 "attende" 18 "nittende" 19 "tyvende" 20 "tenogtyvende" 21
              "toogtyvende" 22 "treogtyvende" 23 "fireogtyvende" 24 "femogtyvende" 25
              "seksogtyvende" 26 "syvogtyvende" 27 "otteogtyvende" 28 "niogtyvende" 29
              "tredivte" 30 "enogtredivte" 31}
              (-> %1 :groups first .toLowerCase))}

  "ordinal (digits)"
  #"0*(\d+)(\.|ste?)"
  {:dim :ordinal
   :value (read-string (first (:groups %1)))}  ; read-string not the safest

  
  )
