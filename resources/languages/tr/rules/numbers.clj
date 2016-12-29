(

  "intersect"
  [(dim :number :grain #(> (:grain %) 1)) (dim :number)] ; grain 1 are taken care of by specific rule
  (compose-numbers %1 %2)

 ;;
 ;; Integers
 ;;

  "integer (0..9)"
  #"(?i)(yok|hiç|sıfır|bir|bi|tek|yek|iki|üç|dört|beş|altı|yedi|sekiz|dokuz)"
  ; 'bir' must be before 'bi' (an informal form of 'bir'), or it won't work because the regex will stop at 'bi'.
  {:dim :number
	  :integer true
	  :value (get {"yok" 0 "hiç" 0 "sıfır" 0 "bi" 1 "bir" 1 "tek" 1 "yek" 1 "iki" 2 "üç" 3 "dört" 4 "beş" 5 
	  "altı" 6 "yedi" 7 "sekiz" 8 "dokuz" 9}
      (-> %1 :groups first clojure.string/lower-case))}
  		  
  "ten"
  #"(?i)on"
  {:dim :number :integer true :value 10 :grain 1}

  "a couple (of)"
  #"(?i)(bir )?çift"
  {:dim :number :integer true :value 2 :grain 1}

  "dozen"
  #"(?i)düzine"
  {:dim :number :integer true :value 12 :grain 1 :grouping true} ;;restrict composition and prevent "2 12"
  
  "group of ten(s)"
  #"(?i)deste"
  {:dim :number :integer true :value 10 :grain 1 :grouping true}

  "hundred"
  #"(?i)yüz"
  {:dim :number :integer true :value 100 :grain 2}

  "thousand"
  #"(?i)bin"
  {:dim :number :integer true :value 1000 :grain 3}

  "million"
  #"(?i)milyon"
  {:dim :number :integer true :value 1000000 :grain 6}
  
  "few" ; TODO set assumption
  #"(bir)?az"
  {:dim :number :integer true :precision :approximate :value 3}

  "integer (10..90)"
  #"(?i)(on|yirmi|otuz|kırk|elli|atmış|altmış|yetmiş|seksen|doksan)"
  ; 60 is 'atmış' in Turkish. However, some people use 'altmış' incorrectly.
  {:dim :number
   :integer true
   :value (get {"on" 10 "yirmi" 20 "otuz" 30 "kırk" 40 "elli" 50 "atmış" 60 "altmış" 60
              "yetmiş" 70 "seksen" 80 "doksan" 90}
             (-> %1 :groups first clojure.string/lower-case))
   :grain 1}
   
  "integer 11..19 21..29 31..39 41..49 51..59 61..69 71..79 81..89 91..99"
  #"(?i)((on|yirmi|otuz|kırk|elli|atmış|altmış|yetmiş|seksen|doksan)(bir|bi|iki|üç|dört|beş|altı|yedi|sekiz|dokuz))"
  {:dim :number
  		:integer true
		:value (get {"onbir" 11 "onbi" 11 "oniki" 12 "onüç" 13 "ondört" 14 "onbeş" 15 "onaltı" 16 "onyedi" 17 "onsekiz" 18 "ondokuz" 19
   					 "yirmibir" 21 "yirmibi" 21 "yirmiiki" 22 "yirmiüç" 23 "yirmidört" 24 "yirmibeş" 25 "yirmialtı" 26 "yirmiyedi" 27 "yirmisekiz" 28 "yirmidokuz" 29
					 "otuzbir" 31 "otuzbi" 31 "otuziki" 32 "otuzüç" 33 "otuzdört" 34 "otuzbeş" 35 "otuzaltı" 36 "otuzyedi" 37 "otuzsekiz" 38 "otuzdokuz" 39
					 "kırkbir" 41 "kırkbi" 41 "kırkiki" 42 "kırküç" 43 "kırkdört" 44 "kırkbeş" 45 "kırkaltı" 46 "kırkyedi" 47 "kırksekiz" 48 "kırkdokuz" 49
					 "ellibir" 51 "ellibi" 51 "elliiki" 52 "elliüç" 53 "ellidört" 54 "ellibeş" 55 "ellialtı" 56 "elliyedi" 57 "ellisekiz" 58 "ellidokuz" 59
					 "atmışbir" 61 "atmışiki" 62 "atmışüç" 63 "atmışdört" 64 "atmışbeş" 65 "atmışaltı" 66 "atmışyedi" 67 "atmışsekiz" 68 "atmışdokuz" 69
					 "altmışbir" 61 "altmışiki" 62 "altmışüç" 63 "altmışdört" 64 "altmışbeş" 65 "altmışaltı" 66 "altmışyedi" 67 "altmışsekiz" 68 "altmışdokuz" 69
					 "yetmişbir" 71 "yetmişbi" 71 "yetmişiki" 72 "yetmişüç" 73 "yetmişdört" 74 "yetmişbeş" 75 "yetmişaltı" 76 "yetmişyedi" 77 "yetmişsekiz" 78 "yetmişdokuz" 79
					 "seksenbir" 81 "seksenbi" 81 "sekseniki" 82 "seksenüç" 83 "seksendört" 84 "seksenbeş" 85 "seksenaltı" 86 "seksenyedi" 87 "seksensekiz" 88 "seksendokuz" 89
					 "doksanbir" 91 "doksanbi" 91 "doksaniki" 92 "doksanüç" 93 "doksandört" 94 "doksanbeş" 95 "doksanaltı" 96 "doksanyedi" 97 "doksansekiz" 98 "doksandokuz" 99
	    }
	    (-> %1 :groups first clojure.string/lower-case))
	    :grain 1}

  "integer 11..99"
  [(integer 10 90 #(#{10 20 30 40 50 60 70 80 90} (:value %))) (integer 1 9)]
  {:dim :number
	  :integer true
	  :value (+ (:value %1) (:value %2))}
   
  "integer 100..900"
  #"(?i)(yüz|ikiyüz|üçyüz|dörtyüz|beşyüz|altıyüz|yediyüz|sekizyüz|dokuzyüz)"
  {:dim :number
        :integer true
		:value (get {"yüz" 100 "ikiyüz" 200 "üçyüz" 300 "dörtyüz" 400 "beşyüz" 500 "altıyüz" 600 "yediyüz" 700 "sekizyüz" 800 "dokuzyüz" 900}
		(-> %1 :groups first clojure.string/lower-case))
		:grain 2}
	  
  "integer 1000..9000"
  #"(?i)(bin|ikibin|üçbin|dörtbin|beşbin|altıbin|yedibin|sekizbin|dokuzbin)"
  {:dim :number
	  :integer true
	  :value (get {"bin" 1000 "ikibin" 2000 "üçbin" 3000 "dörtbin" 4000 "beşbin" 5000 "altıbin" 6000 "yedibin" 7000 "sekizbin" 8000 "dokuzbin" 9000}
	  (-> %1 :groups first clojure.string/lower-case))
	  :grain 3}
	  
  "integer 10000..90000"
  #"(?i)(onbin|yirmibin|otuzbin|kırkbin|ellibin|atmışbin|altmışbin|yetmişbin|seksenbin|doksanbin)"
  {:dim :number
	  	:integer true
		:value (get {"onbin" 10000 "yirmibin" 20000 "otuzbin" 30000 "kırkbin" 40000 "ellibin" 50000 "atmışbin" 60000 "altmışbin" 60000 "yetmişbin" 70000 "seksenbin" 80000 "doksanbin" 90000}
		(-> %1 :groups first clojure.string/lower-case))
		:grain 4}

  "integer 100000..900000"
  #"(?i)(yüzbin|ikiyüzbin|üçyüzbin|dörtyüzbin|beşyüzbin|altıyüzbin|yediyüzbin|sekizyüzbin|dokuzyüzbin)"
  {:dim :number
	  	:integer true
		:value (get {"yüzbin" 100000 "ikiyüzbin" 200000 "üçyüzbin" 300000 "dörtyüzbin" 400000 "beşyüzbin" 500000 "altıyüzbin" 600000 "yediyüzbin" 700000 "sekizyüzbin" 800000 "dokuzyüzbin" 900000}
		(-> %1 :groups first clojure.string/lower-case))
		:grain 5}
		
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

  "number dozen"
  [(integer 1 100) (dim :number #(:grouping %))]
  {:dim :number
   :integer true
   :value (* (:value %1) (:value %2))
   :grain (:grain %2)}

  "number hundreds"
  [(integer 2 9) (integer 100 100)]
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
  [(integer 2 999) (integer 1000000 1000000)]
  {:dim :number
   :integer true
   :value (* (:value %1) (:value %2))
   :grain (:grain %2)}

  ;;
  ;; Decimals
  ;;

  "quarter"
  #"(?i)(çeyrek)"
  {:dim :number :value 0.25}
  
  "half"
  #"(?i)(yarım)"
  {:dim :number :value 0.5}

  "decimal number"
  #"(\d*\.\d+)"
  {:dim :number
   :value (Double/parseDouble (first (:groups %1)))}

  "number dot number"
  [(dim :number #(not (:number-prefixed %))) #"(?i)nokta|virgül" (dim :number #(not (:number-suffixed %)))]
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
  [#"(?i)-|eksi\s?|negatif\s?" (dim :number #(not (:number-prefixed %)))]
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
  [(dim :number #(not (:number-suffixed %))) #"(?i)([kmgb])(?=[\W\$€]|$)"]
  (let [multiplier (get {"k" 1000 "m" 1000000 "g" 1000000000 "b" 1000}
                        (-> %2 :groups first clojure.string/lower-case))
        value      (* (:value %1) multiplier)
        int?       (zero? (mod value 1)) ; often true, but we could have 1.1111K
        value      (if int? (long value) value)] ; cleaner if we have the right type
    (assoc %1 :value value
              :integer int?
              :number-suffixed true)) ; prevent "3km" to be 3 billions

  "number suffixes (half-suffix)"
  [(dim :number #(not (:half-suffixed %))) #"(?i)(buçuk)(?=[\W\$€]|$)"]
  (let [value (+ (long (:value %1)) 0.5)
  	    int?  (zero? (mod value 1))
		value (if int? (long value) value)]
  	(assoc %1 :value value
		      :integer int?
		      :half-suffixed true))
			  
  "number suffixes (half-suffix text) (1..9)"
  #"(?i)((bir|bi|iki|üç|dört|beş|altı|yedi|sekiz|dokuz)(buçuk))"
  {:dim :number
	    :value (get {"birbuçuk" 1.5 "bibuçuk" 1.5 "ikibuçuk" 2.5 "üçbuçuk" 3.5 "dörtbuçuk" 4.5 "beşbuçuk" 5.5
	    "altıbuçuk" 6.5 "yedibuçuk" 7.5 "sekizbuçuk" 8.5 "dokuzbuçuk" 9.5}
        (-> %1 :groups first clojure.string/lower-case))}

  "number suffixes (half-suffix text) (10..90)"
  #"(?i)((on|yirmi|otuz|kırk|elli|atmış|altmış|yetmiş|seksen|doksan)(buçuk))"
  {:dim :number
        :value (get {"onbuçuk" 10.5 "yirmibuçuk" 20.5 "otuzbuçuk" 30.5 "kırkbuçuk" 40.5 "ellibuçuk" 50.5 "atmışbuçuk" 60.5
		"altmışbuçuk" 60.5 "yetmişbuçuk" 70.5 "seksenbuçuk" 80.5 "doksanbuçuk" 90.5}
	    (-> %1 :groups first clojure.string/lower-case))}


   "integer 11..19 21..29 31..39 41..49 51..59 61..69 71..79 81..89 91..99"
   #"(?i)((on|yirmi|otuz|kırk|elli|atmış|altmış|yetmiş|seksen|doksan)(bir|bi|iki|üç|dört|beş|altı|yedi|sekiz|dokuz)(buçuk))"
   {:dim :number
	  		:value (get {"onbirbuçuk" 11.5 "onbibuçuk" 11.5 "onikibuçuk" 12.5 "onüçbuçuk" 13.5 "ondörtbuçuk" 14.5 
						 "onbeşbuçuk" 15.5 "onaltıbuçuk" 16.5 "onyedibuçuk" 17.5 "onsekizbuçuk" 18.5 "ondokuzbuçuk" 19.5
	     			     "yirmibirbuçuk" 21.5 "yirmibibuçuk" 21.5 "yirmiikibuçuk" 22.5 "yirmiüçbuçuk" 23.5 "yirmidörtbuçuk" 24.5 
						 "yirmibeşbuçuk" 25.5 "yirmialtıbuçuk" 26.5 "yirmiyedibuçuk" 27.5 "yirmisekizbuçuk" 28.5 "yirmidokuzbuçuk" 29.5
	  					 "otuzbirbuçuk" 31.5 "otuzbibuçuk" 31.5 "otuzikibuçuk" 32.5 "otuzüçbuçuk" 33.5 "otuzdörtbuçuk" 34 
						 "otuzbeşbuçuk" 35.5 "otuzaltıbuçuk" 36.5 "otuzyedibuçuk" 37.5 "otuzsekizbuçuk" 38.5 "otuzdokuzbuçuk" 39.5
	  					 "kırkbirbuçuk" 41.5 "kırkbibuçuk" 41.5 "kırkikibuçuk" 42.5 "kırküçbuçuk" 43.5 "kırkdörtbuçuk" 44 
						 "kırkbeşbuçuk" 45.5 "kırkaltıbuçuk" 46.5 "kırkyedibuçuk" 47.5 "kırksekizbuçuk" 48.5 "kırkdokuzbuçuk" 49.5
	  					 "ellibirbuçuk" 51.5 "ellibibuçuk" 51.5 "elliikibuçuk" 52.5 "elliüçbuçuk" 53.5 "ellidörtbuçuk" 54 
						 "ellibeşbuçuk" 55.5 "ellialtıbuçuk" 56.5 "elliyedibuçuk" 57.5 "ellisekizbuçuk" 58.5 "ellidokuzbuçuk" 59.5
	  					 "atmışbirbuçuk" 61.5 "atmışikibuçuk" 62.5 "atmışüçbuçuk" 63.5 "atmışdörtbuçuk" 64.5 
						 "atmışbeşbuçuk" 65.5 "atmışaltıbuçuk" 66.5 "atmışyedibuçuk" 67.5 "atmışsekizbuçuk" 68.5 "atmışdokuzbuçuk" 69.5
	  					 "altmışbirbuçuk" 61.5 "altmışikibuçuk" 62.5 "altmışüçbuçuk" 63.5 "altmışdörtbuçuk" 64.5 
						 "altmışbeşbuçuk" 65.5 "altmışaltıbuçuk" 66.5 "altmışyedibuçuk" 67.5 "altmışsekizbuçuk" 68.5 "altmışdokuzbuçuk" 69.5
	  					 "yetmişbirbuçuk" 71.5 "yetmişbibuçuk" 71.5 "yetmişikibuçuk" 72.5 "yetmişüçbuçuk" 73.5 "yetmişdörtbuçuk" 74.5 
						 "yetmişbeşbuçuk" 75.5 "yetmişaltıbuçuk" 76.5 "yetmişyedibuçuk" 77.5 "yetmişsekizbuçuk" 78.5 "yetmişdokuzbuçuk" 79.5
	  					 "seksenbirbuçuk" 81.5 "seksenbibuçuk" 81.5 "seksenikibuçuk" 82.5 "seksenüçbuçuk" 83.5 "seksendörtbuçuk" 84.5 
						 "seksenbeşbuçuk" 85.5 "seksenaltıbuçuk" 86.5 "seksenyedibuçuk" 87.5 "seksensekizbuçuk" 88.5 "seksendokuzbuçuk" 89.5
	  					 "doksanbirbuçuk" 91.5 "doksanbibuçuk" 91.5 "doksanikibuçuk" 92.5 "doksanüçbuçuk" 93.5 "doksandörtbuçuk" 94.5 
						 "doksanbeşbuçuk" 95.5 "doksanaltıbuçuk" 96.5 "doksanyedibuçuk" 97.5 "doksansekizbuçuk" 98.5 "doksandokuzbuçuk" 99.5
	  	    }
	  	    (-> %1 :groups first clojure.string/lower-case))}


  "decimal thousand | million"
  [(dim :number #(< (:value %) 999.9) #(not (:integer %))) (integer 1000 1000000 #(#{1000 1000000} (:value %)))]
  (let [value (* (:value %1) (long (:value %2)))
  	    int?  (zero? (mod value 1))
		value (if int? (long value) value)]
  	(assoc %1 :value value
		      :integer int?))
  
  ;;
  ;; Ordinal numbers
  ;;

  "ordinals (first..31st)"
  #"(?i)(birinci|ikinci|üçüncü|dördüncü|beşinci|altıncı|yedinci|sekizinci|dokuzuncu|onuncu|on birinci|on ikinci|on üçüncü|on dördüncü|on beşinci|on altıncı|on yedinci|on sekizinci|on dokuzuncu|yirminci|yirmi birinci|yirmi ikinci|yirmi üçüncü|yirmi dördüncü|yirmi beşinci|yirmi altıncı|yirmi yedinci|yirmi sekizinci|yirmi dokuzuncu|otuzuncu|otuz birinci)"
  {:dim :ordinal
   :value (get {"birinci" 1 "ikinci" 2 "üçüncü" 3 "dördüncü" 4 "beşinci" 5
              "altıncı" 6 "yedinci" 7 "sekizinci" 8 "dokuzuncu" 9 "onuncu" 10 "on birinci" 11
              "on ikinci" 12 "on üçüncü" 13 "on dördüncü" 14 "on beşinci" 15 "on altıncı" 16
              "on yedinci" 17 "on sekizinci" 18 "on dokuzuncu" 19 "yirminci" 20 "yirmi birinci" 21
              "yirmi ikinci" 22 "yirmi üçüncü" 23 "yirmi dördüncü" 24 "yirmi beşinci" 25
              "yirmi altıncı" 26 "yirmi yedinci" 27 "yirmi sekizinci" 28 "yirmi dokuzuncu" 29
              "otuzuncu" 30 "otuz birinci" 31}
              (-> %1 :groups first clojure.string/lower-case))}

  "ordinal (digits)"
  #"0*(\d+) ?('?)(inci|nci|ıncı|ncı|uncu|ncu|üncü|ncü|.)"
  {:dim :ordinal
   :value (read-string (first (:groups %1)))}  ; read-string not the safest


  )
