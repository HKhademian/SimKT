package sim.gates

import sim.base.CachedElement
import sim.base.MultiInputElement
import sim.base.Value

class AndGate(override val inputs: List<Value>) : CachedElement(false), MultiInputElement {
	constructor(vararg inputs: Value) : this(listOf(*inputs))

	override fun compute(cache: Value): Value {
		for (inp in inputs)
			if (!inp.get()) return Value.ZERO      // NOTE: for better performance
		return Value.ONE
	}
}
