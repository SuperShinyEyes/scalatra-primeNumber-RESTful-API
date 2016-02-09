import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import java.lang.ArrayIndexOutOfBoundsException

class HelloServlet extends ScalatraServlet with JacksonJsonSupport {
    protected implicit val jsonFormats: Formats = DefaultFormats

    // ex). "numbers=1,2,3,4" => List("1","2","3","4")
    // ex). "commands=inc,removePrime" => List("inc", "removePrime")
    private def convertToList(str:String): List[String] = {
        /* split() returns Array */
        str.split("=").last.split(",").toList
    }

    private def processInput(numbers: List[String], commands: List[String]): Any = {
        val v = new InputValidator(numbers, commands)
        if (v.isValid) {
            val p = new InputProcessor(v.inputIntegers, commands)
            p.runCommands
            MessageSuccess(p.inputIntegers.mkString(", "))
        } else {
            MessageFail(v.getErrorMessage)
        }
    }

    private def isNumbersEmpty(str:String): Boolean = str.contains("numbers")

    private def getNoInputMessageFail = MessageFail("No input numbers.")

    private def isNumberParameterEmpty(s: String): Boolean = s == "numbers"

    get("/") {
        contentType = formats("json")
        Message("Hello", "World")
    }

    get("/:numbers") {
        contentType = formats("json")
        var numbers: List[String] = convertToList(params("numbers"))
        if (isNumberParameterEmpty(numbers.head)) { getNoInputMessageFail }
        else { MessageSuccess(numbers.mkString(", ")) }

    }

    get("/:numbers&:commands") {
        contentType = formats("json")
        var numbers: List[String] = convertToList(params("numbers"))

        if (isNumberParameterEmpty(numbers.head)) { getNoInputMessageFail }
        else {
            var commands: List[String] = convertToList(params("commands"))
            processInput(numbers, commands)
        }

        // MessageFail("%s %s haha".format(params("numbers"), params("commands")))

    }
}

private case class Message(greeting: String, to: String)

private case class MessageSuccess(numbers: String)

private case class MessageFail(error: String)
