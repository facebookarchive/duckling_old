(
 {}
 
 "19 99999999"
 "999999999"
 "+33 19 76095663"
 "06 2070 2220"
 "(19) 997424919"
 "+55 19 992842606"
 "(650)-283-4757 ramal 897"
 (fn [token _] (and (= :phone-number (:dim token))
                    (= (:text token) (:val token))))
 
 "http://www.bla.com"
 "www.bla.com:8080/path"
 "https://myserver?foo=bar"
 "cnn.com/info"
 "bla.com/path/path?ext=%23&foo=bla"
 "localhost"
 "localhost:8000"
 "http://kimchi" ; local url
 (fn [token _] (and (= :url (:dim token))
                    (= (:text token) (:val token))))
 
 "alex@wit.ai"
 "alex.lebrun@mail.wit.com"
 (fn [token _] (and (= :email (:dim token))
                    (= (:text token) (:val token))))
 
 )
