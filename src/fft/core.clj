(ns fft.core
  (:use [complex.core])
  (:require [clojure.math.numeric-tower :as math]))

(def i (complex 0 1))

(defn fft
  [samples]
  (def length (count samples))
  [(reduce
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
                idx 0)
               ))))
       samples))])
