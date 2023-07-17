(ns kyrosendpoint.jwtutils
   (:require [buddy.auth :refer [authenticated?]]
             [buddy.auth.backends :as backends]
             [buddy.auth.middleware :refer [wrap-authentication]]
             [buddy.sign.util]
             [buddy.sign.jwt :as jwt]))

;; auxiliares

(def jwt-secret "JWT_SECRET")
(def backend (backends/jws {:secret jwt-secret}))


;; utilitários principais

(defn create-token [payload]
  (jwt/sign payload jwt-secret))

(defn wrap-jwt-authentication [handler] 
  (wrap-authentication handler backend))

(defn middleware
  [handler]
  (fn [request]
    (if (authenticated? request)
      (handler request)
      {:status 401 :body {:error "Não autorizado."}})))