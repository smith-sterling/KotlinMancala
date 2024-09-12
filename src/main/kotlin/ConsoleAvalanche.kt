class ConsoleAvalanche: ConsoleMancalaBoard {

    constructor(boardInitType: boardInitType): super(boardInitType)

    constructor(allyStore: Int, allyPits: IntArray, enemyStore: Int, enemyPits: IntArray):
            super(allyStore, allyPits, enemyStore, enemyPits) {
    }

    override fun createBoard(allyStore: Int, allyPits: IntArray, enemyStore: Int, enemyPits: IntArray): ConsoleMancalaBoard {
        return ConsoleAvalanche(allyStore, allyPits, enemyStore, enemyPits)
    }


    override fun endOfGameLogic() {
        return
    }

    override fun lastPitLogic(lastPit: Int, isAlliedTurn: Boolean, isAlliedSide: Boolean): Boolean {
        if (lastPit != -1 &&
                ((isAlliedSide && allyPits[lastPit] > 1) || (!isAlliedSide && enemyPits[lastPit] > 1))) {
            // if ended in a non-empty pit
            return pickUpPit(lastPit, isAlliedTurn, isAlliedSide)
        } else {
            //pit was empty -- turn's over
            return false
        }
    }

}