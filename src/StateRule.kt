/**
 * Created by subic on 4. 10. 2016.
 */
class StateRule @Throws(Exception::class)
constructor(currentCellValue: String, nextCellValue: String, moveDirection: String, internal val nextState: State) {

    internal val currentCellValue: CellValue = CellValue.getCellValueFromString(currentCellValue)
    internal val nextCellValue: CellValue = CellValue.getCellValueFromString(nextCellValue)
    internal val direction: Direction = Direction.getDirectionFromString(moveDirection)

}