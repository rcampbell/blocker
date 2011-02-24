(defproject blocker "1.0.0-SNAPSHOT"
  :description "RESTful webapp skeleton w/Compojure, Ring, Enlive, and ClojureQL"
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [compojure "0.6.0"]
                 [enlive "1.0.0-SNAPSHOT"]
                 [clojureql "1.0.1"]
                 [com.h2database/h2 "1.2.147"]]
  :dev-dependencies [[swank-clojure "1.2.1"]
                     [lein-ring "0.3.2"]]
  :ring {:handler jazeek.core/app})
