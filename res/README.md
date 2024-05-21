# Unary add runner

## Encoding

To simplify the parsing work, the configuration of the machine to be executed is encoded as follows:

| character | meaning                 |
|:----------|:------------------------|
| S         | current_state scanRight |
| E         | current_state eraseOne  |
| h         | to_state HALT           |
| s         | to_state scanRight      |
| e         | to_state eraseOne       |
| r         | action right            |
| l         | action left             |
| ^         | pointer                 |
| :         | tape start              |
| _         | white character encoded |

Transition format = [ current_state ]   rule_1 rule_2 rule_3

Rule format = [ character_read ] [ to_state ] [ character_to_write ] [ action ]

### Encoding example

unary_add.json :

```json
{
  "name" : "unary_add",
  "alphabet": [ "1", ".", "+" ],
  "blank" : ".",
  "states" : [ "scanRight", "eraseOne","HALT" ],
  "initial" : "scanRight",
  "finals" : [ "HALT" ],

  "transitions" : {

    "scanRight": [
      { "read" : "1", "to_state": "scanRight", "write": "1", "action": "RIGHT"},
      { "read" : "+", "to_state": "scanRight", "write": "1", "action": "RIGHT"},
      { "read" : ".", "to_state": "eraseOne" , "write": ".", "action": "LEFT" }
    ],
    "eraseOne": [
      { "read" : "1", "to_state": "HALT" , "write": ".", "action": "RIGHT"}
    ]
  }
}

```

unary_add.json machine encoded:

```
S1s1r+s1r_e_lE1h_r
```

unary_add.json machine encoded machine plus tape to be processed:

```
S1s1r+s1r_e_lE1h_r:11+11
```

Use of the 'Unary add runner':

```
ft_turing res/unary_add_runner.json S1s1r+s1r_e_lE1h_r:11+11
```
