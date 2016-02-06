import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._

class HelloServlet extends ScalatraServlet with JacksonJsonSupport {
  protected implicit val jsonFormats: Formats = DefaultFormats

  def convertToList(str:String): Array[String] = {
      str.split("=")(1).split(",")
  }

  get("/") {
    contentType = formats("json")
    Message("Hello", "World")
  }

    get("/num/:num&:action") {
        // "num=1,2,3"
        val numberString: String = multiParams("num").mkString
        val nl = new NumberList
        nl.convertToList(numberString)
        val actionString: String = multiParams("action").mkString
        val actions = convertToList(actionString)
        actions.foreach(nl.doAction)
        contentType = formats("json")
        MessagePrime(nl.getSummary, actionString)
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

private case class MessagePrime(numbers: String, action: String)
