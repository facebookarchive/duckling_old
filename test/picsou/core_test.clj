(ns picsou.core-test
  (:use [picsou.core]
        [clojure.test])
  (:require [picsou.time :as time]
            [clojure.java.io :as io]))

(def tokens (map (fn [x] {:pred x}) (range 10)))

; partial order where odd and even numbers are compared naturally between them,
; but an odd number and an even number are not comparable

(defn compare-fn [a b]
  (if (= (mod (:pred a) 2) (mod (:pred b) 2))
    (compare (:pred a) (:pred b))
    nil))

; the token with pred 8 is not resolved, all others are

(defn resolve-fn [a]
  (if (= 8 (:pred a))
    [a]
    [(assoc a :value "ok")]))
(def select-winners' @#'picsou.core/select-winners)

; the actual test now

(deftest select-winners-test
  (is (= '({:value "ok", :pred 9} {:value "ok", :pred 6})
         (select-winners' compare-fn resolve-fn tokens))))


;; returns :ok if the corpus runs well, or a string with list of failures otherwise
; (defn diag-corpus [run-corpus-output]
;   (if (= 0 (reduce #(+ %1 (first %2)) 0 run-corpus-output)) ;; no fail
;     :ok (->> run-corpus-output (filter #(not= 0 (first %))) (map second) (reduce #(str %1 "\n" %2)))))

; (deftest datetime-corpus-runs-without-failure
;   (load!)
;   (testing "fr"
;     (is (= (-> (get @corpus-map "fr$core") (run-corpus "fr$core") diag-corpus) :ok)))

;   (testing "en"
;     (is (= (-> (get @corpus-map "en$core") (run-corpus "en$core") diag-corpus) :ok)))

;   (testing "Public API (extract)"
;     (is (= [{:end 7,
;              :start 0,
;              :value {:from "2014-01-01T00:00:00.000-02:00",
;                      :to "2014-02-01T00:00:00.000-02:00"},
;              :body "january",
;              :label "T"}]
;            (extract "january" {:reference-time (time/local-date-time [2013 7 14])} nil [{:module "en$core"
;                                                                                          :dim "time"
;                                                                                          :label "T"}])))))

