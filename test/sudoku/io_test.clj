(ns sudoku.io-test
  (:use sudoku.io clojure.test midje.sweet))

(def p (readFile "test/resources/puzzle1.txt"))

(def exp (vec (flatten (for [i (range 9)] (range 9)))))

(fact p => exp)