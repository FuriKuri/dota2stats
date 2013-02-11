(ns dota2stats.heroview
  (:use [dota2stats.views]
        [hiccup core page]))


(defn hero-page [heros]
  (html5
   (header)
   [:body
    (navi-bar "Heros")
    [:div {:class "container"}
     (fork-me-banner)
     [:h1 "Heros"]     
     [:P
      [:table {:class "table table-hover table-condensed"}
       [:tr
        [:th "ID"]
        [:th "Image"]
        [:th "Name"]]
       (for [hero heros]
         [:tr
          [:td (hero "id")]
          [:td [:img {:src (image-url (hero "name"))}]]
          [:td (hero "localized_name")]])]]]
    (include-js "/js/bootstrap.js")]))