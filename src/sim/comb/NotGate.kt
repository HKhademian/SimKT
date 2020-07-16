package sim.comb

import sim.ComputeValue
import sim.Element
import sim.Value

class NotGate(override val input: Value) : Element {
	override val output = ComputeValue {
		!input.get()
	}
}
