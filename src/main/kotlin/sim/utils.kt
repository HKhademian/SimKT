/// src: https://stackoverflow.com/a/50078501/1803735
@file:Suppress("NOTHING_TO_INLINE", "RedundantVisibilityModifier")

package sim

import kotlin.math.pow

infix fun Int.pow(other: Number) =
	this.toDouble().pow(other.toDouble()).toInt()

/**
 * Returns a list of lists, each built from elements of all lists with the same indexes.
 * Output has length of shortest input list.
 */
public inline fun <T> List<List<T>>.zipLists(): List<List<T>> {
	return zipLists(*this.toTypedArray(), transform = { it })
}

/**
 * Returns a list of lists, each built from elements of all lists with the same indexes.
 * Output has length of shortest input list.
 */
public inline fun <T> zipLists(vararg lists: List<T>): List<List<T>> {
	return zipLists(*lists, transform = { it })
}

/**
 * Returns a list of values built from elements of all lists with same indexes using provided [transform].
 * Output has length of shortest input list.
 */
public inline fun <T, V> zipLists(vararg lists: List<T>, transform: (List<T>) -> V): List<V> {
	val minSize = lists.map(List<T>::size).min() ?: return emptyList()
	val list = mutableListOf<V>()

	val iterators = lists.map { it.iterator() }
	var i = 0
	while (i < minSize) {
		list.add(transform(iterators.map { it.next() }))
		i++
	}

	return list
}
