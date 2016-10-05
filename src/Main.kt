import java.util.*

/**
 * Created by subic on 4. 10. 2016.
 */

private val COMMAND_LENGTH: Int = 5;
private val HALT_STATE = State("halt");


fun main(args: Array<String>) {
    val input : String = readInputFromFile("./input.txt");
    val program : Array<String> = readProgramFromFile("./program.txt")

    val stateMap = parseStateRules(program);
    val initialState = computeInitialState(stateMap, program);
    var tape: Tape = Tape();

    tape.setInitialTapeState(input);

    runProgram(stateMap, initialState, tape);

}




internal fun runProgram(stateMap: Map<String, State>, initialState: State, initialTapeState: Tape) {
    println("[START] Started running program.")

    var currentState = initialState;
    var tape = initialTapeState;
    var step: Int = 0;
    tape.printTape(step);


    while (currentState != HALT_STATE) {

        var currentRule = currentState.getRuleForCellValue(tape.currentCellValue());
        step++;

        tape.applyRule(currentRule);
        tape.printTape(step);
        tape.movePointer(currentRule.getDirection());

        currentState = currentRule.getNextState();

    }

    println("[HALT] Program halted.")
}



fun computeInitialState(stateMap: Map<String, State>, program: Array<String>): State {
    var i = 0;
    while (program[i].isBlankOrComment()) //skip blank lines
        i++;

    var firstCommand = program[i].split(" ");

    return stateMap[firstCommand[0]]!!;
}

fun parseStateRules(program: Array<String>): Map<String, State> {
    var stateMap: Map<String, State> = buildStates(program);

    stateMap = buildRules(program, stateMap);

    return stateMap;
}

fun buildRules(program: Array<String>, stateMap: Map<String, State>): Map<String, State> {

    for ((i, rule: String) in program.withIndex()) {
        if (rule.isBlankOrComment())
            continue;

        val commands = rule.split(" ");
        if (commands.size == COMMAND_LENGTH) {
            var state: State = stateMap[commands[0]]!!;
            state.addRule(commands[1], commands[2], commands[3], stateMap[commands[4]]!!);
        } else {
            throw Exception("Rule in line $i is invalid")
        }
    }

    return stateMap
}

fun buildStates(program: Array<String>): Map<String, State> {
    var stateMap = HashMap<String, State>();

    for ((i, rule: String) in program.withIndex()) {
        if (rule.isBlankOrComment())
            continue;

        val commands = rule.split(" ");
        if (commands.size == COMMAND_LENGTH) {
            stateMap.put(commands[0], State(commands[0]));
        } else {
            throw Exception("Rule in line $i is invalid")
        }
    }

    return stateMap;
}

