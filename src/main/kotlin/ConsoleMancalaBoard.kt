import ansi.AnsiColor
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

abstract class ConsoleMancalaBoard {
    protected var allyStore: Int
    protected var allyPits: IntArray
    protected var enemyStore: Int
    protected var enemyPits: IntArray

    protected lateinit var difficulty: OnePlayerDifficultyLevel

    enum class OnePlayerDifficultyLevel {
        TRIVIAL,
        EASY,
        MEDIUM,
        HARD,
        IMPOSSIBLE
    }

    enum class boardInitType {
        RANDOM,
        ORIGINAL
    }
    constructor(boardInitType: boardInitType) {
        this.allyStore = 0
        this.enemyStore = 0

        when(boardInitType) {
            ConsoleMancalaBoard.boardInitType.RANDOM   -> {
                val array = createRandomArray()
                this.allyPits = array.copyOf()
                this.enemyPits = array.copyOf()
            }
            ConsoleMancalaBoard.boardInitType.ORIGINAL -> {
                this.allyPits = intArrayOf(4, 4, 4, 4, 4, 4)
                this.enemyPits = intArrayOf(4, 4, 4, 4, 4, 4)
            }
        }
    }
    private fun createRandomArray(): IntArray {
        // Create a random array of size 6 with values from 1 to 5
        val randomArray = IntArray(6)
        for (i in 0..5) {
            // Ensures that no pocket can initially end in the store
            do {
                randomArray[i] = Random.nextInt(1, 6)
            } while (randomArray[i] == i + 1)
        }
        return randomArray
    }

    constructor(allyStore: Int, allyPits: IntArray, enemyStore: Int, enemyPits: IntArray) {
        this.allyStore = allyStore
        this.allyPits = allyPits
        this.enemyStore = enemyStore
        this.enemyPits = enemyPits
    }


    fun playGame() {
        if (selectNumPlayers()) playOnePlayer()
        else playTwoPlayers()

        endOfGameLogic()
        endMessage()
    }

    /**
     * Returns true if they want one player
     */
    private fun selectNumPlayers(): Boolean {
        println("How many players do you want: 1 or 2?")

        return getInputFromValidInts(intArrayOf(1, 2)) == 1
    }

    private fun playOnePlayer() {
        //first check for difficulty level
        askDifficultyLevel()
        when(difficulty) {
            OnePlayerDifficultyLevel.TRIVIAL    -> playTrivial()
            OnePlayerDifficultyLevel.EASY       -> playLeveled(1)
            OnePlayerDifficultyLevel.MEDIUM     -> playLeveled(2)
            OnePlayerDifficultyLevel.HARD       -> playLeveled(3)
            OnePlayerDifficultyLevel.IMPOSSIBLE -> playLeveled(10)
        }
    }

    private fun playTrivial() {
        var isPlayersTurn = true
        while (!isGameOver()) {
            if (isPlayersTurn && !takeTurn(true)) {
                isPlayersTurn = false
            } else if (!isPlayersTurn) {
                //picks a random pit, but in a loop in case the AI should get an extra turn
                do {
                    println(this)
                    val validOptions = enemyPits.indices.filter { enemyPits[it] > 0 }.toList()
                    val choice = validOptions.random()
                    Thread.sleep(Random.nextLong(1_000L, 2_500L))
                    println("${playerColor(false)}I${playerColor(null)} choose pocket ${choice + 1}")
                    Thread.sleep(500L)
                } while (pickUpPit(choice, isAlliedTurn = false, isAlliedSide = false) && !isGameOver())
                isPlayersTurn = true
            }
        }
    }

    private fun playLeveled(level: Int) {
        var isPlayersTurn = true
        while (!isGameOver()) {
            if (isPlayersTurn && !takeTurn(true)) {
                isPlayersTurn = false
            } else if (!isPlayersTurn) {
                do {
                    println(this)
                    val choice = alphaBetaMakeChoice(delta = 0, goodPits = allyPits.copyOf(), badPits = enemyPits.copyOf(),
                                                     depth = level, alpha = Int.MIN_VALUE, beta = Int.MAX_VALUE,
                                                     isMaximizingPlayer = true)
                    Thread.sleep(Random.nextLong(1_000L, 1_500L))
                    println("${playerColor(false)}I${playerColor(null)} choose pocket ${choice.first + 1}")
                    Thread.sleep(500L)
                } while (pickUpPit(choice.first, isAlliedTurn = false, isAlliedSide = false) && !isGameOver())
                isPlayersTurn = true
            }
        }
    }

