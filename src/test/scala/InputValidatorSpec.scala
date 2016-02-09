import org.scalatest.FlatSpec
import org.scalatest._
import scala.language.postfixOps
import scala.collection.mutable._

class InputValidatorSpec extends FlatSpec with GivenWhenThen {

    "Input numbers" must "be valid" in {
        Given("1, 2a as an input")
        val iv1 = new InputValidator(List("1", "2a"), List())
        Then("confirm invalidity")
        assertResult(true, "The number value is not right") {
            iv1.isValidDigits == false
        }
        And("it has 0 numbers")
        assertResult(true, "The number value is not right") {
            iv1.inputIntegers.length == 0
        }
        And("it has 1 invalid digit: 2a")
        assertResult(true, "The number value is not right") {
            iv1.getInvalidDigit == "2a"
        }

        val numbers = List.range(1,100)
        Given("%s as an input".format(numbers.mkString(",")))
        val iv2 = new InputValidator(numbers.map(_.toString), List())
        Then("confirm validity")
        assertResult(true, "The number value is not right") {
            iv2.isValidDigits == true
        }
        And("it has 99 items")
        assertResult(true, "The number value is not right") {
            iv2.inputDigits.length == 99
        }
        And("it has 0 commands")
        assertResult(true, "The number value is not right") {
            iv1.inputCommands.length == 0
        }

    }

    "Input commands" must "be valid" in {
        Given("inc, removePrime, inc as an input")
        val commands = List("inc", "removePrime", "inc")
        val iv1 = new InputValidator(List(), commands)
        Then("confirm validity")
        assertResult(true, "The number value is not right") {
            iv1.isValidCommands == true
        }
        And("it has %d commands".format(commands.length))
        assertResult(true, "The number value is not right") {
            iv1.inputCommands.length == commands.length
        }
        And("it has %d numbers".format(0))
        assertResult(true, "The number value is not right") {
            iv1.inputIntegers.length == 0
        }

        Given("inc, removePrrrrime, incc")
        val iv2 = new InputValidator(List(), List("inc", "removePrrrrime", "incc"))
        Then("confirm invalidity")
        assertResult(true, "The number value is not right") {
            iv2.isValidCommands == false
        }
        And("it has 2 invalid commands: removePrrrrime, incc")
        assertResult(true, "The number value is not right") {
            iv2.getInvalidCommands == "removePrrrrime, incc"
        }

    }

    "Input numbers&commands" must "be valid" in {
        val numbers = List.range(1,10)
        val commands = List("inc", "removePrime", "inc")
        Given("numbers=(%s)  commands=(%s) as an input".format(numbers.mkString(","), commands.mkString(",")))
        val iv1 = new InputValidator(numbers.map(_.toString), commands)
        Then("confirm validity")
        assertResult(true, "The number value is not right") {
            iv1.isValid == true
        }
        And("it has %d commands".format(commands.length))
        assertResult(true, "The number value is not right") {
            iv1.inputCommands.length == commands.length
        }
        And("it has %d numbers".format(numbers.length))
        assertResult(true, "The number value is not right") {
            iv1.inputIntegers.length == numbers.length
        }
        And("its error message is empty")
        assertResult(true, "The number value is not right") {
            iv1.getErrorMessage.size == 0
        }

        var numbers2 = List("1","2","3","4a","4a","five")
        var commands2 = List("inc", "removePrrrrime", "incc", "removePrrrrime")
        Given("numbers=(%s)  commands=(%s) as an input".format(numbers2.mkString(","), commands2.mkString(",")))
        val iv2 = new InputValidator(numbers2, commands2)
        Then("confirm invalidity")
        assertResult(true, "The number value is not right") {
            iv2.isValid == false
        }
        println(iv2.getErrorMessage)
        And("has correct error msg:\n\t   Invalid digits: five, 4a\n\t   Invalid commands: removePrrrrime, incc\n")
        assertResult(true, "The number value is not right") {
            iv2.getErrorMessage == "Invalid digits: five, 4a. Invalid commands: removePrrrrime, incc."
        }
    }
}
