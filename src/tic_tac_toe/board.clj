(ns tic-tac-toe.board)

(declare players)

(defn include? [coll item]
  (some #(= item %) coll))

(defn pretty-print [x]
  (cond (> x 7) nil
        (= 2 (mod x 3)) "\n-----------\n"
        :else "|"))

(defn print-board [board]
  (apply str
         (map
           #(str " " (board %) " " (pretty-print %))
           (range 9))))

(defn human [board mark]
  (println)
  (println (print-board board))
  (println)
  (println "Pick your spot (1-9): ")
  (flush)
  (let [input (read-line)]
    (if (and input (include? (range 1 10) input))
      (dec input)
      (recur board mark))))

(defn get-players
  []
  (println "  1- Human (X) vs. Human (O)")
  (println)
  (let [input (read-line)
        _ (prn input)]
    (if (and input (include? (range 1 3) (Integer/parseInt input)))
      (players input)
      (recur))))

(def players
  {1 [{:player-mark "X" :mover human} {:player-mark "O" :mover human}]})

(defn create-board
  []
  (println "Use numbers accordingly to play")
  (println (print-board (vec (range 1 10))))
  (let [[playerOne playerTwo] (get-players)]
    (println playerOne)
    (println playerTwo)))