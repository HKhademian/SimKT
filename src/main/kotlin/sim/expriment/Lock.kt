package sim.expriment

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
