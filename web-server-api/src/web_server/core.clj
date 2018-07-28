(ns web-server.core
  "Example of a basic yada web server"
  (:require
    [clojure.data.json     :as json]
    [clojure.tools.logging :as log]
    [integrant.core        :as ig]
    [yada.swagger          :as swagger :refer [swaggered]]
    [yada.yada             :as yada]))


;; Web Server Routes
;; -----------------------------------------------------------------------------


(defn phonebook-entry-route
  "Resource describing what we should do when the user hits /phonebook/{id}"
  []
  (yada/resource
    {:id          :web-server.core/phonebook-entry
     :description "Phonebook entry"
     :parameters  {:path {:id Long}}
     :produces    [{:media-type #{"application/json;q=0.8"}
                    :charset "UTF-8"}]
     :methods
     {:get
      {:swagger/tags ["default" "getters"]
       :response
       (fn [ctx]
         (json/write-str {:success true :type :get}))}

      :put
      {:swagger/tags ["default" "getters"]
       :response
       (fn [ctx]
         (json/write-str {:success true :type :put}))}

      :delete
      {:swagger/tags ["default" "getters"]
       :response
       (fn [ctx]
         (json/write-str {:success true :type :delete}))}

      :post
      {:swagger/tags ["default" "getters"]
       :response
       (fn [ctx]
         (json/write-str {:success true :type :post}))}}}))


(defn swaggered-route
  [routes]
  (swaggered
    routes
    {:info     {:title       "Phonebook"
                :version     "1.0"
                :description "Phonebook routes"}
     :host     "localhost:3001"
     :schemes  ["http"]
     :basePath "/phonebook-api"})) ;; TODO MAKE ME A VARIABLE


(defn routes
  []
  (let [phonebook-entry-route* ["/entry" [[["/" :id] (phonebook-entry-route)]]]]
    ["/"
      [["phonebook-api" (swaggered-route phonebook-entry-route*)]]]))


;; Web Server - Integrant Lifecycle Methods
;; -----------------------------------------------------------------------------


(defmethod ig/init-key :web-server/core
  [_ {:web-server.core/keys [host port] :as config}]
  (let [listener (yada/listener (routes) {:port port})]
    (log/infof "Started http server on port %s" (:port listener))
    {:listener listener
     ;; host is used for announcement in dev
     :host host}))


(defmethod ig/halt-key! :web-server/core
  [_ {:keys [listener]}]
  (when-let [close (:close listener)]
    (close)))
