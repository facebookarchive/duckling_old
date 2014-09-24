(ns picsou.try
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs.core.async :refer [put! chan <!]]
            [cljs.reader :as reader]
            [goog.events :as events]
            [goog.string :as gstring]
            [goog.date :as gdate]
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
  (let [phrase (om/get-state owner :text)
        lang (om/get-state owner :lang)
        now (gdate/DateTime.)
        fnow (str (.toIsoString now) "/" (.getTimezoneOffset now))]
    (.log js/console "Working on: " phrase)
    (.pushState js/history nil nil (str "#" phrase))
    (put! (om/get-state owner :fb) :reset)
    (edn-xhr {:method "GET"
              :url (str "parse/" lang "/" fnow "/" phrase)
              :on-complete #(do (om/update! app [:parse] (first %)))})))

(defn handle-change [e owner {:keys [text] :as state}]
  (om/set-state! owner :text (.. e -target -value)))

(defn- token->div [{:keys [rule text route] :as token}]
  (dom/div #js {:className "token"}
    (if (seq route)
      (apply dom/div nil (map token->div route))
      (dom/div #js {:className "text"} text))
    (dom/div #js {:className "rule"} rule)))

(defn parse-view [cursor owner]
  (reify
    om/IRender
    (render [this]
      (dom/div nil
        (apply dom/div #js {:className "value"}
          (let [{:keys [start grain end]} (:value cursor)]
            (if end
              [(dom/span nil "From ")
               (dom/span #js {:className "start"} start)
               (dom/span nil " to ")
               (dom/span #js {:className "end"} end)
               (dom/span #js {:className "grain"} (str " " grain))]
              [(dom/span #js {:className "start"} start)
               (dom/span #js {:className "grain"} (str " " grain))])))
          (token->div cursor)))))

(defn- send-feedback [state owner]
  (.log js/console (:stage state))
  (case (:stage state)
    :init (om/set-state! owner :stage :clicked)
    :clicked (om/set-state! owner :stage :final)))

(defn feedback-view [cursor owner]
  (reify
    om/IInitState
    (init-state [_]
      {:stage :init
       :feedback ""})
    om/IWillMount
    (will-mount [_]
      (let [fb-chan (om/get-state owner :fb)]
        (go (loop []
          (let [info (<! fb-chan)]
            (om/set-state! owner :stage :init)
            (om/set-state! owner :feedback "")
            (recur))))))
    om/IRenderState
    (render-state [_ {:keys [stage feedback] :as state}]
      (dom/div #js {:className "feedback"}
        (when (= :clicked stage)
          (dom/div nil "Thanks, we got your feedback. Feel free to provide additional information:"
            (dom/textarea #js {:type :text :ref "feedback" :value (:feedback state)
                            :onChange #(om/set-state! owner :feedback (.. % -target -value))})))
        (if (#{:init :clicked} stage)
          (dom/a #js {:onClick #(send-feedback state owner)}
            (case (:stage state) :init "Flag as incorrect result"
                                 :clicked "Send"))
          (dom/div nil "Thanks!"))))))

(defn main-view [app owner]
  (reify
    om/IInitState
    (init-state [_]
      {:text "next week"
       :lang "en$core"
       :fb (chan)}) ; channel to communicate with feedback component 
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
          (dom/select #js {:value (:lang state)
                           :onChange #(om/set-state! owner :lang (.. % -target -value))}
                      (dom/option #js {:value "en$core"} "English")
                      (dom/option #js {:value "cn$core"} "Chinese")
                      (dom/option #js {:value "es$core"} "Spanish")
                      (dom/option #js {:value "fr$core"} "French"))
          (dom/button #js {:onClick #(get-parse app owner)} "Try me!"))
        (om/build parse-view (:parse app))
        (om/build feedback-view app {:init-state {:fb (:fb state)}})))))

(om/root main-view app-state
  {:target (. js/document (getElementById "main"))})

