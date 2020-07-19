package sim.base


/**
 * if an element is a sequential,
 * it needs to cache it's states
 * to update it's state-machine
 * every time, in each sim cycle
 * we use this method to update its
 * internal state
 */
interface Eval {
	fun eval() =
		Unit // default, for comb. circuits
}

fun Value?.eval(): Unit =
	(this as? Eval)?.eval() ?: Unit

fun Bus?.eval(): Unit =
	this?.forEach { it.eval() } ?: Unit
