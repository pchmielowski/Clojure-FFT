(ns fft.core
  (:use [complex.core]))

(def a (complex 0 1))

(defn write-file []
  (print (real-part (* a a)))
  (print (imaginary-part (* a a)))
  (with-open [w (clojure.java.io/writer "/home/piotrek/txt" :append true)]
    (.write w (real-part (* a a)))))

(write-file)
