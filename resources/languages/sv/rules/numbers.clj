(

  "intersect"
  [(dim :number :grain #(> (:grain %) 1)) (dim :number)] ; grain 1 are taken care of by specific rule
  (compose-numbers %1 %2)

  "intersect (with and)"
  [(dim :number :grain #(> (:grain %) 1)) #"(?i)och" (dim :number)] ; grain 1 are taken care of by specific rule
  (compose-numbers %1 %3)

 ;;
 ;; Integers
 ;;

  "integer (0..19)"
  #"(?i)(inget|ingen|noll|en|ett|två|tretton|tre|fyra|femton|fem|sexton|sex|sju|åtta|nitton|nio|tio|elva|tolv|fjorton|sjutton|arton|nitton)"
  ; fourteen must be before four, or it won't work because the regex will stop at four
  {:dim :number
   :integer true
   :value (get {"inget" 0 "ingen" 0 "noll" 0 "en" 1 "ett" 1 "två" 2 "tre" 3 "fyra" 4 "fem" 5
              "sex" 6 "sju" 7 "åtta" 8 "nio" 9 "tio" 10 "elva" 11
              "tolv" 12 "tretton" 13 "fjorton" 14 "femton" 15 "sexton" 16
              "sjutton" 17 "arton" 18 "nitton" 19}
              (-> %1 :groups first .toLowerCase))}

  "ten"
  #"(?i)ti0"
  {:dim :number :integer true :value 10 :grain 1}

  "single"
  #"(?i)enkel"
  {:dim :number :integer true :value 1 :grain 1}

  "a pair"
  #"(?i)ett par"
  {:dim :number :integer true :value 2 :grain 1}

  "dozen"
  #"(?i)dussin"
  {:dim :number :integer true :value 12 :grain 1 :grouping true} ;;restrict composition and prevent "2 12"

  "hundred"
  #"(?i)hundra?"
  {:dim :number :integer true :value 100 :grain 2}

  "thousand"
  #"(?i)tusen?"
  {:dim :number :integer true :value 1000 :grain 3}

  "million"
  #"(?i)miljon(er)?"
  {:dim :number :integer true :value 1000000 :grain 6}

  "couple"
  #"ett par"
  {:dim :number :integer true :value 2}

  "few" ; TODO set assumption
  #"(några )?få"
  {:dim :number :integer true :precision :approximate :value 3}

  "integer (20..90)"
  #"(?i)(tjugo|tretti|trettio|fyrtio|femtio|sextio|sjuttio|åttio|nittio)"
  {:dim :number
   :integer true
   :value (get {"tjugo" 20 "trettio" 30 "tretti" 30 "fyrtio" 40 "femtio" 50 "sextio" 60
              "sjuttio" 70 "åttio" 80 "nittio" 90}
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
  [#"(?i)-|minus\s?" (dim :number #(not (:number-prefixed %)))]
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
  #"(?i)(första|andra|tredje|fjärde|femte|sjätte|sjunde|åttonde|nionde|tionde|elfte|tolfte|trettonde|fjortonde|femtonde|sextonde|sjuttonde|artonde|nittonde|tjugonde|tjugoförsta|sjugoandra|tjugotredje|tjugofjärde|tjugofemte|tjugosjätte|tjugosjunde|tjugoåttonde|tjugonionde|trettionde|trettioförsta)"
  {:dim :ordinal
   :value (get {"första" 1 "andra" 2 "tredje" 3 "fjärde" 4 "femte" 5
              "sjätte" 6 "sjunde" 7 "åttonde" 8 "nionde" 9 "tionde" 10 "elfte" 11
              "tolfte" 12 "trettonde" 13 "fjortonde" 14 "femtonde" 15 "sextonde" 16
              "sjuttonde" 17 "artonde" 18 "nittonde" 19 "tjugonde" 20 "tjugoförsta" 21
              "tjugoandra" 22 "tjugotredje" 23 "tjugofjärde" 24 "tjugofemte" 25
              "tjugosjätte" 26 "tjugosjunde" 27 "tjugoåttonde" 28 "tjugonionde" 29 "trettionde" 30 "trettioförsta" 31}
              (-> %1 :groups first .toLowerCase))}

  "ordinal (digits)"
  #"0*(\d+)"
  {:dim :ordinal
   :value (read-string (first (:groups %1)))}  ; read-string not the safest


  )
