(ns tic-tac-toe.game)

(def winning-sets
  [[0 1 2], [3 4 5], [6 7 8], [0 3 6], [1 4 7], [2 5 8], [0 4 8], [2 4 6]])

(defn- winner-exists-on-spots? [board spaces]
  (and
    (apply = (map #(board %) spaces))
    (not (= " " (board (first spaces))))))

(defn winner-on-one-set [board set]
  (if (winner-exists-on-spots? board set)
    (board (first set))
    nil))

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

(defn winner
  [board]
  (some #(winner-on-one-set board %) winning-sets))

(defn game-finished?
  [board]
  (boolean (or (winner board) (full? board))))

(defn ^:dynamic message
  [winner]
  (if (nil? winner)
    (str "Draw!!")
    (str "Winner is " winner)))

(defn move-on-board
  [board spot player]
  (if (= " " (board spot))
    (assoc board spot player)))

(defn valid-move? [board spot]
  (try (= (board spot) " ")
       (catch Exception e false)))

(defn next-mark
  [mark]
  (if (= "X" mark)
    "O"
    "X"))

(defn- indexed
  [coll]
  (map vector coll
       (iterate inc 0)))

(defn- index-filter
  [pred coll]
  (for [[spot i]
        (indexed coll) :when
        (pred spot)]
    i))

(defn empty-squares [board]
  (index-filter empty-square? board))
