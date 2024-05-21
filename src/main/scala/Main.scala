import io.circe.parser.decode
import io.circe.generic.auto.deriveDecoder
import java.io.{FileNotFoundException, IOException}

@main def ft_turing(args: String*): Int =

  println(args)

  val (config_path, input) = parse_args(args) match
    case Left(ParseArgsError.Help) => print_help(); return 0
    case Left(ParseArgsError.NotEnoughArgs) => println("usage: ft_turing [-h] jsonfile input"); return 1
    case Right((config_path, input)) => (config_path, input)
  
  println("[+] Loading config...")
  val config = try
    os.read(config_path)
  catch
    case e: FileNotFoundException => println(s"ft_turing: error: file not found: $config_path"); return 1
    case e: IOException => println(s"ft_turing: error: could not open: $config_path"); return 1

  val tjson = decode[TuringConfig](config) match
      case Left(error) => println("ft_turing: error: failed to parse json: " + error); return 1
      case Right(value) => value

  println("[+] Validating config...")
  val (machine, state): (TuringMachine, TuringState) = TuringMachine.load(tjson, input) match
    case Left(error) => println(s"ft_turing: error: ${error.message()}"); return 1
    case Right(values) => values

  println("[+] Starting machine...")
  TuringMachine.runMachine(machine, state) match
    case Left(error) => println(s"ft_turing: error: ${error.message()}"); return 1
    case Right(final_state) => println(s"${final_state.pretty_print(machine.blank)}\nmachine ${machine.name} ran to completion successfully!")

  0
