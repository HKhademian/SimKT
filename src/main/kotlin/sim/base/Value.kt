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


private class Constant constructor(private val value: Boolean, val name: String = "") : Value {
	override fun toString() = value.toIntString()
	override fun get() = value
	override val title: String
		get() = if (name.isNotBlank()) name else "<Const>"
}


/**
 * this type of value holds reference to source value
 * it gets values from `source` and return it (on the fly calculation)
 * when you set another value to this, it just change source
 */
private class Variable(private var source: Value = Value.ZERO, val name: String = "") : MutableSingleInputElement, MutableValue, Eval {
	override fun toString() = source.toIntString()
	override val title: String get() = if (name.isNotBlank()) name else source.title

	override var input: Value
		get() = source;
		set(value) {
			source = value
		}

	override fun get() = source.get()

	override fun set(value: Value) {
		this.source = value
	}
}

fun Boolean.toIntString() =
	if (this) "1" else "0"

fun Value.toIntString() =
	get().toIntString()

/** create a constant value */
@JvmOverloads
@JvmName("constant")
fun const(value: Boolean, name: String = ""): Value =
	if (name.isBlank()) value.toValue() else Constant(value, name)

/** create a mutable value */
@JvmOverloads
fun mut(value: Boolean, name: String = ""): MutableValue =
	Variable(value.toValue(), name)


/** cache current value, and keep it for ever */
@JvmName("constant")
fun Value.toConst(): Value =
	get().toValue()

/** cache current value, and keep it for ever */
@JvmName("mut")
fun Value.toMut(): MutableValue =
	if (this is MutableValue) this
	else Variable(get().toValue(), title)

/** converts a value to equivalent int */
fun Value.toInt(): Int =
	if (get()) 1 else 0

/** converts a bool to eq value */
fun Boolean.toValue(): Value =
	if (this) Value.ONE else Value.ZERO
