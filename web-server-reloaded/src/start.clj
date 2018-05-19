(ns start
  "Start the web server app w/ reloaded workflow"
  (:require
    [clojure.tools.logging :as log]
    [system :as system]
    [integrant.core :as ig]))

(defn -main
  [& args]
  (log/info "Starting on port " (get-in (system/config :dev) [:web-server/core :port]))
  (ig/load-namespaces (:ig/system (system/config :dev)))
  (ig/init (:ig/system (system/config :dev))))
