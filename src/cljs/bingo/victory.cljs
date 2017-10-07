(ns bingo.victory)

(defonce SIZE 3)

(defn get-atom-value [item] @(:clicked item))

(defn get-columns [items]
 (loop
   [i 0 coll []]
   (if
     (< i SIZE)
     (recur
       (inc i)
       (conj
         coll
         (map #(nth items %) (range i (* SIZE SIZE) SIZE))))
     coll)))

(defn get-rows [items]
 (loop
   [i 0 coll []]
   (if
     (< i (* SIZE SIZE))
     (recur
       (+ i SIZE)
       (conj
         coll
         (map #(nth items %) (range i (+ i SIZE)))))
     coll)))

(defn has-winner [items]
  (some
    true?
    (map
      #(every? true? (map get-atom-value %))
      items)))

(defn has-win-row [items]
  (let [rows (get-rows items)]
    (has-winner rows)))

(defn has-win-column [items]
  (let [cols (get-columns items)]
    (has-winner cols)))

(defn has-win-diagonal [items]
  false)

(defn has-win [items]
  (or
    (has-win-row items)
    (has-win-column items)
    (has-win-diagonal items)))

