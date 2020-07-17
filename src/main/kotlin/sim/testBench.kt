package sim

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import sim.base.Value
import sim.base.Variable
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
	val data = Variable(Value.ZERO)
	val clock = Variable(Value.ZERO)
	val dff = DFlipFlop(data, clock)

	runBlocking {

		async {
			while (true) {
				clock.toggle()
				println("clock: $clock")
				delay(1000)
			}
		}

		async {
			while (true) {
				data.toggle()
				println("data: $data")
				delay(3500)
			}
		}

		while (true) {
			dff.eval()
			println("dff: $dff")
			delay(900)
		}


	}
}
