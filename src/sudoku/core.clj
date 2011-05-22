(ns sudoku.core)

(defn fibo [n]
  (if (< n 2) 1
      (+ (fibo (dec n)) (fibo (- n 2) ))))