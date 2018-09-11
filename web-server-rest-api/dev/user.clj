(ns user
  (:require
   [clojure.tools.namespace.repl :refer :all]
   [io.aviso.ansi]
   [integrant.repl.state]
   [io.aviso.ansi]
   [spyscope.core]))

(when (System/getProperty "web_server.load_nrepl")
  (require 'web-server.nrepl))

(defn dev
  "Call this to launch the dev system"
  []
  (println "[web-server] Loading Clojure code, please wait...")
  (require 'web-server.dev)
  (when-not integrant.repl.state/system
    (println (io.aviso.ansi/bold-yellow "[web-server] Enter (go) to start the dev system")))
  (in-ns 'web-server.dev))

(defn fixed!
  "If, for some reason, the Clojure code in the project fails to
  compile - we still bring up a REPL to help debug the problem. Once
  the problem has been resolved you can call this function to continue
  development."
  []
  (refresh-all)
  (in-ns 'web-server.dev))
