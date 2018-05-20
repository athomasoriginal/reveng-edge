# Web Server

Start with the Hello World Web Server because it introduces a simple web server and the basics of implementing a "system". This project will show how to setup:

* Project Organization
* Configuration File - [Aero](https://github.com/juxt/aero)
* System - [Integrant](https://github.com/weavejester/integrant)
* Server - [Yada](https://github.com/juxt/yada)
* Logging - [tools.logging](https://github.com/clojure/tools.logging)

## Quick Start

* Install Clojure

  See the [Clojure Install Guide](https://clojure.org/guides/getting_started)

* Start the web server

  ```bash
  make serve
  ```

* Visit the app

  http://localhost:3001/hello

## Learnings

### Yada

[Documentation](https://juxt.pro/yada/manual/index.html#the-simple-way-construct-your-own)

### Integrant

If you were to compare the way Bridge and Edge configure Integrant, a big difference is that is seems like Bridge does not use `load-namespaces`. The reason is because if you one looks [here](https://github.com/robert-stuttaford/bridge/blob/master/src/bridge/service.clj#L23) we see that the ns's are being required in when this file runs. If you do not want to have to require files in this manner, Integrant provides `load-namespaces`.
