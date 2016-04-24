(ns duckling.core-test
  (:use [duckling.core]
        [clojure.test])
  (:require [clojure.java.io :as io]
            [duckling.resource :as res]))

(def tokens (map (fn [x] {:pred x}) (range 10)))

; partial order where odd and even numbers are compared naturally between them,
; but an odd number and an even number are not comparable

(defn compare-fn [a b]
  (when (= (mod (:pred a) 2) (mod (:pred b) 2))
    (compare (:pred a) (:pred b))))

; the token with pred 8 is not resolved, all others are

(defn resolve-fn [a]
  (if (= 8 (:pred a))
    [a]
    [(assoc a :value "ok")]))
(def select-winners' @#'duckling.core/select-winners)

; the actual test now

(deftest select-winners-test
  (is (= '({:value "ok", :pred 9} {:value "ok", :pred 6})
         (select-winners' compare-fn (constantly nil) resolve-fn tokens))))


(defn diag-corpus
  "Returns :ok if the corpus runs well, or a string with list of failures otherwise."
  [run-corpus-output]
  (if (= 0 (reduce #(+ %1 (first %2)) 0 run-corpus-output)) ;; no fail
    :ok
    (->> run-corpus-output
         (filter #(not= 0 (first %)))
         (map second)
         (reduce #(str %1 "\n" %2)))))

(defmacro with-timeout [millis res & body]
  `(let [future# (future ~@body)]
     (try
       (.get future# ~millis java.util.concurrent.TimeUnit/MILLISECONDS)
       (catch java.util.concurrent.TimeoutException x#
         (do
           (future-cancel future#)
           ~res)))))

(deftest datetime-corpus-runs-without-failure
  (load!)
  (testing "fr and en"
    (are [lang] (let [module (format "%s$core" lang)]
                  (= :ok (-> (get @corpus-map module)
                             (run-corpus module)
                             diag-corpus)))
      "fr"
      "en"))
  (testing "Public API (extract)"
    (is (= [{:end 12
             :start 0
             :value {:values
                     '({:type "value"
                        :value "2014-01-01T00:00:00.000-02:00"
                        :grain :month})
                      :type "value"
                      :value "2014-01-01T00:00:00.000-02:00"
                      :grain
                      :month}
             :body "january 2014"
             :label "T"}]
           (extract "january 2014" (default-context :corpus) nil [{:module "en$core"
                                                                   :dim "time"
                                                                   :label "T"}]))))
  (testing "Public API (extract) with leven-stash"
    (is (= [{:end 9
             :start 0
             :value {:value 2
                     :unit "brasse"}
             :body "2 brasses"
             :label "T"}]
           (extract "2 brasses" (default-context :corpus) [{:dim :leven-unit
                                                            :value "brasse"
                                                            :pos 2
                                                            :end 9}]
                                                          [{:module "en$core"
                                                            :dim "quantity"
                                                            :label "T"}]))))
  (testing "Very big one"
    (is (< 1
           (count
             (with-timeout 10000 "TIMEOUT!!"
                           (extract "Oct. 12 from 2 to 5 p.m. Monday-Friday, Sept. 7-March 1, 8 a.m.-noon, 1-5 p.m."
                                    (default-context :corpus) nil [{:module "en$core"
                                                                    :dim "time"
                                                                    :label "T"}])))))))

(deftest load!-api-test
  (let [check (fn [arg exp]
                (= (set exp) (set (load! arg))))]
    (testing "load! should load all languages by default"
      (with-redefs [res/get-files (constantly ["numbers.clj"])
                    res/get-subdirs (constantly ["en" "cn" "pt"])]
        (is (check nil [:en$core :cn$core :pt$core]))
        (is (check {} [:en$core :cn$core :pt$core]))
        (is (check {:config {} :languages []} [:en$core :cn$core :pt$core]))))
    (testing "load! should accept a list of languages"
      (are [arg exp] (check {:languages arg} exp)
        ["fr"] [:fr$core]
        ["foo" "bar"] []
        ["bar" "es" "pt" "ru"] [:es$core :pt$core :ru$core]))
    (testing "load! should accept a custom config"
      (is (check {:config {:en$numbers {:corpus ["numbers"] :rules ["numbers"]}}}
                 [:en$numbers])))
    (testing "load! should return the list of loaded modules"
      (are [arg exp] (check arg exp)
        {:languages ["fr"]} [:fr$core]
        {:languages ["fr" "foo" "en"]} [:fr$core :en$core]
        {:config {:en$numbers {:corpus ["numbers"] :rules ["numbers"]}}} [:en$numbers]

        {:config {:en$numbers {:corpus ["numbers"] :rules ["numbers"]}}
         :languages ["fr" "blah"]}
        [:fr$core :en$numbers]))))
