import org.scalatra.test.specs2.MutableScalatraSpec

class HelloServletSpec extends MutableScalatraSpec {
    addServlet(classOf[HelloServlet], "/*")

    "GET / on HelloServlet" should {
        "return a greeting" in {
            get("/") {
                body must equalTo("""{"greeting":"Hello","to":"World"}""")
            }
        }
    }

    "Get /numbers=1,2,3" should {
        "return 1,2,3" in {
            get("/numbers=1,2,3") {
                body must equalTo("""{"numbers":"numbers=1,2,3"}""")
            }
        }
    }

    "Get /numbers=1,2,3,7,19,2141,5077&commands=removePrime" should {
        "return {numbers:\"\"}" in {
            get("/numbers=1,2,3,7,19,2141,5077&commands=removePrime") {
                body must equalTo("""{"numbers":""}""")
            }
        }
    }

    "Get /numbers=1,1,1,3,4,5,6,99,300,3123&commands=inc,inc,inc,removePrime,removePrime,removePrime" should {
        "return {\"numbers\":\"4, 6, 8, 9, 102, 303, 3126\"}" in {
            val request2 = "numbers=1,1,1,3,4,5,6,99,300,3123&commands=inc,inc,inc,removePrime,removePrime,removePrime"
            val reply2 = "{\"numbers\":\"4, 6, 8, 9, 102, 303, 3126\"}"
            get(request2) {
                body must equalTo("""%s""".format(reply2))
            }
        }
    }

    "Get /numbers=3,6,23,5520,7041&commands=inc,removePrime,inc,removePrime" should {
        "return {\"numbers\":\"25\"}" in {
            val request3 = "/numbers=3,6,23,5520,7041&commands=inc,removePrime,inc,removePrime"
            val reply3 = "{\"numbers\":\"25\"}"
            get(request3) {
                body must equalTo("""%s""".format(reply3))
            }
        }
    }

}
