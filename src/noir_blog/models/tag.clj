(ns noir-blog.models.tag
  (:require [simpledb.core :as db]
            [clojure.string :as string]
            [noir.validation :as vali]))

(defn init! []
  (db/put! :tags {}))

(defn tag-update-fn [existing-tags new-tags id]
  (let [new-tags-map (reduce merge (for [tag new-tags] {tag [id]}))]
    (merge-with into existing-tags new-tags-map)))

(defn tags->string [tags]
  (string/join ", " tags))