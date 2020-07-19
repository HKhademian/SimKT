package sim.gates

import sim.base.*

@PublishedApi
internal class NotGate(override val input: Value) : SingleComputeElement, SingleInputElement {
	override val title = "NotGate"

	override fun eval() =
		input.eval()

	override fun compute() =
		(!input.get()).toValue()

	override fun toString() =
		output.toString()
}
