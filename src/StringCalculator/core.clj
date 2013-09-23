(ns StringCalculator.core
  (:use clojure.string))


(defn parseArguments
  [argString]
  (re-find #"//(.*)\n(.*)" argString))
  
(defn getDelimiter
  [arguments]
  (java.util.regex.Pattern/compile (cond
                                    (= arguments nil) "\\s+"
                                    :else (get arguments 1))))

(defn getStringOfNumbers
  [argString args]
  (cond
   (= args nil) argString
   :else (get args 2)))

(defn add
  ([] 0)
  ([argString]
     (def args (parseArguments argString))
     (def delimeter (getDelimiter args))
     (def stringOfNumbers (getStringOfNumbers argString args))
     (def argsVector (split stringOfNumbers delimeter))
     (reduce + (map read-string argsVector))))

     
