(ns pedestal_tomcat.core
      (:gen-class)
      (:require [io.pedestal.http :as http]
          [io.pedestal.http.route :as route]))

(defn respond-hello [request]
  {:status  200  :body  "Hello, Pedestal com Tomcat..."})

(def routes
  (route/expand-routes
    #{["/greet" :get respond-hello :route-name :greet]}))

(defn server []
  (http/create-server
    { ::http/routes routes
      ::http/type :tomcat
      ::http/port 8888}))

(defn -main
  "Aplicação para API - Tomcat com Pedestal."
  [& args]
  (println "Hello, Pedestal com Tomcat! "))
