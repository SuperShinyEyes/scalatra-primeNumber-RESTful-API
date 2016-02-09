import org.scalatest.FlatSpec
import org.scalatest._
import scala.collection.mutable.Set

class InputProcessorSpec extends FlatSpec with GivenWhenThen {

    "Primes" should "be removed" in {
        Given("1,2,3,4 as an input")
        val ip1 = new InputProcessor(Seq("removePrime"), Set(1,2,3,4))

        When("removePrime is called")
        ip1.removePrimes

        Then("1,2,3 should be removed")
        assertResult(true, "The number value is not right") {
            println(ip1.inputIntegers)
            ip1.inputIntegers == Set(4)
        }
    }

    "Inc" should "increment all items by 1" in {
        Given("3,7,23,555")
        val ip1 = new InputProcessor(Seq("removePrime"), Set(3,7,23,555))

        When("inccrement is called")
        ip1.increment

        Then("=> 4,8,24,556")
        assertResult(true, "The number value is not right") {
            ip1.inputIntegers == Set(4,8,24,556)
        }
    }


}
