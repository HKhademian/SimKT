package sim.base

/**
 * `CachedElement` is a bootstrap element implement,
 * it caches the result, and each time eval,
 * remember, if `isSequential` is not set, then it acts like there is no cache
 */
abstract class CachedElement(override val isSequential: Boolean = false) : Element {
  private val cache = VariableValue(false)

  open fun compute(): Value =
	Value.ZERO

  final override fun eval(): Value {
	cache.set(compute())
	return cache
  }

  /**
   * if the gate is a not sequential it updates its cache
   * then returns cached value
   */
  override val output: Value
	get() = cache.apply { if (!isSequential) eval() }
}

