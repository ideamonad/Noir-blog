(defproject noir-blog/noir-blog "0.1.0" 
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [clj-time "0.3.7"]
                 [noir "1.3.0"]
                 [org.markdownj/markdownj "0.3.0-1.0.2b4"]
                 [simpledb "0.1.4"]
                 [rome "1.0"]]
  :profiles {:user {}}
  :main noir-blog.server
  :min-lein-version "2.0.0"
  :description "A fully functional blog that serves an example of a noir project.")


