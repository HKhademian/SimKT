package sim.tool

import sim.base.Bus
import sim.base.MutableSingleInputElement
import sim.base.Value

fun <T> Value.keepSource(newValue: Value, action: (it: Value, source: Value, newValue: Value) -> T): T {
	this as MutableSingleInputElement
	val source = this.input
	this.input = newValue
	val result = action(this, source, newValue)
	this.input = source
	return result
}

fun <T> Bus.keepSource(newValue: Bus, action: (it: Bus, source: Bus, newValue: Bus) -> T): T {
	val me = this.map { it as MutableSingleInputElement }
	val source = me.map { it.input }
	me.forEachIndexed { i, it -> it.input = newValue[i] }
	val result = action(this, source, newValue)
	me.forEachIndexed { i, it -> it.input = source[i] }
	return result
}
