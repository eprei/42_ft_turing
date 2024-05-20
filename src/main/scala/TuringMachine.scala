import scala.annotation.tailrec

final case class TuringMachine(
    name: String,
    blank: Char,
    final_states: Seq[String],
    rules: Map[String, Map[Char, TuringRule]]
):
    def pretty_status(state: TuringState): String =
        s"${
            state.pretty_print(this.blank)
        }${
            state.match_rule(this.rules) match
                case Some(rule) => " -> " + rule.pretty_print()
                case None => ""
        }"
    
    def next(oldState: TuringState): Either[RunError, TuringState] =
        if this.final_states.contains(oldState.state) then
            Right(oldState)
        else
            oldState.match_rule(this.rules) match
                case Some(rule) => Right(oldState.next(rule, this.blank))
                case None => Left(RunError.BlockedError)

    private def createNewState(oldState: TuringState, rule: TuringRule): Either[RunError, TuringState] =
        val newTape: Seq[Char] = oldState.tape.updated(oldState.pointer, rule.write)
        val newPointer: Int = rule.action match
            case TuringAction.RIGHT => oldState.pointer + 1
            case TuringAction.LEFT => oldState.pointer - 1
        newPointer match
            case n if n < 0 => Left(RunError.OutOfTapeError)
            case _ => Right(TuringState(tape = newTape, state = rule.to_state, pointer = newPointer))

trait TuringError:
    def message(): String

enum ValidateError extends TuringError:
    def message(): String = this match
        case BlankNotInAlphabet => "blank character not present in alphabet"
        case BlankInTape => "blank character present in tape"
        case UnknownCharTape => "unknown characters found in tape"
        case UnknownChar => "unknown character found in config"
        case UnknownState => "unknown state found in config"
        case InvalidAction => "invalid action found in config"
    case BlankNotInAlphabet, BlankInTape, UnknownCharTape, UnknownState, UnknownChar, InvalidAction

enum RunError extends TuringError:
    def message(): String = this match
        case BlockedError => "not found any known rule for this position"
        case OutOfTapeError => "head out of tape"
    case BlockedError, OutOfTapeError

object TuringMachine:

    def load(config: TuringConfig, tape: String): Either[TuringError, (TuringMachine, TuringState)] =
        val name = config.name
        val alphabet = config.alphabet.distinct

        if !alphabet.contains(config.blank) then return Left(ValidateError.BlankNotInAlphabet)
        if tape.contains(config.blank) then return Left(ValidateError.BlankInTape)
        if tape.distinct.diff(alphabet).nonEmpty then
            return Left(ValidateError.UnknownCharTape)
        val tape_seq: Seq[Char] = tape.toSeq

        val states = config.states.distinct
        if !states.contains(config.initial) then return Left(ValidateError.UnknownState)
        val final_states = config.finals.distinct
        if final_states.diff(states).nonEmpty then
            return Left(ValidateError.UnknownState)
        
        validate_rule_table(config.transitions, alphabet, states) match
            case Some(error) => return Left(error)
            case _ => ()
        
        val rules = transform_rule_table(config.transitions)
        
        Right((
            TuringMachine(name, config.blank, final_states, rules),
            TuringState(tape_seq, config.initial, 0)
        ))

    private def transform_rule_table(transitions: Map[String, Seq[TuringRuleConfig]]): Map[String, Map[Char, TuringRule]] =
        transitions.map(s => 
            (
                s._1,
                s._2.distinctBy(rule => rule.read)
                    .map(elem => (elem.read, TuringRule(elem.to_state, elem.write,
                        elem.action match
                            case "RIGHT" => TuringAction.RIGHT
                            case "LEFT" => TuringAction.LEFT
                            case _ => throw Error("this should not happen, invalid action found after checks")
                        
                    )))
                    .toMap
            )
        )
    
    private def validate_rule_table(transitions: Map[String,Seq[TuringRuleConfig]],
        alphabet: Seq[Char], states: Seq[String]): Option[TuringError] =
        // Start states
        if transitions.keySet.diff(states.toSet).nonEmpty then return Some(ValidateError.UnknownState)

        //Chars
        if !transitions.values.forall(rules => 
            rules.forall(rule =>
                alphabet.contains(rule.read) &&
                alphabet.contains(rule.write)
            )
        ) then return Some(ValidateError.UnknownChar)

        // End States
        if !transitions.values.forall(rules => 
            rules.forall(rule =>
                states.contains(rule.to_state)
            )
        ) then return Some(ValidateError.UnknownState)

        // Actions
        if !transitions.values.forall(rules => 
            rules.forall(rule =>
                rule.action match
                    case "RIGHT" | "LEFT" => true
                    case _ => false
            )
        ) then return Some(ValidateError.InvalidAction)

        None

    def runMachine(machine: TuringMachine, state: TuringState): Either[TuringError, Unit] =
        @tailrec
        def step(machine: TuringMachine, state: TuringState): Either[TuringError, Unit] =

            println(machine.pretty_status(state)) //not pure but if we dont print it here then we won't till end of machine which is also not the plan

            val new_state = machine.next(state) match
                case Left(error) => return Left(error)
                case Right(value) if machine.final_states.contains(value.state) => return Right(())
                case Right(value) => value
            
            step(machine, new_state)
        
        println(s"running machine ${machine.name}")
        
        step(machine, state)

