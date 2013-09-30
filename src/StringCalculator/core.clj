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


(defn math-operation
  [argString operation]
     (def argumentVector (parseArguments argString))
     (def numberVector (split (getStringOfNumbers argString argumentVector) (getDelimiter argumentVector)))
     (reduce operation (map read-string numberVector)))


(defn add
  ([] 0)
  ([argString]
     (math-operation argString (fn [a b] (+ a b)))))
     
(defn subtract
  ([] 0)
  ([argString]
     (math-operation argString (fn [a b] (- a b)))))

(defn divide
  ([] 0)
  ([argString]
     (math-operation argString (fn [a b] (/ a b)))))

(defn multiply
  ([] 0)
  ([argString]
     (math-operation argString (fn [a b] (* a b)))))

(defn parse-math
  [stringArg]
  (def matched-group (re-find (re-pattern "\\(([a-z0-9\\s]*)\\)") stringArg))
  (cond 
   (= matched-group nil) nil
   :else (get matched-group 1)))

(defn do-operation
  [expression]
  (def expression-parts (re-find #"(\w+)\s+(\d+\s+\d+)" expression))
  (def operation (get expression-parts 1))
  (def arguments (get expression-parts 2))
  (cond
   (= operation "add") (add arguments)
   (= operation "subtract") (subtract arguments)
   (= operation "divide") (divide arguments)
   (= operation "multiply") (multiply arguments)))


(defn math
  [stringArg]
  (def expression (parse-math stringArg))
  (cond
   (= expression nil) 0
   :else (do-operation expression)))



;if ( create node
;if ) calculate result of node
;iterate
;
;(add 3 (add(4 4))
