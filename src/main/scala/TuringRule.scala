enum  TuringAction:
    case RIGHT, LEFT

final case class TuringRule(
    to_state: String,
    write: Char,
    action: TuringAction
)
