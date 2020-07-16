package sim.complex

import sim.base.CachedElement
import sim.base.Value
import sim.base.VariableValue

/** posedge dff */
class DFlipFlop(
  val data: Value,
  val clock: Value
) : CachedElement(true) {
  val q get() = output
  private val prevClock = VariableValue(false)

  override fun compute(cache: Value): Value {
	val clk = clock.get()
	val posedge = !prevClock.get() && clk
	prevClock.set(clk)
	return if (posedge) data else cache
  }
}