import java.io.{FileNotFoundException, IOException}

enum IOError extends TuringError:
  def message(): String = this match
    case FileNotFound => "file not found"
    case FileReadError => "error while reading file"
    case InvalidArguments => "usage: ft_turing [-h] jsonfile input"
    case Help => """usage: ft_turing [-h] jsonfile input

positional arguments:
  jsonfile    json description of the machine
  input       input of the machine

optional arguments:
-h, --help    show this help message and exit"""
  case FileNotFound, FileReadError, InvalidArguments, Help

def fileOpener(args: Seq[String]):  Either[TuringError, String] =
  search_help(args) match
    case Left(error) => Left(error)
    case Right(_) =>
      args.size match
        case 2 =>
          try
            val turingConfig = os.read(os.pwd / args.head)
            Right(turingConfig)
          catch
            case e: FileNotFoundException => Left(IOError.FileNotFound)
            case e: IOException => Left(IOError.FileReadError)
        case _ =>
          Left(IOError.InvalidArguments)

def search_help(args: Seq[String]): Either[TuringError, Unit] =
  if args.contains("-h") || args.contains("--help") then Left(IOError.Help)
  else Right(())
