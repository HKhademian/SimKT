package sim.base

/**
 * anything in a digital world is either 0 or 1
 * but may it's not clear what is it at some moment
 * like result of and gate,
 * so we handle any values in this value interface,
 * which allows us to lazily access the value
 */
interface Value {
	companion object {
		val ZERO = ConstValue(false)
		val ONE = ConstValue(true)
	}

	fun get(): Boolean =
		false // default impl. just returns 0 logic value
}

/**
 * mutable `Value`, which we can set it's value to what we want
 */
interface MutableValue : Value {
	fun set(value: Boolean) =
		Unit // default impl. just trash the command

	fun set(value: Value) =
		set(value.get())
}

class ConstValue(private val value: Boolean) : Value {
	override fun get() = value
	override fun toString() = value.toString()
}

class VariableValue(private var value: Boolean) : MutableValue {
	override fun get() = value
	override fun set(value: Boolean) {
		this.value = value
	}

	override fun toString() = value.toString()
}

/** Broadcast every `get` calls to value */
class ProxyValue(private val value: Value) : Value by value {
	override fun toString() = value.toString()
}

/** Broadcast every `set` calls to value */
class ProxyMutableValue(private val value: MutableValue) : MutableValue by value {
	override fun toString() = value.toString()
}

/**
 * a `ComputeValue` is like normal one, but it evaluate it's value by computing it, each time
 */
class ComputeValue(private val compute: () -> Boolean) : Value {
	override fun get() = compute()
	override fun toString() = get().toString()
}
