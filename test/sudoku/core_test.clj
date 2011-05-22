(ns sudoku.core-test
  (:use sudoku.core clojure.test midje.sweet))

(fact (fibo 6) => 13)

(deftest fibo-test
  (is (= 13 (fibo 6))))

