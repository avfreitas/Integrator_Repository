(defproject clojure_mysql "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}

  :repl-options {:init-ns user}

  :dependencies [ [org.clojure/clojure "1.10.1"]
                  [org.clojure/java.jdbc "0.7.9"]
                  [mysql/mysql-connector-java "8.0.21"]]

  :jvm-opts ["-Dclojure.server.myrepl={:port,5555,:accept,clojure.core.server/repl}"]

  :main ^:skip-aot clojure_mysql.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
              :jvm-opts ["-Dclojure.compiler.direct-linking=true"] }
              :dev {:dependencies []
              :source-paths ["dev"]}})
