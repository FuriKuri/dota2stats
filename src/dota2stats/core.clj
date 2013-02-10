 (ns dota2stats.core
   (:use dota2stats.views
         dota2stats.steam
         compojure.core
         [hiccup.middleware :only (wrap-base-url)]
         [ring.adapter.jetty :only [run-jetty]])
   (:require [compojure.route :as route]
             [compojure.handler :as handler]
             [compojure.response :as response]))

(defroutes main-routes
  (GET "/" [] (index-page))
  (GET "/heros" [] (hero-page (heros)))
  (GET "/matches" [steamid] (matchs-page (matches steamid)))
  (GET "/match" [matchid] (match-page (match matchid)))
  (route/resources "/")
  (route/not-found "Page not found"))

(def app
  (-> (handler/site main-routes)
      (wrap-base-url)))

(defn -main []
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "8080"))]
    (run-jetty app {:port port})))