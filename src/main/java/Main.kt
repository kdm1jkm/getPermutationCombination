import java.util.*
import kotlin.collections.ArrayList

fun main() {
    getCombination(listOf(1, 2, 3, 4), 2).forEach {
        println(it.toString())
    }
}

// source에 같은 원소가 있을 경우를 고려하지 않음
fun <T> getPermutation(source: List<T>, count: Int): List<List<T>> {
    when {
        count < 0 ->
            throw IndexOutOfBoundsException()

        count == 0 ->
            return listOf(emptyList())

        count == 1 ->
            return source.map { listOf(it) }

    }

    val result: MutableList<List<T>> = ArrayList()
    source.forEachIndexed { i, element ->
        val otherPermutations = getPermutation(source.filterIndexed { index, _ -> index != i }, count - 1)
        val combinedPermutations = otherPermutations.map {
            it.toMutableList()
        }
        combinedPermutations.forEach {
            it.add(0, element)
        }
        result.addAll(combinedPermutations)
    }

    return result
}

fun <T> getCombination(source: List<T>, count: Int): List<List<T>> {
    when {
        count < 0 ->
            throw IndexOutOfBoundsException()

        count == 0 -> {
            return Collections.emptyList()
        }
        source.size == count -> {
            return listOf(source)
        }

        count == 1 -> {
            return source.map { listOf(it) }
        }

    }

    val result: MutableList<List<T>> = ArrayList()
    for ((i, element) in source.withIndex()) {
//        if (i > source.size - count) break
        val otherCombination = getCombination(source.subList(i + 1, source.size), count - 1)

        val combinedCombination = otherCombination.map {
            it.toMutableList()
        }
        combinedCombination.forEach {
            result.add(it.apply { add(0, element) })
        }
    }

    return result
}
