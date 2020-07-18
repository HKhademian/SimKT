package sim.complex

import sim.base.CachedElement
import sim.base.MultiInputElement
import sim.base.Value
import sim.base.toInt
import sim.pow

/** simulated N2One multiplexer */
class MuxN(
	val selectors: List<Value>,
	override val inputs: List<Value>
) : CachedElement(false), MultiInputElement {
	init {
		if (inputs.size != 2 pow selectors.size)
			throw RuntimeException("inputs does not match")
	}

	override fun compute(cache: Value): Value {
		val select = selectors.toInt()
		return inputs[select]
	}
}

/** select between 2^n inputs */
fun mux(selector: List<Value>, vararg inputs: Value) =
	MuxN(selector, listOf(*inputs))

/** select between 2 inputs */
fun mux2(selector: Value, input0: Value, input1: Value) =
	MuxN(listOf(selector), listOf(input0, input1))

/** select between 4 inputs */
fun mux4(selector0: Value, selector1: Value, input0: Value, input1: Value, input3: Value, input4: Value) =
	MuxN(listOf(selector0, selector1), listOf(input0, input1, input3, input4))
