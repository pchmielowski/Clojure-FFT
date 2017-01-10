(ns fft.core
  (:use [complex.core])
  (:require [clojure.math.numeric-tower :as math]))

(def i (complex 0 1))

(defn dft
  [smpls]
  (def len (count smpls))
  (vec
    (map
      (fn [k]
        (reduce
          +
          (map-indexed
            (fn
              [n sample]
              (* sample
                 (exp (- (*
                           (/
                             (* 2 Math/PI i)
                             len)
                           n k)))))
            smpls)))
      (range len))))

(defn make-rotating-function
  [N]
  (defn rotate-sample
    [idx sample]
    (*
      sample
      (exp
        (/
          (* -2 Math/PI i idx)
          N)))))

(defn twotimes [input]
  (reduce into (repeat 2 input)))

(defn rotate-vector
  [vector]
  (vec
    (map-indexed
      (make-rotating-function (count vector))
      vector)))

(defn evn [input] (take-nth 2 input))
(defn odd [input] (take-nth 2 (rest input)))

(defn fft
  [samples]
  (def length (count samples))
  (vec
    (if
      (= length 1)
      (map (fn [s] (complex s)) samples)
      (map
        +
        (twotimes
          (fft (evn samples)))
        (rotate-vector
          (twotimes
            (fft (odd samples))))))))
