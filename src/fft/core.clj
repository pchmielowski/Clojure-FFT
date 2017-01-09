(ns fft.core
  (:use [complex.core])
  (:require [clojure.math.numeric-tower :as math]))

(defn show [input ch]
  (doseq [x input] (printf (str ch "%s\n") x))
  (println))
(def i (complex 0 1))

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
    (printf "%s of %s %s\n" idx N sample)
    (*
      sample
      (exp
        (/
          (* -2 Math/PI i idx)
          N)))))

(defn twotimes [input]
  (printf "in: %s\n" input)
  (def
    retval
    (reduce into (repeat 2 input)))
  (printf "out: %s\n" retval)
  retval)

(defn rotate-vector
  [vector N]
  (show vector "")
  (map-indexed
    (make-rotating-function N)
    vector))

(defn evn [input] (take-nth 2 input))
(defn odd [input] (take-nth 2 (rest input)))

(defn fft
  [samples]
  (def length (count samples))
  (if
    (= length 1)
    (map (fn [s] (complex s)) samples)
    (map
      +
      (twotimes
        (dft (evn samples)))
      (rotate-vector
        (twotimes
          (dft (odd samples)))
        length))))