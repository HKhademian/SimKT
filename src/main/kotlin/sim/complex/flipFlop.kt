package sim.complex

import sim.base.MutableMultiInputElement
import sim.base.Value
import sim.base.mut
import sim.tool.println
import sim.tool.test
import sim.expriment.nand as nandS
import sim.expriment.not as notS

/**
 * https://en.wikipedia.org/wiki/Flip-flop_(electronics)#/media/File:D-Type_Flip-flop_Diagram.svg
 */
private fun block(input1: Value, input2: Value): Value {
	val nand1 = nandS(input1, input2)
	val nand2 = nandS(nand1, input2)
	val nand3 = nandS(nand1)
	val nand4 = nandS(nand2, nand3)
	(nand3 as MutableMultiInputElement).inputs.add(nand4)
	return nand3
}

/**
 * https://en.wikipedia.org/wiki/Flip-flop_(electronics)#/media/File:D-Type_Flip-flop_Diagram.svg
 */
fun flipflop(clock: Value, data: Value): Value =
	block(block(data, notS(clock)), clock)

/**
 * D-FlipFlop, master slave pattern
 * create by two d-gated-latches
 */
fun flipFlopMS(clock: Value, data: Value): Value =
	gatedLatchD(notS(clock), gatedLatchD(clock, data))

/**
 * D-FlipFlop, rising edge
 * nand gates
 * https://en.wikipedia.org/wiki/Flip-flop_(electronics)
 * https://upload.wikimedia.org/wikipedia/commons/9/99/Edge_triggered_D_flip_flop.svg
 */
fun flipFlopRE(clock: Value, data: Value): Value {
	val nand1 = nandS()
	val nand2 = nandS()
	val nand3 = nandS()
	val nand4 = nandS()
	val nand5 = nandS()
	val nand6 = nandS()

	(nand1 as MutableMultiInputElement).inputs.add(nand2)
	(nand1 as MutableMultiInputElement).inputs.add(nand4)

	(nand2 as MutableMultiInputElement).inputs.add(clock)
	(nand2 as MutableMultiInputElement).inputs.add(nand1)

	(nand3 as MutableMultiInputElement).inputs.add(clock)
	(nand3 as MutableMultiInputElement).inputs.add(nand2)
	(nand3 as MutableMultiInputElement).inputs.add(nand4)

	(nand4 as MutableMultiInputElement).inputs.add(data)
	(nand4 as MutableMultiInputElement).inputs.add(nand3)

	(nand5 as MutableMultiInputElement).inputs.add(nand6)
	(nand5 as MutableMultiInputElement).inputs.add(nand2)

	(nand6 as MutableMultiInputElement).inputs.add(nand5)
	(nand6 as MutableMultiInputElement).inputs.add(nand3)

	return nand5
}

internal fun main() {
	val clock = mut(true, "Clock")
	val data = mut(true, "Data")
	val res = flipflop(clock, data)

	res.get().println()
	res.println()


	data.reset()
	println("clock:$clock,\tdata:$data,\tres:${res}")

	clock.toggle()
	data.set()
	println("clock:$clock,\tdata:$data,\tres:${res}")

	clock.toggle()
	data.reset()
	println("clock:$clock,\tdata:$data,\tres:${res}")

	data.set()
	println("clock:$clock,\tdata:$data,\tres:${res}")

	clock.toggle()
	println("clock:$clock,\tdata:$data,\tres:${res}")

	clock.toggle()
	println("clock:$clock,\tdata:$data,\tres:${res}")

	clock.toggle()
	println("clock:$clock,\tdata:$data,\tres:${res}")

	test("rising edge") {
		clock.toggle()
		println("clock:$clock,\tdata:$data,\tres:${res}")
		clock.toggle()
		println("clock:$clock,\tdata:$data,\tres:${res}")
		clock.toggle()
		println("clock:$clock,\tdata:$data,\tres:${res}")
		clock.toggle()
		println("clock:$clock,\tdata:$data,\tres:${res}")


		println()
		data.reset()
		clock.toggle()
		res.get()
		clock.toggle()
		res.get()
		println("clock:$clock,\tdata:$data,\tres:${res}")
		clock.toggle()
		println("clock:$clock,\tdata:$data,\tres:${res}")
		clock.toggle()
		println("clock:$clock,\tdata:$data,\tres:${res}")

		println()
		clock.set()
		data.set()
		println("clock:$clock,\tdata:$data,\tres:${res}")
		clock.toggle()
		println("clock:$clock,\tdata:$data,\tres:${res}")
		clock.toggle()
		println("clock:$clock,\tdata:$data,\tres:${res}")

		println()
		data.set()
		println("clock:$clock,\tdata:$data,\tres:${res}")
		println("clock:$clock,\tdata:$data,\tres:${res}")

		println()
		clock.reset()
		println("clock:$clock,\tdata:$data,\tres:${res}")
		println("clock:$clock,\tdata:$data,\tres:${res}")

		println()
		data.set()
		println("clock:$clock,\tdata:$data,\tres:${res}")
		println("clock:$clock,\tdata:$data,\tres:${res}")
	}

//	(0..25).forEach {
//		clock.toggle()
//		if (it % 5 == 0) data.toggle()
//		println("i:$it,\tclock:$clock,\tdata:$data,\tres:$res")
//		Thread.sleep(10);
//	}
}
