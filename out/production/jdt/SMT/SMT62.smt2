(declare-fun averageGrade () Int)
(assert ( > averageGrade 90 ))
(assert ( < averageGrade 100 ))
(check-sat)
(get-model)
(exit)