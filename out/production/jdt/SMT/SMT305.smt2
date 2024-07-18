(declare-fun day () Int)
(declare-fun month () Int)
(declare-fun year () Int)
(assert ( > day 1 ))
(assert ( > month 1 ))
(assert ( > year 1 ))
(assert ( < day 31 ))
(assert ( < month 12 ))
(assert ( < year 2020 ))
(assert ( = month 1 ))
(assert ( > day 31 ))
(assert (or ( > month 4 ) ( < month 4 )) )
(assert (or ( > month 6 ) ( < month 6 )) )
(assert ( = month 9 ))
(assert ( > day 30 ))
(assert ( = month 2 ))
(assert ( >= day 28 ))
(assert ( = month 2 ))
(assert ( = day 29 ))
(check-sat)
(get-model)
(exit)