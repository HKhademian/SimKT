package sim.gates

import sim.base.Value
import sim.base.Wire

inline fun wire(input: Value) = Wire(input)
inline fun notg(input: Value) = NotGate(input)
inline val Value.not get() = NotGate(this)
//inline fun Value.not() = NotGate(this)

inline fun andg(vararg inputs: Value) = AndGate(*inputs)
inline fun andg(inputs: Iterable<Value>) = AndGate(inputs.toList())
inline fun nandg(vararg inputs: Value) = NandGate(*inputs)
inline fun nandg(inputs: Iterable<Value>) = NandGate(inputs.toList())

inline fun org(vararg inputs: Value) = OrGate(*inputs)
inline fun org(inputs: Iterable<Value>) = OrGate(inputs.toList())
inline fun norg(vararg inputs: Value) = NorGate(*inputs)
inline fun norg(inputs: Iterable<Value>) = NorGate(inputs.toList())

inline fun xorg(vararg inputs: Value) = XorGate(*inputs)
inline fun xorg(inputs: Iterable<Value>) = XorGate(inputs.toList())
inline fun xnorg(vararg inputs: Value) = XnorGate(*inputs)
inline fun xnorg(inputs: Iterable<Value>) = XnorGate(inputs.toList())
