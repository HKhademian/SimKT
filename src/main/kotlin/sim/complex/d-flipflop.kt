package sim.complex

import sim.base.CachedElement
import sim.base.Value
import sim.base.Variable

/** posedge dff */
class DFlipFlop(
	val data: Value,
	val clock: Value
) : CachedElement(true) {
	val q get() = output

	private val prevClock = Variable(false)

	override fun compute(cache: Value): Value {
		val clk = clock.get()
		val prevClk = prevClock.get()
		val posedge = !prevClk && clk
		prevClock.set(clk)
		return if (posedge) data else cache
	}
}
