# blocker

Blocker is a lightweight project skeleton that implements [CRUD](http://en.wikipedia.org/wiki/Create,_read,_update_and_delete)
operations in a
[RESTful](http://en.wikipedia.org/wiki/Representational_State_Transfer)
manner using standard Clojure libraries.

It should give new Clojure developers a reasonable starting point from
which to build their web applications.

The following components have been pre-wired:

* [H2 database](http://www.h2database.com/) has been embedded to provide a JDBC-compliant durable store.

* [ClojureQL](http://clojureql.org/) is used to ...

* [Enlive](https://github.com/cgrand/enlive/wiki) is used to merge pure HTML views with data models.

* [Compojure](https://github.com/weavejester/compojure/wiki) is used to define routes, mapping our RESTful URLs to their respective handlers.

* [Ring](https://github.com/mmcgrana/ring/wiki) provides our [Jetty](http://jetty.codehaus.org/jetty/) adapter and HTTP framework.

## Usage



## License

Copyright (C) 2010 Robert Campbell

Distributed under the Eclipse Public License, the same as Clojure.
