@file:Suppress("NOTHING_TO_INLINE", "unused")
@file:JvmName("GateKt")
@file:JvmMultifileClass

package sim.expriment

import sim.base.Bus
import sim.base.Value
import sim.base.const
import sim.base.mut
import sim.tool.test

inline fun Bus.not() = this.map { not(it) }.toList()

inline fun and(vararg inputs: Value) = and(listOf(*inputs))
inline infix fun Value.and(other: Value) = and(this, other)
infix fun Value.and(other: Bus) = other.map { this and it }.toList()
infix fun Bus.and(other: Value) = this.map { it and other }.toList()
infix fun Bus.and(other: Bus) = this.zip(other).map { (lhs, rhs) -> lhs and rhs }

inline fun nand(vararg inputs: Value) = not(and(listOf(*inputs)))
inline infix fun Value.nand(other: Value) = nand(this, other)
infix fun Value.nand(other: Bus) = other.map { this nand it }.toList()
infix fun Bus.nand(other: Value) = this.map { it nand other }.toList()
infix fun Bus.nand(other: Bus) = this.zip(other).map { (lhs, rhs) -> lhs nand rhs }

inline fun or(vararg inputs: Value) = or(listOf(*inputs))
inline infix fun Value.or(other: Value) = or(this, other)
infix fun Value.or(other: Bus) = other.map { this or it }.toList()
infix fun Bus.or(other: Value) = this.map { it or other }.toList()
infix fun Bus.or(other: Bus) = this.zip(other).map { (lhs, rhs) -> lhs or rhs }

inline fun nor(vararg inputs: Value) = nor(listOf(*inputs))
inline infix fun Value.nor(other: Value) = nor(this, other)
infix fun Value.nor(other: Bus) = other.map { this nor it }.toList()
infix fun Bus.nor(other: Value) = this.map { it nor other }.toList()
infix fun Bus.nor(other: Bus) = this.zip(other).map { (lhs, rhs) -> lhs nor rhs }

inline fun xor(vararg inputs: Value) = xor(listOf(*inputs))
inline infix fun Value.xor(other: Value) = xor(this, other)
infix fun Value.xor(other: Bus) = other.map { this xor it }.toList()
infix fun Bus.xor(other: Value) = this.map { it xor other }.toList()
infix fun Bus.xor(other: Bus) = this.zip(other).map { (lhs, rhs) -> lhs xor rhs }

inline fun xnor(vararg inputs: Value) = xnor(listOf(*inputs))
inline infix fun Value.xnor(other: Value) = xnor(this, other)
infix fun Value.xnor(other: Bus) = other.map { this xnor it }.toList()
infix fun Bus.xnor(other: Value) = this.map { it xnor other }.toList()
infix fun Bus.xnor(other: Bus) = this.zip(other).map { (lhs, rhs) -> lhs xnor rhs }

internal fun main() {
	if (true) {
		test("loop on Not") {
			val lastGate = mut(false)
			val gate = not(lastGate)
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
			val gate = nand(listOf(lastGate, const(true)))
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
			val gate = xor(listOf(lastGate, const(true)))
			lastGate.set(gate)

			println(gate)
			println(gate)
			println(gate)
			println(gate)
			println(gate)
			println(gate)
		}
	}

	if (true) {
		val data = mut(false)
		val en = mut(false)
		val q = mut(true)
		val and1 = and(listOf(data, en))
		val and2 = and(listOf(not(data), en))
		q.set(nor(listOf(and2, nor(listOf(q, and1)))))

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

	val A = const(false)
	val B = const(true)
	val C = const(true)
	val D = const(false)
	val res = and(xor(or(A, C), D), C, nor(A, B))
	test("benchmark exp") {
		repeat(100000) {
			res.get()
		}
	}
}
