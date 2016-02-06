import org.scalatest.FlatSpec
import org.scalatest.Assertions._

class NumberSpec extends FlatSpec {
    val one = new Number
    one.value = "1"
    assertResult(true) {
        one.value == 1
    }
}
