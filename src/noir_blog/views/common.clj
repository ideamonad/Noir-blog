(ns noir-blog.views.common
  (use noir.core
       hiccup.core
       hiccup.element
       hiccup.page))

(def blog-name "Obscureshapes")

;; Links and includes
(def main-links [])

(def admin-links [{:url "/blog/" :text "Blog"}
                  {:url "/blog/admin" :text "Posts"}
                  {:url "/blog/admin/users" :text "Users"}
                  {:url "/blog/logout" :text "Logout"}])

(def includes {:jquery (include-js "http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js")
               :default (include-css "/css/default.css")
               :reset (include-css "/css/reset.css")
               :custom (include-css "/css/custom.css")
               :blog.js (include-js "/js/blog.js")
               :twitter.js (include-js "/js/twitter.js")
               :gsearch.js (include-js "/js/gsearch.js")
               :mathjax (include-js "http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML")
               })

(def contact-links [{:url "https://github.com/pgarland" :content "Github"}
                    {:url "https://plus.google.com/100929493239667214451" :content "Google+"}
                    {:url "https://secure.flickr.com/photos/pgarland" :content "Flickr"}
                    {:url "http://www.twitter.com/garlandp" :content "Twitter"}
                    {:url "http://blog.obscureshapes.com/blog/feed.atom" :cls "image" :content (image "/img/feed-icon-28x28.png")}])

;; Helper partials

(defpartial build-head [incls]
            [:head
             [:title blog-name]
             (map #(get includes %) incls)])

(defpartial link-item [{:keys [url cls content]}]
            [:li
             (link-to {:class cls} url content)])

(defpartial contact-bar []
  (html5
   [:div#left
    (image "/img/blog-portrait-2011.png")
    "Phillip Garland"
    [:br]
    "pgarland@gmail.com"
    (map link-item contact-links)]))

(defpartial search-bar []
  (html5
   [:div#right
    [:gcse:search]]))

;; Layouts

(defpartial main-layout [& content]
            (html5
              (build-head [:reset :default :jquery :custom :blog.js :twitter.js :gsearch.js :mathjax])
              [:body
               [:div#wrapper
                [:div.content
                 [:div#header
                  [:h1 (link-to "/blog/" blog-name)]
                  [:ul.nav
                   (map link-item main-links)]]
                 content]]]))

(defpartial admin-layout [& content]
            (html5
              (build-head [:reset :default :jquery :blog.js])
              [:body
               [:div#wrapper
                [:div.content
                 [:div#header
                  [:h1 (link-to "/blog/admin" "Admin")]
                  [:ul.nav
                   (map link-item admin-links)]]
                 content]]]))
                
