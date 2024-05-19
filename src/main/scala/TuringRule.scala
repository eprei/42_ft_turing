enum  TuringAction:
    case RIGHT, LEFT

final case class TuringRule(
    to_state: String,
    write: Char,
    action: TuringAction
):
    def pretty_print(): String =
        s"(${this.to_state}, ${this.write}, ${this.action})"

