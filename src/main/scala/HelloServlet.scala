import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._

class HelloServlet extends ScalatraServlet with JacksonJsonSupport {
  protected implicit val jsonFormats: Formats = DefaultFormats

  get("/") {
    contentType = formats("json")
    Message("Hello", "World")
  }

  get("/num/:num") {
    val numbers:Seq[String] = multiParams("num")
    println(numbers)
    println(numbers.mkString slice(4, -1))
    contentType = formats("json")
    Message(numbers.mkString, "World")
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
