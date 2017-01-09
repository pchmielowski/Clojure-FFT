(ns fft.core
  (:use [complex.core])
  (:require [clojure.math.numeric-tower :as math]))

(defn show [input ch] (doseq [x input] (printf (str ch "%s\n") x)))
(def i (complex 0 1))

(defn dft
  [smpls]
  (def len (count smpls))
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
    )
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

(defn fft
  [samples]
  (def length (count samples))
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
        (make-rotating-function length)
        (twotimes
          (dft (odd samples)))))))