# Edge "reverse engineered"

## Introduction

For newcomers to Clojure, one of the big questions is "How do I build a web app?". In Clojure, there are no frameworks like Django, Rails or Express. There are libraries which you piece together. Okay, so which libraries should I use and when? What are the best practices? How should I organize my code?

In answer to this, [Juxt](https://juxt.pro/index.html) created [Edge](https://github.com/juxt/edge). Edge is an example web app meant to show new Clojure developers what a modern, production ready Clojure app looks like. It illustrates:

- Web Server
- Web Server + Reloaded Workflow
- Web Server + REST API
- Web Server + Graphql API
- Web Server + DB
- Web Server + authentication
- Web App - SSR
- Web App - SPA
- Event Systems
- Modern Clojure Development Workflow

This is a great learning tool. But this learning tool also has a lot going on. For example, what if you only want to create a SPA? You need a server and some HTML. Do you really need everything else? Okay, so what, at a minimum do I need?

**What this is**

This project aims to breakdown and discuss the different parts of the [Edge](https://github.com/juxt/edge) web app so you can better see exactly what you need, but also gain a greater appreciation for how Edge is working to piece together the Clojure ecosystem.

**What this is not**

This project will not teach you how to use the libraries. I will provide context, explain them a little and provide some alternatives. However, teaching each of them is not the goal. Further, I will not go into implementation details. For example, I will not breakdown the authentication system illustrated in Edge. In my mind, they have done an excellent job of that.

## Learning Path

I would recommend reading through this project in the following order:

- [Web Server](https://github.com/tkjone/reveng-edge/tree/master/web-server)
- [Web Server Reloaded](https://github.com/tkjone/reveng-edge/tree/master/web-server-reloaded)
- [Web Server Templates](https://github.com/tkjone/reveng-edge/tree/master/web-server-templates)
- [Web Server API](https://github.com/tkjone/reveng-edge/tree/master/web-server-api)
- [Web Server Graphql API](https://github.com/tkjone/reveng-edge/tree/master/web-server-graphql-api)
