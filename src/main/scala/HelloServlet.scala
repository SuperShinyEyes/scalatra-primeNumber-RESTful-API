import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import java.lang.ArrayIndexOutOfBoundsException

class HelloServlet extends ScalatraServlet with JacksonJsonSupport {
    protected implicit val jsonFormats: Formats = DefaultFormats

    // ex). "numbers=1,2,3,4" => List("1","2","3","4")
    // ex). "commands=inc,removePrime" => List("inc", "removePrime")
    def convertToList(str:String): List[String] = {
        /* split() returns Array */
        str.split("=")(1).split(",").toList
    }

    get("/") {
        contentType = formats("json")
        Message("Hello", "World")
    }
    get("/:numbers") {
        contentType = formats("json")
        MessageSuccess(params("numbers"))
    }

    get("/:numbers&:commands") {
        contentType = formats("json")
        var digits: List[String] =  List()
        var commands: List[String] = List()
        try {
            digits = convertToList(params("numbers"))
        } catch {
            case ex: ArrayIndexOutOfBoundsException => MessageFail("Invalid numbers")
        }

        try {
            commands = convertToList(params("commands"))
        } catch {
            case ex: ArrayIndexOutOfBoundsException => MessageFail("Invalid numbers")
        }

        val v = new InputValidator(digits, commands)
        if (v.isValid) {
            val p = new InputProcessor(v.inputIntegers, commands)
            p.runCommands
            // MessageSuccess(p.inputIntegers.mkString(", "))
            MessageSuccess("hahaha")
        } else {
            MessageFail(v.getErrorMessage)
        }

    }
}

private case class Message(greeting: String, to: String)

private case class MessageSuccess(numbers: String)

private case class MessageFail(error: String)
