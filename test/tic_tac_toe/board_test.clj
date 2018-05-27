(ns tic-tac-toe.board-test
  (:use [clojure.test :refer :all]
            [tic-tac-toe.board :refer :all]
            [tic-tac-toe.game :only [message]]))
(def actual-winner (ref :not-over))

(defn mock-message [winner]
  (dosync (ref-set actual-winner winner)))

(defn make-input [coll]
  (apply str (interleave coll (repeat "\n"))))

(deftest prints-new-board
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

(deftest verify-user-input
  (testing "it return a number"
    (with-redefs [read-line (constantly "4")]
      (is (= 4 (verify-input))))))

(deftest verify-players
  (testing "verify valid players"
    (with-redefs [verify-input (constantly 1)]
      (let [[playerOne playerTwo] (get-players)]
        (is (= "X" (:mark playerOne)))
        (is (= "O" (:mark playerTwo)))))))

(deftest get-valid-move-for-player
  (testing "valid move for player X"
    (let [board ["X" "X" " "
                 "O" "O" " "
                 " " " " " "]
          player {:mark "X" :mover human}]
      (with-redefs [verify-input (constantly 3)]
        (is (= ["X" "X" "X"
                "O" "O" " "
                " " " " " "]) (get-valid-move board player)))))

  (testing "recur valid move for wrong first input"
    (let [board ["X" "X" " "
                 "O" "O" " "
                 " " " " " "]
          player {:mark "X" :mover human}]
      (with-in-str
        (make-input '(10 3))
        (is (= ["X" "X" "X"
                "O" "O" " "
                " " " " " "]) (get-valid-move board player))))))

(deftest full-game
  (binding [message mock-message]
    (with-in-str
      (make-input '(1 1 9 2 8 3))
      (start-game)
      (is (= "X" @actual-winner)))))