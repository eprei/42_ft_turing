final case class TuringState(
    tape: Seq[Char],
    state: String,
    pointer: Int = 0
):
    def pretty_print(blank: Char): String =
        val slice_end_pos = (this.pointer + 1).max(TuringState.print_len)
        val (left_seq, rest) = this.tape
            .padTo(TuringState.print_len, blank)
            .slice(slice_end_pos - TuringState.print_len, slice_end_pos)
            .splitAt(this.pointer - (slice_end_pos - TuringState.print_len))
        val (middle_char, right_seq) = rest.splitAt(1)

        val char = this.tape.apply(this.pointer)

        val tape_repr =  s"${left_seq.mkString("")}<${middle_char.mkString("")}>${right_seq.mkString("")}"

        s"[$tape_repr] (${this.state}, $char)"

    def match_rule(rules: Map[String, Map[Char, TuringRule]]): Option[TuringRule] =
        val char = this.tape.apply(this.pointer)
        rules.get(this.state) match
            case Some(value) => value.get(char)
            case None => None


object TuringState:
    val print_len: Int = 20

