(ns sudoku.core
  (:use clojure.set)
  (:use [clojure.contrib.seq :only [find-first]]))

(defn select-row [board index]
  (let [start (- index (rem index 9))
        end (+ start 9)]
    (set (map #(board %) (range start end)))))

(defn select-column [board index]
  (let [start (rem index 9)
        end (+ start 73)]
    (set (map #(board %) (range start end 9)))))

(defn select-square [board index]
  (let [x (- (rem index 9)(rem (rem index 9) 3))
        y (- (quot index 9) (rem (quot index 9) 3))
        spos (+ x (* y 9))
        r (range spos (+ spos 3))
        rng (union (set r) (set (map #(+ % 9) r)) (set (map #(+ % 18) r)))]
    (set (map #(board %) rng))))

(defn possible-values [board index]
  (difference (set (range 1 10)) (select-row board index) (select-column board index) (select-square board index)))

(defn smallest-change-set [board]
  (when-let [sets (not-empty (map #(vector % (possible-values board %))
                                  (filter #(zero? (board %)) (range (count board)))))]
    (reduce #(if (< (count (second %1)) (count (second %2))) %1 %2) sets)))

(defn solve [board]
  (if-let [change (smallest-change-set board)]
    (let [index (first change)
          numbers (second change)
          solutions (map #(solve (assoc board index %)) (sort numbers))]
      (find-first #(not (nil? %)) solutions))
    board))