package sim.gate

import sim.Value

fun NandGate(inputs: List<Value>) = NotGate(AndGate(inputs))
fun NandGate(vararg inputs: Value) = NotGate(AndGate(*inputs))

fun NorGate(inputs: List<Value>) = NotGate(OrGate(inputs))
fun NorGate(vararg inputs: Value) = NotGate(OrGate(*inputs))

fun NxorGate(inputs: List<Value>) = NotGate(XorGate(inputs))
fun NxorGate(vararg inputs: Value) = NotGate(XorGate(*inputs))
