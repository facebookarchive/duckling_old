(
 "intersect"
  [(dim :number :grain #(> (:grain %) 1)) (dim :number)] ; grain 1 are taken care of by specific rule
  (compose-numbers %1 %2)

  "intersect (cu și)"
  [(dim :number :grain #(> (:grain %) 1)) #"(?i)(si|și)" (dim :number)] ; grain 1 are taken care of by specific rule
  (compose-numbers %1 %3)

 ;;
 ;; Integers
 ;;

    "integer (0..19)"
    #"(?i)(unsprezece|doisprezece|treisprezece|paisprezece|cincisprezece|[sș]aispreece|[sș]aptesprezece|optsprezece|nou[aă]sprezece|un[sș]pe|doi[sș]pe|trei[sș]pe|pai[sș]pe|cin[sș]pe|[sș]ai[sș]pe|[sș]apti[sș]pe|opti[sș]pe|nou[aă][sș]pe|zero|nimic|nici(.+)|una|unu(l?)|doi|dou[aă]|trei|patru|cinci|[sș]ase|[sș]apte|opt|nou[aă]|zec[ei]|[iî]nt[aâ]i|un|o)"
    {:dim :number
    :integer true
    :value (get {"zero" 0 "nimic" 0 "nici unul" 0 "nici unu" 0 "nici una" 0 "nici o" 0 "nicio" 0
              "unul" 1 "unu" 1 "una" 1 "un" 1 "o" 1 "intai" 1 "întâi" 1
              "doi" 2 "doua" 2 "două" 2 "trei" 3 "patru" 4 "cinci" 5
              "sase" 6 "șase" 6 "sapte" 7 "șapte" 7 "opt" 8 "noua" 9 "nouă" 9
              "zece" 10 "zeci" 10
              "unsprezece" 11 "unspe" 11 "unșpe" 11
              "doisprezece" 12 "doispe" 12 "doișpe" 12
              "treisprezece" 13 "treispe" 13 "treișpe" 13
              "paisprezece" 14 "paispe" 14 "paișpe" 14
              "cincisprezece" 15 "cinspe" 15 "cinșpe" 15
              "saisprezece" 16 "șaisprezece" 16 "saispe" 16 "șaișpe" 16
              "saptesprezece" 17 "șaptesprezece" 17 "saptispe" 17 "șaptișpe" 17
              "optsprezece" 18 "optispe" 18 "optișpe" 18
              "nouasprezece" 19 "nouăsprezece" 19 "nouaspe" 19 "nouășpe" 19
              }
        (-> %1 :groups first clojure.string/lower-case))
    }

    "zeci (cu spatiu)"
    #"(?i)zeci"
    {:dim :number :integer true :value 10 :grain 2}

    "suta"
    #"(?i)sut[aăe]?"
    {:dim :number :integer true :value 100 :grain 2}

    "mie"
    #"(?i)mi[ei]?"
    {:dim :number :integer true :value 1000 :grain 3}

    "milion"
    #"(?i)milio(n|ane)?"
    {:dim :number :integer true :value 1000000 :grain 6}

    "miliard"
    #"(?i)miliar(de|d)?"
    {:dim :number :integer true :value 1000000000 :grain 9}

    "integer (numeric)"
    #"(\d{1,10})"
    {:dim :number
     :integer true
     :value (Long/parseLong (first (:groups %1)))}

    "integer (20..90)"
    #"(?i)(dou[aă].?zeci|trei.?zeci|patru.?zeci|cinci.?zeci|[sș]ai.?zeci|[sș]apte.?zeci|opt.?zeci|nou[aă].?zeci)"
    {:dim :number
    :integer true
    :value (get {"douazeci" 20 "douăzeci" 20 "doua zeci" 20 "două zeci" 20 "treizeci" 30 "patruzeci" 40 "trei zeci" 30 "patru zeci" 40
              "cincizeci" 50 "saizeci" 60 "cinci zeci" 50 "sai zeci" 60 "șaizeci" 60 "șai zeci" 60
              "saptezeci" 70 "șaptezeci" 70 "sapte zeci" 70 "șapte zeci" 70 "optzeci" 80 "nouazeci" 90 "nouăzeci" 90
              "opt zeci" 80 "noua zeci" 90 "nouă zeci" 90}
             (-> %1 :groups first clojure.string/lower-case))
    :grain 2}

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

    "number zeci (cu spatiu)"
    [(integer 1 9) #"(?i).?zeci"]
    {:dim :number
    :integer true
    :value (* (:value %1) 10)
    :grain (:grain %2)}

    "number sute"
    [(integer 1 99) (integer 100 100)]
    {:dim :number
    :integer true
    :value (* (:value %1) (:value %2))
    :grain (:grain %2)}

    "number mii"
    [(integer 1 999) (integer 1000 1000)]
    {:dim :number
    :integer true
    :value (* (:value %1) (:value %2))
    :grain (:grain %2)}

    "number milioane"
    [(integer 1 99) (integer 1000000 1000000)]
    {:dim :number
    :integer true
    :value (* (:value %1) (:value %2))
    :grain (:grain %2)}

    "number miliarde"
    [(integer 1 99) (integer 1000000000 1000000000)]
    {:dim :number
    :integer true
    :value (* (:value %1) (:value %2))
    :grain (:grain %2)}

    "integer cu separator de mii dot"
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
     :value (-> (:groups %1)
                first
                (clojure.string/replace #"," ".")
                Double/parseDouble)}

    "decimal with thousands separator"
      #"(\d+(\.\d\d\d)+,\d+)"
      {:dim :number
       :value (-> (:groups %1)
                first
                (clojure.string/replace #"\." "")
                (clojure.string/replace #"," ".")
                Double/parseDouble)}

    ;; negative number
      "numbers prefix with - or minus"
      [#"(?i)-|minus\s?" (dim :number #(not (:number-prefixed %)))]
      (let [multiplier -1
            value      (* (:value %2) multiplier)
            int?       (zero? (mod value 1)) ; often true, but we could have 1.1111K
            value      (if int? (long value) value)] ; cleaner if we have the right type
        (assoc %2 :value value
                  :integer int?
                  :number-prefixed true)) ; prevent "- -3km" to be 3 billions

    ;; suffixes
        "numbers suffixes with (negativ)"
        [(dim :number #(not (:number-prefixed %))) #"(?i)(negativ|neg)" ]
        (let [multiplier -1
              value      (* (:value %1) multiplier)
              int?       (zero? (mod value 1)) ; often true, but we could have 1.1111K
              value      (if int? (long value) value)] ; cleaner if we have the right type
          (assoc %1 :value value
                    :integer int?
                    :number-suffixed true)) ; prevent "3km" to be 3 billions

    ;;
    ;; Ordinal numbers
    ;;

    "ordinals (primul..9lea)"
    #"(?i)(prim(ul|a)|(al|a) (doi.*lea|doua)|(al|a) trei.*(lea|a)|(al|a) patr.*(ulea|a)|(al|a) cinci.*(lea|a)|(al|a) [sș]ase.*(lea|a)|(al|a) [sș]apte.*(lea|a)|(al|a) opt.*(ulea|a)|(al|a) nou.*(ălea|a))"
    {:dim :ordinal
    :value (get {"primul" 1 "prima" 1 "al doilea" 2 "a doua" 2 "al doi-lea" 2 "al doi lea" 2
                "al treilea" 3 "al trei lea" 3 "al trei-lea" 3 "a treia" 3 "a trei a" 3
                "al patrulea" 4 "al patru lea" 4 "al patru-lea" 4 "a patra" 4
                "al cincilea" 5 "al cinci lea" 5 "al cinci-lea" 5 "a cincia" 5 "a cinci a" 5
                "al saselea" 6 "al șaselea" 6 "al sase lea" 6 "al șase lea" 6 "al sase-lea" 6 "al șase-lea" 6 "a sasea" 6 "a șasea" 6 "a sase a" 6 "a șase a" 6
                "al saptelea" 7 "al șsaptelea" 7 "al sapte lea" 7 "al șapte lea" 7 "al sapte-lea" 7 "al ;apte-lea" 7 "a saptea" 7 "a șaptea" 7 "a sapte a" 7 "a șapte a" 7
                "al aptulea" 8 "al opt lea" 8 "al opt-lea" 8 "a opta" 8 "a opt a" 8
                "al noualea" 9 "al nouălea" 9 "al noua lea" 9 "al nouă lea" 9 "al noua-lea" 9 "al nouă-lea" 9 "a noua" 9
                }
              (-> %1 :groups first clojure.string/lower-case))}

    "ordinal (digits)"
      [#"(?i)(al|a)" #"(0*(\d+))" #"(?i)(-lea|lea|-a|a)"]
      {:dim :ordinal
      :value (read-string (first (:groups %2)))}  ; read-string not the safest

  ; composition
  "special composition for missing hundreds like in one twenty two"
  [(integer 1 9) (integer 10 99)] ; grain 1 are taken care of by specific rule
  {:dim :number
   :integer true
   :value (+ (* (:value %1) 100) (:value %2))
   :grain 1}

 )
