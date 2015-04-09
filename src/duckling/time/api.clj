(ns duckling.time.api
  (:require [duckling.time.pred :as pred]
            [duckling.time.obj :as t]
            [clj-time.coerce :as c])
  (:refer-clojure :exclude [resolve]))

(defn resolve
  "Given a token presumably produced by this module, returns a list of tokens
  with a resolved value. For time in particular, predicates are turned into
  actual time instants or intervals.
  It returns a list of tokens instead of just one, because several values may
  be possible.
  If the token cannot be resolved, :value must be removed."
  [token context]
  (take 1 (pred/resolve token context)))

(defn export-value
  "Given a token, returns its value for the outside world.
  Datetimes are modified by date-fn."
  [{:keys [dim value direction] :as token} {:keys [date-fn] :as opts}]
  (let [date-fn (or date-fn str)]
    (when value
  	  (case dim
        :time   (cond
                  (contains? #{"before" "after"} direction)
                    (case direction
                      "before" {:type "interval"
                                :to (date-fn (:start value))}
                      "after"  {:type "interval"
                                :from (date-fn (:start value))})
                  (:end value)
                    {:type "interval"
                     :from {:value (date-fn (:start value))
                            :grain (:grain value)}
                     :to   {:value (date-fn (:end value))
                            :grain (:grain value)}}
                  :else
                    {:type "value"
                     :value (date-fn (:start value))
                     :grain (:grain value)})
        :duration ; if there is only one field, we can set :value and :unit
                  ; otherwise just keep the fields
                  (let [[[unit val] & more] (seq value)
                        add-fields (when-not more {:value val
                                                   :unit unit})]
                    (merge value
                           add-fields
                           {:normalized {:value (t/period->duration value)
                                         :unit "second"}}))

        (:temperature :distance :amount-of-money :number :volume)
                  (merge {:type "value" :value value}
                         (select-keys token [:unit]))

        :quantity (select-keys token [:value :unit :product])

  	    {:value value})))) ; nest value for other dims
