@file:Suppress("NOTHING_TO_INLINE", "unused")
@file:JvmName("GateKt")
@file:JvmMultifileClass

package sim.expriment

import sim.base.LockElement
import sim.base.MultiInputElement
import sim.base.SingleInputElement
import sim.base.Value
import sim.base.Value.Companion.ONE
import sim.base.Value.Companion.ZERO
import sim.test.test

private typealias Gate = LockElement

private class NotGate(override val input: Value) : SingleInputElement, Gate("Not", {
	!input.get()
})

private class AndGate(override val inputs: List<Value>) : MultiInputElement, Gate("And", {
	(inputs.find { !it.get() } == null)
})

private class OrGate(override val inputs: List<Value>) : MultiInputElement, Gate("Or", {
	(inputs.find { it.get() } != null)
})

private class NandGate(override val inputs: List<Value>) : MultiInputElement, Gate("Nand", {
	(inputs.find { !it.get() } != null)
})

private class NorGate(override val inputs: List<Value>) : MultiInputElement, Gate("Nor", {
	(inputs.find { it.get() } == null)
})

private class XorGate(override val inputs: List<Value>) : MultiInputElement, Gate("Xor", {
	inputs.fold(false) { pre, cur -> pre xor cur.get() }
})

private class XnorGate(override val inputs: List<Value>) : MultiInputElement, Gate("Xnor", {
	inputs.fold(true) { pre, cur -> !(pre xor cur.get()) }
})

fun not(input: Value): Value = NotGate(input)
fun and(inputs: List<Value>): Value = AndGate(inputs)
fun nand(inputs: List<Value>): Value = NandGate(inputs)
fun or(inputs: List<Value>): Value = OrGate(inputs)
fun nor(inputs: List<Value>): Value = NorGate(inputs)
fun xor(inputs: List<Value>): Value = XorGate(inputs)
fun xnor(inputs: List<Value>): Value = XnorGate(inputs)

private fun main() {
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
}
