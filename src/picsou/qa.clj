(ns picsou.qa
  (:require [picsou.core :as p]
            [cheshire.core :as j]))

(defn go []
  (let [all (j/parse-string (slurp "resources/export.json"))
        en (get (first (filter #(= "en" (% "body")) all)) "start")]
    (doseq [[phrase start end] en]
      (when (<= end (count phrase))
        (let [s (subs phrase start end)
              _ (prn s)
              {:keys [winners] :as res} (p/parse s p/default-context :en$core [{:dim :time :label "T"}])]
          (prn (map :value winners)))))))
