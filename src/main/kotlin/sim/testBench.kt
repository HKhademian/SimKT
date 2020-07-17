package sim

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import sim.base.VariableValue
import sim.complex.DFlipFlop

//fun main() {
//  val A = ConstValue(true)
//  val B = ConstValue(false)
//  val C = ConstValue(false)
//
//  val res = andg(
//
//	org(A.not, B, C),
//	notg(B),
//	xorg(B, A)
//
//  )
//
//  println("res: " + res.get())
//
//  println("res mux: " + mux2(Value.ONE, A, B))
//}

fun main() {
	val data = VariableValue(false)
	val clock = VariableValue(false)
	val dff = DFlipFlop(data, clock)

	runBlocking {

		async {
			while (true) {
				clock.set(!clock.get())
				println("clock tick")
				delay(1000)
			}
		}

		while (true) {
			val value = dff.eval().get()
			data.set(!value)
			println(value)
			delay(100)
		}

	}
}
