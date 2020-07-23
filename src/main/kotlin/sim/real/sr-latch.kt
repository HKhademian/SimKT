package sim.real

import sim.base.SingleCachedElement
import sim.base.Value
import sim.base.and
import sim.base.nor

class SRLatch(
	val set: Value,
	val reset: Value,
	val en: Value
) : SingleCachedElement() {
	override val title = "SR-Latch"

	val q get() = output

	override fun compute(): Value {
		// https://www.allaboutcircuits.com/uploads/articles/gated-sr-latch-truth-table.jpg
		val q = cache // previous value
		val and1 = and(set, en)
		val and2 = and(reset, en)
		return nor(and2, nor(q, and1))
	}
}
