/**
 * Created by subic on 4. 10. 2016.
 */
class StateRule @Throws(Exception::class)
constructor(currentCellValue: String, nextCellValue: String, moveDirection: String, nextState: State) {


    private var currentCellValue: CellValue
    private var nextCellValue: CellValue
    private var moveDirection: Direction
    private var nextState: State

    init {
        this.currentCellValue = CellValue.getCellValueFromString(currentCellValue)
        this.nextCellValue = CellValue.getCellValueFromString(nextCellValue)
        this.moveDirection = Direction.getDirectionFromString(moveDirection)
        this.nextState = nextState
    }

    internal fun getNextCellValue(): CellValue = nextCellValue

    internal fun getDirection(): Direction = moveDirection

    internal fun getNextState(): State = nextState


}