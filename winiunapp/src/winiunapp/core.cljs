(ns winiunapp.core
    (:require [reagent.core :as reagent :refer [atom]]
              [re-mdl.core :as mdl]
              [winiunapp.validate :as val]))

(enable-console-print!)

(println "This text is printed from src/winiunapp/core.cljs.")

; (defonce app-state (atom {:text "Hello world!"}))
(defonce datos (atom {:app "" :ip "" :comandos "" :screen false}))

;; This is the comboBox for the apps
; (defn comboBox-app []
;   [:div.mdl-textfield.mdl-js-textfield.mdl-textfield--floating-label.getmdl-select
;     [:input#sample4.mdl-textfield__input
;      {:readOnly "readonly", :value "", :type "text"}]
;     [:input {:name "sample4", :value "", :type "hidden"}]
;     [:i.mdl-icon-toggle__label.material-icons "keyboard_arrow_down"]
;     [:label.mdl-textfield__label {:for "sample4"} "App"]
;     [:ul.mdl-menu.mdl-menu--bottom-left.mdl-js-menu
;      {:for "sample4"}
;      [:li.mdl-menu__item {:data-val "CMD"} "cmd"]]])

;; This is the textfield for the server ip
(defn textfield-ip []
  [mdl/textfield
   :id              "tfIP"
   :floating-label? true
   :label           "Server IP"
   :model           (get-in @datos [:ip])
   :pattern         "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$"
   :invalid-message "Input is not a valid IP"
   ; :handler-fn       (fn [nuevo](swap! datos assoc :ip nuevo))
   :handler-fn      #(swap! datos assoc :ip %)])

;; This is the textfield for the command to send
(defn textfield-command []
  [mdl/textfield
   :id              "tfCommand"
   :floating-label? true
   :label           "Command"
   :model           (get-in @datos [:comandos])
   :handler-fn      #(swap! datos assoc :comandos %)])

;; This is the Checkbox for the screenshoot
(defn checkbox-screen []
  [mdl/toggle-checkbox
   :id            "cbScreen"
   :label         "Screenshoot"
   :model         (get-in @datos [:screen])
   :handler-fn    #(swap! datos assoc :screen %)])

;; This is the button start
(defn button-start []
  [:div
   {:class "button"}
   [mdl/button
    :id             "btnStart"
    :raised?        true
    :accent?        true
    :child          "Start"
    :ripple-effect? true
    ; :on-click       (fn [_]
    ;                    (val/validate-form datos))
    :on-click       #(val/validate-form datos)]])

;; This is the text area
(defn response-input []
  [:div
   [mdl/textfield
    :id         "input-text"
    :label      "Response..."
    :floating-label? true
    :type       :textarea
    :model      (get-in @val/aresponse [:response])
    :rows       14
    :disabled?  true
    ; :handler-fn #(get-in @val/aresponse [:response])
    ]])

;; This is a responsive grid
(defn grid []
  (let [style {:box-sizing       "border-box"
               :background-color "#DFDFDF"
               :height           "300px"
               :width            "320px"
               :padding-left     "8px"
               :padding-top      "4px"
               :color            "white"}
         style2 {:box-sizing       "border-box"
                 :background-color "#DFDFDF"
                 :height           "300px"
                 :width            "620px"
                 :padding-left     "8px"
                 :padding-top      "4px"
                 :color            "white"}]
    (mdl/grid
     :children [[mdl/cell
                 :attr     {:style style}
                 :col      4
                 :children [
                            ; (comboBox-app)
                            (textfield-ip)
                            (textfield-command)
                            (checkbox-screen)
                            (button-start)]]
                [mdl/cell
                 :attr     {:style style2}
                 :col      4
                 :children [(response-input)]]
                [:div [:img {:src (get-in @val/aresponse [:image])}]]
               ])))

(reagent/render-component [grid]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
