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
    case e: FileNotFoundException => println(s"error: file not found: $config_path"); return 1
    case e: IOException => println(s"error: could not open: $config_path"); return 1

  val tjson = decode[TuringConfig](config) match
      case Left(error) => println("ft_turing: error: failed to parse json"); return 1 //specify error
      case Right(value) => value
      
  println("[+] Validating config...")
  println("[+] Starting machine...")
  println(tjson)
  println(input)
  0
