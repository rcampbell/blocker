(ns blocker.db
  (:refer-clojure :exclude [compile distinct drop take sort conj! disj!])
  (:use clojureql.core))

(def db
  {:classname   "org.h2.Driver"
   :subprotocol "h2"
   :user        "sa"
   :password    ""
   :subname     "~/blocker"})

(open-global db)

(let [blocks (table :blocks)]
  (letfn [(create-id!
           []
           (let [alphanumeric (map char (concat (range 48 58)    ; 0-9
                                                (range 65 91)    ; A-Z
                                                (range 97 123))) ; a-z
                 confusing #{\0 \O \o \1 \L \l \5 \S \s}
                 pool (remove confusing alphanumeric)]
             (->> pool shuffle (take 6) (apply str))))]
    (defn create-block! [text]
      (let [id (create-id!)]
        (conj! blocks {:id id :text text})
        id)))
  (defn get-block [id]
    (project (select blocks (where (= :id id))) [:text]))
  (defn update-block! [{:keys [id text]}]
    (update-in! blocks (where (= :id id)) {:text text}))
  (defn delete-block! [id]
    (disj! blocks (where (= :id id))))
  (defn list-blocks []
    (select blocks true)))
 
(defn clob->str [clob]
  (.getSubString clob 1 (.length clob)))
