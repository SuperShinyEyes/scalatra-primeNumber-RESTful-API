import org.scalatra.test.specs2.MutableScalatraSpec

class HelloServletSpec extends MutableScalatraSpec {
  addServlet(classOf[HelloServlet], "/*")

  "GET / on HelloServlet" should {
    "return a greeting" in {
      get("/") {
        body must equalTo("""{"greeting":"Hello","to":"World"}""")
      }
    }

    val request2 = "numbers=1,1,1,3,4,5,6,99,300,3123&commands=inc,inc,inc,removePrime,removePrime,removePrime"
    val reply2 = "{\"numbers\":\"4, 6, 8, 9, 102, 303, 3126\"}"
    "%s\nreturns\n%s".format(request2, reply2) in {
      get(request2) {
        body must equalTo("""%s""".format(reply2))
      }
    }

    val request3 = "/numbers=3,6,23,5520,7041&commands=inc,removePrime,inc,removePrime"
    val reply3 = "{\"numbers\":\"25\"}"
    "%s\nreturns\n%s".format(request3, reply3) in {
      get(request3) {
        body must equalTo("""%s""".format(reply3))
      }
    }

    // "return a number json" in {
    //   get("/num/num=1,2&action=inc,removePrime") {
    //     body must equalTo("""{"numbers":"1,2","action":"action=inc,removePrime"}""")
    //   }
    // }

  }
}
