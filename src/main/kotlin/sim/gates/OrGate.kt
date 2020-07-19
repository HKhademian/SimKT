package sim.gates

import sim.base.*

@PublishedApi
internal class OrGate(override val inputs: Bus) : SingleComputeElement, MultiInputElement {
	override val title = "OrGate"

	override fun eval() =
		inputs.eval()

	override fun compute(): Value {
		for (inp in inputs)
			if (inp.get()) return Value.ONE // NOTE: for better performance
		return Value.ZERO
	}

	override fun toString() =
		output.toString()
}
