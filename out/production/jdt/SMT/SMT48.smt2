(declare-fun averageGrade () Int)
(assert ( > averageGrade 90 ))
(assert ( >= averageGrade 100 ))
(assert ( > averageGrade 80 ))
(assert ( >= averageGrade 90 ))
(assert ( <= averageGrade 70 ))
(assert ( <= averageGrade 60 ))
(assert ( > averageGrade 0 ))
(assert ( < averageGrade 60 ))
(check-sat)
(get-model)
(exit)