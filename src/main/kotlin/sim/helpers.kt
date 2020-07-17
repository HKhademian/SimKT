package sim

import sim.base.ConstValue
import sim.base.Value
import kotlin.math.pow

infix fun Int.pow(other: Number) =
	this.toDouble().pow(other.toDouble()).toInt()

/** converts a group of values to equivalent int */
fun List<Value>.toInt(): Int =
	this.asReversed().foldRight(0) { it, acc: Int -> (acc shl 1) or (if (it.get()) 1 else 0) }

/** converts a group of values to equivalent long int */
fun List<Value>.toLong(): Long =
	this.asReversed().foldRight(0) { it, acc: Long -> (acc shl 1) or (if (it.get()) 1 else 0) }

/** converts a integer to n-bit list of values */
fun Int.toValue(n: Int = 32) =
	(0 until n).asSequence().map { 2 pow it }.map { (this and it) != 0 }.map { ConstValue(it) }.toList()
