package sim.gates

import sim.base.CachedElement
import sim.base.MultiInputElement
import sim.base.Value
import sim.base.toValue

class XorGate(override val inputs: List<Value>) : CachedElement(false), MultiInputElement {
	constructor(vararg inputs: Value) : this(listOf(*inputs))

	override fun compute(cache: Value): Value {
		var res = false
		for (inp in inputs)
			res = res xor inp.get()
		return res.toValue()
	}
}
