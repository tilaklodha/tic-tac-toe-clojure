(ns tic-tac-toe.game-test
  (:use [clojure.test])
  (:use [tic-tac-toe.game :refer :all]))

(deftest create-board
  (testing "it creates empty board"
    (is (= [" " " " " "
            " " " " " "
            " " " " " "]
           (make-board)))))