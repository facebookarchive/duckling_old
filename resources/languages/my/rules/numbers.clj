(
 ;;
 ;; Integers
 ;;
  "integer 0"
  #"သုံည|မရှိ"
  {:dim :number
   :integer true
   :value 0}

  "integer (1..10)"
  #"တစ်|နှစ်|သုံး|လေး|ငါး|ခြေါက်|ခုနှစ်|ရှစ်|ကိုး|တစ်ဆယ်"
  {:dim :number
   :integer true
   :value (get {"တစ်" 1 "နှစ်" 2 "သုံး" 3 "လေး" 4 "ငါး" 5
                "ခြေါက်" 6 "ခုနှစ်" 7 "ရှစ်" 8 "ကိုး" 9 "တစ်ဆယ်" 10}
               (:text %1))}

  "integer (0..9) - numeric"
  #"၀|၁|၂|၃|၄|၅|၆|၇|၈|၉"
  {:dim :number
   :integer true
   :value (get {"၀" 0 "၁" 1 "၂" 2 "၃" 3 "၄" 4 "၅" 5
                "၆" 6 "၇" 7 "၈" 8 "၉" 9}
               (:text %1))}


  "integer (1..3) - pali"
  #"ပထမ|ဒုတိယ|တတိယ"
  {:dim :number
   :integer true
   :value (get {"ပထမ" 1 "ဒုတိယ" 2 "တတိယ" 3}
               (:text %1))}

  "integer (11..19) "
  [#"ဆယ့်" (integer 1 9)]
  {:dim :number
   :integer true
   :value (+ 10 (:value %2))}

  "integer (10..90)"
  [(integer 1 9) #"ဆယ်"]
  {:dim :number
   :integer true
   :value (* (:value %1) 10)}

  "integer (11..99) "
  [(integer 1 9) #"ဆယ့်" (integer 1 9)]
  {:dim :number
   :integer true
   :value (+ (:value %1) (* 10 (:value %3)))}

  "integer (100..900)"
  [(integer 1 9) #"ရာ"]
  {:dim :number
   :integer true
   :value (* (:value %1) 100)}

  "integer (1000..9000)"
  [(integer 1 9) #"ထောင်"]
  {:dim :number
   :integer true
   :value (* (:value %1) 1000)}

  "integer (10000..90000)"
  [(integer 1 9) #"သောင်း"]
  {:dim :number
   :integer true
   :value (* (:value %1) 10000)}
)