package sim.comb

import sim.Element
//import sim.ProxyValue
import sim.Value

class Wire(override val input: Value) : Element {
	// override val output = ProxyValue(input)
	// simply get value from input :D
	override val output get() = input
}
