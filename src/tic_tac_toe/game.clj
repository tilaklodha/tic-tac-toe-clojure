(ns tic-tac-toe.game)

(defn make-board
  []
  (vec (repeat 9 " ")))

(defn empty-square?
  ([board]
    (= " " board))
  ([board spot]
   (= " " (board spot))))

(defn full?
  [board]
  (not-any? empty-square? board))