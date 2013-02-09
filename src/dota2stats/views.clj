(ns dota2stats.views
  (:use [hiccup core page]))

(defn header []
  [:head
   [:title "Dota2 Stats"]
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

(defn fork-me-banner []
  [:a {:href "https://github.com/FuriKuri/dota2stats"}
   [:img {:style "position: absolute; top: 41px; right: 0; border: 0;"
          :src "https://s3.amazonaws.com/github/ribbons/forkme_right_darkblue_121621.png"
          :alt "Fork me on GitHub"}]])

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

(defn image-url [hero-name]
  (let [hero (clojure.string/replace hero-name #"npc_dota_hero_" "")]
    (str "http://media.steampowered.com/apps/dota2/images/heroes/" hero "_sb.png")))

(defn hero-page [heros]
  (html5
   (header)
   [:body
    (navi-bar "Heros")
    [:div {:class "container"}
     (fork-me-banner)
     [:h1 "Heros"]     
     [:P
      [:table {:class "table"}
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

(defn match-page []
  (html5
   (header)
   [:body
    (navi-bar "Matches")
    [:div {:class "container"}
     (fork-me-banner)
     [:h1 "Matches"]
     [:P "Content"]]
    (include-js "/js/bootstrap.js")]))