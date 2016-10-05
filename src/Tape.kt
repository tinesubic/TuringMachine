/**
 * Created by subic on 5. 10. 2016.
 */

class Tape {
    private val tape: MutableList<CellValue>? = arrayListOf();
    private var pointer = 0;


    fun setInitialTapeState(input: String) {
        for (char: Char in input) {
            tape!!.add(tape.size, CellValue.getCellValueFromString(char.toString()))
        }

        if(input.length == 0)
            tape!!.add(CellValue.BLANK);
    }


    fun printTape(step: Int) {
        print("[$step] ");
        for (cell: CellValue in tape!!)
            print(cell.character);
        println()
    }

    internal fun currentCellValue(): CellValue {
        return tape!![pointer];
    }

    fun applyRule(currentRule: StateRule) {
        tape!![pointer] = currentRule.getNextCellValue();
    }

    internal fun movePointer(direction: Direction) {
        if (direction == Direction.RIGHT) {
            moveRight();
        } else if (direction == Direction.LEFT) {
            moveLeft();
        }
    }

    private fun moveLeft() {
        if (pointer > 0) {
            pointer--;
        } else {
            tape!!.add(0, CellValue.BLANK);
        }
    }

    private fun moveRight() {
        if (pointer >= tape!!.size - 1) {
            tape.add(tape.size, CellValue.BLANK)
        }
        pointer++;
    }
}
