(defproject noir-blog "0.1.0"
            :description "A fully functional blog that serves an example of a noir project."
            :dependencies [[org.clojure/clojure "1.3.0"]
                           [clj-time "0.3.0"]
                           [noir "1.2.1"]
                           [org.markdownj/markdownj "0.3.0-1.0.2b4"]
                           [simpledb "0.1.4"]]
            :dev-dependencies [[swank-clojure "1.4.0-SNAPSHOT"]
                               [clojure-source "1.3.0"]]
            :main noir-blog.server)

