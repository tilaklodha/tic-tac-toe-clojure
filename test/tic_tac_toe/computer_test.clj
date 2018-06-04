(ns tic-tac-toe.computer-test
  (:use [clojure.test :refer :all]
        [tic-tac-toe.computer :refer :all]
        [tic-tac-toe.game :only [make-board]]))

(deftest make-score-board-for-move
  (testing "generate score with move"
    (is (= {:score 10 :move 2} (make-score-board 10 2)))))

