# TuringMachine
Simple [Turing Machine](https://en.wikipedia.org/wiki/Turing_machine) implemented in [Kotlin](https://kotlinlang.org/)

Rules: `<current_state> <current_cell_value> <next_cell_value> <move> <next_state>`

States: Can be any continuous string (s0, S0, some_state...). End with state "halt"

Cell values: can be `0`, `1` or `_` (blank).

Movement: Can move left (`<`), right (`>`) or no move (`*`)

Below, a simple example of 3-state Busy Beaver program which generates 6 sequential `1` characters on tape.

```
;3 state busy beaver
S0 _ 1 > S1
S0 0 _ * S0
S0 1 1 * halt

S1 _ _ > S2
S1 0 _ * S0
S1 1 1 > S1

S2 _ 1 < S2
S2 0 _ * S0
S2 1 1 < S0
```

