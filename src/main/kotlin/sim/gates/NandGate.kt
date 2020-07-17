package sim.gates

import sim.base.Bus
import sim.base.Value

fun NandGate(inputs: Bus) = NotGate(AndGate(inputs))
fun NandGate(vararg inputs: Value) = NotGate(AndGate(*inputs))
