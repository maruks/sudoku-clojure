(ns sudoku.core
  (:use clojure.set)
  (:use [clojure.contrib.seq :only [find-first]]))

(defn select-row [vek i]
  (let [start (- i (rem i 9))
        end (+ start 9)]
    (set (for [e (range start end)](vek e)))))

(defn select-column [vek i]
  (let [start (rem i 9)
        end (+ start 73)]
    (set (for [e (range start end 9)](vek e)))))  

(defn select-square [vek i]
  (let [x (- (rem i 9)(rem (rem i 9) 3))
        y (- (quot i 9) (rem (quot i 9) 3))
        spos (+ x (* y 9))
        r (range spos (+ spos 3))
        rng (union (set r) (set (map #(+ % 9) r)) (set (map #(+ % 18) r)))]
    (set (for [e rng](vek e)))))

(defn smallest-change-set [vek]
  (let [sets (for [i (range (count vek)) :when (zero? (vek i))]
               [i (difference (set (range 1 10)) (select-row vek i) (select-column vek i) (select-square vek i))])]
    (if (not (seq sets))
      nil
      (reduce #(if (< (count (%1 1)) (count (%2 1))) %1 %2) sets))))

(defn solve [vek]
  (let [s (smallest-change-set vek)]
    (if (nil? s)
      vek
      (if (not (seq (s 1)))
        nil
        (let [sol (for [i (s 1)] (solve (assoc vek (first s) i))) ]
          (find-first #(not (nil? %)) sol))))))