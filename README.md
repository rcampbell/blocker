# Blocker

Blocker is a lightweight project skeleton that implements [CRUD](http://en.wikipedia.org/wiki/Create,_read,_update_and_delete)
operations in a
[RESTful](http://en.wikipedia.org/wiki/Representational_State_Transfer)
manner using standard Clojure libraries. It should give new Clojure developers a reasonable starting point from
which to build their web applications.

The following components have been pre-wired:

* [H2 database](http://www.h2database.com/) has been embedded to provide JDBC-compliant durable storage.

* [ClojureQL](http://clojureql.org/) is used to map Clojure
  expressions to relational database queries.

* [Enlive](https://github.com/cgrand/enlive/wiki) is used to merge pure XHTML views with data models.

* [Compojure](https://github.com/weavejester/compojure/wiki) is used to define routes, mapping our RESTful URLs to their respective handlers.

* [Ring](https://github.com/mmcgrana/ring/wiki) provides our
  [Jetty](http://jetty.codehaus.org/jetty/) adapter and core HTTP abstraction.

## Usage

This example assumes you have [Git](http://git-scm.com/),
[Leiningen](https://github.com/technomancy/leiningen/blob/master/README.md),
and [Emacs installed and configured](#).

In your shell:

    rrc@X ~/projects
    $ git clone git://github.com/rcampbell/blocker.git
    Initialized empty Git repository in ~/projects/blocker/.git/
    remote: Counting objects: 17, done.
    remote: Compressing objects: 100% (12/12), done.
    remote: Total 17 (delta 1), reused 0 (delta 0)
    Receiving objects: 100% (17/17), done.
    Resolving deltas: 100% (1/1), done.

    rrc@X ~/projects
    $ cd blocker

    rrc@X /projects/blocker (master)
    $ lein deps
    Copying 13 files to ~/projects/blocker/lib
    Copying 18 files to ~/projects/blocker/lib/dev
    
    rrc@X /projects/blocker (master)
    $ lein swank
    user=> Connection opened on local port  4005
    #<ServerSocket ServerSocket[addr=localhost/127.0.0.1,port=0,localport=4005]>
    
In Emacs, connect to our swank server:

    M-x slime-connect
    
Once you are connected you can initialize the database by evaluating
the `init.clj` script in the REPL:

    user> (load "blocker/init")
    
You should now open up `core.clj` and compile it:

    C-c C-k
    
You can now open [http://localhost:3000/](http://localhost:3000/) in
your browser to see the running application. Any changes you make to
your Clojure source files require only a recompile to appear in the
running application. No reboots or redeploys are necessary. 

You can also run the server from the shell using the Ring plugin for
Lein:

    lein ring server
    
With this method, you only have to save your Clojure source files for
changes to appear in the running application.

## License

Copyright (C) 2010 Robert Campbell

Distributed under the Eclipse Public License, the same as Clojure.
