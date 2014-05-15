(
 "phone number"
 #"(?i)(\+?[0-9\(][0-9\- \(\)\.]{6,16}( ?e?xt?\.? ?\d+)?)"
 {:dim :phone-number
  :val (-> %1 :groups first)}
 
 "url"
 #"((https?://)?(\w+\.)+\w{2,4}(:\d+)?[^ ]*)"
 {:dim :url
  :val (-> %1 :groups first)}

 "localhost url"
 #"((https?://)?localhost(:\d+)?[^ ]*)"
 {:dim :url
  :val (-> %1 :groups first)}

 "local url"
 #"((https?://)\w+(:\d+)?[^ ]*)"
 {:dim :url
  :val (-> %1 :groups first)}

 "email"
 #"([\w\.]+@[\w\.]+)"
 {:dim :email
  :val (-> %1 :groups first)}
 
)