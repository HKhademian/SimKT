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
	fun eval(time: Long) //= Unit // default, for comb. circuits
}

fun Any?.eval(time: Long): Unit = when (this) {
	is Eval -> this.eval(time)
//	is SingleInputElement -> input.eval(time)
//	is MultiInputElement -> inputs.eval(time)
	is Iterable<*> -> forEach { it.eval(time) } ?: Unit
	else -> Unit
}
