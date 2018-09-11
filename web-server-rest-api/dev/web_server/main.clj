(ns web-server.main
  (:require
    rebel-readline.clojure.main
    rebel-readline.core
    io.aviso.ansi))

(defn -main
  [& args]
  (rebel-readline.core/ensure-terminal
    (rebel-readline.clojure.main/repl
      :init (fn []
              (try
                (println "[web-server] Loading Clojure code, please wait...")
                (require 'web-server.dev)
                (in-ns 'web-server.dev)
                (println (io.aviso.ansi/bold-yellow "[web-server] Now enter (go) to start the dev system"))
                (catch Exception e
                  (.printStackTrace e)
                  (println "[web-server] Failed to require dev, this usually means there was a syntax error. See exception above.")
                  (println "[web-server] Please correct it, and enter (fixed!) to resume development.")))))))
