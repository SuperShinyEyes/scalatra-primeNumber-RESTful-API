import org.scalatra.test.specs2.MutableScalatraSpec

class HelloServletSpec extends MutableScalatraSpec {
    addServlet(classOf[HelloServlet], "/*")

    private def testGet(params: String, reply: String) = {
        s"Get $params" should {
            s"return $reply" in {
                get(params) {
                    body must equalTo(s"""$reply""")
                }
            }
        }
    }

    testGet("/numbers=1,2,3,7,19,2141,5077&commands=removePrime", """{"numbers":""}""")

    testGet("numbers=1,1,1,3,4,5,6,99,300,3123&commands=inc,inc,inc,removePrime,removePrime,removePrime", "{\"numbers\":\"4, 6, 8, 9, 102, 303, 3126\"}")

    testGet("/numbers=3,6,23,5520,7041&commands=inc,removePrime,inc,removePrime", "{\"numbers\":\"25\"}")

    testGet("/numbers=&commands=removePrime", """{"error":"No numbers"}""")

    testGet("/numbers=1&commands=", """{"error":"No commands"}""")

    testGet("/numbers=1a&commands=inc", """{"error":"Invalid numbers: 1a. "}""")

    testGet("/numbers=1&commands=incc", """{"error":"Invalid commands: incc."}""")
}
