(ns tic-tac-toe.game-test
  (:use [clojure.test])
  (:use [tic-tac-toe.game :refer :all]))

(deftest create-board
  (testing "it creates empty board"
    (is (= [" " " " " "
            " " " " " "
            " " " " " "]
           (make-board)))))

(deftest check-empty-spot
  (testing "return true if spot is empty"
    (let [board (make-board)]
      (is (= true (empty-square? board 0)))))

  (testing "return false if spot is not empty"
    (let [board [" " " " " "
                 " " " " " "
                 " " " " "X"]]
      (is (= false (empty-square? board 8))))))

(deftest check-if-board-is-full-or-not
  (testing "if board is not full"
    (is (= false (full? (make-board)))))

  (testing "if board is full"
    (let [board ["X" "O" "X"
                 "O" "X" "O"
                 "O" "X" "O"]]
      (is (= true (full? board))))))

(deftest prints-winner
  (testing "prints winner or nil"
    (let [board ["X" "X" "X"
                 "O" "O" " "
                 " " " " " "]
          winner-set [0 1 2]
          non-winner-set [4 6 8]]
      (is (= "X" (winner-on-one-set board winner-set)))
      (is (= nil (winner-on-one-set board non-winner-set)))))

  (testing "prints X when X wins for all combinations"
    (are [board] (= "X" (winner board))
                 ["X" "X" "X"
                  "O" "O" " "
                  " " " " " "]
                 ["O" "O" " "
                  "X" "X" "X"
                  " " " " " "]
                 ["O" "O" " "
                  " " " " " "
                  "X" "X" "X"]
                 ["X" "O" " "
                  "X" "O" " "
                  "X" " " " "]
                 ["O" "X" " "
                  "O" "X" " "
                  " " "X" " "]
                 ["O" " " "X"
                  "O" " " "X"
                  " " " " "X"]
                 ["X" " " " "
                  "O" "X" "O"
                  " " " " "X"]
                 [" " " " "X"
                  "O" "X" "O"
                  "X" " " " "])))

(deftest move-player-on-board
  (testing "move X on board"
    (let [board ["X" "X" "X"
                 "O" "O" " "
                 " " " " " "]]
      (is (= ["X" "X" "X"
              "O" "O" "X"
              " " " " " "] (move-on-board board 5 "X"))))))

(deftest game-over-or-not
  (testing "if board has winner"
    (let [board ["X" "X" "X"
                 "O" "O" " "
                 " " " " " "]]
      (is (= true (game-over? board)))))

  (testing "if board is full"
    (let [board ["X" "O" "X"
                 "O" "O" "X"
                 "X" "X" "O"]]
      (is (= true (game-over? board)))))

  (testing "if-board-is-empty"
    (let [board ["X" "X" "O"
                 "O" "X" " "
                 " " " " " "]]
      (is (= false (game-over? board))))))

(deftest check-valid-move
  (testing "if move is valid"
    (let [board ["X" "X" "X"
                 "O" "O" " "
                 " " " " " "]]
      (is (= true (valid-move? board 5)))))

  (testing "if move is invalid"
    (let [board ["X" "X" "X"
                 "O" "O" " "
                 " " " " " "]]
      (is (= false (valid-move? board 4))))))