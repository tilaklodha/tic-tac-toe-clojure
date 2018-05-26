(ns tic-tac-toe.board)

(defn pretty-print [x]
  (cond (> x 7) nil
        (= 2 (mod x 3)) "\n-----------\n"
        :else "|"))

(defn print-board [board]
  (apply str
         (map
           #(str " " (board %) " " (pretty-print %))
           (range 9))))

(defn create-board
  []
  (println (print-board (vec (range 1 10)))))