(ns bingo.home
  (:require
    [reagent.core :as r]
    [bingo.victory :as victory]))

(defn log [& args] (apply (.-log js/console) args))

(defonce items
  [
   { :id 1 :item "First pull request" }
   { :id 2 :item "Talked about text editors in #dev" }
   { :id 3 :item "Introduced bug in production" }
   { :id 4 :item "Fixed a bug in production" }
   { :id 5 :item "Writing on the wrong channel" }
   { :id 6 :item "Late from the standup" }
   { :id 7 :item "Dropped production Table" }
   { :id 8 :item "Presented a sharing session" }
   { :id 9 :item "Heard a joke about dutch people" }
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
   [:h2 "Developer bingo!"]
   (bingo-grid initial-items)
   [:h2 { :class (when has-win "winner")}
    (if has-win "AWESOME!" "Keep going, you'll get there.")]]))

