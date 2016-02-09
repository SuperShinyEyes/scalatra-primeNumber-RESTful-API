import scala.collection.mutable.Set

class InputProcessor(private val _inputCommands: Seq[String], private var _inputIntegers: Set[Int]) {

    def inputCommands = _inputCommands

    def inputIntegers = _inputIntegers

    def inputIntegers_= (s: Set[Int]) = _inputIntegers = s

    def isPrime(n: Int): Boolean = ((2 until (n+2)/2) forall (n % _ != 0))

    def removePrimes = inputIntegers = inputIntegers.filterNot(isPrime(_))

    def increment: Unit = inputIntegers = inputIntegers.map(_ + 1)


}
