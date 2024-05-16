import io.circe.parser.*
import io.circe.Json
import io.circe.DecodingFailure
import ujson.Str
import java.net.SocketImpl
import scala.util.Try
import os.stat

final case class TuringMachine(
    name: String,
    state: TuringState,
    final_states: Seq[String],
    rules: Map[String, Map[Char, TuringRule]]
)

trait TuringError:
    def message(): String

enum ValidateError extends TuringError:
    def message(): String = this match
        case BlankNotInAlphabet => "blank character not present in alphabet"
        case BlankInTape => "blank character present in tape"
        case UnknownCharTape => "unknown characters found in tape"
        case UnknownChar => "unknown character found in config"
        case UnknownState => "unknown state found in config"
    case BlankNotInAlphabet, BlankInTape, UnknownCharTape, UnknownState, UnknownChar

object TuringMachine:

    def load(config: TuringConfig, tape: String): Either[TuringError, TuringMachine] =
        val name = config.name
        val alphabet = config.alphabet.distinct

        if !alphabet.contains(config.blank) then return Left(ValidateError.BlankNotInAlphabet)
        if tape.contains(config.blank) then return Left(ValidateError.BlankInTape)
        if tape.distinct.diff(alphabet).size > 0 then
            return Left(ValidateError.UnknownCharTape)

        val states = config.states.distinct
        if !states.contains(config.initial) then return Left(ValidateError.UnknownState)
        val final_states = config.finals.distinct
        if final_states.diff(states).size > 0 then
            return Left(ValidateError.UnknownState)
        
        //TODO continue here
        
        Left(ValidateError.BlankInTape)
