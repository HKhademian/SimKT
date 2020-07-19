package sim.gates

import sim.base.*

@PublishedApi
internal class XorGate(override val inputs: Bus) : SingleComputeElement, MultiInputElement {
	override val title = "XorGate"

	override fun eval() =
		inputs.eval()

	override fun compute(): Value {
		var res = false
		for (inp in inputs)
			res = res xor inp.get()
		return res.toValue()
	}

	override fun toString() =
		output.toString()
}
