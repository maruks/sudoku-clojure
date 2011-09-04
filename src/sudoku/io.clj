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
    (if (nil? vek)
      (println "No solution")
      (let [s (apply str(interpose "\n" (for [p (partition 9 vek)] (apply str(interpose \, p)))))]
        (println (str s "\n")))))