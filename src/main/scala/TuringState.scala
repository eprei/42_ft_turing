final case class TuringState(
    tape: Seq[Char],
    state: String,
    pointer: Int = 0
)
