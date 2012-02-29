(ns noir-blog.views.feed
  (:use noir.core)
  (:require [noir-blog.models.post :as posts]
            [noir-blog.views.common :as common])
  (:import (com.sun.syndication.feed.synd SyndFeedImpl SyndEntryImpl SyndContentImpl)
           (com.sun.syndication.io SyndFeedOutput)
           (java.text SimpleDateFormat)))

(declare make-entry make-entries)

(defn make-feed []
  (let [feed (SyndFeedImpl.)]
    (doto feed
      (.setFeedType "atom_1.0")
      (.setTitle common/blog-name)
      (.setLink "http://localhost:8080/feed.atom")
      (.setDescription "A blog about biology, cats, computers, etc")
      (.setEntries (make-entries)))))

(defn make-entry [{title :title perma-link :perma-link date :date body :body}]
  (let [entry (SyndEntryImpl.)
        content (SyndContentImpl.)
        sdf (java.text.SimpleDateFormat. "MM/dd/yy")]
    (doto content
      (.setType "text/html")
      (.setValue body))
    (doto entry
      (.setTitle title)
      (.setLink (str "http://blog.obscureshapes.com" perma-link))
      (.setPublishedDate (. sdf parse date))
      (.setDescription content))))

(defn make-entries []
  (for [post (posts/get-all)]
    (make-entry post)))

(defpage "/blog/feed.atom" []
  (let [out (SyndFeedOutput.)]
    (. out outputString (make-feed))))