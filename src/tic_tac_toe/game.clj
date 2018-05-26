(ns tic-tac-toe.game)

(defn make-board [] (vec (repeat 9 " ")))

(defn empty-square? [board spot]
  (= " " (board spot)))