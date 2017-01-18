(
 {}
 
 "0743115099"
 "0743 115 099"
 "074 31 15 099"
 "0743 11 50 99"
 "+4 0743115099"
 "004 0743115099"
 "0743115099 int 897"
 "650-283-4757"
"+1 6502834757"
"+33 4 76095663"
"06 2070 2220"
"(650)-283-4757 ext 897"
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