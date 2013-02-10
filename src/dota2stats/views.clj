(ns dota2stats.views
  (:use [hiccup core page]
        [clj-time.coerce]))

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

(defn match-list [matches]
  [:table {:class "table table-hover"}
   [:tr
    [:th "Match Time"]
    [:th "Match ID"]]
   (for [match matches]
     [:tr
      [:td (java.util.Date. (* (Long. (match "start_time")) 1000))]
      [:td [:a {:href (str "/match?matchid=" (match "match_id"))} (match "match_id")]]])])

(defn show-matches [matches]
  (let [status (matches "status")]
    (if (= status 15)
      [:p (matches "statusDetail")]
      (match-list (matches "matches")))))

(defn matchs-page [matches-result]
  (html5
   (header)
   [:body
    (navi-bar "Matches")
    [:div {:class "container"}
     (fork-me-banner)
     [:h1 "Matches"]
     [:P
      [:form
       [:fieldset
        [:legend "Find your last 25 matches"]
        [:label "Steam Id"]
        [:input {:type "text" :placeholder "Your ingame name" :name "steamid"}]
        [:br]
        [:button {:type "submit" :class "btn"} "Search"]]]]
     (if (not (nil? matches-result)) (show-matches matches-result))]
    (include-js "/js/bootstrap.js")]))

(def modes
  {1 "All Pick"
   2 "Captains Mode"
   3 "Random Draft"
   4 "Single Draft"
   5 "All Random"
   6 " "
   7 "Diretide"
   8 "Reverse Captains Mode"
   9 "Greeviling"
   10 "Tutorial"
   11 "Mid Only"
   12 "Least Played"
   13 "New Player Pool"})

(defn match-details [match]
  [:div
   [:p
    "Likes " [:span {:class "badge badge-success"} (match "positive_votes")]
    "  Dislikes " [:span {:class "badge badge-important"} (match "negative_votes")]]
   [:dl {:class "dl-horizontal"}
    [:dt "Game Mode"]
    [:dd (modes (match "game_mode"))]
    [:dt "Winner"]
    [:dd (if (= (match "radiant_win") true) "Radiant" "Dire")]
    [:dt "Duration"]
    [:dd (str (quot (Integer. (match "duration")) 60) " min")]]]
  )

(defn match-page [match]
  (html5
   (header)
   [:body
    (navi-bar "Matches")
    [:div {:class "container"}
     (fork-me-banner)
     [:h1 (str "Match ID:" (match "match_id"))]
     [:P (match-details match)]]
    (include-js "/js/bootstrap.js")]))
