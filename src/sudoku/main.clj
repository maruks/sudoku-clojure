(ns sudoku.main
  (:use sudoku.core)
  (:gen-class))

(defn -main [& args]
  (if (seq args)
    (println "Usage: fibo <number>")))