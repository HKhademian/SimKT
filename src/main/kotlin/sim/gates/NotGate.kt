package sim.gates

import sim.base.ComputeValue
import sim.base.Element
import sim.base.Value

class NotGate(override val input: Value) : Element {
  override val output = ComputeValue {
	!input.get()
  }
}
