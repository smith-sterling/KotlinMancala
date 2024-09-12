class ConsoleCapture: ConsoleMancalaBoard {
    constructor(boardInitType: boardInitType): super(boardInitType)

    constructor(allyStore: Int, allyPits: IntArray, enemyStore: Int, enemyPits: IntArray):
            super(allyStore, allyPits, enemyStore, enemyPits) {
    }

    override fun createBoard(allyStore: Int, allyPits: IntArray, enemyStore: Int, enemyPits: IntArray): ConsoleMancalaBoard {
        return ConsoleCapture(allyStore, allyPits, enemyStore, enemyPits)
    }


    override fun endOfGameLogic() {
        allyStore += allyPits.sum()
        enemyStore += enemyPits.sum()
        for (i in 0..5) {
            allyPits[i] = 0
            enemyPits[i] = 0
        }
    }


    override fun lastPitLogic(lastPit: Int, isAlliedTurn: Boolean, isAlliedSide: Boolean): Boolean {
        if (lastPit != -1 && isAlliedSide == isAlliedTurn &&
                ((isAlliedSide && allyPits[lastPit] == 1) || (!isAlliedSide && enemyPits[lastPit] == 1))) {
            // if ended in an empty pit
            return capture(lastPit, isAlliedTurn)
        } else {
            //pit was empty -- turn's over
            return false
        }
    }

    private fun capture(lastPit: Int, isAlliedTurn: Boolean): Boolean {
        if (isAlliedTurn && enemyPits[5 - lastPit] > 0) {
            allyStore += enemyPits[5 - lastPit] + 1
            enemyPits[5 - lastPit] = 0
            allyPits[lastPit] = 0
        } else if (!isAlliedTurn && allyPits[5 - lastPit] > 0) {
            enemyStore += allyPits[5 - lastPit] + 1
            allyPits[5 - lastPit] = 0
            enemyPits[lastPit] = 0
        }
        return false
    }

}