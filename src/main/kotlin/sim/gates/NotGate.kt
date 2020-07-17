package sim.gates

import sim.base.CachedElement
import sim.base.Value

class NotGate(override val input: Value) : CachedElement(false) {
	override fun compute(cache: Value): Value {
		return Value.from(!input.get())
	}
}
