import org.scalatest.FlatSpec
import org.scalatest._
import scala.language.postfixOps
import scala.collection.mutable._

class InputValidatorSpec extends FlatSpec with GivenWhenThen {

    "Wrong numbers" must "fail" in {
        Given("1, 2a as an input")
        val v = new InputValidator(List("1", "2a"), List())
        Then("confirm invalidity")
        assertResult(true, "The number value is not right") {
            v.isValidNumbers == false
        }
        And("it has 0 numbers")
        assertResult(true, "The number value is not right") {
            v.inputIntegers.length == 0
        }
        And("it has 1 invalid number: 2a")
        assertResult(true, "The number value is not right") {
            v.getDistinctInvalidNumberAsString == "2a"
        }
    }

    "Correct numbers" must "succeed" in {
        val numbers = List.range(1,100)
        Given("%s as an input".format(numbers.mkString(",")))
        val v = new InputValidator(numbers.map(_.toString), List())
        Then("confirm validity")
        assertResult(true, "The number value is not right") {
            v.isValidNumbers == true
        }
        And("it has 99 items")
        assertResult(true, "The number value is not right") {
            v.inputNumbers.length == 99
        }
        And("it has 0 commands")
        assertResult(true, "The number value is not right") {
            v.inputCommands.length == 0
        }

    }

    "Wrong commands" must "fail" in {
        Given("inc, removePrrrrime, incc")
        val v = new InputValidator(List(), List("inc", "removePrrrrime", "incc"))
        Then("confirm invalidity")
        assertResult(true, "The number value is not right") {
            v.isValidCommands == false
        }
        And("it has 2 invalid commands: removePrrrrime, incc")
        assertResult(true, "The number value is not right") {
            v.getDistinctInvalidCommandsAsString == "removePrrrrime, incc"
        }
    }

    "Correct numbers and commands" must "succeed" in {
        val numbers = List.range(1,10)
        val commands = List("inc", "removePrime", "inc")
        Given("numbers=(%s)  commands=(%s) as an input".format(numbers.mkString(","), commands.mkString(",")))
        val v = new InputValidator(numbers.map(_.toString), commands)
        Then("confirm validity")
        assertResult(true, "The number value is not right") {
            v.isValid == true
        }
        And("it has %d commands".format(commands.length))
        assertResult(true, "The number value is not right") {
            v.inputCommands.length == commands.length
        }
        And("it has %d numbers".format(numbers.length))
        assertResult(true, "The number value is not right") {
            v.inputIntegers.length == numbers.length
        }
        And("its error message is empty")
        assertResult(true, "The number value is not right") {
            v.getErrorMessage.size == 0
        }
    }

    "Wrong numbers and commands" must "Fail" in {
        val numbers = List("1","2","3","4a","4a","five")
        val commands = List("inc", "removePrrrrime", "incc", "removePrrrrime")
        Given("numbers=(%s)  commands=(%s) as an input".format(numbers.mkString(","), commands.mkString(",")))
        val v = new InputValidator(numbers, commands)
        Then("confirm invalidity")
        assertResult(true, "The number value is not right") {
            v.isValid == false
        }
        println(v.getErrorMessage)
        And("has correct error msg:\n\t   Invalid numbers: five, 4a\n\t   Invalid commands: removePrrrrime, incc\n")
        assertResult(true, "The number value is not right") {
            v.getErrorMessage == "Invalid numbers: five, 4a. Invalid commands: removePrrrrime, incc."
        }
    }
}
