(declare-fun age () Int)
(declare-fun distance () Int)
(assert ( > age 4 ))
(assert ( >= age 14 ))
(assert ( > age 15 ))
(assert ( >= distance 10 ))
(assert ( > distance 10 ))
(assert ( < age 60 ))
(check-sat)
(get-model)
(exit)