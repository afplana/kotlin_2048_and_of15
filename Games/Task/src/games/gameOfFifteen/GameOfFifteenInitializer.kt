package games.gameOfFifteen

import java.util.*
import java.util.Collections.swap

interface GameOfFifteenInitializer {
    /*
     * Even permutation of numbers 1..15
     * used to initialized the first 15 cells on a board.
     * The last cell is empty.
     */
    val initialPermutation: List<Int>
}

class RandomGameInitializer : GameOfFifteenInitializer {
    /*
     * Generate a random permutation from 1 to 15.
     * `shuffled()` function might be helpful.
     * If the permutation is not even, make it even (for instance,
     * by swapping two numbers).
     */
    override val initialPermutation by lazy {
        val permutation = (1 until 15).toList().shuffled(Random())
        if (!isEven(permutation)) permutation.apply {swap(this, 0, 1)}
        permutation
    }
}

