(ns tic-tac-toe.board-test
  (:require [clojure.test :refer :all]
            [tic-tac-toe.board :refer :all]))

(deftest create-new-board
  (testing "it creates a new board"
    (is (=  " 1 | 2 | 3 \n-----------\n 4 | 5 | 6 \n-----------\n 7 | 8 | 9 " (print-board (vec (range 1 10)))))))

(deftest test-printing-in-new-line
  (testing "it prints |"
    (is (= "|" (pretty-print 1)))
    (is (= "|" (pretty-print 0)))
    (is (= "|" (pretty-print 3)))
    (is (= "|" (pretty-print 4)))
    (is (= "|" (pretty-print 6)))
    (is (= "|" (pretty-print 7))))

  (testing "it prints in new line"
    (is (= "\n-----------\n" (pretty-print 2)))
    (is (= "\n-----------\n" (pretty-print 5))))

  (testing "it does noting"
    (is (= nil (pretty-print 8)))))
