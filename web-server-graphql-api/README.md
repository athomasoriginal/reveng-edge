# Web Server Reloaded

In this project, we see how Edge puts together an alternative to [web-server-api](https://github.com/tkjone/reveng-edge/tree/master/web-server-api), which is RESTFUL, and see how we can setup a graphql API.

## Housekeeping

Please install [graphiql-app](https://github.com/skevy/graphiql-app) before going through the quickstart.

## Quick Start

- Install Clojure

  See the [Clojure Install Guide](https://clojure.org/guides/getting_started)

- Start the web server

  ```bash
  make serve
  ```

- Visit the terminal and start the reloaded workflow

  ```bash
  web-server.dev=> (go)
  ```

- Open graphiql app

- Add our graphql endpoint to `graphiql-app`

  ```bash
  http://localhost:3001/graphql
  ```

  > The above goes in the input field titled: `GraphQL Endpoint`

- Sanity check - try querying

  ```bash
  {
    person(id: 1) {
      firstname
    }
  }
  ```

  > You should see a response of { "data": { "person": { "firstname": "Tom" } } }

## Learnings

### Graphql Setup Notes

There are three, basic parts to setting up graphql:

- 1.  Create the Schema

- 2.  Create a resolver

- 3.  Create data (see `data.edn` file)

once we have completed this, we can start testing, even without our browser/app graphql IDE:

- Start the app

  ```
  make serve
  ```

- Run the app

  ```clojure
  (go)
  ```

- Import the web-server.core namespace

  ```clojure
  (require 'web-server.core)
  ```

  > This gives us access to all the functions we require. If you compare this to the lacinia tutorial, they have done this step in `dev-resources/user.clj`. Now, we could also do this, but I thought it would be good to do this manually and see how it could be done.

- Query your graphql schema

  ```clojure
  (web-server.core/q "{ person(id: \"1\") { firstname surname phone }}")
  ```

### Graphiql - Browser v. App

Once we have our graphql endpoint setup, we can rock out with our IDE to play with it. We have three options: repl, browser ide and app ide. The repl version is seen above, the other two will be explored below.

For the browser version, checkout `resources/public/graphiql`. To work with this one:

- Move into `resources/public/graphiql`

  ```bash
  cd ./resources/public/graphiql
  ```

- Install node dependencies

  ```bash
  yarn
  ```

- run reset after

  ```bash
  (reset)
  ```

  > You only need to `reset` if your app is already running.

You will also need to serve the index.html file if you want to hit it from the app at `localhost:3001/public/graphiql/index.html`. To see how this is configured, see `content-routes` in `web-server/core`.
