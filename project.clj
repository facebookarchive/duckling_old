(defproject wit/picsou (str "0.1." (or (System/getenv "CIRCLE_BUILD_NUM") 0))
  :description "Date & Number parser"
  :main picsou.core
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :url "https://github.com/oliviervaussy/picsou"
  :cljsbuild {
    :builds [{
        ; The path to the top-level ClojureScript source directory:
        :source-paths ["src-cljs"]
        ; The standard ClojureScript compiler options:
        ; (See the ClojureScript compiler documentation for details.)
        :compiler {
          :output-to "resources/public/main.js"  ; default: target/cljsbuild-main.js
          :output-dir "resources/public/out"
          :optimizations :none
          :pretty-print true
          :source-map true}}]} ;; will add goog/ cljs/ om/ in public
  :plugins [[s3-wagon-private "1.1.2"]
            [quickie "0.2.5"]
            [lein-midje "3.1.3"]
            [lein-cljsbuild "1.0.4-SNAPSHOT"]]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.nrepl "0.2.3"]
                 [org.clojure/tools.logging "0.2.6"]
                 [org.clojure/clojurescript "0.0-2311"]
                 [om "0.7.1"]
                 [clj-time "0.8.0"]
                 [prismatic/plumbing "0.1.0"]]
  :deploy-repositories [["private" {:url "s3p://wit-ai/releases" :username :env :passphrase :env :sign-releases false}]]
  :profiles {:dev {:dependencies [[org.clojure/tools.trace "0.7.6"]
                                  [midje "1.6.3"]
                                  [cheshire "5.3.1"]]}
             :uberjar {:aot [picsou.core]}}
  :test-selectors {:default (complement :benchmark)
                   :benchmark :benchmark})
