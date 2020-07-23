package sim.expriment

import sim.base.Value

class Bus2<T : Value>
private constructor(private val base: List<T>) : Collection<T> by base {
	operator fun get(index: Int): T =
		base[index]
}
