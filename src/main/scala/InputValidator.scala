import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Set
import java.lang.NumberFormatException

class InputValidator(private var _inputNumbers: List[String], private val _inputCommands: List[String]) {
    /*
      Inputs should be validated and sanitized.
      There are only two valid commands: inc(increment), and removePrime
    */

    private def convertListStringsToDistinctListIntegers(ls: List[String]): List[Int] = {
        ls.map(_.toInt).distinct
    }

    private var _inputIntegers: List[Int] = if (isValidNumbers) convertListStringsToDistinctListIntegers(inputNumbers) else List()

    val allowedCommands = Vector("inc", "dec", "removePrime")

    def inputNumbers_= (s: List[String]) = _inputNumbers = s

    def inputNumbers = _inputNumbers

    def inputCommands = _inputCommands

    def inputIntegers_= (newList: List[Int]) = _inputIntegers = newList

    def inputIntegers = _inputIntegers

    def isValidNumber(n: String): Boolean = {
        try { n.toInt; true } catch { case ex: NumberFormatException => false }
    }

    def isValidNumbers: Boolean = {
        inputNumbers forall isValidNumber
    }

    def isValidCommands: Boolean = inputCommands.forall(allowedCommands.contains(_))

    def isValid: Boolean = isValidNumbers && isValidCommands

    def getDistinctInvalidNumberAsString: String = {
        /*
           validNumbers = { x | for all x which is an Integer  }
           ex).
                "1" => 1
                "1a" => fail
                "01" => 1
                "1.1" => fail
                "-3" => -3
        */
        val invalids: Set[String] = Set()
        for (number <- inputNumbers) {
            try {
                number.toInt
            } catch {
                case ex: NumberFormatException => invalids += number
            }
        }
        invalids.mkString(", ")
    }

    def getDistinctInvalidCommandsAsString: String = {
        (inputCommands.distinct diff allowedCommands).mkString(", ")
    }

    def getErrorMessage: String = {
        var msg = ""
        if (! isValidNumbers) { msg = "Invalid numbers: %s. ".format(getDistinctInvalidNumberAsString)}
        if (! isValidCommands) { msg += "Invalid commands: %s.".format(getDistinctInvalidCommandsAsString)}
        msg
    }
}
