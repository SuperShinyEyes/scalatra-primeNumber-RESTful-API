# RemovePrim&Increment RESTful API using SBT-Scalatra

![demo](/file/demo.gif)

## Author
Seyoung Park<br>
[seyoung.arts.park@protonmail.com](seyoung.arts.park@protonmail.com)

## Requirements
* Scala 2.10
* Scalatra 2.2.0
* SBT IDEA 1.3.0
* ScalaTest 2.2.6

# Start the web server and reload it on file change
```bash
./sbt "~;container:start; container:reload /"

# Let's curl!
curl localhost:8080/numbers=1,2,7\&commands=inc,removePrime,inc,removePrime
# Returns {"numbers":"9"}

# If you are using a browser remove \ before &
localhost:8080/numbers=1,2,7&commands=inc,removePrime,inc,removePrime
# Returns {"numbers":"9"}
```
## Tests
```bash
./sbt test
```

## Assignment Description
Toteuta Scalalla REST-rajapinta, joka ottaa vastaan kaksi parametria. Ensimmäinen on lista numeroita ja toinen on lista operaatioita. Ensimmäisen listan numerot ovat kokonaislukuja väliltä 0-10000. Lista operaatioista voi sisältää 0, 1, tai 2 kpl seuraavista operaatioista: inc, removePrimes. Operaatio "inc" kasvattaa jokaista numerolistan alkiota yhdellä ja "removePrimes"-operaatio poistaa numerolistasta kaikki alkuluvut. Operaatiot suoritetaan annetussa järjestyksessä.

## Value properties
### 1. TDD
```scala
// src/test/scala/InputProcessorSpec.scala
"Commands" can "be multiple" in {
    Given("inc, removePrime, inc, removePrime and List(3,6,23,5520,7041)")
    val ip1 = new InputProcessor(List(3,6,23,5520,7041), List("inc, removePrime, inc, removePrime"))

    When("increment is called")
    ip1.increment

    Then("=> %s".format(ip1.inputIntegers.mkString(",")))
    assertResult(true, "The number value is not right") {
        ip1.inputIntegers == List(4,7,24,5521,7042)
    }

    When("removePrimes is called")
    ip1.removePrimes

    Then("=> %s".format(ip1.inputIntegers.mkString(",")))
    assertResult(true, "The number value is not right") {
        ip1.inputIntegers == List(4,24,7042)
    }

    When("increment is called")
    ip1.increment

    Then("=> %s".format(ip1.inputIntegers.mkString(",")))
    assertResult(true, "The number value is not right") {
        ip1.inputIntegers == List(5,25,7043)
    }

    When("removePrimes is called")
    ip1.removePrimes

    Then("=> %s".format(ip1.inputIntegers.mkString(",")))
    assertResult(true, "The number value is not right") {
        ip1.inputIntegers == List(25)
    }
}
```
### 2. DRY principle
```scala
// src/test/scala/HelloServletSpec.scala
private def testGet(params: String, reply: String) = {
    s"Get $params" should {
        s"return $reply" in {
            get(params) {
                body must equalTo(s"""$reply""")
            }
        }
    }
}

/* Test invalid inputs */
testGet("/numbers=&commands=removePrime", """{"error":"No numbers"}""")
testGet("/numbers=1&commands=", """{"error":"No commands"}""")
testGet("/numbers=1.1,1a,-3.2&commands=inc", """{"error":"Invalid numbers: 1a, -3.2, 1.1. "}""")
testGet("/numbers=1&commands=incc", """{"error":"Invalid commands: incc."}""")
}
```

### 3. Concise code
```scala
// src/main/scala/InputValidator.scala
def isPrime(n: Int): Boolean = ((2 until (n+2)/2) forall (n % _ != 0)) && n > 1
def removePrimes = inputIntegers = inputIntegers.filterNot(isPrime(_))
def increment: Unit = inputIntegers = inputIntegers.map(_ + 1)
```

### 4. Scalability & Scope
```scala
// src/main/scala/InputProcessor.scala
private var _inputIntegers: List[Int]
def inputIntegers = _inputIntegers
def inputIntegers_= (newList: List[Int]) = _inputIntegers = newList
```

### 5. Readability
```scala
// src/main/scala/InputValidator.scala
def getDistinctInvalidNumberAsString: String = {
    /*
       validNumbers = { x | for all x which is an Integer  }
       ex).
            "1" => 1
            "1a" => fail
            "01" => 1
            "1.1" => fail
            "-3" => -3
    */
    val invalids: Set[String] = Set()
    for (number <- inputNumbers) {
        try {
            number.toInt
        } catch {
            case ex: NumberFormatException => invalids += number
        }
    }
    invalids.mkString(", ")
}

def getDistinctInvalidCommandsAsString: String = {
    (inputCommands.distinct diff allowedCommands).mkString(", ")
}

def getErrorMessage: String = {
    var msg = ""
    if (! isValidNumbers) { msg = "Invalid numbers: %s. ".format(getDistinctInvalidNumberAsString)}
    if (! isValidCommands) { msg += "Invalid commands: %s.".format(getDistinctInvalidCommandsAsString)}
    msg
}
```

### 6. Systematic Development
```
commit 7309556883d26b7f60b4a1bc2088e8f3a084962e
Author: Young Park <i.make.seyoung@gmail.com>
Date:   Wed Feb 10 10:17:18 2016 +0200

    Test negative/float inputs

commit e3b1c91ce50516bcfbd1300f8e5356cdc831300a
Author: Young Park <i.make.seyoung@gmail.com>
Date:   Wed Feb 10 10:11:14 2016 +0200

    Redefine primality

    https://en.wikipedia.org/wiki/Prime_number
           A prime is a natural number greater than 1 that has no positive divisor
           other than 1 and itself.

commit 5cf6f0c4daae95fbccd34adcdf69d94b6770b7c7
Author: Young Park <i.make.seyoung@gmail.com>
Date:   Wed Feb 10 01:34:22 2016 +0200

    Relocate prime file to file/

commit a2eba586647d9a3ad682e3e839f30c3754ff3851
Author: Young Park <i.make.seyoung@gmail.com>
Date:   Wed Feb 10 01:31:26 2016 +0200

    Add isPrime tests

commit 1fae741212f6ef60e8987015f917080a0dbcd4ea
Author: Young Park <i.make.seyoung@gmail.com>
Date:   Wed Feb 10 01:14:40 2016 +0200

    Add primes file

commit df62764aad653f56968ccd010de2529822258094
Author: Young Park <i.make.seyoung@gmail.com>
Date:   Wed Feb 10 00:12:19 2016 +0200

    Update README
```
