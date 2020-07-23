package sim.expriment

import sim.base.Element
import sim.base.SingleOutputElement
import sim.base.Value
import sim.base.mut
import java.io.Closeable

internal class Lock : Closeable {
	var isLock = false; private set

	fun lock(): Lock = this.also { isLock = true }

	override fun close() {
		isLock = false
	}

	inline fun <T> eval(crossinline task: () -> T): T? =
		if (isLock) null
		else lock().use { task() }

}

open class LockElement : Element, SingleOutputElement {
	private val lock = Lock()
	private val cache = mut(false, "cache")

	override val output
		get() = lock.eval(this::compute)?.also(cache::set) ?: cache

	open fun compute(): Value =
		Value.ZERO

	override fun toString() =
		output.toString()
}
