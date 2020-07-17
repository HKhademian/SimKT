package sim.gates

import sim.base.CachedElement
import sim.base.Value
import sim.base.toValue

class NotGate(override val input: Value) : CachedElement(false) {
	override fun compute(cache: Value) =
		(!input.get()).toValue()
}
