package sim.complex

import sim.base.Element
import sim.base.SingleCachedElement
import sim.base.Value
import sim.base.mut

/** posedge dff */
class DFlipFlop(
	val data: Value,
	val clock: Value
) : SingleCachedElement() {
	val q get() = output

	private val prevClock = mut(false)

	override fun eval(time: Long) {
		(data as? Element)?.eval(time)
		(clock as? Element)?.eval(time)
		super.eval(time)
	}

	override fun compute(): Value {
		val clk = clock.get()
		val prevClk = prevClock.get()
		val posedge = !prevClk && clk
		prevClock.set(clk)
		return if (posedge) data else cache
	}
}
