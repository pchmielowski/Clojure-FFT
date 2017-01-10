(ns fft.core-test
  (:use [complex.core]
        [clojure.algo.generic :only (root-type)])
  (:require [clojure.test :refer :all]
            [fft.core :refer :all]
            [clojure.algo.generic.math-functions :as gmf]))

(defn
  cmp
  "Compares collections of Complex numbers"
  [a b]
  (if (not= (count a) (count b))
    (throw (Exception. (format "Lenghts mismatch: %d != %d" (count a) (count b)))))
  (and
    (= (type a) (type b))
    (every?
      true?
      (map-indexed
        (fn [idx itm]
          (equals itm (nth a idx) 0.1))
        b))))

(deftest calculates_fft
  (is (cmp
        (fft [0])
        [(complex 0 0)]))
  (is (cmp
        (fft [1])
        [(complex 1 0)]))
  (is (cmp
        (fft [1 0])
        [(complex 1 0) (complex 1 0)]))
  (is (cmp
        (fft [0 1])
        [(complex 1 0) (complex -1 0)]))
  (is (cmp
        (fft [0 1 2 3])
        [(complex 6 0)
         (complex -2 2)
         (complex -2 0)
         (complex -2 -2)]))
  (is (cmp
        (fft [0 1 2 3 0 1 2 3 0 1 2 3 0 1 2 3])
        [(complex 24 0) (complex 0 0) (complex 0 0) (complex 0 0) (complex -8 8) (complex 0 0) (complex 0 0) (complex 0 0)
         (complex -8 0) (complex 0 0) (complex 0 0) (complex 0 0) (complex -8 -8) (complex 0 0) (complex 0 0) (complex 0 0)]))
  )

(deftest calculates_dft
  (is (cmp
        (dft [0])
        [(complex 0 0)]))
  (is (cmp
        (dft [1])
        [(complex 1 0)]))
  (is (cmp
        (dft [1 0])
        [(complex 1 0) (complex 1 0)]))
  (is (cmp
        (dft [0 1])
        [(complex 1 0) (complex -1 0)]))
  (is (cmp
        (dft [0 1 2 3])
        [(complex 6 0)
         (complex -2 2)
         (complex -2 0)
         (complex -2 -2)]))
  (is (cmp
        (dft [0 1 2 3 0 1 2 3 0 1 2 3 0 1 2 3])
        [(complex 24 0) (complex 0 0) (complex 0 0) (complex 0 0) (complex -8 8) (complex 0 0) (complex 0 0) (complex 0 0)
         (complex -8 0) (complex 0 0) (complex 0 0) (complex 0 0) (complex -8 -8) (complex 0 0) (complex 0 0) (complex 0 0)]))
  )
