import os.RelPath

enum ParseArgsError:
    case Help, NotEnoughArgs

def parse_args(args: Seq[String]): Either[ParseArgsError, (os.Path, String)] =
    if args.contains("-h") || args.contains("--help") then
        Left(ParseArgsError.Help)
    else
        args.size match
            case n if n < 2 => Left(ParseArgsError.NotEnoughArgs)
            case _ => Right((
                (os.pwd / RelPath(args(0))),
                args(1)
            ))

def print_help(): Unit =
    println("""usage: ft_turing [-h] jsonfile input

positional arguments:
  jsonfile    json description of the machine
  input       input of the machine

optional arguments:
-h, --help    show this help message and exit""")
