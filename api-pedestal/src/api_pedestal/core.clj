
  (ns api-pedestal.core
      (:gen-class)
      (:require [io.pedestal.http :as http]
          [io.pedestal.http.route :as route]))

(defn respond-hello [request]
  {:status  200  :body  "Hello, Pedestal..."})

(def routes
  (route/expand-routes
    #{["/greet"  :get respond-hello :route-name :greet]}))

(defn server []
  (http/create-server
    { ::http/routes routes
      ::http/type :jetty
      ::http/port 3002}))

(defn start []
  (http/start (server)))

(defn stop []
  (http/stop (server)))

(start)

(stop)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World! ") )
