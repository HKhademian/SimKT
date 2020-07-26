@file:Suppress("NOTHING_TO_INLINE", "unused")
@file:JvmName("GateKt")
@file:JvmMultifileClass

package sim.base

private typealias Gate = SimpleElement

private class BufferGate(override val input: Value) : SingleInputElement, Gate("Buffer", {
	(input.get())
})

private class NotGate(override val input: Value) : SingleInputElement, Gate("Not", {
	(!input.get())
})

private class AndGate(override val inputs: MutableList<Value>) : MutableMultiInputElement, Gate("And", {
	inputs.fold(true) { pre, cur -> pre and cur.get() }
})

private class NandGate(override val inputs: MutableList<Value>) : MutableMultiInputElement, Gate("Nand", {
	!inputs.fold(true) { pre, cur -> pre and cur.get() }
})

private class OrGate(override val inputs: MutableList<Value>) : MutableMultiInputElement, Gate("Or", {
	inputs.fold(false) { pre, cur -> pre or cur.get() }
})

private class NorGate(override val inputs: MutableList<Value>) : MutableMultiInputElement, Gate("Nor", {
	!inputs.fold(false) { pre, cur -> pre or cur.get() }
})

private class XorGate(override val inputs: MutableList<Value>) : MutableMultiInputElement, Gate("Xor", {
	inputs.fold(false) { pre, cur -> pre xor cur.get() }
})

private class XnorGate(override val inputs: MutableList<Value>) : MutableMultiInputElement, Gate("Xnor", {
	!inputs.fold(false) { pre, cur -> pre xor cur.get() }
})

fun not(input: Value): Value = NotGate(input)
fun and(inputs: List<Value>): Value = AndGate(inputs.toMutableList())
fun nand(inputs: List<Value>): Value = NandGate(inputs.toMutableList())
fun or(inputs: List<Value>): Value = OrGate(inputs.toMutableList())
fun nor(inputs: List<Value>): Value = NorGate(inputs.toMutableList())
fun xor(inputs: List<Value>): Value = XorGate(inputs.toMutableList())
fun xnor(inputs: List<Value>): Value = XnorGate(inputs.toMutableList())
