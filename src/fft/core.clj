(ns fft.core
  (:use [complex.core])
  (:require [clojure.math.numeric-tower :as math]))

(defn dft
  [smpls]
  (def len (count smpls))
  (vec
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
                         len)
                       idx outerIdx)))))
            smpls))
        )
      smpls
      ))
  )

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
