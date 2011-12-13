(ns sudoku.io-test
  (:use sudoku.io midje.sweet))

(def p (read-file "test/resources/puzzle1.txt"))

(def exp (vec (flatten (for [i (range 9)] (range 9)))))

(fact p => exp)