package sim.gates

import sim.base.*

@PublishedApi
internal class AndGate(override val inputs: Bus) : SingleComputeElement, MultiInputElement {
	override val title = "AndGate"

	override fun eval() =
		inputs.eval()

	override fun compute(): Value {
		for (inp in inputs)
			if (!inp.get()) return Value.ZERO      // NOTE: for better performance
		return Value.ONE
	}

	override fun toString() =
		output.toString()
}
