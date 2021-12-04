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

(defn calculate [depth horizontal aim commands]
  (if (empty? commands)
    (* horizontal depth)
    (let [move (first (first commands))
          amount (Integer/parseInt (second (first commands)))]
      (case move
        "forward" (calculate (+ depth (* aim amount)) (+ horizontal amount) aim (rest commands))
        "up" (calculate depth horizontal (- aim amount) (rest commands))
        "down" (calculate depth horizontal (+ aim amount) (rest commands))))))

(defn part-two []
  (calculate 0 0 0 (read-position "src/day2/input.txt")))

(comment
  (map #(aim % {:horizontal 0 :depth 0 :aim 0}) (read-position "src/day2/input.txt"))

  )