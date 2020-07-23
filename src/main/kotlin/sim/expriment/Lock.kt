package sim.expriment

import sim.base.Element
import sim.base.SingleOutputElement
import sim.base.Value
import sim.base.mut
import java.io.Closeable

class SimpleLock : Closeable {
	var isLock = false; private set

	fun <T> lock(task: () -> T): T? =
		if (isLock) null else this.use { isLock = true; task() }

	override fun close() {
		isLock = false
	}
}

open class LockElement : Element, SingleOutputElement {
	private val lock = SimpleLock()
	private val cache = mut(false, "cache")

	override val output
		get() = lock.lock(this::compute)?.also(cache::set) ?: cache

	open fun compute(): Value =
		Value.ZERO

	override fun toString() =
		output.toString()
}
