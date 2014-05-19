(ns picsou.core-test
  (:use [picsou.core]
        [clojure.test])
  (:require [picsou.time :as time]))

(def my-config {:fr$datetime {:corpus ["fr.time"
                                       "fr.numbers"
                                       "fr.temperature"
                                       "fr.finance"
                                       "en.communication"]
                              :rules  ["fr.time"
                                       "fr.numbers"
                                       "fr.cycles"
                                       "fr.duration"
                                       "fr.temperature"
                                       "en.finance" ; sic
                                       "en.communication"]} ; sic
                :en$datetime {:corpus ["en.time"
                                       "en.numbers"
                                       "en.temperature"
                                       "en.finance"
                                       "en.communication"]
                              :rules  ["en.time"
                                       "en.numbers"
                                       "en.cycles"
                                       "en.duration"
                                       "en.temperature"
                                       "en.finance"
                                       "en.communication"]}
                :es$datetime {:corpus ["es.time"
                                       "es.numbers"
                                       "es.temperature"
                                       "es.finance"
                                       "en.communication"]
                              :rules  ["es.time"
                                       "es.numbers"
                                       "es.cycles"
                                       "es.duration"
                                       "es.temperature"
                                       "en.finance"
                                       "en.communication"]}})

;; returns :ok if the corpus runs well, or a string with list of failures otherwise
(defn diag-corpus [run-corpus-output]
  (if (= 0 (reduce #(+ %1 (first %2)) 0 run-corpus-output)) ;; no fail
    :ok (->> run-corpus-output (filter #(not= 0 (first %))) (map second) (reduce #(str %1 "\n" %2)))))

(deftest datetime-corpus-runs-without-failure
  (reload! my-config)
  (testing "fr"
    (is (= (-> (get @corpus-map "fr$datetime") (run-corpus "fr$datetime") diag-corpus) :ok)))

  (testing "en"
    (is (= (-> (get @corpus-map "en$datetime") (run-corpus "en$datetime") diag-corpus) :ok)))

  (testing "Public API (extract)"
    (is (= [{:end 7, :start 0, :value {:from "2014-01-01T00:00:00.000-02:00", :to "2014-02-01T00:00:00.000-02:00"}, :body "january", :label "T"}]
           (extract "january" {:reference-time (time/local-date-time [2013 7 14])} nil [{:module "en$datetime"
                                                                                         :dim "time"
                                                                                         :label "T"}])))))

