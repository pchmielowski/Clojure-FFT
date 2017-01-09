(ns fft.core
  (:use [complex.core])
  (:require [clojure.math.numeric-tower :as math]))

(def i (complex 0 1))

(defn dft
  [samples]
  (def length (count samples))
  (map-indexed
    (fn [outerIdx value]
      (reduce
        +
        (map-indexed
          (fn
            [idx sample]
            (* sample
               (exp
                 (-
                  (*
                   (/
                    (* 2 Math/PI i)
                    length)
                   idx outerIdx)))))
          samples))
      )
    samples
    )
  )

(defn fft
  [samples]
  (def length (count samples))
  (defn rotate
    [idx sample]
    (*
     sample
     (exp
       (/
        (* -2 Math/PI i idx)
        length))))
  (defn evn [input] (take-nth 2 input))
  (defn odd [input] (take-nth 2 (rest input)))
  (defn twotimes [input] (reduce into (repeat 2 input)))
  (if
    (= length 1)
    (map (fn [s] (complex s)) samples)
    (map
      +
      (twotimes
        (dft (evn samples)))
      (map-indexed
        rotate
        (twotimes
          (dft (odd samples)))))))