import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Set

class InputValidator {

    private val _inputDigits: Seq[String] = Seq()
    private val _inputCommands: Seq[String] = Seq()

    private val _inputIntegers: Set[Int] = Set()

    def inputDigits = _inputDigits

    def inputCommands = _inputCommands

    def inputIntegers = _inputIntegers

    def isValidDigits: Boolean = true

    def isValidCommands: Boolean = true

    def isValid: Boolean = true
}
