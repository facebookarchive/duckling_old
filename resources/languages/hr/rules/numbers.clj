("intersect" ;handles things like hundert zwei

  [(dim :number :grain #(> (:grain %) 1)) (dim :number)] ; grain 1 are taken care of by specific rule
  (compose-numbers %1 %2)


  "numbers i"
  [(integer 20 90 #(#{20 30 40 50 60 70 80 90} (:value %))) #"i" (integer 1 9)]
  {:dim :number :integer true :value (+ (:value %1) (:value %3))}

  ;;
  ;; Integers
  ;;

  "integer (0..19)"
  #"(?i)(ni(s|š)ta|ni(s|š)tica|nula|jedanaest|dvanaest|trinaest|jeda?n(a|u|o(ga?)?)?|dv(i?je)?(a|o)?(ma)?|tri(ma)?|(č|c)etiri|(č|c)etrnaest|petnaest|pet|(s|š)esnaest|(š|s)est|sedamnaest|sedam|osamnaest|osam|devetnaest|devet)"
  ; fourteen must be before four, or it won't work because the regex will stop at four
  {:dim     :number
   :integer true
   :value   (get
              {"nista"      0
               "ništa"      0
               "nistica"    0
               "ništica"    0
               "nula"       0
               "jedanaest"  11
               "dvanaest"   12
               "trinaest"   13
               "jedan"      1
               "jednog"     1
               "jednoga"    1
               "jedna"      1
               "jednu"      1
               "dva"        2
               "dvama"      2
               "dvoma"      2
               "dvije"      2
               "dvje"       2
               "tri"        3
               "trima"      3
               "cetiri"     4
               "cetrnaest"  14
               "četiri"     4
               "četrnaest"  14
               "petnaest"   15
               "pet"        5
               "šesnaest"   16
               "sesnaest"   16
               "šest"       6
               "sedamnaest" 17
               "sedam"      7
               "osamnaest"  18
               "osam"       8
               "devetnaest" 19
               "devet"      9}
              (-> %1 :groups first clojure.string/lower-case))}


  "single"
  #"(?i)sam"
  {:dim :number :integer true :value 1 :grain 1}

  "a pair"
  #"(?i)par"
  {:dim :number :integer true :value 2 :grain 1}

  "ten"
  #"(?i)deset|cener"
  {:dim :number :integer true :value 10 :grain 1}

  "dozen"
  #"(?i)tucet?"
  {:dim :number :integer true :value 12 :grain 1 :grouping true} ;;restrict composition and prevent "2 12"

  "hundered"
  #"(?i)stotin(u|a|e)"
  {:dim :number :integer true :value 100 :grain 2}

  "thousand"
  #"(?i)tisu(c|ć)(a|u|e)"
  {:dim :number :integer true :value 1000 :grain 3}

  "million"
  #"(?i)milij(u|o)na?"
  {:dim :number :integer true :value 1000000 :grain 6}

  "couple"
  #"(?i)par"
  {:dim :number :integer true :value 2}

  "few" ; TODO set assumption
  #"nekoliko"
  {:dim :number :integer true :precision :approximate :value 3}

  "integer (20..90)"
  #"(?i)(dvadeset|trideset|(c|č)etrdeset|pedeset|(š|s)esdeset|sedamdeset|osamdeset|devedeset)"
  {:dim     :number
   :integer true
   :value   (get
              {"dvadeset"   20
               "trideset"   30
               "cetrdeset"  40
               "četrdeset"  40
               "pedeset"    50
               "šesdeset"   60
               "sesdeset"   60
               "sedamdeset" 70
               "osamdeset"  80
               "devedeset"  90}
              (-> %1 :groups first clojure.string/lower-case))
   :grain   1}

  "integer (100..900)"
  #"(?i)(sto|dvjest(o|a)|tristo|(c|č)etiristo|petsto|(š|s)esto|sedamsto|osamsto|devetsto)"
  {:dim     :number
   :integer true
   :value   (get
              {"sto"       100
               "dvjesto"   200
               "dvjesta"   200
               "tristo"    300
               "cetiristo" 400
               "četiristo" 400
               "petsto"    500
               "sesto"     600
               "šesto"     600
               "sedamsto"  700
               "osamsto"   800
               "devetsto"  900}
              (-> %1 :groups first clojure.string/lower-case))
   :grain   1}

  "integer 21..99"
  [(integer 10 90 #(#{20 30 40 50 60 70 80 90} (:value %))) (integer 1 9)]
  {:dim :number :integer true :value (+ (:value %1) (:value %2))}

  "numbers 100..999"
  [(integer 1 9) (integer 100 100) (integer 0 99)]
  {:dim     :number
   :integer true
   :value   (+ (* (:value %1) (:value %2)) (:value %3))}

  "integer (numeric)"
  #"(\d{1,18})"
  {:dim :number :integer true :value (Long/parseLong (first (:groups %1)))}

  "integer with thousands separator ."
  #"(\d{1,3}(\.\d\d\d){1,5})"
  {:dim     :number
   :integer true
   :value   (.longValue
              (.parse (NumberFormat/getInstance Locale/GERMAN) (first (:groups %1))))}

  ; composition
  ; "number dozen"
  ; [(integer 1 10) (dim :number #(:grouping %))]
  ; {:dim :number
  ;  :integer true
  ;  :value (* (:value %1) (:value %2))
  ;  :grain (:grain %2)}


  "number hundreds"
  [(integer 1 99) (integer 100 100)]
  {:dim     :number
   :integer true
   :value   (* (:value %1) (:value %2))
   :grain   (:grain %2)}

  "number thousands"
  [(integer 1 999) (integer 1000 1000)]
  {:dim     :number
   :integer true
   :value   (* (:value %1) (:value %2))
   :grain   (:grain %2)}

  "number millions"
  [(integer 1 99) (integer 1000000 1000000)]
  {:dim     :number
   :integer true
   :value   (* (:value %1) (:value %2))
   :grain   (:grain %2)}

  ;;
  ;; Decimals
  ;;

  "decimal number"
  #"(\d*,\d+)"
  {:dim   :number
   :value (.doubleValue
            (.parse (NumberFormat/getInstance Locale/GERMAN) (first (:groups %1))))}

  "number dot number"
  [(dim :number #(not (:number-prefixed %))) #"(?i)cijela|to(c|č)ka|zarez" (dim :number #(not (:number-suffixed %)))]
  {:dim :number :value (+ (* 0.1 (:value %3)) (:value %1))}


  "decimal with thousands separator"
  #"(\d+(\.\d\d\d)+\,\d+)"
  {:dim   :number
   :value (.doubleValue
            (.parse (NumberFormat/getInstance Locale/GERMAN) (first (:groups %1))))}

  ;; negative number
  "numbers prefix with -, negative or minus"
  [#"(?i)-|minus|negativ" (dim :number #(not (:number-prefixed %)))]
  (let [multiplier -1
        value      (* (:value %2) multiplier)
        int?       (zero? (mod value 1)) ; often true, but we could have 1.1111K
        value      (if int? (long value) value)]
    ; cleaner if we have the right type
    (assoc %2 :value        value
           :integer         int?
           :number-prefixed true))
  ; prevent "- -3km" to be 3 billions


  ;; suffixes

  ; note that we check for a space-like char after the M, K or G
  ; to avoid matching 3 Mandarins
  "numbers suffixes (K, M, G)"
  [(dim :number #(not (:number-suffixed %))) #"(?i)([kmg])(?=[\W\$€]|$)"]
  (let [multiplier (get {"k" 1000 "m" 1000000 "g" 1000000000}
                        (-> %2 :groups first clojure.string/lower-case))
        value      (* (:value %1) multiplier)
        int?       (zero? (mod value 1)) ; often true, but we could have 1.1111K
        value      (if int? (long value) value)]
    ; cleaner if we have the right type
    (assoc %1 :value        value
           :integer         int?
           :number-suffixed true))
  ; prevent "3km" to be 3 billions

  ;;
  ;; Ordinal numbers
  ;;

  "ordinals (first..19th)"
  #"(?i)(prv(i|a|o(ga?)?)|drug(i|a|o(ga?)?)|tre(c|ć)(i|a|e(ga?)?)|(č|c)etvrt(i|a|o(ga?)?)|pet(i|a|o(ga?)?)|(š|s)est(i|a|o(ga?)?)|sedm(i|a|o(ga?)?)|osm(i|a|o(ga?)?)|devet(i|a|o(ga?)?)|deset(i|a|o(ga?)?)|jedanaest(i|a|o(ga?)?)|dvanaest(i|a|o(ga?)?)|trinaest(i|a|o(ga?)?)|(c|č)etrnaest(i|a|o(ga?)?)|petnaest(i|a|o(ga?)?)|(s|š)esnaest(i|a|o(ga?)?)|sedamnaest(i|a|o(ga?)?)|osamnaest(i|a|o(ga?)?)|devetnaest(i|a|o(ga?)?))"
  {:dim   :ordinal
   :value (get
            {"prvi"          1
             "prva"          1
             "prvo"          1
             "prvog"         1
             "prvoga"        1
             "drugi"         2
             "druga"         2
             "drugo"         2
             "drugog"        2
             "drugoga"       2
             "treci"         3
             "treca"         3
             "trece"         3
             "treći"         3
             "treća"         3
             "trećeg"        3
             "trećega"       3
             "cetvrti"       4
             "cetvrta"       4
             "cetvrto"       4
             "cetvrtog"      4
             "cetvrtoga"     4
             "četvrti"       4
             "četvrta"       4
             "četvrto"       4
             "četvrtog"      4
             "četvrtoga"     4
             "peti"          5
             "peta"          5
             "peto"          5
             "petog"         5
             "petoga"        5
             "sesti"         6
             "sesta"         6
             "sesto"         6
             "sestog"        6
             "sestoga"       6
             "šesti"         6
             "šesta"         6
             "šesto"         6
             "šestog"        6
             "šestoga"       6
             "sedmi"         7
             "sedma"         7
             "sedmo"         7
             "sedmog"        7
             "sedmoga"       7
             "osmi"          8
             "osma"          8
             "osmo"          8
             "osmog"         8
             "osmoga"        8
             "deveti"        9
             "deveta"        9
             "deveto"        9
             "devetog"       9
             "devetoga"      9
             "deseti"        10
             "deseta"        10
             "deseto"        10
             "desetog"       10
             "desetoga"      10
             "jedanaesti"    11
             "jedanaesta"    11
             "jedanaesto"    11
             "jedanaestog"   11
             "jedanaestoga"  11
             "dvanaesti"     12
             "dvanaesta"     12
             "dvanaesto"     12
             "dvanaestog"    12
             "dvanaestoga"   12
             "trinaesti"     13
             "trinaesta"     13
             "trinaesto"     13
             "trinaestog"    13
             "trinaestoga"   13
             "cetrnaesti"    14
             "cetrnaesta"    14
             "cetrnaesto"    14
             "cetrnaestog"   14
             "cetrnaestoga"  14
             "četrnaesti"    14
             "četrnaesta"    14
             "četrnaesto"    14
             "četrnaestog"   14
             "četrnaestoga"  14
             "petnaesti"     15
             "petnaesta"     15
             "petnaesto"     15
             "petnaestog"    15
             "petnaestoga"   15
             "sesnaesti"     16
             "sesnaesta"     16
             "sesnaesto"     16
             "sesnaestog"    16
             "sesnaestoga"   16
             "šesnaesti"     16
             "šesnaesta"     16
             "šesnaesto"     16
             "šesnaestog"    16
             "šesnaestoga"   16
             "sedamnaesti"   17
             "sedamnaesta"   17
             "sedamnaesto"   17
             "sedamnaestog"  17
             "sedamnaestoga" 17
             "osamnaesti"    18
             "osamnaesta"    18
             "osamnaesto"    18
             "osamnaestog"   18
             "osamnaestoga"  18
             "devetnaesti"   19
             "devetnaesta"   19
             "devetnaesto"   19
             "devetnaestog"  19
             "devetnaestoga" 19}
            (-> %1 :groups first clojure.string/lower-case))}

  "ordinal (digits)"
  #"0*(\d+)(\.| ?(t(i|a)(n|r|s)?)|(ste(n|r|s)?))"
  {:dim :ordinal :value (read-string (first (:groups %1)))})  ; read-string not the safest
