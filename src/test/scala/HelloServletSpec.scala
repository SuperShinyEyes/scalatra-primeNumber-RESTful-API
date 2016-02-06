import org.scalatra.test.specs2.MutableScalatraSpec

class HelloServletSpec extends MutableScalatraSpec {
  addServlet(classOf[HelloServlet], "/*")

  "GET / on HelloServlet" should {
    "return a greeting" in {
      get("/") {
        body must equalTo("""{"greeting":"Hello","to":"World"}""")
      }
    }

    "return a number json" in {
      get("/num/num=1,2&action=inc,removePrime") {
        body must equalTo("""{"numbers":"1,2","action":"action=inc,removePrime"}""")
      }
    }

    "return a number json" in {
      get("/num/num=1,2&action=inc,removePrime") {
        body must equalTo("""{"numbers":"1,2","action":"action=inc,removePrime"}""")
      }
    }

  }
}
