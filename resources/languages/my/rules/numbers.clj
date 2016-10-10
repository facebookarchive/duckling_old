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


  "integer (1..10) - pali"
  #"ပထမ|ဒုတိယ|တတိယ"
  {:dim :number
   :integer true
   :value (get {"ပထမ" 1 "ဒုတိယ" 2 "တတိယ" 3}
               (:text %1))}

)