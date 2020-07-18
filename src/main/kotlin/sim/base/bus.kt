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


/** set a value to all of a bus lines */
fun MutableBus.set(value: Value) = this.forEach { it.set(value) }

/** set a bus values to another bus lines */
fun MutableBus.set(other: Bus) = this.zip(other).forEach { (lhs, rhs) -> lhs.set(rhs) }

/** write a value to all of a bus lines */
fun Value.writeOn(other: MutableBus) = other.set(this)

/** write bus values to another bus lines */
fun Bus.writeOn(other: MutableBus) = other.set(this)


/** slice and create another bus from a bus */
fun Bus.slice(from: Int = 0, to: Int = -1) =
	this.subList(from, if (to >= 0) to else this.size + to + 1)

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
