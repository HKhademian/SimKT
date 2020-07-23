@file:Suppress("NOTHING_TO_INLINE", "unused")

package sim.base

private open class ComputeElement(override val title: String, private val computer: () -> Value) : SingleComputeElement {
	override fun compute() = computer()

	override fun toString() =
		output.toString()
}

private class NotGate(override val input: Value) : SingleInputElement, ComputeElement("Not", {
	(!input.get()).toValue()
})

private class AndGate(override val inputs: Bus) : MultiInputElement, ComputeElement("And", {
	(inputs.find { !it.get() } == null).toValue()
})

private class NandGate(override val inputs: Bus) : MultiInputElement, ComputeElement("Nand", {
	(inputs.find { !it.get() } != null).toValue()
})

private class OrGate(override val inputs: Bus) : MultiInputElement, ComputeElement("Or", {
	(inputs.find { it.get() } != null).toValue()
})

private class NorGate(override val inputs: Bus) : MultiInputElement, ComputeElement("Nor", {
	(inputs.find { it.get() } == null).toValue()
})

private class XorGate(override val inputs: Bus) : MultiInputElement, ComputeElement("Xor", {
	inputs.fold(false) { pre, cur -> pre xor cur.get() }.toValue()
})

private class XnorGate(override val inputs: Bus) : MultiInputElement, ComputeElement("Xnor", {
	inputs.fold(true) { pre, cur -> !(pre xor cur.get()) }.toValue()
})


fun not(input: Value): Value = NotGate(input)
inline fun Bus.not() = this.map { not(it) }.toList()

fun and(inputs: List<Value>): Value = AndGate(inputs)
inline fun and(vararg inputs: Value) = and(listOf(*inputs))
inline infix fun Value.and(other: Value) = and(this, other)
infix fun Value.and(other: Bus) = other.map { this and it }.toList()
infix fun Bus.and(other: Value) = this.map { it and other }.toList()
infix fun Bus.and(other: Bus) = this.zip(other).map { (lhs, rhs) -> lhs and rhs }

fun nand(inputs: List<Value>): Value = NandGate(inputs)
inline fun nand(vararg inputs: Value) = not(and(listOf(*inputs)))
inline infix fun Value.nand(other: Value) = nand(this, other)
infix fun Value.nand(other: Bus) = other.map { this nand it }.toList()
infix fun Bus.nand(other: Value) = this.map { it nand other }.toList()
infix fun Bus.nand(other: Bus) = this.zip(other).map { (lhs, rhs) -> lhs nand rhs }

fun or(inputs: List<Value>): Value = OrGate(inputs)
inline fun or(vararg inputs: Value) = or(listOf(*inputs))
inline infix fun Value.or(other: Value) = or(this, other)
infix fun Value.or(other: Bus) = other.map { this or it }.toList()
infix fun Bus.or(other: Value) = this.map { it or other }.toList()
infix fun Bus.or(other: Bus) = this.zip(other).map { (lhs, rhs) -> lhs or rhs }

fun nor(inputs: List<Value>): Value = NorGate(inputs)
inline fun nor(vararg inputs: Value) = nor(listOf(*inputs))
inline infix fun Value.nor(other: Value) = nor(this, other)
infix fun Value.nor(other: Bus) = other.map { this nor it }.toList()
infix fun Bus.nor(other: Value) = this.map { it nor other }.toList()
infix fun Bus.nor(other: Bus) = this.zip(other).map { (lhs, rhs) -> lhs nor rhs }

fun xor(inputs: List<Value>): Value = XorGate(inputs)
inline fun xor(vararg inputs: Value) = xor(listOf(*inputs))
inline infix fun Value.xor(other: Value) = xor(this, other)
infix fun Value.xor(other: Bus) = other.map { this xor it }.toList()
infix fun Bus.xor(other: Value) = this.map { it xor other }.toList()
infix fun Bus.xor(other: Bus) = this.zip(other).map { (lhs, rhs) -> lhs xor rhs }

fun xnor(inputs: List<Value>): Value = XnorGate(inputs)
inline fun xnor(vararg inputs: Value) = xnor(listOf(*inputs))
inline infix fun Value.xnor(other: Value) = xnor(this, other)
infix fun Value.xnor(other: Bus) = other.map { this xnor it }.toList()
infix fun Bus.xnor(other: Value) = this.map { it xnor other }.toList()
infix fun Bus.xnor(other: Bus) = this.zip(other).map { (lhs, rhs) -> lhs xnor rhs }

internal fun main() {
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
