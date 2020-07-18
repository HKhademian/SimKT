package sim

import sim.base.Bus
import sim.base.Element
import sim.base.MultiInputElement
import sim.base.Value

/// pretty write value in buffer
fun Value.write(
	buffer: StringBuffer = StringBuffer(),
	prefix: String = "",
	childrenPrefix: String = ""
): StringBuffer {
	buffer.append(prefix)
	buffer.append(title)
	buffer.append("\n")

	fun writeChild(child: Value?, hasNext: Boolean, childrenPrefix: String): StringBuffer {
		val pref = childrenPrefix + if (hasNext) "├─── " else "└─── "
		return if (child != null) {
			val childPref = childrenPrefix + if (hasNext) "│    " else "     "
			child.write(buffer, pref, childPref)
		} else {
			buffer.append(pref)
			buffer.append("{null}")
			buffer.append('\n')
			buffer
		}
	}

	if (this is MultiInputElement && this.inputs.isNotEmpty()) {
		this.inputs.dropLast(1).forEach {
			writeChild(it, true, childrenPrefix)
		}
		writeChild(this.inputs.last(), false, childrenPrefix)
	} else if (this is Element) {
		writeChild(this.input, false, childrenPrefix)
	}
	return buffer
}

fun Value.println() =
	println(write())

fun Bus.println() =
	forEach { it.println() }
