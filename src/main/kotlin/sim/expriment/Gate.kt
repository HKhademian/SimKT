package sim.expriment

import sim.base.Value
import sim.base.Value.Companion.ONE
import sim.base.Value.Companion.ZERO
import sim.base.const
import sim.base.mut
import sim.base.toValue
import sim.test.test

@PublishedApi
internal
class NotGate(private val input: Value) : LockElement() {
	override fun compute(): Value {
		return (!input.get()).toValue()
	}
}

@PublishedApi
internal
class AndGate(private val inputs: Iterable<Value>) : LockElement() {
	override fun compute(): Value {
		return (inputs.find { !it.get() } == null).toValue()
	}
}

@PublishedApi
internal
class OrGate(private val inputs: Iterable<Value>) : LockElement() {
	override fun compute(): Value {
		return (inputs.find { it.get() } != null).toValue()
	}
}

@PublishedApi
internal
class NandGate(private val inputs: Iterable<Value>) : LockElement() {
	override fun compute(): Value {
		return (inputs.find { !it.get() } != null).toValue()
	}
}

@PublishedApi
internal
class NorGate(private val inputs: Iterable<Value>) : LockElement() {
	override fun compute(): Value {
		return (inputs.find { it.get() } == null).toValue()
	}
}

@PublishedApi
internal
class XorGate(private val inputs: Iterable<Value>) : LockElement() {
	override fun compute() =
		inputs.fold(false) { pre, cur -> pre xor cur.get() }.toValue()
}

@PublishedApi
internal
class XnorGate(private val inputs: Iterable<Value>) : LockElement() {
	override fun compute() =
		inputs.fold(true) { pre, cur -> !(pre xor cur.get()) }.toValue()
}

internal fun main() {
	val gates = listOf("and" to ::AndGate, "or" to ::OrGate, "nand" to ::NandGate, "nor" to ::NorGate, "xor" to ::XorGate, "xnor" to ::XnorGate)

	if (false) gates.forEach { (name, constructor) ->
		test({ constructor(listOf(ONE, ONE)) }, "$name 1-1")
		test({ constructor(listOf(ONE, ZERO)) }, "$name 1-0")
		test({ constructor(listOf(ZERO, ZERO)) }, "$name 0-0")
		test({ constructor(listOf(ZERO, ONE)) }, "$name 0-1")
		test({ constructor(listOf(ZERO, ONE, ONE)) }, "$name 0-1-1")
		test({ constructor(listOf(ZERO, ONE, ZERO)) }, "$name 0-1-0")
		println("**********************************")
	}

	if (false) {
		test("loop on Not") {
			val lastGate = mut(false)
			val gate = NotGate(lastGate)
			lastGate.set(gate)

			println(gate)
			println(gate)
			println(gate)
			println(gate)
			println(gate)
			println(gate)

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
		}
	}

	val data = mut(false)
	val en = mut(false)
	val q = mut(true)
	val and1 = AndGate(listOf(data, en))
	val and2 = AndGate(listOf(NotGate(data), en))
	q.set(NorGate(listOf(and2, NorGate(listOf(q, and1)))))

	test("d latch en=0 data=0") {
		en.set(false); data.set(false);
		println(q)
		println(q)
		println(q)
		println(q)
		println(q)
		println(q)
	}

	test("d latch en=0 data=1") {
		en.set(false); data.set(true);
		println(q)
		println(q)
		println(q)
		println(q)
		println(q)
		println(q)
	}

	test("d latch en=1 data=1") {
		en.set(true); data.set(true);
		println(q)
		println(q)
		println(q)
		println(q)
		println(q)
		println(q)
	}

	test("d latch en=1 data=0") {
		en.set(true); data.set(false);
		println(q)
		println(q)
		println(q)
		println(q)
		println(q)
		println(q)
	}

	test("d latch en=1") {
		en.set(true)

		data.set(false)
		println(q)
		println(q)

		data.set(true)
		println(q)
		println(q)

		data.set(false)
		println(q)
		println(q)
	}
}
