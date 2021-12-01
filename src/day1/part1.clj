(ns day1.part1)

(def small-input
  [199
   200
   208
   210
   200
   207
   240
   269
   260
   263])

(defn count-depth-increases [input]
  (filter (fn [list]
            (> (second list) (first list))) input)
  )


(comment
  ;answer is 7
  (count-depth-increases small-input)
  )