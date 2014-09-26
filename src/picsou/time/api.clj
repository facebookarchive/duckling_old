(ns picsou.time.api
  (:require [picsou.time.pred :as pred]
            [picsou.time.obj :as t])
  (:refer-clojure :exclude [resolve]))

(declare date->str)

(defn resolve
  "Given a token presumably produced by this module, returns a list of tokens
  with a resolved value. For time in particular, predicates are turned into
  actual time instants or intervals.
  It returns a list of tokens instead of just one, because several values may
  be possible.
  If the token cannot be resolved, :value must be removed."
  [token context]
  (pred/resolve token context))

(defn export-value
  "Given a token, returns its value for the outside world. For instance,
  temporal expressions are coerced into strings."
  [{:keys [dim value] :as token}]
  (when value
	  (case dim
	    :time   (if (:end value)
	              {:type "interval"
	               :from {:value (date->str (:start value))
	                      :grain (:grain value)}
	               :to   {:value (date->str (:end value))
	                      :grain (:grain value)}}
	              {:type "value"
	               :value (date->str (:start value))
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
     
      :quantity (select-keys token [:value :unit :product])
	    
	    value))) ; keep value as is for other dims

(defn date->str
  "Coerce date to string for API output"
  [t]
  (str t))

; (defn resolve-duration
;   "Returns a collection of durations (exactly one) in seconds.
;    Note that it's an appoximation, we use ((now + duration) - now) to convert into seconds."
;   [{:keys [val latent] :as token} context]
;   {:pre [val]}
;   [(-> (t/interval (now context) (t/plus (now context) val))
;        (t/in-secs))])
