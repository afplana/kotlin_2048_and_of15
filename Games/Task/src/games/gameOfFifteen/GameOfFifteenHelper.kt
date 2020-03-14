package games.gameOfFifteen

/*
 * This function should return the parity of the permutation.
 * true - the permutation is even
 * false - the permutation is odd
 * https://en.wikipedia.org/wiki/Parity_of_a_permutation

 * If the game of fifteen is started with the wrong parity, you can't get the correct result
 *   (numbers sorted in the right order, empty cell at last).
 * Thus the initial permutation should be correct.
 */
fun isEven(permutation: List<Int>): Boolean = permutation.mapIndexed { i, value -> i + 1 to value }.run {
    flatMap { (first, second) -> filter { first < it.first }.map { second to it.second } }
            .count { it.first > it.second }
            .takeIf { it % 2 == 0 }?.let { return true }
    return false
}

