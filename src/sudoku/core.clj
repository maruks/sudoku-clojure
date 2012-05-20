(ns sudoku.core
  (:use clojure.set)
  (:use [clojure.contrib.seq :only [find-first]]))

(defmulti indexes (fn [what index] what))

(defmethod indexes :row [what index]
  (let [start (- index (rem index 9))
        end (+ start 9)]
    (range start end)))

(defmethod indexes :column [what index]
  (let [start (rem index 9)
        end (+ start 73)]
    (range start end 9)))

(defmethod indexes :square [what index]
  (let [x (- (rem index 9)(rem (rem index 9) 3))
        y (- (quot index 9) (rem (quot index 9) 3))
        spos (+ x (* y 9))
        r (range spos (+ spos 3))]
    (union (set r) (set (map #(+ % 9) r)) (set (map #(+ % 18) r)))))

(defn possible-values [board index]
  (letfn [(select [what] (set (map #(board %) (indexes what index))))]
    (difference (set (range 1 10)) (select :row) (select :column) (select :square))))

(defn smallest-change-set [board]
  (when-let [sets (not-empty (map #(vector % (possible-values board %))
                                  (filter #(zero? (board %)) (range (count board)))))]
    (reduce #(if (< (count (second %1)) (count (second %2))) %1 %2) sets)))

(defn solve [board]
  (if-let [change (smallest-change-set board)]
    (let [[index numbers] change
          solutions (map #(solve (assoc board index %)) (sort numbers))]
      (find-first #(not (nil? %)) solutions))
    board))