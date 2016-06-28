(defproject wit/duckling "0.4.6"
  :description "Date & Number parser"
  :license {:url "https://github.com/wit-ai/duckling"
            :comments "see LICENSE"}
  :url "https://github.com/wit-ai/duckling"
  :plugins [[s3-wagon-private "1.1.2"]
            [lein-midje "3.1.3"]]
  :repl-options {:init-ns duckling.core}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.nrepl "0.2.3"]
                 [org.clojure/tools.logging "0.2.6"]
                 [clj-time "0.8.0"]
                 [prismatic/plumbing "0.3.3"]]
  :deploy-repositories [["clojars" {:creds :gpg}]]
  :profiles {:dev {:dependencies [[org.clojure/tools.trace "0.7.6"]
                                  [midje "1.6.3"]
                                  [cheshire "5.3.1"]]}
             :uberjar {:aot [duckling.core]}}
  :test-selectors {:default (complement :benchmark)
                   :benchmark :benchmark}
  :scm {:name "git"
        :url "https://github.com/wit-ai/duckling"}
  :pom-addition [:developers [:developer
                               [:name "Wit.ai"]
                               [:url "https://wit.ai"]
                               [:email "contact@wit.ai"]
                               [:timezone "-7"]]])
