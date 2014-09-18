(
 "phone number"
 #"(?i)(\+?[0-9\(][0-9\- \(\)\.]{6,16}( ?e?xt?\.? ?\d+)?)"
 {:dim :phone-number
  :value (-> %1 :groups first)}
 
 "url"
 #"((https?://)?(\w+\.)+\w{2,4}(:\d+)?[^ ]*)"
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
 #"([\w\.]+@[\w\.]+)"
 {:dim :email
  :value (-> %1 :groups first)}
 
)