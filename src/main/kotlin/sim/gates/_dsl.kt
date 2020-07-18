@file:Suppress("NOTHING_TO_INLINE", "unused")

package sim.gates

import sim.base.Bus
import sim.base.Value
import sim.base.toBus

inline fun notg(input: Value) = NotGate(input)
inline fun Value.not() = NotGate(this)
inline fun Bus.not() = this.map { it.not() }.toList()

inline fun andg(vararg inputs: Value) = AndGate(*inputs)
inline fun andg(inputs: Iterable<Value>) = AndGate(inputs.toList())
inline infix fun Value.and(other: Value) = AndGate(this, other)
infix fun Value.and(other: Bus) = other.map { this and it }.toList()
infix fun Bus.and(other: Value) = this.map { it and other }.toList()
infix fun Bus.and(other: Bus) = this.zip(other).map { (lhs, rhs) -> lhs and rhs }

inline fun nandg(vararg inputs: Value) = NandGate(*inputs)
inline fun nandg(inputs: Iterable<Value>) = NandGate(inputs.toList())
inline infix fun Value.nand(other: Value) = NandGate(this, other)
infix fun Value.nand(other: Bus) = other.map { this nand it }.toList()
infix fun Bus.nand(other: Value) = this.map { it nand other }.toList()
infix fun Bus.nand(other: Bus) = this.zip(other).map { (lhs, rhs) -> lhs nand rhs }

inline fun org(vararg inputs: Value) = OrGate(*inputs)
inline fun or(inputs: Iterable<Value>) = OrGate(inputs.toList())
inline infix fun Value.or(other: Value) = OrGate(this, other)
infix fun Value.or(other: Bus) = other.map { this or it }.toList()
infix fun Bus.or(other: Value) = this.map { it or other }.toList()
infix fun Bus.or(other: Bus) = this.zip(other).map { (lhs, rhs) -> lhs or rhs }

inline fun norg(vararg inputs: Value) = NorGate(*inputs)
inline fun nor(inputs: Iterable<Value>) = NorGate(inputs.toList())
inline infix fun Value.nor(other: Value) = NorGate(this, other)
infix fun Value.nor(other: Bus) = other.map { this nor it }.toList()
infix fun Bus.nor(other: Value) = this.map { it nor other }.toList()
infix fun Bus.nor(other: Bus) = this.zip(other).map { (lhs, rhs) -> lhs nor rhs }

inline fun xorg(vararg inputs: Value) = XorGate(*inputs)
inline fun xorg(inputs: Iterable<Value>) = XorGate(inputs.toList())
inline infix fun Value.xor(other: Value) = XorGate(this, other)
infix fun Value.xor(other: Bus) = other.map { this xor it }.toList()
infix fun Bus.xor(other: Value) = this.map { it xor other }.toList()
infix fun Bus.xor(other: Bus) = this.zip(other).map { (lhs, rhs) -> lhs xor rhs }

inline fun xnorg(vararg inputs: Value) = XnorGate(*inputs)
inline fun xnorg(inputs: Iterable<Value>) = XnorGate(inputs.toList())
inline infix fun Value.xnor(other: Value) = XnorGate(this, other)
infix fun Value.xnor(other: Bus) = other.map { this xnor it }.toList()
infix fun Bus.xnor(other: Value) = this.map { it xnor other }.toList()
infix fun Bus.xnor(other: Bus) = this.zip(other).map { (lhs, rhs) -> lhs xnor rhs }

fun main() {
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
