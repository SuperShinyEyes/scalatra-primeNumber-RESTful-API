import org.scalatest.FlatSpec
import org.scalatest._
import scala.language.postfixOps
import scala.collection.mutable._

class InputValidatorSpec extends FlatSpec with GivenWhenThen {

    "Input numbers" must "be valid" in {
        Given("1, 2a as an input")
        val iv1 = new InputValidator(List("1", "2a"), List())
        Then("be invalid")
        assertResult(true, "The number value is not right") {
            iv1.isValidDigits == false
        }

        var input = List.range(1,100)
        Given("%s as an input".format(input.mkString(",")))
        val iv2 = new InputValidator(input.map(_.toString), List())
        Then("be valid")
        assertResult(true, "The number value is not right") {
            iv2.isValidDigits == true
        }
    }



}
