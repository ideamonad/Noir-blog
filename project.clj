(defproject noir-blog "0.1.0"
            :description "A fully functional blog that serves an example of a noir project."
            :dependencies [[org.clojure/clojure "1.3.0"]
                           [clj-time "0.3.7"]
                           [noir "1.3.0-beta2"]
                           [org.markdownj/markdownj "0.3.0-1.0.2b4"]
                           [simpledb "0.1.4"]
                           [rome "1.0"]]
            :dev-dependencies [[clojure-source "1.3.0"]]
            :main noir-blog.server)

