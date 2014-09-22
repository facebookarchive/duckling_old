(ns picsou.qa
  (:require [picsou.core :as p]
            [cheshire.core :as j]))

(defn go []
  (let [all (j/parse-string (slurp "resources/export.json"))
        en (get (first (filter #(= "en" (% "body")) all)) "start")
        ;en (take 100 en)
        results (for [[phrase start' end'] en :when (<= end' (count phrase))]
                  (let [s (trim (subs phrase start' end')) ;remove whitespaces
                        {:keys [winners] :as res} (p/parse s p/default-context :en$core [{:dim :time :label "T"}])
                        {:keys [start end value] :as first-winner} (first winners)
                        covers? (and start
                                     (= 0 start)
                                     (= (count s) end))]
                    [s covers? value]))
        ko (remove second results)]
    (printf "%d/%d passing\n" (- (count results) (count ko)) (count results))
    (doseq [r ko]
      (printf "%-40s %s\n" (first r) (-> r last :start)))))