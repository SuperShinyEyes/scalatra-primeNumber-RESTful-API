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

    /* Test valid inputs */
    testGet("/numbers=1,,,,,2&commands=inc,dec", """{"numbers":"1, 2"}""")

    testGet("/numbers=-7,-3,-2,0,1,2,3,7,19,2141,5077&commands=removePrime", """{"numbers":"-7, -3, -2, 0, 1"}""")

    testGet("numbers=1,1,1,3,4,5,6,99,300,3123&commands=inc,inc,inc,removePrime,removePrime,removePrime", "{\"numbers\":\"4, 6, 8, 9, 102, 303, 3126\"}")

    testGet("/numbers=3,6,23,5520,7041&commands=inc,removePrime,inc,removePrime", "{\"numbers\":\"25\"}")

    testGet("/numbers=1,2,3,4,5,6&commands=dec", """{"numbers":"0, 1, 2, 3, 4, 5"}""")

    testGet("/numbers=1,2,3,4,5,6&commands=dec,inc,removePrime", """{"numbers":"1, 4, 6"}""")


    /* Test number sanitization */
    testGet("/numbers=01,0002&commands=inc", """{"numbers":"2, 3"}""")


    /* Test invalid inputs */
    testGet("/numbers=&commands=removePrime", """{"error":"No numbers"}""")

    testGet("/numbers=1&commands=", """{"error":"No commands"}""")

    testGet("/numbers=1.1,1a,-3.2&commands=inc", """{"error":"Invalid numbers: 1a, -3.2, 1.1. "}""")

    testGet("/numbers=1&commands=incc", """{"error":"Invalid commands: incc."}""")
}
