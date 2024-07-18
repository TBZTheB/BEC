(declare-fun day () Int)
(declare-fun month () Int)
(declare-fun year () Int)
(assert ( > day 1 ))
(assert ( > month 1 ))
(assert ( > year 1 ))
(assert ( < day 31 ))
(assert ( < month 12 ))
(assert ( < year 2020 ))
(assert (or ( > month 1 ) ( < month 1 )) )
(assert ( = month 3 ))
(assert ( > day 31 ))
(assert ( = month 4 ))
(assert ( > day 30 ))
(assert ( = month 2 ))
(assert ( < day 28 ))
(check-sat)
(get-model)
(exit)