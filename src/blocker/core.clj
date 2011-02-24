(ns blocker.core
  (:use [clojure.string :only (join)]
        [net.cgrand.enlive-html :only (deftemplate do-> set-attr prepend content clone-for)]
        [compojure.core :only (GET POST PUT DELETE defroutes)]
        [ring.adapter.jetty :only (run-jetty)] )
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.response :as response]
            [blocker.db :as db]))

(letfn [(redirect [status]
                  (fn [uri] {:status status
                            :headers {"Location" uri}}))]
  (def ^{:doc "moved permanently" :private true}
    moved-to  (redirect 301)))

(deftemplate edit-view "index.html" [id text]
  [:form]     (do-> (set-attr :action (str "/blocks/" id))
                    (prepend {:tag :input :attrs {:type "hidden"
                                                  :name "_method"
                                                  :value "PUT"}}))
  [:textarea] (content text))

(deftemplate list-view "list.html" [blocks]
  {[:dt] [:dd]} (clone-for [{:keys [id text]} blocks]
                           [:a]  (do-> (set-attr :href (str "/blocks/" id))
                                       (content id))
                           [:dd] (content (db/clob->str text))))

(defn create-block! [text]
  (let [id (db/create-block! text)]
    (moved-to (str "/blocks/" id))))

(defn get-block [id]
  (->> @(db/get-block id) first :text db/clob->str (edit-view id)))

(defn update-block! [{id :id, :as block}]
  (db/update-block! block)
  (get-block id))

(defn delete-block! [id]
  (db/delete-block! id)
  (moved-to "/"))

(defroutes main-routes
  (GET    "/"           []                 (response/resource "index.html"))
  (GET    "/blocks/"    []                 (list-view @(db/list-blocks)))
  (POST   "/blocks/"    [& {:keys [text]}] (create-block! text))
  (GET    "/blocks/:id" [id]               (get-block id))
  (PUT    "/blocks/:id" [& params]         (update-block! params))
  (DELETE "/blocks/:id" [id]               (delete-block! id))
  (route/not-found "Page not found"))

(def app
  (handler/site main-routes))

(defonce *server* (future (run-jetty (var app) {:port 3000})))

