package sim.gates

import sim.base.Value
import sim.base.Wire

inline fun wire(input: Value) = Wire(input)
inline fun notg(input: Value) = NotGate(input)
inline val Value.not get() = NotGate(this)
//inline fun Value.not() = NotGate(this)

inline fun andg(vararg inputs: Value) = AndGate(*inputs)
inline fun andg(inputs: List<Value>) = AndGate(inputs)
inline fun nandg(vararg inputs: Value) = NandGate(*inputs)
inline fun nandg(inputs: List<Value>) = NandGate(inputs)

inline fun org(vararg inputs: Value) = OrGate(*inputs)
inline fun org(inputs: List<Value>) = OrGate(inputs)
inline fun norg(vararg inputs: Value) = NorGate(*inputs)
inline fun norg(inputs: List<Value>) = NorGate(inputs)

inline fun xorg(vararg inputs: Value) = XorGate(*inputs)
inline fun xorg(inputs: List<Value>) = XorGate(inputs)
inline fun xnorg(vararg inputs: Value) = XnorGate(*inputs)
inline fun xnorg(inputs: List<Value>) = XnorGate(inputs)
