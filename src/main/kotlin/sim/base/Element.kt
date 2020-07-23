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
		get() = Value.ZERO // default impl.

//	override fun eval(time: Long) =
//		input.eval(time)
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

///**
// * if an element can handles a input and an output,
// * like not-gate, we use this type of element which is
// * both `SingleInputElement` and `SingleOutputElement`
// */
//interface SingleElement : SingleInputElement, SingleOutputElement

/**
 * if an element can handle n inputs, like and gate
 * we use this type of element to unify all usages
 */
interface MultiInputElement : Element {
	val inputs: Bus

//	override fun eval(time: Long) =
//		inputs.eval(time)
}

///**
// * if an element can handle n outputs, like counters
// * we use this type of element to unify all usages
// */
//interface MultiOutputElement : Element, Bus {
//	val outputs: Bus
//}

///**
// * if an element can handles n inputs and m outputs,
// * like decoders, we use this type of element which is
// * both `MultiInputElement` and `MultiOutputElement`
// */
//interface MultiElement : MultiInputElement, MultiOutputElement
