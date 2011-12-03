(defproject sudoku "1.0.0-SNAPSHOT"
  :description "Sudoku solver"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [org.clojure/clojure-contrib "1.2.0"]]
  :dev-dependencies [[swank-clojure "1.3.3"]
                     [midje "1.3-alpha5"]
                     [lein-midje "1.0.4"]]
  :aot [#"sudoku.*"]
  :main sudoku.main)
