package sim.tool

import sim.base.*

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
	childrenPrefix: String = ""
): StringBuffer {
	buffer.append(prefix)
	when (this) {
		is DebugWriter -> writeTo(buffer)
		is Value -> writeTo(buffer)
		else -> buffer.append(this.toString())
	}
	buffer.append("\n")

	fun writeChild(child: Value?, hasNext: Boolean, childrenPrefix: String): StringBuffer {
		val pref = childrenPrefix + if (hasNext) "├─── " else "└─── "
		return if (child != null) {
			val childPref = childrenPrefix + if (hasNext) "│    " else "     "
			child.debugWrite(buffer, pref, childPref)
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
	} else if (this is SingleInputElement) {
		writeChild(this.input, false, childrenPrefix)
	}
	return buffer
}
