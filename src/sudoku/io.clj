(ns sudoku.io
  (:require [clojure.contrib.duck-streams :as dst])
  (:require [clojure.contrib.string :as str] ))

(defn readFile [f]
  (vec (map #(Integer. (.trim %))(str/split #",|\n" (slurp f)))))

(defn writeFile [f vec]
  (if (nil? vec)
    (dst/spit f "No solution")
    (dst/spit f (apply str (interpose "," vec)))))