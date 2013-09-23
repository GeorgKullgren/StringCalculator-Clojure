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
     (def argumentVector (parseArguments argString))
     (def numberVector (split (getStringOfNumbers argString argumentVector) (getDelimiter argumentVector)))
     (reduce + (map read-string numberVector))))

     
