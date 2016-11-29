(
 "phone number"
 #"(?i)(\+?[0-9\(][0-9\- \(\)\.]{6,16}( ?e?xt?\.? ?\d+)?)"
 {:dim :phone-number
  :value (-> %1 :groups first)}

  "phone number given by each number" ;;eight nine nine one two six seven four three nine
  [(integer 0 9) (integer 0 9) (integer 0 9) (integer 0 9) (integer 0 9) (integer 0 9) 
  	(integer 0 9) (integer 0 9) (integer 0 9) (integer 0 9)] 
  {:dim :phone-number
  :value (clojure.string/join "" [(:value %1) (:value %2) (:value %3) (:value %4) ;; To be improved
  			(:value %5) (:value %6) (:value %7) (:value %8) (:value %9) (:value %10)])}
 
 "url"
 #"((https?://)?(\w+\.)+[A-z]{2,4}(:\d+)?[^ ]*)"
 {:dim :url
  :value (-> %1 :groups first)}

 "localhost url"
 #"((https?://)?localhost(:\d+)?[^ ]*)"
 {:dim :url
  :value (-> %1 :groups first)}

 "local url"
 #"((https?://)\w+(:\d+)?[^ ]*)"
 {:dim :url
  :value (-> %1 :groups first)}

 "email"
 #"([\w\.\-_]+@[\w\.\-_]+)"
 {:dim :email
  :value (-> %1 :groups first)}
 
)