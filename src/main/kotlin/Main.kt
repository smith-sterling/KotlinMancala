import ConsoleMancalaBoard.boardInitType

fun main(args: Array<String>) {
    var board = ConsoleAvalanche(boardInitType.RANDOM)
    board.playGame()

//    var otherBoard = ConsoleCapture(boardInitType.RANDOM)
//    otherBoard.playGame()


//    testExampleData()

}



private fun testExampleData() {
    val avalanche: List<List<Int>> = listOf(
            //first 25 in groups of 5
            listOf(5, 4, 1, 2, 1, 2),
            listOf(5, 4, 3, 5, 3, 2),
            listOf(1, 4, 2, 1, 5, 4),
            listOf(2, 4, 1, 2, 3, 4),
            listOf(2, 4, 5, 5, 3, 2),

            listOf(4, 4, 5, 4, 3, 3),
            listOf(2, 4, 1, 5, 5, 4),
            listOf(3, 1, 2, 4, 3, 5),
            listOf(1, 4, 5, 2, 3, 4),
            listOf(1, 4, 3, 1, 1, 3),

            listOf(2, 3, 1, 4, 3, 3),
            listOf(3, 2, 1, 4, 3, 3),
            listOf(4, 2, 5, 1, 1, 5),
            listOf(1, 3, 2, 4, 1, 3),
            listOf(2, 4, 1, 4, 3, 4),

            listOf(4, 4, 5, 5, 3, 4),
            listOf(5, 3, 1, 4, 5, 5),
            listOf(1, 3, 2, 5, 5, 5),
            listOf(5, 2, 5, 1, 1, 5),
            listOf(1, 3, 3, 5, 3, 5),

            listOf(5, 4, 3, 2, 1, 3),
            listOf(3, 3, 3, 1, 3, 3),
            listOf(4, 4, 5, 4, 3, 2),
            listOf(1, 2, 3, 4, 4, 4),
            listOf(3, 3, 3, 5, 1, 3),


            //second 25 in groups of 5
            listOf(2, 2, 5, 2, 5, 5),
            listOf(3, 2, 2, 5, 3, 2),
            listOf(4, 1, 1, 4, 5, 4),
            listOf(4, 2, 3, 5, 1, 5),
            listOf(5, 1, 2, 5, 4, 3),

            listOf(5, 4, 5, 1, 1, 2),
            listOf(1, 4, 5, 2, 5, 3),
            listOf(2, 2, 1, 1, 5, 2),
            listOf(1, 2, 5, 5, 4, 3),
            listOf(3, 2, 2, 4, 4, 4),

            listOf(5, 4, 1, 5, 3, 4),
            listOf(3, 2, 5, 5, 5, 5),
            listOf(5, 4, 2, 5, 3, 4),
            listOf(1, 3, 2, 4, 5, 4),
            listOf(4, 2, 2, 5, 3, 2),

            listOf(1, 4, 1, 5, 3, 2),
            listOf(4, 4, 3, 2, 4, 4),
            listOf(5, 2, 1, 2, 5, 4),
            listOf(1, 1, 1, 1, 1, 4),
            listOf(2, 1, 5, 2, 1, 2),

            listOf(5, 4, 3, 1, 3, 2),
            listOf(1, 2, 3, 2, 3, 2),
            listOf(5, 3, 2, 4, 4, 2),
            listOf(4, 3, 1, 5, 1, 3),
            listOf(5, 1, 2, 5, 1, 4),


            //third 25 in groups of 5
            listOf(2, 3, 2, 1, 4, 3),
            listOf(4, 1, 1, 1, 3, 3),
            listOf(1, 3, 5, 2, 5, 2),
            listOf(3, 4, 3, 4, 1, 5),
            listOf(3, 1, 5, 2, 1, 4),

            listOf(4, 4, 3, 2, 5, 5),
            listOf(3, 1, 5, 4, 3, 4),
            listOf(1, 1, 1, 5, 5, 3),
            listOf(2, 1, 3, 4, 3, 5),
            listOf(5, 1, 3, 4, 1, 4),

            listOf(3, 2, 3, 1, 1, 5),
            listOf(5, 3, 5, 1, 3, 3),
            listOf(4, 2, 5, 1, 4, 5),
            listOf(1, 4, 3, 2, 3, 3),
            listOf(3, 3, 1, 2, 3, 2),

            listOf(3, 4, 1, 5, 3, 3),
            listOf(1, 3, 2, 4, 1, 5),
            listOf(3, 1, 5, 1, 4, 3),
            listOf(5, 3, 1, 1, 4, 4),
            listOf(3, 2, 3, 1, 1, 3),

            listOf(4, 2, 2, 2, 1, 4),
            listOf(2, 3, 3, 5, 5, 4),
            listOf(5, 4, 5, 4, 3, 4),
            listOf(1, 2, 2, 4, 3, 2),
            listOf(5, 4, 1, 4, 4, 2)
    )
    val capture: List<List<Int>> = listOf(
            //first 25 in groups of 5
            listOf(2, 3, 3, 1, 3, 4),
            listOf(2, 4, 2, 1, 4, 5),
            listOf(2, 3, 2, 1, 1, 2),
            listOf(5, 1, 3, 5, 4, 2),
            listOf(5, 3, 2, 4, 3, 2),

            listOf(4, 2, 2, 4, 1, 4),
            listOf(1, 2, 1, 1, 4, 2),
            listOf(3, 2, 5, 2, 5, 2),
            listOf(5, 3, 3, 4, 5, 2),
            listOf(5, 2, 3, 4, 5, 3),

            listOf(3, 3, 5, 5, 4, 3),
            listOf(2, 1, 1, 2, 1, 2),
            listOf(1, 1, 5, 4, 5, 2),
            listOf(4, 1, 3, 4, 4, 5),
            listOf(4, 2, 3, 5, 1, 3),

            listOf(2, 1, 3, 5, 3, 4),
            listOf(2, 1, 2, 4, 4, 3),
            listOf(3, 1, 3, 5, 3, 3),
            listOf(3, 3, 2, 4, 4, 4),
            listOf(1, 2, 5, 4, 5, 2),

            listOf(1, 2, 1, 5, 5, 5),
            listOf(3, 3, 2, 4, 1, 5),
            listOf(4, 2, 1, 4, 3, 2),
            listOf(3, 3, 1, 1, 4, 4),
            listOf(1, 3, 5, 2, 1, 2),


            //second 25 in groups of 5
            listOf(5, 3, 5, 1, 3, 5),
            listOf(5, 1, 5, 5, 4, 2),
            listOf(2, 4, 1, 2, 4, 2),
            listOf(2, 4, 5, 4, 1, 4),
            listOf(4, 1, 1, 1, 1, 3),

            listOf(4, 2, 5, 5, 1, 2),
            listOf(2, 2, 5, 4, 3, 3),
            listOf(5, 1, 1, 4, 4, 5),
            listOf(3, 1, 3, 5, 5, 4),
            listOf(1, 2, 1, 5, 4, 3),

            listOf(2, 3, 5, 4, 5, 4),
            listOf(1, 2, 2, 5, 3, 5),
            listOf(2, 2, 1, 2, 1, 3),
            listOf(2, 3, 5, 1, 5, 4),
            listOf(4, 1, 1, 5, 3, 3),

            listOf(2, 1, 3, 2, 1, 2),
            listOf(5, 2, 5, 1, 3, 2),
            listOf(5, 4, 1, 5, 5, 2),
            listOf(5, 4, 2, 4, 3, 5),
            listOf(3, 3, 2, 4, 4, 2),

            listOf(2, 3, 1, 1, 4, 3),
            listOf(5, 4, 5, 1, 3, 2),
            listOf(1, 2, 3, 5, 1, 2),
            listOf(2, 3, 2, 2, 3, 4),
            listOf(2, 1, 5, 5, 1, 3),


            //third 25 in groups of 5
            listOf(1, 3, 2, 5, 4, 4),
            listOf(2, 3, 1, 5, 3, 2),
            listOf(1, 4, 3, 5, 5, 4),
            listOf(3, 3, 2, 1, 5, 3),
            listOf(2, 1, 5, 1, 1, 2),

            listOf(4, 2, 2, 2, 5, 4),
            listOf(3, 1, 5, 4, 5, 3),
            listOf(2, 1, 1, 5, 4, 2),
            listOf(1, 1, 5, 1, 1, 3),
            listOf(1, 1, 5, 5, 5, 2),

            listOf(1, 1, 3, 2, 4, 3),
            listOf(5, 2, 3, 4, 1, 5),
            listOf(5, 3, 1, 4, 5, 4),
            listOf(4, 2, 5, 1, 5, 3),
            listOf(5, 4, 2, 5, 4, 3),

            listOf(3, 4, 1, 2, 1, 3),
            listOf(3, 3, 5, 4, 3, 5),
            listOf(4, 2, 5, 4, 1, 5),
            listOf(2, 2, 5, 5, 3, 2),
            listOf(4, 3, 2, 2, 1, 2),

            listOf(2, 4, 3, 5, 3, 4),
            listOf(3, 3, 3, 5, 4, 4),
            listOf(5, 3, 2, 1, 5, 5),
            listOf(2, 2, 5, 4, 1, 5),
            listOf(3, 4, 1, 4, 5, 5)
    )

    println("Avalanche\t| Capture")
    println("-".repeat(24))
    for (i in 5 downTo 0) {
        println("Pocket ${6 - i}:")
        for (j in 1..5) {
            println("${j}:\t ${avalanche.count { it[i] == j }}\t\t|\t${capture.count { it[i] == j }}")
        }
        println()
    }
}








//advanced something maybe having stones worth different amounts, and with any time you pick up the pit,
// the heaviest (most expensive) stone gets dropped first
