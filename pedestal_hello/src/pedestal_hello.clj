(ns pedestal_hello
  (:require   [io.pedestal.http :as http]
              [io.pedestal.http.route :as route]))

(defn response [request]
      {:status 200 :body "Hello, Pedestal...."})

(def routes
  (route/expand-routes
    #{["/ola"   :get   response   :route-name   :ola]}))

(defn create-server []
  (http/create-server
    { ::http/routes routes
      ::http/type :jetty
      ::http/port 8899}))

(defn  start []
  (http/start  (create-server)))
