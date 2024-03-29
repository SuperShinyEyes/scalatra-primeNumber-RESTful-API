import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._

class HelloServlet extends ScalatraServlet with JacksonJsonSupport {
    protected implicit val jsonFormats: Formats = DefaultFormats

    // ex). "numbers=1,2,3,4" => List("1","2","3","4")
    // ex). "commands=inc,removePrime" => List("inc", "removePrime")
    // ex). "commands=inc,removePrime,    dec" => List("inc", "removePrime", "dec")
    private def convertToList(str:String): List[String] = {
        /* split() returns Array */
        str.split("=").last.split(",").toList.map((x$1:String) => x$1.trim).filterNot(_ == "")
    }

    private def getMessageAfterProcess(numbers: List[String],
            commands: List[String]): Message = {
        val v = new InputValidator(numbers, commands)
        if (v.isValid) {
            val p = new InputProcessor(v.inputIntegers, commands)
            p.runCommands
            MessageSuccess(p.inputIntegers.mkString(", "))
        } else {
            MessageFail(v.getErrorMessage)
        }
    }

    private def getEmptyParameterMessageFail(param: String) = MessageFail(s"No $param")

    private def isParameterEmpty(param: String, arg: String): Boolean = param == arg

    get("/:numbers&:commands") {
        contentType = formats("json")
        var numbers: List[String] = convertToList(params("numbers"))
        var commands: List[String] = convertToList(params("commands"))

        if (isParameterEmpty("numbers", numbers.head)) { getEmptyParameterMessageFail("numbers") }
        else if (isParameterEmpty("commands", commands.head)) { getEmptyParameterMessageFail("commands") }
        else {
            getMessageAfterProcess(numbers, commands)
        }
    }
}


abstract class Message

private case class MessageSuccess(numbers: String) extends Message

private case class MessageFail(error: String) extends Message
