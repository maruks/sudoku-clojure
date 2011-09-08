(ns sudoku.main
  (:use sudoku.core)
  (:use sudoku.io)
  (:gen-class))


(defn parmap [f coll] (let [agents (map #(agent %) coll)]
                        (doseq [a agents]
                           (do (println "!")
                           (println (deref a)))
                           
                           )))


(def functions {:s map :p pmap :a parmap})

(defn -main [& args]

  (cond (= (count args) 1) (let [board (read-file (first args))
                                 solution (solve board)]
                             (write-file solution))
        (= (count args) 2) (let [boards (read-batch-file (first args))
                                 funct (functions (keyword (nth args 1)))
                                 solutions (funct #(solve %) boards)]
                             (doseq [solution solutions] (write-file solution))
                             (shutdown-agents))
        :else (println "Usage: SudokuSolver <file name>")))