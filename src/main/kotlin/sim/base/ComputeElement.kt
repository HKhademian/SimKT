package sim.base

interface SingleComputeElement : SingleOutputElement {
	override val output
		get() = compute()

	fun compute(): Value =
		Value.ZERO
}

interface MultiComputeElement : MultiOutputElement {
	override val outputs
		get() = compute()

	fun compute(): Bus =
		ZERO_BUS
}

/**
 * `SingleCachedElement` is a bootstrap element implement,
 * it caches the result, and each time eval,
 * remember, if `isSequential` is not set, then it acts like there is no cache
 */
abstract class SingleCachedElement : SingleComputeElement {
	override val isSequential = true

	protected val cache = mut(false)

	/**
	 * compute current value, then stores it to cache
	 * NOTE: eval inputs first
	 */
	override fun eval() =
		compute().toConst().let(cache::set)

	override val output: Value
		get() = cache

	override fun toString() =
		output.toString()
}

