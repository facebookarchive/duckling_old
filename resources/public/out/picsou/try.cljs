(ns picsou.try
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs.core.async :refer [put! chan <!]]
            [cljs.reader :as reader]
            [goog.events :as events]
            [goog.string :as gstring]
            [goog.string.format :as gformat]) ; not sure why I need to import this on top of goog.string?
  (:import [goog.net XhrIo]
           goog.net.EventType
           [goog.events EventType]))

(enable-console-print!)

(print (gstring/format "Booting") (.. js/window -location -hash))

(def app-state
  (atom
    {:phrase "tomorrow at 6am"
     :parse {:rule "rule"
             :route [{:rule "regex"
                      :text "tomorrow at 6am"}]}}))

;;
;; Backend communication
;;

(defn edn-xhr [{:keys [method url data on-complete]}]
  (let [xhr (XhrIo.)]
    (events/listen xhr goog.net.EventType.COMPLETE
      (fn [e]
        (on-complete (reader/read-string (.getResponseText xhr)))))
    (. xhr
      (send url method (when data (pr-str data))
        #js {"Content-Type" "application/edn"}))))

(defn get-parse [app owner]
  (let [phrase (.-value (om/get-node owner "phrase"))]
    (.log js/console "Working on: " phrase)
    (.pushState js/history nil nil (str "#" phrase))
    (edn-xhr {:method "GET"
              :url (str "parse/" phrase)
              :on-complete #(do (om/update! app [:parse] (first %)))})))

(defn handle-change [e owner {:keys [text] :as state}]
  (om/set-state! owner :text (.. e -target -value)))

(defn parse-view [cursor owner]
  (reify
    om/IRender
    (render [this]
      (dom/div nil (:text cursor)))))

(defn main-view [app owner]
  (reify
    om/IInitState
    (init-state [_]
      {:text "next week"})
    om/IWillMount
    (will-mount [_]
      (when-let [[_ msg-id] (re-find #"#(.+)$" (.. js/window -location -hash))]
        nil #_(get-parse (or msg-id "random") app)))
    om/IRenderState
    (render-state [this state]
      (dom/div nil
        (dom/div nil
          (dom/input #js {:type :text :ref "phrase" :value (:text state)
                          :onChange #(handle-change % owner state)
                          :onKeyDown #(when (= (.-key %) "Enter")
                                        (get-parse app owner))})
          (dom/button #js {:onClick #(get-parse app owner)} "Try me!"))
        (dom/div nil (om/build parse-view (:parse app)))))))

(om/root main-view app-state
  {:target (. js/document (getElementById "main"))})

