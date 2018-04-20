(defproject server "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [compojure "1.6.0"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-json "0.4.0"]
                 [ring/ring-defaults "0.3.1"]
                 ;[ring-cors/ring-cors "0.1.12"]
                 [jumblerg/ring.middleware.cors "1.0.1"]
                 ;SSH
                 [com.jcraft/jsch "0.1.54"]
                 [org.apache.commons/commons-vfs2 "2.2"]
                 [commons-logging/commons-logging "1.2"]
                 ;Winium
                 [org.seleniumhq.selenium/selenium-java "2.53.0"]
                 [com.github.2gis.winium/winium-elements-desktop "0.2.0-1"]
                 [com.github.2gis.winium/winium-webdriver "0.1.0-1"]
                 [org.clojure/data.codec "0.1.1"]]



  :main server.command
  :plugins [[lein-ring "0.9.7"]
            [lein-cloverage "1.0.10"]]
  :ring {:handler server.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
