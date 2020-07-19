@file:Suppress("NOTHING_TO_INLINE", "unused")

package sim.gates

import sim.base.Bus
import sim.base.Value
import sim.base.toBus

inline fun not(input: Value): Value = NotGate(input)
inline fun Bus.not() = this.map { not(it) }.toList()

inline fun and(inputs: Iterable<Value>): Value = AndGate(inputs.toList())
inline fun and(vararg inputs: Value) = and(listOf(*inputs))
inline infix fun Value.and(other: Value) = and(this, other)
infix fun Value.and(other: Bus) = other.map { this and it }.toList()
infix fun Bus.and(other: Value) = this.map { it and other }.toList()
infix fun Bus.and(other: Bus) = this.zip(other).map { (lhs, rhs) -> lhs and rhs }

inline fun nand(inputs: Iterable<Value>): Value = not(and(inputs))
inline fun nand(vararg inputs: Value) = not(and(listOf(*inputs)))
inline infix fun Value.nand(other: Value) = nand(this, other)
infix fun Value.nand(other: Bus) = other.map { this nand it }.toList()
infix fun Bus.nand(other: Value) = this.map { it nand other }.toList()
infix fun Bus.nand(other: Bus) = this.zip(other).map { (lhs, rhs) -> lhs nand rhs }

inline fun or(inputs: Iterable<Value>): Value = OrGate(inputs.toList())
inline fun or(vararg inputs: Value) = or(listOf(*inputs))
inline infix fun Value.or(other: Value) = or(this, other)
infix fun Value.or(other: Bus) = other.map { this or it }.toList()
infix fun Bus.or(other: Value) = this.map { it or other }.toList()
infix fun Bus.or(other: Bus) = this.zip(other).map { (lhs, rhs) -> lhs or rhs }

inline fun nor(inputs: Iterable<Value>): Value = not(or(inputs))
inline fun nor(vararg inputs: Value) = nor(listOf(*inputs))
inline infix fun Value.nor(other: Value) = nor(this, other)
infix fun Value.nor(other: Bus) = other.map { this nor it }.toList()
infix fun Bus.nor(other: Value) = this.map { it nor other }.toList()
infix fun Bus.nor(other: Bus) = this.zip(other).map { (lhs, rhs) -> lhs nor rhs }

inline fun xor(inputs: Iterable<Value>): Value = XorGate(inputs.toList())
inline fun xor(vararg inputs: Value) = xor(listOf(*inputs))
inline infix fun Value.xor(other: Value) = xor(this, other)
infix fun Value.xor(other: Bus) = other.map { this xor it }.toList()
infix fun Bus.xor(other: Value) = this.map { it xor other }.toList()
infix fun Bus.xor(other: Bus) = this.zip(other).map { (lhs, rhs) -> lhs xor rhs }

inline fun xnor(inputs: Iterable<Value>): Value = not(xor((inputs)))
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
