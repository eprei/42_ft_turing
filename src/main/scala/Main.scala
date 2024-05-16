import io.circe.parser.decode
import io.circe.generic.auto.deriveDecoder

@main def ft_turing(args: String*): Unit =
  println("[+] Cmdline options...")
  println("[+] Loading config...")
  println("[+] Validating config...")
  println("[+] Loading input...")
  println("[+] Validating input...")
  println("[+] Starting machine...")

  fileOpener(args) match
    case Right(content) =>
      val tjson = decode[TuringConfig](content) match
        case Left(error) => println("ft_turing: error: failed to parse json")
        case Right(value) => println(value)
    case Left(error) => println(error.message())
