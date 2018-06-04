(ns tic-tac-toe.computer
  (:use [tic-tac-toe.game :only [winner full? next-mark empty-squares]]))


(declare memoized-best-scored-board)

(defn make-score-board [score move]
  {:score score :move move})
