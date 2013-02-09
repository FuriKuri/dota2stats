(defproject dota2stats "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
  				 [compojure "1.1.5"]
  				 [hiccup "1.0.2"]
  				 ]
  :plugins [[lein-swank "1.4.4"]
  			[lein-ring "0.7.1"]]
  :min-lein-version "2.0.0"
  :ring {:handler dota2stats.core/app})
