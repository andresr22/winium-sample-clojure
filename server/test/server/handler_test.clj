(ns server.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [server.handler :as handler]))

; (deftest test-app
;   (testing "main route"
;     (let [response (app (mock/request :get "/"))]
;       (is (= (:status response) 200))
;       (is (= (:body response) "Hello World"))))
;
;   (testing "not-found route"
;     (let [response (app (mock/request :get "/invalid"))]
;       (is (= (:status response) 404)))))

(deftest main-test
  (let [data_user {:name "andres" :id 12}]
        (is (= (handler/json data_user)
                {:status 200
                 :headers {"Content-Type" "application/json"}
                 :body [{:name data_user} {:name data_user}]}))))
