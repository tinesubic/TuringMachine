import java.io.File

/**
 * Created by subic on 4. 10. 2016.
 */

internal enum class CellValue(val character: String) {
    BLANK("_"), ONE("1"), ZERO("0");

    companion object {
        @Throws(Exception::class)
        fun getCellValueFromString(stateString: String): CellValue {
            when (stateString) {
                "_" -> return CellValue.BLANK
                "1" -> return CellValue.ONE
                "0" -> return CellValue.ZERO
                else -> throw Exception("Invalid cell character.")
            }
        }
    }

}

internal enum class Direction {
    NO_MOVE, LEFT, RIGHT;

    companion object {
        @Throws(Exception::class)
        fun getDirectionFromString(moveDirection: String): Direction {
            when (moveDirection) {
                "*" -> return Direction.NO_MOVE
                "<" -> return Direction.LEFT
                ">" -> return Direction.RIGHT
                else -> throw Exception("Invalid direction character.")
            }
        }
    }
}

fun String.isBlankOrComment(): Boolean = this.isBlank() || this.isComment()

private fun String.isComment(): Boolean = this.startsWith(";")

fun readProgramFromFile(filepath: String): Array<String> {
    return File(filepath).readLines().toTypedArray();
}

fun readInputFromFile(filepath: String): String {
    var file = File(filepath);
    for (line : String in file.readLines())
        return line;

    return "";
}
