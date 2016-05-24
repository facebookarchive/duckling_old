(
    ;;
    ;; integer
    ;;
    "integer (numeric)"
    #"(\d{1,18})"
    {:dim :number
     :integer true
     :value (Long/parseLong (first (:groups %1)))}

    "integer (0..9) - word number"
    #"ศูนย์|หนึ่ง|เอ็ด|สอง|ยี่|สาม|สี่|ห้า|หก|เจ็ด|แปด|เก้า"
    {:dim :number
     :integer true
     :value (get {"ศูนย์" 0 "หนึ่ง" 1 "เอ็ด" 1 "สอง" 2 "ยี่" 2 "สาม" 3 "สี่" 4
                  "ห้า" 5 "หก" 6 "เจ็ด" 7 "แปด" 8 "เก้า" 9 }
                (:text %1))}

    "integer (0..9) - thai number"
    #"๐|๑|๒|๓|๔|๕|๖|๗|๘|๙"
    {:dim :number
     :integer true
     :value (get {"๐" 0 "๑" 1 "๒" 2 "๓" 3 "๔" 4 "๕" 5 "๖" 6 "๗" 7 "๘" 8 "๙" 9} (:text %1))}

    "integer (11..19)"
    [#"สิบ|\A๑{1}" (integer 1 9)]
    {:dim :number
     :integer true
     :value (+ 10 (:value %2))}

    "integer (20..90)"
    [(integer 2 9) #"สิบ|๐"]
    {:dim :number
     :integer true
     :value (* (:value %1) 10)}

    "integer 21..99 - word number"
    [(integer 20 90 #(#{20 30 40 50 60 70 80 90} (:value %))) (integer 1 9)]
    {:dim :number
     :integer true
     :value (+ (:value %1) (:value %2))}

     "integer 21..99 - thai number"
     [#(#{2 3 4 5 6 7 8 9} (:value %)) (integer 1 9)]
     {:dim :number
      :integer true
      :value (+ (* (:value %1) 10) (:value %2))}

    "integer (10)"
    #"สิบ|๑๐"
    {:dim :number
     :integer true
     :value (get {"สิบ" 10 "๑๐" 10} (:text %1))}

     "integer (100)"
     #"หนึ่งร้อย|ร้อย|๑๐๐"
     {:dim :number
      :integer true
      :value (get {"หนึ่งร้อย" 100 "ร้อย" 100 "๑๐๐" 100} (:text %1))}

      "integer (1000)"
      #"หนึ่งพัน|พัน|๑๐๐๐"
      {:dim :number
       :integer true
       :value (get {"หนึ่งพัน" 1000 "พัน" 1000 "๑๐๐๐" 1000} (:text %1))}

     "integer (200..900)"
     [#(#{2 3 4 5 6 7 8 9} (:value %)) #"ร้อย|๐๐"]
     {:dim :number
      :integer true
      :value (* 100 (:value %1))}
)
