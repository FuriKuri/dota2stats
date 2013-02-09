(ns dota2stats.views
  (:use [hiccup core page]))

(defn header []
  [:head
   [:title "Hello World"]
   (include-css "/css/bootstrap.css")
   [:style "body {padding-top: 60px;}"]])

(defn navi-bar [active]
  [:div {:class "navbar navbar-inverse navbar-fixed-top"}
   [:div {:class "navbar-inner"}
    [:div {:class "container"}
     [:button {:type "button" :class "btn btn-navbar" :data-toggle "collapse" :data-target ".nav-collapse"}
      [:span {:class "icon-bar"}]
      [:span {:class "icon-bar"}]
      [:span {:class "icon-bar"}]]
     [:a {:href "#" :class "brand"} "Dota 2 Stats"]
        [:div {:class "nav-collapse collapse"}
         [:ul {:class "nav"}
          [:li (if (= active "Home") {:class "active"}) [:a {:href "/"} "Home"]]
          [:li (if (= active "Matches") {:class "active"}) [:a {:href "matches"} "Matches"]]
          [:li (if (= active "Heros") {:class "active"}) [:a {:href "heros"} "Heros"]]]]]]])

(defn index-page []
  (html5
   (header)
   [:body
    (navi-bar "Home")
    [:div {:class "container"}
     [:h1 "Dota 2 Stats"]
     [:P "Content"]]
    (include-js "/js/bootstrap.js")]))

(defn hero-page [heros]
  (html5
   (header)
   [:body
    (navi-bar "Heros")
    [:div {:class "container"}
     [:h1 "Heros"]
     
     [:P
      [:table {:class "table"}
       [:tr
        [:th "ID"]
        [:th "Name"]
        [:th "Image"]]
       (for [hero heros]
         [:tr
          [:td (hero "id")]
          [:td (hero "localized_name")]])]]]
    (include-js "/js/bootstrap.js")]))

(defn match-page []
  (html5
   (header)
   [:body
    (navi-bar "Matches")
    [:div {:class "container"}
     [:h1 "Matches"]
     [:P "Content"]]
    (include-js "/js/bootstrap.js")]))