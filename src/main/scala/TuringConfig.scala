final case class TuringRuleConfig(
    read: Char,
    to_state: String,
    write: Char,
    action: String
)

final case class TuringConfig(
    name: String,
    alphabet: Seq[Char],
    blank: Char,
    states: Seq[String],
    initial: String,
    finals: Seq[String],
    transitions: Map[
        String,
        Seq[TuringRuleConfig]
    ]
)
