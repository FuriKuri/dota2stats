(ns dota2stats.playerview
  (:use dota2stats.views
        [hiccup core page]))

(defn player-table [team players]
  [:div
   [:strong team]	
   [:table {:class "table table-hover"}
    [:tr
     [:th "Hero"]
     [:th "Level"]]
   (for [player players]
     [:tr
      [:td (player "hero_id")]
      [:td (player "level")]])]])

(defn player-details [match]
  (let [players (match "players")
  		radiant-players (filter #(< 100 (% "player_slot")) players)
  		dire-players (filter #(> 100 (% "player_slot")) players)]
  	[:p
  	 (player-table "Radiant" radiant-players)
  	 (player-table "Dire" dire-players)]))