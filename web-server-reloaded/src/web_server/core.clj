(ns web-server.core
  "Example of a basic yada web server"
  (:require
    [clojure.tools.logging :as log]
    [integrant.core :as ig]
    [yada.yada :as yada]))


;; Web Server Routes
;; -----------------------------------------------------------------------------


(defn route []
  ["/hello" (yada/handler "Hello World -- I win!\n")])


;; Web Server Integrant Lifecycle Methods
;; -----------------------------------------------------------------------------


(defmethod ig/init-key :web-server/core
  [_ {:web-server.core/keys [host port] :as config}]
  (let [listener (yada/listener (route) {:port port})]
    (log/infof "Started http server on port %s" (:port listener))
    {:listener listener
     ;; host is used for announcement in dev
     :host host}))


(defmethod ig/halt-key! :web-server/core
  [_ {:keys [listener]}]
  (when-let [close (:close listener)]
    (close)))
