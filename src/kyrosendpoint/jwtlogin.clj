(ns kyrosendpoint.jwtlogin
  (:require [kyrosendpoint.jwtutils :as utils]
            [kyrosendpoint.db :as db]
            [buddy.hashers :as hs]))

(defn login [request]
  (let [nome (get-in request [:query-params :nome])
        senha (hs/derive(get-in request [:query-params :senha]))]
    (if (nil? (db/nome-by-senha senha))
      {:status "404" :body "não encontrado"}
      (utils/create-token nome))))

;;(defn usuarios[request]
;;  ())
