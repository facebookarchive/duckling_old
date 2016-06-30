(
  ;;
  ;; Integers
  ;;
  "number (0..15)"
  #"(?i)(zero|uma?|d(oi|ua)s|tr(ê|e)s|quatro|cinco|seis|sete|oito|nove|dez|onze|doze|treze|(ca|qua)torze|quinze)"
  {:dim :number
   :integer true
   :value (get {"zero" 0 "um" 1 "uma" 1 "dois" 2 "duas" 2 "três" 3 "tres" 3 "quatro" 4 "cinco" 5 "seis" 6
                "sete" 7 "oito" 8 "nove" 9 "dez" 10 "onze" 11 "doze" 12 "treze" 13 "catorze" 14 "quatorze" 14 "quinze" 15}
              (-> %1 :groups first clojure.string/lower-case))}

  "dozen"
  #"(?i)d[úu]zias?"
  {:dim :number :integer true :value 12 :grain 1 :grouping true} ;;restrict composition and prevent "2 12"

  ; composition
  "number dozen"
  [(integer 1 10) (dim :number #(:grouping %))]
  {:dim :number
   :integer true
   :value (* (:value %1) (:value %2))
   :grain (:grain %2)}

  "number (20..90)"
  #"(?i)(vinte|trinta|quarenta|cin(q[uü]|co)enta|sessenta|setenta|oitenta|noventa)"
  {:dim :number
   :integer true
   :value (get {"vinte" 20 "trinta" 30 "quarenta" 40 "cinquenta" 50 "cinqüenta" 50 "cincoenta" 50 "sessenta" 60 "setenta" 70
                "oitenta" 80 "noventa" 90}
             (-> %1 :groups first clojure.string/lower-case))}

  "number (16..19)"
  [(integer 10 10) #"(?i)e" (integer 6 9)]
  {:dim :number
   :integer true
   :value (+ 10 (:value %3))}

  "number (21..29 31..39 41..49 51..59 61..69 71..79 81..89 91..99)"
  [(integer 20 90 #(#{20 30 40 50 60 70 80 90} (:value %))) #"(?i)e" (integer 1 9)]
  {:dim :number
   :integer true
   :value (+ (:value %1) (:value %3))}

  "number (16..19)"
  #"(?i)(dez[ea]sseis|dez[ea]ssete|dezoito|dez[ea]nove)"
  {:dim :number
   :integer true
   :value (get {"dezesseis" 16 "dezasseis"  16 "dezessete"  17 "dezassete" 17 "dezoito" 18 "dezenove" 19 "dezanove" 19}
              (-> %1 :groups first clojure.string/lower-case))}

  "number 100..1000 "
  #"(?i)(ce(m|to)|duzentos|trezentos|quatrocentos|quinhentos|seiscentos|setecentos|oitocentos|novecentos|mil)"
  {:dim :number
   :integer true
   :value (get {"cem" 100 "cento" 100 "duzentos" 200 "trezentos" 300 "quatrocentos" 400 "quinhentos" 500 "seiscentos" 600
                "setecentos" 700 "oitocentos" 800 "novecentos" 900 "mil" 1000}
              (-> %1 :groups first clojure.string/lower-case))}

  "numbers 200..999"
  [(integer 2 9) (integer 100 100) (integer 0 99)]
  {:dim :number
   :integer true
   :value (+ (* (:value %1) (:value %2)) (:value %3))}

  ;; numeric

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

  ;;
  ;; Decimals
  ;;

  "decimal number"
  #"(\d*,\d+)"
  {:dim :number
   :value (parse-number-fr (first (:groups %1)))}

  "number dot number"
  [(dim :number #(not (:number-prefixed %))) #"(?i)ponto" (dim :number #(not (:number-suffixed %)))]
  {:dim :number
   :value (+ (* 0.1 (:value %3)) (:value %1))}

  "decimal with thousands separator"
  #"(\d+(\.\d\d\d)+,\d+)"
  {:dim :number
   :value (-> (:groups %1)
            first
            (clojure.string/replace #"\." "")
            parse-number-fr)}

  ;; prefixes
  "numbers prefix with -, negative or minus"
  [#"(?i)-|menos" (dim :number #(not (:number-prefixed %)))]
  (let [multiplier -1
        value      (* (:value %2) multiplier)
        int?       (zero? (mod value 1)) ; often true, but we could have 1.1111K
        value      (if int? (long value) value)] ; cleaner if we have the right type
    (assoc %2 :value value
              :integer int?
              :number-prefixed true)) ; prevent "- -3km" to be 3 billions

  ;; suffixes
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

  "ordinals (primeiro..10)"
  #"(?i)((primeir|segund|quart|quint|sext|s[eé]tim|oitav|non|d[eé]cim)(os?|as?))"
  {:dim :ordinal
   :value (get {"primeiro" 1 "segundo" 2 "terceiro" 3 "quarto" 4 "quinto" 5 "sexto" 6 "sétimo" 7 "setimo" 7 "oitavo" 8 "nono" 9 "décimo" 10 "decimo" 10
                "primeiros" 1 "segundos" 2 "terceiros" 3 "quartos" 4 "quintos" 5 "sextos" 6 "sétimos" 7 "setimos" 7 "oitavos" 8 "nonos" 9 "décimos" 10 "decimos" 10
                "primeira" 1 "segunda" 2 "terceira" 3 "quarta" 4 "quinta" 5 "sexta" 6 "sétima" 7 "setima" 7 "oitava" 8 "nona" 9 "décima" 10 "decima" 10
                "primeiras" 1 "segundas" 2 "terceiras" 3 "quartas" 4 "quintas" 5 "sextas" 6 "sétimas" 7 "setimas" 7 "oitavas" 8 "nonas" 9 "décimas" 10 "decimas" 10}
              (-> %1 :groups first clojure.string/lower-case))}

  )
