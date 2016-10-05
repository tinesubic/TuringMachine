/**
 * Created by subic on 4. 10. 2016.
 */
class StateRule @Throws(Exception::class)
constructor(currentCellValue: String, nextCellValue: String, moveDirection: String, nextState: State) {


    private var currentCellValue: CellValue? = null
    private var nextCellValue: CellValue? = null
    private var moveDirection: Direction? = null
    private var nextState: State? = null

    init {
        this.currentCellValue = CellValue.getCellValueFromString(currentCellValue)

        this.nextCellValue = CellValue.getCellValueFromString(nextCellValue)
        this.moveDirection = Direction.getDirectionFromString(moveDirection)
        this.nextState = nextState
    }

    internal fun getNextCellValue(): CellValue {
        return nextCellValue!!;
    }

    internal fun getDirection(): Direction {
        return moveDirection!!;
    }

    internal fun getNextState(): State {
        return nextState!!;
    }


}