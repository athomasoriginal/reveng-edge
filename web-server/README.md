# Web Server

Start with the Hello World Web Server because it introduces a simple web server and the basics of implementing a "system". This project will show how to setup:

- Project Structure
- Project Configuration File - [Aero](https://github.com/juxt/aero)
- System - [Integrant](https://github.com/weavejester/integrant)
- Server - [Yada](https://github.com/juxt/yada)
- Logging - [tools.logging](https://github.com/clojure/tools.logging)

## Quick Start

- Install Clojure

  See the [Clojure Install Guide](https://clojure.org/guides/getting_started)

- Start the web server

  ```bash
  make serve
  ```

- Visit the app

  http://localhost:3001/hello

## Learnings

- [System](#system)
- [Server](#server)

### System

This functionality is provided by Integrant.

**Alternatives**

- Component
- Mount

**What is it?**

Some developers will not even add this to a project. Aside from its main benefits, if for nothing else, I like that it is a simple addition, and can bring clarity to your web app. For proper explanations, see the following:

- [Integrant Explained](https://skillsmatter.com/skillscasts/9820-enter-integrant-a-micro-framework-for-data-driven-architecture-with-james-reeves)
- [Lambda Island Explains Integrant](https://lambdaisland.com/episodes/integrant)

**Notes**

Implementing Integrant can be done in different ways. For example, if you were to compare the way [Bridge](https://github.com/robert-stuttaford/bridge) and Edge configure Integrant, a big difference is that is seems like Bridge does not use `load-namespaces`. The reason is because if you one looks [here](https://github.com/robert-stuttaford/bridge/blob/master/src/bridge/service.clj#L23) we see that the ns's are being required in when this file runs. If you do not want to have to require files in this manner, Integrant provides `load-namespaces`.

### Server

This functionality is provided by Yada.

**Alternatives**

- Ring
- Pedestal
- Liberator

**What is it**

[Documentation](https://juxt.pro/yada/manual/index.html#the-simple-way-construct-your-own)

**Notes**

Honestly, yada is an easy setup and while Ring is likely the one you hear people using a lot, but I would give this a try.
