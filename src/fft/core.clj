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

(defn fft [samples] (dft samples))
