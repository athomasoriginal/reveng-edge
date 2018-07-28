# Web Server API

This is the fourth instalment we are building upon the [Web Server Reloaded](https://github.com/tkjone/reveng-edge/tree/master/web-server-reloaded) project. This is an example of what it would look like to build web api for an application using the Edge system.

NOTES:

TODO: go through the express API's and try to see if we can follow one of the good ones here.

TODO: turn some stuff into variables - host and basepath

## Notes on Style

I have included my routes in the `core.clj` file. This is fine when we are working on small, demo apps, but it is a good idea to separate these out when the app scales. For now though, it will all be easier to reason about if its in the same file.

## Housekeeping

- Install Clojure

  See the [Clojure Install Guide](https://clojure.org/guides/getting_started)

## Quick Start

- Start the web server

  ```bash
  make serve
  ```

- Visit the terminal and start the reloaded workflow

  ```bash
  web-server.dev=> (go)
  ```

- Visit the app

  http://localhost:3001/phonebook-api

You should now see the swagger ui. The swagger ui should show you a route called `http://localhost:3001/phonebook-api/entry`. This route should have 4 methods associated to it: `GET`, `PUT`, `DELETE` and `POST`.

## Learnings

- [clojure.data](#clojure.data)
- [Swagger](#swagger)
- [Bidi](#bidi)

### clojure.data

If you're ever looking to convert clojure -> json checkout the `clojure.data.json` library. You can see it used in this app in `core.clj`

### Swagger

As you can see, we are using the `swaggered` function which is going to provide a built-in swagger-ui for our routes, however, there is an alternative to this where you can write your own custom setup for the swagger ui. This will be covered in future entries.

### Bidi

When working with bidi routes, be careful with the vectors if you are using the terse version. It is very easy to mess them up and kind of difficult to keep track of them when you are just starting out with it.
