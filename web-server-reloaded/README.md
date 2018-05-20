# Web Server Reloaded

Where web-server]() illustrated how to start a system based web-server, this is the next iteration. We focus on adding the [reloaded-workflow](http://thinkrelevance.com/blog/2013/06/04/clojure-workflow-reloaded). The majority of the changes to make this happen can be found in `dev` and the `deps.edn` file.

As with web-server, I have diverged a little from Edge full reloaded workflow to make it easier to understand how it fits in. Areas of divergence:

* graphql
* tests
* manifold

See the sections below for a deeper breakdown of how reloaded works.

## Quick Start

* Install Clojure

  See the [Clojure Install Guide](https://clojure.org/guides/getting_started)

* Start the web server

  ```bash
  make serve
  ```

* Visit the terminal and start the reloaded workflow

  ```bash
  web-server.dev=> (go)
  ```

* Visit the app

  http://localhost:3001/hello

* Make a change to `web-server.core/route`

  ```clojure
  ;; from this
  ["/hello" (yada/handler "Hello World\n")])

  ;; to something like this
  ["/hello" (yada/handler "Hello World -- Reloaded!\n")])
  ```

* Reload the app

  ```bash
  web-server.dev=> (reset)
  ```

* Re-visit the app

  http://localhost:3001/hello

  > You should now see `Hello World -- Reloaded!` when you visit the app

## Learnings

### Integrant

Keys can be tricky :(

### The REPL Reloaded Workflow

As I mentioned, I pretty much just extracted the Edge reloaded workflow and did not make too many changes. However, one of the more "significant" changes is in how I organized the project. Instead of the `aliases` dir from Edge, I put everything into `dev`. Hopefully I am not missing something important about the `aliases` dir and whether or not there was a rational for doing this other than organization. Either way, here is a small breakdown of the Reloaded Workflow:

* Edge starts its reloaded workflow by running the `repl` shell script

  ```bash
  $ .bin/repl
  ```

* This is going to kick off the following:

  ```bash
  deps.edn (1) --> user.clj (2) --> nrepl.clj (3) --> edge.rebel.main (4) --> dev.clj (5)
  ```

  1.  [deps.edn](https://github.com/juxt/edge/blob/master/app/deps.edn) specifically, they are running `:dev`, `build`, `:dev/rebel`, `:dev/nrepl` and `:dev/cljs`. Things to keep in mind is that running these expands whats on the classpath. This expansion is what allows `user.clj` and `:dev/nrepl` to be called and do their thing (read points 2 and 3) and than start the main repl script (read point 4)
  2.  [user.clj](https://github.com/juxt/edge/blob/master/app/dev/user.clj) is automatically imported when its on the classpath. So this guy just loads itself and in doing so requires `nrepl.clj`
  3.  [nrepl](https://github.com/juxt/edge/blob/master/app/aliases/nrepl/nrepl.clj) automatically starts a `nrepl-server`. This automatic chain of events stops now.
  4.  [edge.rebel.main](https://github.com/juxt/edge/blob/master/app/aliases/rebel/edge/rebel/main.clj) has a main function which, when called, requires the `dev.clj` ns and drops you into it. Which leads us to the next point.
  5.  [dev.clj](https://github.com/juxt/edge/blob/master/app/dev/dev.clj) This ns starts by setting up integrant by passing it your `config.edn`.

At this point, you are ready to call `go`.

The above seemed challenging to me at first because, like Clojure in general, you can miss a lot of things when your not 100% what the language features do. If your thinking this is the case with any language, I like to think that is not as true as one would think. In Clojure, and lisp in general, there are traditions (READ: conventions). This conventions speak to you when your are in their context. In a way, its self documenting. But you have to know the conventions.

I also want to point out that this is not the only way to set up a workflow like this. This one felt like it was "jumping around" a bit. But that is not so much the case as it feels that Edge has design goals they are trying to achieve and when viewed as a part of the whole of "Edge" and where this project fits into Juxt, it starts to make sense.

Final note, if you are wondering about the `dev` function in `dev.clj`. I believe this is there as a util type function to quickly get your repl experience to where you need it to be.

### user.clj

This file is loaded automatically when it is found at the root of the java classpath. However, this is not something we want loaded while in production, so a convention seems to be to put it into a directory called `dev` at the root of your project and then specify the directory `dev` to be an added path in an alias. You can read more about this [here](http://thinkrelevance.com/blog/2013/06/04/clojure-workflow-reloaded)
