import java.util.*

/**
 * Created by subic on 4. 10. 2016.
 */

private val COMMAND_LENGTH: Int = 5
private val HALT_STATE = State("halt")


fun main(args: Array<String>) {
    val input: String = readInputFromFile("./input.txt")
    val program: Array<String> = readProgramFromFile("./program.txt")

    val stateMap = parseStateRules(program)
    val initialState = computeInitialState(stateMap, program)

    val tape: Tape = Tape(input)

    runProgram(stateMap, initialState, tape)

}


internal fun runProgram(stateMap: Map<String, State>, initialState: State, initialTapeState: Tape) {
    println("[START] Started running program.")

    var currentState = initialState
    val tape = initialTapeState
    tape.printTape()


    while (currentState != HALT_STATE) {

        val currentRule = currentState.getRuleForCellValue(tape.currentCellValue())

        with(tape) {
            this.applyRule(currentRule)
            this.printTape()

        }



        currentState = currentRule.nextState

    }

    println("[HALT] Program halted.")
}


//Finds which state should be first
fun computeInitialState(stateMap: Map<String, State>, program: Array<String>): State {
    var i = 0
    while (program[i].isBlankOrComment()) //skip blank lines
        i++

    val firstCommand = program[i].split(" ")

    val initialState : State? =stateMap[firstCommand[0]]

    if (initialState != null ) {
        return HALT_STATE
    } else {
        return initialState as State
    }

}

fun parseStateRules(program: Array<String>): Map<String, State> {
    var stateMap: Map<String, State> = buildStates(program) //first, build a map of states

    stateMap = buildRules(program, stateMap) //then build rule object and link them to states

    return stateMap
}

fun buildRules(program: Array<String>, stateMap: Map<String, State>): Map<String, State> {

    for ((i, rule: String) in program.withIndex()) {
        if (rule.isBlankOrComment()) //skip lines which are not commancds
            continue

        val commands = rule.split(" ")
        if (commands.size == COMMAND_LENGTH) {
            val state: State? = stateMap[commands[0]]
            val nextState: State? = stateMap[commands[4]]

            if (state != null && nextState != null)
                state.addRule(commands[1], commands[2], commands[3], nextState)
        } else {
            throw Exception("Rule in line $i is invalid")
        }
    }

    return stateMap
}

fun buildStates(program: Array<String>): Map<String, State> {
    val stateMap = HashMap<String, State>()
    stateMap.put("halt", HALT_STATE)

    for ((i, rule: String) in program.withIndex()) {
        if (rule.isBlankOrComment())
            continue

        val commands = rule.split(" ")
        if (commands.size == COMMAND_LENGTH) {
            stateMap.put(commands[0], State(commands[0]))
        } else {
            throw Exception("Rule in line $i is invalid")
        }
    }

    return stateMap
}

