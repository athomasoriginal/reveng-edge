# Web Server Templates

In this third instalment we are building upon the [Web Server Reloaded](https://github.com/tkjone/reveng-edge/tree/master/web-server-reloaded) project by showing how Edge put together a SSR web app. The big feature here is adding `Templating`, which is a big selling point of Web Frameworks.

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

- Visit the app

  http://localhost:3001/phonebook-app

## Learnings

- [Templating Engine](#templating-engine)

### Templating Engine

This functionality is provided by Selmer.

**Alternatives**

Honestly, most alternatives do not seem to be very well updated, so I would continue to rock out with Selmer

**What is it**

This library was inspired by Djangos templating engine. I would recommend reading their guide [Templating intro](https://docs.djangoproject.com/en/2.0/topics/templates/) section.

**Notes**

You do have to do some configuration for templates. At a high level this involves:

1.  Configuring Selmer to know where to find HTML file
2.  Add Selmer to our "System"

I did remove some of the very nice tags that Edge provided. If you need a reference for how to do these, please see [here](https://github.com/juxt/edge/blob/a3902a7771b51bb57c159cc9d5ec5619dde84b7f/app/src/edge/phonebook.clj) or read the selmer documentation.
