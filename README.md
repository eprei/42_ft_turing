# ft_turing

A Turing machine simulator implemented in Scala that reads machine configurations from JSON files and simulates their execution. This project focuses on functional programming paradigms and is part of the 42 school curriculum.

## About

This project implements a single-headed, single-tape Turing machine simulator that can:
- Read machine descriptions from JSON configuration files
- Process input strings according to the machine's rules
- Display step-by-step execution with visual tape representation
- Handle multiple types of Turing machines for different computations

## Technologies

- Scala 3.4.1
- SBT (Scala Build Tool)
- Libraries:
  - circe (JSON parsing)
  - os-lib (file operations)
  - play-json
  - scala-toolkit

## Implemented Turing Machines

The project includes several predefined Turing machine configurations:

1. `unary_add.json`: Computes unary addition
2. `palindrome_decider.json`: Determines if the input is a palindrome
3. `0n1n.json`: Decides if the input belongs to the language 0ⁿ1ⁿ
4. `02n.json`: Decides if the input belongs to the language 0²ⁿ
5. `unary_add_runner.json`: [Meta Turing machine](https://github.com/eprei/42_ft_turing/tree/main/res#readme) that runs the unary addition machine

## Prerequisites

- Java Runtime Environment (JRE)
- Scala Build Tool (SBT)

## Building

```bash
make compile
```

## Usage

The program can be run using make commands for predefined machines:
```bash
# Unary addition
make run1 INPUT="your_input"

# Palindrome checker
make run2 INPUT="your_input"

# Language recognition (0ⁿ1ⁿ)
make run3 INPUT="your_input"

# Language recognition (0²ⁿ)
make run4 INPUT="your_input"

# Meta Turing machine
make run5 INPUT="your_input"
```

## JSON Configuration Format

Machine descriptions use the following JSON format:
```json
{
    "name": "machine_name",
    "alphabet": ["symbols"],
    "blank": "blank_symbol",
    "states": ["state_names"],
    "initial": "initial_state",
    "finals": ["final_states"],
    "transitions": {
        "state_name": [
            {
                "read": "symbol",
                "to_state": "next_state",
                "write": "symbol",
                "action": "LEFT/RIGHT"
            }
        ]
    }
}
```

## Features

- Functional programming approach
- Immutable data structures
- Pure functions
- Pattern matching
- Error handling using `Either`
- Type-safe state transitions
- Visual representation of tape state and head position
- Comprehensive input validation

## Project Structure

```
.
├── src/
│   ├── main/scala/
│   │   ├── Main.scala           # Entry point
│   │   ├── ParseArgs.scala      # Command line parsing
│   │   ├── TuringConfig.scala   # Configuration types
│   │   ├── TuringMachine.scala  # Core machine logic
│   │   ├── TuringRule.scala     # Transition rules
│   │   └── TuringState.scala    # Machine state
│   └── test/scala/
│       └── MySuite.scala        # Test suite
├── res/                         # Machine configurations
├── build.sbt                    # Build configuration
└── Makefile                     # Build commands
```

## Error Handling

The simulator handles various error cases:
- Malformed JSON configurations
- Invalid machine descriptions
- Invalid input strings
- Unknown characters in input
- Invalid state transitions
- Machine execution errors

## Example Output

```
[<1>11-11=.............] (scanright, 1) -> (scanright, 1, RIGHT)
[1<1>1-11=.............] (scanright, 1) -> (scanright, 1, RIGHT)
[11<1>-11=.............] (scanright, 1) -> (scanright, 1, RIGHT)
```

## Functional Programming Focus

This project emphasizes functional programming concepts:
- Pure functions with no side effects
- Immutable data structures
- Pattern matching for control flow
- Algebraic data types (ADTs) for state representation
- Error handling using functional concepts (`Either`, `Option`)
- Recursive algorithms instead of imperative loops
- Type safety through the Scala type system
