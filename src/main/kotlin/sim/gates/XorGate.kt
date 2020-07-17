package sim.gates

import sim.base.CachedElement
import sim.base.Constant
import sim.base.MultiInputElement
import sim.base.Value

class XorGate(override val inputs: List<Value>, isSequential: Boolean = false) : CachedElement(isSequential), MultiInputElement {
	constructor(vararg inputs: Value) : this(listOf(*inputs))

	override fun compute(cache: Value): Value {
		var res = false
		for (inp in inputs)
			res = res xor inp.get()
		return Constant(res)
	}
}
