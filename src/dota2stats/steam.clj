(ns dota2stats.steam
  (:require [clojure.data.json :as json]
            [clj-http.client :as client]))

(defn heros []
  (let [steam-key (get (System/getenv) "STEAM_KEY")
        hero-url (str "http://api.steampowered.com/IEconDOTA2_570/GetHeroes/v0001/?language=en_us&key=" steam-key)
        heros-json (json/read-str ((client/get hero-url) :body))]
    ((heros-json "result") "heroes")))