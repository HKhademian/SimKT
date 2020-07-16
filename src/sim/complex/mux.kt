package sim.complex

import sim.base.CachedElement
import sim.base.MultiInputElement
import sim.base.Value
import sim.pow
import sim.toInt

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

fun mux2(selector: Value, input0: Value, input1: Value) =
  MuxN(listOf(selector), listOf(input0, input1))

fun mux4(selector0: Value, selector1: Value, input0: Value, input1: Value, input3: Value, input4: Value) =
  MuxN(listOf(selector0, selector1), listOf(input0, input1, input3, input4))
