package sim.gates

import sim.base.CachedElement
import sim.base.Value
import sim.base.toValue

@PublishedApi
internal class NotGate(override val input: Value) : CachedElement(false) {
	override val title: String
		get() = "NotGate"

	override fun compute(cache: Value) =
		(!input.get()).toValue()
}
