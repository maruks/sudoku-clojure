(ns sudoku.main
  (:use sudoku.core)
  (:use sudoku.io)
  (:gen-class))

(defn -main [& args]
  (if (seq args)
    (let [f (readFile (first args))
          s (solve f)] (writeFile System/out s))
    (println "Usage: SudokuSolver <file name>")))