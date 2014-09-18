(ns picsou.qa
  (:require [picsou.core :as p]
            [cheshire.core :as j]))

(defn go []
  (let [all (j/parse-string (slurp "resources/export.json"))
        en (get (first (filter #(= "en" (% "body")) all)) "start")
        results (for [[phrase start' end'] en :when (<= end' (count phrase))]
                  (let [s (subs phrase start' end')
                        {:keys [winners] :as res} (p/parse s p/default-context :en$core [{:dim :time :label "T"}])
                        {:keys [start end value] :as first-winner} (first winners)
                        covers? (and start
                                     (= 0 start)
                                     (= (count s) end))]
                    [s covers? value]))]
    (prn (count results))
    (prn (count (filter second results)))))
