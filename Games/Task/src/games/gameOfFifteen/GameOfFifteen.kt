package games.gameOfFifteen

import board.Direction
import board.createGameBoard
import games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game = GameOfFifteen(initializer)

class GameOfFifteen(private val initializer: GameOfFifteenInitializer) : Game {
    private val board = createGameBoard<Int?>(4)
    /*
    private val solutionBoard = createGameBoard<Int?>(4)

    override fun initialize() {
        board.filter {
            it == null
        }.forEachIndexed { index, cell ->
            board[cell] = initializer.initialPermutation.getOrNull(index)
            solutionBoard[cell] = (1..15).toList().getOrNull(index)
        }
    }

    override fun canMove(): Boolean  = board.any { it == null }


    override fun hasWon(): Boolean {
        return board.getAllCells().all{cell ->
            board[cell] == solutionBoard[cell]
        }
    }

    override fun processMove(direction: Direction) {
        val toMoveCell = board.find { it == null }
        val fromMoveCell = with(board){
            toMoveCell?.getNeighbour(direction.reversed())
        }
        fromMoveCell?.let{
            board[toMoveCell!!] = board[fromMoveCell]
            board[fromMoveCell] = null
        }
    }

    override fun get(i: Int, j: Int): Int? = board.run { get(getCell(i,j)) }
     */

    override fun initialize() {
        var indexes = 0
        val values = initializer.initialPermutation

        (1..board.width).forEach {
            (1..board.width).forEach { index ->
                board[board.getCell(it, index)] = if( indexes < values.size) values[indexes++] else null
            }
        }
    }

    override fun canMove() = board.any { it == null }

    override fun hasWon(): Boolean {
        var indexes = 1
        var hasWon = false

        (1..board.width).forEach {
            (1..board.width).forEach { index ->
                hasWon = !(get(it, index) != indexes && indexes != board.width*board.width)
                indexes++
            }
        }
        return hasWon
    }

    override fun processMove(direction: Direction) {
        val to = board.find { it == null }

        val from = (board).run { to!!.getNeighbour(direction.reversed()) }

        from!!.apply {
            board[to!!] = board[from]
            board[from] = null
        }
    }

    override fun get(i: Int, j: Int): Int? = with(board) { this[getCell(i, j)] }
}