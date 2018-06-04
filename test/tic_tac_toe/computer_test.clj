(ns tic-tac-toe.computer-test
  (:use [clojure.test :refer :all]
        [tic-tac-toe.computer :refer :all]
        [tic-tac-toe.game :only [make-board]]))

(deftest make-score-board-for-move
  (testing "generate score with move"
    (is (= {:score 10 :move 2} (make-score-board 10 2)))))

(deftest get-optimal-move
  (testing "computer calculate score if next move wins"
    (let [board [" " "O" " "
                 " " "X" " "
                 " " "O" "X"]
          mark "X"
          depth 0
          move 0]
      (is (= {:score 10 :move 0} (score-board board move mark depth)))))

  (testing "computer calculate score for next move if no win"
    (let [board [" " "O" "X"
                 " " "X" " "
                 " " "O" "X"]
          mark "X"
          depth 0
          move 0]
      (is (= {:score 10 :move 0} (score-board board move mark depth))))))

(deftest test-computer-blocks-player-win
  (are [move board] (= move (computer board "X"))
                    1 [" " " " " "
                       "X" "O" "X"
                       " " "O" " "]
                    4 ["O" " " " "
                       " " " " "X"
                       "X" "O" "O"]
                    4 ["O" " " " "
                       " " " " "X"
                       " " " " "O"]
                    6 ["O" "X" " "
                       " " "X" " "
                       " " "O" "O"]))
