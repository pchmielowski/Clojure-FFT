(ns fft.core
  (:use [complex.core])
  (:require [clojure.math.numeric-tower :as math]))

(def i (complex 0 1))

(defn fft
  [s]
  (def N (count s))
  [(reduce
     +
     (map-indexed
       (fn
         [idx itm]
         (* itm
            (exp
              (-
               (*
                (/
                 (* 2 Math/PI i)
                 N)
                idx 0)
               ))))
       s))])
