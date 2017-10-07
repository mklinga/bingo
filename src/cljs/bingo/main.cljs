(ns bingo.home
  (:require
    [reagent.core :as r]
    [bingo.victory :as victory]))

(defn log [& args] (apply (.-log js/console) args))

(defonce items
  [
   { :id 1 :item "Dropped production Table" }
   { :id 2 :item "Talked about text editors in #dev" }
   { :id 3 :item "Introduced bug in production" }
   { :id 4 :item "Fixed a bug in production" }
   { :id 5 :item "Dropped production Table" }
   { :id 6 :item "Talked about text editors in #dev" }
   { :id 7 :item "Introduced bug in production" }
   { :id 8 :item "Fixed a bug in production" }
   { :id 9 :item "Fixed a bug in production" }
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
  [:div.bingo-grid
   (doall
     (map grid-item items))])

(defn main-view []
  (let [has-win (victory/has-win initial-items)]
  [:div
   [:h2 "Developer ringo!"]
   (bingo-grid initial-items)
   [:h2 (if has-win "Victory!" "Keep going, you'll get there.")]]))
