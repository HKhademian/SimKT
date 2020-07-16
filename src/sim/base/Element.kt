package sim.base

/**
 * each element in the final simulation is at least an element,
 * it has one `input`, one `output`, can `eval` on each simulation cycle
 */
interface Element : Value {
  val input: Value
  val output: Value

  val isSequential: Boolean
	get() = false

  /**
   * if an element is a sequential,
   * it needs to cache it's states
   * to update it's state-machine
   * every time, in each sim cycle
   * we use this method to update its
   * internal state
   */
  fun eval(): Value =
	Value.ZERO // default, for comb. circuits

  /**
   * each element, at least has a single output,
   * this method by default returns its fist output
   */
  override fun get() =
	output.get()
}

/**
 * if an element can handle n inputs, like and gate
 * we use this type of element to unify all usages
 */
interface MultiInputElement : Element {
  val inputs: List<Value>
  override val input
	get() = inputs[0] // default impl. just uses first input
}

/**
 * if an element can handle n outputs, like counters
 * we use this type of element to unify all usages
 */
interface MultiOutputElement : Element {
  val outputs: List<Value>
  override val output
	get() = outputs[0]  // default impl. just uses first output
}

/**
 * if an element can handles n inputs and m outputs,
 * like decoders, we use this type of element which is
 * both `MultiInputElement` and `MultiOutputElement`
 */
interface MultiElement : MultiInputElement, MultiOutputElement
