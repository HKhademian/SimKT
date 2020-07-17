package sim.base

import sim.pow

typealias Wire = Variable
typealias Bus = List<Value>
typealias MutableBus = List<MutableValue>

/** creates a n-bit mutable bus to use */
fun bus(n: Int = 32): MutableBus =
	(0 until n).map { Variable() }.toList()

/** create bus of some values */
fun bus(vararg value: Value): Bus =
	listOf(*value)

/** create bus of some values */
fun mutableBus(vararg value: MutableValue): MutableBus =
	listOf(*value)

/** merge some bus to getter */
fun merge(vararg buses: Bus): Bus =
	listOf(*buses).flatten()

/** merge some mutable bus to getter */
fun mutableMerge(vararg buses: MutableBus): MutableBus =
	listOf(*buses).flatten()

/** converts a group of values to equivalent int */
fun Bus.toInt(): Int =
	this.foldRight(0) { it, acc: Int -> (acc shl 1) or (it.toInt()) }

/** converts a group of values to equivalent long int */
fun Bus.toLong(): Long =
	this.foldRight(0) { it, acc: Long -> (acc shl 1) or (it.toInt().toLong()) }

/** converts a integer to n-bit list of values */
fun Int.toBus(n: Int = 32): Bus =
	(0 until n).asSequence().map { 2 pow it }.map { (this and it) != 0 }.map { Constant(it) }.toList()
