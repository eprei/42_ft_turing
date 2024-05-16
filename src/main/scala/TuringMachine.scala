final case class TuringMachine(
    state: TuringState,
    rules: Map[String, Map[Char, TuringRule]]
)
