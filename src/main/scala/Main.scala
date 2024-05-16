import io.circe.parser.decode
import io.circe.generic.auto.deriveDecoder

@main def ft_turing(): Unit =
  println("[+] Cmdline options...")
  println("[+] Loading config...")
  println("[+] Validating config...")
  println("[+] Loading input...")
  println("[+] Validating input...")
  println("[+] Starting machine...")

  val content: String = os.read(os.pwd / "docs" / "test.json")
  val tjson = decode[TuringConfig](content) match
    case Left(error) => println("ft_turing: error: failed to parse json")
    case Right(value) => value


