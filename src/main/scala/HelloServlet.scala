import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._

class HelloServlet extends ScalatraServlet with JacksonJsonSupport {
  protected implicit val jsonFormats: Formats = DefaultFormats

  def convertToList(str:String): List[String] = {
      // split() returns toArray
      str.split("=")(1).split(",").toList
  }

  get("/") {
    contentType = formats("json")
    Message("Hello", "World")
  }

    get("/:numbers&:commands") {
        contentType = formats("json")
        // "num=1,2,3"
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

  get("/r/") {
    val numbers:String = params("list")
    val action:String = params("action")
    contentType = formats("json")
    Message(numbers, action)
  }

  get("/korea") {
    contentType = formats("json")
    Message("Annyung", "World")
  }

  get("/name/:name") {
    contentType = formats("json")
    Message({params("name")}, "World")
  }
}

private case class Message(greeting: String, to: String)

private case class MessagePrime(numbers: String, commands: String)

private case class MessageSuccess(numbers: String)

private case class MessageFail(error: String)
