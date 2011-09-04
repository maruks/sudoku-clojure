(ns sudoku.main
  (:use sudoku.core)
  (:use sudoku.io)
  (:gen-class))

(defn -main [& args]

  (cond (= (count args) 1) (let [board (read-file (first args))
                                 solution (solve board)]
                             (write-file solution))
        (= (count args) 2) (let [boards (read-batch-file (first args))
                                 solutions (pmap #(solve %) boards)]
                             (doseq [solution solutions] (write-file solution)))
        :else (println "Usage: SudokuSolver <file name>"))
  (System/exit 0))