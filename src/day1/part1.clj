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

(defn sliding-window)


(comment
  (count-depth-increases test-input)
  (count-depth-increases (read-numeric-file "src/day1/input.txt"))
  )