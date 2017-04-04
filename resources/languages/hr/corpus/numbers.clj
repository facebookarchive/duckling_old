(
  ; Context map
  {}

  "0"
  "nula"
  "nista"
  "ništa"
  "nistica"
  "ništica"
  (number 0)

  "1"
  "jedan"
  "sam"
  (number 1)

  "2"
  "dva"
  "par"
  (number 2)

  "33"
  "trideset i tri"
  "trideset tri"
  "0033"
  (number 33)
  
  "14"
  "cetrnaest"
  "četrnaest"
  (number 14)
  
  "16"
  "šesnaest"
  "sesnaest"
  (number 16)

  "17"
  "sedamnaest"
  (number 17)

  "18"
  "osamnaest"
  (number 18)

  "1,1"
  "jedan cijela jedan"
  "1,10"
  "01,10"
  (number 1.1)

  "0,77"
  ",77"
  (number 0.77)
  
  "100.000"
  "100000"
  "100K"
  "100k"
  (number 100000)
  
  "3M"
  "3000K"
  "3000000"
  "3.000.000"
  (number 3000000)
  
  "1.200.000"
  "1200000"
  "1,2M"
  "1200K"
  ",0012G"
  (number 1200000)

  "- 1.200.000"
  "-1200000"
  "minus 1.200.000"
  "-1,2M"
  "-1200K"
  "-,0012G"
  (number -1200000)

  "5 tisuća"
  "pet tisuća"
  "pet tisuca"
  (number 5000)

  "stotinu dvadeset dva"
  (number 122)

  "dvjesto tisuća"
  "dvjesto tisuca"
  "dvije stotine tisuca"
  "dvije stotine tisuća"
  (number 200000)

  "dvadeset i jedna tisuca jedanaest"
  "dvadeset i jedna tisuća jedanaest"
  (number 21011)

  "sedam stotina dvadeset jedna tisuća dvanaest"
  "sedam stotina dvadeset jedna tisuca dvanaest"
  (number 721012)

;  This passes but maximum stash size for sentence is reached so comment in dev
;  "trideset jedan milijun dvije stotine pedeset i šest tisuća sedam stotina dvadeset i jedan"
;  "trideset jedan milijun dvije stotine pedeset i šest tisuca sedam stotina dvadeset i jedan"
;  "trideset jedan milijun dvije stotine pedeset i šest tisuca sedam stotina dvadeset jedan"
;  "trideset i jedan milijun dvije stotine pedeset šest tisuca sedam stotina dvadeset jedan"
;  (number 31256721)

  "4."
  "4ti"
  "4ta"
  "četvrti"
  "četvrta"
  "četvrto"
  "cetvrti"
  "cetvrta"
  "cetvrto"
  (ordinal 4)
)

