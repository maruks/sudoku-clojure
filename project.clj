(defproject sudoku "1.0.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.2.1"]
                 [org.clojure/clojure-contrib "1.2.0"]]
  :dev-dependencies [[swank-clojure "1.3.1"]
                     [midje "1.1.1"]]
  :aot [#"sudoku.*"]
  :main sudoku.main)