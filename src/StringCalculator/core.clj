(ns StringCalculator.core
  (:use clojure.string))


(defn add
  ([] 
     0)
  ([argString]
     (def argsVector (split argString #"(\s+)"))
     (reduce + (map read-string argsVector))))
