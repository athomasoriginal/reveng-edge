(ns web-server.selmer
  "Configure selmer for the project + provide helpers"
  (:require
   [clojure.java.io :as io]
   [hiccup.core :refer [html]]
   [integrant.core :as ig]
   [schema.core :as s]
   [selmer.filter-parser :refer [compile-filter-body]]
   [selmer.parser :as selmer]
   [yada.yada :as yada]))

(defmethod ig/init-key :web-server/selmer
  [_ {:web-server.selmer/keys [template-caching?]}]
  (selmer/set-resource-path! (io/resource "templates"))

  (if template-caching?
    (selmer.parser/cache-on!)
    (selmer.parser/cache-off!)))
