package sim.complex

import sim.base.*
import sim.println
import sim.zipLists

/** simulated N2One multiplexer */
class MuxN(
	val selectors: List<Value>,
	override val inputs: List<Value>
) : MultiInputElement, SingleComputeElement {
	init {
		var inputCount = 1 shl selectors.size
		if (inputs.size != inputCount)
			throw RuntimeException("inputs does not match")
	}

	override val title = "Mux"

	override fun eval() =
		inputs.eval()

	override fun compute(): Value {
		val select = selectors.toInt()
		return inputs[select]
	}
}

/** select between 2^n inputs */
fun mux(selector: List<Value>, vararg inputs: Value): Value =
	MuxN(selector, listOf(*inputs))

/** select between 2 inputs */
fun mux2(selector: Value, input0: Value, input1: Value): Value =
	MuxN(listOf(selector), listOf(input0, input1))

/** select between 4 inputs */
fun mux4(selector0: Value, selector1: Value, input0: Value, input1: Value, input2: Value, input3: Value): Value =
	MuxN(listOf(selector0, selector1), listOf(input0, input1, input2, input3))


/**
 * mux M*N to M
 * M: input bus, each bus size
 * N: selector bus size
 */
fun mux(selector: List<Value>, vararg inputs: Bus): Bus =
	listOf(*inputs).zipLists().map { MuxN(selector, it) }.toList()

/**
 * mux M*2 to M
 * M: input bus, each bus size
 * N: selector bus size
 */
fun mux2(selector: Value, input0: Bus, input1: Bus): Bus =
	input0.zip(input1).map { (inp0, inp1) -> mux2(selector, inp0, inp1) }.toList()

/**
 * mux M*4 to M
 * M: input bus, each bus size
 * N: selector bus size
 */
fun mux4(selector0: Value, selector1: Value, input0: Bus, input1: Bus, input2: Bus, input3: Bus): Bus =
	input0.zip(input1).zip(input2).zip(input3).map { mux4(selector0, selector1, it.first.first.first, it.first.first.second, it.first.second, it.second) }.toList()


internal fun main() {
	val A = 74.toBus(16)
	val B = 99.toBus(16)
	val s = Variable(false)

	val res = mux2(s, A, B)

	res[0].println()

	println(
		"""
		s: $s
		A: ${A.toInt()}
		B: ${B.toInt()}
		res: ${res.toInt()}
		
	""".trimIndent()
	)

	// I change just the selector
	// see how result changed, on the fly, in real time
	s.set(true)

	println(
		"""
		s: $s
		A: ${A.toInt()}
		B: ${B.toInt()}
		res: ${res.toInt()}
		
	""".trimIndent()
	)
}
