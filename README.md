# ft_turing

A single-headed, single tape Turing machine simulator that reads machine descriptions from JSON files. This project is part of the 42 school curriculum, focusing on functional programming paradigms and implemented in Scala.

## Description

This program simulates a Turing machine by reading a JSON description file containing the machine's specifications (states, transitions, alphabet) and an input string. It then displays the step-by-step execution of the machine, showing the tape state and head position at each transition.

## Prerequisites

- Java Runtime Environment (JRE)
- Scala Build Tool (SBT)

## Installation

1. Clone the repository

2. Build the project:
```bash
make compile
```

## Usage

The project includes several predefined Turing machines that can be run using make commands:

```bash
# Unary addition
make run1 INPUT="your_input"

# Palindrome checker
make run2 INPUT="your_input"

# Language recognition (0ⁿ1ⁿ)
make run3 INPUT="your_input"

# Language recognition (0²ⁿ)
make run4 INPUT="your_input"

# Meta Turing machine (unary addition runner)
make run5 INPUT="your_input"
```

### Clean Build
```bash
make clean
```

## Features

- JSON-based machine description
- Step-by-step visualization of the machine's execution
- Support for various Turing machine implementations:
  - Unary addition (`res/unary_add.json`)
  - Palindrome checker (`res/palindrome_decider.json`)
  - Language recognition 0ⁿ1ⁿ (`res/0n1n.json`)
  - Language recognition 0²ⁿ (`res/02n.json`)
  - Meta Turing machine (`res/unary_add_runner.json`)
- Error handling for invalid inputs and machine descriptions
- Visual representation of the tape and head position

## Machine Description Format

The JSON machine description must include:

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

## Project Structure

```
.
├── build.sbt           # SBT build configuration
├── Makefile           # Build and run commands
├── res/               # JSON machine descriptions
│   ├── unary_add.json
│   ├── palindrome_decider.json
│   ├── 0n1n.json
│   ├── 02n.json
│   └── unary_add_runner.json
└── src/               # Source code
```

## Example Output

```
[<1>11-11=.............] (scanright, 1) -> (scanright, 1, RIGHT)
[1<1>1-11=.............] (scanright, 1) -> (scanright, 1, RIGHT)
[11<1>-11=.............] (scanright, 1) -> (scanright, 1, RIGHT)
...
```

## Error Handling

The program handles various error cases:
- Malformed JSON files
- Invalid machine descriptions
- Invalid input strings
- Machine execution errors

## License

This project is part of the 42 school curriculum.

