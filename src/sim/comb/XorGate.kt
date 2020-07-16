package sim.comb

import sim.ComputeValue
import sim.MultiInputElement
import sim.Value

class XorGate(override val inputs: List<Value>) : MultiInputElement {
	constructor(vararg inputs: Value) : this(listOf(*inputs))

	override val output = ComputeValue {
		// TODO: cache for each call
		var res = false
		for (inp in inputs)
			res = res xor inp.get()
		return@ComputeValue res
	}
}
