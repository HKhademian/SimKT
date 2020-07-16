package sim

interface Element : Value {
	val input: Value
	val output: Value

	/**
	 * if an element is a sequential,
	 * it needs to cache it's states
	 * to update it's state-machine
	 * every time, in each sim cycle
	 * we use this method to update its
	 * internal state
	 */
	fun eval() =
		Unit // default, for comb. circuits

	/**
	 * each element, at least has a single output,
	 * this method by default returns its fist output
	 */
	override fun get() =
		output.get()
}

interface MultiInputElement : Element {
	val inputs: List<Value>
	override val input get() = inputs[0]
}

interface MultiOutputElement : Element {
	val outputs: List<Value>
	override val output get() = outputs[0]
}

interface MultiElement : MultiInputElement, MultiOutputElement
