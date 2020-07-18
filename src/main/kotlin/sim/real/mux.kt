package sim.real

import sim.base.Bus
import sim.base.Value
import sim.gates.and
import sim.gates.not
import sim.gates.or

// hear I just try to implement real mux
// I like to use recursive method to design data-path
// it's important to note, I of these methods are run at startup and compiled to
// simple and/or/... gates
// there is no programming tricks here
// this implementation may has some slower results, ratter than the simulate implement

/** a simple 2 to 1 mux */
fun mux2(selector: Value, inp0: Value, inp1: Value): Value =
	(inp0 and selector.not()) or (inp1 and selector)

/** a simple 2M to M mux */
fun mux2(selector: Value, inp0: Bus, inp1: Bus): Bus =
	inp0.zip(inp1).map { (inp0, inp1) -> mux2(selector, inp0, inp1) }.toList()

/**
 * a N to 1 mux
 * recursively forms multiplexer connections
 * input lines must be 2^selector lines
 */
fun mux(selector: Bus, input: Bus): Value =
	if (selector.size <= 1) mux2(selector[0], input[0], input[1])
	else mux2(
		selector[selector.size - 1],
		mux(selector.dropLast(1), input.slice(0 until input.size / 2)),
		mux(selector.dropLast(1), input.slice(input.size until input.size))
	)

/**
 * a N*M to M mux,
 * I rewrite recursive part here, to prevent extra recursive
 */
fun mux(selector: Bus, input: List<Bus>): Bus =
	if (selector.size <= 1) mux2(selector[0], input[0], input[1])
	else mux2(
		selector[selector.size - 1],
		mux(selector.dropLast(1), input.slice(0 until input.size / 2)),
		mux(selector.dropLast(1), input.slice(input.size until input.size))
	)
