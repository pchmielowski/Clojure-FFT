(ns fft.core-test
  (:use [complex.core]
        [clojure.algo.generic :only (root-type)])
  (:require [clojure.test :refer :all]
            [fft.core :refer :all]
            [clojure.algo.generic.math-functions :as gmf]))

(deftest calculates_fft
  (are [x y] (= x y) 
       (fft [0]) [(complex 0 0)]
       ;(fft [1]) [(complex 1 0)]
       ))

      ; (fft [1 0]) [(complex 1 0) (complex  1 0)]
      ; (fft [0 1]) [(complex 1 0) (complex -1 0)]

      ; (fft [0 1 2 3])
      ; [(complex  6  0)
      ;  (complex -2  2)
      ;  (complex -2  0)
      ;  (complex -2 -2)]))

;(gmf/approx= 2.01 2.0 1e-2)
