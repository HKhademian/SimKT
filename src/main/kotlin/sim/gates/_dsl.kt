@file:Suppress("NOTHING_TO_INLINE")

package sim.gates

import sim.base.Value
import sim.base.Wire

//inline fun wire(input: Value) = Wire(input)
inline fun notg(input: Value) = NotGate(input)
inline val Value.not get() = NotGate(this)
//inline fun Value.not() = NotGate(this)

inline fun andg(vararg inputs: Value) = AndGate(*inputs)
inline fun andg(inputs: Iterable<Value>) = AndGate(inputs.toList())
inline infix fun Value.and(other: Value) = AndGate(this, other)

inline fun nandg(vararg inputs: Value) = NandGate(*inputs)
inline fun nandg(inputs: Iterable<Value>) = NandGate(inputs.toList())
inline infix fun Value.nand(other: Value) = NandGate(this, other)

inline fun org(vararg inputs: Value) = OrGate(*inputs)
inline fun or(inputs: Iterable<Value>) = OrGate(inputs.toList())
inline infix fun Value.or(other: Value) = OrGate(this, other)

inline fun norg(vararg inputs: Value) = NorGate(*inputs)
inline fun nor(inputs: Iterable<Value>) = NorGate(inputs.toList())
inline infix fun Value.nor(other: Value) = NorGate(this, other)

inline fun xorg(vararg inputs: Value) = XorGate(*inputs)
inline fun xorg(inputs: Iterable<Value>) = XorGate(inputs.toList())
inline infix fun Value.xor(other: Value) = XorGate(this, other)

inline fun xnorg(vararg inputs: Value) = XnorGate(*inputs)
inline fun xnorg(inputs: Iterable<Value>) = XnorGate(inputs.toList())
inline infix fun Value.xnor(other: Value) = XnorGate(this, other)