    /**
     * Returns a pair of <best choice, estimated increase>
     */
    private fun alphaBetaMakeChoice(delta: Int, goodPits: IntArray, badPits: IntArray, depth: Int,
                                    alpha: Int, beta: Int, isMaximizingPlayer: Boolean): Pair<Int, Int> {
        if (depth == 0) {
            //as low as we want to search
            return Pair(-1, delta)
        } else if (goodPits.all { it == 0 } || badPits.all { it == 0 }) {
            //if you end early from end of game, sometimes you need to move stones to your stores.
            // This affects the AI's decisions
            val newBoard = createBoard(allyStore = 0, allyPits = goodPits.copyOf(), enemyStore = 0, enemyPits = badPits.copyOf())
            newBoard.endOfGameLogic()
            return Pair(-1, delta + newBoard.enemyStore - newBoard.allyStore)
        }

        var alpha2 = alpha  //because you can't update the parameter values
        var beta2 = beta    //because you can't update the parameter values

        if (isMaximizingPlayer) {
            var maxEval = Pair(-1, Int.MIN_VALUE)
            //for each child position
            for (i in 0..5) {
                if (badPits[i] > 0) {
                    val newBoard = createBoard(allyStore = 0, allyPits = goodPits.copyOf(), enemyStore = 0, enemyPits = badPits.copyOf())
                    val anotherTurn = newBoard.pickUpPit(i, isAlliedTurn = false, isAlliedSide = false)
                    val increase = newBoard.enemyStore
                    var eval = alphaBetaMakeChoice(delta = delta + increase, goodPits = newBoard.allyPits,
                                                   badPits = newBoard.enemyPits, depth = depth - 1, alpha = alpha2,
                                                   beta = beta2, isMaximizingPlayer = anotherTurn)
                    eval = Pair(i, eval.second)
                    if (eval.second > maxEval.second) maxEval = eval
                    alpha2 = max(alpha2, eval.second)
                    if (beta2 <= alpha2) break
                }
            }
            return maxEval
        } else {
            var minEval = Pair(-1, Int.MAX_VALUE)
            //for each child position
            for (i in 0..5) {
                if (goodPits[i] > 0) {
                    val newBoard = createBoard(allyStore = 0, allyPits = goodPits.copyOf(), enemyStore = 0, enemyPits = badPits.copyOf())
                    val anotherTurn = newBoard.pickUpPit(i, isAlliedTurn = true, isAlliedSide = true)
                    val decrease = newBoard.allyStore
                    var eval = alphaBetaMakeChoice(delta = delta - decrease, goodPits = newBoard.allyPits,
                                                   badPits = newBoard.enemyPits, depth = depth - 1, alpha = alpha2,
                                                   beta = beta2, isMaximizingPlayer = !anotherTurn)
                    eval = Pair(i, eval.second)
                    if (eval.second < minEval.second) minEval = eval
                    beta2 = min(beta2, eval.second)
                    if (beta2 <= alpha2) break
                }
            }
            return minEval
        }

    }

    protected abstract fun createBoard(allyStore: Int, allyPits: IntArray, enemyStore: Int, enemyPits: IntArray): ConsoleMancalaBoard

    private fun askDifficultyLevel() {
        println("What difficulty level would you like to play against?")
        for (value in OnePlayerDifficultyLevel.entries) {
            println("${value.ordinal + 1}: ${value.name}")
        }

        val validDifficultyLevels = (1..OnePlayerDifficultyLevel.entries.size).toList().toIntArray()
        var difficultyLevel = getInputFromValidInts(validDifficultyLevels)

        this.difficulty = OnePlayerDifficultyLevel.entries.toTypedArray()[difficultyLevel - 1]
    }

    private fun getInputFromValidInts(validValues: IntArray): Int {
        var input = -1
        while (input < 0) {
            try {
                input = readln().toInt()
                if (!validValues.contains(input)) {
                    input = -1
                    throw NumberFormatException()
                }
            } catch (e: NumberFormatException) {
                println("Try again")
            }
        }
        return input
    }


    private fun playTwoPlayers() {
        var isAlliedTurn = true
        while (!isGameOver()) {
            if (!takeTurn(isAlliedTurn)) {
                isAlliedTurn = !isAlliedTurn
            }
        }
    }

    /**
     * Lets you define, when the game is over, whether you get the stones on your side
     */
    protected abstract fun endOfGameLogic()

