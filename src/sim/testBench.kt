package sim

import sim.base.ConstValue
import sim.base.Value
import sim.complex.mux2
import sim.gates.*

fun main() {
  val A = ConstValue(true)
  val B = ConstValue(false)
  val C = ConstValue(false)

  val res = andg(

	org(A.not, B, C),
	notg(B),
	xorg(B, A)

  )

  println("res: " + res.get())

  println("res mux: " + mux2(Value.ONE, A, B))
}
