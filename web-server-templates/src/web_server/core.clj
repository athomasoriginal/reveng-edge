(ns web-server.core
  "Example of a basic yada web server"
  (:require
    [clojure.tools.logging :as log]
    [integrant.core :as ig]
    [yada.yada :as yada]
    [selmer.parser :as selmer]))


;; Web Server Routes
;; -----------------------------------------------------------------------------


(defn route []
  ["/hello" (yada/handler "Hello World!\n")])


(defn html-route []
  ["/phonebook-app"
    (yada/resource
      {:id :web-server/phonebook-app
       :methods
       {:get
        {:produces "text/html"
         :response
         (fn [ctx]
           (selmer/render-file
             "phonebook-app.html"
             {:title "Edge phonebook app"
              :custom-text "Your text here :)"}))}}})])



;; Web Server Integrant Lifecycle Methods
;; -----------------------------------------------------------------------------


(defmethod ig/init-key :web-server/core
  [_ {:web-server.core/keys [host port] :as config}]
  (let [listener (yada/listener (html-route) {:port port})]
    (log/infof "Started http server on port %s" (:port listener))
    {:listener listener
     ;; host is used for announcement in dev
     :host host}))


(defmethod ig/halt-key! :web-server/core
  [_ {:keys [listener]}]
  (when-let [close (:close listener)]
    (close)))
