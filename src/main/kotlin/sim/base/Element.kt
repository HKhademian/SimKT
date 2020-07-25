package sim.base

/**
 * each element in the final simulation is at least an element,
 * it has one `input`, one `output`, can `eval` on each simulation cycle
 */
interface Element /*: Eval*/ {
//	val isSequential: Boolean get() = false
}

interface SingleInputElement : Element {
	val input: Value
}

interface SingleOutputElement : Element, Value {
	val output: Value

	/**
	 * each element, at least has a single output,
	 * this method by default returns its fist output
	 */
	override fun get() =
		output.get()// default impl.
}

/**
 * if an element can handle n inputs, like and gate
 * we use this type of element to unify all usages
 */
interface MultiInputElement : Element {
	val inputs: List<Value>
}


interface MutableSingleInputElement : SingleInputElement {
	override var input: Value
}

interface MutableMultiInputElement : MultiInputElement {
	override val inputs: MutableList<Value>
}
