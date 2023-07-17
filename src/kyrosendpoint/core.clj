(ns kyrosendpoint.core
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route] 
            [kyrosendpoint.db :as db] 
            [buddy.hashers :as hs]
            [kyrosendpoint.jwtutils :as utils]
            [kyrosendpoint.jwtlogin :as login]))

;;gera uma string pseudo-aleatória

(defn gerar-uuid [len]
  (apply str (take len (repeatedly #(char (+ (rand 26) 65))))))

(defn respond-test [request]
  {:status 200 :body "Server is running"})

(defn criar-usuario [request]
  (let [nome (get-in request [:query-params :nome])
        email (get-in request [:query-params :email])
        senha (hs/derive (get-in request [:query-params :senha])) 
        ] 
    (db/add-user {:nome nome :email email :senha senha})
    {:status 200 :body "Seu usuario foi criado e assinado com um token JWT"}))

(defn criar-note [request]
  ;; id cancelado temporariamente para testes com o arquivo schema.sql antigo
  (let [dono (get-in request [:query-params :dono])
        titulo (get-in request [:query-params :titulo])
        conteudo (get-in request [:query-params :conteudo])]
    (db/add-note {:titulo titulo :conteudo conteudo :dono dono})
    {:status 200 :body "Anotação criada com sucesso"}))

(defn acessar-nota [request]
  (let [dono (get-in request [:query-params :dono])
        senha (get-in request [:query-params :senha])
        ] 
      (db/mostra-notas dono)))

(defn atualizar-titulo[request]
  (let [id (get-in request [:query-params :id])
        novo-titulo (get-in request [:query-params :novo-titulo])] 
       (db/update-titulo novo-titulo id)))

(defn atalho-usuarios[]
  db/mostra-usuarios)

(defn deletar-nota[request]
  (let [id (get-in request [:query-params :id])]
    (db/deletar-nota id)))

(def routes
  (route/expand-routes 
   #{["/test" :get respond-test :route-name :test]
     ["/usuarios" :get atalho-usuarios :route-name :usuarios]
     ["/minhasnotas" :get acessar-nota :route-name :minhas-notas]
     ["/deletanota" :get deletar-nota :route-name :deleta-nota] 
     ["/criarnote" :post criar-note :route-name :criar-note]
     ["/criarusuario" :post criar-usuario :route-name :criar-usuario]}))


(def server-spec
  (http/create-server 
   {::http/routes routes
    ::http/type :jetty
    ::http/port 8890}))


(defn sobe-server []
  (http/start (http/create-server server-spec)))

;; atom para parar/resetaverr o server
(defonce server(atom nil))

(defn para-server []
  (http/stop @server))

(defn restart-server[]
  (para-server)
  (sobe-server))