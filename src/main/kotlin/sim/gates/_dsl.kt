@file:Suppress("NOTHING_TO_INLINE")

package sim.gates

import sim.base.Bus
import sim.base.Value

//inline fun wire(input: Value) = Wire(input)
inline fun notg(input: Value) = NotGate(input)
inline fun Value.not() = NotGate(this)
inline fun Bus.not() = this.map { it.not() }.toList()

inline fun andg(vararg inputs: Value) = AndGate(*inputs)
inline fun andg(inputs: Iterable<Value>) = AndGate(inputs.toList())
inline infix fun Value.and(other: Value) = AndGate(this, other)
inline infix fun Bus.and(other: Value) = this.map { it and other }.toList()
inline infix fun Value.and(other: Bus) = other.map { this and it }.toList()

inline fun nandg(vararg inputs: Value) = NandGate(*inputs)
inline fun nandg(inputs: Iterable<Value>) = NandGate(inputs.toList())
inline infix fun Value.nand(other: Value) = NandGate(this, other)
inline infix fun Bus.nand(other: Value) = this.map { it nand other }.toList()
inline infix fun Value.nand(other: Bus) = other.map { this nand it }.toList()

inline fun org(vararg inputs: Value) = OrGate(*inputs)
inline fun or(inputs: Iterable<Value>) = OrGate(inputs.toList())
inline infix fun Value.or(other: Value) = OrGate(this, other)
inline infix fun Bus.or(other: Value) = this.map { it or other }.toList()
inline infix fun Value.or(other: Bus) = other.map { this or it }.toList()

inline fun norg(vararg inputs: Value) = NorGate(*inputs)
inline fun nor(inputs: Iterable<Value>) = NorGate(inputs.toList())
inline infix fun Value.nor(other: Value) = NorGate(this, other)
inline infix fun Bus.nor(other: Value) = this.map { it nor other }.toList()
inline infix fun Value.nor(other: Bus) = other.map { this nor it }.toList()

inline fun xorg(vararg inputs: Value) = XorGate(*inputs)
inline fun xorg(inputs: Iterable<Value>) = XorGate(inputs.toList())
inline infix fun Value.xor(other: Value) = XorGate(this, other)
inline infix fun Bus.xor(other: Value) = this.map { it xor other }.toList()
inline infix fun Value.xor(other: Bus) = other.map { this xor it }.toList()

inline fun xnorg(vararg inputs: Value) = XnorGate(*inputs)
inline fun xnorg(inputs: Iterable<Value>) = XnorGate(inputs.toList())
inline infix fun Value.xnor(other: Value) = XnorGate(this, other)
inline infix fun Bus.xnor(other: Value) = this.map { it xnor other }.toList()
inline infix fun Value.xnor(other: Bus) = other.map { this xnor it }.toList()
