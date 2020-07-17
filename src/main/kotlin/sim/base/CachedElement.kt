package sim.base

/**
 * `CachedElement` is a bootstrap element implement,
 * it caches the result, and each time eval,
 * remember, if `isSequential` is not set, then it acts like there is no cache
 */
abstract class CachedElement(override val isSequential: Boolean = false) : Element {
  private val cache = Variable(false)

  protected open fun compute(cache: Value): Value =
	Value.ZERO

  /**
   * compute current value, then stores it to cache
   * and returns result
   */
  final override fun eval() =
	compute(cache).also(cache::set)

  /**
   * if the gate is a not sequential it updates its cache
   * then returns cached value
   */
  override val output: Value
	get() = cache.apply { if (!isSequential) eval() }

  override fun toString() =
	output.toString()
}

