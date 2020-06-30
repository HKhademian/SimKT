package sim.gate

import sim.ComputeValue
import sim.MultiInputElement
import sim.Value

class AndGate(override val inputs: List<Value>) : MultiInputElement {
	constructor(vararg inputs: Value) : this(listOf(*inputs))

	override val output = ComputeValue {
		// TODO: cache for each call
		for (inp in inputs)
			if (!inp.get()) return@ComputeValue false      // NOTE: for better performance
		return@ComputeValue true
	}
}

