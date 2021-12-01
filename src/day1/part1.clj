(ns day1.part1
  (:require [clojure.string :as str]))

(def test-input
  [199
   200
   208
   210
   200                                                      ;; drop
   207
   240
   269
   260                                                      ;; drop
   263])

(defn count-depth-increases [input]
  (->> input
       (partition 2 1 [0])
       (filter (fn [[a b]] (< a b)))
       (count)))

(defn read-numeric-file [path]
  (->> path
       (slurp)
       (str/split-lines)
       (map #(Integer/parseInt %))))

(defn sliding-window [num-vector]
  (->> num-vector
       (partition 3 1 [0])
       (map #(apply + %))
       (count-depth-increases)))


(comment
  (count-depth-increases test-input)
  (count-depth-increases (read-numeric-file "src/day1/input.txt"))
  (sliding-window test-input)
  (map #(apply + %) '((1 2 3) (2 3 4) (3 4 5) (4 5 6)))
  ;; => (6 9 12 15)

  (map #(list (apply + %)) '((1 2 3) (2 3 4) (3 4 5) (4 5 6)))
  ;; => ((6) (9) (12) (15))

  (sliding-window (read-numeric-file "src/day1/input.txt"))
  )