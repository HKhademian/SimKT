@file:Suppress("NOTHING_TO_INLINE", "unused")
@file:JvmName("GateKt")
@file:JvmMultifileClass

package sim.base

private typealias Gate = SimpleElement

private class NotGate(override val input: Value) : SingleInputElement, Gate("Not", {
	(!input.get())
})

private class AndGate(override val inputs: Bus) : MultiInputElement, Gate("And", {
	(inputs.find { !it.get() } == null)
})

private class NandGate(override val inputs: Bus) : MultiInputElement, Gate("Nand", {
	(inputs.find { !it.get() } != null)
})

private class OrGate(override val inputs: Bus) : MultiInputElement, Gate("Or", {
	(inputs.find { it.get() } != null)
})

private class NorGate(override val inputs: Bus) : MultiInputElement, Gate("Nor", {
	(inputs.find { it.get() } == null)
})

private class XorGate(override val inputs: Bus) : MultiInputElement, Gate("Xor", {
	inputs.fold(false) { pre, cur -> pre xor cur.get() }
})

private class XnorGate(override val inputs: Bus) : MultiInputElement, Gate("Xnor", {
	inputs.fold(true) { pre, cur -> !(pre xor cur.get()) }
})

fun not(input: Value): Value = NotGate(input)
fun and(inputs: List<Value>): Value = AndGate(inputs)
fun nand(inputs: List<Value>): Value = NandGate(inputs)
fun or(inputs: List<Value>): Value = OrGate(inputs)
fun nor(inputs: List<Value>): Value = NorGate(inputs)
fun xor(inputs: List<Value>): Value = XorGate(inputs)
fun xnor(inputs: List<Value>): Value = XnorGate(inputs)

private fun main() {
	if (false) {
		val A = 7.toBus(8)
		val B = 11.toBus(8)

		println(
			"""
		A: ${A}
		B: ${B}
		!A:  ${A.not()}
		A and B: ${A and B}
		A nand B: ${A nand B}
		A or B: ${A or B}
		A nor B: ${A nor B}
		A xor B: ${A xor B}
		A xnor B: ${A xnor B}
		""".trimIndent()
		)
	}
}
