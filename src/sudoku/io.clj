(ns sudoku.io
  (:require [clojure.java.io :as io] )
  (:require [clojure.contrib.string :as str] ))

(defn read-file [f]
  (with-open [rdr (io/reader f)]
    (let [digits (filter #(Character/isDigit %) (reduce concat '() (line-seq rdr)))]
      (vec (map #(Integer. (.toString %)) digits)))))
  
(defn read-batch-file [f]
  (let [rdr (io/reader f)
        lines (line-seq rdr)]
    (for [line lines]
      (vec (map #(Integer. (.toString %)) (seq (.trim line)))))))



(defn write-file [vek]
  (if vek
    (let [v (map (fn [i] (str
                          (vek (dec i))
                          (if (zero? (rem i 9)) (if (zero? (rem i 27)) "\n\n" "\n") (if (zero? (rem i 3)) "  " " ")))) (range 1 82))]
      (println (apply str v)))
    (println "No solution")))