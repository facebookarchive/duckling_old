(ns picsou.time
  (:use [clojure.tools.logging]
        [plumbing.core])
  (:require
    [picsou.util :as util]
    [picsou.time.util :as tu]
    [clj-time.core :as t]
    [clj-time.local :as tl])
  (:refer-clojure :exclude [resolve]))

(defn now
  "Replace clj-time's now with something that takes context into account.
  Without context, defaults to local time now."
  ([] (tl/local-now))
  ([context] (or (:reference-time context) (now))))

(defn resolve-time' [{:keys [pred not-immediate] :as token} ctx]
  (try (let [cursor (now ctx)
             [start end :as interval] (pred cursor ctx)]
         ; if not-immediate (like "Tuesday"), we might need to call pred twice
         (if (and interval (tu/in? cursor interval) not-immediate)
           (pred (if (:backward ctx) start end) ctx) ; run again because we don't want immediate
           interval))
       (catch Throwable e
         (warnf "picsou resolve-time' error: %s" (.getMessage e))))) ; OK

(defn resolve-time
  "Returns a collection of date intervals.
   - if no :direction provided in context, looks forward first, and backward if nothing found.
   - if a :direction (:forward | :backward) is provided, only look there."
  [{:keys [pred not-immediate type from to fields grain] :as token} context]
  {:pre [pred]}
  (let [now     (now context)
        fwd  (when (or (not (:direction context)) (= :forward (:direction context)))
               (resolve-time' token context))
        bwd (when (or
                    (= :backward (:direction context))
                    (and (not (:direction context)) (nil? fwd)))
              (resolve-time' token (assoc context :backward true)))
        [dfrom dto] (or fwd bwd [nil nil])]
    (cond
      (and (nil? dfrom) (nil? dto))
      []
      
      (= :interval type)
      [{:type :interval
        :from (-> {:value (str dfrom)}
                  (merge (:fields from))
                  (?> grain assoc :grain grain) ; by default, take the global grain
                  (?> (:grain from) assoc :grain (:grain from)))
        :to   (-> {:value (str dto)}
                  (merge (:fields to))
                  (?> grain assoc :grain grain)
                  (?> (:grain to) assoc :grain (:grain to)))}]
      
      :else
      (-> {:value (str dfrom) :type :value}
          (merge fields)
          (?> grain assoc :grain grain)
          vector))))

(defn resolve-duration
  "Returns a collection of durations (exactly one) in seconds.
   Note that it's an appoximation, we use ((now + duration) - now) to convert into seconds."
  [{:keys [val latent] :as token} context]
  {:pre [val]}
  [(-> (t/interval (now context) (t/plus (now context) val))
       (t/in-secs))])

(defn resolve-default-val
  "Returns a collection of values (the :val field directly)"
  [{:keys [val] :as token} context]
  (if val
    [val]
    []))

(defn- add-fields-and-grain
  "Add fields to the value, if the value is a map"
  [fields grain value]
  (if (map? value)
    (->> value
         (merge fields)
         (?>> grain merge {:grain grain}))
    value))

(defn resolve
  "Try to resolve a token. Returns a collection of values ready for external world."
  [{:keys [dim latent timezone fields grain] :as token} context]
  (case dim
         :time (resolve-time token context)
         :duration (resolve-duration token context)
         (resolve-default-val token context)))

(defn local-date-time
  "Builds a non GMT datetime in order to run the corpus etc."
  [args]
  (t/from-time-zone (apply t/date-time args)
                    (t/time-zone-for-offset -2)))