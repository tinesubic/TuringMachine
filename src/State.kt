import java.util.*

/**
 * Created by subic on 4. 10. 2016.
 */
class State(val stateName: String) {

    private val ruleMap: HashMap<CellValue, StateRule> = hashMapOf()


    @Throws(Exception::class)
    fun addRule(currentCellState: String, nextCellState: String, direction: String, nextState: State) {
        if (!ruleExists(currentCellState)) {
            ruleMap.put(CellValue.getCellValueFromString(currentCellState), StateRule(currentCellState, nextCellState, direction, nextState))
        } else {
            throw Exception("Rule already exists")
        }

    }

    internal fun getRuleForCellValue(cellValue: CellValue): StateRule = ruleMap[cellValue]!!

    private fun ruleExists(currentCellState: String): Boolean =
            ruleMap.containsKey(CellValue.getCellValueFromString(currentCellState))
}
