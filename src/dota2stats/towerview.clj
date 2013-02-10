(ns dota2stats.towerview
  (:use [dota2stats.views]
        [hiccup core page]))

(def towers
  {"Tier 1 Bot" 1
   "Tier 2 Bot" 2
   "Tier 3 Bot" 4
   "Tier 1 Mid" 8
   "Tier 2 Mid" 16
   "Tier 3 Mid" 32
   "Tier 1 Top" 64
   "Tier 2 Top" 128
   "Tier 3 Top" 256
   "Ancient Bot" 512
   "Ancient Top" 1024})

(def barracks
  {"Melee Bot" 1
   "Ranged Bot" 2
   "Melee Mid" 4
   "Ranged Mid" 8
   "Melee Top" 16
   "Ranged Top" 32})

(defn tower-status [tower status]
  (let [x-bits (towers tower)
        y-bits status]
    (if (= 0 (bit-and y-bits x-bits))
      [:span {:class "label label-important"} tower]
      [:span {:class "label label-success"} tower])))

(defn barrack-status [barrack status]
  (let [x-bits (barracks barrack)
        y-bits status]
    (if (= 0 (bit-and y-bits x-bits))
      [:span {:class "label label-important"} barrack]
      [:span {:class "label label-success"} barrack])))

(defn tower-status-container [match]
  [:div
   [:div {:class "row tower"}
    [:div {:class "span12"} [:strong "Tower"]]
    ]
   [:div {:class "row tower"}
    [:div {:class "span6"} "Radiant"]
    [:div {:class "span6"} "Dire"]
    ]
   [:div {:class "row tower"}
    [:div {:class "span2"} (tower-status "Tier 1 Top" (match "tower_status_radiant"))]
    [:div {:class "span2"} (tower-status "Tier 1 Mid" (match "tower_status_radiant"))]
    [:div {:class "span2"} (tower-status "Tier 1 Bot" (match "tower_status_radiant"))]

    [:div {:class "span2"} (tower-status "Tier 1 Top" (match "tower_status_dire"))]
    [:div {:class "span2"} (tower-status "Tier 1 Mid" (match "tower_status_dire"))]
    [:div {:class "span2"} (tower-status "Tier 1 Bot" (match "tower_status_dire"))]
    ]

   [:div {:class "row tower"}
    [:div {:class "span2"} (tower-status "Tier 2 Top" (match "tower_status_radiant"))]
    [:div {:class "span2"} (tower-status "Tier 2 Mid" (match "tower_status_radiant"))]
    [:div {:class "span2"} (tower-status "Tier 2 Bot" (match "tower_status_radiant"))]

    [:div {:class "span2"} (tower-status "Tier 2 Top" (match "tower_status_dire"))]
    [:div {:class "span2"} (tower-status "Tier 2 Mid" (match "tower_status_dire"))]
    [:div {:class "span2"} (tower-status "Tier 2 Bot" (match "tower_status_dire"))]
    ]

   [:div {:class "row tower"}
    [:div {:class "span2"} (tower-status "Tier 3 Top" (match "tower_status_radiant"))]
    [:div {:class "span2"} (tower-status "Tier 3 Mid" (match "tower_status_radiant"))]
    [:div {:class "span2"} (tower-status "Tier 3 Bot" (match "tower_status_radiant"))]

    [:div {:class "span2"} (tower-status "Tier 3 Top" (match "tower_status_dire"))]
    [:div {:class "span2"} (tower-status "Tier 3 Mid" (match "tower_status_dire"))]
    [:div {:class "span2"} (tower-status "Tier 3 Bot" (match "tower_status_dire"))]
    ]

   [:div {:class "row tower"}
    [:div {:class "span1"} (barrack-status "Melee Top" (match "barracks_status_radiant"))]
    [:div {:class "span1"} (barrack-status "Ranged Top" (match "barracks_status_radiant"))]
    [:div {:class "span1"} (barrack-status "Melee Mid" (match "barracks_status_radiant"))]
    [:div {:class "span1"} (barrack-status "Ranged Mid" (match "barracks_status_radiant"))]
    [:div {:class "span1"} (barrack-status "Melee Bot" (match "barracks_status_radiant"))]
    [:div {:class "span1"} (barrack-status "Ranged Bot" (match "barracks_status_radiant"))]

    [:div {:class "span1"} (barrack-status "Melee Top" (match "barracks_status_dire"))]
    [:div {:class "span1"} (barrack-status "Ranged Top" (match "barracks_status_dire"))]
    [:div {:class "span1"} (barrack-status "Melee Mid" (match "barracks_status_dire"))]
    [:div {:class "span1"} (barrack-status "Ranged Mid" (match "barracks_status_dire"))]
    [:div {:class "span1"} (barrack-status "Melee Bot" (match "barracks_status_dire"))]
    [:div {:class "span1"} (barrack-status "Ranged Bot" (match "barracks_status_dire"))]
    ]

   [:div {:class "row tower"}
    [:div {:class "span3"} (tower-status "Ancient Top" (match "tower_status_radiant"))]
    [:div {:class "span3"} (tower-status "Ancient Bot" (match "tower_status_radiant"))]
    [:div {:class "span3"} (tower-status "Ancient Top" (match "tower_status_dire"))]
    [:div {:class "span3"} (tower-status "Ancient Bot" (match "tower_status_dire"))]
    ]
   ])