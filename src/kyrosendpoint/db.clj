(ns kyrosendpoint.db
  (:require [buddy.hashers :refer [check]]
            [clojure.java.jdbc :as jdbc]
            [next.jdbc :as next]
            [next.jdbc.sql :as sql]
            [next.jdbc.result-set :as rs]
            [honeysql.core :as h]
            [honeysql.helpers :as hh]))
;;PS:// ainda em adaptação com o next.jdbc, considerando que clojure.java.jdbc está considerado
;; substituído no README do projeto
(def postgre-db {:dbtype "postgresql"
                 :dbname "endpointdb"
                 :user "kyros"
                 :password "kyrospassword"
                 })

;;principais

(defn mostra-notas[dono]
  (sql/find-by-keys postgre-db :notes {:dono dono}))

(defn add-user [record]
  (jdbc/insert! postgre-db :usuarios record))

(defn add-note [record]
  (jdbc/insert! postgre-db :notes record))

(defn update-titulo [novo antigo]
  (sql/update! postgre-db :notes {:titulo novo} {:titulo antigo}))

(defn update-conteudo [novo titulo]
  (sql/update! postgre-db :notes {:conteudo novo}{:titulo titulo}))

(defn deletar-nota [titulo]
(sql/delete! postgre-db [:titulo titulo]))


;;auxiliares para o honeysql
(def db (next/get-datasource postgre-db))

(defn query-helper [sql]
  (next/execute! db sql
                 {:return-keys true
                  :builder-fn rs/as-unqualified-maps}))

(defn query-helper-one [sql]
  (next/execute-one! db sql
                 {:return-keys true
                  :builder-fn rs/as-unqualified-maps}))
;;

;;necessário para autenticações
(defn nome-by-senha [{:keys [nome senha]}]
  (let [nome (-> (hh/select :*)
                 (hh/from :usuarios)
                 (hh/where := :nome nome)
                 h/format
                 query-helper-one)
        nome-clean (dissoc nome :senha)] 
      (if (and nome (check senha (:senha nome)))
      nome-clean
      nil)))

;;utilitário de teste
(defn mostra-usuarios []
  (let [usuarios (->
                  (hh/select :*)
                  (hh/from :usuarios)
                  h/format
                  query-helper)
        nome-limpo (map #(dissoc % :password) usuarios)]
    nome-limpo))
    