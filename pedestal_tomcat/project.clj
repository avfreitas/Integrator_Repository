(defproject pedestal_tomcat "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}

  :repl-options {:init-ns user}

  :dependencies [ [org.clojure/clojure "1.10.1"]
                  [io.pedestal/pedestal.service "0.5.7"]
                  [io.pedestal/pedestal.route "0.5.7"]
                  [io.pedestal/pedestal.tomcat "0.5.8"]
                  [org.slf4j/slf4j-simple "1.7.28"] ]

  :plugins [[lein-uberwar "0.2.1"]]

  :uberwar {:handler pedestal_tomcat.core/respond-hello}

  :jvm-opts ["-Dclojure.server.myrepl={:port,5555,:accept,clojure.core.server/repl}"]

  :main ^:skip-aot api_pedestal.core

  :target-path "target/%s"

  :profiles { :uberjar {:aot :all
                        :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}
              :dev {:dependencies []
              :source-paths ["dev"]}})
