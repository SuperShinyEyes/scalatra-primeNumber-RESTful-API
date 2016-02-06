import org.scalatest.FlatSpec
import org.scalatest.Assertions._

class NumberListSpec extends FlatSpec {

    "num=1,2,3" should "have three itmes" in {
        assertResult(true){
            val nl = new NumberList
            nl.convertToList("num=1,2,3")
            println("=====Inside test=====")
            println(nl.buf(0).value, nl.buf(1).value)
            nl.buf.length == 3
        }
    }

    "num=1a,2,3,01" should "have two itmes" in {
        assertResult(true){
            val nl = new NumberList
            nl.convertToList("num=1a,2,3")
            println(nl.buf)
            nl.buf.length == 2
        }
    }

}
