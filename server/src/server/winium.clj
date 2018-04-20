(ns server.winium
  (:import [org.openqa.selenium OutputType TakesScreenshot]
           [org.openqa.selenium WebElement]
           [org.openqa.selenium.winium DesktopOptions WiniumDriver]
           [org.apache.commons.io FileUtils]
           [java.io File FileInputStream]
           [java.nio.file Paths Files]
           [java.net URL]
           [java.util Base64]))

(defn encoder [image-path]
  (let [image-data (Files/readAllBytes (Paths/get image-path))
        base64Image (.encodeToString (Base64/getEncoder) image-data)]
       base64Image))

(defn build-option [appPath]
  (let [option (new DesktopOptions)]
       (.setApplicationPath option appPath)
       (.setDebugConnectToRunningApp option false)
       (.setLaunchDelay option (int 2))
       option))

(defn build-url [ip]
  (new URL (str "http://" ip ":9999")))

(defn build-driver [url option]
  (new WiniumDriver url option))

; (defn build-window [driver]
;   (.findElementByName driver "Text Area"))

(defn get-data [key data_user]
  (let [key (keyword key)]
    (get data_user key)))

(defonce return (atom {}))

(defn winium [data_user]
  (let [ip (get-data "ip" data_user)
        command (get-data "comandos" data_user)
        screen (get-data "screen" data_user)
        option (build-option "C:/windows/system32/cmd.exe")
        url (build-url ip)
        driver (build-driver url option)
        ; window (build-window driver)
        window (.findElementByName driver "Text Area")]
       (.sendKeys window (into-array [(str command "\r")]))
       (let [texto (.getText window)]
          (swap! return assoc :texto texto))
       (if (true? (Boolean/valueOf screen))
         (let [scrFile (.getScreenshotAs driver OutputType/FILE)]
           ; (FileUtils/copyFile scrFile (new File "/home/screenshot.png"))
           (println (.getAbsolutePath scrFile))
           (swap! return assoc :image (str "data:image/png;base64," (encoder (.toURI scrFile)))))
         (swap! return assoc :image nil))
    (.close driver)) @return)
