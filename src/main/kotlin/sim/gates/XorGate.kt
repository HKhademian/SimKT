package sim.gates

import sim.base.*

class XorGate(override val inputs: Bus) : CachedElement(false), MultiInputElement {
	constructor(vararg inputs: Value) : this(bus(*inputs))

	override val title: String
		get() = "XorGate"

	override fun compute(cache: Value): Value {
		var res = false
		for (inp in inputs)
			res = res xor inp.get()
		return res.toValue()
	}
}
