package sim.base

open class SimpleElement(override val title: String, private val computer: () -> Boolean) : Value, SingleOutputElement, Element {
	override fun toString() = get().toIntString()
	override val output: Value get() = get().toValue()
	override fun get(): Boolean = computer()
}

open class ValueElement(override val title: String, private val computer: () -> Value) : Value, SingleOutputElement, Element {
	override fun toString() = get().toIntString()
	override fun get(): Boolean = output.get()
	override val output: Value get() = computer()
}

open class LockElement(override val title: String, private val computer: () -> Boolean) : Value, SingleOutputElement, Element {
	override fun toString() = get().toIntString()
	override val output: Value get() = get().toValue()
	override fun get(): Boolean = synchronized(lock) {
		if (!lock) {
			lock = true
			cache = computer()
			lock = false
		} else {
			//println("locked")
		}
		return@synchronized cache
	}

	private var lock = false
	private var cache = false
}
