(ns StringCalculator.core-test
  (:require [StringCalculator.core :refer :all])
  (:use [midje.sweet]))

(fact "Test add with no arguments returns 0"
      (add) => 0)

(tabular
 (fact "Test add"
       (add ?string) => ?result)
 ?string ?result
  "2"     2
  "4"     4
)