    /**
     * Returns true if player should receive an extra turn.
     */
    private fun takeTurn(isAlliedTurn: Boolean): Boolean {
        return pickUpPit(readInputPit(isAlliedTurn), isAlliedTurn, isAlliedTurn)
    }


    private fun endMessage() {
        print("\n".repeat(5))
        if (allyStore == enemyStore) {
            println("Good game!\nLooks like a draw this time...")
        } else {
            val allyWon = allyStore > enemyStore
            print("Good game! And a special congratulations to " +
                          playerColor(allyWon) + "Player ${if (allyWon) 1 else 2}" + playerColor(null) +
                          " for winning with ${if (allyWon) allyStore else enemyStore} stones!")
        }
        print("\n".repeat(5))
        print(this)
    }

    /**
     * Returns true if the game is over.
     */
    private fun isGameOver(): Boolean {
        return allyPits.all { it == 0 } || enemyPits.all { it == 0 }
    }

    /**
     * Gets from console what pit the user would like to start with
     */
    private fun readInputPit(isAlliedTurn: Boolean): Int {
        println("Which pit do you wish to take, " + playerColor(isAlliedTurn) + "Player ${if (isAlliedTurn) 1 else 2}" +
                        playerColor(null) + "?")
        println(this.toString())

        var pit = -1
        val validPits =
                if (isAlliedTurn) allyPits.indices.filter { allyPits[it] > 0 }
                else enemyPits.indices.filter { enemyPits[it] > 0 }

        while (pit < 0) {
            try {
                pit = readln().toInt()
                if (!validPits.contains(pit - 1)) {
                    pit = -1
                    throw NumberFormatException()
                }
            } catch (e: NumberFormatException) {
                println("Try again")
            }
        }

        return pit - 1
    }

    /**
     * Returns true if player should receive an extra turn.
     */
    protected fun pickUpPit(pitIndex: Int, isAlliedTurn: Boolean, isAlliedSide: Boolean): Boolean {
        val stones = if (isAlliedSide) allyPits[pitIndex] else enemyPits[pitIndex]
        if (isAlliedSide) allyPits[pitIndex] = 0
        else enemyPits[pitIndex] = 0
        return dumpStones(pitIndex, stones, isAlliedTurn, isAlliedSide)
    }

    /**
     * Returns true if player should receive an extra turn.
     */
    private fun dumpStones(pitFrom: Int, numStones: Int, isAlliedTurn: Boolean, isAlliedSide: Boolean): Boolean {
        var stonesRemaining = numStones

        var lastPit = -1
        for (i in pitFrom - 1 downTo 0) {
            if (isAlliedSide) allyPits[i]++
            else enemyPits[i]++

            if (--stonesRemaining == 0) {
                lastPit = i
                break
            }
        }

        if (stonesRemaining > 0) {
            if (isAlliedSide == isAlliedTurn) {
                stonesRemaining--
                if (isAlliedTurn) allyStore++
                else enemyStore++
            }

            if (stonesRemaining > 0) {
                //still have stones -- carry over to other side
                return dumpStones(6, stonesRemaining, isAlliedTurn, !isAlliedSide)
            } else {
                return true     //extra turn
            }
        } else {
            //ended in a pit (not the store)
            return lastPitLogic(lastPit, isAlliedTurn, isAlliedSide)
        }
    }

    protected abstract fun lastPitLogic(lastPit: Int, isAlliedTurn: Boolean, isAlliedSide: Boolean): Boolean

    override fun toString(): String {
        var s = rowBreak(true)
        s += " ".repeat(8) + "|\t\t ${playerColor(false)}${fourChars(enemyStore)}${playerColor(null)}|\n"
        s += rowBreak(true)
        s += rowPits(5)
        s += rowBreak(false)
        s += rowPits(4)
        s += rowBreak(false)
        s += rowPits(3)
        s += rowBreak(false)
        s += rowPits(2)
        s += rowBreak(false)
        s += rowPits(1)
        s += rowBreak(false)
        s += rowPits(0)
        s += rowBreak(true)
        s += " ".repeat(8) + "|${playerColor(true)}${fourChars(allyStore)}${playerColor(null)}\t\t |\n"
        s += rowBreak(true)
        return s
    }

    private fun rowBreak(isStoreBoundary: Boolean): String {
        return " ".repeat(8) +
                if (isStoreBoundary) "+" + "-".repeat(12) + "+\n"
                else "|----+--+----|\n"
    }

