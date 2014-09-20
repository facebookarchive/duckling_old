(ns picsou.ui.http
  (:use [plumbing.core]
        [org.httpkit.server :as server]
        [compojure.core :only [defroutes GET POST PUT DELETE]]
        [compojure.handler :only [site]]
        [compojure.route :as route])
  (:require [picsou.core :as pic]
            [ring.middleware.format-response :refer [wrap-restful-response]]
            [ring.middleware.format-params :refer [wrap-restful-params]]))


; (defn cher-controller [{{:keys [hex-msg-id]} :params :as request}]
;   (let [msg-id (str (Long/parseLong hex-msg-id 16))]
;     (prn "Chrome Extension request" msg-id)
;     (fetch/fetch "a@lxbrun.com" "r0see1888etoile" {:gmail-msg-id msg-id})))

(defn- trim [token]
  (-> token
      (dissoc :pred)
      (assoc :rule (-> token :rule :name))
      (assoc :route (map trim (:route token)))
      #_(update-in [])))

(defn parse-controller [{{:keys [phrase]} :params}]
  (prn "Parsing" phrase)
  (let [{:keys [winners stash]} (pic/parse phrase pic/default-context :en$core)]
    (prn "Got" (first winners))
    {:body (map trim winners)}))

(defroutes routes
  (GET "/parse/:phrase" [] parse-controller)
  (route/resources "/"))

(defonce stop-http-server (atom nil))

(defn -main []
  (when-let [f @stop-http-server] (f))

  (let [env (System/getenv)
        her-http-port (Integer/parseInt (or (get env "PICSOU_HTTP_PORT") "6888"))]
    (reset! stop-http-server
            (server/run-server (-> routes
                                   (wrap-restful-params)
                                   (wrap-restful-response :formats [:edn :json]))
                               {:name "picsou" :port her-http-port}))))

