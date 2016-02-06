import org.scalatest.FlatSpec
import org.scalatest.Assertions._

class NumberSpec extends FlatSpec {
    val one = new Number
    one.value = "1"
    println(one.value)

    "1" should "equal to Int(1)" in {
        assertResult(true, "The number value is not right") {
            one.value == 1
        }
    }

    it must "be prime" in {
        assertResult(true) {
            one.isPrime
        }
    }

    "Number" must "be digits-only" in {
        val number = new Number
        intercept[java.io.IOException] {
            number.value = "asdf"
        }
    }

    "Number" must "have digits in right order" in {
        val two = new Number
        intercept[java.io.IOException] {
            two.value = "01"
        }
    }

}
