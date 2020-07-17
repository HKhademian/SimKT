package sim.gates

import sim.base.*

class OrGate(override val inputs: Bus) : CachedElement(false), MultiInputElement {
	constructor(vararg inputs: Value) : this(bus(*inputs))

	override fun compute(cache: Value): Value {
		for (inp in inputs)
			if (inp.get()) return Value.ONE // NOTE: for better performance
		return Value.ZERO
	}
}
