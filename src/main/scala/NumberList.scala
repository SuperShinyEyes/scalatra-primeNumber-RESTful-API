import scala.collection.mutable.ArrayBuffer
import java.io.IOException

class NumberList {
    // private val _stringNumberList: Seq[String]
    // private val _intNumberList: Array[Number]
    val buf = ArrayBuffer.empty[Number]

    def createNumberObject(n:String): Unit = {
        val temp = new Number
        try {
            temp.value = n
            buf += temp
        } catch {
            case ex: IOException => println(s"!!!IOException: $n is not a proper number")

        }
    }

    def convertToList(str:String): Unit = {
        val ignore = "num="
        val stringWithOutParameter: String = str.dropWhile(c => ignore.indexOf(c) >= 0)
        stringWithOutParameter.split(",").foreach( createNumberObject )
    }

    def getSummary: String = {
        val numbers = ArrayBuffer.empty[Int]
        buf.foreach(numbers += _.value)
        numbers.mkString(",")
    }
    // def stringNumberList_ = (str: String) = _stringNumberList =

    def doAction(action:String): Unit = {
        action match {
            case "inc" => buf.foreach(_.increment)

            case "removePrime" => buf.filterNot(_.isPrime)

            case whoa => println(s"$action")
        }
    }

}

// println(nl.buf)
