(ns clojure_mysql.core
  (:gen-class))

(require '[clojure.java.jdbc :as jdbc])

(let [db-host "localhost"
      db-port 3307
      db-name "scpe"]

(def db {:classname "com.mysql.cj.jdbc.Driver"
         :subprotocol "mysql"
         :subname (str "//" db-host ":" db-port "/" db-name "?useTimezone=true&serverTimezone=UTC")
         :user "root"
         :password "maua"}))

(def resp (jdbc/query  db ["SELECT * FROM curso WHERE id_curso = 1"]))

(defn -main
  "Código clojure para acessar MySQL via jdbc"
  [& args]
  (println resp))
