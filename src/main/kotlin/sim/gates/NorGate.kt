package sim.gates

import sim.base.Bus
import sim.base.Value

fun NorGate(inputs: Bus) = NotGate(OrGate(inputs))
fun NorGate(vararg inputs: Value) = NotGate(OrGate(*inputs))
