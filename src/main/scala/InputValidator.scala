import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Set
import java.lang.NumberFormatException

class InputValidator(private var _inputNumbers: List[String], private val _inputCommands: List[String]) {
    /*
      Inputs should be validated and sanitized.
      There are only two valid commands: inc(increment), and removePrime
      Digit validation and sanitization works as following:
        "1" => 1
        "1a" => fail
        "01" => 1
        "1.1" => fail
        "-3" => -3
    */

    private var _inputIntegers: List[Int] = List()

    val allowedCommands = Vector("inc", "removePrime")

    def inputNumbers_= (s: List[String]) = _inputNumbers = s

    def inputNumbers = _inputNumbers

    def inputCommands = _inputCommands

    def inputIntegers_= (newList: List[Int]) = _inputIntegers = newList

    def inputIntegers = _inputIntegers

    def isValidNumbers: Boolean = {
        /*
          Try to convert numbers into integers as well as removing duplicates
          When failed, stop and return false
        */
        try {
            inputIntegers = inputNumbers.map(_.toInt).toSet.toList.sorted
        } catch {
            case ex: NumberFormatException => return false
        }
        return true
    }

    def isValidCommands: Boolean = inputCommands.forall(allowedCommands.contains(_))

    def isValid: Boolean = isValidNumbers && isValidCommands

    def getInvalidNumber: String = {
        // .getClass
        val invalids: Set[String] = Set()
        for (number <- inputNumbers) {
            try {
                number.toInt
            } catch {
                case ex: NumberFormatException => invalids += number
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
        if (! isValidNumbers) { msg = "Invalid numbers: %s. ".format(getInvalidNumber)}
        if (! isValidCommands) { msg += "Invalid commands: %s.".format(getInvalidCommands)}
        return msg
    }
}
