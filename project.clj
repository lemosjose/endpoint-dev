(defproject kyros-endpoint "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.postgresql/postgresql "42.2.5"]
                 [honeysql/honeysql "1.0.444"]
                 [io.pedestal/pedestal.service "0.5.7"]
                 [io.pedestal/pedestal.jetty "0.5.7"]
                 [io.pedestal/pedestal.route]
                 [buddy/buddy-core "1.4.0"]
                 [buddy/buddy-auth "2.1.0"]
                 [buddy/buddy-hashers "1.3.0"]
                 [seancorfield/next.jdbc "1.1.613"]
                 ]
  :main ^:skip-aot kyros-endpoint.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
