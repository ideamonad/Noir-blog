(ns noir-blog.views.common
  (use noir.core
       hiccup.core
       hiccup.page-helpers))

(def blog-name "Obscureshapes")

;; Links and includes
(def main-links [{:url "/blog/admin" :text "Admin"}])

(def admin-links [{:url "/blog/" :text "Blog"}
                  {:url "/blog/admin" :text "Posts"}
                  {:url "/blog/admin/users" :text "Users"}
                  {:url "/blog/logout" :text "Logout"}])

(def includes {:jquery (include-js "http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js")
               :default (include-css "/css/default.css")
               :reset (include-css "/css/reset.css")
               :blog.js (include-js "/js/blog.js")})

(def contact-links [{:url "https://github.com/pgarland" :text "Github"}
                    {:url "https://plus.google.com/100929493239667214451" :text "Google+"}
                    {:url "https://secure.flickr.com/photos/pgarland" :text "Flickr"}
                    {:url "http://www.twitter.com/garlandp" :text "Twitter"}])

;; Helper partials

(defpartial build-head [incls]
            [:head
             [:title blog-name]
             (map #(get includes %) incls)])

(defpartial link-item [{:keys [url cls text]}]
            [:li
             (link-to {:class cls} url text)])

;; Layouts

(defpartial main-layout [& content]
            (html5
              (build-head [:reset :default :jquery :blog.js])
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
                
