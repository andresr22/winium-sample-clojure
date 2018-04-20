(ns server.handler
  (:require [server.winium :as winium]
            [compojure.core :refer [GET POST OPTIONS defroutes]]
            [compojure.route :as route]
            [ring.util.response :refer [resource-response response]]
            [ring.middleware.json :as middleware]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            ;[ring.middleware.cors :refer [wrap-cors]]))
            [ring.middleware.cors :refer :all]))

(defn view []
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (slurp "resources/public/index.html")})

(defn request-winium [req]
  (let [body (:body req)
        data_user (read-string (slurp body))]
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body (response (winium/winium data_user))}))

(defroutes app-routes
  (POST  "/winium/" req (request-winium req))
  (OPTIONS "/" [] (view))
  (GET  "/" [] (view))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-cors #".")
      (wrap-cors identity)
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)
      (wrap-defaults api-defaults)))
