/**
 * Created by subic on 5. 10. 2016.
 */

class Tape {
    private val tape: MutableList<CellValue>? = arrayListOf();
    private var pointer = 0;
    private var steps = 0;


    fun setInitialTapeState(input: String) {
        for (char: Char in input) {
            tape!!.add(tape.size, CellValue.getCellValueFromString(char.toString()))
        }

        if (input.length == 0)
            tape!!.add(CellValue.BLANK);
    }


    fun printTape() {
        print("[$steps] ");
        for (cell: CellValue in tape!!)
            print(cell.character);
        println()
    }

    internal fun currentCellValue(): CellValue = tape!![pointer]

    fun applyRule(currentRule: StateRule) {
        tape!![pointer] = currentRule.getNextCellValue();
        steps++;
    }

    internal fun movePointer(direction: Direction) {
        when (direction) {
            Direction.RIGHT -> moveRight();
            Direction.LEFT -> moveLeft();
        }
    }

    private fun moveLeft() {
        when { //if at the end of tape, add one blank cell on end
            pointer > 0 -> pointer--;
            else -> tape!!.add(0, CellValue.BLANK);
        }
    }

    private fun moveRight() { //if at the beginning of tape, add a blank cell in front
        if (pointer >= tape!!.size - 1) {
            tape.add(tape.size, CellValue.BLANK)
        }
        pointer++;
    }
}
