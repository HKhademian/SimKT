package sim.expriment

import sim.base.*
import sim.base.Value.Companion.ONE
import sim.base.Value.Companion.ZERO
import sim.test.test

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

class AndGate(private val inputs: Iterable<Value>) : LockElement() {
	override fun compute(): Value {
		return (inputs.find { !it.get() } == null).toValue()
	}
}

class OrGate(private val inputs: Iterable<Value>) : LockElement() {
	override fun compute(): Value {
		return (inputs.find { it.get() } != null).toValue()
	}
}

class NandGate(private val inputs: Iterable<Value>) : LockElement() {
	override fun compute(): Value {
		return (inputs.find { !it.get() } != null).toValue()
	}
}

class NorGate(private val inputs: Iterable<Value>) : LockElement() {
	override fun compute(): Value {
		return (inputs.find { it.get() } == null).toValue()
	}
}

class XorGate(private val inputs: Iterable<Value>) : LockElement() {
	override fun compute() =
		inputs.fold(false) { pre, cur -> pre xor cur.get() }.toValue()
}

class XnorGate(private val inputs: Iterable<Value>) : LockElement() {
	override fun compute() =
		inputs.fold(true) { pre, cur -> !(pre xor cur.get()) }.toValue()
}

internal fun main() {
	val gates = listOf("and" to ::AndGate, "or" to ::OrGate, "nand" to ::NandGate, "nor" to ::NorGate, "xor" to ::XorGate, "xnor" to ::XnorGate)

	gates.forEach { (name, constructor) ->
		test({ constructor(listOf(ONE, ONE)) }, "$name 1-1")
		test({ constructor(listOf(ONE, ZERO)) }, "$name 1-0")
		test({ constructor(listOf(ZERO, ZERO)) }, "$name 0-0")
		test({ constructor(listOf(ZERO, ONE)) }, "$name 0-1")
		test({ constructor(listOf(ZERO, ONE, ONE)) }, "$name 0-1-1")
		test({ constructor(listOf(ZERO, ONE, ZERO)) }, "$name 0-1-0")
		println("**********************************")
	}

	test("loop on Nand") {
		val lastGate = mut(false)
		val gate = NandGate(listOf(lastGate, const(true)))
		lastGate.set(gate)

		println(gate)
		println(gate)
		println(gate)
		println(gate)
		println(gate)
		println(gate)

		null
	}

	test("loop on Xor") {
		val lastGate = mut(false)
		val gate = XorGate(listOf(lastGate, const(true)))
		lastGate.set(gate)

		println(gate)
		println(gate)
		println(gate)
		println(gate)
		println(gate)
		println(gate)

		null
	}
}
