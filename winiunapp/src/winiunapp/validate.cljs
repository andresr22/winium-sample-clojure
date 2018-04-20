(ns winiunapp.validate
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs-http.client :as http]
            [clojure.string :as str]
            [cljs.core.async :refer [<!]]
            ))

(defonce aresponse (atom {:response ""}))

; (defn console-out-print [args]
;   (.apply (.-log js/console) js/console (into-array args)))

(defn write-atom [image texto]
  (swap! aresponse assoc :image image)
  (swap! aresponse assoc :response (subs texto 0 (str/index-of texto "\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n"))))

(defn http-client [data_user]
  ; (println "Esto es el offset: " (-> js/document (.getElementsByClassName "mdl-textfield__error") (.-value)))
  ; (go (let [response (<! (http/post "http://192.168.1.108:3000/winium/" {:edn-params data_user}))]
  (go (let [response (<! (http/post "http://localhost:3000/winium/" {:body data_user :headers {"Content-Type" "text/plain"}}))]
        (prn (:status response)
         (prn (:body response)))
        (let [image (get-in response [:body :body :image])
              texto (get-in response [:body :body :texto])]
              (write-atom image texto))
        ; (swap! aresponse assoc :image (get-in response [:body :body :image]))
        ; (swap! aresponse assoc :response (get-in response [:body :body :texto]))
        ; (println "index-of: " (subs (get-in response [:body :body :texto]) 0 (str/index-of (get-in response [:body :body :texto]) "32>\r")))
        )))


(defn validate-form [app-state]
  (let [app-state @app-state]
        (if (and (> (count (get app-state :ip)) 0)
                 (> (count (get app-state :comandos)) 0))
            (http-client app-state)
            (do (js/alert "Faltan datos")))
        ; (console-out-print @aresponse)
        ))

; (defn mouseClicked [app-state]
;   (let [validate (validate-form app-state)]
;         (println validate)
;         (if (true? validate)
;             (http-client app-state) (.log js/console validate))))
