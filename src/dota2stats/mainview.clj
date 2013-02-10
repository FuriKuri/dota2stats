(ns dota2stats.mainview
  (:use [dota2stats.views]
        [hiccup core page]))

(defn index-page []
  (html5
   (header)
   [:body
    (navi-bar "Home")
    [:div {:class "container"}
     (fork-me-banner)
     [:h1 "Dota 2 Stats"]
     [:P "Content"]]
    (include-js "/js/bootstrap.js")]))