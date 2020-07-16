package sim.gates

import sim.base.Value

fun NandGate(inputs: List<Value>) = NotGate(AndGate(inputs))
fun NandGate(vararg inputs: Value) = NotGate(AndGate(*inputs))
