(ns day2.dive
  (:require [clojure.string :as str]))

(defn read-position [path]
  "Creates output like ([forward 4] [down 4]) "
  (->> path
       (slurp)
       (str/split-lines)
       (map #(str/split % #" "))))

(defn move  [command map]
  "Takes a map {:horizontal 5 :depth 3} and returns new position"
  (let [dir (first command)
        amount (Integer/parseInt (second command))]
    (cond
      (= dir "forward")
      {:horizontal (+ amount (map :horizontal))
       :depth      (map :depth)}
      (= dir "down")
      {:horizontal (map :horizontal)
       :depth      (+ amount (map :depth))}
      (= dir "up")
      {:horizontal (map :horizontal)
       :depth      (- (map :depth) amount)})))


(defn calculate-position [commands]
  (for [command commands]
    (move command {:horizontal 0 :depth 0})))

(defn part-one []
  (let [directions (calculate-position (read-position "src/day2/input.txt"))
        horizontal (apply + (map :horizontal directions))
        depth (apply + (map :depth directions))]
    (* horizontal depth)))

(comment

  (read-position "src/day2/input.txt")

  )