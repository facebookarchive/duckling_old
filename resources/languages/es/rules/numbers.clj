(
  ;;
  ;; Integers
  ;;
  "number (0..15)"
  #"(?i)((c|z)ero|un(o|a)?|dos|tr(é|e)s|cuatro|cinco|s(e|é)is|siete|ocho|nueve|die(z|s)|once|doce|trece|catorce|quince)"
  {:dim :number
   :integer true
   :value (get {"cero" 0 "zero" 0 "un" 1 "uno" 1 "una" 1 "dos" 2 "tres" 3 "trés" 3 "cuatro" 4 "cinco" 5
              "seis" 6 "séis" 6 "siete" 7 "ocho" 8 "nueve" 9 "diez" 10 "dies" 10  "once" 11
              "doce" 12 "trece" 13 "catorce" 14 "quince" 15}
              (-> %1 :groups first clojure.string/lower-case))}

  "number (20..90)"
  #"(?i)(veinte|treinta|cuarenta|cincuenta|sesenta|setenta|ochenta|noventa)"
  {:dim :number
   :integer true
   :value (get {"veinte" 20 "treinta" 30 "cuarenta" 40 "cincuenta" 50 "sesenta" 60 "setenta" 70 "ochenta" 80 "noventa" 90}
             (-> %1 :groups first clojure.string/lower-case))}

  "number (16..19)"
  [(integer 10 10) #"(?i)y" (integer 6 9)]
  {:dim :number
   :integer true
   :value (+ 10 (:value %3))}

  "number (21..29 31..39 41..49 51..59 61..69 71..79 81..89 91..99)"
  [(integer 20 90 #(#{20 30 40 50 60 70 80 90} (:value %))) #"(?i)y" (integer 1 9)]
  {:dim :number
   :integer true
   :value (+ (:value %1) (:value %3))}

  "number (16..19 21..29)"
  #"(?i)(die(c|s)is(é|e)is|diecisiete|dieciocho|diecinueve|veintiun(o|a)|veintidos|veintitr(é|e)s|veinticuatro|veinticinco|veintis(é|e)is|veintisiete|veintiocho|veintinueve)"
  {:dim :number
   :integer true
   :value (get {"dieciseis"16 "diesiseis"  16 "diesiséis"  16 "dieciséis" 16 "diecisiete" 17 "dieciocho" 18 "diecinueve" 19 "veintiuno" 21 "veintiuna" 21 "veintidos" 22 "veintitres" 23 "veintitrés" 23 "veinticuatro" 24 "veinticinco" 25
              "veintiseis" 26 "veintiséis" 26 "veintisiete" 27 "veintiocho" 28 "veintinueve" 29}
              (-> %1 :groups first clojure.string/lower-case))}

  "number 100..1000 "
  #"(?i)(cien(to)?s?|doscientos|trescientos|cuatrocientos|quinientos|seiscientos|setecientos|ochocientos|novecientos|mil)"
  {:dim :number
   :integer true
   :value (get {"cien" 100 "cientos" 100 "ciento"  100 "doscientos" 200 "trescientos" 300 "cuatrocientos" 400 "quinientos" 500 "seiscientos" 600 "setecientos" 700 "ochocientos" 800 "novecientos" 900 "mil" 1000}
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
  [(dim :number #(not (:number-prefixed %))) #"(?i)punto" (dim :number #(not (:number-suffixed %)))]
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

  "ordinals (primero..10)"
  #"(?i)(primer|tercer(os?|as?)?|(primer|segund|cuart|quint|sext|s[eé]ptim|octav|noven|d[eé]cim)(os?|as?))"
  {:dim :ordinal
   :value (get {"primer" 1 "primero" 1 "segundo" 2 "tercero" 3 "tercer" 3 "cuarto" 4 "quinto" 5 "sexto" 6 "séptimo" 7 "septimo" 7 "octavo" 8 "noveno" 9 "décimo" 10 "decimo" 10 "primeros" 1 "segundos" 2 "terceros" 3 "cuartos" 4 "quintos" 5 "sextos" 6 "séptimos" 7 "septimos" 7 "octavos" 8 "novenos" 9 "décimos" 10 "decimos" 10 "primera" 1 "segunda" 2 "tercera" 3 "cuarta" 4 "quinta" 5 "sexta" 6 "séptima" 7 "septima" 7 "octava" 8 "novena" 9 "décima" 10 "decima" 10 "primeras" 1 "segundas" 2 "terceras" 3 "cuartas" 4 "quintas" 5 "sextas" 6 "séptimas" 7 "septimas" 7 "octavas" 8 "novenas" 9 "décimas" 10 "decimas" 10}
              (-> %1 :groups first clojure.string/lower-case))}

  )
