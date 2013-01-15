(ns noir-blog.views.blog
  (:use noir.core
        hiccup.core
        hiccup.element
        hiccup.page)
  (:require [noir-blog.models.post :as posts]
            [noir-blog.views.common :as common]
            [noir-blog.models.user :as user]
            [noir.response :as resp]
            [clojure.string :as string]))

;; Page structure

(defpartial post-item [{:keys [perma-link title md-body date tme tags] :as post}]
            (when post
              [:li.post
               [:h2 (link-to perma-link title)]
               [:ul.datetime
                [:li date]
                [:li tme]
                (when (user/admin?)
                  [:li (link-to (posts/edit-url post) "edit")])]
               [:div.content md-body]
;;               [:div.tags "Tags: " (string/join "," tags)]
               [:div.social (link-to {:class "twitter-share-button"
                                      :data-count "none"
                                      :data-url (str "http://blog.obscureshapes.com" perma-link)
                                      :data-text title}
                                     "https://twitter.com/share")]]))

(defpartial blog-page [page items]
            (common/main-layout
             [:ul.nav
              (common/contact-bar)
              [:ul.posts
               (map post-item items)
               (when page
                 (when (> (Integer. page) 1)
                   [:linkbutton (link-to (str "/blog/page/" (dec (Integer. page))) "Newer Posts")]))
               (when page
                (when (= (count items) 10)
                  [:linkbutton (link-to (str "/blog/page/" (inc (Integer. page))) "Older Posts")]))]
              (common/search-bar)]))

;; Blog pages

(defpage "/" []
         (resp/redirect "/blog/"))

(defpage "/blog/" []
  (resp/redirect "/blog/page/1"))

(defpage "/blog/page/:page" {:keys [page]}
         (blog-page page (posts/get-page page)))

(defpage "/blog/page/" []
         (render "/blog/page/:page" {:page 1}))

(defpage "/blog/view/:perma" {:keys [perma]}
         (if-let [cur (posts/moniker->post perma)]
           (blog-page nil [cur])))
