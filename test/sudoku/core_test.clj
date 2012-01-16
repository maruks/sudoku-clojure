(ns sudoku.core-test
  (:use sudoku.core sudoku.io midje.sweet))

(def p1 (read-file "test/resources/puzzle1.txt"))
(def p2 (read-file "test/resources/puzzle2.txt"))
(def p3 (read-file "test/resources/puzzle3.txt"))
(def p4 (read-file "test/resources/puzzle4.txt"))
(def p5 (read-file "test/resources/puzzle5.txt"))
(def p6 (read-file "test/resources/puzzle6.txt"))

(def s3 (read-file "test/resources/sol3.txt"))
(def s4 (read-file "test/resources/sol4.txt"))
(def s5 (read-file "test/resources/sol5.txt"))
(def s6 (read-file "test/resources/sol6.txt"))

(def firstRow #{2 0 8 6 7})
(def secondRow #{3 0 2 5})
(def lastRow #{2 6 0 8 9})
(def firstColumn #{2 0 5 9})
(def secondColumn #{0 3 6})
(def lastColumn #{7 0 3 4 9})
(def upperLeftSquare #{2 3 5 6 0})
(def middleRightSquare #{9 3 1 0})
(def bottomRightSquare #{2 4 1 9 0})

(defn select [what board index] (set (map #(board %) (indexes what index))))

(fact (select :row p2 0) => firstRow)
(fact (select :row p2 3) => firstRow)
(fact (select :row p2 8) => firstRow)

(fact (select :row p2 9) => secondRow)
(fact (select :row p2 14) => secondRow)
(fact (select :row p2 17) => secondRow)

(fact (select :row p2 72) => lastRow)
(fact (select :row p2 76) => lastRow)
(fact (select :row p2 80) => lastRow)

(fact (select :column p2 0) => firstColumn)
(fact (select :column p2 27) => firstColumn)
(fact (select :column p2 72) => firstColumn)

(fact (select :column p2 1) => secondColumn)
(fact (select :column p2 28) => secondColumn)
(fact (select :column p2 73) => secondColumn)

(fact (select :column p2 8) => lastColumn)
(fact (select :column p2 35) => lastColumn)
(fact (select :column p2 80) => lastColumn)

(fact (select :square p2 0) => upperLeftSquare)
(fact (select :square p2 10) => upperLeftSquare)
(fact (select :square p2 20) => upperLeftSquare)

(fact (select :square p2 33) => middleRightSquare)
(fact (select :square p2 43) => middleRightSquare)
(fact (select :square p2 53) => middleRightSquare)

(fact (select :square p2 60) => bottomRightSquare)
(fact (select :square p2 61) => bottomRightSquare)
(fact (select :square p2 62) => bottomRightSquare)
(fact (select :square p2 69) => bottomRightSquare)
(fact (select :square p2 70) => bottomRightSquare)
(fact (select :square p2 71) => bottomRightSquare)
(fact (select :square p2 78) => bottomRightSquare)
(fact (select :square p2 79) => bottomRightSquare)
(fact (select :square p2 80) => bottomRightSquare)

(fact (solve p1) => nil)
(fact (solve p3) => s3)
(fact (solve p4) => s4)
(fact (solve p5) => s5)
(fact (solve p6) => s6)

(fact (count s3) => 81)
(fact (count s4) => 81)
(fact (count s5) => 81)
(fact (count s6) => 81)
