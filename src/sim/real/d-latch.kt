package sim.real

import sim.base.CachedElement
import sim.base.Value
import sim.gates.andg
import sim.gates.norg
import sim.gates.not

class DLatch(
  val data: Value,
  val en: Value
) : CachedElement(true) {
  val q get() = output
  override fun compute(cache: Value): Value {
	// https://www.allaboutcircuits.com/uploads/articles/internal-logic-d-latch.jpg
	val q = cache // previous value
	val and1 = andg(data, en)
	val and2 = andg(data.not, en)
	return norg(and2, norg(q, and1))
  }
}