package sim.real

import sim.base.SingleCachedElement
import sim.base.Value
import sim.gates.and
import sim.gates.nor
import sim.gates.not

class DLatch(
	val data: Value,
	val en: Value
) : SingleCachedElement() {
	override val title = "D-Latch"

	val q get() = output

	override fun compute(): Value {
		// https://www.allaboutcircuits.com/uploads/articles/internal-logic-d-latch.jpg
		val q = cache // previous value
		val and1 = and(data, en)
		val and2 = and(not(data), en)
		return nor(and2, nor(q, and1))
	}
}
