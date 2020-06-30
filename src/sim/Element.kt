package sim

interface Element : Value {
	val input: Value
	val output: Value

	override fun get() = output.get()
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
