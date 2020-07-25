package sim.complex

import sim.base.*
import sim.tool.testOn
import sim.expriment.nor as norS

// https://www.allaboutcircuits.com/uploads/articles/gated-sr-latch-truth-table.jpg
fun latchSR(en: Value, set: Value, reset: Value): Value =
	mut(false, "q").also { q ->
		q.set(norS(and(en, reset), nor(q, and(en, set))))
	}

// https://www.allaboutcircuits.com/uploads/articles/internal-logic-d-latch.jpg
fun latchD(en: Value, data: Value): Value =
	latchSR(en, data, not(data))

internal fun main() {
	val EN = mut(true)
	val S = mut(true)
	val R = mut(true)
	val D = mut(true)

	val ld = latchD(EN, D)
	val lsr = latchSR(EN, S, R)

	testOn(ld, "D-Latch init ")

	testOn(ld, "D-Latch init ")

	EN.set(false); D.set(false)
	testOn(ld, "D-Latch EN=0 D=0")

	EN.set(true); D.set(false)
	testOn(ld, "D-Latch EN=1 D=0")

	EN.set(false); D.set(true)
	testOn(ld, "D-Latch EN=0 D=1")

	EN.set(true); D.set(true)
	testOn(ld, "D-Latch EN=1 D=1")


	testOn(lsr, "SR-Latch init ")
	testOn(lsr, "SR-Latch EN=0 S=0 R=0") {
		EN.set(false); S.set(false); R.set(false)
	}
	testOn(lsr, "SR-Latch EN=0 S=1 R=0") {
		EN.set(false); S.set(true); R.set(false)
	}
	testOn(lsr, "SR-Latch EN=0 S=0 R=1") {
		EN.set(false); S.set(false); R.set(true)
	}
	testOn(lsr, "SR-Latch EN=1 S=0 R=0") {
		EN.set(true); S.set(false); R.set(false)
	}
	testOn(lsr, "SR-Latch EN=1 S=1 R=0") {
		EN.set(true); S.set(true); R.set(false)
	}
	testOn(lsr, "SR-Latch EN=1 S=0 R=1") {
		EN.set(true); S.set(false); R.set(true)
	}
	testOn(lsr, "SR-Latch EN=1 S=1 R=0") {
		EN.set(true); S.set(true); R.set(false)
	}
	testOn(lsr, "SR-Latch EN=1 S=1 R=1") {
		EN.set(true); S.set(true); R.set(true)
	}
	testOn(lsr, "SR-Latch EN=1 S=1 R=1") {
		EN.set(true); S.set(true); R.set(true)
	}
	testOn(lsr, "SR-Latch EN=1 S=1 R=1") {
		EN.set(true); S.set(true); R.set(true)
	}

}
