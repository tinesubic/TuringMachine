import java.util.*

/**
 * Created by subic on 4. 10. 2016.
 */
class State(stateName: String) {


    private var stateName: String
    private var ruleMap: HashMap<CellValue, StateRule>

    init {
        this.stateName = stateName
        this.ruleMap = HashMap(3)
    }

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
