package sim.real

import sim.base.Value
import sim.base.Variable
import sim.gates.andg
import sim.gates.org
import sim.gates.xorg

/** attention: return values are lazy calculated */
fun halfAdder(A: Value, B: Value, res: Variable, carry: Variable) {
	res.set(xorg(A, B))
	carry.set(andg(A, B))
}

/** attention: return values are lazy calculated */
fun fullAdder(A: Value, B: Value, C: Value, res: Variable, carry: Variable) {
	val s1 = Variable()
	val c1 = Variable()
	val c2 = Variable()
	halfAdder(A, B, s1, c1)
	halfAdder(C, s1, res, c2)
	carry.set(org(c1, c2))
}

// test adders
fun main() {
	val A = Variable(true)
	val B = Variable(true)
	val res = Variable()
	val car = Variable()
	halfAdder(A, B, res, car)
	println("res: $res    carry: $car")
	A.set(false)
	println("res: $res    carry: $car")
}
