(ns tic-tac-toe.board
  (:use [tic-tac-toe.game :refer :all]))

(declare players)

(defn include? [coll item]
  (some #(= item %) coll))

(defn verify-input []
  (try (Integer/parseInt (read-line))
       (catch NumberFormatException e nil)))

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
  (let [input (verify-input)]
    (if (and input (include? (range 1 10) input))
      (dec input)
      (recur board mark))))

(defn get-players
  []
  (println "1- Human (X) vs. Human (O)")
  (println "2- Computer (X) vs. Human (O)")
  (println "3- Human (X) vs. Computer (O)")
  (println "4- Computer (X) vs. Computer (O)")
  (println)
  (let [input (verify-input)]
    (if (and input (include? (range 1 5) input))
      (players input)
      (recur))))

(defn get-valid-move [board player]
  (let [spot ((:mover player) board (:mark player))]
    (if (valid-move? board spot)
      (move-on-board board spot (:mark player))
      (recur board player))))

(def players
  {1 [{:mark "X" :mover human} {:mark "O" :mover human}]
   2 [{:mark "X" :mover computer} {:mark "O" :mover human}]
   3 [{:mark "X" :mover human} {:mark "O" :mover computer}]
   4 [{:mark "X" :mover computer} {:mark "O" :mover computer}]})

(defn start-game
  ([]
    (println "Use numbers accordingly to play")
    (println (print-board (vec (range 1 10))))
    (let [[playerOne playerTwo] (get-players)]
      (start-game (make-board) playerOne playerTwo)))
  ([board playerOne playerTwo]
   (if (game-finished? board)
     (do
       (println "\n" (message (winner board)) "\n")
       (println (print-board board)))
     (do
       (println (str "Turn " (:mark playerOne)))
       (recur
         (get-valid-move board playerOne) playerTwo playerOne)))))