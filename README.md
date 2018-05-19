* Make vs. bin/repl

  `:dev:build`

  v.

  `serve`

- For the above, they go through different routes to call the clojure file that is going to kick off the system. I actually like the feel of the `bridge` more. Its more to the point, less reliant on the `deps.edn` specifying what should be done. More straightforward path. In the end, bridge is saying, hey, if I have a clj tool, I will feed it the clojure file I want it to run directly. Kind of like a start script.

- Edge requires in `dev` to the `rebel.main` which is the script that actually provides the functions that are going to run the system. Not a bad separation.

- Edge provides out of the box for the reloaded workflow. Bridge does not even do this.

- If your thinking its not that annoying to start and stop the server all the time, think again. Its very annoying, so the server thing we have going is actually very useful.

- the logback file is where we control the logging configurations - turn them off of debug
