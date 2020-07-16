package sim

import sim.base.ConstValue
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
}
