package sim.complex

import sim.base.MutableMultiInputElement
import sim.base.Value
import sim.base.mut
import sim.tool.test
import sim.expriment.nand as andS
import sim.expriment.nor as norS
import sim.expriment.not as notS

/**
 * simple SR-latch
 * https://en.wikibooks.org/wiki/Electronics/Flip_Flops
 * https://en.wikibooks.org/wiki/Electronics/Flip_Flops#/media/File:RS_Flip-flop_(NOR).svg
 */
fun latchSR(set: Value, reset: Value): Value {
	val nor1 = norS(reset)
	val nor2 = norS(set)

	(nor1 as MutableMultiInputElement).inputs.add(nor2)

	(nor2 as MutableMultiInputElement).inputs.add(nor1)

	return nor1
}

/**
 * gated SR-latch or clocked ST-Latch
 * https://en.wikibooks.org/wiki/Electronics/Flip_Flops
 * https://www.allaboutcircuits.com/uploads/articles/gated-sr-latch-truth-table.jpg
 */
fun gatedLatchSR(en: Value, set: Value, reset: Value): Value =
	latchSR(andS(en, reset), andS(en, set))

/**
 * gated D-latch or clocked D-FlipFlop
 * https://en.wikibooks.org/wiki/Electronics/Flip_Flops
 * https://www.allaboutcircuits.com/uploads/articles/internal-logic-d-latch.jpg
 */
fun gatedLatchD(en: Value, data: Value): Value =
	gatedLatchSR(en, data, notS(data))

internal fun main() {
	val EN = mut(true, "EN")
	val S = mut(true, "S")
	val R = mut(true, "R")
	val D = mut(true, "D")

	val ld = gatedLatchD(EN, D)
	val lsr = gatedLatchSR(EN, S, R)

	test("D-GatedLatch init ") { ld.get() }

	test("D-GatedLatch init ") { ld.get() }

	EN.set(false); D.set(false)
	test("D-GatedLatch EN=0 D=0") { ld.get() }

	EN.set(true); D.set(false)
	test("D-FlipFlop EN=1 D=0") { ld.get() }

	EN.set(false); D.set(true)
	test("D-GatedLatch EN=0 D=1") { ld.get() }

	EN.set(true); D.set(true)
	test("D-GatedLatch EN=1 D=1") { ld.get() }


	test("SR-GatedLatch init ") { lsr.get() }
	test("SR-GatedLatch EN=0 S=0 R=0") {
		EN.set(false); S.set(false); R.set(false); lsr.get()
	}
	test("SR-GatedLatch EN=0 S=1 R=0") {
		EN.set(false); S.set(true); R.set(false); lsr.get()
	}
	test("SR-GatedLatch EN=0 S=0 R=1") {
		EN.set(false); S.set(false); R.set(true); lsr.get()
	}
	test("SR-GatedLatch EN=1 S=0 R=0") {
		EN.set(true); S.set(false); R.set(false); lsr.get()
	}
	test("SR-GatedLatch EN=1 S=1 R=0") {
		EN.set(true); S.set(true); R.set(false); lsr.get()
	}
	test("SR-GatedLatch EN=1 S=0 R=1") {
		EN.set(true); S.set(false); R.set(true); lsr.get()
	}
	test("SR-GatedLatch EN=1 S=1 R=0") {
		EN.set(true); S.set(true); R.set(false); lsr.get()
	}
	test("SR-GatedLatch EN=1 S=1 R=1") {
		EN.set(true); S.set(true); R.set(true); lsr.get()
	}
	test("SR-GatedLatch EN=1 S=1 R=1") {
		EN.set(true); S.set(true); R.set(true);lsr.get()
	}
	test("SR-GatedLatch EN=1 S=1 R=1") {
		EN.set(true); S.set(true); R.set(true);lsr.get()
	}

}
