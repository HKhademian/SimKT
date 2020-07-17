package sim.gates

import sim.base.Bus
import sim.base.Value

fun XnorGate(inputs: Bus) = NotGate(XorGate(inputs))
fun XnorGate(vararg inputs: Value) = NotGate(XorGate(*inputs))
