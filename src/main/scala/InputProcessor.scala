class InputProcessor(private var _inputIntegers: List[Int], private val _inputCommands: List[String]) {

    def inputCommands = _inputCommands

    def inputIntegers = _inputIntegers

    def inputIntegers_= (newList: List[Int]) = _inputIntegers = newList

    /*
       https://en.wikipedia.org/wiki/Prime_number
       A prime is a natural number greater than 1 that has no positive divisor
       other than 1 and itself.
    */
    def isPrime(n: Int): Boolean = ((2 until (n+2)/2) forall (n % _ != 0)) && n > 1

    def removePrimes = inputIntegers = inputIntegers.filterNot(isPrime(_))

    def increment: Unit = inputIntegers = inputIntegers.map(_ + 1)

    def decrement: Unit = inputIntegers = inputIntegers.map(_ - 1)

    def runCommand(cmd: String) = {
        cmd match {
            case "inc" => increment

            case "dec" => decrement

            case "removePrime" => removePrimes

            /* In practice whoa will be never called since invalid commands fail before */
            case whoa => println(s"$cmd")
        }
    }

    def runCommands = inputCommands foreach runCommand
}
