package sim.test

import sim.debugWrite

@JvmOverloads
inline fun test(msg: String = "sim/test", crossinline task: () -> Any? = {}) {
	println("***** $msg *****")
	val res = task() ?: return
	println(res.debugWrite())
}

@JvmOverloads
fun testOn(target: Any?, msg: String = "sim/test", task: Runnable? = null) =
	test(msg) { task?.run(); target }
