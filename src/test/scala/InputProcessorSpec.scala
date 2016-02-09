import org.scalatest.FlatSpec
import org.scalatest._

class InputProcessorSpec extends FlatSpec with GivenWhenThen {

    "Primes" should "be removed" in {
        Given("1,2,3,4 as an input")
        val ip1 = new InputProcessor(Seq("removePrime"), List(1,2,3,4))

        When("removePrime is called")
        ip1.removePrimes

        Then("1,2,3 should be removed")
        assertResult(true, "The number value is not right") {
            println(ip1.inputIntegers)
            ip1.inputIntegers == List(4)
        }
    }

    "Inc" should "increment all items by 1" in {
        Given("3,7,23,555")
        val ip1 = new InputProcessor(Seq("removePrime"), List(3,7,23,555))

        When("increment is called")
        ip1.increment

        Then("=> 4,8,24,556")
        assertResult(true, "The number value is not right") {
            ip1.inputIntegers == List(4,8,24,556)
        }
    }


    "Commands" can "be multiple" in {
        Given("inc, removePrime, inc, removePrime and List(3,6,23,5520,7041)")
        val ip1 = new InputProcessor(Seq("inc, removePrime, inc, removePrime"), List(3,6,23,5520,7041))

        When("increment is called")
        ip1.increment

        Then("=> %s".format(ip1.inputIntegers.mkString(",")))
        assertResult(true, "The number value is not right") {
            ip1.inputIntegers == List(4,7,24,5521,7042)
        }

        When("removePrimes is called")
        ip1.removePrimes

        Then("=> %s".format(ip1.inputIntegers.mkString(",")))
        assertResult(true, "The number value is not right") {
            ip1.inputIntegers == List(4,24,7042)
        }

        When("increment is called")
        ip1.increment

        Then("=> %s".format(ip1.inputIntegers.mkString(",")))
        assertResult(true, "The number value is not right") {
            ip1.inputIntegers == List(5,25,7043)
        }

        When("removePrimes is called")
        ip1.removePrimes

        Then("=> %s".format(ip1.inputIntegers.mkString(",")))
        assertResult(true, "The number value is not right") {
            ip1.inputIntegers == List(25)
        }


    }
}
