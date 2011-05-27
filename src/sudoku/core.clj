(ns sudoku.core
  (:use clojure.set)
  (:use [clojure.contrib.seq :only [find-first]]))

(defn selectRow [vec i]
  (let [start (- i (rem i 9))
        end (+ start 9)]
    (set (for [e (range start end)](vec e)))))

(defn selectColumn [vec i]
  (let [start (rem i 9)
        end (+ start 73)]
    (set (for [e (range start end 9)](vec e)))))  

(defn selectSquare [vec i]
  (let [x (- (rem i 9)(rem (rem i 9) 3))
        y (- (quot i 9) (rem (quot i 9) 3))
        spos (+ x (* y 9))
        r (range spos (+ spos 3))
        rng (union (set r) (set (map #(+ % 9) r)) (set (map #(+ % 18) r)))]
    (set (for [e rng](vec e)))))

(defn smallestChangeSet [vec]
  (let [sets (for [i (range (count vec)) :when (zero? (vec i))]
               [i (difference (set (range 1 10)) (selectRow vec i) (selectColumn vec i) (selectSquare vec i))])]
    (if (not (seq sets))
      nil
      (reduce #(if (< (count (%1 1)) (count (%2 1))) %1 %2) sets))))

(defn solve [vec]
  (let [s (smallestChangeSet vec)]
    (if (nil? s)
      vec
      (if (not (seq (s 1)))
        nil
        (let [sol (for [i (s 1)] (solve (assoc vec (first s) i))) ]
          (find-first #(not (nil? %)) sol))))))