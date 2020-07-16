package sim.comb

import sim.Value

fun XnorGate(inputs: List<Value>) = NotGate(XorGate(inputs))
fun XnorGate(vararg inputs: Value) = NotGate(XorGate(*inputs))
