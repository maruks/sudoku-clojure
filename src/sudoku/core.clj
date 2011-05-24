(ns sudoku.core
  (:use clojure.set))

(defn selectRow [vec i]
  (let [start (- i (rem i 9))
        end (+ start 8)]
  (set (for [e (range start end)](nth vec e)))))

(defn selectColumn [vec i]
  (let [start (rem i 9)
        end (+ start 72)]
  (set (for [e (range start end 9)](nth vec e)))))  

(defn selectSquare [vec i]
  (let [x (- (rem i 9)(rem (rem i 9) 3))
        y (- (quot i 9) (rem (quot i 9) 3))
        spos (+ x (* y 9))
        r (range spos (+ spos 2))
        rng (union (set r) (set (map #(+ % 9) r)) (set (map #(+ % 18) r)))]
  (set (for [e rng](nth vec e)))))

(defn smallestChangeSet [vec]
  (let [sets (for [i (range (count vec)) :when (zero? (nth vec i))]
               [i (difference (set (range 1 9)) (selectRow vec i) (selectColumn vec i) (selectSquare vec i))])]
    (if (not (seq sets))
      nil
      (reduce #(if (< (count (%1 1)) (count (%2 1))) %1 %2) sets))))

(defn solve [vec]
  (let [s (smallestChangeSet vec)]
    (do
      (println vec)
      (println (first s))
      (println (nth s 1))
      (if (nil? s)
        vec
       (if (not (seq (nth s 1)))

         (do (println "NIL") nil)

         (do (println "SOL")
         (let [sol
              (for [i (nth s 1)] (solve (assoc vec (first s) i))) ]
          (do (println sol)
          (some #(if (nil? %) false %) sol)))
          ))))))