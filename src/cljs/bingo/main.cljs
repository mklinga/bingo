(ns bingo.home
  (:require [reagent.core :as r]))

(defn log [& args] (apply (.-log js/console) args))

;; (defonce click-count (r/atom 0))
;; (defn state-ful-with-atom []
;;   [:div {:on-click #(swap! click-count inc)}
;;    "I have been clicked " @click-count " times."])

(defonce items
  [
   { :id 1 :item "Dropped production Table" }
   { :id 2 :item "Talked about text editors in #dev" }
   { :id 3 :item "Introduced bug in production" }
   { :id 4 :item "Fixed a bug in production" }
 ])

(def initial-items (map #(assoc % :clicked (r/atom false)) items))

(defn grid-item [item]
  (let [clicked (:clicked item)]
  [:div.grid-item
   {:key (:id item)
    :class (if (true? @clicked) "clicked" "not-clicked")
    :on-click #(swap! clicked false?)}
   (:item item)]))

(defn bingo-grid [items]
  [:div
   (doall
     (map grid-item items))])

(defn main-view []
  [:div
   [:h2 "Developer Bingo!"]
   (bingo-grid initial-items)])
