package sim.real

import sim.base.Value
import sim.gates.andg
import sim.gates.org
import sim.gates.xorg


/** attention: return values are lazy calculated */
fun halfAdder(A: Value, B: Value): List<Value> {
	val res = xorg(A, B)
	val carry = andg(A, B)
	return listOf(res, carry)
}

/** attention: return values are lazy calculated */
fun fullAdder(A: Value, B: Value, C: Value): List<Value> {
	val (s1, c1) = halfAdder(A, B)
	val (s2, c2) = halfAdder(C, s1)
	val carry = org(c1, c2)
	return listOf(s2, carry)
}
