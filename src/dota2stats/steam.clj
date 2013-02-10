(ns dota2stats.steam
  (:require [clojure.data.json :as json]
            [clj-http.client :as client]))

(defn heros []
  (let [steam-key (get (System/getenv) "STEAM_KEY")
        hero-url (str "http://api.steampowered.com/IEconDOTA2_570/GetHeroes/v0001/?language=en_us&key=" steam-key)
        heros-json (json/read-str ((client/get hero-url) :body))]
    ((heros-json "result") "heroes")))

(defn match [match-id]
  (let [steam-key (get (System/getenv) "STEAM_KEY")
        match-url (str "https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/V001/?key=" steam-key "&match_id=" match-id)
        match-json (json/read-str ((client/get match-url) :body))]
    (match-json "result")))

(defn matches [steam-id]
  (if (not (clojure.string/blank? steam-id))
    (let [steam-key (get (System/getenv) "STEAM_KEY")
          matches-url (str "https://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/V001/?key=" steam-key "&account_id=" steam-id)
          matches-json (json/read-str ((client/get matches-url) :body))
          matches-result (matches-json "result")]
      matches-result)
    nil))