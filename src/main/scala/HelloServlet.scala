import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._

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

    get("/:numbers&:commands") {
        contentType = formats("json")
        val digits: List[String] = convertToList(params("numbers"))
        val commands: List[String] = convertToList(params("commands"))

        val v = new InputValidator(digits, commands)
        if (v.isValid) {
            val p = new InputProcessor(v.inputIntegers, commands)
            p.runCommands
            MessageSuccess(p.inputIntegers.mkString(", "))
        } else {
            MessageFail(v.getErrorMessage)
        }

    }
}

private case class Message(greeting: String, to: String)

private case class MessageSuccess(numbers: String)

private case class MessageFail(error: String)
