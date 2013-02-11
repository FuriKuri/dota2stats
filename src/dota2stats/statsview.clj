(ns dota2stats.statsview
  (:use [dota2stats.views]
        [hiccup core page]))

(defn var-def [data]
  (map #(str "[" (nth % 0) "," (nth % 1) "]")
       (for [c (range 0 (count data))]
         [c (nth data c)])))

(defn var-def-as-string [data name]
  (str "var " name " = [" (reduce #(str %1 "," %2) (var-def data)) "];"))

(defn vars [stats]
  (str (var-def-as-string (map #(% "kills") stats) "d1")
       (var-def-as-string (map #(% "deaths") stats) "d2")
       (var-def-as-string (map #(% "assists") stats) "d3")))

(defn vars-list [] "{data : d1, label : 'Kills'}, {data : d2, label : 'Deaths'}, {data : d3, label : 'Assists'}")

(defn show-stats [stats]
  [:script "(function basic(container) { " (vars stats) "  graph = Flotr.draw(container, [ " (vars-list) "], {    xaxis: {      minorTickFreq: 4    },     grid: {      minorVerticalLines: true    }  });})(document.getElementById('stats'));"])

(defn stats-page [stats]
  (html5
   (header)
   [:body
    (navi-bar "Stats")
    [:div {:class "container"}
     (fork-me-banner)
     [:h1 "Dota 2 Stats"]
     [:P
      [:form
       [:fieldset
        [:legend "View your stats"]
        [:label "Steam Id"]
        [:input {:type "text" :placeholder "Your Steam ID" :name "steamid"}]
        [:br]
        [:button {:type "submit" :class "btn"} "Search"]]]]
    ;; [:div {:class "stats"}]
     ;; (if (not (nil? stats)) (show-stats stats))
     ]
    (if (not (nil? stats))
      [:p
       [:div {:id "stats"}]
       (include-js "/js/flotr2.min.js")
       (show-stats stats)])
      (include-js "/js/bootstrap.js")]))