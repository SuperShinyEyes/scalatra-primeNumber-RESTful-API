import org.scalatest.FlatSpec
import org.scalatest._
import scala.io.Source

class InputProcessorSpec extends FlatSpec with GivenWhenThen {

    /* Test isPrime function with 1000 primes from utm.edu */
    "Prime function" must "work" in {
        Given("1000 primes from https://primes.utm.edu/lists/small/1000.txt")
        /* Convert to Set because we are using primes twice */
        val primes: Set[Int] = Source.fromFile("file/1000primesWithNewline.txt").getLines.toSet.map((x$1:String) => x$1.toInt)
        val nonPrimes: Set[Int] = Iterator.range(2, 7920).toSet -- primes
        val ip = new InputProcessor(List(), List())

        Then("All primes are confirmed as primes")
        assertResult(true) {
            primes.forall(ip.isPrime(_))
        }

        And("All non-primes(2~7919) are confirmed as non-primes")
        assertResult(true) {
            nonPrimes.forall(!ip.isPrime(_))
        }
    }


    "Primes" should "be removed" in {
        Given("1,2,3,4 as an input")
        val ip1 = new InputProcessor(List(1,2,3,4),List("removePrime"))

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
        val ip1 = new InputProcessor(List(3,7,23,555),List("removePrime"))

        When("increment is called")
        ip1.increment

        Then("=> 4,8,24,556")
        assertResult(true, "The number value is not right") {
            ip1.inputIntegers == List(4,8,24,556)
        }
    }


    "Commands" can "be multiple" in {
        Given("inc, removePrime, inc, removePrime and List(3,6,23,5520,7041)")
        val ip1 = new InputProcessor(List(3,6,23,5520,7041), List("inc, removePrime, inc, removePrime"))

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
