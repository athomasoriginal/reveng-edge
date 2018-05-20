# Edge "reverse engineered"

## Introduction

For newcomers to Clojure, one of the big questions is "How do I build a web app?". In Clojure, there are no frameworks like Django, Rails or Express. There are libraries which you piece together. Okay, so which libraries should I use and when? What are the best practices? How should I organize my code? To put it another way: What does a Clojure web app look like?

In answer to this, [Juxt](https://juxt.pro/index.html) created [Edge](https://github.com/juxt/edge). Edge is an example web app meant to show new Clojure developers what a modern, production ready Clojure app looks like. It illustrates:

* Web API
* SSR
* SPA
* Graphl
* Event System
* Modern Workflow

This is a great learning tool. But this learning tool also has a lot going on. For example, what if you only want to create a SPA? You need a server and some HTML. Do you really need everything else? Okay, so what, at a minimum do I need?

**What this project is**

This project aims to breakdown and discuss the different parts of the [Edge](https://github.com/juxt/edge) web app so you can better see exactly what you need, but also gain a greater appreciation for how Edge is working to piece together the Clojure ecosystem.

**What this project is not**

This project will not teach you how to use the libraries. I will provide context, explain them a little and provide some alternatives. However, teaching each of them is not the goal.

## Learning Path

I would recommend reading through this project in the following order:

* [Web Server]()
* [Web Server Reloaded]()
