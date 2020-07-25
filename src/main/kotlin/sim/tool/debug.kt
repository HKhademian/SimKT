package sim.tool

import sim.base.MultiInputElement
import sim.base.SingleInputElement
import sim.base.Value
import sim.base.toInt

fun Any.println() =
	println(debugWrite())

/** every class that support beautiful custom debug print, must implements this */
interface DebugWriter {
	/** this method write a pretty debug information about object to given buffer */
	fun writeDebug(buffer: StringBuffer)
}

fun Value.writeTo(buffer: StringBuffer): StringBuffer =
	buffer.append("$title=${toInt()}")

fun DebugWriter.writeTo(buffer: StringBuffer) =
	this.writeDebug(buffer)


/** pretty write value in buffer */
fun Any.debugWrite(
	buffer: StringBuffer = StringBuffer(),
	prefix: String = "",
	childrenPrefix: String = "",
	hierarchy: MutableList<Any> = mutableListOf()
): StringBuffer {
	val contains = hierarchy.contains(this)

	buffer.append(prefix)
	when (this) {
		is DebugWriter -> writeTo(buffer)
		is Value -> writeTo(buffer)
		else -> buffer.append(this.toString())
	}
	if(contains)
		buffer.append(" #LOOP#")
	buffer.append("\n")

	fun writeChild(child: Value?, hasNext: Boolean, childrenPrefix: String): StringBuffer {
		val pref = childrenPrefix + if (hasNext) "├─── " else "└─── "
		return if (child != null) {
			val childPref = childrenPrefix + if (hasNext) "│    " else "     "
			child.debugWrite(buffer, pref, childPref, hierarchy)
		} else {
			buffer.append(pref)
			buffer.append("{null}")
			buffer.append('\n')
			buffer
		}
	}

	if (!contains) {
		hierarchy.add(this)

		if (this is MultiInputElement && this.inputs.isNotEmpty()) {
			this.inputs.dropLast(1).forEach {
				writeChild(it, true, childrenPrefix)
			}
			writeChild(this.inputs.last(), false, childrenPrefix)
		} else if (this is SingleInputElement) {
			writeChild(this.input, false, childrenPrefix)
		}
	}

	return buffer
}
