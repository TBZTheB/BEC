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
(assert (or ( > month 3 ) ( < month 3 )) )
(assert (or ( > month 5 ) ( < month 5 )) )
(assert ( = month 7 ))
(assert ( > day 31 ))
(assert (or ( > month 4 ) ( < month 4 )) )
(assert ( = month 6 ))
(assert ( <= day 30 ))
(check-sat)
(get-model)
(exit)