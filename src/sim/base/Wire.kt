package sim.base

class Wire(override val input: Value) : Element {
	// override val output = ProxyValue(input)
	// simply get value from input :D
	override val output get() = input
}
