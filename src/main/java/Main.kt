import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    when (args[0]) {
        "p" -> {
            getPermutation(args.toList().subList(1, args.size - 1), args[args.size - 1].toInt()).forEach {
                println(it.toString())
            }
        }
        "c" -> {
            getCombination(args.toList().subList(1, args.size - 1), args[args.size - 1].toInt()).forEach {
                println(it.toString())
            }
        }
    }
}

fun <T> getPermutation(source: List<T>, count: Int): List<List<T>> {
    // 재귀함수의 종료조건
    when {
        // 가능하지 않음!
        count < 0 ->
            throw IndexOutOfBoundsException()

        // 0개를 뽑으면 그냥 빈 결과
        count == 0 ->
            return listOf(emptyList())

        // 1개를 뽑는 경우는 각각의 원소가 결과
        count == 1 ->
            return source.map { listOf(it) }
    }

    val result: MutableList<List<T>> = ArrayList()
    // 한 원소를 고름
    source.forEachIndexed { i, element ->
        // 고른 원소를 포함한 경우
        val otherPermutations = getPermutation(source.filterIndexed { index, _ -> index != i }, count - 1)
        val combinedPermutations = otherPermutations.map {
            it.toMutableList()
        }
        combinedPermutations.forEach {
            it.add(0, element)
        }

        // 고른 원소를 포함하지 않은 경우
        result.addAll(combinedPermutations)
    }

    return result
}


fun <T> getCombination(source: List<T>, count: Int): List<List<T>> {
    // 재귀함수의 종료 조선
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


    // 첫 번째 원소를 포함(모든 원소에 대해 하면 순열이 되므로 하나만 골라야 함)
    val otherCombination = getCombination(source.subList(1, source.size), count - 1)

    val combinedCombination = otherCombination.map {
        it.toMutableList()
    }
    combinedCombination.forEach {
        result.add(it.apply { add(0, source[0]) })
    }

    // 첫 번째 원소를 제외
    result.addAll(getCombination(source.subList(1, source.size), count))

    return result
}
