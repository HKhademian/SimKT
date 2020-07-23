package sim.test

import sim.debugWrite
import java.util.function.Consumer

inline fun <T> measure(crossinline task: () -> T): Pair<Pair<Long, Long>, T> {
	val startMs = System.currentTimeMillis()
	val startTick = System.nanoTime()
	val res: T = task()
	val endTick = System.nanoTime()
	val endMs = System.currentTimeMillis()
	val diffTick: Long = endTick - startTick
	val diffMs: Long = endMs - startMs
	return (diffTick to diffMs) to res
}

@JvmOverloads
@JvmName("test")
inline fun test(msg: String, crossinline task: (() -> Any?) = {}) {
	println("***** $msg *****")
	val (takes, res) = measure(task)
	val (ticks, milis) = takes
	if (res != null && res != Unit) print(res.debugWrite())
	println("***** takes: $milis ms and $ticks cycles *****\n")
}

@JvmSynthetic
fun testOn(target: Any?, msg: String = "sim/test", task: (() -> Unit) = { }) =
	test(msg) { task.invoke(); target }

@JvmSynthetic
fun <T> test(init: () -> T, msg: String = "sim/test", task: ((T) -> Unit) = { }): T =
	init().also { testOn(it, msg) { task.invoke(it) } }


@JvmOverloads
@JvmName("testOn")
fun jvmTestOn(target: Any?, msg: String = "sim/test", task: Runnable? = null) =
	test(msg) { task?.run(); target }

@JvmOverloads
@JvmName("test")
fun <T> jvmTest(init: () -> T, msg: String = "sim/test", task: Consumer<T>? = null): T =
	init().also { testOn(it, msg) { task?.accept(it) } }
