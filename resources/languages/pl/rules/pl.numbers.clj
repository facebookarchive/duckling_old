(
 
 "intersect"
 [(dim :number :grain #(> (:grain %) 1)) (dim :number)] ; grain 1 are taken care of by specific rule
 (compose-numbers %1 %2) 
 
 "intersect (with and)"
 [(dim :number :grain #(> (:grain %) 1)) #"(?i)i|a" (dim :number)] ; grain 1 are taken care of by specific rule
 (compose-numbers %1 %3) 

 ;;
 ;; Integers
 ;;
 
 ;; "integer (0..19)"
 ;; #"(?i)(none|zlich|naught|nought|nil|zero|one|two|three|fourteen|four|five|sixteen|six|seventeen|seven|eighteen|eight|nineteen|nine|eleven|twelve|thirteen|fifteen|no)"
 ;; ; fourteen must be before four, or it won't work because the regex will stop at four
 ;; {:dim :number
 ;;  :integer true
 ;;  :value (get {"no" 0 "none" 0 "zlich" 0 "naught" 0 "nought" 0 "nil" 0 "zero" 0 "one" 1 "two" 2 "three" 3 "four" 4 "five" 5
 ;;             "six" 6 "seven" 7 "eight" 8 "nine" 9 "ten" 10 "eleven" 11
 ;;             "twelve" 12 "thirteen" 13 "fourteen" 14 "fifteen" 15 "sixteen" 16
 ;;             "seventeen" 17 "eighteen" 18 "nineteen" 19}
 ;;             (-> %1 :groups first .toLowerCase))}
 
 ;; "ten"
 ;; #"(?i)ten"
 ;; {:dim :number :integer true :value 10 :grain 1}

 "one"
 #"(?i)jed(en|nego|nemu|nym|na|nej|n(a|ą))"
 {:dim :number :integer true :value 1 :grain 1}
 
 "two"
 #"(?i)dw(a|(o|ó)(ch|m)|oma|iema|ie)"
 {:dim :number :integer true :value 2 :grain 1}
 
 "three"
 #"(?i)trz(y|ema|ech)"
 {:dim :number :integer true :value 3 :grain 1}

 "four"
 #"(?i)czter(ej|y|ech|em|ema)"
 {:dim :number :integer true :value 4 :grain 1}

 "five"
 #"(?i)pi(e|ę)(c|ć)(iu|oma|u)?"
 {:dim :number :integer true :value 5 :grain 1}

 "six"
 #"(?i)sze(s|ś)(c|ć)(iu|oma|u)?"
 {:dim :number :integer true :value 6 :grain 1}
 
 "seven"
 #"(?i)sied(miu|em|mioma)"
 {:dim :number :integer true :value 7 :grain 1}

 "eight"
 #"(?i)o(s|ś)(iem|miu|mioma)"
 {:dim :number :integer true :value 8 :grain 1}

 "nine"
 #"(?i)dziewi(e|ę)(ć|c)(iu|ioma)?"
 {:dim :number :integer true :value 9 :grain 1}

 "ten"
 #"(?i)dzisi(e|ę)(ć|c)(iu|ioma)?"
 {:dim :number :integer true :value 10 :grain 1}

 "eleven"
 #"(?i)jedena(stu|(s|ś)cie|stoma)"
 {:dim :number :integer true :value 11 :grain 1}

 "twelve"
 #"(?i)dwunast(u|oma)|dwana(ś|s)cie"
 {:dim :number :integer true :value 12 :grain 1}

 "thirteen"
 #"(?i)trzyna(ś|s)(tu|cie|toma)"
 {:dim :number :integer true :value 13 :grain 1}

 "fourteen"
 #"(?i)czterna(s|ś)(tu|cie|toma)"
 {:dim :number :integer true :value 14 :grain 1}

 "fifteen"
 #"(?i)pietna(s|ś)(tu|cie|toma)"
 {:dim :number :integer true :value 15 :grain 1}

 "sixteen"
 #"(?i)szesna(s|ś)(tu|cie|toma)"
 {:dim :number :integer true :value 16 :grain 1}

 "seventeen"
 #"(?i)siedemna(s|ś)(tu|cie|toma)"
 {:dim :number :integer true :value 17 :grain 1}

 "eighteen"
 #"(?i)osiemna(s|ś)(tu|cie|toma)"
 {:dim :number :integer true :value 18 :grain 1}

 "nineteen"
 #"(?i)dziewietna(s|ś)(tu|cie|toma)"
 {:dim :number :integer true :value 19 :grain 1}

 "twenty"
 #"(?i)dwadzie(ś|s)cia|dwudziest(u|oma)"
 {:dim :number :integer true :value 20 :grain 1}
 
 "thirty"
 #"(?i)trzydzie(s|ś)ci"
 {:dim :number :integer true :value 30 :grain 1}
 
 "single"
 #"(?i)single"
 {:dim :number :integer true :value 1 :grain 1}
 
 "dozen"
 #"(?i)tuzin"
 {:dim :number :integer true :value 12 :grain 1 :grouping true} ;;restrict composition and prevent "2 12"

 "hundred"
 #"(?i)sto|setki"
 {:dim :number :integer true :value 100 :grain 2}

 "thousand"
 #"(?i)ty(s|ś)i(a|ą)ce?"
 {:dim :number :integer true :value 1000 :grain 3}

 "million"
 #"(?i)miliony??"
 {:dim :number :integer true :value 1000000 :grain 6}

 "couple"
 #"pare"
 {:dim :number :integer true :value 2}
 
 "a few" ; TODO set assumption
 #"kilk(a|u)"
 {:dim :number :integer true :precision :approximate :value 3}

 "integer (20..90)"
 #"(?i)(twenty|thirty|fou?rty|fifty|sixty|seventy|eighty|ninety)"
 {:dim :number
  :integer true
  :value (get {"twenty" 20 "thirty" 30 "fourty" 40 "forty" 40 "fifty" 50 "sixty" 60
               "seventy" 70 "eighty" 80 "ninety" 90}
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
 
 "integer with thousands separator ,"
 #"(\d{1,3}(,\d\d\d){1,5})"
 {:dim :number
  :integer true
  :value (-> (:groups %1)
             first
             (clojure.string/replace #"," "")
             Long/parseLong)}
 
                                        ; composition
 "special composition for missing hundreds like in one twenty two"
 [(integer 1 9) (integer 10 99)] ; grain 1 are taken care of by specific rule
 {:dim :number
  :integer true
  :value (+ (* (:value %1) 100) (:value %2))
  :grain 1}


 "number dozen"
 [(integer 1 10) (dim :number #(:grouping %))]
 {:dim :number
  :integer true
  :value (* (:value %1) (:value %2))
  :grain (:grain %2)}


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
 [#"(?i)-|minus\s?|negative\s?" (dim :number #(not (:number-prefixed %)))]
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
 ;; TODO: optimize/streamline?
 
 "first ordinal"
 #"(?i)pierw?sz(y|ego|emu|m|(a|ą)|ej)"
 {:dim :ordinal
  :value 1}

 "second ordinal"
 #"(?i)drugi?(ego|emu|m|(a|ą)|ej)?"
 {:dim :ordinal
  :value 2}

 "third ordinal"
 #"(?i)trzeci(ego|ch|emu|m|mi|ej|(a|ą))?"
 {:dim :ordinal
  :value 3}

 "fourth ordinal"
 #"(?i)czwart(y|ego|emu|ym|(a|ą)|ej)"
 {:dim :ordinal
  :value 4}

 "fifth ordinal"
 #"(?i)pi(a|ą)t(y|ego|emu|m|(a|ą)|ej)"
 {:dim :ordinal
  :value 5}

 "sixth ordinal"
 #"(?i)sz(o|ó)st(y|ego|emu|m|(a|ą)|ej)"
 {:dim :ordinal
  :value 6}

 "seventh ordinal"
 #"(?i)si(o|ó)dm(y|ego|emu|m|(a|ą)|ej)"
 {:dim :ordinal
  :value 7}

 "8th ordinal"
 ;;case insensitivity doesn't apply to polish chars, hence the Ó
 #"(?i)(o|ó|Ó)sm(y|ego|emu|m|(a|ą)|ej)" 
 {:dim :ordinal
  :value 8}

 "9th ordinal"
 #"(?i)dziewi(a|ą)t(ym|y|ego|em|emu|(a|ą)|ej)"
 {:dim :ordinal
  :value 9}

 "10th ordinal"
 #"(?i)dziesi(a|ą)t(ym|y|ego|emu|(a|ą)|ej)"
 {:dim :ordinal
  :value 10}

 "11th ordinal"
 #"(?i)jedenast(ym|y|ego|emu|(a|ą)|ej)"
 {:dim :ordinal
  :value 11}

 "12th ordinal"
 #"(?i)dwunast(ym|y|ego|emu|(a|ą)|ej)"
 {:dim :ordinal
  :value 12}

 "13th ordinal"
 #"(?i)trzynast(ym|y|ego|emu|(a|ą)|ej)"
 {:dim :ordinal
  :value 13}

 "14th ordinal"
 #"(?i)czternast(ym|y|ego|emu|(a|ą)|ej)"
 {:dim :ordinal
  :value 14}
 
 "15th ordinal"
 #"(?i)pi(e|ę)tnast(ym|y|ego|emu|(a|ą)|ej)"
 {:dim :ordinal
  :value 15}

 "16th ordinal"
 #"(?i)szesnast(ym|y|ego|emu|(a|ą)|ej)"
 {:dim :ordinal
  :value 16}

 "17th ordinal"
 #"(?i)siedemnast(ym|y|ego|emu|(a|ą)|ej)"
 {:dim :ordinal
  :value 17}

 "18th ordinal"
 #"(?i)osiemnast(ym|y|ego|emu|(a|ą)|ej)"
 {:dim :ordinal
  :value 18}
 
 "19th ordinal"
 #"(?i)dziewi(ę|e)tnast(ym|y|ego|emu|(a|ą)|ej)"
 {:dim :ordinal
  :value 19}
 
 "20th ordinal"
 #"(?i)dwudziest(ym|y|ego|emu|(a|ą)|ej)"
 {:dim :ordinal
  :value 20}

 "21st ordinal no space"
 #"(?i)dwudziest(ym|y|ego|emu|(a|ą)|ej)pierw?sz(y|ego|emu|m|(a|ą)|ej)"
 {:dim :ordinal
  :value 21}

 "22nd ordinal no space"
 #"(?i)dwudziest(ym|y|ego|emu|(a|ą)|ej)drugi?(ego|emu|m|(a|ą)|ej)?"
 {:dim :ordinal
  :value 22}

 "23rd ordinal no space"
 #"(?i)dwudziest(ym|y|ego|emu|(a|ą)|ej)trzeci(ego|ch|emu|m|mi|ej|(a|ą))?"
 {:dim :ordinal
  :value 23}

 "24th ordinal no space"
 #"(?i)dwudziest(ym|y|ego|emu|(a|ą)|ej)czwart(y|ego|emu|ym|(a|ą)|ej)"
 {:dim :ordinal
  :value 24}
 
 "21-29th ordinal" ;;FIX WON'T HANDLE THE WHITESPACE CASE
 [#"(?i)dwudziest(ym|y|ego|emu|(a|ą)|ej)( |-)?"(dim :ordinal)]
 {:dim :ordinal :value (+ 20 (get %2 :value))}
 
 "30th ordinal"
 #"(?i)trzydziest(ym|y|ego|emu|(a|ą)|ej)"
 {:dim :ordinal
  :value 30}

 "31-39th ordinal"
 [#"(?i)trzydziest(ym|y|ego|emu|(a|ą)|ej)( |-)?"(dim :ordinal)]
 {:dim :ordinal :value (+ 30 (get %2 :value))}

 "ordinal (digits)"
 #"0*(\d+)( |-)?(szy|sza|szym|ego|go|szego|gi(ego|ej)?|st(a|y|ej)|t(ej|y|ego)|ci(ego)?)"
 {:dim :ordinal
  :value (read-string (first (:groups %1)))}  ; read-string not the safest

 
 )
