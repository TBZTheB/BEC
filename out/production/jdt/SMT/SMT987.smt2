(declare-fun day () Int)
(declare-fun month () Int)
(declare-fun year () Int)
(assert ( <= year 1900 ))
(assert ( > year 1900 ))
(assert ( <= 1900 year ))
(assert ( <= 1901 year ))
(assert ( < 0 month ))
(assert ( < 1 month ))
(check-sat)
(get-model)
(exit)