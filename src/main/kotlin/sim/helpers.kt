package sim

import sim.base.Value
import sim.base.toBus
import sim.base.toInt
import kotlin.math.pow

/**
 * this method provides:
 * 1. sign/zero/one extention: when provide `n` value
 * 2. shift: when provide `shift` value
 *
 * sign-extend if `signed`
 * zero-extend if `extend`=`Value.Zero`
 * one-extend if `extend`=`Value.One`
 *
 * all these can use together,
 *
 * sign-extend, zero-extend,
 * shift-left-arth, shift-left-logical,
 * shift-right-arth, shift-right-logical,
 */
@JvmOverloads
fun List<Value>.extend(n: Int = 0, shift: Int = 0, signed: Boolean = false, extend: Value = Value.ZERO): List<Value> {
	val size = this.size
	val len = if (n > 0) n else size
	val last = this.last()

	return (0 until len)
		.asSequence()
		.map { it - shift } // shift ability
		.map { i ->
			when {
				i < 0 -> extend
				i >= size -> if (!signed) extend else last
				else -> this[i]
			}
		}
		.toList()
}


/** zero-extend, default to 32 */
@JvmOverloads
fun List<Value>.zeroEx(n: Int = 32) =
	extend(n = n)

/** one-extend, default to 32 */
@JvmOverloads
fun List<Value>.oneEx(n: Int = 32) =
	extend(n = n, extend = Value.ONE)

/** sign-extend, default to 32 */
@JvmOverloads
fun List<Value>.signEx(n: Int = 32) =
	extend(n = n, signed = true)


/** signed, left:+, right:- */
@JvmOverloads
fun List<Value>.shift(shift: Int, signed: Boolean = false, n: Int = 0) =
	extend(n = n, shift = shift, signed = signed)


/** multiply by 2, with shift +1, do not changes system bits (number of values) */
fun List<Value>.mul2() =
	extend(shift = 1)

/** multiply by 4, with shift +2, do not changes system bits (number of values) */
fun List<Value>.mul4() =
	extend(shift = 2)

/** divide by 2, with shift -1, do not changes system bits (number of values) */
fun List<Value>.div2() =
	extend(shift = -1)

/** divide by 4, with shift -2, do not changes system bits (number of values) */
fun List<Value>.div4() =
	extend(shift = -2)


/** converts to 32bit system, zero extended */
fun List<Value>.x32() =
	extend(n = 32, signed = false)


/** converts to 32bit system, signed extended */
fun List<Value>.x32signed() =
	extend(n = 32, signed = true)


// test abilities
internal fun main() {
	val src = 1374.toBus(12) // 12-bit value list
	// printing is ltr 0:n
	// but correct binary is ltr n:0
	// so do not confuse
	println(
		"""
		|src: 		${src.toInt()} 		= ${src}
		|x32: 		${src.x32().toInt()} 		= ${src.x32()}
		|s32: 		${src.x32signed().toInt()} 		= ${src.x32signed()}
		|
		|src: 		${src.toInt()} 		= ${src}
		|mul2:		${src.mul2().toInt()} 		= ${src.mul2()}
		|mul4:		${src.mul4().toInt()} 		= ${src.mul4()}
		|div2:		${src.div2().toInt()} 		= ${src.div2()}
		|div4:		${src.div4().toInt()} 		= ${src.div4()}
		|
		|src: 		${src.toInt()} 		= ${src}
		|sll1:		${src.extend(shift = 1).toInt()} 		= ${src.extend(shift = 1)}
		|sla1:		${src.extend(shift = 1, signed = true).toInt()} 		= ${src.extend(shift = 1, signed = true)}
		|srl1:		${src.extend(shift = -1).toInt()} 		= ${src.extend(shift = -1)}
		|sra1:		${src.extend(shift = -1, signed = true).toInt()} 		= ${src.extend(shift = -1, signed = true)}
		|""".trimMargin()
	)
}
