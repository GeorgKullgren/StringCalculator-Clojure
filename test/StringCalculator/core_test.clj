(ns StringCalculator.core-test
  (:require [StringCalculator.core :refer :all])
  (:use [midje.sweet]))

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

