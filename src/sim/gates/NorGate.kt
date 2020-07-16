package sim.gates

import sim.base.Value

fun NorGate(inputs: List<Value>) = NotGate(OrGate(inputs))
fun NorGate(vararg inputs: Value) = NotGate(OrGate(*inputs))
