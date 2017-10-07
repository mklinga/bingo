(ns bingo.victory)

(defonce SIZE 3)

(defn get-atom-value [item] @(:clicked item))

(defn get-columns [items]
  (let [first-indices (range 0 SIZE)]
    (map
      (fn [index] (map #(nth items %) (range index (* SIZE SIZE) SIZE)))
      first-indices)))

(defn get-rows [items]
  (let [first-indices (range 0 (* SIZE SIZE) SIZE)]
    (map
      (fn [index] (map #(nth items %) (range index (+ index SIZE))))
      first-indices)))

(defn get-diagonals [items]
   (let
     [down (range 0 (* SIZE SIZE) (inc SIZE))
      up (range (dec SIZE) (dec (* SIZE SIZE)) (dec SIZE))]
     (conj
       []
       (map #(nth items %) down)
       (map #(nth items %) up))))

(defn has-winner [items]
  (some true? (map #(every? true? (map get-atom-value %)) items)))

(defn has-win-row [items]
  (let [rows (get-rows items)]
    (has-winner rows)))

(defn has-win-column [items]
  (let [cols (get-columns items)]
    (has-winner cols)))

(defn has-win-diagonal [items]
  (let [diags (get-diagonals items)]
    (has-winner diags)))

(defn has-win [items]
  (or
    (has-win-row items)
    (has-win-column items)
    (has-win-diagonal items)))

