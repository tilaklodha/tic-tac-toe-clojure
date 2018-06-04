(ns tic-tac-toe.computer
  (:use [tic-tac-toe.game :only [winner full? next-mark empty-squares]]))


(declare memoized-best-scored-board)

(defn make-score-board [score move]
  {:score score :move move})

(defn score-board [board move mark depth]
  (let [next-board (assoc board move mark)]
    (cond
      (= mark (winner next-board)) (make-score-board (- 10 depth) move)
      (full? next-board) (make-score-board 0 move)
      :else (make-score-board
              (- (:score (memoized-best-scored-board
                           next-board
                           (next-mark mark)
                           (inc depth))))
              move))))

(defn- scored-board-compare [a b]
  (if (> (:score b) (:score a)) b a))

(defn- child-scored-boards [board mark depth]
  (map
    #(score-board board % mark depth)
    (empty-squares board)))

(defn- best-scored-board [board mark depth]
  (reduce
    scored-board-compare
    (child-scored-boards board mark depth)))

(def memoized-best-scored-board (memoize best-scored-board))

(defn computer [board mark]
  (let [best-board (memoized-best-scored-board board mark 0)]
    (:move best-board)))
