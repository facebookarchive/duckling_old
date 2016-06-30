(

  "intersect"
  [(dim :number :grain #(> (:grain %) 1)) (dim :number)] ; grain 1 are taken care of by specific rule
  (compose-numbers %1 %2)


 ;;
 ;; Integers
 ;;

  "integer (0..19)"
  #"(?i)(null|üksteist|üks|kaksteist|kaks|kolmteist|kolm|neliteist|neli|viisteist|viis|kuusteist|kuus|seitseteist|seitse|kaheksateist|kaheksa|üheksateist|üheksa|kümme)"
  ; fourteen must be before four, or it won't work because the regex will stop at four
  {:dim :number
   :integer true
   :value (get {"null" 0 "üks" 1 "kaks" 2 "kolm" 3 "neli" 4 "viis" 5
              "kuus" 6 "seitse" 7 "kaheksa" 8 "üheksa" 9 "kümme" 10 "üksteist" 11
              "kaksteist" 12 "kolmteist" 13 "neliteist" 14 "viisteist" 15 "kuusteist" 16
              "seitseteist" 17 "kaheksateist" 18 "üheksateist" 19}
              (-> %1 :groups first clojure.string/lower-case))}

  "ten"
  #"(?i)kümme"
  {:dim :number :integer true :value 10 :grain 1}

  "hundred"
  #"(?i)sada"
  {:dim :number :integer true :value 100 :grain 2}

  "thousand"
  #"(?i)tuhat"
  {:dim :number :integer true :value 1000 :grain 3}

  "million"
  #"(?i)miljoni?t?"
  {:dim :number :integer true :value 1000000 :grain 6}

  "a couple of"
  #"paar"
  {:dim :number :integer true :value 2}

  "(a )?few" ; TODO set assumption
  #"mõni"
  {:dim :number :integer true :precision :approximate :value 3}

  "integer (20..90)"
  #"(?i)(kakskümmend|kolmkümmend|nelikümmend|viiskümmend|kuuskümmend|seitsekümmend|kaheksakümmend|üheksakümmend)"
  {:dim :number
   :integer true
   :value (get {"kakskümmend" 20 "kolmkümmend" 30 "nelikümmend" 40 "viiskümmend" 50 "kuuskümmend" 60
              "seitsekümmend" 70 "kaheksakümmend" 80 "üheksakümmend" 90}
             (-> %1 :groups first clojure.string/lower-case))
   :grain 1}

  "integer 21..99"
  [(integer 10 90 #(#{20 30 40 50 60 70 80 90} (:value %))) (integer 1 9)]
  {:dim :number
   :integer true
   :value (+ (:value %1) (:value %2))}

  "integer (200..900)"
  #"(?i)(kakssada|kolmsada|nelisada|viissada|kuussada|seitsesada|kaheksasada|üheksasada)"
  {:dim :number
   :integer true
   :value (get {"kakssada" 200 "kolmsada" 300 "nelisada" 400 "viissada" 500 "kuussada" 600
              "seitsesada" 700 "kaheksasada" 800 "üheksasada" 900}
             (-> %1 :groups first clojure.string/lower-case))
   :grain 2}

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

  "integer with thousands separator space"
  #"(\d{1,3}(\s\d\d\d){1,5})"
  {:dim :number
   :integer true
   :value (-> (:groups %1)
            first
            (clojure.string/replace #"\s" "")
            Long/parseLong)}

  ; composition

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


  "decimal with thousands separator"
  #"(\d+(,\d\d\d)+\.\d+)"
  {:dim :number
   :value (-> (:groups %1)
            first
            (clojure.string/replace #"," "")
            Double/parseDouble)}

  ;; negative number
  "numbers prefix with -, negative or minus"
  [#"(?i)-|miinus|negatiivne" (dim :number #(not (:number-prefixed %)))]
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

  "ordinals (first..19th)"
  #"(?i)(esimene|teine|kolmas|neljas|viies|kuues|seitsmes|kaheksas|üheksas|kümnes|üheteistkümnes|kaheteistkümnes|kolmeteistkümnes|neljateistkümnes|viieteistkümnes|kuueteistkümnes|seitsmeteistkümnes|kaheksateistkümnes|üheksateistkümnes)"
  {:dim :ordinal
   :value (get {"esimene" 1 "teine" 2 "kolmas" 3 "neljas" 4 "viies" 5
              "kuues" 6 "seitsmes" 7 "kaheksas" 8 "üheksas" 9 "kümnes" 10 "üheteistkümnes" 11
              "kaheteistkümnes" 12 "kolmeteistkümnes" 13 "neljateistkümnes" 14 "viieteistkümnes" 15 "kuueteistkümnes" 16
              "seitsmeteistkümnes" 17 "kaheksateistkümnes" 18 "üheksateistkümnes" 19}
              (-> %1 :groups first clojure.string/lower-case))}

  "ordinal (digits)"
  #"0*(\d+)\."
  {:dim :ordinal
   :value (read-string (first (:groups %1)))}  ; read-string not the safest


  )
