(defproject wit/picsou "0.1.0"
  :description "Date & Number parser"
  :main picsou.core
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :url "https://github.com/oliviervaussy/picsou"
  :plugins [[s3-wagon-private "1.1.2"]]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/tools.nrepl "0.2.3"]
                 [org.clojure/tools.logging "0.2.6"]
                 [clj-time "0.4.4"]
                 [prismatic/plumbing "0.1.0"]]
  :deploy-repositories [["private" {:url "s3p://wit-ai/releases" :username :env :passphrase :env :sign-releases false}]]
  :profiles {:dev {:dependencies [[org.clojure/tools.trace "0.7.6"]]}
             :uberjar {:aot [picsou.core]}}
  :test-selectors {:default (complement :benchmark)
                   :benchmark :benchmark})
