(  "intersect" ;handles things like honderd twee
  [(dim :number :grain #(> (:grain %) 1)) (dim :number)] ; grain 1 are taken care of by specific rule
  (compose-numbers %1 %2)

  "numbers en"
 [(integer 1 9) #"en" (integer 20 90 #(#{20 30 40 50 60 70 80 90} (:value %)))]
 {:dim :number
  :integer true
  :value (+ (:value %1) (:value %3))}

 ;;
 ;; Integers
 ;;

 "integer (0..19)"
 #"(?i)(geen|nul|niks|een|één|twee|drie|vier|vijftien|vijf|zestien|zes|zeventien|zeven|achtien|acht|negentien|negen|tien|elf|twaalf|dertien|veertien)"
 ; fourteen must be before four, or it won't work because the regex will stop at four
 {:dim :number
  :integer true
  :value (get {"geen" 0 "nul" 0 "niks" 0 "een" 1 "één" 1 "twee" 2 "drie" 3 "vier" 4 "vijf" 5 "zes" 6 "zeven" 7 "acht" 8 "negen" 9
              "tien" 10 "elf" 11 "twaalf" 12 "dertien" 13 "veertien" 14 "vijftien" 15 "zestien" 16 "zeventien" 17 "achtien" 18 "negentien" 19}
            (-> %1 :groups first clojure.string/lower-case))}

 "ten"
 #"(?i)tien"
 {:dim :number :integer true :value 10 :grain 1}

 "dozen"
 #"(?i)dozijn"
 {:dim :number :integer true :value 12 :grain 1 :grouping true} ;;restrict composition and prevent "2 12"

 "hundred"
 #"(?i)honderd"
 {:dim :number :integer true :value 100 :grain 2}

 "thousand"
 #"(?i)duizend"
 {:dim :number :integer true :value 1000 :grain 3}

 "million"
 #"(?i)miljoen"
 {:dim :number :integer true :value 1000000 :grain 6}

 "couple"
 #"(?i)(een )?paar"
 {:dim :number :integer true :value 2}

 "few" ; TODO set assumption
 #"(?i)meerdere"
 {:dim :number :integer true :precision :approximate :value 3}

 "integer (20..90)"
 #"(?i)(twintig|dertig|veertig|vijftig|zestig|zeventig|tachtig|negentig)"
 {:dim :number
  :integer true
  :value (get {"twintig" 20 "dertig" 30 "veertig" 40 "vijftig" 50 "zestig" 60
              "zeventig" 70 "tachtig" 80 "negentig" 90}
            (-> %1 :groups first clojure.string/lower-case))
  :grain 1}

 "integer ([2-9][1-9])"
 #"(?i)(een|twee|drie|vier|vijf|zes|zeven|acht|negen)(?:e|ë)n(twintig|dertig|veertig|vijftig|zestig|zeventig|tachtig|negentig)"
 {:dim :number
  :integer true
  :value (+ (get {"een" 1 "twee" 2 "drie" 3 "vier" 4 "vijf" 5
                  "zes" 6 "seven" 7 "acht" 8 "negen" 9}
                  (-> %1 :groups first clojure.string/lower-case))
            (get {"twintig" 20 "dertig" 30 "veertig" 40 "vijftig" 50
                  "zestig" 60 "zeventig" 70 "tachtig" 80 "negentig" 90}
                  (-> %1 :groups second clojure.string/lower-case)))}

 "integer (numeric)"
 #"(\d{1,18})"
 {:dim :number
  :integer true
  :value (Long/parseLong (first (:groups %1)))}

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
   :value (parse-number-fr (first (:groups %1)))}

  "decimal with thousands separator"
  #"(\d+(\.\d\d\d)+,\d+)"
  {:dim :number
   :value (-> (:groups %1)
            first
            (clojure.string/replace #"\." "")
            parse-number-fr)}

 ;;
 ;; Negative number
 ;;

 "numbers prefix with -, negative or minus"
 [#"(?i)-|min|minus|negatief" (dim :number #(not (:number-prefixed %)))]
 (let [multiplier -1
      value      (* (:value %2) multiplier)
      int?       (zero? (mod value 1)) ; often true, but we could have 1.1111K
      value      (if int? (long value) value)] ; cleaner if we have the right type
   (assoc %2 :value value
            :integer int?
            :number-prefixed true)) ; prevent "- -3km" to be 3 billions

 ;;
 ;; Suffixes
 ;;

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

 "ordinals (first..19th)"
 #"(?i)(eerste|tweede|derde|vierde|vijfde|zeste|zevende|achtste|negende|tiende|elfde|twaalfde|veertiende|vijftiende|zestiende|zeventiende|achtiende|negentiende)"
 {:dim :ordinal
  :value (get {"eerste" 1 "tweede" 2 "derde" 3 "vierde" 4 "vijfde" 5 "zesde" 6 "zevende" 7 "achste" 8 "negende" 9
              "tiende" 10 "elfde" 11 "twaalfde" 12 "dertiende" 13 "veertiende" 14 "vijftiende" 15 "zestiende" 16 "zeventiende" 17
              "achtiende" 18 "negentiende" 19}
              (-> %1 :groups first clojure.string/lower-case))}

 "ordinal (digits)"
 #"0*(\d+)(\.| ?(ste|de))"
 {:dim :ordinal
  :value (read-string (first (:groups %1)))})  ; read-string not the safest
