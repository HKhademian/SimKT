package sim.expriment

import java.io.Closeable

class SimpleLock : Closeable {
	var isLock = false; private set

	fun <T> lock(task: () -> T): T? =
		if (isLock) null else this.use { isLock = true; task() }

	override fun close() {
		isLock = false
	}
}
