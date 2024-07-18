(declare-fun day () Int)
(declare-fun month () Int)
(declare-fun year () Int)

(check-sat)
(get-model)
(exit)