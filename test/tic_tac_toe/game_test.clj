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