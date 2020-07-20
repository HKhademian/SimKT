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
		@JvmField
		val ZERO: Value = Constant(false, "ZERO")

		@JvmField
		val ONE: Value = Constant(true, "ONE")
	}

	fun get(): Boolean =
		false // default impl. just returns 0 logic value

	val title: String
		get() = "Value" // default title
}

/**
 * mutable `Value`, which we can set it's value to what we want
 */
interface MutableValue : Value {
	fun set(value: Value) =
		Unit // default impl. just trash the command

	fun set(value: Boolean) =
		set(value.toValue())

	/** write one */
	fun set() =
		set(Value.ONE)

	/** write zero */
	fun reset() =
		set(Value.ZERO)

	/** write toggle */
	fun toggle() =
		set(!get())

	override val title: String
		get() = "MutValue"
}


class Constant @PublishedApi internal constructor(private val value: Boolean, val name: String = "") : Value {
	override fun get() = value
	override fun toString() = if (value) "1" else "0"
	override val title: String
		get() = "<Const>$name"
}


class Variable @PublishedApi internal constructor(private var value: Value = Value.ZERO, val name: String = "") : MutableValue, Eval {
	@PublishedApi
	internal constructor(value: Boolean, name: String = "")
		: this(value.toValue(), name)

	override fun get() =
		value.get()

	override fun set(value: Value) {
		this.value = value
	}

	override fun toString() =
		value.toString()

	override val title: String
		get() = "<Variable>$name"

	override fun eval() =
		value.eval()
}

/** create a constant value */
@JvmOverloads
@JvmName("constant")
fun const(value: Boolean, name: String = "") =
	Constant(value, name)

/** create a mutable value */
@JvmOverloads
fun mut(value: Boolean = false, name: String = "") =
	Variable(value, name)


/** cache current value, and keep it for ever */
@JvmName("constant")
fun Value.const(): Constant =
	Constant(get())

/** cache current value, and keep it for ever */
fun Value.mut(): MutableValue =
	if (this is MutableValue) this else Variable(get())

/** converts a value to equivalent int */
fun Value.toInt(): Int =
	if (get()) 1 else 0


/** converts a bool to eq value */
fun Boolean.toValue() =
	if (this) Value.ONE else Value.ZERO


/** Broadcast every `get` calls to value */
class ProxyValue(private val value: Value) : Value by value {
	override fun toString() = value.toString()
	override val title = "Proxy"
}

/** Broadcast every `set` calls to value */
class ProxyMutableValue(private val value: MutableValue) : MutableValue by value {
	override fun toString() = value.toString()
	override val title = "MutProxy"
}

/**
 * a `ComputeValue` is like normal one, but it evaluate it's value by computing it, each time
 */
class ComputeValue(private val compute: () -> Boolean) : Value {
	override fun get() = compute()
	override fun toString() = get().toString()
	override val title = "Compute"
}
