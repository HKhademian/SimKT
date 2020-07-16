package sim

import kotlin.math.pow

infix fun Int.pow(other: Number) =
  this.toDouble().pow(other.toDouble()).toInt()