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
fun flipflopBlock(clock: Value, data: Value): Value =
	block(block(data, notS(clock)), clock)

/**
 * D-FlipFlop, master slave pattern
 * create by two d-gated-latches
 */
fun flipflopMS(clock: Value, data: Value): Value =
	gatedLatchD(notS(clock), gatedLatchD(clock, data))

/**
 * D-FlipFlop, rising edge
 * nand gates
 * https://en.wikipedia.org/wiki/Flip-flop_(electronics)
 * https://upload.wikimedia.org/wikipedia/commons/9/99/Edge_triggered_D_flip_flop.svg
 */
fun flipflopRE(clock: Value, data: Value): Value {
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

/** creates and returns a bus of rising-edge-flipflops */
fun flipflopRE(clock: Value, newDataBus: List<Value>): List<Value> =
	newDataBus.map { flipflopRE(clock, it) }.toList()

/** creates a bus of master-slave-flipflops */
fun flipflopMS(clock: Value, newDataBus: List<Value>): List<Value> =
	newDataBus.map { flipflopMS(clock, it) }.toList()

/** creates a bus of block-flipflops */
fun flipflopBlock(clock: Value, newDataBus: List<Value>): List<Value> =
	newDataBus.map { flipflopBlock(clock, it) }.toList()

private fun testBenchmark() {
	val clock = mut(true, "Clock")
	val data = mut(true, "Data")
	repeat(10) {
		println("************** $it ***************")

		listOf(
			"FF" to flipflopRE(clock, data),
			"FF-MS" to flipflopMS(clock, data),
			"FF-RE" to flipflopRE(clock, data)
		).forEach { (name, ff) ->
			test("benchmark $name") {
				repeat(10000000) {
					if (it % 7 == 0) data.toggle()
					clock.toggle()
					ff.get()
				}
			}
			System.gc()
		}
	}
}

private fun test1() {
	val clock = mut(true, "Clock")
	val data = mut(true, "Data")
	val res = flipflopRE(clock, data)

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

	(0..25).forEach {
		clock.toggle()
		if (it % 5 == 0) data.toggle()
		println("i:$it,\tclock:$clock,\tdata:$data,\tres:$res")
		Thread.sleep(10);
	}
}

internal fun main() {
	test1()
}
