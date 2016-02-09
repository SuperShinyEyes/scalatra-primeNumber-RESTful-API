import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Set
import java.lang.NumberFormatException

class InputValidator(private var _inputDigits: List[String], private val _inputCommands: List[String]) {

    def init = inputDigits = inputDigits.toSet.toList

    private var _inputIntegers: List[Int] = List()

    val allowedCommands = Vector("inc", "removePrime")

    def inputDigits_= (s: List[String]) = _inputDigits = s

    def inputDigits = _inputDigits

    def inputCommands = _inputCommands

    def inputIntegers_= (i: List[Int]) = _inputIntegers = i

    def inputIntegers = _inputIntegers

    def isValidDigits: Boolean = {
        try {
            inputIntegers = inputDigits.map(_.toInt).toSet.toList.sorted
        } catch {
            case ex: NumberFormatException => println("Invalid digits!")
            return false
        }
        return true
    }

    def isValidCommands: Boolean = inputCommands.forall(allowedCommands.contains(_))

    def isValid: Boolean = isValidDigits && isValidCommands

    def getInvalidDigit: String = {
        // .getClass
        val invalids: Set[String] = Set()
        for (digit <- inputDigits) {
            try {
                digit.toInt
            } catch {
                case ex: NumberFormatException => invalids += digit
            }
        }
        return invalids.mkString(", ")
    }

    def getInvalidCommands: String = {
        val uniqueCommands = inputCommands.toSet
        val invalidCommands = uniqueCommands diff allowedCommands.toSet
        return invalidCommands.mkString(", ")
    }

    def getErrorMessage: String = {
        var msg = ""
        if (! isValidDigits) { msg = "Invalid digits: %s\n".format(getInvalidDigit)}
        if (! isValidCommands) { msg += "Invalid commands: %s\n".format(getInvalidCommands)}
        return msg
    }
}
