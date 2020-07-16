package sim.real

import sim.base.CachedElement
import sim.base.Value
import sim.gates.andg
import sim.gates.norg

class SRLatch(
  val set: Value,
  val reset: Value,
  val en: Value
) : CachedElement(true) {
  val q get() = output
  override fun compute(cache: Value): Value {
	// https://www.allaboutcircuits.com/uploads/articles/gated-sr-latch-truth-table.jpg
	val q = cache // previous value
	val and1 = andg(set, en)
	val and2 = andg(reset, en)
	return norg(and2, norg(q, and1))
  }
}