(defproject clj-duckling "0.4.25-dev"
  :description "A Clojure library that parses text into structured data"
  :license {:url "https://github.com/wit-ai/duckling"
            :comments "see LICENSE"}
  :url "https://github.com/wit-ai/duckling"
  :plugins [[s3-wagon-private "1.1.2"]
            [lein-midje "3.1.3"]
            [lein-codox "0.10.3"]]
  :repl-options {:init-ns duckling.core}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.nrepl "0.2.13"]
                 [org.clojure/tools.logging "0.4.0"]
                 [clj-time "0.13.0"]
                 [prismatic/plumbing "0.5.4"]]
  :deploy-repositories [["clojars" {:creds :gpg}]]
  :profiles {:dev {:dependencies [[org.clojure/tools.trace "0.7.9"]
                                  [midje "1.8.3"]
                                  [cheshire "5.7.1"]]}
             :uberjar {:aot [duckling.core]}}
  :test-selectors {:default (complement :benchmark)
                   :benchmark :benchmark}
  :scm {:name "git"
        :url "https://github.com/dpom/clj-duckling"}
  :pom-addition [:developers [:developer
                               [:name "Dan Pomohaci"]
                              [:url "https://github.com/dpom/clj-duckling"]
                               [:email "dan.pomohaci@gmail.com"]
                               [:timezone "+3"]]]
  :codox {:doc-paths ["docs"]
          :output-path "docs/api"}

)
