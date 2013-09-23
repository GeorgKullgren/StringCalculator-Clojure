(ns StringCalculator.core
  (:use clojure.string))



;;  (def stringParts (re-find #"ww(.)\\s(.*)" argString))

(defn add
  ([] 
     0)
  ([argString]
     (def stringParts (re-find #"//(.*)\n(.*)" argString))
     (def delimeter (java.util.regex.Pattern/compile (cond
                                                      (= stringParts nil) "\\s+"
                                                      :else (get stringParts 1))))
     (def numberString (cond
                        (= stringParts nil) argString
                        :else (get stringParts 2)))
     (def argsVector (split numberString delimeter))
     (reduce + (map read-string argsVector))))

