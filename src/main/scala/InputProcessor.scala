import scala.collection.mutable.Set

class InputProcessor(private var _inputIntegers: List[Int], private val _inputCommands: List[String]) {

    // Remove duplicates and sort in ascending order
    // def init = inputIntegers = inputIntegers.toSet.toList.sorted

    def inputCommands = _inputCommands

    def inputIntegers = _inputIntegers

    def inputIntegers_= (newList: List[Int]) = _inputIntegers = newList

    def isPrime(n: Int): Boolean = ((2 until (n+2)/2) forall (n % _ != 0))

    def removePrimes = inputIntegers = inputIntegers.filterNot(isPrime(_))

    def increment: Unit = inputIntegers = inputIntegers.map(_ + 1)

    def runCommand(cmd: String) = {
        cmd match {
            case "inc" => increment

            case "removePrime" => removePrimes

            case whoa => println(s"$cmd")
        }
    }

    def runCommands = inputCommands foreach runCommand
}
