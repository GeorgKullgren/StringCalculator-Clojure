(ns StringCalculator.core)


(defn add
  ([] 
     0)
  ([argString]
     (read-string argString)))

