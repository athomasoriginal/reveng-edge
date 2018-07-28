(ns web-server.core
  "Example of a basic yada web server"
  (:require
    [clojure.edn :as edn]
    [clojure.java.io :as io]
    [clojure.tools.logging :as log]
    [com.walmartlabs.lacinia :as lacinia]
    [com.walmartlabs.lacinia.util :as util]
    [com.walmartlabs.lacinia.schema :as schema]
    [integrant.core :as ig]
    [yada.resources.classpath-resource :refer [new-classpath-resource]]
    [yada.yada :as yada]))



;; Graphql
;; -----------------------------------------------------------------------------

(defn get-persons
  [persons context args value]
  (let [{:keys [id]} args]
    (get persons id)))


(defn resolver-map
  []
  (let [data    (-> (io/resource "data.edn")
                    slurp
                    edn/read-string)
        persons (->> data
                     :persons
                     (reduce #(assoc %1 (:id %2) %2) {}))]
    {:query/person (partial get-persons persons)}))


(defn load-schema
  []
  (-> (io/resource "schema.edn")
      slurp
      edn/read-string
      (util/attach-resolvers (resolver-map))
      schema/compile))


(defn q
  "A utility function for testing graphql from the repl"
  [query-string]
  (lacinia/execute (load-schema) query-string nil nil))


;; Routes
;; -----------------------------------------------------------------------------

(defn content-routes
  "Serve the graphiql app - should only be used in development."
  []
  ["/"
   [
    ["public/" (assoc (new-classpath-resource "public") :id :web-server.resources/static)]]])


(defn graphql-route
  "Handle our graphql route."
  []
  ["/"
   [
    ["graphql"
     (yada/resource
       {:methods
        {:post
         {:consumes "application/json"
          :produces "application/json"
          :response (fn [ctx]
                      (lacinia/execute (load-schema) (-> ctx :body :query) nil nil))}}})]]])


(defn routes
  "All the routes."
  []
  [""
   [
    (graphql-route)

    (content-routes)]])



;; Web Server Integrant Lifecycle Methods
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