    private fun fourChars(value: Int): String {
        return if (value > 99) " $value"
        else if (value > 9) " $value "
        else "  $value "
    }

    private fun rowPits(pitIndex: Int): String {
        return " ".repeat(6) + "${pitIndex + 1}" + " " +
                "|${playerColor(if (allyPits[pitIndex] > 0) true else null)}${fourChars(allyPits[pitIndex])}${playerColor(null)}|  " +
                "|${playerColor(if (enemyPits[5 - pitIndex] > 0) false else null)}${fourChars(enemyPits[5 - pitIndex])}${playerColor(null)}|" +
                " ${6 - pitIndex}\n"
    }

    private fun playerColor(isAlly: Boolean?): String {
        return if (isAlly == null) ansi.ANSI.hardReset()
        else if (isAlly) ansi.ANSI.color(AnsiColor.CYAN)
        else ansi.ANSI.color(AnsiColor.RED)
    }


    fun helpMe(depth: Int = 10): MutableList<Pair<Int, Int>> {
        //TODO: implement a way of searching that trims and then goes deeper
        val toReturn = mutableListOf<Pair<Int, Int>>()
        do {
            val choice = alphaBetaMakeChoice(delta = 0, goodPits = this.allyPits.copyOf(), badPits = this.enemyPits.copyOf(),
                                             depth = 1, alpha = Int.MIN_VALUE, beta = Int.MAX_VALUE,
                                             isMaximizingPlayer = false)
            toReturn.add(choice)
        } while(pickUpPit(pitIndex = choice.first, isAlliedTurn = true, isAlliedSide = true))
        return toReturn
    }

    fun helpMeSlowAndPrimitive(depth: Int = 10): MutableList<Pair<Int, Int>> {
        val toReturn = mutableListOf<Pair<Int, Int>>()
        do {
            val choice = primitiveMakeChoice(delta = 0, goodPits = this.allyPits.copyOf(), badPits = this.enemyPits.copyOf(),
                                             depth = depth, isMaximizingPlayer = false)
            toReturn.add(choice)
        } while(pickUpPit(pitIndex = choice.first, isAlliedTurn = true, isAlliedSide = true))
        return toReturn
    }

    private fun primitiveMakeChoice(delta: Int, goodPits: IntArray, badPits: IntArray, depth: Int,
                                    isMaximizingPlayer: Boolean): Pair<Int, Int> {
        if (depth == 0) {
            return Pair(-1, delta)
        } else if (goodPits.all { it == 0 } || badPits.all { it == 0 }) {
            val newBoard = createBoard(allyStore = 0, allyPits = goodPits.copyOf(), enemyStore = 0, enemyPits = badPits.copyOf())
            newBoard.endOfGameLogic()
            return Pair(-1, delta + newBoard.enemyStore - newBoard.allyStore)
        }

        if (isMaximizingPlayer) {
            var maxEval = Pair(-1, Int.MIN_VALUE)
            for (i in 0..5) {
                if (badPits[i] > 0) {
                    val newBoard = createBoard(allyStore = 0, allyPits = goodPits.copyOf(), enemyStore = 0, enemyPits = badPits.copyOf())
                    val anotherTurn = newBoard.pickUpPit(i, isAlliedTurn = false, isAlliedSide = false)
                    val increase = newBoard.enemyStore
                    var eval = primitiveMakeChoice(delta = delta + increase, goodPits = newBoard.allyPits,
                                                   badPits = newBoard.enemyPits, depth = depth - 1,
                                                   isMaximizingPlayer = anotherTurn)
                    eval = Pair(i, eval.second)
                    if (eval.second > maxEval.second) maxEval = eval
                }
            }
            return maxEval
        } else {
            var minEval = Pair(-1, Int.MAX_VALUE)
            //for each child position
            for (i in 0..5) {
                if (goodPits[i] > 0) {
                    val newBoard = createBoard(allyStore = 0, allyPits = goodPits.copyOf(), enemyStore = 0, enemyPits = badPits.copyOf())
                    val anotherTurn = newBoard.pickUpPit(i, isAlliedTurn = true, isAlliedSide = true)
                    val decrease = newBoard.allyStore
                    var eval = primitiveMakeChoice(delta = delta - decrease, goodPits = newBoard.allyPits,
                                                   badPits = newBoard.enemyPits, depth = depth - 1,
                                                   isMaximizingPlayer = !anotherTurn)
                    eval = Pair(i, eval.second)
                    if (eval.second < minEval.second) minEval = eval
                }
            }
            return minEval
        }
    }
}
