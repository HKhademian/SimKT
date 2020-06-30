package sim

interface Value {
	fun get(): Boolean
}

interface MutableValue {
	fun set(value: Boolean)
}

class ConstValue(private val value: Boolean) : Value {
	override fun get() = value
	override fun toString() = value.toString()
}

/// Broadcast every `get` calls to value
class ProxyValue(private val value: Value) : Value by value {
	override fun toString() = value.toString()
}

/// Broadcast every `set` calls to value
class ProxyMutableValue(private val value: MutableValue) : MutableValue by value {
	override fun toString() = value.toString()
}

class ComputeValue(private val compute: () -> Boolean) : Value {
	override fun get() = compute()
	override fun toString() = get().toString()

}
