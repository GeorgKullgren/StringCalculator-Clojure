(ns StringCalculator.core-test
  (:require [StringCalculator.core :refer :all])
  (:use [midje.sweet]))

(fact "Test parsing argument string with optional delimeter"
      (parseArguments "//ff\n55ff11") => ["//ff\n55ff11" "ff" "55ff11"])

(fact "Test parsing default argument string"
      (parseArguments "33 22") => nil)

(fact "Test get delimiter from parse result"
      (getDelimiter ["//ff\n55ff11" "ff" "55ff11"]) => #"ff")

(fact "Test get delimiter from empty parse result returns default \"\\s+\""
      (getDelimiter nil) => #"\s+")

(fact "getStringOfNumbers returns default if arglist is empty"
      (getStringOfNumbers "string" nil) => "string")

(fact "getStringOfNumbers returns second element in vector if it exists"
      (getStringOfNumbers "string" ["first" "second" "third"]) => "third")

(fact "Test add with no arguments returns 0"
      (add) => 0)


(tabular
 (fact "Test add"
       (add ?string) => ?result)
 ?string ?result
  "22"    22
  "4"     4
  "1 2"   3
  "23 7"  30
 "3 4 7"  14
)

(tabular
 (fact "Test add with optional delimeter"
       (add ?string) => ?result)

 ?string       ?result
 "//f\n3f2"    5
 "//gh\n3gh2"  5
 "//f g\n3f g2" 5
)

(tabular
 (fact "Test subtraction"
       (subtract ?string) => ?result)
      
 ?string       ?result
 "10 4"        6
 "15 5 3"      7
 "//gh\n3gh2"  1
) 

(tabular
 (fact "Test division"
       (divide ?string) => ?result)
      
 ?string       ?result
 "10 5"        2
 "15 5 3"      1
 "//gh\n6gh2"  3
) 

(tabular
 (fact "Test multiplication"
       (multiply ?string) => ?result)
      
 ?string       ?result
 "10 5"        50
 "2 5 3"       30
 "//gh\n6gh2"  12
) 

(tabular
 (fact "Tokenize string"
   (tokenize-string ?argument) => ?result)
 
 ?argument       ?result
 "add 5 7"       ["add" "5" "7"]
 "(add 5 7)"     ["(" "add" "5" "7"]
)

(tabular
 (fact "Parse grouped expression"
   (math ?string) => ?result)
 
 ?string          ?result
 "(add 4 3)"       7
 "(subtract 4 3)"  1
 "(multiply 4 3)"  12
 "(divide 15 3)"   5
 "(add 4 (add 1 5))" 10
 "add (subtract 10 9) (add 3 3)"  7
 "(add (subtract 10 9) (add 3 3))"  7
)


