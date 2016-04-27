(
  ;;
  ;; Ordinal numbers
  ;;

  "ordinals (chéad, dara, etc.)"
  #"(?i)(ch[ée]ad|aon[úu]|t-aon[úu]|dara|tr[íi][úu]|ceathr[úu]|c[úu]igi[úu]|s[ée][úu]|seacht[úu]|ocht[úu]|t-ocht[úu]|nao[úu]|deichi[úu]|fichi[úu]|tr[íi]ochad[úu]|daichead[úu]|caogad[úu]|seascad[úu]|seacht[óo]d[úu]|ocht[óo]d[úu]|t-ocht[óo]d[úu]|n[óo]chad[úu]|c[ée]ad[úu]|mili[úu]|milli[úu]n[úu])"
  {:dim :ordinal
   :value (get {"chéad" 1 "chead" 1 "aonú" 1 "aonu" 1 "t-aonú" 1 "t-aonu" 1 "dara" 2 "tríú" 3 "tríu" 3 "triú" 3 "triu" 3 "ceathrú" 4 "ceathru" 4 "cúigiú" 5  "cuigiú" 5 "cúigiu" 5 "cuigiu" 5 "séú" 6 "séu" 6 "seú" 6 "seu" 6 "seachtú" 7 "seachtu" 7 "ochtú" 8 "ochtu" 8 "t-ochtú" 8 "t-ochtu" 8 "naoú" 9 "naou" 9 "deichiú" 10 "deichiu" 10 "fichiú" 20 "fichiu" 20 "tríochadú" 30 "triochadú" 30 "tríochadu" 30 "triochadu" 30 "daicheadú" 40 "daicheadu" 40 "caogadú" 50 "caogadu" 50 "seascadú" 60 "seascadu" 60 "seachtódú" 70 "seachtodú" 70 "seachtódu" 70 "seachtodu" 70 "ochtódú" 80 "ochtodú" 80 "ochtódu" 80 "ochtodu" 80 "t-ochtódú" 80 "t-ochtodú" 80 "t-ochtódu" 80 "t-ochtodu" 80 "nóchadú" 90 "nóchadu" 90 "nochadú" 90 "nochadu" 90 "céadú" 100 "céadu" 100 "ceadú" 100 "ceadu" 100 "miliú" 1000 "miliu" 1000 "milliúnú" 1000000 "milliúnu" 1000000 "milliunú" 1000000 "milliunu" 1000000}
               (-> %1 :groups first .toLowerCase))}

  "ordinal (digits)"
  #"0*(\d+) ?(?i)(a|d|[úu])"
  {:dim :ordinal
   :value (read-string (first (:groups %1)))}  ; read-string not the safest

  "count numbers"
  #"(?i)a (n[áa]id|haon|d[óo]|tr[íi]|ceathair|c[úu]ig|s[ée]|seacht|hocht|naoi|deich)"
  {:dim :number
   :integer true
   :value (get {"náid" 0 "naid" 0 "haon" 1 "dó" 2 "do" 2 "trí" 3 "tri" 3 "ceathair" 4 "cúig" 5 "cuig" 5 "sé" 6 "se" 6 "seacht" 7 "hocht" 8 "naoi" 9 "deich" 10}
               (-> %1 :groups first .toLowerCase))}

  ;; Integers
  "numbers, 20-90"
  #"(?i)(fiche|tr[íi]ocha|daichead|caoga|seasca|seacht[óo]|ocht[óo]|n[óo]cha)"
  {:dim :number
   :integer true
   :value (get {"fiche" 20 "tríocha" 30 "triocha" 30
                "daichead" 40 "caoga" 50 "seasca" 60 "seachtó" 70 "seachto" 70
                "ochtó" 80 "ochto" 80 "nócha" 90 "nocha" 90}
               (-> %1 :groups first .toLowerCase))
   :grain 1}

  "numbers, 1-10"
  #"(?i)(aon|dh[áa]|tr[íi]|ceithre|c[úu]ig|seacht|s[ée]|ocht|naoi|deich)"
  {:dim :number
   :integer true
   :value (get {"aon" 1 "dhá" 2 "dha" 2 "trí" 3 "tri" 3 "ceithre" 4
                "cúig" 5 "cuig" 5 "sé" 6 "se" 6 "seacht" 7 "ocht" 8
                "naoi" 9 "deich" 10}
               (-> %1 :groups first .toLowerCase))}

  ;; Post-nominal numbers
  "old vigesimal numbers, 20s"
  #"(?i)is (dh?[áa] fhichead|tr[íi] fichid|ceithre fichid)"
  {:dim :number
   :integer true
   :value (get {"dá fhichead" 40 "da fhichead" 40 "dhá fhichead" 40 "dha fhichead" 40 "trí fichid" 60 "tri fichid" 60 "ceithre fichid" 80}
               (-> %1 :groups first .toLowerCase))}

  "old vigesimal numbers, 20s + 10"
  #"(?i)d[ée]ag is (fiche|dh?[áa] fhichead|tr[íi] fichid|ceithre fichid)"
  {:dim :number
   :integer true
   :value (get {"fiche" 30 "dá fhichead" 50 "da fhichead" 50 "dhá fhichead" 50 "dha fhichead" 50 "trí fichid" 70 "tri fichid" 70 "ceithre fichid" 90}
               (-> %1 :groups first .toLowerCase))}

  "déag"
  #"(?i)d[ée]ag"
  {:dim :number
   :integer true
   :value 10 }

  "amháin"
  #"(?i)amh[áa]in"
  {:dim :number
   :integer true
   :value 1 }

  "integer (numeric)"
  #"(\d{1,18})"
  {:dim :number
   :integer true
   :value (Long/parseLong (first (:groups %1)))}


)