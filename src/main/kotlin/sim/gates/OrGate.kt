package sim.gates

import sim.base.Bus
import sim.base.CachedElement
import sim.base.MultiInputElement
import sim.base.Value

@PublishedApi
internal class OrGate(override val inputs: Bus) : CachedElement(false), MultiInputElement {
	override val title: String
		get() = "OrGate"

	override fun compute(cache: Value): Value {
		for (inp in inputs)
			if (inp.get()) return Value.ONE // NOTE: for better performance
		return Value.ZERO
	}
}
