(ns dota2stats.matchview
  (:use dota2stats.towerview
        dota2stats.views
        [hiccup core page]))


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
        [:input {:type "text" :placeholder "Your Steam ID" :name "steamid"}]
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
    [:dd (str (quot (Integer. (match "duration")) 60) " min")]
    [:dt "First Blood Time"]
    [:dd (str (quot (Integer. (match "first_blood_time")) 60) " min")]]
   (tower-status-container match)
   ]
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