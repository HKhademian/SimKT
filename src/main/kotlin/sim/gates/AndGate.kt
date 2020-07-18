package sim.gates

import sim.base.*

class AndGate(override val inputs: Bus) : CachedElement(false), MultiInputElement {
	constructor(vararg inputs: Value) : this(bus(*inputs))

	override val title: String
		get() = "AndGate"

	override fun compute(cache: Value): Value {
		for (inp in inputs)
			if (!inp.get()) return Value.ZERO      // NOTE: for better performance
		return Value.ONE
	}
}
