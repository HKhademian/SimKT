package sim.expriment

/// tutorialspoint.com/digital_circuits/digital_circuits_decoders.htm

import sim.base.*
import sim.base.Value.Companion.ONE

fun dec1(en: Value, a0: Value): Bus {
	return bus(
		and(en, not(a0)),
		and(en, a0)
	)
}

fun dec2(en: Value, a0: Value, a1: Value): Bus {
	val not_a0 = not(a0)
	val not_a1 = not(a1)
	return bus(
		and(en, not_a0, not_a1),
		and(en, a0, not_a1),
		and(en, not_a0, a1),
		and(en, a0, a1)
	)
}

fun dec3(en: Value, a0: Value, a1: Value, a2: Value): Bus =
	merge(
		dec2(and(en, not(a2)), a0, a1),
		dec2(and(en, a2), a0, a1)
	)

fun dec4(en: Value, a0: Value, a1: Value, a2: Value, a3: Value): Bus =
	merge(
		dec3(and(en, not(a3)), a0, a1, a2),
		dec3(and(en, a3), a0, a1, a2)
	)

fun dec(en: Value, inputs: List<Value>): Bus = when {
	inputs.size == 2 -> dec2(en, inputs[0], inputs[1])
	inputs.size == 1 -> dec1(en, inputs[0])
	inputs.isEmpty() -> listOf()
	else ->
		merge(
			dec(and(en, not(inputs.last())), inputs.dropLast(1)),
			dec(and(en, inputs.last()), inputs.dropLast(1))
		)
}

fun dec(en: Value, vararg inputs: Value): Bus =
	dec(en, listOf(*inputs))

internal fun main() {
	val EN = mut(true)
	val A = mut(true)
	val B = mut(true)
	val C = mut(true)
	val D = mut(true)
	val E = mut(true)

	val decs = listOf(
		"d1" to dec1(EN, A),
		"d1n" to dec(EN, A),
		"d2" to dec2(EN, A, B),
		"d2n" to dec(EN, A, B),
		"d3" to dec3(EN, A, B, C),
		"d3n" to dec(EN, A, B, C),
		"d4" to dec4(EN, A, B, C, D),
		"d4n" to dec(EN, A, B, C, D),
		"d5n" to dec(EN, A, B, C, D, E)
	)

	decs.forEach { println(it) }
	println()

	EN.set(false)
	decs.forEach { println(it) }
	println()

	EN.set(false)
	A.set(false)
	decs.forEach { println(it) }
	println()

	EN.set(true)
	A.set(false)
	decs.forEach { println(it) }
	println()

	(0 until 32).forEach {
		println("$it: " + dec(ONE, it.toBus(5)))
	}

}
