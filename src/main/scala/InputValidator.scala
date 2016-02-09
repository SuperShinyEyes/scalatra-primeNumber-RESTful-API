import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Set
import java.lang.NumberFormatException

class InputValidator(private var _inputDigits: List[String], private val _inputCommands: List[String]) {
    /*
      Inputs should be validated and sanitized.
      There are only two valid commands: inc(increment), and removePrime
      Digit validation and sanitization works as following:
        "1" => 1
        "1a" => fail
        "01" => 1
    */

    private var _inputIntegers: List[Int] = List()

    val allowedCommands = Vector("inc", "removePrime")

    def inputDigits_= (s: List[String]) = _inputDigits = s

    def inputDigits = _inputDigits

    def inputCommands = _inputCommands

    def inputIntegers_= (newList: List[Int]) = _inputIntegers = newList

    def inputIntegers = _inputIntegers

    def isValidDigits: Boolean = {
        /*
          Try to convert digits into integers as well as removing duplicates
          When failed, stop and return false
        */
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
        if (! isValidDigits) { msg = "Invalid digits: %s. ".format(getInvalidDigit)}
        if (! isValidCommands) { msg += "Invalid commands: %s.".format(getInvalidCommands)}
        return msg
    }
}
