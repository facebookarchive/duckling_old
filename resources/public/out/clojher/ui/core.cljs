(ns clojher.ui.core
  ;(:require-macros [cljs.core.async.macros :refer [go]])
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
    {:email {:from [{:email "Zoe"}]
             :to [{:email "alex@wit.ai"}]
             :date "2014-10-08"
             :subject "Comment ça va?"
             :plain "0123\n\n6789"}
     :meta  {:category "module"
             :notes [{:start 3 :end 4 :class "splitter"}
                     {:start 6 :end 7 :class "ignore"}]}}))

;;
;; Backend communication
;;

(def ^:private meths
  {:get "GET"
   :put "PUT"
   :post "POST"
   :delete "DELETE"})

(defn edn-xhr [{:keys [method url data on-complete]}]
  (let [xhr (XhrIo.)]
    (events/listen xhr goog.net.EventType.COMPLETE
      (fn [e]
        (on-complete (reader/read-string (.getResponseText xhr)))))
    (. xhr
      (send url (meths method) (when data (pr-str data))
        #js {"Content-Type" "application/edn"}))))

(defn get-message [msg-id app]
  (edn-xhr {:method :get
            :url (str "messages/" msg-id)
            :on-complete #(do (om/transact! app :email (constantly (:email %)))
                              (om/transact! app :meta (constantly (:meta %)))
                              (.pushState js/history nil nil (str "#" (-> % :email :msg-id))))}))

(defn save-meta [app meta]
  (print "saving meta" meta)
  (edn-xhr {:method :put
            :url (str "messages/" (-> @app :email :msg-id) "/meta")
            :data {:meta meta}
            :on-complete #(om/update! app :meta meta)}))


;; returns a list of [start end "classA classB ..."]
(defn segments [notes length]
  (let [; remove non-span notes, like category
        notes (filter :start notes)
        ; returns the classes of notes that contain the given interval
        containers (fn [i-start i-end]
                     (->> (filter (fn [{:keys [start end]}] (<= start i-start i-end end)) notes)
                          (map :class)
                          (interpose " ")
                          (apply str)))
        points (-> (mapcat (juxt :start :end) notes)
                   (concat [0 length])
                   set sort)
        segs (map vector points (next points))
        res  (map (fn [[start end]] (vector start
                                            end
                                            (containers start end))) segs)]
    (vec res)))

(segments (-> @app-state :meta :notes) 30)

(defn delete-note [app note]
  (save-meta app (update-in (:meta @app) [:notes] (fn [xs] (vec (remove #(= note %) xs))))))

(defn add-note [event app]
  (let [class (.. event -target -id)
        sel (.getSelection js/window)]
    (when (= "Range" (.-type sel))
      (let [range (.getRangeAt sel 0)
            parent (.. range -commonAncestorContainer -parentElement)
            span-start (.parseInt js/window (.. parent (getAttribute "data-start")))
            _ (when (js/isNaN span-start) (throw "Cannot create interleaved notes yet"))
            start (+ span-start (.-startOffset range))
            end   (+ span-start (.-endOffset range))]
        (save-meta app (update-in (:meta @app) [:notes] #(conj % {:start start
                                                                  :end end
                                                                  :class class})))))))

;; Returns a list of dom/buttons and dom/spans
;;
(defn span [app text notes [start end classes]]
  (.log js/console "span" start end classes)
  (conj
    ; display control box for notes starting at this span
    (vec
      (for [n notes :when (= start (:start n))]
        (dom/span nil
          (dom/button #js {:onClick #(delete-note app @n)} "-")
          (when (= "proposed" (:status n))
            (dom/button #js {:onClick #(save-meta app (update-in (:meta @app) [:notes]
                                                                 (fn [xs] (map (fn [nn]
                                                                                 (if (= @n nn)
                                                                                   (assoc nn :status "ok")
                                                                                   nn))
                                                                               xs))))} "OK")))))

    ; display the text span itself
    (dom/span #js {:className classes
                   :data-start start
                   :style #js {:white-space "pre-wrap"}}
              (subs text start end))))

(defn contacts [xs]
  (->> xs
       (map (fn [{:keys [name email]}] (if name (gstring/format "%s (%s)" name email) email)))
       (interpose ", ")
       (apply str)))

(defn contacts-view [app owner]
  (reify
    om/IInitState
    (init-state [_]
      {})
    om/IWillMount
    (will-mount [_]
      (let [[_ msg-id] (re-find #"#(.+)$" (.. js/window -location -hash))]
        (get-message (or msg-id "random") app)))
    om/IRenderState
    (render-state [this {:keys [delete]}]
      (let [email (:email app)
            text (:plain email)
            segs (segments (-> app :meta :notes) (count text))]
        (dom/div nil
          (dom/div #js {:id "controls"}
            (dom/div nil
              (dom/a #js {:onClick #(get-message "random" app) :href "#"} "Random")
              (dom/hr nil)
              (dom/div nil (contacts (:from email)))
              (dom/div nil (contacts (:to email)))
              (dom/div nil (str (:date email)))
              (dom/div nil (gstring/format "%s (%s)" (:subject email) (:msg-id email)))
              (dom/hr nil))

            (apply dom/div #js {:id "category"}
              (map (fn [cat]
                     (dom/button #js {:id cat
                                      :className (when (= cat (-> app :meta :category)) "selected")
                                      :onClick #(save-meta app (assoc (:meta @app) :category cat))}
                                     cat))
                   ["plain" "module" "ignore" "flag"]))

            (apply dom/div #js {:id "notes"}
              (map (fn [note-class]
                (dom/button #js {:id note-class
                                 :onClick #(add-note % app)} note-class))
                   ["splitter" "signature" "header" "ignore"])))

          (apply dom/div #js {:id "msg-body"}
            (mapcat (partial span app text (-> app :meta :notes)) segs)))))))

(om/root contacts-view app-state
  {:target (. js/document (getElementById "contacts"))})

