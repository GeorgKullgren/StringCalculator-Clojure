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

;--------------------

(defrecord VectorArgument [arg rest])

(defn tokenize-string
  [string]
  (def normalizedString (replace (replace string "(" "( ") ")" " )"))   
  (split normalizedString (getDelimiter nil)))

(defn getOperation
  [vector]
  (def op (if (= (get vector 0) "(")
            (get vector 1)
            (get vector 0)))
  (def rest (if (= (get vector 0) "(")
              (subvec vector 2)
              (subvec vector 1)))

  (VectorArgument. op rest))

(declare recursiveMath)

(defn getArgument
  [vector]
  (def arg (get vector 0))
  (if (= arg "(")
    (recursiveMath (subvec vector 1))
    (VectorArgument. (read-string arg) (subvec vector 1))))

(defn do-math
  [operation argument1 argument2]
  (def op (cond
           (= operation "add") (fn [a b] (+ a b))
           (= operation "subtract") (fn [a b] (- a b))
           (= operation "divide") (fn [a b] (/ a b))
           (= operation "multiply") (fn [a b] (* a b))
           :else (assert "Illegal operation")))
  (op argument1 argument2))

(defn recursiveMath
  [vector]
  (def operation (getOperation vector))
  (def argument1 (getArgument (:rest operation)))
  (def argument2 (getArgument (:rest argument1)))
  (VectorArgument. (do-math (:arg operation) (:arg argument1) (:arg argument2)) (:rest argument2))
)


(defn math
  [stringArg]
  (def vector (tokenize-string stringArg))
  (:arg (recursiveMath vector)))
