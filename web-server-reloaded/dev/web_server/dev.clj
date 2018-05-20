;; Copyright © 2016-2018, JUXT LTD.
(ns web-server.dev
  (:require
   [clojure.core.async :as a :refer [>! <! >!! <!! chan buffer dropping-buffer sliding-buffer close! timeout alts! alts!! go-loop]]
   [clojure.java.io :as io]
   [clojure.reflect :refer [reflect]]
   [clojure.repl :refer [apropos dir doc find-doc pst source]]
   [system :as web-server-system]
   [integrant.repl :refer [clear halt prep init reset reset-all]]
   [integrant.repl.state :refer [system]]
   web-server.reload
   [io.aviso.ansi]))


(when (System/getProperty "web_server.reset_on_hup")
  (web-server.reload/reset-on-hup))

(defn go []
  (let [res (integrant.repl/go)]
    (println (io.aviso.ansi/yellow
               (format "[Edge] Website can be browsed at http://%s/"
                       (-> system :web-server/core :host))))
    (println (io.aviso.ansi/bold-yellow "[Edge] Now make code changes, then enter (reset) here"))
    res))

(integrant.repl/set-prep! #(web-server-system/new-system :dev))

(defn cljs-repl
  "Start a ClojureScript REPL"
  []
  (eval
    `(do
       (require 'figwheel-sidecar.repl-api)
       (figwheel-sidecar.repl-api/cljs-repl))))
